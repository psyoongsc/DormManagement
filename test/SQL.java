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
			conn = DriverManager.getConnection("jdbc:mysql://" + IP + "/생활관?useSSL=false&useUnicode=true&characterEncoding=UTF-8", "root", "fss7182963");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public SQL(String IP) {
		try {
			this.IP = IP;
			conn = DriverManager.getConnection("jdbc:mysql://" + IP + "/생활관?useSSL=false&useUnicode=true&characterEncoding=UTF-8", "root", "fss7182963");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public SQL(String IP, String id, String pw) {
		try {
			this.IP = IP;
			conn = DriverManager.getConnection("jdbc:mysql://" + IP + "/생활관?useSSL=false&useUnicode=true&characterEncoding=UTF-8", id, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setInfo(String id, String pw) {
		info = new LoginInfo(id, pw);
	}
	
	public void doLogin() {
		String SQL = "select pw, 성명 from 사용자 where id=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, info.getId());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(info.getPw())) {
					info.setName(rs.getString(2));
				} else {
					System.out.println("비밀번호 틀림");
				}
			} else {
				System.out.println("로그인 정보 없음");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doLogin(String id, String pw) {
		String checkID = "select pw, 성명 from 사용자 where id=?";
		
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(checkID);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(pw)) {
					info.setPw(rs.getString(1));
					info.setName(rs.getString(2));
				} else {
					System.out.println("비밀번호 틀림");
				}
			} else {
				System.out.println("로그인 정보 없음");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void confirmApplication(String priority, String period, String dormID, String eatDays) {
		String SQL = "insert into 신청(`학생id`, `기숙기간`, `생활관복합id`, `지망번호`, `식사구분코드`) values(?, ?, ?, ?, ?)";
		
		try { // 이미 신청한 내역 있으면 삭제하고 insert 수행 해야함
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
		
		System.out.println(sql.info.getPw()); // 예외처리 필요
	}
}
