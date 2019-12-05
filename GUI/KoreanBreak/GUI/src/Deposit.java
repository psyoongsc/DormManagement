import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JCheckBox;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Deposit extends JFrame {
	private JTextField textField;
	private JTextField textField_5;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_23;
	private JTextField textField_25;
	private JTextField textField_26;
	private JTextField textField_27;
	private JTextField textField_24;
	private JTextField textField_28;
	private JTextField textField_29;
	private JTextField textField_30;
	private JTextField textField_31;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_32;
	private JTextField textField_1;
	private JTextField textField_2;
	public Deposit() {
		getContentPane().setLayout(null);
		setTitle("입금창");
		textField = new JTextField();
		textField.setEditable(false);
		textField.setForeground(new Color(0, 0, 0));
		textField.setBackground(new Color(112, 128, 144));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		textField.setText("20150154");
		textField.setBounds(12, 10, 160, 45);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("\uC785\uAE08\uD655\uC778\uC5EC\uBD80");
		chckbxNewCheckBox.setForeground(Color.RED);
		chckbxNewCheckBox.setBounds(818, 53, 107, 23);
		chckbxNewCheckBox.setEnabled(false);
		getContentPane().add(chckbxNewCheckBox);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setText("생활관");
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_5.setColumns(10);
		textField_5.setBackground(SystemColor.menu);
		textField_5.setBounds(97, 145, 94, 23);
		getContentPane().add(textField_5);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setText("\uC2E0\uCCAD\uB0B4\uC5ED");
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setForeground(Color.BLACK);
		textField_8.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		textField_8.setColumns(10);
		textField_8.setBackground(new Color(112, 128, 144));
		textField_8.setBounds(12, 101, 86, 89);
		getContentPane().add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setEditable(false);
		textField_9.setText("1\uB144\uAE30\uC219");
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setForeground(Color.BLACK);
		textField_9.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		textField_9.setColumns(10);
		textField_9.setBackground(new Color(112, 128, 144));
		textField_9.setBounds(97, 101, 279, 45);
		getContentPane().add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setEditable(false);
		textField_10.setText("식사구분");
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_10.setColumns(10);
		textField_10.setBackground(SystemColor.menu);
		textField_10.setBounds(189, 145, 94, 23);
		getContentPane().add(textField_10);
		
		textField_19 = new JTextField();
		textField_19.setEditable(false);
		textField_19.setText("식사(하계방학)");
		textField_19.setHorizontalAlignment(SwingConstants.CENTER);
		textField_19.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_19.setColumns(10);
		textField_19.setBackground(SystemColor.menu);
		textField_19.setBounds(282, 145, 94, 23);
		getContentPane().add(textField_19);
		
		textField_20 = new JTextField();
		textField_20.setEditable(false);
		textField_20.setText("1\uD559\uAE30\uC785\uC0AC");
		textField_20.setHorizontalAlignment(SwingConstants.CENTER);
		textField_20.setForeground(Color.BLACK);
		textField_20.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		textField_20.setColumns(10);
		textField_20.setBackground(new Color(112, 128, 144));
		textField_20.setBounds(375, 101, 555, 45);
		getContentPane().add(textField_20);
		
		textField_21 = new JTextField();
		textField_21.setEditable(false);
		textField_21.setText("1지망");
		textField_21.setHorizontalAlignment(SwingConstants.CENTER);
		textField_21.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_21.setColumns(10);
		textField_21.setBackground(SystemColor.menu);
		textField_21.setBounds(375, 145, 94, 23);
		getContentPane().add(textField_21);
		
		textField_22 = new JTextField();
		textField_22.setEditable(false);
		textField_22.setText("식사구분");
		textField_22.setHorizontalAlignment(SwingConstants.CENTER);
		textField_22.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_22.setColumns(10);
		textField_22.setBackground(SystemColor.menu);
		textField_22.setBounds(467, 145, 94, 23);
		getContentPane().add(textField_22);
		
		textField_23 = new JTextField();
		textField_23.setEditable(false);
		textField_23.setText("2지망");
		textField_23.setHorizontalAlignment(SwingConstants.CENTER);
		textField_23.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_23.setColumns(10);
		textField_23.setBackground(SystemColor.menu);
		textField_23.setBounds(560, 145, 94, 23);
		getContentPane().add(textField_23);
		
		textField_25 = new JTextField();
		textField_25.setEditable(false);
		textField_25.setText("식사구분");
		textField_25.setHorizontalAlignment(SwingConstants.CENTER);
		textField_25.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_25.setColumns(10);
		textField_25.setBackground(SystemColor.menu);
		textField_25.setBounds(651, 145, 94, 23);
		getContentPane().add(textField_25);
		
		textField_26 = new JTextField();
		textField_26.setEditable(false);
		textField_26.setText("3지망");
		textField_26.setHorizontalAlignment(SwingConstants.CENTER);
		textField_26.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_26.setColumns(10);
		textField_26.setBackground(SystemColor.menu);
		textField_26.setBounds(743, 145, 94, 23);
		getContentPane().add(textField_26);
		
		textField_27 = new JTextField();
		textField_27.setEditable(false);
		textField_27.setText("식사구분");
		textField_27.setHorizontalAlignment(SwingConstants.CENTER);
		textField_27.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_27.setColumns(10);
		textField_27.setBackground(SystemColor.menu);
		textField_27.setBounds(836, 145, 94, 23);
		getContentPane().add(textField_27);
		
		textField_24 = new JTextField();
		textField_24.setEditable(false);
		textField_24.setText("선발");
		textField_24.setHorizontalAlignment(SwingConstants.CENTER);
		textField_24.setForeground(Color.BLACK);
		textField_24.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		textField_24.setColumns(10);
		textField_24.setBackground(new Color(112, 128, 144));
		textField_24.setBounds(12, 208, 86, 63);
		getContentPane().add(textField_24);
		
		textField_28 = new JTextField();
		textField_28.setEditable(false);
		textField_28.setText("생활관구분");
		textField_28.setHorizontalAlignment(SwingConstants.CENTER);
		textField_28.setForeground(Color.BLACK);
		textField_28.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		textField_28.setColumns(10);
		textField_28.setBackground(new Color(112, 128, 144));
		textField_28.setBounds(97, 208, 186, 42);
		getContentPane().add(textField_28);
		
		textField_29 = new JTextField();
		textField_29.setEditable(false);
		textField_29.setText("기간구분");
		textField_29.setHorizontalAlignment(SwingConstants.CENTER);
		textField_29.setForeground(Color.BLACK);
		textField_29.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		textField_29.setColumns(10);
		textField_29.setBackground(new Color(112, 128, 144));
		textField_29.setBounds(282, 208, 186, 42);
		getContentPane().add(textField_29);
		
		textField_30 = new JTextField();
		textField_30.setEditable(false);
		textField_30.setText("식사구분");
		textField_30.setHorizontalAlignment(SwingConstants.CENTER);
		textField_30.setForeground(Color.BLACK);
		textField_30.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		textField_30.setColumns(10);
		textField_30.setBackground(new Color(112, 128, 144));
		textField_30.setBounds(468, 208, 186, 42);
		getContentPane().add(textField_30);
		
		textField_31 = new JTextField();
		textField_31.setEditable(false);
		textField_31.setText("입사금액");
		textField_31.setHorizontalAlignment(SwingConstants.CENTER);
		textField_31.setForeground(Color.BLACK);
		textField_31.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		textField_31.setColumns(10);
		textField_31.setBackground(new Color(112, 128, 144));
		textField_31.setBounds(651, 208, 279, 42);
		getContentPane().add(textField_31);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(97, 167, 94, 23);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(189, 167, 94, 23);
		getContentPane().add(textField_4);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(282, 167, 94, 23);
		getContentPane().add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(375, 167, 94, 23);
		getContentPane().add(textField_7);
		
		textField_11 = new JTextField();
		textField_11.setEditable(false);
		textField_11.setColumns(10);
		textField_11.setBounds(467, 167, 94, 23);
		getContentPane().add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setEditable(false);
		textField_12.setColumns(10);
		textField_12.setBounds(560, 167, 94, 23);
		getContentPane().add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.setEditable(false);
		textField_13.setColumns(10);
		textField_13.setBounds(651, 167, 94, 23);
		getContentPane().add(textField_13);
		
		textField_14 = new JTextField();
		textField_14.setEditable(false);
		textField_14.setColumns(10);
		textField_14.setBounds(743, 167, 94, 23);
		getContentPane().add(textField_14);
		
		textField_15 = new JTextField();
		textField_15.setEditable(false);
		textField_15.setColumns(10);
		textField_15.setBounds(836, 167, 94, 23);
		getContentPane().add(textField_15);
		
		textField_16 = new JTextField();
		textField_16.setEditable(false);
		textField_16.setColumns(10);
		textField_16.setBounds(97, 248, 186, 23);
		getContentPane().add(textField_16);
		
		textField_17 = new JTextField();
		textField_17.setEditable(false);
		textField_17.setColumns(10);
		textField_17.setBounds(283, 248, 186, 23);
		getContentPane().add(textField_17);
		
		textField_18 = new JTextField();
		textField_18.setEditable(false);
		textField_18.setColumns(10);
		textField_18.setBounds(467, 248, 186, 23);
		getContentPane().add(textField_18);
		
		textField_32 = new JTextField();
		textField_32.setEditable(false);
		textField_32.setColumns(10);
		textField_32.setBounds(651, 248, 279, 23);
		getContentPane().add(textField_32);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setText("가상계좌번호");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setForeground(Color.BLACK);
		textField_1.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(112, 128, 144));
		textField_1.setBounds(12, 312, 112, 36);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(123, 312, 160, 36);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("고지서출력");
		btnNewButton.setBackground(UIManager.getColor("Button.light"));
		btnNewButton.setBounds(360, 325, 94, 23);
		getContentPane().add(btnNewButton);
		setVisible(true);
		setSize(1012,452);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Deposit();
	}
}
