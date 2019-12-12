package DormitoryProgram;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.StringTokenizer;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class AdminFinalSelected extends JFrame {
   OutputStream os = null;
   InputStream is = null;
   
   int count = 0;

   private JTextField textField;
   
   public AdminFinalSelected(Socket socket) {
      setTitle("입사자 등록 및 조회");
      String title[] = { "학번", "생활관", "호실번호" };
      DefaultTableModel model = new DefaultTableModel(title, 0);
      
      JTable table = new JTable(model);
      table.setCellSelectionEnabled(true);
      table.setFocusable(false);
      table.setColumnSelectionAllowed(false);
      table.setRowSelectionAllowed(false);
      table.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            int row = table.getSelectedRow();
            int column = table.getSelectedColumn();
            Object value = table.getValueAt(row, column);
         }
      });
      table.setBounds(12, 379, 860, 264);

      getContentPane().setLayout(null);

      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(12, 242, 860, 387);
      getContentPane().add(scrollPane);
      scrollPane.setViewportView(table);

      textField = new JTextField();
      textField.setForeground(Color.WHITE);
      textField.setText("입사자 등록 및 조회");
      textField.setHorizontalAlignment(SwingConstants.CENTER);
      textField.setFont(new Font("HY견고딕", Font.PLAIN, 16));
      textField.setEditable(false);
      textField.setColumns(10);
      textField.setBackground(new Color(112, 128, 144));
      textField.setBounds(12, 10, 200, 30);
      getContentPane().add(textField);

      JButton btnNewButton = new JButton("조회");
      btnNewButton.addActionListener(new ActionListener() {
         int cnt = 0;

         public void actionPerformed(ActionEvent e) {
            cnt++;

            if (cnt <= 1) {
               try {
                  String str1, str2, str3;
                  String arr[] = { "", "", "" };

                  os = socket.getOutputStream();
                  is = socket.getInputStream();

                  Protocol protocol = new Protocol();
                  byte[] buf = protocol.getPacket(); // QueryRReulst

                  protocol = new Protocol(Protocol.PT_REQ_OPEN);
                  protocol.setKindofOpen("7");

                  os.write(protocol.getPacket());

                  is.read(buf);

                  protocol.setPacket(buf[0], buf);
                  String str6 = protocol.getOpenQueryResult();
                  StringTokenizer stkr = new StringTokenizer(str6, "#");

                  if (buf[0] == 19) {
                     program: while (stkr.hasMoreTokens()) {

                        str1 = stkr.nextToken();
                        if (str1 == "") {
                           break program;
                        }
                        str2 = stkr.nextToken();
                        int i3 = Integer.parseInt(stkr.nextToken());

                        arr[0] = str1;
                        arr[1] = str2;
                        arr[2] = i3 + "";
                        model.addRow(arr);
                        count++;
                     }
                  }
                  // 리소스 반환
               } catch (Exception e1) {
                  e1.printStackTrace();
               }
            } else if (cnt > 1) {
               btnNewButton.setEnabled(false);
               JOptionPane.showMessageDialog(null, "이미 조회 되었습니다.");
            }
         }
      });
      btnNewButton.setBounds(657, 209, 95, 23);
      getContentPane().add(btnNewButton);

      JButton btnNewButton_1 = new JButton("저장");
      btnNewButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            try {
               os = socket.getOutputStream();
               is = socket.getInputStream();
               int cnt = 0;
               String data = "";
               
               Protocol protocol = new Protocol();
               byte[] buf = protocol.getPacket();

               table.setEditingColumn(3);

               while (cnt < count) {
                  System.out.println(cnt + " " + count);
                  String studentNum = table.getValueAt(cnt, 0).toString();
                  String roomNum = table.getValueAt(cnt, 2).toString();

                  data += studentNum + "#" + roomNum;

                  if (cnt < count - 1) {
                     data += "#";
                  }
                  cnt++;
                 
               }
               System.out.println(data);

               if (data == null) {
                  JOptionPane.showMessageDialog(null, "저장할 내용이 없습니다.");
               } else {

                  JOptionPane.showMessageDialog(null, "정상적으로 저장 되었습니다.");

                  protocol = new Protocol(Protocol.SEND_DATA);
                  protocol.setData(data);
                  protocol.setWhere(Protocol.UPDATE_ROOMNUMBER);

                  System.out.println("서버에게 데이터 전송");
                  os.write(protocol.getPacket());

                  program: while (true) {
                     is.read(buf);
                     int packetType = buf[0];

                     protocol.setPacket(packetType, buf);

                     switch (packetType) {
                     case Protocol.RES_DATA:
                        System.out.println("서버가 데이터 등록 결과 전송.");
                        break program;
                     }
                  }
               }
            }

            catch (IOException ioe) {
               ioe.getStackTrace();
            }

         }

      });
      btnNewButton_1.setBounds(777, 209, 95, 23);
      getContentPane().add(btnNewButton_1);

      setVisible(true);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setSize(902, 676);
   }
}