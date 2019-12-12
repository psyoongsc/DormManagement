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

public class AdminDeposit extends JFrame {

   OutputStream os = null;
   InputStream is = null;

   private JTextField textField;
   int count = 0;
   int savecnt = 0;
   int order = 0;

   public AdminDeposit(Socket socket) {
      setTitle("입금 여부 조회");
      String title[] = { "학번", "입사비용", "가상계좌번호", "관비납비여부" };
      DefaultTableModel model = new DefaultTableModel(title, 0);
      JTable table = new JTable(model);
      table.setFocusable(false);
      table.setBackground(new Color(112, 128, 144));
      table.setBounds(12, 379, 860, 264);

      getContentPane().setLayout(null);

      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(12, 242, 860, 387);
      getContentPane().add(scrollPane);
      scrollPane.setViewportView(table);

      textField = new JTextField();
      textField.setForeground(Color.WHITE);
      textField.setText("입금 여부 조회");
      textField.setHorizontalAlignment(SwingConstants.CENTER);
      textField.setFont(new Font("HY견고딕", Font.PLAIN, 16));
      textField.setEditable(false);
      textField.setColumns(10);
      textField.setBackground(new Color(112, 128, 144));
      textField.setBounds(12, 23, 166, 30);
      getContentPane().add(textField);

      JButton btnNewButton = new JButton("조회");
      btnNewButton.addActionListener(new ActionListener() {
         int cnt = 0;

         public void actionPerformed(ActionEvent e) {
            cnt++;

            if (cnt <= 1) {
               try {
                  String str1, str3, str4;
                  String arr[] = { "", "", "", "" };

                  os = socket.getOutputStream();
                  is = socket.getInputStream();

                  Protocol protocol = new Protocol();
                  byte[] buf = protocol.getPacket(); // QueryRReulst

                  protocol = new Protocol(Protocol.PT_REQ_OPEN);
                  protocol.setKindofOpen("5");
                  System.out.println(protocol.getKindofOpen());
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
                        int i1 = Integer.parseInt(stkr.nextToken());
                        
                        str3 = stkr.nextToken();
                        str4 = stkr.nextToken();
                        arr[0] = str1;
                        arr[1] = i1 + "";
                        arr[2] = str3;
                        arr[3] = str4;
                        
                        count ++;
                        
                        model.addRow(arr);
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
      btnNewButton.setBounds(670, 193, 95, 23);
      getContentPane().add(btnNewButton);

      JButton btnNewButton_1 = new JButton("저장");
      btnNewButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
        	 order ++;
        	 if( order == 1) {
            try {
            	System.out.println("저장!");
               os = socket.getOutputStream();
               is = socket.getInputStream();
               int cnt = 0;
               String data = "";

               Protocol protocol = new Protocol();
               byte[] buf = protocol.getPacket();

               table.setEditingColumn(2); // 가상계좌번호
               table.setEditingColumn(3); // 납부? 미납

               while (cnt < count) {
                  System.out.println(cnt + " " + count);
                  String studentNum = table.getValueAt(cnt, 0).toString();
                  String accountNum = table.getValueAt(cnt, 2).toString();

                  data += studentNum + "#" + accountNum;

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
                  protocol.setWhere(Protocol.UPDATE_ACCOUTNUM);

                  System.out.println(data);
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
         else {
        	 try {
             	System.out.println("저장!");
                os = socket.getOutputStream();
                is = socket.getInputStream();
                int cnt = 0;
                String data = "";

                Protocol protocol = new Protocol();
                byte[] buf = protocol.getPacket();

                table.setEditingColumn(2); // 가상계좌번호
                table.setEditingColumn(3); // 납부? 미납

                while (cnt < count) {
                   System.out.println(cnt + " " + count);
                   String studentNum = table.getValueAt(cnt, 0).toString();
                   String payStatus = table.getValueAt(cnt, 3).toString();

                   data += studentNum + "#" + payStatus;

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
                   protocol.setWhere(Protocol.UPDATE_PAYMENT_STATUS);

                   System.out.println(data);
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
         }
      });
      btnNewButton_1.setBounds(777, 193, 95, 23);
      getContentPane().add(btnNewButton_1);
      
      JButton button = new JButton("선발");
      button.addActionListener(new ActionListener() { // 
          public void actionPerformed(ActionEvent arg0) {

        	if(savecnt < 1) {
             try {
            	   os = socket.getOutputStream();
                   is = socket.getInputStream();
                   savecnt ++ ;
                   String data = "";

                   Protocol protocol = new Protocol();
                   byte[] buf = protocol.getPacket();


                   protocol = new Protocol(Protocol.SEND_DATA);
                   protocol.setData("");
                   protocol.setWhere(Protocol.DEPOSIT_TO_TUBER_CERTIFICATE);
                   
                   System.out.println("서버에게 데이터 전송");
                   os.write(protocol.getPacket());
                   	
                   is.read(buf);
                   
                }
             catch (IOException ioe) {
                ioe.getStackTrace();
             }
             JOptionPane.showMessageDialog(null, "선발 완료");
          }else {
        	  JOptionPane.showMessageDialog(null, "이미 선발되었습니다.");
          }
          }
       });
      
      button.setBounds(563, 193, 95, 23);
      getContentPane().add(button);
      
      setVisible(true);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setSize(902, 676);
   }
}