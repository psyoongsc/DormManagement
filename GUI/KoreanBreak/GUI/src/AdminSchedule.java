import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class AdminSchedule extends JFrame{
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JButton btnNewButton;
	
	public AdminSchedule() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;	
		String str;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/test2", "root", "1234");
			st = con.createStatement();
			str = "select * from ¿œ¡§";
			rs = st.executeQuery(str);
			ResultSetMetaData rsmt = rs.getMetaData();
			int c = rsmt.getColumnCount();
			Vector column = new Vector(c);
			for(int i = 1; i <= c; i++)
			{
				column.add(rsmt.getColumnName(i));
			}
			Vector data = new Vector();
			Vector row = new Vector();
			while(rs.next())
			{
				row = new Vector(c);
				for(int i = 1; i <= c; i++) {
					row.add(rs.getString(i));
				}
				data.add(row);
			}
			getContentPane().setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 148, 688, 333);
			scrollPane.setLayout(null);
			getContentPane().add(scrollPane);
			
			table = new JTable(data,column);
			scrollPane.setViewportView(table);
			
			textField = new JTextField();
			textField.setText("\uC77C\uC815");
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.PLAIN, 16));
			textField.setEditable(false);
			textField.setColumns(10);
			textField.setBackground(new Color(112, 128, 144));
			textField.setBounds(12, 10, 130, 30);
			getContentPane().add(textField);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC120\uD0DD\uC548\uD568", "\uC77C\uC815\uCF54\uB4DC1", "\uC77C\uC815\uCF54\uB4DC2", "\uC77C\uC815\uCF54\uB4DC3"}));
			comboBox.setBounds(12, 95, 110, 30);
			getContentPane().add(comboBox);
			
			textField_1 = new JTextField();
			textField_1.setBounds(121, 65, 472, 60);
			getContentPane().add(textField_1);
			textField_1.setColumns(10);
			
			textField_2 = new JTextField();
			textField_2.setEditable(false);
			textField_2.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.PLAIN, 12));
			textField_2.setText("\uC77C\uC815\uD3B8\uC9D1");
			textField_2.setBackground(new Color(192, 192, 192));
			textField_2.setBounds(12, 65, 110, 30);
			getContentPane().add(textField_2);
			textField_2.setColumns(10);
			
			textField_3 = new JTextField();
			textField_3.setHorizontalAlignment(SwingConstants.CENTER);
			textField_3.setText("\uC2DC\uC791\uC77C\uC2DC");
			textField_3.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.PLAIN, 12));
			textField_3.setColumns(10);
			textField_3.setBackground(new Color(255, 255, 255));
			textField_3.setBounds(590, 65, 110, 30);
			getContentPane().add(textField_3);
			
			textField_4 = new JTextField();
			textField_4.setHorizontalAlignment(SwingConstants.CENTER);
			textField_4.setText("\uC885\uB8CC\uC77C\uC2DC");
			textField_4.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.PLAIN, 12));
			textField_4.setColumns(10);
			textField_4.setBackground(new Color(255, 255, 255));
			textField_4.setBounds(590, 95, 110, 30);
			getContentPane().add(textField_4);
			String body, sDate, fDate, code;;
			body = textField_1.getText();
			sDate = textField_3.getText();
			fDate = textField_4.getText();
			code = comboBox.getSelectedItem().toString();
			
			btnNewButton = new JButton("\uC800\uC7A5");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SQL sql = new SQL();
					sql.InsertSchedule(body, code, sDate, fDate);
					dispose();
					new AdminSchedule();
				}
			});
			btnNewButton.setBounds(609, 14, 91, 23);
			getContentPane().add(btnNewButton);
			
			



		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR");
		}finally {
			try {
				st.close();
				rs.close();
				con.close();
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "CLOSE ERROR");
			}
		}
		setVisible(true);
		setSize(750,520);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new AdminSchedule();

	}
}

