package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQL {
	public String IP;
	public Connection conn;
	public LoginInfo info;
	
	public SQL() {
		try {
			IP = "127.0.0.1";
			conn = DriverManager.getConnection("jdbc:mysql://" + IP + "/��Ȱ��?useSSL=false&useUnicode=true&characterEncoding=UTF-8", "root", "fss7182963");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public SQL(String IP) {
		try {
			this.IP = IP;
			conn = DriverManager.getConnection("jdbc:mysql://" + IP + "/��Ȱ��?useSSL=false&useUnicode=true&characterEncoding=UTF-8", "root", "fss7182963");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public SQL(String IP, String id, String pw) {
		try {
			this.IP = IP;
			conn = DriverManager.getConnection("jdbc:mysql://" + IP + "/��Ȱ��?useSSL=false&useUnicode=true&characterEncoding=UTF-8", id, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setInfo(String id, String pw) {
		info = new LoginInfo(id, pw);
	}
	
	public void doLogin() {
		String SQL = "select pw, ���� from ����� where id=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, info.getId());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(info.getPw())) {
					info.setName(rs.getString(2));
				} else {
					System.out.println("��й�ȣ Ʋ��");
				}
			} else {
				System.out.println("�α��� ���� ����");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doLogin(String id, String pw) {
		String checkID = "select pw, ���� from ����� where id=?";
		
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(checkID);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(pw)) {
					info.setPw(rs.getString(1));
					info.setName(rs.getString(2));
				} else {
					System.out.println("��й�ȣ Ʋ��");
				}
			} else {
				System.out.println("�α��� ���� ����");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void confirmApplication(String priority, String period, String dormID, String eatDays) {
		String SQL = "insert into ��û(`�л�id`, `����Ⱓ`, `��Ȱ������id`, `������ȣ`, `�Ļ籸���ڵ�`) values(?, ?, ?, ?, ?)";
		
		try { // �̹� ��û�� ���� ������ �����ϰ� insert ���� �ؾ���
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, info.getName());
			pstmt.setString(2, period);
			pstmt.setString(3, dormID);
			pstmt.setString(4, priority);
			pstmt.setString(5, eatDays);
			
			int r = pstmt.executeUpdate();
			
			System.out.println(r>0?"success":"fail");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String args[]) {
		SQL sql = new SQL();
		
		//sql.setInfo("20160011", "*******");
		sql.doLogin("2016001", "******");
		
		System.out.println(sql.info.getPw()); // ����ó�� �ʿ�
	}
}
