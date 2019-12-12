package DormitoryProgram;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.util.StringTokenizer;
import java.io.*;

public class MENU_STUDENT extends JFrame {
   private JTextField textField;
   private JTextField textField_1;
   private JTextField stuId;
   private JTextField stuName;
   private JTextField textField_4;
   private JTextField textField_5;
   private JTextField textField_6;
   private JTextField textField_7;
   private JTextField stuStatus;
   private JTextField textField_9;
   private JTextField stuSex;
   private JTextField textField_11;
   private JButton btnNewButton;
   private JButton button;
   private JButton button_1;
   private JButton button_2;
   private JButton button_3;
   private JTextField textField_12;
   private JTextField stuPhoneNum;
   private OutputStream os;
   private InputStream is;
   
   private LoginInfo info = new LoginInfo();

public MENU_STUDENT(String sql, Socket socket) { //기존의 SQL의 결과를 가져오는 방식과 달리 결과값을 합친 문장을 잘라서 넣는 방식을 사용하였다.
      
      StringTokenizer tokens = new StringTokenizer(sql, "#");
      info.setId(tokens.nextToken().toString().trim());
      info.setName(tokens.nextToken().toString().trim());
      info.setStatus(tokens.nextToken().toString().trim());
      info.setSex(tokens.nextToken().toString().trim());
      info.setPhoneNumber(tokens.nextToken().toString().trim());
      
      
      setTitle("학생메뉴");
      getContentPane().setLayout(null);
      
      textField = new JTextField();
      textField.setForeground(Color.WHITE);
      textField.setEditable(false);
      textField.setBackground(new Color(112, 128, 144));
      textField.setFont(new Font("HY견고딕", Font.PLAIN, 16));
      textField.setHorizontalAlignment(SwingConstants.CENTER);
      textField.setText("\uD559\uC0DD\uC815\uBCF4");
      textField.setBounds(12, 10, 100, 30);
      getContentPane().add(textField);
      textField.setColumns(10);
      
      textField_1 = new JTextField();
      textField_1.setEditable(false);
      textField_1.setBackground(new Color(192, 192, 192));
      textField_1.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_1.setText("학번");
      textField_1.setHorizontalAlignment(SwingConstants.CENTER);
      textField_1.setBounds(12, 64, 80, 30);
      getContentPane().add(textField_1);
      textField_1.setColumns(10);
      
      stuId = new JTextField();
      stuId.setEditable(false);
      stuId.setHorizontalAlignment(SwingConstants.CENTER);
      stuId.setText(info.getId());
      stuId.setBounds(90, 64, 120, 30);
      getContentPane().add(stuId);
      stuId.setColumns(10);
      
      stuName = new JTextField();
      stuName.setEditable(false);
      stuName.setHorizontalAlignment(SwingConstants.CENTER);
      stuName.setText(info.getName());
      stuName.setColumns(10);
      stuName.setBounds(284, 64, 120, 30);
      getContentPane().add(stuName);
      
      textField_4 = new JTextField();
      textField_4.setEditable(false);
      textField_4.setBackground(new Color(192, 192, 192));
      textField_4.setText("성명");
      textField_4.setHorizontalAlignment(SwingConstants.CENTER);
      textField_4.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_4.setColumns(10);
      textField_4.setBounds(206, 64, 80, 30);
      getContentPane().add(textField_4);
      
      textField_7 = new JTextField();
      textField_7.setEditable(false);
      textField_7.setBackground(new Color(192, 192, 192));
      textField_7.setText("학적상태");
      textField_7.setHorizontalAlignment(SwingConstants.CENTER);
      textField_7.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_7.setColumns(10);
      textField_7.setBounds(206, 116, 80, 30);
      getContentPane().add(textField_7);
      
      stuStatus = new JTextField();
      stuStatus.setEditable(false);
      stuStatus.setHorizontalAlignment(SwingConstants.CENTER);
      stuStatus.setText(info.getStatus());
//      System.out.println(sql.getInfo().getStatus().toString().trim());
      stuStatus.setColumns(10);
      stuStatus.setBounds(284, 116, 120, 30);
      getContentPane().add(stuStatus);
      
      textField_9 = new JTextField();
      textField_9.setEditable(false);
      textField_9.setBackground(new Color(192, 192, 192));
      textField_9.setText("성별");
      textField_9.setHorizontalAlignment(SwingConstants.CENTER);
      textField_9.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_9.setColumns(10);
      textField_9.setBounds(12, 116, 80, 30);
      getContentPane().add(textField_9);
      
      stuSex = new JTextField();
      stuSex.setEditable(false);
      stuSex.setHorizontalAlignment(SwingConstants.CENTER);
      stuSex.setText(info.getSex());
      stuSex.setColumns(10);
      stuSex.setBounds(90, 116, 120, 30);
      getContentPane().add(stuSex);
      
      textField_11 = new JTextField();
      textField_11.setForeground(Color.WHITE);
      textField_11.setEditable(false);
      textField_11.setText("메뉴");
      textField_11.setHorizontalAlignment(SwingConstants.CENTER);
      textField_11.setFont(new Font("HY견고딕", Font.PLAIN, 16));
      textField_11.setColumns(10);
      textField_11.setBackground(new Color(112, 128, 144));
      textField_11.setBounds(12, 235, 100, 30);
      getContentPane().add(textField_11);
      
      btnNewButton = new JButton("생활관 정보조회");
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               new StudentDorInfo(info, socket);
            } catch (Exception ex) {
               ex.printStackTrace();
            }
         }
      });
      btnNewButton.setBounds(12, 295, 200, 30);
      getContentPane().add(btnNewButton);
      
      button = new JButton("생활관 입사신청");
      button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
           if("재학생".equals(info.getStatus()) || "군제대복학생".equals(info.getStatus())) {
              new StudentDorBow(info, socket);
           }
           else
              JOptionPane.showMessageDialog(null, "입사 신청이 불가능한 대상입니다.");
         }
      });
      button.setBounds(263, 295, 200, 30);
      getContentPane().add(button);
      
      button_1 = new JButton("신청 확인 및 고지서 출력");
      button_1.addActionListener(new ActionListener() { // 선발
         public void actionPerformed(ActionEvent e) {
           if("재학생".equals(info.getStatus()) || "군제대복학생".equals(info.getStatus())) {
            try {
            os = socket.getOutputStream();
            is = socket.getInputStream();
            Protocol protocol = new Protocol();
            byte[] buf = protocol.getPacket();
            
            protocol= new Protocol(Protocol.PT_REQ_CAN);
            protocol.setStudentNum(info.getId());
            protocol.setKindofOpen("1");
            // 20180650, 1
            os.write(protocol.getPacket());
            
            is.read(buf);
            protocol.setPacket(buf[0], buf);
            
            System.out.println(protocol.getcanOpen());
            
            if(protocol.getcanOpen().equals("O")) {
               new StudentDeposit(info, socket);
            }
            else {
              JOptionPane.showMessageDialog(null, "고지서 출력 대상자가 아닙니다. "); 
            }
            }
            catch(IOException a) {
               a.getStackTrace();
            }
         
         }
         else {
            JOptionPane.showMessageDialog(null, "고지서 출력 대상자가 아닙니다.");
         }
         }
      });
      button_1.setBounds(12, 348, 200, 30);
      getContentPane().add(button_1);
      
      button_2 = new JButton("결핵진단서 제출");
      button_2.addActionListener(new ActionListener() { // 입금
         public void actionPerformed(ActionEvent e){
            try {
               if("재학생".equals(info.getStatus()) || "군제대복학생".equals(info.getStatus())) {
                os = socket.getOutputStream();
                is = socket.getInputStream();
                Protocol protocol = new Protocol();
                byte[] buf = protocol.getPacket();
                
                protocol= new Protocol(Protocol.PT_REQ_CAN);
                protocol.setStudentNum(info.getId());
                protocol.setKindofOpen("2");
                
                os.write(protocol.getPacket());
                
                is.read(buf);
                protocol.setPacket(buf[0], buf);
                
                
                
                if(protocol.getcanOpen().equals("O")) {
                   
                   new StudentTuberCertificate(info, socket);
                }
                else {
                  JOptionPane.showMessageDialog(null, "결핵진단서 제출 대상자가 아닙니다. "); 
                }
               }
               else {
                   JOptionPane.showMessageDialog(null, "결핵진단서 제출 대상자가 아닙니다. "); 
                 
            }
            }
            
                catch(IOException a) {
                   a.getStackTrace();
                }
         }
      });
      button_2.setBounds(263, 348, 200, 30);
      getContentPane().add(button_2);
      
      button_3 = new JButton("생활관 호실조회");
      button_3.addActionListener(new ActionListener() { // 배정
         public void actionPerformed(ActionEvent e) {
            try {
               if("재학생".equals(info.getStatus()) || "군제대복학생".equals(info.getStatus())) {
                os = socket.getOutputStream();
                is = socket.getInputStream();
                Protocol protocol = new Protocol();
                byte[] buf = protocol.getPacket();
                
                protocol= new Protocol(Protocol.PT_REQ_CAN);
                protocol.setStudentNum(info.getId());
                protocol.setKindofOpen("3");
                
                os.write(protocol.getPacket());
                
                is.read(buf);
                protocol.setPacket(buf[0], buf);
                if(protocol.getcanOpen().equals("O")) {
                   new StudentRoomInfo(info, socket); ///////////
                }
                else {
                  JOptionPane.showMessageDialog(null, "생활관 호실조회 대상자가 아닙니다. "); 
                }
               }
               else {
                  JOptionPane.showMessageDialog(null, "생활관 호실조회 대상자가 아닙니다. "); 
               }
                }
                catch(IOException a) {
                   a.getStackTrace();
                }
         }
      });
      button_3.setBounds(12, 400, 200, 30);
      getContentPane().add(button_3);
      
      textField_12 = new JTextField();
      textField_12.setText("휴대전화번호");
      textField_12.setHorizontalAlignment(SwingConstants.CENTER);
      textField_12.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_12.setEditable(false);
      textField_12.setColumns(10);
      textField_12.setBackground(Color.LIGHT_GRAY);
      textField_12.setBounds(12, 172, 80, 30);
      getContentPane().add(textField_12);
      
      stuPhoneNum = new JTextField();
      stuPhoneNum.setText(info.getPhoneNumber());
      stuPhoneNum.setHorizontalAlignment(SwingConstants.CENTER);
      stuPhoneNum.setEditable(false);
      stuPhoneNum.setColumns(10);
      stuPhoneNum.setBounds(90, 171, 120, 30);
      getContentPane().add(stuPhoneNum);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(500,500);
      
   }
}