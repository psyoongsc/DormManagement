import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.*;
import java.io.InputStreamReader;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.filechooser.*;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class TuberCertificate extends JFrame {
	private JMenuBar menuBar;
	private JMenuItem mntmNewMenuItem;
	private JLabel lblNewLabel;
	JTextArea textArea = new JTextArea();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_2;
	private JTextField textField_4;

	public TuberCertificate() {
		setTitle("\uACB0\uD575\uC9C4\uB2E8\uC11C");
		getContentPane().setLayout(null);
		textArea.setEditable(false);
		textArea.setBounds(24, 475, 313, 34);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		getContentPane().add(textArea);

		JButton btnNewButton_1 = new JButton("제출");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "제출되었습니다.");
			}
		});
		btnNewButton_1.setBounds(372, 475, 65, 34);
		btnNewButton_1.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		getContentPane().add(btnNewButton_1);
		

		menuBar = new JMenuBar();
		menuBar.setBounds(0, -1, 974, 23);
		getContentPane().add(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		mnNewMenu.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		mnNewMenu.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnNewMenu);

		mntmNewMenuItem = new JMenuItem("Open");
		mntmNewMenuItem.addActionListener(new OpenActionListener());

		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu.add(mntmNewMenuItem);

		lblNewLabel = new JLabel("Image");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(287, 46, 456, 398);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBackground(new Color(112, 128, 144));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("20150154");
		textField.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		textField.setBounds(10, 32, 123, 28);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setText("제출일");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField_1.setBackground(new Color(112, 128, 144));
		textField_1.setBounds(12, 70, 82, 34);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setBounds(93, 70, 135, 34);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setText("진단일");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		textField_2.setBackground(new Color(112, 128, 144));
		textField_2.setColumns(10);
		textField_2.setBounds(12, 122, 82, 34);
		getContentPane().add(textField_2);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setColumns(10);
		textField_4.setBounds(93, 122, 135, 34);
		getContentPane().add(textField_4);
		
		setVisible(true);
		setSize(800,600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);






	}

	class OpenActionListener implements ActionListener {
		JFileChooser chooser;

		OpenActionListener() {
			chooser= new JFileChooser(); // �뙆�씪 �떎�씠�뼹濡쒓렇 �깮�꽦
		}
		public void actionPerformed(ActionEvent e) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"�씠誘몄� �뙆�씪", // �뙆�씪 �씠由꾩뿉 李쎌뿉 異쒕젰�맆 臾몄옄�뿴
					"jpg", "gif", "jpeg", "bmp", "png", "psd", "ai", "sketch", "tif", "tiff", "tga", "webp"); // �뙆�씪 �븘�꽣濡� �궗�슜�릺�뒗 �솗�옣�옄. *.jpg. *.gif留� �굹�뿴�맖
			chooser.setFileFilter(filter); // �뙆�씪 �떎�씠�뼹濡쒓렇�뿉 �뙆�씪 �븘�꽣 �꽕�젙

			// �뙆�씪 �떎�씠�뼹濡쒓렇 異쒕젰
			int ret = chooser.showOpenDialog(null);
			if(ret != JFileChooser.APPROVE_OPTION) { // �궗�슜�옄媛�  李쎌쓣 媛뺤젣濡� �떕�븯嫄곕굹 痍⑥냼 踰꾪듉�쓣 �늻瑜� 寃쎌슦
				JOptionPane.showMessageDialog(null,"�뙆�씪�쓣 �꽑�깮�븯吏� �븡�븯�뒿�땲�떎", "寃쎄퀬", 

						JOptionPane.WARNING_MESSAGE);
				return;
			}

			// �궗�슜�옄媛� �뙆�씪�쓣 �꽑�깮�븯怨� "�뿴湲�" 踰꾪듉�쓣 �늻瑜� 寃쎌슦
			String filePath = chooser.getSelectedFile().getPath(); // �뙆�씪 寃쎈줈紐낆쓣 �븣�븘�삩�떎.
			lblNewLabel.setIcon(new ImageIcon(filePath)); // �뙆�씪�쓣 濡쒕뵫�븯�뿬 �씠誘몄� �젅�씠釉붿뿉 異쒕젰�븳�떎.
			textArea.append(filePath);
		}
	}
	public static void main(String[] args) {
		new TuberCertificate();
	}
	
}
