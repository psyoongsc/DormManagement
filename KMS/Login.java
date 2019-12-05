package KMS;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import PSY.LoginInfo;
import PSY.LoginThread;
import YJ.Protocol;

// JOptionPane.showMessageDialog(owner, "�α��� ����"); //this : �θ� ������ �߾ӿ� ǥ��,  this�� �ȵǹǷ� ������ owner = this,  null : ȭ�� �߾�

public class Login extends JFrame {
	private JTextField textLogin;
	private JPasswordField textPassword;
	private JButton btnOk;
	private static Login owner; // �ؽ�Ʈ ����

	static Protocol protocol;
	static OutputStream os;
	static InputStream is;

	private Socket socket = null;
	private LoginThread client = null;
	private LoginInfo stuInfo = null;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) throws IOException {

		if (args.length != 2)
			System.out.println("Usage: java ChatClient host port");
		else {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						owner = new Login(args[0], Integer.parseInt(args[1]));
						owner.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

		}
	}

	/**
	 * Create the frame.
	 */
	public Login() {

	} // UI 부분

	public Login(String serverName, int serverPort) {
		super("로그인창"); // ����

		owner = this; // ������

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 424, 271);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514");
		lblNewLabel.setBounds(35, 37, 60, 40);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblNewLabel_1.setBounds(35, 98, 60, 40);
		getContentPane().add(lblNewLabel_1);

		textLogin = new JTextField();
		textLogin.setBounds(109, 38, 259, 39);
		textLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		getContentPane().add(textLogin);
		textLogin.setColumns(10);
		textPassword = new JPasswordField();
		textPassword.setBounds(109, 98, 260, 40);
		textPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnOk.doClick(); // ����
			}
		});
		getContentPane().add(textPassword);

		btnOk = new JButton("\uB85C\uADF8\uC778");
		btnOk.setBounds(35, 150, 333, 40);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textLogin.getText().toString().trim().equals(""))
					JOptionPane.showMessageDialog(null, "ID를 입력하세요.");
				else if (textPassword.getText().toString().trim().equals(""))
					JOptionPane.showMessageDialog(null, "PASSWORD를 입력하세요.");
				else {
					String id = textLogin.getText(); // 쓸 기회를 주고 이걸 받아야하는데 그냥 받으니까 저래 나오는거다.
					String pwd = textPassword.getText();
					// 로그인 정보 생성 및 패킷 전송
					protocol = new Protocol(Protocol.PT_RES_LOGIN);
					protocol.setId(id);
					protocol.setPassword(pwd);
					System.out.println("로그인 정보 전송");
					try {
						os.write(protocol.getPacket());
						os.flush();
					} catch (IOException ioe) {
						System.out.println("Sending error: " + ioe.getMessage());
						stop();
					}
					// System.out.println(protocol.getId());
				}
			}
		});
		getContentPane().add(btnOk);
		
		owner.disable();

		System.out.println("Establishing connection. Please wait ...");
		try {
			socket = new Socket(serverName, serverPort);
			System.out.println("Connected: " + socket);
			owner.enable();
			start();
		} catch (UnknownHostException uhe) {
			System.out.println("Host unknown: " + uhe.getMessage());
			System.exit(0);
		} catch (IOException ioe) {
			System.out.println("Unexpected exception: " + ioe.getMessage());
			System.exit(0);
		}
	}

	public void start() throws IOException { // Stream Open
		is = socket.getInputStream();
		os = socket.getOutputStream();
		System.out.println("in start");
		System.out.println(socket.getPort());
		client = new LoginThread(this, socket);
	}

	public void stop() {
		try {
			if (is != null)
				is.close();
			if (os != null)
				os.close();
			if (socket != null)
				socket.close();
		} catch (IOException ioe) {
			System.out.println("Error closing ...");
		}
		client.stop();
	}

	public void handle() throws IOException {
		System.out.println("in handle");
		protocol = new Protocol();
		byte[] buf = protocol.getPacket();
		int chance = 5;

		while (true) {
			
			is.read(buf);
			int packetType = buf[0];
			protocol.setPacket(packetType, buf);
			if (packetType == Protocol.PT_EXIT) {
				System.out.println("클라이언트 종료");
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
				break;
			}

			switch (packetType) {
			case Protocol.PT_REQ_LOGIN:
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							owner.setVisible(true);
//                    login.startClient();

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				JOptionPane.showMessageDialog(null, "로그인 정보가 잘못되었습니다. " + "남은 기회 " + --chance + "번");
				System.out.println("로그인 실패\n");

				System.out.println("서버가 로그인 정보 요청");
				System.out.println("남은 기회 : " + chance);
				break;

			case Protocol.PT_LOGIN_RESULT:
				System.out.println("서버가 로그인 결과 전송.");
				String result = protocol.getLoginResult();
				if (result.equals("1")) {
					String queryResult = protocol.getQueryResult();
					System.out.println("* 환영합니다 *");
					if (queryResult.charAt(0) == 'a') {
						new MENU_ADMIN(queryResult);
					} else {
						new MENU_STUDENT(queryResult);
					}
					owner.setVisible(false);

				} else {
					System.out.println("비밀번호 오류횟수 5회이상 아이디입니다.");
					JOptionPane.showMessageDialog(null, "비밀번호 오류횟수 5회이상 아이디입니다.");
				}
				protocol = new Protocol(Protocol.PT_EXIT);
				System.out.println("종료 패킷 전송");
				os.write(protocol.getPacket());
				break;
			}
		}
	}

//		if(args.length < 2) System.out.println("사용법 : " + "java LoginClient 주소 포트번호");

}
