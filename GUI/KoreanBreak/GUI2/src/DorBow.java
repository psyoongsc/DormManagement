import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JScrollPane;

public class DorBow extends JFrame {
	public DorBow() {
		setTitle("입사서약서");
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("동의하지않음");
		btnNewButton.setBackground(UIManager.getColor("Button.highlight"));
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 20));
		btnNewButton.setBounds(168, 521, 153, 50);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("동의");
		btnNewButton_1.setBackground(UIManager.getColor("Button.highlight"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DorApplication();
				setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 20));
		btnNewButton_1.setBounds(408, 521, 153, 50);
		getContentPane().add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(99, 50, 564, 461);
		getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setText("");
		
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		setVisible(true);
		setSize(800,800);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new DorBow();

	}
}
