

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQL {
	private String IP;
	private Connection conn;
	private LoginInfo info;
	
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
	public LoginInfo getInfo() {
		return info;
	}
	
	public void doLogin() {
		String SQL = "select 사용자.pw, 사용자.성명, 학생.성별, 코드.코드명, 학생.학생주소, 학생.보호자주소, 학생.주민등록번호, 학생.휴대전화번호 from 사용자, 학생, 코드 where 학생.학생id=? and 사용자.id=? and 코드.코드구분id=004 and 코드.코드=학생.학적상태";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, info.getId());
			pstmt.setString(2, info.getId());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(info.getPw())) {
					info.setName(rs.getString(2));
					
					info.setSex(rs.getString(3));
					info.setStatus(rs.getString(4));
					info.setStudentAddress(rs.getString(5));
					info.setParentAddress(rs.getString(6));
					info.setIdentificationNumber(rs.getString(7));
					info.setPhoneNumber(rs.getString(8));
				} else {
					System.out.println("비밀번호가 틀렸습니다.");
				}
			} else {
				System.out.println("로그인 정보가 잘못되었습니다.");
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doLogin(String id, String pw) {
		String SQL = "select 사용자.pw, 사용자.성명, 학생.성별, 코드.코드명, 학생.학생주소, 학생.보호자주소, 학생.주민등록번호, 학생.휴대전화번호 from 사용자, 학생, 코드 where 학생.학생id=? and 사용자.id=? and 코드.코드구분id=004 and 코드.코드=학생.학적상태";
		
		try {
			info.setId(id);
			info.setPw(pw);
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, info.getId());
			pstmt.setString(2, info.getId());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(info.getPw())) {
					info.setName(rs.getString(2));
					
					info.setSex(rs.getString(3));
					info.setStatus(rs.getString(4));
					info.setStudentAddress(rs.getString(5));
					info.setParentAddress(rs.getString(6));
					info.setIdentificationNumber(rs.getString(7));
					info.setPhoneNumber(rs.getString(8));
				} else {
					System.out.println("비밀번호가 틀렸습니다.");
				}
			} else {
				System.out.println("로그인 정보가 잘못되었습니다.");
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void confirmApplication(String priority, String period, String dormID, String eatDays) {
		String SQL = "insert into 신청(`학생id`, `기숙기간`, `생활관복합id`, `지망번호`, `식사구분코드`) values(?, ?, ?, ?, ?)";
		
		try { // �̹� ��û�� ���� ������ �����ϰ� insert ���� �ؾ���
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, info.getName());
			pstmt.setString(2, period);
			pstmt.setString(3, dormID);
			pstmt.setString(4, priority);
			pstmt.setString(5, eatDays);
			
			int r = pstmt.executeUpdate();
			
			System.out.println(r>0?"success":"fail");
			
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
