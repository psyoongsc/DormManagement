import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DorApplication extends JFrame {
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JComboBox comboBox_1;
	private JTextField textField_10;
	private JComboBox comboBox_2;
	private JTextField textField_11;
	private JTextField textField_12;
	private JComboBox comboBox_3;
	private JComboBox comboBox_4;
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
	private JTextField textField_28;
	private JButton button;
	private JButton button_1;
	private String NoEat = "식사안함";
	public DorApplication() {
		setTitle("생활관창");
		getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBackground(new Color(112, 128, 144));
		textField.setText("20150154");
		textField.setBounds(12, 10, 117, 29);
		getContentPane().add(textField);
		textField.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBackground(new Color(192, 192, 192));
		textField_2.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setText("성명");
		textField_2.setBounds(129, 10, 86, 29);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBackground(new Color(255, 255, 255));
		textField_4.setEditable(false);
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setText("김민수");
		textField_4.setColumns(10);
		textField_4.setBounds(214, 10, 86, 29);
		getContentPane().add(textField_4);

		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setBackground(new Color(192, 192, 192));
		textField_6.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setText("학년");
		textField_6.setColumns(10);
		textField_6.setBounds(299, 10, 86, 29);
		getContentPane().add(textField_6);

		textField_1 = new JTextField();
		textField_1.setBackground(new Color(255, 255, 255));
		textField_1.setEditable(false);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setText("2");
		textField_1.setColumns(10);
		textField_1.setBounds(382, 10, 86, 29);
		getContentPane().add(textField_1);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBackground(new Color(192, 192, 192));
		textField_3.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setText("학적상태");
		textField_3.setColumns(10);
		textField_3.setBounds(467, 10, 86, 29);
		getContentPane().add(textField_3);

		textField_5 = new JTextField();
		textField_5.setBackground(new Color(255, 255, 255));
		textField_5.setEditable(false);
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setText("재학");
		textField_5.setColumns(10);
		textField_5.setBounds(550, 10, 86, 29);
		getContentPane().add(textField_5);

		textField_7 = new JTextField();
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

		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"선택안함", "푸름2동", "푸름3동"}));
		comboBox.setBounds(214, 97, 86, 29);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String cb = comboBox.getSelectedItem().toString();
				if(cb == "선택안함") {
					comboBox_1.setEnabled(false);
					comboBox_2.setEnabled(false);
				}
				else
				{
					comboBox_1.setEnabled(true);
				
				}
			}
		});
		getContentPane().add(comboBox);

		textField_9 = new JTextField();
		textField_9.setEditable(false);
		textField_9.setBackground(new Color(192, 192, 192));
		textField_9.setText("식사구분");
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField_9.setColumns(10);
		textField_9.setBounds(299, 97, 86, 29);
		getContentPane().add(textField_9);

		comboBox_1 = new JComboBox();
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String cb = comboBox_1.getSelectedItem().toString();
				if(cb == "선택안함")
					comboBox_2.setEnabled(false);
				else
					comboBox_2.setEnabled(true);
			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"선택안함", "5일식", "7일식", "식사안함"}));
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setBounds(382, 97, 86, 29);
		comboBox_1.setEnabled(false);
		getContentPane().add(comboBox_1);

		textField_10 = new JTextField();
		textField_10.setEditable(false);
		textField_10.setText("식사(방학)");
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField_10.setColumns(10);
		textField_10.setBackground(new Color(192, 192, 192));
		textField_10.setBounds(467, 97, 86, 29);
		getContentPane().add(textField_10);

		comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"선택안함", "5일식", "7일식", "식사안함"}));
		comboBox_2.setBackground(Color.WHITE);
		comboBox_2.setBounds(550, 97, 86, 29);
		comboBox_2.setEnabled(false);
		getContentPane().add(comboBox_2);

		textField_11 = new JTextField();
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

		comboBox_3 = new JComboBox();
		comboBox_3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String cb = comboBox_3.getSelectedItem().toString();
				if(cb == "선택안함") {
					comboBox_5.setEnabled(false);
					comboBox_6.setEnabled(false);
					comboBox_7.setEnabled(false);
					comboBox_8.setEnabled(false);
					
				}
				else if ( cb == "5일식")
				{
					comboBox_5.setEnabled(true);
				}
				else if( cb == "7일식")
				{
					comboBox_5.setEnabled(true);
				}
				else if ( cb == "식사안함")
				{
					comboBox_5.setEnabled(true);
				}
				else
				{
					comboBox_5.setEnabled(true);
				}
			}
		});
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"선택안함", "5일식", "7일식", NoEat}));
		comboBox_3.setBackground(Color.WHITE);
		comboBox_3.setBounds(382, 231, 86, 29);
		comboBox_3.setEnabled(false);
		getContentPane().add(comboBox_3);

		comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"선택안함", "푸름1동", "푸름2동", "푸름3동", "푸름4동", "오름1동", "오름2동", "오름3동", "신평관"}));
		comboBox_4.setBackground(Color.WHITE);
		comboBox_4.setBounds(214, 231, 86, 29);
		comboBox_4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String cb = comboBox_4.getSelectedItem().toString();
				if(cb == "선택안함") {
					comboBox_3.setEnabled(false);
					comboBox_5.setEnabled(false);
					comboBox_6.setEnabled(false);
					comboBox_7.setEnabled(false);
					comboBox_8.setEnabled(false);
				}
				else if ( cb == "오름1동")
				{
					comboBox_3.setEnabled(true);
					comboBox_3.removeItem(NoEat);
				}
				else if( cb == "오름2동")
				{
					comboBox_3.setEnabled(true);
					comboBox_3.removeItem(NoEat);
				}
				else if ( cb == "오름3동")
				{
					comboBox_3.setEnabled(true);
					comboBox_3.removeItem(NoEat);
				}
				else
				{
					comboBox_3.setEnabled(true);
					comboBox_3.removeItem(NoEat);
					comboBox_3.addItem(NoEat);
				}
			}
		});
		getContentPane().add(comboBox_4);

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

		comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"선택안함", "푸름1동", "푸름2동", "푸름3동", "푸름4동", "오름1동", "오름2동", "오름3동", "신평관"}));
		comboBox_5.setBackground(Color.WHITE);
		comboBox_5.setBounds(214, 280, 86, 29);
		comboBox_5.setEnabled(false);
		comboBox_5.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String cb = comboBox_5.getSelectedItem().toString();
				if(cb == "선택안함") {
					comboBox_6.setEnabled(false);
					comboBox_7.setEnabled(false);
					comboBox_8.setEnabled(false);
				}
				else if ( cb == "오름1동")
				{
					comboBox_6.setEnabled(true);
					comboBox_6.removeItem(NoEat);
				}
				else if( cb == "오름2동")
				{
					comboBox_6.setEnabled(true);
					comboBox_6.removeItem(NoEat);
				}
				else if ( cb == "오름3동")
				{
					comboBox_6.setEnabled(true);
					comboBox_6.removeItem(NoEat);
				}
				else
				{
					comboBox_6.setEnabled(true);
					comboBox_6.removeItem(NoEat);
					comboBox_6.addItem(NoEat);
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

		comboBox_6 = new JComboBox();
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"선택안함", "5일식", "7일식", NoEat}));
		comboBox_6.setBackground(Color.WHITE);
		comboBox_6.setBounds(382, 280, 86, 29);
		comboBox_6.setEnabled(false);
		comboBox_6.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String cb = comboBox_6.getSelectedItem().toString();
				if(cb == "선택안함") {
					comboBox_7.setEnabled(false);
					comboBox_8.setEnabled(false);
				}
				else if ( cb == "5일식")
				{
					comboBox_7.setEnabled(true);
					comboBox_7.removeItem(NoEat);
				}
				else if( cb == "7일식")
				{
					comboBox_7.setEnabled(true);
					comboBox_7.removeItem(NoEat);
				}
				else if ( cb == "식사안함")
				{
					comboBox_7.setEnabled(true);
					comboBox_7.removeItem(NoEat);
				}
				else
				{
					comboBox_7.setEnabled(true);
					comboBox_7.removeItem(NoEat);
					comboBox_7.addItem(NoEat);
				}
			}
		});
		getContentPane().add(comboBox_6);

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
		comboBox_7.setModel(new DefaultComboBoxModel(new String[] {"선택안함", "푸름1동", "푸름2동", "푸름3동", "푸름4동", "오름1동", "오름2동", "오름3동", "신평관"}));
		comboBox_7.setBackground(Color.WHITE);
		comboBox_7.setBounds(214, 326, 86, 29);
		comboBox_7.setEnabled(false);
		comboBox_7.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String cb = comboBox_7.getSelectedItem().toString();
				if(cb == "선택안함") {
					comboBox_8.setEnabled(false);
				}
				else if ( cb == "오름1동")
				{
					comboBox_8.setEnabled(true);
					comboBox_8.removeItem(NoEat);
				}
				else if( cb == "오름2동")
				{
					comboBox_8.setEnabled(true);
					comboBox_8.removeItem(NoEat);
				}
				else if ( cb == "오름3동")
				{
					comboBox_8.setEnabled(true);
					comboBox_8.removeItem(NoEat);
				}
				else
				{
					comboBox_8.setEnabled(true);
					comboBox_8.removeItem(NoEat);
					comboBox_8.addItem(NoEat);
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
		comboBox_8.setModel(new DefaultComboBoxModel(new String[] {"선택안함", "5일식", "7일식", NoEat}));
		comboBox_8.setBackground(Color.WHITE);
		comboBox_8.setBounds(382, 326, 86, 29);
		comboBox_8.setEnabled(false);
		getContentPane().add(comboBox_8);

		textField_18 = new JTextField();
		textField_18.setEditable(false);
		textField_18.setText("1지망");
		textField_18.setHorizontalAlignment(SwingConstants.CENTER);
		textField_18.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField_18.setColumns(10);
		textField_18.setBackground(new Color(128, 128, 128));
		textField_18.setBounds(12, 231, 117, 29);
		getContentPane().add(textField_18);

		textField_19 = new JTextField();
		textField_19.setEditable(false);
		textField_19.setText("2지망");
		textField_19.setHorizontalAlignment(SwingConstants.CENTER);
		textField_19.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField_19.setColumns(10);
		textField_19.setBackground(new Color(128, 128, 128));
		textField_19.setBounds(12, 280, 117, 29);
		getContentPane().add(textField_19);

		textField_20 = new JTextField();
		textField_20.setEditable(false);
		textField_20.setText("3지망");
		textField_20.setHorizontalAlignment(SwingConstants.CENTER);
		textField_20.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField_20.setColumns(10);
		textField_20.setBackground(new Color(128, 128, 128));
		textField_20.setBounds(12, 326, 117, 29);
		getContentPane().add(textField_20);

		JButton btnNewButton = new JButton("신청");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int stuNum = 20150154;
				String l1y = "1년";
				String l1h = "1학기";
				String cc1 = "1지망";
				String cc2 = "2지망";
				String cc3 = "3지망";
				String strc = comboBox.getSelectedItem().toString();
				String strc1 = comboBox_1.getSelectedItem().toString();
				String strc2 = comboBox_2.getSelectedItem().toString();
				String strc3 = comboBox_3.getSelectedItem().toString();
				String strc4 = comboBox_4.getSelectedItem().toString();
				String strc5 = comboBox_5.getSelectedItem().toString();
				String strc6 = comboBox_6.getSelectedItem().toString();
				String strc7 = comboBox_7.getSelectedItem().toString();
				String strc8 = comboBox_8.getSelectedItem().toString();
			}


		});
		btnNewButton.setBackground(UIManager.getColor("Button.highlight"));
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 10));
		btnNewButton.setBounds(606, 159, 55, 35);
		getContentPane().add(btnNewButton);

		textField_21 = new JTextField();
		textField_21.setHorizontalAlignment(SwingConstants.CENTER);
		textField_21.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField_21.setColumns(10);
		textField_21.setBackground(new Color(255, 255, 255));
		textField_21.setBounds(550, 231, 117, 29);
		getContentPane().add(textField_21);

		textField_22 = new JTextField();
		textField_22.setHorizontalAlignment(SwingConstants.CENTER);
		textField_22.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField_22.setColumns(10);
		textField_22.setBackground(new Color(255, 255, 255));
		textField_22.setBounds(550, 280, 117, 29);
		getContentPane().add(textField_22);

		textField_23 = new JTextField();
		textField_23.setHorizontalAlignment(SwingConstants.CENTER);
		textField_23.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField_23.setColumns(10);
		textField_23.setBackground(new Color(255, 255, 255));
		textField_23.setBounds(550, 326, 117, 29);
		getContentPane().add(textField_23);

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

		textField_28 = new JTextField();
		textField_28.setText("01097337207");
		textField_28.setHorizontalAlignment(SwingConstants.CENTER);
		textField_28.setEditable(false);
		textField_28.setColumns(10);
		textField_28.setBackground(Color.WHITE);
		textField_28.setBounds(214, 38, 86, 29);
		getContentPane().add(textField_28);

		button = new JButton("편집");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_28.setEditable(true);
			}
		});
		button.setFont(new Font("굴림", Font.PLAIN, 10));
		button.setBackground(UIManager.getColor("Button.highlight"));
		button.setBounds(299, 38, 55, 29);
		getContentPane().add(button);

		button_1 = new JButton("저장");
		button_1.setFont(new Font("굴림", Font.PLAIN, 10));
		button_1.setBackground(UIManager.getColor("Button.highlight"));
		button_1.setBounds(353, 38, 55, 29);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_28.setEditable(false);
			}
		});
		getContentPane().add(button_1);
		setVisible(true);
		setSize(750,450);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new DorApplication();

	}
}
