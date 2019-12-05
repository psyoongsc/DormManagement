import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminStudentAllocate extends JFrame {
	private JTable table;
	private JTextField textField;
	private JButton btnNewButton;

	public AdminStudentAllocate() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;	
		String str;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/test2", "root", "1234");
			st = con.createStatement();
			str = "select * from ¼±¹ß";
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
			textField.setText("\uC120\uBC1C\uC790\uBA85\uB2E8");
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 16));
			textField.setEditable(false);
			textField.setColumns(10);
			textField.setBackground(new Color(112, 128, 144));
			textField.setBounds(12, 10, 130, 30);
			getContentPane().add(textField);
			
			btnNewButton = new JButton("\uC785\uAE08\uC694\uCCAD");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SQL sql = new SQL();
					sql.InsertDepositStudent();
				}
			});
			btnNewButton.setBounds(633, 14, 91, 23);
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
		new AdminStudentAllocate();

	}

}