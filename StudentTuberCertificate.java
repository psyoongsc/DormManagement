package DormitoryProgram;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.*;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.filechooser.*;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.border.LineBorder;

public class StudentTuberCertificate extends JFrame {

	private JMenuBar menuBar;
	private JMenuItem mntmNewMenuItem;
	JTextArea textArea = new JTextArea();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField6;
	static String filename = null;
	static String filesize = null;
	private int cnt = 0;

	OutputStream os = null;
	InputStream is = null;

	static ArrayList<byte[]> data = new ArrayList<>();

	static File f = null;

	public StudentTuberCertificate(LoginInfo info, Socket socket) throws IOException {

		System.out.println("결진 제출창");
		os = socket.getOutputStream();
		is = socket.getInputStream();

		setAutoRequestFocus(false);
		setTitle("결핵진단서 제출");
		getContentPane().setLayout(null);

		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setBackground(Color.WHITE);
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("HY寃ш퀬�뵓", Font.PLAIN, 16));
		textField_5.setBounds(133, 94, 330, 35);
		getContentPane().add(textField_5);
		textField_5.setColumns(10);

		JButton btnNewButton_1 = new JButton("제출");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (filename == null) {
					JOptionPane.showMessageDialog(null, "제출 파일을 선택해주세요!");
				} else {

					try {

						Protocol protocol = new Protocol(Protocol.PT_REQ_UPLOAD); // 업로드 요청

						// 제출할 파일명, UI에서 파일을 입력하게 함.
						setFileInfo(filename);

						protocol.setFilename(filename);
						protocol.setFileSize(filesize); // 파일명과 파일크기를 전송
						System.out.println(protocol.getFileSize());
						if (filename != null) {
							fileSplit(filename, filesize);
						} // 파일분할.

						protocol.setFrag("1"); // 분할여부
						protocol.setLast("0"); // 1이 마지막
						protocol.setSeqnum(Integer.toString(cnt));
						protocol.setFile(data.get(cnt++)); // packet에다 file을 넣음

						os.write(protocol.getPacket());

						boolean program_stop = false;

						program: while (true) {

							protocol = new Protocol();
							byte[] buf = protocol.getPacket();
							is.read(buf);
							int packetType = buf[0];
							protocol.setPacket(packetType, buf);

							if (packetType == Protocol.PT_EXIT) {
								System.out.println("클라이언트 종료");
								break;
							}

							switch (packetType) {
							case Protocol.PT_RES_UPLOAD:

								if (protocol.getUploadResult().equals("2")) {

									protocol = new Protocol(protocol.PT_REQ_UPLOAD); // 수신바람

									protocol.setFilename(filename);
									protocol.setFileSize(filesize);
									protocol.setFrag("1"); // 분할여부

									if (data.size() - 1 == cnt) { // cnt == 103이면,,,
										protocol.setLast("1"); // 마지막
										protocol.setSeqnum(Integer.toString(cnt));
										protocol.setFile(data.get(cnt++));
										os.write(protocol.getPacket());

										break;
									} else {
										protocol.setLast("0");
										protocol.setSeqnum(Integer.toString(cnt));
										protocol.setFile(data.get(cnt++));
										os.write(protocol.getPacket());
									}
								} else {
									break program;
								}

								break;
							}
							if (program_stop)
								break;
						}
					}

					catch (IOException ioe) {
						ioe.printStackTrace();
					}

					JOptionPane.showMessageDialog(null, "정상적으로 제출 되었습니다.");
				}
			}
		});

		btnNewButton_1.setBounds(381, 62, 82, 28);
		btnNewButton_1.setFont(new Font("HY寃ш퀬�뵓", Font.PLAIN, 12));
		getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("파일 열기");
		btnNewButton_2.addActionListener(new ActionListener() { // 파일입력
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				// FileNameExtensionFilter defaultFilter;

				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				jfc.addChoosableFileFilter(new FileNameExtensionFilter("이미지 파일", "jpg", "gif", "jpeg", "bmp", "png",
						"psd", "ai", "sketch", "tif", "tiff", "tga", "webp"));
				int response = jfc.showOpenDialog(null);

				if (response == JFileChooser.APPROVE_OPTION) {
					f = jfc.getSelectedFile();
					filename = f.getName();
				}
				System.out.println(filename);
				textField_5.setText(filename);
			}
		});

		btnNewButton_2.setBounds(10, 95, 121, 34);
		getContentPane().add(btnNewButton_2);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBackground(new Color(112, 128, 144));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText(info.getId());
		textField.setFont(new Font("HY寃ш퀬�뵓", Font.PLAIN, 16));
		textField.setBounds(145, 24, 123, 28);
		getContentPane().add(textField);
		textField.setColumns(10);

		textField6 = new JTextField();
		textField6.setEditable(false);
		textField6.setBackground(new Color(112, 128, 144));
		textField6.setHorizontalAlignment(SwingConstants.CENTER);
		textField6.setText(info.getName());
		textField6.setFont(new Font("HY寃ш퀬�뵓", Font.PLAIN, 16));
		textField6.setBounds(10, 24, 123, 28);

		getContentPane().add(textField6);
		textField6.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setText("제출일");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("HY寃ш퀬�뵓", Font.PLAIN, 12));
		textField_1.setBackground(new Color(112, 128, 144));
		textField_1.setBounds(10, 172, 82, 34);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBackground(Color.WHITE);
		textField_3.setEditable(false);
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setBounds(88, 173, 135, 34);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setText("진단일");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("HY寃ш퀬�뵓", Font.PLAIN, 12));
		textField_2.setBackground(new Color(112, 128, 144));
		textField_2.setColumns(10);
		textField_2.setBounds(243, 172, 82, 34);
		getContentPane().add(textField_2);

		textField_4 = new JTextField();
		textField_4.setBackground(Color.WHITE);
		textField_4.setEditable(false);
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setColumns(10);
		textField_4.setBounds(315, 173, 135, 33);
		getContentPane().add(textField_4);

		try {
			Protocol protocol = new Protocol();
			byte[] buf = protocol.getPacket();

			protocol = new Protocol(Protocol.PT_REQ_OPEN);
			protocol.setStudentNum(info.getId());
			protocol.setKindofOpen("11");

			os.write(protocol.getPacket());

			is.read(buf);
			protocol.setPacket(buf[0], buf);
			String result = protocol.getOpenQueryResult();
			StringTokenizer stkr = new StringTokenizer(result, "#");
			System.out.println(result);

			String submitday = "", dignosisday = "";

			if (buf[0] == 19) {

				submitday = stkr.nextToken();
				dignosisday = stkr.nextToken();
			}

			textField_3.setText(submitday);
			textField_4.setText(dignosisday);
			System.out.println(submitday + dignosisday);

			if (!dignosisday.equals("")) {
				btnNewButton_1.disable();
			}

		} catch (IOException e) {

		}
		setVisible(true);
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public static void setFileInfo(String filename) {
		long fsize = 0; // 파일 정보

		if (f.exists()) {
			fsize = f.length();
		}
		filesize = Long.toString(fsize);
		System.out.println(filesize);
	}

	static void fileSplit(String filename, String filesize) throws FileNotFoundException, IOException {

		int fsize = Integer.parseInt(filesize);
		FileInputStream file = new FileInputStream(f); // ★★★★★★★★★
		data = new ArrayList();
		for (int i = 0; i <= fsize / 2000; i++) { // 103358 byte -> 104번 0~ 103
			data.add(new byte[2000]);
			file.read(data.get(i), 0, 2000);
		}
	}
}