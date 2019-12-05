import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;

public class MENU_STUDENT extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JButton btnNewButton;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	public MENU_STUDENT() {
		setTitle("학생메뉴");
		getContentPane().setLayout(null);
		textField = new JTextField();
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
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setText("20150154");
		textField_2.setBounds(90, 64, 120, 30);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setText("김민수");
		textField_3.setColumns(10);
		textField_3.setBounds(284, 64, 120, 30);
		getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBackground(new Color(192, 192, 192));
		textField_4.setText("성명");
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField_4.setColumns(10);
		textField_4.setBounds(206, 64, 80, 30);
		getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setBackground(new Color(192, 192, 192));
		textField_5.setText("학년");
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField_5.setColumns(10);
		textField_5.setBounds(12, 116, 80, 30);
		getContentPane().add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setText("2");
		textField_6.setColumns(10);
		textField_6.setBounds(90, 116, 120, 30);
		getContentPane().add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setBackground(new Color(192, 192, 192));
		textField_7.setText("재학상태");
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField_7.setColumns(10);
		textField_7.setBounds(206, 116, 80, 30);
		getContentPane().add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setText("재학중");
		textField_8.setColumns(10);
		textField_8.setBounds(284, 116, 120, 30);
		getContentPane().add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setEditable(false);
		textField_9.setBackground(new Color(192, 192, 192));
		textField_9.setText("휴대전화번호");
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField_9.setColumns(10);
		textField_9.setBounds(12, 166, 80, 30);
		getContentPane().add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setEditable(false);
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setText("01097337207");
		textField_10.setColumns(10);
		textField_10.setBounds(90, 166, 120, 30);
		getContentPane().add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setEditable(false);
		textField_11.setText("메뉴");
		textField_11.setHorizontalAlignment(SwingConstants.CENTER);
		textField_11.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		textField_11.setColumns(10);
		textField_11.setBackground(new Color(112, 128, 144));
		textField_11.setBounds(12, 235, 100, 30);
		getContentPane().add(textField_11);
		
		btnNewButton = new JButton("생활관조회");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DormitoryInformation();
			}
		});
		btnNewButton.setBounds(12, 295, 100, 30);
		getContentPane().add(btnNewButton);
		
		button = new JButton("생활관신청");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DorBow();
			}
		});
		button.setBounds(144, 295, 100, 30);
		getContentPane().add(button);
		
		button_1 = new JButton("입금");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Deposit();
			}
		});
		button_1.setBounds(284, 295, 100, 30);
		getContentPane().add(button_1);
		
		button_2 = new JButton("결핵진단서");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TuberCertificate();
			}
		});
		button_2.setBounds(12, 352, 100, 30);
		getContentPane().add(button_2);
		
		button_3 = new JButton("호실정보조회");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RoomInformation();
			}
		});
		button_3.setBounds(144, 352, 100, 30);
		getContentPane().add(button_3);
		
		JButton btnNewButton_1 = new JButton("로그아웃");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login();
				dispose();
			}
		});
		btnNewButton_1.setBounds(383, 14, 91, 23);
		getContentPane().add(btnNewButton_1);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		
	}

	public static void main(String[] args) {
		new MENU_STUDENT();

	}
}
