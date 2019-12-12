package DormitoryProgram;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.StringTokenizer;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.*;

public class StudentBillPrint extends JFrame {
   private JTextField textField_22;
   private JTextField textField_23;
   private JTextField textField_11;
   private JTextField textField_12;
   private JTextField textField_16;
   JScrollPane scrollPane = new JScrollPane();
   private JTextField textField_2;
   private JTextField textField_3;
   private JTextField textField_4;
   private JTextField textField_5;
   private JTextField textField_6;
   private JTextField textField_7;
   private JTextField textField_10;
   private JTextField textField_13;
   private JTextField textField_14;
   private JTextField textField_15;
   private JTextField textField_17;
   private JTextField textField_18;
   private JTable table_1;
   private JTextField textField;
   private JTextField textField_19;
   private JTextField textField_1;
   private JTextField textField_8;
   private JTextField textField_21;
   private JTextField textField_24;
   private JTextField textField_25;
   private JTextField textField_9;
   private JTextField textField_20;
   private JTextField textField_26;

   private OutputStream os;
   private InputStream is;
   private JTextField textField_27;
   private JTextField textField_28;
   
   LoginInfo info = null;

   public StudentBillPrint(LoginInfo info, Socket socket) {
      getContentPane().setForeground(Color.WHITE);
      
      this.info = info;

      scrollPane.setBounds(12, 234, 1200, 120);

      getContentPane().setLayout(null);
      setTitle("고지서 출력");
      
      textField_6 = new JTextField();
      textField_6.setText("운영관리비");
      textField_6.setHorizontalAlignment(SwingConstants.CENTER);
      textField_6.setFont(new Font("Dialog", Font.PLAIN, 12));
      textField_6.setEditable(false);
      textField_6.setColumns(10);
      textField_6.setBackground(SystemColor.activeCaption);
      textField_6.setBounds(313, 166, 117, 45);
      getContentPane().add(textField_6);

      textField_22 = new JTextField();
      textField_22.setEditable(false);
      textField_22.setText("생활관구분");
      textField_22.setHorizontalAlignment(SwingConstants.CENTER);
      textField_22.setFont(new Font("Dialog", Font.PLAIN, 12));
      textField_22.setColumns(10);
      textField_22.setBackground(SystemColor.activeCaption);
      textField_22.setBounds(477, 75, 140, 23);
      getContentPane().add(textField_22);

      textField_23 = new JTextField();
      textField_23.setEditable(false);
      textField_23.setText("구분");
      textField_23.setHorizontalAlignment(SwingConstants.CENTER);
      textField_23.setFont(new Font("Dialog", Font.PLAIN, 12));
      textField_23.setColumns(10);
      textField_23.setBackground(SystemColor.activeCaption);
      textField_23.setBounds(63, 166, 117, 45);
      getContentPane().add(textField_23);

      textField_11 = new JTextField();
      textField_11.setBackground(SystemColor.activeCaption);
      textField_11.setHorizontalAlignment(SwingConstants.CENTER);
      textField_11.setText("기간");
      textField_11.setEditable(false);
      textField_11.setColumns(10);
      textField_11.setBounds(178, 166, 140, 23);
      getContentPane().add(textField_11);

      textField_12 = new JTextField();
      textField_12.setBackground(SystemColor.activeCaption);
      textField_12.setHorizontalAlignment(SwingConstants.CENTER);
      textField_12.setText("전기간");
      textField_12.setEditable(false);
      textField_12.setColumns(10);
      textField_12.setBounds(178, 188, 139, 23);
      getContentPane().add(textField_12);
      
      textField_2 = new JTextField();
      textField_2.setText("학번");
      textField_2.setHorizontalAlignment(SwingConstants.CENTER);
      textField_2.setFont(new Font("Dialog", Font.PLAIN, 12));
      textField_2.setEditable(false);
      textField_2.setColumns(10);
      textField_2.setBackground(SystemColor.activeCaption);
      textField_2.setBounds(202, 75, 140, 23);
      getContentPane().add(textField_2);
      
      textField_3 = new JTextField();
      textField_3.setText("성명");
      textField_3.setHorizontalAlignment(SwingConstants.CENTER);
      textField_3.setFont(new Font("Dialog", Font.PLAIN, 12));
      textField_3.setEditable(false);
      textField_3.setColumns(10);
      textField_3.setBackground(SystemColor.activeCaption);
      textField_3.setBounds(340, 75, 139, 23);
      getContentPane().add(textField_3);
      
      textField_4 = new JTextField();
      textField_4.setHorizontalAlignment(SwingConstants.CENTER);
      textField_4.setText("금액");
      textField_4.setEditable(false);
      textField_4.setColumns(10);
      textField_4.setBounds(63, 210, 117, 35);
      getContentPane().add(textField_4);
      
      textField_5 = new JTextField();
      textField_5.setHorizontalAlignment(SwingConstants.CENTER);
      textField_5.setEditable(false);
      textField_5.setColumns(10);
      textField_5.setBounds(202, 98, 139, 23);
      getContentPane().add(textField_5);
      
      textField_7 = new JTextField();
      textField_7.setText("합계");
      textField_7.setHorizontalAlignment(SwingConstants.CENTER);
      textField_7.setFont(new Font("Dialog", Font.PLAIN, 12));
      textField_7.setEditable(false);
      textField_7.setColumns(10);
      textField_7.setBackground(SystemColor.activeCaption);
      textField_7.setBounds(695, 166, 106, 45);
      getContentPane().add(textField_7);
      
      textField_10 = new JTextField();
      textField_10.setBackground(SystemColor.activeCaption);
      textField_10.setHorizontalAlignment(SwingConstants.CENTER);
      textField_10.setText("식비(1일2식)");
      textField_10.setEditable(false);
      textField_10.setColumns(10);
      textField_10.setBounds(429, 166, 176, 23);
      getContentPane().add(textField_10);
      
      textField_13 = new JTextField();
      textField_13.setBackground(SystemColor.activeCaption);
      textField_13.setHorizontalAlignment(SwingConstants.CENTER);
      textField_13.setText("은행");
      textField_13.setToolTipText("은행");
      textField_13.setEditable(false);
      textField_13.setColumns(10);
      textField_13.setBounds(63, 315, 139, 23);
      getContentPane().add(textField_13);
      
      textField_14 = new JTextField();
      textField_14.setText("생활관구분");
      textField_14.setHorizontalAlignment(SwingConstants.CENTER);
      textField_14.setFont(new Font("Dialog", Font.PLAIN, 12));
      textField_14.setEditable(false);
      textField_14.setColumns(10);
      textField_14.setBackground(SystemColor.menu);
      textField_14.setBounds(933, 145, 140, 23);
      getContentPane().add(textField_14);
      
      textField_15 = new JTextField();
      textField_15.setText("식사구분");
      textField_15.setHorizontalAlignment(SwingConstants.CENTER);
      textField_15.setFont(new Font("Dialog", Font.PLAIN, 12));
      textField_15.setEditable(false);
      textField_15.setColumns(10);
      textField_15.setBackground(SystemColor.menu);
      textField_15.setBounds(1073, 145, 139, 23);
      getContentPane().add(textField_15);
      
      textField_17 = new JTextField();
      textField_17.setEditable(false);
      textField_17.setColumns(10);
      textField_17.setBounds(933, 167, 140, 23);
      getContentPane().add(textField_17);
      
      textField_18 = new JTextField();
      textField_18.setEditable(false);
      textField_18.setColumns(10);
      textField_18.setBounds(1073, 167, 139, 23);
      getContentPane().add(textField_18);
      
      JLabel lblxxX = new JLabel("       2020년도 1학기 생활관비 납부고지서");
      lblxxX.setFont(new Font("한컴돋움", Font.BOLD, 19));
      lblxxX.setBounds(213, 37, 415, 23);
      getContentPane().add(lblxxX);
      
      textField = new JTextField();
      textField.setHorizontalAlignment(SwingConstants.CENTER);
      textField.setEditable(false);
      textField.setColumns(10);
      textField.setBounds(340, 98, 139, 23);
      getContentPane().add(textField);
      
      textField_19 = new JTextField();
      textField_19.setHorizontalAlignment(SwingConstants.CENTER);
      textField_19.setEditable(false);
      textField_19.setColumns(10);
      textField_19.setBounds(477, 98, 139, 23);
      getContentPane().add(textField_19);
      
      textField_1 = new JTextField();
      textField_1.setBackground(SystemColor.activeCaption);
      textField_1.setHorizontalAlignment(SwingConstants.CENTER);
      textField_1.setEditable(false);
      textField_1.setColumns(10);
      textField_1.setBounds(429, 188, 176, 23);
      getContentPane().add(textField_1);
      
      textField_8 = new JTextField();
      textField_8.setHorizontalAlignment(SwingConstants.CENTER);
      textField_8.setFont(new Font("Dialog", Font.PLAIN, 12));
      textField_8.setEditable(false);
      textField_8.setColumns(10);
      textField_8.setBackground(SystemColor.menu);
      textField_8.setBounds(313, 209, 117, 36);
      getContentPane().add(textField_8);
      
      textField_21 = new JTextField();
      textField_21.setHorizontalAlignment(SwingConstants.CENTER);
      textField_21.setEditable(false);
      textField_21.setColumns(10);
      textField_21.setBounds(179, 210, 139, 35);
      getContentPane().add(textField_21);
      
      textField_24 = new JTextField();
      textField_24.setHorizontalAlignment(SwingConstants.CENTER);
      textField_24.setFont(new Font("Dialog", Font.PLAIN, 12));
      textField_24.setEditable(false);
      textField_24.setColumns(10);
      textField_24.setBackground(SystemColor.menu);
      textField_24.setBounds(695, 210, 106, 35);
      getContentPane().add(textField_24);
      
      textField_25 = new JTextField();
      textField_25.setHorizontalAlignment(SwingConstants.CENTER);
      textField_25.setEditable(false);
      textField_25.setColumns(10);
      textField_25.setBounds(429, 209, 177, 36);
      getContentPane().add(textField_25);
      
      JLabel label = new JLabel("납부계좌");
      label.setFont(new Font("한컴돋움", Font.BOLD, 14));
      label.setBounds(63, 282, 415, 23);
      getContentPane().add(label);
      
      textField_9 = new JTextField();
      textField_9.setBackground(SystemColor.activeCaption);
      textField_9.setToolTipText("은행");
      textField_9.setText("가상계좌번호");
      textField_9.setHorizontalAlignment(SwingConstants.CENTER);
      textField_9.setEditable(false);
      textField_9.setColumns(10);
      textField_9.setBounds(202, 315, 196, 23);
      getContentPane().add(textField_9);
      
      textField_20 = new JTextField();
      textField_20.setToolTipText("은행");
      textField_20.setText("농협은행");
      textField_20.setHorizontalAlignment(SwingConstants.CENTER);
      textField_20.setEditable(false);
      textField_20.setColumns(10);
      textField_20.setBounds(63, 336, 139, 23);
      getContentPane().add(textField_20);
      
      textField_26 = new JTextField();
      textField_26.setToolTipText("은행");
      textField_26.setHorizontalAlignment(SwingConstants.CENTER);
      textField_26.setEditable(false);
      textField_26.setColumns(10);
      textField_26.setBounds(202, 337, 196, 22);
      getContentPane().add(textField_26);
      
      textField_27 = new JTextField();
      textField_27.setText("공공요금");
      textField_27.setHorizontalAlignment(SwingConstants.CENTER);
      textField_27.setFont(new Font("Dialog", Font.PLAIN, 12));
      textField_27.setEditable(false);
      textField_27.setColumns(10);
      textField_27.setBackground(SystemColor.activeCaption);
      textField_27.setBounds(605, 166, 92, 45);
      getContentPane().add(textField_27);
      
      textField_28 = new JTextField();
      textField_28.setHorizontalAlignment(SwingConstants.CENTER);
      textField_28.setFont(new Font("Dialog", Font.PLAIN, 12));
      textField_28.setEditable(false);
      textField_28.setColumns(10);
      textField_28.setBackground(SystemColor.menu);
      textField_28.setBounds(603, 210, 93, 35);
      getContentPane().add(textField_28);
      setVisible(true);
      setSize(849,419);
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      
       try {
          
            String str1, str2, str3, str5;
             int i1;
             
             os = socket.getOutputStream();
             is = socket.getInputStream();
             
             Protocol protocol = new Protocol();
             byte[] buf = protocol.getPacket();
            
             protocol = new Protocol(Protocol.PT_REQ_INQUIRE_ONE); // REQ_INQUIRE_ONE
             
             protocol.setWhere("9");
             System.out.println(info.getId());
             protocol.setStudentNumINQ(this.info.getId());
             
             System.out.println(protocol.getWhere() + "," + protocol.getStudentNumINQ());
             
             os.write(protocol.getPacket());
             
             is.read(buf);
             
             protocol.setPacket(buf[0], buf);
             String str = protocol.getOpenQueryResult();
             StringTokenizer stkr = new StringTokenizer(str, "#");
             
             String id, name, dorm, period, dormfee, mealfee, othercharge, totalfee, account, eatday;
             /////////////////////
             id = stkr.nextToken();
             name = stkr.nextToken();
             dorm = stkr.nextToken();
             period = stkr.nextToken();
             eatday = stkr.nextToken();
             dormfee = stkr.nextToken();
             mealfee = stkr.nextToken();
             othercharge = stkr.nextToken();
             totalfee = stkr.nextToken();
             account = stkr.nextToken();
             
             textField_5.setText(info.getId());
             textField.setText(name);
             textField_19.setText(dorm);
             textField_21.setText(period);
             textField_8.setText(dormfee);
             textField_25.setText(mealfee);
             textField_28.setText(othercharge);
             textField_24.setText(totalfee);
             textField_26.setText(account);
             
             if(dorm == "오름관1동") {
                if(eatday == "7일식") {
                   textField_1.setText("7일식(월~일) 조식, 석식제공");
                }
                else {
                   textField_1.setText("5일식(월~금) 조식, 석식제공");   
                }
             }
             else
                if(eatday == "7일식") {
                   textField_1.setText("7일식(월~일) 중식, 석식제공");
                }
                else {
                   textField_1.setText("5일식(월~일) 중식, 석식제공");
                }
          } catch (Exception e1) {
             e1.printStackTrace();
          }
   }
} // 관 따라 식사유형 수정