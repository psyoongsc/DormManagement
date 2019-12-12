// UpdateSchedule 진짜 최종..

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

public class UpdateSchedule extends JFrame {
   private JTextField textField;
   private JTextField txtid;
   private JTextField textField_2;
   private JTextField txtid_2;
   private JTextField textField_6;
   private JTable table;
   private JTextField textField_1;
   private JTextField textField_3;
   private OutputStream os = null;
   private InputStream is= null;
   private JButton button;
   int cnt= 0;
   
   public UpdateSchedule(Socket socket) {
      String title[]= {"일정명","시작일시","종료일시"};
      DefaultTableModel model = new DefaultTableModel(title, 0); 
      getContentPane().setLayout(null);
      
      textField = new JTextField();
      textField.setForeground(Color.WHITE);
      textField.setText("선발 일정 등록");
      textField.setHorizontalAlignment(SwingConstants.CENTER);
      textField.setFont(new Font("HY견고딕", Font.PLAIN, 16));
      textField.setEditable(false);
      textField.setColumns(10);
      textField.setBackground(new Color(112, 128, 144));
      textField.setBounds(12, 10, 130, 30);
      getContentPane().add(textField);
      
      JButton btnNewButton = new JButton("수정");
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String t2 = textField_2.getText();
            String t4 = textField_6.getText();
            String t6 = textField_3.getText();
			
		if(t2.equals("") || t4.equals("") || t6.equals("") ) {
				 JOptionPane.showMessageDialog(null, "필드를 모두 입력해주세요.");
		}
			else {
				try {
					
				os = socket.getOutputStream();
				is = socket.getInputStream();
				Protocol protocol = new Protocol();
			    byte[] buf = protocol.getPacket();
			        
			    System.out.println("서버가 데이터 요청");
                protocol = new Protocol(Protocol.SEND_DATA);
                String data = t2 +"#" + t4 + "#" + t6 +"#";
               
                protocol.setData(data);
                protocol.setWhere(protocol.SCHEDULE);

                System.out.println("서버에게 데이터 전송");
                os.write(protocol.getPacket());

			        program:while(true){
			        	
			        is.read(buf);
			            int packetType = buf[0];

			            protocol.setPacket(packetType,buf);

			            switch(packetType){
			                case Protocol.RES_DATA:
			                    System.out.println("일정 등록 완료.");
			                    
			                    break program;
			            }
			        }
				}
				catch(IOException io) {
					io.getStackTrace();
				}
			JOptionPane.showMessageDialog(null, "수정 성공했습니다.");
			
				}
			}
		});
      
      
      btnNewButton.setBounds(690, 14, 91, 23);
      getContentPane().add(btnNewButton);
      
      txtid = new JTextField();
      txtid.setText("일정명");
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
      
      txtid_2 = new JTextField();
      txtid_2.setText("시작일시");
      txtid_2.setHorizontalAlignment(SwingConstants.CENTER);
      txtid_2.setFont(new Font("HY견고딕", Font.PLAIN, 8));
      txtid_2.setEditable(false);
      txtid_2.setColumns(10);
      txtid_2.setBackground(Color.LIGHT_GRAY);
      txtid_2.setBounds(170, 67, 60, 23);
      getContentPane().add(txtid_2);
      
      textField_6 = new JTextField();
      textField_6.setFont(new Font("굴림", Font.PLAIN, 8));
      textField_6.setHorizontalAlignment(SwingConstants.CENTER);
      textField_6.setColumns(10);
      textField_6.setBackground(Color.WHITE);
      textField_6.setBounds(229, 67, 100, 23);
      getContentPane().add(textField_6);
      
      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(12, 161, 769, 259);
      getContentPane().add(scrollPane);
      
      
      textField_1 = new JTextField();
      textField_1.setText("종료일시");
      textField_1.setHorizontalAlignment(SwingConstants.CENTER);
      textField_1.setFont(new Font("HY견고딕", Font.PLAIN, 8));
      textField_1.setEditable(false);
      textField_1.setColumns(10);
      textField_1.setBackground(Color.LIGHT_GRAY);
      textField_1.setBounds(328, 67, 60, 23);
      getContentPane().add(textField_1);
      
      textField_3 = new JTextField();
      textField_3.setHorizontalAlignment(SwingConstants.CENTER);
      textField_3.setFont(new Font("굴림", Font.PLAIN, 8));
      textField_3.setColumns(10);
      textField_3.setBackground(Color.WHITE);
      textField_3.setBounds(387, 67, 100, 23);
      getContentPane().add(textField_3);
      
      button = new JButton("조회");
      button.setBounds(586, 14, 91, 23);
      getContentPane().add(button);
      setVisible(true);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setSize(809,469);
      button.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            if(cnt == 0){
            	cnt ++;

                table = new JTable(model);
                table.setCellSelectionEnabled(true);
                table.setFocusable(false);
                table.setColumnSelectionAllowed(false);
                table.setRowSelectionAllowed(false);
                
                String arr[]={"","","","","","","","",""};

                try {
              	  String str1, str2, str3;
                    
                    os = socket.getOutputStream();
                    is = socket.getInputStream();
                    
                    Protocol protocol = new Protocol();
                    byte[] buf = protocol.getPacket(); // QueryRReulst
                   
                    protocol = new Protocol(Protocol.PT_REQ_OPEN);
                    protocol.setKindofOpen("1");
                    
                    os.write(protocol.getPacket());
                    
                    is.read(buf);
                    
                    protocol.setPacket(buf[0], buf);
                    String str4 = protocol.getOpenQueryResult();
                    StringTokenizer stkr = new StringTokenizer(str4, "#");
                    
                    if(buf[0] == 19) {
                   	 program: while(stkr.hasMoreTokens()){
                   	 
                       str1=stkr.nextToken();
                        if(str1 == "") {
                       	break program;
                       }
                       str2=stkr.nextToken();
                       str3=stkr.nextToken();
                       
                       arr[0]=str1;
                       arr[1]=str2;
                       arr[2]=str3;
                       model.addRow(arr);
                   	 }
                    }
                } catch (Exception e1) {
                   e1.printStackTrace();
                }
                scrollPane.setViewportView(table);
            }
            
            else{
           
 			JOptionPane.showMessageDialog(null, "이미 조회되었습니다 !");
 			
 				}
 			}
 		});
       
   }
}
