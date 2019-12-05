

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
	public void InsertStudentAllocate() {
		String SQL = "insert into 선발" 
				+"select  학생id, 생활관복합id, 기숙기간" 
				+"from 임시신청테이블" + 
				"where 기숙기간 = '1년' and 점수합계 is null";
		String SQL1 = "insert into 선발" 
				+"select 신청.학생id, 신청.생활관복합id, 신청.기숙기간" + 
				"from (select row_number() over(partition by 기숙기간,생활관복합id, 지망번호 order by 점수합계 desc) as tempNumber, "
				+"임시신청자.학생id, 임시신청자.생활관복합id, 임시신청자.기숙기간 from (select 학생id, 생활관복합id, 기숙기간, 지망번호, 점수합계 from 임시신청테이블"
				+" where 기숙기간 = '1학기' and 지망번호 = 1) as 임시신청자 left outer join 선발 on 임시신청자.학생id = 선발.학생id where 선발.학생id is null)"
				+" as 신청, 생활관_신청" + "where 신청.기숙기간 = 생활관_신청.기숙기간 and 신청.생활관복합id = 생활관_신청.생활관복합id and 신청.tempNumber "
				+"<= 생활관_신청.복합수용인원_남 + 생활관_신청.복합수용인원_여 - (select count(*) from 선발 where 선발.생활관복합id = 생활관_신청.생활관복합id)";
		String SQL2 = "insert into 선발" 
				+"select 신청.학생id, 신청.생활관복합id, 신청.기숙기간" + 
				"from (select row_number() over(partition by 기숙기간,생활관복합id, 지망번호 order by 점수합계 desc) as tempNumber, "
				+"임시신청자.학생id, 임시신청자.생활관복합id, 임시신청자.기숙기간 from (select 학생id, 생활관복합id, 기숙기간, 지망번호, 점수합계 from 임시신청테이블"
				+" where 기숙기간 = '1학기' and 지망번호 = 2) as 임시신청자 left outer join 선발 on 임시신청자.학생id = 선발.학생id where 선발.학생id is null)"
				+" as 신청, 생활관_신청" + "where 신청.기숙기간 = 생활관_신청.기숙기간 and 신청.생활관복합id = 생활관_신청.생활관복합id and 신청.tempNumber "
				+"<= 생활관_신청.복합수용인원_남 + 생활관_신청.복합수용인원_여 - (select count(*) from 선발 where 선발.생활관복합id = 생활관_신청.생활관복합id)";
		String SQL3 = "insert into 선발" 
				+"select 신청.학생id, 신청.생활관복합id, 신청.기숙기간" + 
				"from (select row_number() over(partition by 기숙기간,생활관복합id, 지망번호 order by 점수합계 desc) as tempNumber, "
				+"임시신청자.학생id, 임시신청자.생활관복합id, 임시신청자.기숙기간 from (select 학생id, 생활관복합id, 기숙기간, 지망번호, 점수합계 from 임시신청테이블"
				+" where 기숙기간 = '1학기' and 지망번호 = 3) as 임시신청자 left outer join 선발 on 임시신청자.학생id = 선발.학생id where 선발.학생id is null)"
				+" as 신청, 생활관_신청" + "where 신청.기숙기간 = 생활관_신청.기숙기간 and 신청.생활관복합id = 생활관_신청.생활관복합id and 신청.tempNumber "
				+"<= 생활관_신청.복합수용인원_남 + 생활관_신청.복합수용인원_여 - (select count(*) from 선발 where 선발.생활관복합id = 생활관_신청.생활관복합id)";

		try { // 이미 신청한 내역 있으면 삭제하고 insert 수행 해야함
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			PreparedStatement pstmt1 = conn.prepareStatement(SQL1);
			PreparedStatement pstmt2 = conn.prepareStatement(SQL2);
			PreparedStatement pstmt3 = conn.prepareStatement(SQL3);
			int r = pstmt.executeUpdate();
			int r1 = pstmt1.executeUpdate();
			int r2 = pstmt2.executeUpdate();
			int r3 = pstmt3.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}
	public void InsertAwaitingStudent() {
		String SQL = "insert into 대기" 
				+"select 임시신청테이블.학생id, row_number() over(order by 임시신청테이블.점수합계 desc) as 대기번호"
				+"from 임시신청테이블 left outer join 선발 on 임시신청테이블.학생id = 선발.학생id "
				+"where 선발.학생id is null" + 
				"group by 임시신청테이블.학생id";



		try { // 이미 신청한 내역 있으면 삭제하고 insert 수행 해야함
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			int r = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}
	public void InsertDepositStudent() {
		String SQL = "insert into 입금 (`학생id`, `입사비용`) " + 
				"select 신청.학생id," + 
				"case when 신청.식사구분코드 = 10 then 생활관_신청.7일식비용 + 생활관_신청.생활관사용료 + 생활관_신청.기타공공요금" + 
				"when 신청.식사구분코드 = 20 then 생활관_신청.5일식비용 + 생활관_신청.생활관사용료 + 생활관_신청.기타공공요금" + 
				"else 0" + 
				"end as 생활관비" + 
				"from 신청, 선발, 생활관_신청" + 
				"where 신청.학생id = 선발.학생id and 신청.기숙기간 = 선발.기숙기간 and 신청.생활관복합id = 선발.생활관복합id and 선발.생활관복합id = 생활관_신청.생활관복합id and 신청.기숙기간 = 생활관_신청.기숙기간" + 
				"group by 신청.학생id";
		try { // 이미 신청한 내역 있으면 삭제하고 insert 수행 해야함
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			int r = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void UpdateDepositToDefault() {
		String SQL = "UPDATE `mydb`.`입금` SET `가상계좌번호` = '3521386939411', `관비납부여부` = '미납' WHERE (`학생id` = '20160011');" + 
				"UPDATE `mydb`.`입금` SET `가상계좌번호` = '3521386939403', `관비납부여부` = '미납' WHERE (`학생id` = '20160022');" + 
				"UPDATE `mydb`.`입금` SET `가상계좌번호` = '3521386939402', `관비납부여부` = '미납' WHERE (`학생id` = '20160298');" + 
				"UPDATE `mydb`.`입금` SET `가상계좌번호` = '3521386939401', `관비납부여부` = '미납' WHERE (`학생id` = '20171111');" + 
				"UPDATE `mydb`.`입금` SET `가상계좌번호` = '3521386939404', `관비납부여부` = '미납' WHERE (`학생id` = '20180003');" + 
				"UPDATE `mydb`.`입금` SET `가상계좌번호` = '3521386939405', `관비납부여부` = '미납' WHERE (`학생id` = '20180105');" + 
				"UPDATE `mydb`.`입금` SET `가상계좌번호` = '3521386939406', `관비납부여부` = '미납' WHERE (`학생id` = '20180179');" + 
				"UPDATE `mydb`.`입금` SET `가상계좌번호` = '3521386939407', `관비납부여부` = '미납' WHERE (`학생id` = '20180198');" + 
				"UPDATE `mydb`.`입금` SET `가상계좌번호` = '3521386939408', `관비납부여부` = '미납' WHERE (`학생id` = '20180457');" + 
				"UPDATE `mydb`.`입금` SET `가상계좌번호` = '3521386939409', `관비납부여부` = '미납' WHERE (`학생id` = '20180548');" + 
				"UPDATE `mydb`.`입금` SET `가상계좌번호` = '3521386939410', `관비납부여부` = '미납' WHERE (`학생id` = '20191111');";
		try { // 이미 신청한 내역 있으면 삭제하고 insert 수행 해야함
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			int r = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void InsertTuberOkStudent() {
		String SQL = "insert into 결핵진단서 (`학생id`)" + 
				"select 학생id from 입금 where 관비납부여부 = '납부';";
		try { // 이미 신청한 내역 있으면 삭제하고 insert 수행 해야함
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			int r = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void InsertAssignStudent() {
		String SQL = "insert into 배정(학생id, 생활관id)" + 
				"" + 
				"select 선발직전.학생id, case when (선발직전.생활관복합id = 10 or 선발직전.생활관복합id = 11 and 선발직전.num <= 호실.수용인원) then 20" + 
				"when (선발직전.생활관복합id = 10 or 선발직전.생활관복합id = 11 and 선발직전.num > 호실.수용인원) then 10" + 
				"when (선발직전.생활관복합id = 30 or 선발직전.생활관복합id = 31 or 선발직전.생활관복합id = 32) then 30 " + 
				"when (선발직전.생활관복합id = 60 and 선발직전.num <= 호실.수용인원) then 70" + 
				"when (선발직전.생활관복합id = 80 or 선발직전.생활관복합id = 81 or 선발직전.생활관복합id = 82 or 선발직전.생활관복합id = 83) then 80" + 
				"else 선발직전.생활관복합id end as 생활관id" + 
				"from (select row_number() over(partition by 선발.생활관복합id) as num ,선발.학생id, 선발.생활관복합id " + 
				"from 결핵진단서, 선발 where 결핵진단서.제출일시 is not null and year(결핵진단서.진단일시) = 2020 and 결핵진단서.학생id = 선발.학생id) as 선발직전, 호실, 생활관" + 
				"where 생활관.생활관id = 호실.생활관id and 선발직전.생활관복합id = 생활관.생활관복합id" + 
				"" + 
				"group by 선발직전.학생id;";
		try { // 이미 신청한 내역 있으면 삭제하고 insert 수행 해야함
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			int r = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void InsertSchedule(String sName, String sCode, String startTime, String finishTime) {
		String SQL = "insert into 일정(일정명 , 일정구분코드 , 시작일시 , 종료일시) values (?,?,?,?)";
		try { // 이미 신청한 내역 있으면 삭제하고 insert 수행 해야함
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, sName);
			pstmt.setString(2, sCode);
			pstmt.setString(3, startTime);
			pstmt.setString(4, finishTime);
			int r = pstmt.executeUpdate();
			
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