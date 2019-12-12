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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class StudentRoomInfo extends JFrame {
   private JTextField textField;
   private JTextField textField_1;
   private JTextField textField_2;
   private JTextField textField_3;
   private JTextField textField_4;
   private JTextField textField_5;
   private JTextField stuDormName;
   private JTextField textField_7;
   private JTextField styRoomType;
   private JTextField textField_9;
   private JTextField stuRoomNum;
   private JTextField stuHowManyInRoom;
   private JTextField textField_12;
   private JTextField textField_13;
   private JTextField textField_14;

   public StudentRoomInfo(LoginInfo info, Socket socket) {
      setTitle("생활관 호실 조회");
      getContentPane().setLayout(null);

      textField = new JTextField();
      textField.setForeground(Color.WHITE);
      textField.setText("생활관 호실 조회");
      textField.setHorizontalAlignment(SwingConstants.CENTER);
      textField.setFont(new Font("HY견고딕", Font.PLAIN, 16));
      textField.setEditable(false);
      textField.setColumns(10);
      textField.setBackground(new Color(112, 128, 144));
      textField.setBounds(12, 10, 170, 30);
      getContentPane().add(textField);

      textField_1 = new JTextField();
      textField_1.setText("학번");
      textField_1.setHorizontalAlignment(SwingConstants.CENTER);
      textField_1.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_1.setEditable(false);
      textField_1.setColumns(10);
      textField_1.setBackground(Color.LIGHT_GRAY);
      textField_1.setBounds(12, 64, 80, 30);
      getContentPane().add(textField_1);

      textField_2 = new JTextField();
      textField_2.setText(info.getId());
      textField_2.setHorizontalAlignment(SwingConstants.CENTER);
      textField_2.setEditable(false);
      textField_2.setColumns(10);
      textField_2.setBounds(90, 64, 120, 30);
      getContentPane().add(textField_2);

      textField_3 = new JTextField();
      textField_3.setText("성명");
      textField_3.setHorizontalAlignment(SwingConstants.CENTER);
      textField_3.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_3.setEditable(false);
      textField_3.setColumns(10);
      textField_3.setBackground(Color.LIGHT_GRAY);
      textField_3.setBounds(226, 64, 80, 30);
      getContentPane().add(textField_3);

      textField_4 = new JTextField();
      textField_4.setText(info.getName());
      textField_4.setHorizontalAlignment(SwingConstants.CENTER);
      textField_4.setEditable(false);
      textField_4.setColumns(10);
      textField_4.setBounds(304, 64, 120, 30);
      getContentPane().add(textField_4);

      textField_5 = new JTextField();
      textField_5.setText("생활관");
      textField_5.setHorizontalAlignment(SwingConstants.CENTER);
      textField_5.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_5.setEditable(false);
      textField_5.setColumns(10);
      textField_5.setBackground(Color.LIGHT_GRAY);
      textField_5.setBounds(12, 216, 80, 30);
      getContentPane().add(textField_5);

      stuDormName = new JTextField();
      stuDormName.setHorizontalAlignment(SwingConstants.CENTER);
      stuDormName.setEditable(false);
      stuDormName.setColumns(10);
      stuDormName.setBounds(90, 216, 120, 30);
      getContentPane().add(stuDormName);

      textField_7 = new JTextField();
      textField_7.setText("호실유형");
      textField_7.setHorizontalAlignment(SwingConstants.CENTER);
      textField_7.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_7.setEditable(false);
      textField_7.setColumns(10);
      textField_7.setBackground(Color.LIGHT_GRAY);
      textField_7.setBounds(12, 271, 80, 30);
      getContentPane().add(textField_7);

      styRoomType = new JTextField();
      styRoomType.setHorizontalAlignment(SwingConstants.CENTER);
      styRoomType.setEditable(false);
      styRoomType.setColumns(10);
      styRoomType.setBounds(90, 271, 120, 30);
      getContentPane().add(styRoomType);

      textField_9 = new JTextField();
      textField_9.setText("호실");
      textField_9.setHorizontalAlignment(SwingConstants.CENTER);
      textField_9.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_9.setEditable(false);
      textField_9.setColumns(10);
      textField_9.setBackground(Color.LIGHT_GRAY);
      textField_9.setBounds(12, 325, 80, 30);
      getContentPane().add(textField_9);

      stuRoomNum = new JTextField();
      stuRoomNum.setHorizontalAlignment(SwingConstants.CENTER);
      stuRoomNum.setEditable(false);
      stuRoomNum.setColumns(10);
      stuRoomNum.setBounds(90, 325, 120, 30);
      getContentPane().add(stuRoomNum);

      stuHowManyInRoom = new JTextField();
      stuHowManyInRoom.setHorizontalAlignment(SwingConstants.CENTER);
      stuHowManyInRoom.setEditable(false);
      stuHowManyInRoom.setColumns(10);
      stuHowManyInRoom.setBounds(304, 271, 120, 30);
      getContentPane().add(stuHowManyInRoom);

      textField_12 = new JTextField();
      textField_12.setText("식사유형");
      textField_12.setHorizontalAlignment(SwingConstants.CENTER);
      textField_12.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_12.setEditable(false);
      textField_12.setColumns(10);
      textField_12.setBackground(Color.LIGHT_GRAY);
      textField_12.setBounds(226, 271, 80, 30);
      getContentPane().add(textField_12);

      textField_13 = new JTextField();
      textField_13.setText("수용성별");
      textField_13.setHorizontalAlignment(SwingConstants.CENTER);
      textField_13.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_13.setEditable(false);
      textField_13.setColumns(10);
      textField_13.setBackground(Color.LIGHT_GRAY);
      textField_13.setBounds(226, 216, 80, 30);
      getContentPane().add(textField_13);

      textField_14 = new JTextField();
      textField_14.setText(info.getSex());
      textField_14.setHorizontalAlignment(SwingConstants.CENTER);
      textField_14.setEditable(false);
      textField_14.setColumns(10);
      textField_14.setBounds(304, 216, 120, 30);
      getContentPane().add(textField_14);

      JButton inquiry = new JButton("조회");
      inquiry.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            try {
               InputStream is = socket.getInputStream();
               OutputStream os = socket.getOutputStream();
               
               Protocol protocol = new Protocol(Protocol.PT_UNDEFINED);
               protocol.getPacket();
               
               protocol = new Protocol(Protocol.PT_REQ_OPEN);
               protocol.setKindofOpen("12");
               protocol.setStudentNumOpen(info.getId());
               
               os.write(protocol.getPacket());
               
               byte[] buf = new byte[Protocol.LEN_DATA_MAX];
               is.read(buf);
               
               protocol.setPacket(buf[0], buf);
               String roomInfo = protocol.getOpenQueryResult(); // ex) 오름관2동#일반실#7일식#600
               
               StringTokenizer st = new StringTokenizer(roomInfo, "#");
               
               stuDormName.setText(st.nextToken());
               styRoomType.setText(st.nextToken());
               stuHowManyInRoom.setText(st.nextToken());
               stuRoomNum.setText(st.nextToken());
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      });
      inquiry.setBounds(323, 181, 95, 23);
      getContentPane().add(inquiry);
      setVisible(true);
      setSize(450, 450);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   }

   public static void main(String[] args) {
      // TODO Auto-generated method stub

   }
}