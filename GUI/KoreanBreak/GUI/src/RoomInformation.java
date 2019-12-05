import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class RoomInformation extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	public RoomInformation() {
		setTitle("\uD638\uC2E4\uC815\uBCF4");
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setText("호실정보");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(new Color(112, 128, 144));
		textField.setBounds(12, 10, 100, 30);
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
		textField_2.setText("20150154");
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
		textField_4.setText("김민수");
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
		textField_5.setBounds(12, 162, 80, 30);
		getContentPane().add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setText("오름3동");
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(90, 162, 120, 30);
		getContentPane().add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setText("호실종류");
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBackground(Color.LIGHT_GRAY);
		textField_7.setBounds(12, 217, 80, 30);
		getContentPane().add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setText("일반실");
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBounds(90, 217, 120, 30);
		getContentPane().add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setText("호실");
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField_9.setEditable(false);
		textField_9.setColumns(10);
		textField_9.setBackground(Color.LIGHT_GRAY);
		textField_9.setBounds(12, 271, 80, 30);
		getContentPane().add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setText("705호");
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setEditable(false);
		textField_10.setColumns(10);
		textField_10.setBounds(90, 271, 120, 30);
		getContentPane().add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setText("2");
		textField_11.setHorizontalAlignment(SwingConstants.CENTER);
		textField_11.setEditable(false);
		textField_11.setColumns(10);
		textField_11.setBounds(90, 326, 120, 30);
		getContentPane().add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setText("호실수용인원");
		textField_12.setHorizontalAlignment(SwingConstants.CENTER);
		textField_12.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField_12.setEditable(false);
		textField_12.setColumns(10);
		textField_12.setBackground(Color.LIGHT_GRAY);
		textField_12.setBounds(12, 326, 80, 30);
		getContentPane().add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.setText("수용성별");
		textField_13.setHorizontalAlignment(SwingConstants.CENTER);
		textField_13.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField_13.setEditable(false);
		textField_13.setColumns(10);
		textField_13.setBackground(Color.LIGHT_GRAY);
		textField_13.setBounds(226, 162, 80, 30);
		getContentPane().add(textField_13);
		
		textField_14 = new JTextField();
		textField_14.setText("남");
		textField_14.setHorizontalAlignment(SwingConstants.CENTER);
		textField_14.setEditable(false);
		textField_14.setColumns(10);
		textField_14.setBounds(304, 162, 120, 30);
		getContentPane().add(textField_14);
		setVisible(true);
		setSize(450,450);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
