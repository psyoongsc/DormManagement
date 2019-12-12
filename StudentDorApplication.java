package DormitoryProgram;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class StudentDorApplication extends JFrame {
   private JTextField textField;
   private JTextField textField_2;
   private JTextField stuNum;
   private JTextField textField_1;
   private JTextField stuName;
   private JTextField textField_3;
   private JTextField stuStatus;
   private JTextField textField_7;
   private JTextField textField_8;
   private JTextField textField_9;
   private JComboBox comboBox_2;
   private JTextField textField_11;
   private JTextField textField_12;
   private JComboBox comboBox_4;
   private JComboBox comboBox_3;
   private JTextField textField_13;
   private JTextField textField_14;
   private JComboBox comboBox_5;
   private JTextField textField_15;
   private JComboBox comboBox_6;
   private JTextField textField_16;
   private JComboBox comboBox_7;
   private JTextField textField_17;
   private JComboBox comboBox_8;
   private JTextField textField_18;
   private JTextField textField_19;
   private JTextField textField_20;
   private JTextField textField_21;
   private JTextField textField_22;
   private JTextField textField_23;
   private JTextField textField_24;
   private JTextField textField_25;
   private JTextField textField_26;
   private JTextField textField_27;
   private JTextField stuPhoneNum;
   JButton btnNewButton = new JButton("신청");
   private JButton button;
   private JButton button_1;
   private String NoEat = "식사안함";
   private JTextField textField_10;
   private JTextField textField_29;

   OutputStream os = null;
   InputStream is = null;

   String period, dorm, desire, meal, period1, dorm1, desire1, meal1;
   String period2, dorm2, desire2, meal2, period3, dorm3, desire3, meal3;

   public StudentDorApplication(LoginInfo info, Socket socket) {
      setTitle("생활관창");
      getContentPane().setLayout(null);

      List<Dorminquire> dormList = new ArrayList<Dorminquire>();
      StringTokenizer st;
      String[] dormNames = null;
      
      try {
         is = socket.getInputStream();
         os = socket.getOutputStream();
         
         Protocol protocol;
         protocol = new Protocol(Protocol.PT_REQ_OPEN);
         protocol.setKindofOpen("9");
         protocol.setStudentNumOpen(info.getId());
         
         os.write(protocol.getPacket());
         os.flush();
         
         byte[] buf = new byte[Protocol.LEN_DATA_MAX];
         
         is.read(buf);
         
         protocol.setPacket(buf[0], buf);
         String openResult = protocol.getOpenQueryResult();
         System.out.println(openResult);
         st = new StringTokenizer(openResult, "#");
         
         Dorminquire dormInfo;
         
         while(st.hasMoreTokens()) {
            dormInfo = new Dorminquire();
            dormInfo.setDorm(st.nextToken());
            dormInfo.setNomealprovided(st.nextToken());
            dormInfo.setDormfee(st.nextToken());
            dormInfo.setSmeal(st.nextToken());
            dormInfo.setFmeal(st.nextToken());
            dormInfo.setOthercharge(st.nextToken());
            
            dormList.add(dormInfo);
         }
         
         dormNames = new String[dormList.size() - 1];
         int j = 0;
         for(int i=0; i<dormList.size(); i++, j++) {
            if(dormList.get(i).getDorm().equals("푸름관2동(1년)") || dormList.get(i).getDorm().equals("푸름관3동(1년)"))
               j -= 1;
            else
               dormNames[j] = dormList.get(i).getDorm();
         }
      } catch (IOException ioe) {
         ioe.printStackTrace();
      }

      textField = new JTextField();
      textField.setForeground(Color.WHITE);
      textField.setEditable(false);
      textField.setFont(new Font("HY견고딕", Font.PLAIN, 16));
      textField.setHorizontalAlignment(SwingConstants.CENTER);
      textField.setBackground(new Color(112, 128, 144));
      textField.setText("학생정보");
      textField.setBounds(12, 10, 117, 29);
      getContentPane().add(textField);
      textField.setColumns(10);

      textField_2 = new JTextField();
      textField_2.setEditable(false);
      textField_2.setBackground(new Color(192, 192, 192));
      textField_2.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_2.setHorizontalAlignment(SwingConstants.CENTER);
      textField_2.setText("학번");
      textField_2.setBounds(129, 10, 86, 29);
      getContentPane().add(textField_2);
      textField_2.setColumns(10);

      stuNum = new JTextField();
      stuNum.setText(info.getId());
      stuNum.setBackground(new Color(255, 255, 255));
      stuNum.setEditable(false);
      stuNum.setHorizontalAlignment(SwingConstants.CENTER);
      stuNum.setColumns(10);
      stuNum.setBounds(214, 10, 86, 29);
      getContentPane().add(stuNum);

      textField_1 = new JTextField();
      textField_1.setEditable(false);
      textField_1.setBackground(new Color(192, 192, 192));
      textField_1.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_1.setHorizontalAlignment(SwingConstants.CENTER);
      textField_1.setText("성명");
      textField_1.setColumns(10);
      textField_1.setBounds(299, 10, 86, 29);
      getContentPane().add(textField_1);

      stuName = new JTextField();
      stuName.setText(info.getName());
      stuName.setBackground(new Color(255, 255, 255));
      stuName.setEditable(false);
      stuName.setHorizontalAlignment(SwingConstants.CENTER);
      stuName.setColumns(10);
      stuName.setBounds(382, 10, 86, 29);
      getContentPane().add(stuName);

      textField_3 = new JTextField();
      textField_3.setEditable(false);
      textField_3.setBackground(new Color(192, 192, 192));
      textField_3.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_3.setHorizontalAlignment(SwingConstants.CENTER);
      textField_3.setText("학적상태");
      textField_3.setColumns(10);
      textField_3.setBounds(467, 10, 86, 29);
      getContentPane().add(textField_3);

      stuStatus = new JTextField();
      stuStatus.setBackground(new Color(255, 255, 255));
      stuStatus.setEditable(false);
      stuStatus.setHorizontalAlignment(SwingConstants.CENTER);
      stuStatus.setText(info.getStatus());
      stuStatus.setColumns(10);
      stuStatus.setBounds(550, 10, 86, 29);
      getContentPane().add(stuStatus);

      textField_7 = new JTextField();
      textField_7.setForeground(Color.WHITE);
      textField_7.setEditable(false);
      textField_7.setBackground(new Color(112, 128, 144));
      textField_7.setFont(new Font("HY견고딕", Font.PLAIN, 16));
      textField_7.setHorizontalAlignment(SwingConstants.CENTER);
      textField_7.setText("1년기숙");
      textField_7.setBounds(12, 97, 117, 29);
      getContentPane().add(textField_7);
      textField_7.setColumns(10);

      textField_8 = new JTextField();
      textField_8.setEditable(false);
      textField_8.setBackground(new Color(192, 192, 192));
      textField_8.setText("생활관");
      textField_8.setHorizontalAlignment(SwingConstants.CENTER);
      textField_8.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_8.setColumns(10);
      textField_8.setBounds(129, 97, 86, 29);
      getContentPane().add(textField_8);

      JComboBox comboBox_1 = new JComboBox();
      comboBox_1.setBackground(new Color(255, 255, 255));
      if(info.getSex().equals("남"))
         comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "선택안함", "푸름관2동(1년)" }));
      else
         comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "선택안함", "푸름관3동(1년)" }));
      comboBox_1.setBounds(214, 97, 86, 29);
      comboBox_1.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {
            comboBox_2.setSelectedItem("선택안함");
            btnNewButton.setEnabled(false);
            String cb = comboBox_1.getSelectedItem().toString();
            if (cb.equals("선택안함")) {
               comboBox_2.setEnabled(false);
            } else {
               comboBox_2.setEnabled(true);
            }
         }
      });
      getContentPane().add(comboBox_1);

      textField_9 = new JTextField();
      textField_9.setEditable(false);
      textField_9.setBackground(new Color(192, 192, 192));
      textField_9.setText("식사구분");
      textField_9.setHorizontalAlignment(SwingConstants.CENTER);
      textField_9.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_9.setColumns(10);
      textField_9.setBounds(299, 97, 86, 29);
      getContentPane().add(textField_9);
      
      textField_21 = new JTextField();
      textField_21.setText("0");
      textField_21.setHorizontalAlignment(SwingConstants.CENTER);
      textField_21.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_21.setColumns(10);
      textField_21.setBackground(new Color(255, 255, 255));
      textField_21.setBounds(550, 231, 117, 29);
      getContentPane().add(textField_21);

      textField_22 = new JTextField();
      textField_22.setText("0");
      textField_22.setHorizontalAlignment(SwingConstants.CENTER);
      textField_22.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_22.setColumns(10);
      textField_22.setBackground(new Color(255, 255, 255));
      textField_22.setBounds(550, 280, 117, 29);
      getContentPane().add(textField_22);

      textField_23 = new JTextField();
      textField_23.setText("0");
      textField_23.setHorizontalAlignment(SwingConstants.CENTER);
      textField_23.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_23.setColumns(10);
      textField_23.setBackground(new Color(255, 255, 255));
      textField_23.setBounds(550, 326, 117, 29);
      getContentPane().add(textField_23);
      
      textField_29 = new JTextField();
      textField_29.setText("0");
      textField_29.setHorizontalAlignment(SwingConstants.CENTER);
      textField_29.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_29.setColumns(10);
      textField_29.setBackground(Color.WHITE);
      textField_29.setBounds(550, 97, 117, 29);
      getContentPane().add(textField_29);
      setVisible(true);
      setSize(750, 450);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      comboBox_2 = new JComboBox();
      comboBox_2.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {
            String cb = comboBox_2.getSelectedItem().toString();
            if (cb.equals("선택안함")) {
               btnNewButton.setEnabled(false);
               textField_29.setText("0");
            }
            else {
               btnNewButton.setEnabled(true);
               int dormfee=0, mealfee=0, otherfee=0, price=0;
               for(int i=0; i<dormList.size(); i++) {
                  if(dormList.get(i).getDorm().equals(comboBox_1.getSelectedItem().toString())) {
                     dormfee = Integer.parseInt(dormList.get(i).getDormfee());
                     if(cb.equals("5일식"))
                        mealfee = Integer.parseInt(dormList.get(i).getFmeal());
                     else if(cb.equals("7일식"))
                        mealfee = Integer.parseInt(dormList.get(i).getSmeal());
                     else
                        mealfee = 0;
                     otherfee = Integer.parseInt(dormList.get(i).getOthercharge());
                     break;
                  }
               }
               price = dormfee + mealfee + otherfee;
               textField_29.setText(price + "");
            }
         }
      });
      comboBox_2.addItem("선택안함");
      comboBox_2.addItem("5일식");
      comboBox_2.addItem("7일식");
      comboBox_2.addItem("식사안함");
      comboBox_2.setBackground(Color.WHITE);
      comboBox_2.setBounds(382, 97, 86, 29);
      comboBox_2.setEnabled(false);
      getContentPane().add(comboBox_2);

      textField_11 = new JTextField();
      textField_11.setForeground(Color.WHITE);
      textField_11.setEditable(false);
      textField_11.setText("한학기 기숙");
      textField_11.setHorizontalAlignment(SwingConstants.CENTER);
      textField_11.setFont(new Font("HY견고딕", Font.PLAIN, 16));
      textField_11.setColumns(10);
      textField_11.setBackground(new Color(112, 128, 144));
      textField_11.setBounds(12, 178, 117, 29);
      getContentPane().add(textField_11);

      textField_12 = new JTextField();
      textField_12.setEditable(false);
      textField_12.setText("생활관");
      textField_12.setHorizontalAlignment(SwingConstants.CENTER);
      textField_12.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_12.setColumns(10);
      textField_12.setBackground(new Color(192, 192, 192));
      textField_12.setBounds(129, 231, 86, 29);
      getContentPane().add(textField_12);

      comboBox_4 = new JComboBox();
      comboBox_4.addItem("선택안함");
      comboBox_4.addItem("5일식");
      comboBox_4.addItem("7일식");
      comboBox_4.addItem("식사안함");
      comboBox_4.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {
            String cb = comboBox_4.getSelectedItem().toString();
            if (cb.equals("선택안함")) {
               btnNewButton.setEnabled(false);
               comboBox_5.setEnabled(false);
               comboBox_6.setEnabled(false);
               comboBox_7.setEnabled(false);
               comboBox_8.setEnabled(false);
               comboBox_5.setSelectedItem("선택안함");
               textField_21.setText("0");
            } else {
               btnNewButton.setEnabled(true);
               comboBox_5.setEnabled(true);
               int dormfee=0, mealfee=0, otherfee=0, price=0;
               for(int i=0; i<dormList.size(); i++) {
                  if(dormList.get(i).getDorm().equals(comboBox_3.getSelectedItem().toString())) {
                     dormfee = Integer.parseInt(dormList.get(i).getDormfee());
                     if(cb.equals("5일식"))
                        mealfee = Integer.parseInt(dormList.get(i).getFmeal());
                     else if(cb.equals("7일식"))
                        mealfee = Integer.parseInt(dormList.get(i).getSmeal());
                     else
                        mealfee = 0;
                     otherfee = Integer.parseInt(dormList.get(i).getOthercharge());
                     break;
                  }
               }
               price = dormfee + mealfee + otherfee;
               textField_21.setText(price + "");
            }
         }
      });

      comboBox_4.setBackground(Color.WHITE);
      comboBox_4.setBounds(382, 231, 86, 29);
      comboBox_4.setEnabled(false);
      getContentPane().add(comboBox_4);

      comboBox_3 = new JComboBox();
      comboBox_3.addItem("선택안함");
      for(int i=0; i<dormNames.length; i++) {
         comboBox_3.addItem(dormNames[i]);
      }
      
      comboBox_3.setBackground(Color.WHITE);
      comboBox_3.setBounds(214, 231, 86, 29);
      comboBox_3.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {
            btnNewButton.setEnabled(false);
            comboBox_4.setSelectedItem("선택안함");
            String cb = comboBox_3.getSelectedItem().toString();
            if (cb.equals("선택안함")) {
               comboBox_4.setEnabled(false);
               comboBox_5.setEnabled(false);
               comboBox_6.setEnabled(false);
               comboBox_7.setEnabled(false);
               comboBox_8.setEnabled(false);
               comboBox_1.setSelectedItem("선택안함");
            } else if (cb.equals("오름관1동")) {
               comboBox_4.setEnabled(true);
               comboBox_4.removeItem("식사안함");
            } else if (cb.equals("오름관2,3동")) {
               comboBox_4.setEnabled(true);
               System.out.println("안녕");
               comboBox_4.removeItem("식사안함");
            } else {
               comboBox_4.setEnabled(true);
               comboBox_4.removeItem("식사안함");
               comboBox_4.addItem("식사안함");
            }
         }
      });
      getContentPane().add(comboBox_3);

      textField_13 = new JTextField();
      textField_13.setEditable(false);
      textField_13.setText("식사구분");
      textField_13.setHorizontalAlignment(SwingConstants.CENTER);
      textField_13.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_13.setColumns(10);
      textField_13.setBackground(new Color(192, 192, 192));
      textField_13.setBounds(299, 231, 86, 29);
      getContentPane().add(textField_13);

      textField_14 = new JTextField();
      textField_14.setEditable(false);
      textField_14.setText("생활관");
      textField_14.setHorizontalAlignment(SwingConstants.CENTER);
      textField_14.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_14.setColumns(10);
      textField_14.setBackground(new Color(192, 192, 192));
      textField_14.setBounds(129, 280, 86, 29);
      getContentPane().add(textField_14);
      
      comboBox_6 = new JComboBox();
      comboBox_6.addItem("선택안함");
      comboBox_6.addItem("5일식");
      comboBox_6.addItem("7일식");
      comboBox_6.addItem("식사안함");
      comboBox_6.setBackground(Color.WHITE);
      comboBox_6.setBounds(382, 280, 86, 29);
      comboBox_6.setEnabled(false);
      comboBox_6.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {
            String cb = comboBox_6.getSelectedItem().toString();
            if (cb.equals("선택안함")) {
               comboBox_7.setEnabled(false);
               comboBox_8.setEnabled(false);
               comboBox_7.setSelectedItem("선택안함");
               textField_22.setText("0");
               btnNewButton.setEnabled(false);
            } else {
               comboBox_7.setEnabled(true);
               btnNewButton.setEnabled(true);
               
               int dormfee=0, mealfee=0, otherfee=0, price=0;
               for(int i=0; i<dormList.size(); i++) {
                  if(dormList.get(i).getDorm().equals(comboBox_5.getSelectedItem().toString())) {
                     dormfee = Integer.parseInt(dormList.get(i).getDormfee());
                     if(cb.equals("5일식"))
                        mealfee = Integer.parseInt(dormList.get(i).getFmeal());
                     else if(cb.equals("7일식"))
                        mealfee = Integer.parseInt(dormList.get(i).getSmeal());
                     else
                        mealfee = 0;
                     otherfee = Integer.parseInt(dormList.get(i).getOthercharge());
                     break;
                  }
               }
               price = dormfee + mealfee + otherfee;
               textField_22.setText(price + "");
            }
         }
      });
      getContentPane().add(comboBox_6);

      comboBox_5 = new JComboBox();
      comboBox_5.addItem("선택안함");
      for(int i=0; i<dormNames.length; i++) {
         comboBox_5.addItem(dormNames[i]);
      }
      comboBox_5.setBackground(Color.WHITE);
      comboBox_5.setBounds(214, 280, 86, 29);
      comboBox_5.setEnabled(false);
      comboBox_5.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {
            comboBox_6.setSelectedItem("선택안함");
            String cb = comboBox_5.getSelectedItem().toString();
            if (cb.equals("선택안함")) {
               comboBox_6.setEnabled(false);
               comboBox_7.setEnabled(false);
               comboBox_8.setEnabled(false);
               btnNewButton.setEnabled(true);
            } else if (cb.equals("오름관1동")) {
               if (cb == comboBox_3.getSelectedItem().toString()) {
                  comboBox_5.setSelectedItem("선택안함");
                  JOptionPane.showMessageDialog(null, "중복선택은 불가능합니다.");
               } else {
                  comboBox_6.setEnabled(true);
                  comboBox_6.removeItem(NoEat);
                  btnNewButton.setEnabled(false);
               }
            } else if (cb.equals("오름관2,3동")) {
               if (cb == comboBox_3.getSelectedItem().toString()) {
                  comboBox_5.setSelectedItem("선택안함");
                  JOptionPane.showMessageDialog(null, "중복선택은 불가능합니다.");
               } else {
                  comboBox_6.setEnabled(true);
                  comboBox_6.removeItem(NoEat);
                  btnNewButton.setEnabled(false);
               }
            } else {
               if (cb.equals(comboBox_3.getSelectedItem().toString())) {
                  comboBox_5.setSelectedItem("선택안함");
                  JOptionPane.showMessageDialog(null, "중복선택은 불가능합니다.");
               } else {
                  comboBox_6.setEnabled(true);
                  comboBox_6.removeItem(NoEat);
                  comboBox_6.addItem(NoEat);
                  btnNewButton.setEnabled(false);
               }
            }
         }
      });
      getContentPane().add(comboBox_5);

      textField_15 = new JTextField();
      textField_15.setEditable(false);
      textField_15.setText("식사구분");
      textField_15.setHorizontalAlignment(SwingConstants.CENTER);
      textField_15.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_15.setColumns(10);
      textField_15.setBackground(new Color(192, 192, 192));
      textField_15.setBounds(299, 280, 86, 29);
      getContentPane().add(textField_15);


      textField_16 = new JTextField();
      textField_16.setEditable(false);
      textField_16.setText("생활관");
      textField_16.setHorizontalAlignment(SwingConstants.CENTER);
      textField_16.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_16.setColumns(10);
      textField_16.setBackground(new Color(192, 192, 192));
      textField_16.setBounds(129, 326, 86, 29);
      getContentPane().add(textField_16);

      comboBox_7 = new JComboBox();
      comboBox_7.addItem("선택안함");
      for(int i=0; i<dormNames.length; i++) {
         comboBox_7.addItem(dormNames[i]);
      }
      comboBox_7.setBackground(Color.WHITE);
      comboBox_7.setBounds(214, 326, 86, 29);
      comboBox_7.setEnabled(false);
      comboBox_7.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {
            comboBox_8.setSelectedItem("선택안함");
            String cb = comboBox_7.getSelectedItem().toString();
            if (cb.equals("선택안함")) {
               comboBox_8.setEnabled(false);
               btnNewButton.setEnabled(true);
            } else if (cb.equals("오름관1동")) {
               if (cb.equals(comboBox_3.getSelectedItem().toString())
                     || cb.equals(comboBox_5.getSelectedItem().toString())) {
                  comboBox_7.setSelectedItem("선택안함");
                  JOptionPane.showMessageDialog(null, "중복선택은 불가능합니다.");
               } else {
                  comboBox_8.setEnabled(true);
                  comboBox_8.removeItem(NoEat);
                  btnNewButton.setEnabled(false);
               }
            } else if (cb.equals("오름관2,3동")) {
               if (cb.equals(comboBox_3.getSelectedItem().toString())
                     || cb.equals(comboBox_5.getSelectedItem().toString())) {
                  comboBox_7.setSelectedItem("선택안함");
                  JOptionPane.showMessageDialog(null, "중복선택은 불가능합니다.");
               } else {
                  comboBox_8.setEnabled(true);
                  comboBox_8.removeItem(NoEat);
                  btnNewButton.setEnabled(false);
               }
            } else {
               if (cb.equals(comboBox_3.getSelectedItem().toString())
                     || cb.equals(comboBox_5.getSelectedItem().toString())) {
                  comboBox_7.setSelectedItem("선택안함");
                  JOptionPane.showMessageDialog(null, "중복선택은 불가능합니다.");
               } else {
                  comboBox_8.setEnabled(true);
                  comboBox_8.removeItem(NoEat);
                  comboBox_8.addItem(NoEat);
                  btnNewButton.setEnabled(false);
               }
            }
         }
      });
      getContentPane().add(comboBox_7);

      textField_17 = new JTextField();
      textField_17.setEditable(false);
      textField_17.setText("식사구분");
      textField_17.setHorizontalAlignment(SwingConstants.CENTER);
      textField_17.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_17.setColumns(10);
      textField_17.setBackground(new Color(192, 192, 192));
      textField_17.setBounds(299, 326, 86, 29);
      getContentPane().add(textField_17);

      comboBox_8 = new JComboBox();
      comboBox_8.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {
            String str = comboBox_8.getSelectedItem().toString();
            if (str.equals("선택안함")) {
               btnNewButton.setEnabled(false);
               textField_23.setText("0");
            }
            else
               btnNewButton.setEnabled(true);
            int dormfee=0, mealfee=0, otherfee=0, price=0;
            for(int i=0; i<dormList.size(); i++) {
               if(dormList.get(i).getDorm().equals(comboBox_7.getSelectedItem().toString())) {
                  dormfee = Integer.parseInt(dormList.get(i).getDormfee());
                  if(str.equals("5일식"))
                     mealfee = Integer.parseInt(dormList.get(i).getFmeal());
                  else if(str.equals("7일식"))
                     mealfee = Integer.parseInt(dormList.get(i).getSmeal());
                  else
                     mealfee = 0;
                  otherfee = Integer.parseInt(dormList.get(i).getOthercharge());
                  break;
               }
            }
            price = dormfee + mealfee + otherfee;
            textField_23.setText(price + "");
         }
      });
      comboBox_8.addItem("선택안함");
      comboBox_8.addItem("5일식");
      comboBox_8.addItem("7일식");
      comboBox_8.addItem("식사안함");
      comboBox_8.setBackground(Color.WHITE);
      comboBox_8.setBounds(382, 326, 86, 29);
      comboBox_8.setEnabled(false);
      getContentPane().add(comboBox_8);

      textField_18 = new JTextField();
      textField_18.setForeground(Color.WHITE);
      textField_18.setEditable(false);
      textField_18.setText("1지망");
      textField_18.setHorizontalAlignment(SwingConstants.CENTER);
      textField_18.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_18.setColumns(10);
      textField_18.setBackground(new Color(128, 128, 128));
      textField_18.setBounds(12, 231, 117, 29);
      getContentPane().add(textField_18);

      textField_19 = new JTextField();
      textField_19.setForeground(Color.WHITE);
      textField_19.setEditable(false);
      textField_19.setText("2지망");
      textField_19.setHorizontalAlignment(SwingConstants.CENTER);
      textField_19.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_19.setColumns(10);
      textField_19.setBackground(new Color(128, 128, 128));
      textField_19.setBounds(12, 280, 117, 29);
      getContentPane().add(textField_19);

      textField_20 = new JTextField();
      textField_20.setForeground(Color.WHITE);
      textField_20.setEditable(false);
      textField_20.setText("3지망");
      textField_20.setHorizontalAlignment(SwingConstants.CENTER);
      textField_20.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_20.setColumns(10);
      textField_20.setBackground(new Color(128, 128, 128));
      textField_20.setBounds(12, 326, 117, 29);
      getContentPane().add(textField_20);

      btnNewButton.setEnabled(false);
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            try {

               is = socket.getInputStream();
               os = socket.getOutputStream();

               Protocol protocol = new Protocol();
               byte[] buf = protocol.getPacket();

               String period = "1년";
               String dorm = comboBox_1.getSelectedItem().toString();
               String desire = "1";
               String meal = comboBox_2.getSelectedItem().toString();
               if (meal.equals("선택안함"))
                  dorm = "선택안함";

               String period1 = "1학기";
               String dorm1 = comboBox_3.getSelectedItem().toString();
               String desire1 = "1";
               String meal1 = comboBox_4.getSelectedItem().toString();
               if (meal1.equals("선택안함"))
                  dorm1 = "선택안함";

               String period2 = "1학기";
               String dorm2 = comboBox_5.getSelectedItem().toString();
               String desire2 = "2";
               String meal2 = comboBox_6.getSelectedItem().toString();
               if (meal2.equals("선택안함"))
                  dorm2 = "선택안함";

               String period3 = "1학기";
               String dorm3 = comboBox_7.getSelectedItem().toString();
               String desire3 = "3";
               String meal3 = comboBox_8.getSelectedItem().toString();
               if (meal3.equals("선택안함"))
                  dorm3 = "선택안함";

               protocol = new Protocol(Protocol.PT_REQ_APPLICATION);

               protocol.setAId(info.getId());
               protocol.setPeriod(period);
               protocol.setDorm(dorm);
               protocol.setDesire(desire);
               protocol.setMeal(meal); 
               protocol.setPeriod1(period1);
               protocol.setDorm1(dorm1);
               protocol.setDesire1(desire1);
               protocol.setMeal1(meal1);
               protocol.setPeriod2(period2);
               protocol.setDorm2(dorm2);
               protocol.setDesire2(desire2);
               protocol.setMeal2(meal2);
               protocol.setPeriod3(period3);
               protocol.setDorm3(dorm3);
               protocol.setDesire3(desire3);
               protocol.setMeal3(meal3);

               System.out.println("생활관 정보 전송");
               os.write(protocol.getPacket());
               os.flush();

               program: while (true) {
            	   buf = new byte[Protocol.LEN_DATA_MAX];
                  is.read(buf);
                  int packetType = buf[0];
                  protocol.setPacket(packetType, buf);

                  if (packetType == Protocol.PT_EXIT) {
                     System.out.println("클라이언트 종료");
                     break;
                  }

                  switch (packetType) {
                  case Protocol.PT_RES_APPLICATION:
                     System.out.println("서버가 생활관 신청 결과 전송.");
                     String result = protocol.getResApplication();
                     if (result.equals("1")) {
                        System.out.println("신청 성공");
                     }

                     break program;
                  }
               }

               JOptionPane.showMessageDialog(null, "신청이 완료되었습니다.");
               

            } catch (Exception e1) {
               JOptionPane.showMessageDialog(null, "오류가 발생했습니다 처음부터 다시 신청하십시오.");
               System.out.println("Error" + e1);
            }
         }
      });

      btnNewButton.setBackground(UIManager.getColor("Button.highlight"));
      btnNewButton.setFont(new Font("굴림", Font.BOLD, 13));
      btnNewButton.setBounds(574, 150, 93, 48);
      getContentPane().add(btnNewButton);

      textField_24 = new JTextField();
      textField_24.setEditable(false);
      textField_24.setText("입사비용");
      textField_24.setHorizontalAlignment(SwingConstants.CENTER);
      textField_24.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_24.setColumns(10);
      textField_24.setBackground(new Color(192, 192, 192));
      textField_24.setBounds(467, 231, 86, 29);
      getContentPane().add(textField_24);

      textField_25 = new JTextField();
      textField_25.setEditable(false);
      textField_25.setText("입사비용");
      textField_25.setHorizontalAlignment(SwingConstants.CENTER);
      textField_25.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_25.setColumns(10);
      textField_25.setBackground(new Color(192, 192, 192));
      textField_25.setBounds(467, 280, 86, 29);
      getContentPane().add(textField_25);

      textField_26 = new JTextField();
      textField_26.setEditable(false);
      textField_26.setText("입사비용");
      textField_26.setHorizontalAlignment(SwingConstants.CENTER);
      textField_26.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_26.setColumns(10);
      textField_26.setBackground(new Color(192, 192, 192));
      textField_26.setBounds(467, 326, 86, 29);
      getContentPane().add(textField_26);

      textField_27 = new JTextField();
      textField_27.setText("휴대전화번호");
      textField_27.setHorizontalAlignment(SwingConstants.CENTER);
      textField_27.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_27.setEditable(false);
      textField_27.setColumns(10);
      textField_27.setBackground(Color.LIGHT_GRAY);
      textField_27.setBounds(129, 38, 86, 29);
      getContentPane().add(textField_27);

      stuPhoneNum = new JTextField();
      stuPhoneNum.setText(info.getPhoneNumber());
      stuPhoneNum.setEnabled(false);
      stuPhoneNum.setHorizontalAlignment(SwingConstants.CENTER);
      stuPhoneNum.setEditable(false);
      stuPhoneNum.setColumns(10);
      stuPhoneNum.setBackground(Color.WHITE);
      stuPhoneNum.setBounds(214, 38, 86, 29);
      getContentPane().add(stuPhoneNum);

      textField_10 = new JTextField();
      textField_10.setText("입사비용");
      textField_10.setHorizontalAlignment(SwingConstants.CENTER);
      textField_10.setFont(new Font("HY견고딕", Font.PLAIN, 12));
      textField_10.setEditable(false);
      textField_10.setColumns(10);
      textField_10.setBackground(Color.LIGHT_GRAY);
      textField_10.setBounds(467, 97, 86, 29);
      getContentPane().add(textField_10);

   }
}