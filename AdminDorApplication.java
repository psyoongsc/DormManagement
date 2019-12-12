package DormitoryProgram;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class AdminDorApplication extends JFrame { // 관리자 생활관 "3"
	
   private JTextField textField;
   OutputStream os;
   InputStream is;
    int cnt = 0;
    int selectcnt= 0;
    
   public AdminDorApplication(Socket socket) { //LoginInfo info, Socket socket
      String title[]= {"학번","성명","생활관","지망번호","기숙기간","주민등록번호","성별","성적","거리가산점","합계"};
      DefaultTableModel model = new DefaultTableModel(title, 0)
      {
          public boolean isCellEditable(int rowIndex, int mColIndex) {
              return false;
          }
      }; 
      JTable table = new JTable(model);
      table.setCellSelectionEnabled(true);
      table.setFocusable(false);
      table.setColumnSelectionAllowed(false);
      table.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            int row = table.getSelectedRow();
            int column = table.getSelectedColumn();
            Object value = table.getValueAt(row, column);
         }
      });
      table.setBounds(12, 379, 860, 264);
      String arr[]= {"","","","","","","","","",""};
      
      getContentPane().setLayout(null);
      
      JScrollPane scrollPane = new JScrollPane(table);
      scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      scrollPane.setBounds(12, 168, 860, 387);
      getContentPane().add(scrollPane);
      scrollPane.setViewportView(table);
      
      textField = new JTextField();
      textField.setForeground(Color.WHITE);
      textField.setText("신청자 명단");
      textField.setHorizontalAlignment(SwingConstants.CENTER);
      textField.setFont(new Font("HY견고딕", Font.PLAIN, 16));
      textField.setEditable(false);
      textField.setColumns(10);
      textField.setBackground(new Color(112, 128, 144));
      textField.setBounds(12, 35, 130, 30);
      getContentPane().add(textField);
      
      
      JButton button = new JButton("선발");
      button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 
        	 if(selectcnt == 0 && cnt > 0) {
        	 try {
        	 os = socket.getOutputStream();
        	 is = socket.getInputStream();
        	 
        	 Protocol protocol = new Protocol();
        	 byte[] buf = protocol.getPacket();
        	 
        	 protocol = new Protocol(Protocol.SEND_DATA);
        	 protocol.setData("");
        	 protocol.setWhere("8");
        	 
        	 os.write(protocol.getPacket());
        	 
        	 is.read(buf);
        	 selectcnt ++;
        	 
        	 JOptionPane.showMessageDialog(null, "선발 완료되었습니다!");
        	 
        	 }
        	 catch(IOException ope) {
        		 ope.getStackTrace();
        	 	}
        	 }
        	 else {
        		 if(cnt == 0) {
        		 
        		 JOptionPane.showMessageDialog(null, "조회되지않은 상태입니다.");
        		 }
        		 else {
        			 JOptionPane.showMessageDialog(null, "이미 선발된 상태입니다.");
        		 }
        	 }
         }
      });
      
      button.setBounds(781, 135, 91, 23);
      getContentPane().add(button);
      
      JButton button_1 = new JButton("조회");
      button_1.setBounds(678, 135, 91, 23);
      getContentPane().add(button_1);
      setVisible(true);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setSize(902,676);
      button_1.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  
        	if( cnt == 0) {
              try {
            	  
            	 cnt ++;
                 
                 os = socket.getOutputStream();
                 is = socket.getInputStream();
                 
                 Protocol protocol = new Protocol();
                 byte[] buf = protocol.getPacket(); // QueryRReulst
                
                 protocol = new Protocol(Protocol.PT_REQ_INQUIRE_ALL);
     			 protocol.setWhere("4");
     			
                 os.write(protocol.getPacket());
                 
                 is.read(buf);
                 
                 protocol.setPacket(buf[0], buf);
                 
              	 if(buf[0] == Protocol.PT_RES_INQUIRE) { // 결과
              		 
              		String result = protocol.getQueryResultINQ();
			        StringTokenizer stkr = new StringTokenizer(result, "#");
              			
              		while(stkr.hasMoreTokens()) {
              			for(int i = 0; i < 10; i ++) {
              				arr[i] = stkr.nextToken();
              			}
              			model.addRow(arr);
              		}
              	
              	 }
                }
                 //리소스 반환
              catch (Exception e1) {
                 e1.printStackTrace();
              }
          }
          else {
        	  JOptionPane.showMessageDialog(null, "이미 조회가 되었습니다! ");
          }
        }
      });
      }
   }