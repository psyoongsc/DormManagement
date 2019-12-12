package DormitoryProgram;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class UpdateDorForApplication extends JFrame {
	private JTextField textField;
	private JTextField txtid;
	private JTextField textField_2;
	private JTextField txtid_1;
	private JTextField textField_4;
	private JTextField txtid_2;
	private JTextField textField_6;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	
	private OutputStream os = null;
	private InputStream is = null;
	int cnt = 0;
	private JButton button;
	public UpdateDorForApplication(Socket socket) {
		
		String title[]= {"생활관","급식실","기숙기간","생활관사용료","기타공공요금","식사비용7일","식사비용5일","수용인원_남","수용인원_여"};
		DefaultTableModel model = new DefaultTableModel(title, 0); 
		getContentPane().setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setText("생활관사용료");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("HY견고딕", Font.PLAIN, 8));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setBounds(487, 67, 60, 23);
		getContentPane().add(textField_1);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("굴림", Font.PLAIN, 8));
		textField_3.setColumns(10);
		textField_3.setBackground(Color.WHITE);
		textField_3.setBounds(546, 67, 100, 23);
		getContentPane().add(textField_3);
		
		textField_5 = new JTextField();
		textField_5.setText("기타공공요금");
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("HY견고딕", Font.PLAIN, 8));
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBackground(Color.LIGHT_GRAY);
		textField_5.setBounds(645, 67, 60, 23);
		getContentPane().add(textField_5);
		
		textField_7 = new JTextField();
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setFont(new Font("굴림", Font.PLAIN, 8));
		textField_7.setColumns(10);
		textField_7.setBackground(Color.WHITE);
		textField_7.setBounds(704, 67, 100, 23);
		getContentPane().add(textField_7);
		
		textField = new JTextField();
		textField.setText("생활관편집");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(new Color(112, 128, 144));
		textField.setBounds(12, 10, 130, 30);
		getContentPane().add(textField);
		
		JButton btnNewButton = new JButton("저장");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
				os = socket.getOutputStream();
				is = socket.getInputStream();
				//i i v i i i i i i
				
				String t2 = textField_2.getText();
				String t4 = textField_4.getText();
				String t6 = textField_6.getText();
				String t3 = textField_3.getText();
				String t7 = textField_7.getText();
				String t9 = textField_9.getText();
				String t11 = textField_11.getText();
				String t13 = textField_13.getText();
				String t15 = textField_15.getText();
				
				if(t2.equals("")|| t4.equals("") || t6.equals("")|| t3.equals("")|| t7.equals("") || 
						t9.equals("")|| t11.equals("")|| t13.equals("")|| t15.equals("")) {
					JOptionPane.showMessageDialog(null, "정보를 모두 입력해주세요.");
				}
				
				else 
				{
				
				Protocol protocol = new Protocol();
				byte[] buf = protocol.getPacket();
				
				String data = t2 +"#"+ t4+ "#" + t6 + "#" + t3 + "#" + t7 + "#" + t9+ "#" + t11+"#" + t13 + "#" + t15 ;
				
				protocol = new Protocol(Protocol.SEND_DATA); // 데이타 전송
             
                protocol.setData(data);
                protocol.setWhere(protocol.DORM_FOR_APPLICATION);
               os.write(protocol.getPacket());
                
                program:while(true){
                    
                	is.read(buf);
                    int packetType = buf[0];

                    protocol.setPacket(packetType,buf);
                    
                    switch(packetType){

                        case Protocol.RES_DATA:
                            System.out.println("생활관_신청 정보 등록 완료");
                            break program;
                    	}
                	}
				}
				}
				catch(IOException ioe) {
					ioe.getStackTrace();
				}
				//sql.UpdateDorForApplicationPart(i2, i4, t6, i3, i7, i9, i11, i13, i15);
			}
		});
		
		btnNewButton.setBounds(705, 14, 91, 23);
		getContentPane().add(btnNewButton);
		
		txtid = new JTextField();
		txtid.setText("생활관복합id");
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setFont(new Font("HY견고딕", Font.PLAIN, 8));
		txtid.setEditable(false);
		txtid.setColumns(10);
		txtid.setBackground(Color.LIGHT_GRAY);
		txtid.setBounds(12, 67, 60, 23);
		getContentPane().add(txtid);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("굴림", Font.PLAIN, 8));
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setColumns(10);
		textField_2.setBackground(Color.WHITE);
		textField_2.setBounds(71, 67, 100, 23);
		getContentPane().add(textField_2);
		
		txtid_1 = new JTextField();
		txtid_1.setText("급식실id");
		txtid_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtid_1.setFont(new Font("HY견고딕", Font.PLAIN, 8));
		txtid_1.setEditable(false);
		txtid_1.setColumns(10);
		txtid_1.setBackground(Color.LIGHT_GRAY);
		txtid_1.setBounds(170, 67, 60, 23);
		getContentPane().add(txtid_1);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("굴림", Font.PLAIN, 8));
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setColumns(10);
		textField_4.setBackground(Color.WHITE);
		textField_4.setBounds(229, 67, 100, 23);
		getContentPane().add(textField_4);
		
		txtid_2 = new JTextField();
		txtid_2.setText("기숙기간");
		txtid_2.setHorizontalAlignment(SwingConstants.CENTER);
		txtid_2.setFont(new Font("HY견고딕", Font.PLAIN, 8));
		txtid_2.setEditable(false);
		txtid_2.setColumns(10);
		txtid_2.setBackground(Color.LIGHT_GRAY);
		txtid_2.setBounds(328, 67, 60, 23);
		getContentPane().add(txtid_2);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("굴림", Font.PLAIN, 8));
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setColumns(10);
		textField_6.setBackground(Color.WHITE);
		textField_6.setBounds(387, 67, 100, 23);
		getContentPane().add(textField_6);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 161, 792, 259);
		getContentPane().add(scrollPane);
		
		
		textField_8 = new JTextField();
		textField_8.setText("식사비용7일");
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setFont(new Font("HY견고딕", Font.PLAIN, 8));
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBackground(Color.LIGHT_GRAY);
		textField_8.setBounds(12, 110, 60, 23);
		getContentPane().add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setFont(new Font("굴림", Font.PLAIN, 8));
		textField_9.setColumns(10);
		textField_9.setBackground(Color.WHITE);
		textField_9.setBounds(71, 110, 100, 23);
		getContentPane().add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setText("식사비용5일");
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setFont(new Font("HY견고딕", Font.PLAIN, 8));
		textField_10.setEditable(false);
		textField_10.setColumns(10);
		textField_10.setBackground(Color.LIGHT_GRAY);
		textField_10.setBounds(170, 110, 60, 23);
		getContentPane().add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setHorizontalAlignment(SwingConstants.CENTER);
		textField_11.setFont(new Font("굴림", Font.PLAIN, 8));
		textField_11.setColumns(10);
		textField_11.setBackground(Color.WHITE);
		textField_11.setBounds(229, 110, 100, 23);
		getContentPane().add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setText("수용인원-남");
		textField_12.setHorizontalAlignment(SwingConstants.CENTER);
		textField_12.setFont(new Font("HY견고딕", Font.PLAIN, 8));
		textField_12.setEditable(false);
		textField_12.setColumns(10);
		textField_12.setBackground(Color.LIGHT_GRAY);
		textField_12.setBounds(328, 110, 60, 23);
		getContentPane().add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.setHorizontalAlignment(SwingConstants.CENTER);
		textField_13.setFont(new Font("굴림", Font.PLAIN, 8));
		textField_13.setColumns(10);
		textField_13.setBackground(Color.WHITE);
		textField_13.setBounds(387, 110, 100, 23);
		getContentPane().add(textField_13);
		
		textField_14 = new JTextField();
		textField_14.setText("수용인원-여");
		textField_14.setHorizontalAlignment(SwingConstants.CENTER);
		textField_14.setFont(new Font("HY견고딕", Font.PLAIN, 8));
		textField_14.setEditable(false);
		textField_14.setColumns(10);
		textField_14.setBackground(Color.LIGHT_GRAY);
		textField_14.setBounds(487, 110, 60, 23);
		getContentPane().add(textField_14);
		
		textField_15 = new JTextField();
		textField_15.setHorizontalAlignment(SwingConstants.CENTER);
		textField_15.setFont(new Font("굴림", Font.PLAIN, 8));
		textField_15.setColumns(10);
		textField_15.setBackground(Color.WHITE);
		textField_15.setBounds(546, 110, 100, 23);
		getContentPane().add(textField_15);
		
		button = new JButton("조회");
		button.setBounds(607, 14, 91, 23);
		getContentPane().add(button);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(824,469);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(cnt == 0) {
					cnt ++;
					try {

						table = new JTable(model);
						String arr[]={"","","","","","","","",""};
						String str2 = null, str3 = null, str4 = null, rs = null;
						int i1 = 0, i2= 0, i3= 0, i4= 0,i5= 0, i6= 0, i7= 0, i8= 0;
						         
						         os = socket.getOutputStream();
						         is = socket.getInputStream();
						         
						         Protocol protocol = new Protocol();
						         byte[] buf = protocol.getPacket(); // QueryRReulst
						        
						         protocol = new Protocol(Protocol.PT_REQ_OPEN);
						         protocol.setKindofOpen("2");
						         
						         os.write(protocol.getPacket());
						         
						         is.read(buf);
						         
						         protocol.setPacket(buf[0], buf);
						         String result = protocol.getOpenQueryResult();
						         StringTokenizer stkr = new StringTokenizer(result, "#");
						         
						         if(buf[0] == 19) {
						        	 program: while(stkr.hasMoreTokens()){
						        	 
						        	String str1 = stkr.nextToken();
						        	if(str1 == "") {
						            	break program;
						            }
						            str2 =stkr.nextToken();
						            str3= stkr.nextToken();
						            i1=Integer.parseInt(stkr.nextToken());
						            i2=Integer.parseInt(stkr.nextToken());
						            i3=Integer.parseInt(stkr.nextToken());
						            i4=Integer.parseInt(stkr.nextToken());
						            i5=Integer.parseInt(stkr.nextToken());
						            i6=Integer.parseInt(stkr.nextToken());
						            
						            arr[0]=str1;
						            arr[1]=str2;
						            arr[2]=str3;
						            arr[3]=i1+"";
						            arr[4]=i2+"";
						            arr[5]=i3+"";
						            arr[6]=i4+"";
						            arr[7]=i5+"";
						            arr[8]=i6+"";
						           
						            model.addRow(arr);
						        	 }
						        	 }
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					scrollPane.setViewportView(table);
				}
				else
					JOptionPane.showMessageDialog(null,"이미 조회되었습니다 !");
			}
		});
		//2 4 6 3 7 9 11 13 15
	}
}

