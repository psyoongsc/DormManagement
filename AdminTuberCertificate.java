package DormitoryProgram;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64.Decoder;
import java.util.EventObject;
import java.util.StringTokenizer;
import java.awt.event.*;
import java.beans.Encoder;
import java.net.*;
import java.io.*;

public class AdminTuberCertificate extends JFrame {

	private JTextField textField;
	int count = 0;
	private JTextField textField_1;

	private String[] studentList = new String[1000];
	static int studentsize = 0;
	static int idx = 0;
	String title[] = { "학번", "이미지경로", "제출일", "진단일" };
	DefaultTableModel model = new DefaultTableModel(title, 0);

	JTable table = new JTable(model);
	JButton[] btnNewButton;

	OutputStream os = null;
	InputStream is = null;
	FileOutputStream fout = null;

	static String filename = null;
	static int getfilesize = 0;
	static String setfilesize = null;
	static int tempsize = 0;
	static int cnt = 0;
	static int selectcnt = 0;
	static ArrayList<byte[]> data = new ArrayList<>();

	static File f = null;
	private JTextField textField_2;
	private JTextField textField_3;

	public AdminTuberCertificate(Socket socket) {

		setTitle("결핵진단서 제출 확인 및 업로드"); // socket, fs 전달
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(12, 379, 860, 264);
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String value = table.getValueAt(row, 0).toString(); // row, 0// row,3
				textField_1.setText(value);
			}
		});

		// table.getColumnModel().getColumn(1).setCellRenderer(dtcr);

		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 217, 860, 387);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);

		textField = new JTextField();
		textField.setForeground(Color.WHITE);
		textField.setText("결핵진단서 제출 확인 및 업로드");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(new Color(112, 128, 144));
		textField.setBounds(12, 10, 291, 30);
		getContentPane().add(textField);

		JButton btnNewButton = new JButton("다운로드"); // 취소 클릭했을 시 터짐
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String studentNum = textField_1.getText();

				if (studentNum.equals("")) {
					JOptionPane.showMessageDialog(null, "다운로드할 학생을 클릭해주세요!");
				} else

					try {

						os = socket.getOutputStream();
						is = socket.getInputStream();

						JFileChooser chooser = new JFileChooser();
						chooser.setCurrentDirectory(new File("C:\\")); // 맨처음경로를 C로 함
						chooser.setFileSelectionMode(chooser.DIRECTORIES_ONLY); // 디렉토리만 선택가능

						int re = chooser.showSaveDialog(null);
						String savepathname = null;

						if (re == JFileChooser.APPROVE_OPTION) { // 디렉토리를 선택했으면

							File savefile = chooser.getSelectedFile(); // 선택된 디렉토리 저장하고

							savepathname = savefile.getAbsolutePath() + "\\";

							Protocol protocol = new Protocol();
							byte[] buf = protocol.getPacket();

							protocol = new Protocol(Protocol.PT_REQ_DOWNLOAD); // REQ할 때 학생의 학번 전송.
							protocol.setStudentNum(studentNum);
							os.write(protocol.getPacket());

							program: while (true) {

								is.read(buf); // 타입만 읽고 버퍼에 넣음.

								int packetType = buf[0];
								protocol.setPacket(packetType, buf);

								switch (packetType) {

								case Protocol.PT_RES_DOWNLOAD:
									System.out.println(protocol.getSeqnumD());
									if (protocol.getSeqnumD().equals("0")) { // 첫번째로 받을 시 , stream open
										chooser.setCurrentDirectory(new File("C:\\")); // 맨처음경로를 C로 함
										chooser.setFileSelectionMode(chooser.DIRECTORIES_ONLY); // 디렉토리만 선택가능
										savepathname += studentNum + ".jpg"; // server에ㅔ서 setFilename 해줘야 됨.
										textField_3.setText(protocol.getFilenameD());
										//////////////////////////////////////////////////////////////////////////////////
										tempsize = getfilesize = protocol.getFileSizeD();// 이 경로는 filechooser에서 지정한 경
										fout = new FileOutputStream(savepathname);
									}
								}

								if (protocol.isLastD().equals("1")) { // 마지막 파일
									fout.write(buf,
											Protocol.LEN_PROTOCOL_TYPE + Protocol.LEN_FILE_NAME + Protocol.LEN_FRAG
													+ Protocol.LEN_LAST + Protocol.LEN_FILE_SIZE + Protocol.LEN_SEQNUM,
											tempsize); // 파일에다 씀.

									System.out.println("정상적으로 다운 받았습니다 !"); // UI에서 띄워줄거

									fout.close();
									makeInfoNull();

									break program;
								} else {
									fout.write(buf,
											Protocol.LEN_PROTOCOL_TYPE + Protocol.LEN_FILE_NAME + Protocol.LEN_FRAG
													+ Protocol.LEN_LAST + Protocol.LEN_FILE_SIZE + Protocol.LEN_SEQNUM,
											2000); // 버퍼에 쓰인 파일데이터를 write

									System.out.println("다운 중");
									protocol = new Protocol(Protocol.PT_REQ_DOWNLOAD); // 다음 분할 데이터를 전달해주세요
									protocol.setStudentNum(studentNum);

									os.write(protocol.getPacket());
								}

								if (tempsize >= 2000) {
									tempsize -= 2000;
								} else
									tempsize = 0;
							}
							JOptionPane.showMessageDialog(null, "성공적으로 다운되었습니다!");
						} else {
							JOptionPane.showMessageDialog(null, "취소되었습니다!");
						}
					} catch (FileNotFoundException fe) {
						fe.getStackTrace();
					} catch (IOException ioe) {
						ioe.getStackTrace();
					}
			}
		});

		btnNewButton.setBounds(263, 139, 91, 23);
		getContentPane().add(btnNewButton);

		textField_1 = new JTextField();
		textField_1.setBounds(12, 139, 239, 23);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		JButton btnNewButton_1 = new JButton("일괄다운로드");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("일괄다운로드");
				int i = 0;
				String studentNum;
				String savepathname = null;
				String originpathname = null;
				JFileChooser chooser = null;
				boolean cancel = false;

				while (i < studentsize) { // 왜 studentList 가 널이됬지??
					studentNum = studentList[i];
					System.out.println(studentNum);

					try {

						if (i == 0) {
							chooser = new JFileChooser();

							chooser.setCurrentDirectory(new File("C:\\")); // 맨처음경로를 C로 함
							chooser.setFileSelectionMode(chooser.DIRECTORIES_ONLY); // 디렉토리만 선택가능

							int re = chooser.showSaveDialog(null);

							if (re == JFileChooser.APPROVE_OPTION) { // 디렉토리를 선택했으면

								File savefile = chooser.getSelectedFile(); // 선택된 디렉토리 저장하고

								originpathname = savepathname = savefile.getAbsolutePath() + "\\";

							} else {
								cancel = true;
								JOptionPane.showMessageDialog(null, "취소 되었습니다!");
								break;
							}
						}

						Protocol protocol = new Protocol();
						byte[] buf = protocol.getPacket();

						protocol = new Protocol(Protocol.PT_REQ_DOWNLOAD);
						System.out.println("학번" + studentNum);
						protocol.setStudentNum(studentNum);

						os.write(protocol.getPacket());

						program: while (true) {

							is.read(buf); // 타입만 읽고 버퍼에 넣음.

							int packetType = buf[0];
							protocol.setPacket(packetType, buf);

							switch (packetType) {

							case Protocol.PT_RES_DOWNLOAD:
								if (protocol.getSeqnumD().equals("0")) { // 첫번째로 받을 시 , stream open
									savepathname += studentList[i] + ".jpg"; // server에ㅔ서 setFilename 해줘야 됨.

									tempsize = getfilesize = protocol.getFileSizeD();// 이 경로는 filechooser에서 지정한 경
									fout = new FileOutputStream(savepathname);
									i++;
									savepathname = originpathname;

								}

								if (protocol.isLastD().equals("1")) { // 마지막 파일
									fout.write(buf,
											Protocol.LEN_PROTOCOL_TYPE + Protocol.LEN_FILE_NAME + Protocol.LEN_FRAG
													+ Protocol.LEN_LAST + Protocol.LEN_FILE_SIZE + Protocol.LEN_SEQNUM,
											tempsize); // 파일에다 씀.

									System.out.println("정상적으로 다운 받았습니다 !" + studentList[i - 1]); // UI에서 띄워줄거

									textField_3.setText(protocol.getFilenameD());

									fout.close();
									makeInfoNull();

									break program;
								} else {
									fout.write(buf,
											Protocol.LEN_PROTOCOL_TYPE + Protocol.LEN_FILE_NAME + Protocol.LEN_FRAG
													+ Protocol.LEN_LAST + Protocol.LEN_FILE_SIZE + Protocol.LEN_SEQNUM,
											2000); // 버퍼에 쓰인 파일데이터를 write

									protocol = new Protocol(Protocol.PT_REQ_DOWNLOAD); // 다음 분할 데이터를 전달해주세요
									protocol.setStudentNum(studentNum);

									os.write(protocol.getPacket());
								}

								if (tempsize >= 2000) {
									tempsize -= 2000;
								} else
									tempsize = 0;
								break;
							}
						}
					} catch (FileNotFoundException fe) {
						fe.getStackTrace();
					} catch (IOException ioe) {
						ioe.getStackTrace();
					}
				}
				if (!cancel)
					JOptionPane.showMessageDialog(null, "성공적으로 다운되었습니다!");
			}
		});
		btnNewButton_1.setBounds(384, 139, 130, 23);
		getContentPane().add(btnNewButton_1);

		JButton button = new JButton("업로드");
		button.setBounds(263, 184, 91, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String studentNum = textField_1.getText();

				if (studentNum.equals("")) {
					JOptionPane.showMessageDialog(null, "업로드할 학생을 클릭해주세요!");
				} else {
					JFileChooser jfc = new JFileChooser();

					jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
					jfc.addChoosableFileFilter(new FileNameExtensionFilter("이미지 파일", "jpg", "gif", "jpeg", "bmp", "png",
							"psd", "ai", "sketch", "tif", "tiff", "tga", "webp"));
					int response = jfc.showOpenDialog(null);

					if (response == JFileChooser.APPROVE_OPTION) {

						f = jfc.getSelectedFile();
						filename = f.getName();

						textField_2.setText(filename);

						try {

							os = socket.getOutputStream();
							is = socket.getInputStream();

							setFileInfo(f);
							Protocol protocol = new Protocol(Protocol.PT_REQ_UPLOAD_MANAGER); // 업로드 요청

							protocol.setStudentNum(studentNum);
							protocol.setFilenameUM(filename);
							protocol.setFileSizeUM(setfilesize); // 파일명과 파일크기를 전송

							fileSplit(f, setfilesize);

							protocol.setFragUM("1"); // 분할여부
							protocol.setLastUM("0"); // 1이 마지막
							protocol.setSeqnumUM(Integer.toString(cnt));
							protocol.setFileUM(data.get(cnt++)); // packet에다 file을 넣음

							os.write(protocol.getPacket());

							program: while (true) {

								protocol = new Protocol();
								byte[] buf = protocol.getPacket();
								is.read(buf);
								int packetType = buf[0];
								protocol.setPacket(packetType, buf);

								switch (packetType) {

								case Protocol.PT_RES_UPLOAD: // 파일 정보를 주고 가능한지 요청

									if (protocol.getUploadResultUM().equals("2")) {

										protocol = new Protocol(protocol.PT_REQ_UPLOAD_MANAGER); // 수신바람

										protocol.setStudentNum(studentNum);
										protocol.setFilenameUM(filename);
										protocol.setFileSizeUM(setfilesize);
										protocol.setFragUM("1"); // 분할여부

										if (data.size() - 1 == cnt) { // cnt == 103이면,,,
											protocol.setLastUM("1"); // 마지막
											protocol.setSeqnumUM(Integer.toString(cnt));
											protocol.setFileUM(data.get(cnt++));

											makeInfoNull();

											os.write(protocol.getPacket());
											break program;
										} else {
											protocol.setLastUM("0");
											protocol.setSeqnumUM(Integer.toString(cnt));
											protocol.setFileUM(data.get(cnt++));
											os.write(protocol.getPacket());
										}
									} else {
										// break program;
									}
								}
							}
						} catch (IOException ioe) {
							ioe.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "정상적으로 제출 되었습니다.");
					} else {
						JOptionPane.showMessageDialog(null, "취소되었습니다.");
					}
				}

			}
		});
		getContentPane().add(button);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(130, 184, 113, 23);
		getContentPane().add(textField_2);

		JLabel label = new JLabel("학번");
		label.setBounds(12, 124, 52, 15);
		getContentPane().add(label);

		JLabel label_1 = new JLabel("업로드 파일명");
		label_1.setBounds(147, 169, 83, 15);
		getContentPane().add(label_1);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(12, 185, 106, 22);
		getContentPane().add(textField_3);

		JLabel label_2 = new JLabel("  다운로드 파일명");
		label_2.setBounds(12, 169, 106, 15);
		getContentPane().add(label_2);

		JButton button_1 = new JButton("저장");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					os = socket.getOutputStream();
					is = socket.getInputStream();
					int cnt = 0;
					String data = "";

					Protocol protocol = new Protocol();
					byte[] buf = protocol.getPacket();

					table.setEditingColumn(3);

					while (cnt < count) {
						System.out.println(cnt + " " + count);
						String studentNum = table.getValueAt(cnt, 0).toString();
						String date = table.getValueAt(cnt, 3).toString();

						data += studentNum + "#" + date;

						if (cnt < count - 1) {
							data += "#";
						}
						cnt++;
					}
					System.out.println(data);

					if (data == "") {
						JOptionPane.showMessageDialog(null, "저장할 내용이 없습니다.");
					} else {
						JOptionPane.showMessageDialog(null, "정상적으로 저장 되었습니다.");

						protocol = new Protocol(Protocol.SEND_DATA);
						protocol.setData(data);
						protocol.setWhere(Protocol.DIAGNOSIS_DATE);

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
		});

		button_1.setBounds(704, 184, 83, 23);
		getContentPane().add(button_1);

		JButton button_2 = new JButton("조회");
		button_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					String str1, str2, str3, str4;
					String arr[] = { "", "", "", "" };

					while(count > 0) {
						model.removeRow(--count);
						studentsize--;
					}
					os = socket.getOutputStream();
					is = socket.getInputStream();

					Protocol protocol = new Protocol();
					byte[] buf = protocol.getPacket(); // QueryRReulst

					protocol = new Protocol(Protocol.PT_REQ_OPEN);
					protocol.setKindofOpen("6");

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
							str4 = stkr.nextToken();

							arr[0] = str1;
							arr[1] = str2;
							arr[2] = str3;
							arr[3] = str4;

							studentList[studentsize] = str1;
							count++;
							studentsize++;

							model.addRow(arr);
						}
					}
					// 리소스 반환
				} catch (Exception e1) {
					e1.printStackTrace();
				} // 테이블 만드는 문 전체에다 추가해주세요
			}
		});
		button_2.setBounds(618, 184, 83, 23);
		getContentPane().add(button_2);
		
		JButton button_3 = new JButton("선발");
		button_3.setBounds(789, 184, 83, 23);
		getContentPane().add(button_3);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(902, 676);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectcnt == 0) {
					try {
						selectcnt ++;
						os = socket.getOutputStream();
						is = socket.getInputStream();

						Protocol protocol = new Protocol();
						byte[] buf = protocol.getPacket();
						
						protocol = new Protocol(Protocol.SEND_DATA);
						protocol.setWhere("13");
						os.write(protocol.getPacket());
						
						is.read(buf);
						
						JOptionPane.showMessageDialog(null, "선발 완료!");
					}
					catch(IOException ie) {
						ie.getStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "이미 선발되었습니다!");
					}
				}
			}); 
		}

	public static void setFileInfo(File f) {
		long fsize = 0; // 파일 정보

		if (f.exists()) {
			fsize = f.length();
		}
		setfilesize = Long.toString(fsize);
	}

	static void fileSplit(File f, String filesize) throws FileNotFoundException, IOException {

		int fsize = Integer.parseInt(filesize);
		FileInputStream file = new FileInputStream(f); // ★★★★★★★★★
		data = new ArrayList();
		for (int i = 0; i <= fsize / 2000; i++) { // 103358 byte -> 104번 0~ 103
			data.add(new byte[2000]);
			file.read(data.get(i), 0, 2000);
		}
	}

	public static void makeInfoNull() {
		filename = null;
		setfilesize = null;
		getfilesize = 0;
		tempsize = 0;
		cnt = 0;
		data = new ArrayList<>();
		f = null;
	}
}