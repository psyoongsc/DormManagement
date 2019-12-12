package DormitoryProgram;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.util.StringTokenizer;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;
import java.util.StringTokenizer;
import javax.swing.SwingConstants;
import java.io.IOException;
public class MENU_ADMIN extends JFrame {
   
   private JTextField txtF;
   private JTextField txtid;
   private JTextField admID;
   private JTextField textField_4;
   private JTextField admName;
   private JTextField admPhoneNum;
   private JTextField textField_9;
   private JTextField textField_2;
   
   private LoginInfo info = new LoginInfo();
   
   private OutputStream os = null;
   private InputStream is = null;
   
   public MENU_ADMIN(String sql, Socket socket) { //기존의 SQL의 결과를 가져오는 방식과 달리 결과값을 합친 문장을 잘라서 넣는 방식을 사용하였다.
      
      StringTokenizer tokens = new StringTokenizer(sql, "#");
      info.setId(tokens.nextToken().toString().trim());
      info.setName(tokens.nextToken().toString().trim());
      info.setPhoneNumber(tokens.nextToken().toString().trim());
      
      
      setTitle("관리자메뉴");
      getContentPane().setLayout(null);
      
      setSize(515,500);
      
      
      txtF = new JTextField();
      txtF.setForeground(Color.WHITE);
      txtF.setEditable(false);
      txtF.setHorizontalAlignment(SwingConstants.CENTER);
      txtF.setBackground(new Color(112, 128, 144));
      txtF.setFont(new Font("HY견고딕", Font.PLAIN, 16));
      txtF.setText("교직원정보");
      txtF.setBounds(12, 10, 130, 30);
      getContentPane().add(txtF);
      txtF.setColumns(10);
      
      txtid = new JTextField();
      txtid.setText("교직원ID");
      txtid.setHorizontalAlignment(SwingConstants.CENTER);
      txtid.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      txtid.setEditable(false);
      txtid.setColumns(10);
      txtid.setBackground(Color.LIGHT_GRAY);
      txtid.setBounds(12, 56, 80, 30);
      getContentPane().add(txtid);
      
      admID = new JTextField();
      admID.setText(info.getId());
      admID.setHorizontalAlignment(SwingConstants.CENTER);
      admID.setEditable(false);
      admID.setColumns(10);
      admID.setBounds(90, 56, 120, 30);
      getContentPane().add(admID);
      
      textField_4 = new JTextField();
      textField_4.setText("성명");
      textField_4.setHorizontalAlignment(SwingConstants.CENTER);
      textField_4.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_4.setEditable(false);
      textField_4.setColumns(10);
      textField_4.setBackground(Color.LIGHT_GRAY);
      textField_4.setBounds(206, 56, 80, 30);
      getContentPane().add(textField_4);
      
      admName = new JTextField();
      admName.setText(info.getName());
      admName.setHorizontalAlignment(SwingConstants.CENTER);
      admName.setEditable(false);
      admName.setColumns(10);
      admName.setBounds(284, 56, 120, 30);
      getContentPane().add(admName);
      
      admPhoneNum = new JTextField();
      admPhoneNum.setText(info.getPhoneNumber());
      admPhoneNum.setHorizontalAlignment(SwingConstants.CENTER);
      admPhoneNum.setEditable(false);
      admPhoneNum.setColumns(10);
      admPhoneNum.setBounds(90, 95, 120, 30);
      getContentPane().add(admPhoneNum);
      
      textField_9 = new JTextField();
      textField_9.setText("휴대전화번호");
      textField_9.setHorizontalAlignment(SwingConstants.CENTER);
      textField_9.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_9.setEditable(false);
      textField_9.setColumns(10);
      textField_9.setBackground(Color.LIGHT_GRAY);
      textField_9.setBounds(12, 95, 80, 30);
      getContentPane().add(textField_9);
      
      textField_2 = new JTextField();
      textField_2.setForeground(Color.WHITE);
      textField_2.setText("메뉴");
      textField_2.setHorizontalAlignment(SwingConstants.CENTER);
      textField_2.setFont(new Font("HY견고딕", Font.PLAIN, 16));
      textField_2.setEditable(false);
      textField_2.setColumns(10);
      textField_2.setBackground(new Color(112, 128, 144));
      textField_2.setBounds(12, 238, 100, 30);
      getContentPane().add(textField_2);
      
      JButton button = new JButton("신청자 등록 및 조회");
      button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new AdminDorApplication(socket);
         }
      });
      
      button.setBounds(12, 329, 200, 30);
      getContentPane().add(button);
      
      JButton button_1 = new JButton("결핵진단서 제출확인 및 업로드");
      button_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             try
             {
                new AdminTuberCertificate(socket);
             }
             
             catch (Exception ex)
             {
                ex.printStackTrace();
             }
         }
      });
      button_1.setBounds(237, 369, 220, 30);
      getContentPane().add(button_1);

      
      JButton button_2 = new JButton("입사선발자 결과 등록");
      button_2.addActionListener(new ActionListener() {
    	int cnt = 0;
         public void actionPerformed(ActionEvent e) { // 서버에게 전달
        	if(cnt == 0) {
        		try {
        			cnt ++;
        			is = socket.getInputStream();
        			os = socket.getOutputStream();
        	
        			Protocol protocol = new Protocol();
        			byte[] buf = protocol.getPacket();
        			protocol = new Protocol(Protocol.SEND_DATA);
        			protocol.setData("");
        			protocol.setWhere("9");
        	
        			os.write(protocol.getPacket());
        			is.read(buf);
        	
        			JOptionPane.showMessageDialog(null, "입사선발자가 등록되었습니다.");
        	 }
        	 catch(IOException ioe) {
        		 ioe.getStackTrace();
        	 }
        		}
        	 else {
        	 JOptionPane.showMessageDialog(null, "이미 등록되었습니다.");
        	 }
         }
      });
      button_2.setBounds(237, 329, 220, 30);
      getContentPane().add(button_2);
      
      JButton button_4 = new JButton("입금 여부 조회");
      button_4.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try
            {
               new AdminDeposit(socket);
            }
            
            catch (Exception ex)
            {
               ex.printStackTrace();
            }
         }
      });
      button_4.setBounds(12, 369, 200, 30);
      getContentPane().add(button_4);

      
      JButton btnNewButton = new JButton("선발 일정 등록");
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 new UpdateSchedule(socket);
         }
      });
      btnNewButton.setBounds(12, 289, 200, 30);
      getContentPane().add(btnNewButton);
      
      JButton btnNewButton_1 = new JButton("생활관 사용료 및 급식비 등록");
      btnNewButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
        	 new UpdateDorForApplication(socket);
         }
      });
      btnNewButton_1.setBounds(237, 289, 220, 30);
      getContentPane().add(btnNewButton_1);
      
      JButton btnNewButton_2 = new JButton("입사자 등록 및 조회");
      btnNewButton_2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try
            {
               new AdminFinalSelected(socket);
            }
            
            catch (Exception ex)
            {
               ex.printStackTrace();
            }
         }
      });
      btnNewButton_2.setBounds(12, 409, 200, 30);
      getContentPane().add(btnNewButton_2);
      
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}

