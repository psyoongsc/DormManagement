// StudentDorInfo.java 전체 (학생이 생활관정보조회)


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
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.ScrollPaneConstants;

public class StudentDorInfo extends JFrame {
   OutputStream os = null;
   InputStream is = null;
   
   Protocol protocol = new Protocol();
   
   private JTextField textField;
   private JTable table;
   
   public StudentDorInfo(LoginInfo info, Socket socket) {
      String title[]= {"생활관","급식실","기숙기간","생활관사용료","기타공공요금","비용_7일식","비용_5일식","모집인원_남","모집인원_여"};
      DefaultTableModel model = new DefaultTableModel(title, 0)
      {
          public boolean isCellEditable(int rowIndex, int mColIndex) {
              return false;
          }
      };  

      getContentPane().setLayout(null);
      
      textField = new JTextField();
      textField.setForeground(Color.WHITE);
      textField.setText("생활관 정보");
      textField.setHorizontalAlignment(SwingConstants.CENTER);
      textField.setFont(new Font("HY견고딕", Font.PLAIN, 16));
      textField.setEditable(false);
      textField.setColumns(10);
      textField.setBackground(new Color(112, 128, 144));
      textField.setBounds(12, 37, 130, 30);
      getContentPane().add(textField);
      
      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
      scrollPane.setEnabled(false);
      scrollPane.setBounds(12, 161, 792, 259);
      scrollPane.setFocusable(true);
      getContentPane().add(scrollPane);
      
      table = new JTable(model);
      table.setCellSelectionEnabled(true);
      table.setFocusable(false);
      table.setColumnSelectionAllowed(false);
      table.setRowSelectionAllowed(false);

      scrollPane.setViewportView(table);
      
      JButton inquiry = new JButton("조회");
      inquiry.addActionListener(new ActionListener() {
         int cnt = 0;

         public void actionPerformed(ActionEvent e) {
            cnt++;

            if (cnt <= 1) {
               try {
                  String str1, str2, str3;
                  String arr[]={"","","","","","","","",""};
                  
                  os = socket.getOutputStream();
                  is = socket.getInputStream();

                  Protocol protocol = new Protocol();
                  byte[] buf = protocol.getPacket(); // QueryRReulst

                  protocol = new Protocol(Protocol.PT_REQ_OPEN);
                  protocol.setKindofOpen("8");

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
                        str3 = stkr.nextToken();
                        int i4 = Integer.parseInt(stkr.nextToken());
                        int i5 = Integer.parseInt(stkr.nextToken());
                        int i6 = Integer.parseInt(stkr.nextToken());
                        int i7 = Integer.parseInt(stkr.nextToken());
                        int i8 = Integer.parseInt(stkr.nextToken());
                        int i9 = Integer.parseInt(stkr.nextToken());
                        
                        arr[0] = str1;
                        arr[1] = str2;
                        arr[2] = str3;
                        arr[3] = i4 + "";
                        arr[4] = i5 + "";
                        arr[5] = i6 + "";
                        arr[6] = i7 + "";
                        arr[7] = i8 + "";
                        arr[8] = i9 + "";
                        model.addRow(arr);
                     }
                  }
                  // 리소스 반환
               } catch (Exception e1) {
                  e1.printStackTrace();
               }
            } else if (cnt > 1) {
               inquiry.setEnabled(false);
               JOptionPane.showMessageDialog(null, "이미 조회 되었습니다.");
            }
         }
      });
      inquiry.setBounds(709, 127, 95, 23);
      getContentPane().add(inquiry);
      setVisible(true);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setSize(824,469);
      //2 4 6 3 7 9 11 13 15
   }
}