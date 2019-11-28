import java.awt.BorderLayout;
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

public class ApplicationTable extends JFrame {
	public ApplicationTable() {Connection con = null;
	Statement st = null;
	ResultSet rs = null;	
	String str;
	try {
		con = DriverManager.getConnection("jdbc:mysql://localhost/test2", "root", "lucifer18");
		st = con.createStatement();
		str = "select * from Ω≈√ª";
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
		JFrame frame = new JFrame();
		frame.setSize(500,600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();
		JTable table = new JTable(data,column);
		JScrollPane jsp = new JScrollPane(table);
		panel.setLayout(new BorderLayout());
		panel.add(jsp,BorderLayout.CENTER);
		frame.setContentPane(panel);
		frame.setVisible(true);



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
	}
	
	public static void main(String[] args) {
		new ApplicationTable();

	}

}
