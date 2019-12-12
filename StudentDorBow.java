package DormitoryProgram;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import java.net.Socket;
import javax.swing.JTextPane;
import java.io.*;
import java.net.*;
import java.io.*;

public class StudentDorBow extends JFrame {
	private OutputStream os = null;
	private InputStream is = null;
	public StudentDorBow(LoginInfo info, Socket socket) {
		
		setTitle("입사서약서");
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("동의하지않음");
		btnNewButton.setBackground(UIManager.getColor("Button.highlight"));
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				}
		});
		
		btnNewButton.setBounds(149, 521, 186, 50);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("동의");
		btnNewButton_1.setBackground(UIManager.getColor("Button.highlight"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					is = socket.getInputStream();
					os = socket.getOutputStream();
				
					Protocol protocol =new Protocol();
					byte[] buf = protocol.getPacket();
				
					protocol = new Protocol(Protocol.SEND_DATA);
					protocol.setData(info.getId()+"#O#O#");
					protocol.setWhere("15");
					
					os.write(protocol.getPacket());
					
					is.read(buf);
	
					if(buf[0] == Protocol.RES_DATA) {
						System.out.println("동의 데이터 등록");
					}
				}
				catch(IOException ie) {
					ie.getStackTrace();
				}
				new StudentDorApplication(info, socket);
				setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 20));
		btnNewButton_1.setBounds(408, 521, 179, 50);
		getContentPane().add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(106, 32, 564, 461);
		getContentPane().add(scrollPane);
		
		JTextPane textArea = new JTextPane();
		textArea.setEditable(false);
		textArea.setText("제 1조 (정의 및 목적)\r\n이 규정은 금오공과대학교 생활관의 운영에 필요한 사항에 대하여 규정함을 목적으로 한다.\r\n\r\n제 2조 (운영기간)\r\n금오공과대학교 생활관(이하\"생활관\"이라 한다)의 운영기간은 학사일정에 따른 학기개관과 방학 중 특별개관으로 구분하여, 세부사항은 생활관장이 따로 정한다.\r\n\r\n제 3조 (시설이용)\r\n생활관 시설의 이용은 본교 재학생 중 생활관생으로 선발된 자(이하 생활관생 이라 한다)에 한한다. 다만, 생활관장은 생활관 운영에 지장이 없는 경우에는 생활관생 이외이 자에게도 이용을 허가할 수 있다.\r\n\r\n제 4조 (조직)\r\n① 생활관에는 생활관장을 두며, 본교에 재직하는 전임 교원 중에서 총장이 임명한다.\r\n② 생활관의 행정 업무 처리를 위하여 행정실을 두고, 행정실에는 본교 소속직원과 자체직원을 둘 수 있으며, 자체직원의 보수 및 복무에 관한 사항은 생활관장이 따로 정한다.\r\n\r\n제5조(생활관장의 직무)\r\n생활관장은 생활관의 관리운영에 관한 사무를 총괄하고, 소속직원을 지휘․감독하며, 생활관생에 관한 사무를 관장한다.\r\n\r\n제6조(학생회)\r\n생활관은 질서유지 및 면학분위기 조성 등을 위하여 생활관 학생회를 구성할 수 있다.\r\n\r\n제7조 (입사자격)\r\n① 생활관에 입사할 수 있는 자는 본교 재학생 중에서 생활관생으로 선발된 자와 각종 프로그램 수강을 위하여 생활관장으로부터 입사를 허가 받은 자로 한다.\r\n② 생활관장은 본교 방문자 등의 거주 편의를 위해 제1항 이외의 자에게도 입사를 허가할 수 있다.\r\n③ 생활관 입사자격에 관한 세부사항은 생활관장이 따로 정한다.\r\n\r\n제8조 (선발방법)\r\n① 생활관생의 선발방법은 성적 등에 의한 일반선발과 사회적 배려자 등의 우선선발로 구분하고, 이에 대한 세부사항은 생활관장이 따로 정한다.\r\n생활관장은 생활관생 선발계획을 홈페이지 등에 공개하여야 한다.\r\n\r\n제9조(입사 및 퇴사)\r\n① 생활관생으로 선발된 자는 생활관 입사등록 절차를 거쳐 정해진 기간에 입사하여야 한다.\r\n② 생활관생은 입사관련 증빙서류 및 입사약정서를 정해진 기한 내에 생활관 행정실로 제출하여야 한다.\r\n③ 생활관생이 생활관을 퇴사할 경우에는 필요한 절차를 거쳐야 한다.\r\n\r\n제10조 (생활관 수칙)\r\n① 생활관생은 생활관 공동생활과 면학분위기를 저해하는 행위를 하여서는 아니 되며, 생활관생이 지켜야 할 생활관 수칙은 생활관장이 따로 정한다.\r\n② 생활관장은 생활관 수칙 위반자에게 퇴사 등 필요한 처분을 할 수 있다.\r\n\r\n제11조(생활관 점검)\r\n생활관장은 생활관생 관리를 위하여 생활관장은 생활관 점검계획을 수립하고 시행하여야 한다.");
		scrollPane.setViewportView(textArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane.setRowHeaderView(scrollPane_1);
		setVisible(true);
		setSize(800,800);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
