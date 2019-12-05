import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;

public class MENU_ADMIN extends JFrame {
	private JTextField txtF;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtF_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_2;
	private JButton btnNewButton;
	private JButton button_2;
	private JButton button_5;
	private JButton button_6;
	public MENU_ADMIN() {
		setTitle("관리자메뉴");
		getContentPane().setLayout(null);
		setVisible(true);
		setSize(515,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		txtF = new JTextField();
		txtF.setEditable(false);
		txtF.setHorizontalAlignment(SwingConstants.CENTER);
		txtF.setBackground(new Color(112, 128, 144));
		txtF.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		txtF.setText("교직원정보");
		txtF.setBounds(12, 10, 130, 30);
		getContentPane().add(txtF);
		txtF.setColumns(10);
		
		textField = new JTextField();
		textField.setText("\uAD50\uC9C1\uC6D0\uBC88\uD638");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(12, 56, 80, 30);
		getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setText("교직원업무");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setBounds(12, 108, 80, 30);
		getContentPane().add(textField_1);
		
		txtF_1 = new JTextField();
		txtF_1.setText("f20150154");
		txtF_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtF_1.setEditable(false);
		txtF_1.setColumns(10);
		txtF_1.setBounds(90, 56, 120, 30);
		getContentPane().add(txtF_1);
		
		textField_3 = new JTextField();
		textField_3.setText("생활관관리");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(90, 108, 120, 30);
		getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setText("성명");
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBackground(Color.LIGHT_GRAY);
		textField_4.setBounds(206, 56, 80, 30);
		getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setText("교직상태");
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBackground(Color.LIGHT_GRAY);
		textField_5.setBounds(206, 108, 80, 30);
		getContentPane().add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setText("김민수");
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(284, 56, 120, 30);
		getContentPane().add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setText("재직");
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(284, 108, 120, 30);
		getContentPane().add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setText("01097337207");
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBounds(90, 158, 120, 30);
		getContentPane().add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setText("휴대전화번호");
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField_9.setEditable(false);
		textField_9.setColumns(10);
		textField_9.setBackground(Color.LIGHT_GRAY);
		textField_9.setBounds(12, 158, 80, 30);
		getContentPane().add(textField_9);
		
		textField_2 = new JTextField();
		textField_2.setText("메뉴");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBackground(new Color(112, 128, 144));
		textField_2.setBounds(12, 238, 100, 30);
		getContentPane().add(textField_2);
		
		JButton button = new JButton("일정편집");
		button.setFont(new Font("굴림", Font.PLAIN, 10));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminSchedule();
			}
		});
		button.setBounds(12, 298, 100, 30);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("결핵진단서");
		button_1.setFont(new Font("굴림", Font.PLAIN, 10));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminTuberCertificate();
			}
		});
		button_1.setBounds(144, 355, 100, 30);
		getContentPane().add(button_1);
		
		JButton button_3 = new JButton("신청자명단");
		button_3.setFont(new Font("굴림", Font.PLAIN, 10));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminDorApplication();
			}
		});
		button_3.setBounds(144, 298, 100, 30);
		getContentPane().add(button_3);
		
		JButton button_4 = new JButton("입금대기명단");
		button_4.setFont(new Font("굴림", Font.PLAIN, 10));
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminDeposit();
			}
		});
		button_4.setBounds(12, 355, 100, 30);
		getContentPane().add(button_4);
		
		btnNewButton = new JButton("로그아웃");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login();
				dispose();
			}
		});
		btnNewButton.setBounds(398, 10, 91, 23);
		getContentPane().add(btnNewButton);
		
		button_2 = new JButton("선발자명단");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminStudentAllocate();
			}
		});
		button_2.setFont(new Font("굴림", Font.PLAIN, 10));
		button_2.setBounds(284, 298, 100, 30);
		getContentPane().add(button_2);
		
		button_5 = new JButton("최종배정명단");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminFinalSelected();
			}
		});
		button_5.setFont(new Font("굴림", Font.PLAIN, 10));
		button_5.setBounds(284, 355, 100, 30);
		getContentPane().add(button_5);
		
		button_6 = new JButton("대기자명단");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminAwaiterTable();
			}
		});
		button_6.setFont(new Font("굴림", Font.PLAIN, 10));
		button_6.setBounds(12, 401, 100, 30);
		getContentPane().add(button_6);
	}


	public static void main(String[] args) {
		new MENU_ADMIN();

	}

}
