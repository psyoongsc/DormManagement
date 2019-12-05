import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.io.*;
import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.*;

// JOptionPane.showMessageDialog(owner, "�α��� ����"); //this : �θ� ������ �߾ӿ� ǥ��,  this�� �ȵǹǷ� ������ owner = this,  null : ȭ�� �߾�

public class Login extends JFrame {
	private JTextField textLogin;
	private JPasswordField textPassword;
	private JButton btnOk;
	private Login owner; // �ؽ�Ʈ ����

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		super("로그인창"); // ����

		owner = this; // ������

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 546, 365);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514");
		lblNewLabel.setBounds(60, 92, 60, 40);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblNewLabel_1.setBounds(60, 142, 60, 40);
		getContentPane().add(lblNewLabel_1);

		textLogin = new JTextField();
		textLogin.setBounds(146, 93, 259, 39);
		textLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		getContentPane().add(textLogin);
		textLogin.setColumns(10);
		textPassword = new JPasswordField();
		textPassword.setBounds(146, 143, 260, 40);
		textPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnOk.doClick(); // ����
			}
		});
		getContentPane().add(textPassword);

		btnOk = new JButton("\uB85C\uADF8\uC778");
		btnOk.setBounds(416, 92, 80, 90);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textLogin.getText().equals("20150154") && textPassword.getText().equals("1234")) {
					// JOptionPane.showMessageDialog(owner, "�α��� ����"); // �θ� ������ �߾ӿ� ǥ�� this�� �ȵǹǷ� ������ owner = this
					new MENU_STUDENT();
					setVisible(false);
				}
				else if(textLogin.getText().equals("f20150154") && textPassword.getText().equals("1234"))
				{
					new MENU_ADMIN();
					setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(owner, "�α��� ����");
					textPassword.setText("");
					textPassword.requestFocus(); // ���콺
				}
			}
		});
		getContentPane().add(btnOk);

		// ��Ȱ�� ��û â

		// ��Ȱ�� ��û â ��


	}
}