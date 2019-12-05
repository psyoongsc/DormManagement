import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class AdminTuberCertificate extends JFrame{
	private JTable table;
	private JTextField textField;
	
	public AdminTuberCertificate() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;	
		String str;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/test2", "root", "1234");
			st = con.createStatement();
			str = "select * from ∞·«Ÿ¡¯¥‹º≠";
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
			
			JButton btnNewButton = new JButton("\uC120\uBC1C");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SQL sql = new SQL();
					sql.InsertAssignStudent();
				}
			});
			btnNewButton.setBounds(609, 14, 91, 23);
			getContentPane().add(btnNewButton);
			
			textField = new JTextField();
			textField.setText("\uACB0\uC9C4\uB300\uAE30\uBA85\uB2E8");
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.PLAIN, 16));
			textField.setEditable(false);
			textField.setColumns(10);
			textField.setBackground(new Color(112, 128, 144));
			textField.setBounds(12, 10, 130, 30);
			getContentPane().add(textField);



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
}