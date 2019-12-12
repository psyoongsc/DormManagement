package DormitoryProgram;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SQL {
	private String IP;
	private Connection conn;
	private LoginInfo info;
	private ApplyAInfo Ainfo;
	private String data;
	StringTokenizer st;
	Statement stat = null;
	ResultSet rs = null;
	String SQL;

	public SQL() {
		try {
			IP = "127.0.0.1";
			conn = DriverManager.getConnection("jdbc:mysql://localhost/domitory?useSSL=false&serverTimezone=UTC",
					"root", "Dtd0613~~");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SQL(String IP) {
		try {
			this.IP = IP;
			conn = DriverManager.getConnection("jdbc:mysql://localhost/domitory?useSSL=false&serverTimezone=UTC",
					"root", "Dtd0613~~");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SQL(String IP, String id, String pw) {
		try {
			this.IP = IP;

			conn = DriverManager.getConnection("jdbc:mysql://localhost/domitory?useSSL=false&serverTimezone=UTC", id,
					pw);
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

	public void doLogin(int n) {
		String SQL;
		if (n == 1) { // 관리자
			SQL = "select 사용자.pw, 사용자.성명, 관리자.휴대전화번호 from 사용자, 관리자 where 사용자.id=? and 관리자.관리자id=? and 사용자.id = 관리자.관리자id";

			try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, info.getId());
				pstmt.setString(2, info.getId());

				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) { // 존재하는 id라면?
					if (rs.getString(1).equals(info.getPw())) {
						info.setName(rs.getString(2));
						info.setPhoneNumber(rs.getString(3));
					} else { // id는 존재하지만 비밀번호가 틀릴 경우
						System.out.println("비밀번호가 틀렸습니다.");
					}
				} else { // id 조차 존재하지 않는 id일 때
					System.out.println("로그인 정보가 잘못되었습니다.");
				}
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else { // 학생
			SQL = "select 사용자.pw, 사용자.성명, 학생.성별, 코드.코드명, 학생.학생주소, 학생.보호자주소, 학생.주민등록번호, 학생.휴대전화번호 from 사용자, 학생, 코드 where 학생.학생id=? and 사용자.id=? and 코드.코드구분id=004 and 코드.코드=학생.학적상태";

			try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, info.getId());
				pstmt.setString(2, info.getId());
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) { // 존재하는 id라면?
					if (rs.getString(1).equals(info.getPw())) {
						info.setName(rs.getString(2));

						info.setSex(rs.getString(3));
						info.setStatus(rs.getString(4));
						info.setStudentAddress(rs.getString(5));
						info.setParentAddress(rs.getString(6));
						info.setIdentificationNumber(rs.getString(7));
						info.setPhoneNumber(rs.getString(8));

					} else { // id는 존재하지만 비밀번호가 틀릴 경우
						System.out.println("비밀번호가 틀렸습니다.");
					}
				} else { // id 조차 존재하지 않는 id일 때
					System.out.println("로그인 정보가 잘못되었습니다.");
				}
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

			if (rs.next()) {
				if (rs.getString(1).equals(info.getPw())) {
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

	public void submitTuberCertificate(String filename, String studentNum) {
		try {
			String SQL = "update 결핵진단서 set 파일명=?, 제출일시=now() where 학생id=?";
			PreparedStatement pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1, filename);
			pstmt.setString(2, studentNum);

			pstmt.executeUpdate();

			pstmt.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
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

			System.out.println(r > 0 ? "success" : "fail");

			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 클라이언트가 서버에게 전송한 데이터를 서버가 여기로 보내서 넣어줌.
	public void setAinfo(String id, String period, String dorm, String desire, String meal, String period1,
			String dorm1, String desire1, String meal1, String period2, String dorm2, String desire2, String meal2,
			String period3, String dorm3, String desire3, String meal3) {
		Ainfo = new ApplyAInfo(id, period, dorm, desire, meal, period1, dorm1, desire1, meal1, period2, dorm2, desire2,
				meal2, period3, dorm3, desire3, meal3);
	}

	// 저장된 데이터를 불러올 수 있음.
	public ApplyAInfo getAinfo() {
		return Ainfo;
	}

	// 신청내역을 sql테이블에 넣는 부분. 1년 기숙 신청
	public void insertStudent(String result) {
		try {
			Statement pstmt = conn.createStatement();

			String sql = "insert into 신청 (학생id, 기숙기간, 생활관복합id, 지망번호, 식사구분코드)\r\n" + "values\r\n" + "('" + Ainfo.getId()
					+ "', '" + Ainfo.getPeriod() + "', (select 코드.코드 from 코드 where 코드.코드구분id = 002 and 코드.코드명 = '"
					+ Ainfo.getDorm() + "'), " + Ainfo.getDesire()
					+ ", (select 코드.코드 from 코드 where 코드.코드구분id = 005 and 코드.코드명 = '" + Ainfo.getMeal() + "'));\r\n";

			int count = pstmt.executeUpdate(sql);

			if (count == 0) {
				System.out.println("데이터입력실패");
			} else {
				System.out.println("데이터입력성공");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertStudent1(String result1) {
		try {
			Statement pstmt = conn.createStatement();

			String sql1 = "insert into 신청 (학생id, 기숙기간, 생활관복합id, 지망번호, 식사구분코드)\r\n" + "values\r\n" + "('" + Ainfo.getId()
					+ "', '" + Ainfo.getPeriod1() + "', (select 코드.코드 from 코드 where 코드.코드구분id = 002 and 코드.코드명 = '"
					+ Ainfo.getDorm1() + "'), " + Ainfo.getDesire1()
					+ ", (select 코드.코드 from 코드 where 코드.코드구분id = 005 and 코드.코드명 = '" + Ainfo.getMeal1() + "'));\r\n"
					+ "";

			int count1 = pstmt.executeUpdate(sql1);

			if (count1 == 0) {
				System.out.println("데이터입력실패");
			} else {
				System.out.println("데이터입력성공");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertStudent2(String result2) {
		try {
			Statement pstmt = conn.createStatement();

			String sql2 = "insert into 신청 (학생id, 기숙기간, 생활관복합id, 지망번호, 식사구분코드)\r\n" + "values\r\n" + "('" + Ainfo.getId()
					+ "', '" + Ainfo.getPeriod2() + "', (select 코드.코드 from 코드 where 코드.코드구분id = 002 and 코드.코드명 = '"
					+ Ainfo.getDorm2() + "'), " + Ainfo.getDesire2()
					+ ", (select 코드.코드 from 코드 where 코드.코드구분id = 005 and 코드.코드명 = '" + Ainfo.getMeal2() + "'));\r\n"
					+ "";

			int count2 = pstmt.executeUpdate(sql2);

			if (count2 == 0) {
				System.out.println("데이터입력실패");
			} else {
				System.out.println("데이터입력성공");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertStudent3(String result3) {
		try {
			Statement pstmt = conn.createStatement();

			String sql3 = "insert into 신청 (학생id, 기숙기간, 생활관복합id, 지망번호, 식사구분코드)\r\n" + "values\r\n" + "('" + Ainfo.getId()
					+ "', '" + Ainfo.getPeriod3() + "', (select 코드.코드 from 코드 where 코드.코드구분id = 002 and 코드.코드명 = '"
					+ Ainfo.getDorm3() + "'), " + Ainfo.getDesire3()
					+ ", (select 코드.코드 from 코드 where 코드.코드구분id = 005 and 코드.코드명 = '" + Ainfo.getMeal3() + "'));\r\n"
					+ "";

			int count3 = pstmt.executeUpdate(sql3);

			if (count3 == 0) {
				System.out.println("데이터입력실패");
			} else {
				System.out.println("데이터입력성공");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int RegisterDormitoryInfo(String code, String str) { // 들어오는 정수값이 1이면 관리자정보를 비교하는거고 2이면 학생정보를 비교하는 것이다.

		String data = str;
		String SQL = null;
		StringTokenizer st;

		ExtraPointInfo point = new ExtraPointInfo();

		if (code.equals("1")) { // 거리가산점
			try {

				st = new StringTokenizer(data, "#");
				point.setRegionName(st.nextToken());
				point.setExtraPoint(st.nextToken());
				SQL = "update 거리가산점 set 가산점 = " + Double.parseDouble(point.getExtraPoint()) + " where 지역명 = '"
						+ point.getRegionName() + "';";
				Statement pstmt = conn.createStatement();
				int rs = pstmt.executeUpdate(SQL);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (code.equals("2")) { // 일정
			try {
				data = str;
				st = new StringTokenizer(data, "#");
				ScheduleInfo info = new ScheduleInfo();

				info.setScheduleName(st.nextToken());
				info.setStartDate(st.nextToken());
				info.setEndDate(st.nextToken());
				SQL = "update 일정 set 시작일시 = '" + info.getStartDate() + "', 종료일시 = '" + info.getEndDate()
						+ "', 일정구분코드 = 20 where 일정명 = '" + info.getScheduleName() + "';";

				Statement pstmt = conn.createStatement();
				int rs = pstmt.executeUpdate(SQL);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (code.equals("3")) { // 급식실
			try {
				data = str;
				st = new StringTokenizer(data, "#");
				CafeteriaInfo cafe = new CafeteriaInfo();
				cafe.setCafeteriaId(st.nextToken());
				cafe.setTypeOfMeal1(st.nextToken());
				cafe.setTypeOFMeal2(st.nextToken());
				cafe.setOneTimeMealCost(st.nextToken());
				cafe.setNoMealProvision(st.nextToken());
				SQL = "update 급식실 set 식사유형1='" + cafe.getTypeOfMeal1() + "', 식사유형2 = '" + cafe.getTypeOFMeal2()
						+ "', 1회식사비용=" + cafe.getOneTimeMealCost() + ", 식사안함제공여부='" + cafe.getNoMealProvision()
						+ "' where 급식실id = (select 코드 from 코드 where 코드구분id = '003' and 코드명 = '" + cafe.getCafeteriaId()
						+ "');";

				Statement pstmt = conn.createStatement();
				int rs = pstmt.executeUpdate(SQL);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (code.equals("4")) { // 생활관. update로 하자니 모순이 발생하여 비어있는 테이블에 insert하는 방식으로!!!
			try {
				data = str;
				st = new StringTokenizer(data, "#");
				DormitoryInfo dorm = new DormitoryInfo();

				dorm.setDormId(st.nextToken());
				dorm.setComplexDormId(st.nextToken());
				dorm.setCafeteriaId(st.nextToken());

				SQL = "insert into 생활관\n" + "\n" + "select 생활관.코드, 생활관복합.코드, 급식실.코드\n"
						+ "from (select * from 코드 where 코드구분id = 001) as 생활관,  (select * from 코드 where 코드구분id = 002) as 생활관복합, (select * from 코드 where 코드구분id = 003) as 급식실\n"
						+ "where 생활관.코드명 = '" + dorm.getDormId() + "' and 생활관복합.코드명 = '" + dorm.getComplexDormId()
						+ "' and 급식실.코드명 = '" + dorm.getCafeteriaId() + "';\n";

				Statement pstmt = conn.createStatement();
				int rs = pstmt.executeUpdate(SQL);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (code.equals("5")) { // 생활관_신청
			try {
				data = str;
				st = new StringTokenizer(data, "#");
				DormitoryForApplicationInfo dfai = new DormitoryForApplicationInfo();

				dfai.setComplexDormId(st.nextToken());
				dfai.setCafeteriaId(st.nextToken());
				dfai.setPeriod(st.nextToken());
				dfai.setFee(st.nextToken());
				dfai.setPublicUtilityCharges(st.nextToken());
				dfai.setSevenDayMealCost(st.nextToken());
				dfai.setFiveDayMealCost(st.nextToken());
				dfai.setNumber_men(st.nextToken());
				dfai.setNumber_women(st.nextToken());

				SQL = "update 생활관_신청 " + "set 생활관_신청.급식실id = ?, "
						+ "생활관_신청.기숙기간 = ?, 생활관_신청.생활관사용료 = ?, 생활관_신청.기타공공요금 = ?, "
						+ "생활관_신청.비용_7일식 =?, 생활관_신청.비용_5일식 = ?, 생활관_신청.모집인원_남 = ?, " + "생활관_신청.모집인원_여 = ? "
						+ "where 생활관_신청.생활관복합id = ?;";

				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, Integer.parseInt(dfai.getCafeteriaId()));
				pstmt.setString(2, dfai.getPeriod());
				pstmt.setInt(3, Integer.parseInt(dfai.getFee()));
				pstmt.setInt(4, Integer.parseInt(dfai.getPublicUtilityCharges()));
				pstmt.setInt(5, Integer.parseInt(dfai.getSevenDayMealCost()));
				pstmt.setInt(6, Integer.parseInt(dfai.getFiveDayMealCost()));
				pstmt.setInt(7, Integer.parseInt(dfai.getNumber_men()));
				pstmt.setInt(8, Integer.parseInt(dfai.getNumber_women()));
				pstmt.setInt(9, Integer.parseInt(dfai.getComplexDormId()));

				int rs = pstmt.executeUpdate();

				System.out.println(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (code.equals("6")) { // 생활관_호실
			try {
				data = str;
				st = new StringTokenizer(data, "#");
				DormRoomnumberInfo info = new DormRoomnumberInfo();
				info.setDormId(st.nextToken());
				info.setRoomType(st.nextToken());
				info.setAdmitted(st.nextToken());
				info.setOneTimeServiceCharge(st.nextToken());

				SQL = "update 생활관_호실\n" + "set 호실유형 = (select 코드.코드 from 코드 where 코드.코드구분id = '006' and 코드.코드명 = '"
						+ info.getRoomType() + "'), 수용인원 = " + info.getAdmitted() + ", 1일이용료 = "
						+ info.getOneTimeServiceCharge() + "\n"
						+ "where 생활관_호실.생활관id = (select 코드.코드 from 코드 where 코드.코드구분id = '001' and 코드.코드명 = '"
						+ info.getDormId() + "') ";

				Statement pstmt = conn.createStatement();
				int rs = pstmt.executeUpdate(SQL);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (code.equals("7")) { // 호실상세
			try {
				data = str;
				st = new StringTokenizer(data, "#");
				RoomNumDetailsInfo rndi = new RoomNumDetailsInfo();

				rndi.setDormId(st.nextToken());
				rndi.setRoomNum(st.nextToken());
				rndi.setRoomType(st.nextToken());
				rndi.setNumberOfPeople(st.nextToken());
				rndi.setSex(st.nextToken());

				SQL = "update 호실상세\n" + "set 호실상세.호실유형 = (select 코드.코드 from 코드 where 코드.코드구분id = '006' and 코드.코드명 = '"
						+ rndi.getRoomType() + "'), 수용인원 = " + rndi.getNumberOfPeople() + ", 수용성별 = '" + rndi.getSex()
						+ "'\n" + "where (호실상세.생활관id = (select 코드.코드 from 코드 where 코드.코드구분id = '001' and 코드.코드명 = '"
						+ rndi.getDormId() + "')) and 호실상세.호실번호 = " + rndi.getRoomNum() + ";";

				Statement pstmt = conn.createStatement();
				int rs = pstmt.executeUpdate(SQL);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (code.equals("8")) { // 신청 -> 선발
			try {
                Statement stmt = conn.createStatement();
                int rs;
                SQL = "insert into 선발\n" +
                        "select  학생id, 생활관복합id, 기숙기간\n" +
                        "from 임시신청테이블\n" +
                        "where 기숙기간 = '1년' and 점수합계 is null; \n";
                rs = stmt.executeUpdate(SQL);
                SQL = "insert into 선발\n" +
                        "select  임시신청테이블.학생id, 임시신청테이블.생활관복합id, 임시신청테이블.기숙기간\n" +
                        "from 임시신청테이블 left join 선발 on 임시신청테이블.학생id = 선발.학생id\n" +
                        "where 선발.학생id is null and 임시신청테이블.기숙기간 = '1학기' and 임시신청테이블.지망번호 = 1 and 점수합계 is null;\n";
                rs = stmt.executeUpdate(SQL );
                SQL = "insert into 선발\n" +
                        "\n" +
                        "select 신청.학생id, 신청.생활관복합id, 신청.기숙기간\n" +
                        "from (select row_number() over(partition by 기숙기간,생활관복합id, 지망번호 order by 점수합계 desc) as tempNumber, 임시신청자.학생id, 임시신청자.생활관복합id, 임시신청자.기숙기간 from (select 학생id, 생활관복합id, 기숙기간, 지망번호, 점수합계 from 임시신청테이블 where 기숙기간 = '1년' and 지망번호 = " + 1 + ") as 임시신청자 left outer join 선발 on 임시신청자.학생id = 선발.학생id where 선발.학생id is null) as 신청, 생활관_신청\n" +
                        "where 신청.기숙기간 = 생활관_신청.기숙기간 and 신청.생활관복합id = 생활관_신청.생활관복합id and 신청.tempNumber <= 생활관_신청.모집인원_남 + 생활관_신청.모집인원_여 - (select count(*) from 선발 where 선발.생활관복합id = 생활관_신청.생활관복합id);\n";
                rs = stmt.executeUpdate(SQL);
                for (int i = 0; i < 3; i++) {
                    SQL = "insert into 선발\n" +
                            "\n" +
                            "select 신청.학생id, 신청.생활관복합id, 신청.기숙기간\n" +
                            "from (select row_number() over(partition by 기숙기간,생활관복합id, 지망번호 order by 점수합계 desc) as tempNumber, 임시신청자.학생id, 임시신청자.생활관복합id, 임시신청자.기숙기간 from (select 학생id, 생활관복합id, 기숙기간, 지망번호, 점수합계 from 임시신청테이블 where 기숙기간 = '1학기' and 지망번호 = " + Integer.toString(i + 1) + ") as 임시신청자 left outer join 선발 on 임시신청자.학생id = 선발.학생id where 선발.학생id is null) as 신청, 생활관_신청\n" +
                            "where 신청.기숙기간 = 생활관_신청.기숙기간 and 신청.생활관복합id = 생활관_신청.생활관복합id and 신청.tempNumber <= 생활관_신청.모집인원_남 + 생활관_신청.모집인원_여 - (select count(*) from 선발 where 선발.생활관복합id = 생활관_신청.생활관복합id);\n";
                    rs = stmt.executeUpdate(SQL);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
		} else if (code.equals("9")) { // 선발 -> 입금
			try {
				SQL = "insert into 입금 (`학생id`, `입사비용`) \n" + "select 신청.학생id,\n"
						+ "case when 신청.식사구분코드 = 10 then 생활관_신청.비용_7일식 + 생활관_신청.생활관사용료 + 생활관_신청.기타공공요금\n"
						+ "when 신청.식사구분코드 = 20 then 생활관_신청.비용_5일식 + 생활관_신청.생활관사용료 + 생활관_신청.기타공공요금\n" + "else 0\n"
						+ "end as 생활관비\n" + "from 신청, 선발, 생활관_신청\n"
						+ "where 신청.학생id = 선발.학생id and 신청.기숙기간 = 선발.기숙기간 and 신청.생활관복합id = 선발.생활관복합id and 선발.생활관복합id = 생활관_신청.생활관복합id and 신청.기숙기간 = 생활관_신청.기숙기간\n"
						+ "group by 신청.학생id;\n";

				Statement stmt = conn.createStatement();
				stmt.execute(SQL);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (code.equals("10")) { // 그냥 임시로 넣은거......
			try {
				SQL = "update 입금 set 관비납부여부 = \"납부\";";
				Statement stmt = conn.createStatement();
				stmt.execute(SQL);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (code.equals("11")) { // 미납 -> 납부 (입금)
			 data = str;
	         st = new StringTokenizer(data, "#"); 
	         
	         while (st.hasMoreTokens()) { // ? code data
	            try {
	               SQL = "update 입금 set 관비납부여부 = ? where 학생id = ?;";
	               PreparedStatement pstmt = conn.prepareStatement(SQL);
				   pstmt.setString(2, st.nextToken());
				   pstmt.setString(1, st.nextToken());
	               int rsl = pstmt.executeUpdate();

					System.out.println(rsl);
	            } catch (SQLException e) {
	               e.printStackTrace();
	            }
	         }
	      }
		else if (code.equals("12")) { // 입금 -> 결핵진단서
			try {
				SQL = "insert into 결핵진단서 (`학생id`)\n" + "select 학생id from 입금 where 관비납부여부 = '납부';\n";
				Statement stmt = conn.createStatement();
				stmt.execute(SQL);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (code.equals("13")) { // 결핵진단서 -> 배정 
			try {
				SQL = "insert into 배정\r\n" + 
						"\r\n" + 
						"select 선발직전.학생id, case when (선발직전.생활관복합id = 10 or 선발직전.생활관복합id = 11 and 선발직전.num <= 생활관_호실.수용인원) then 20\r\n" + 
						"when (선발직전.생활관복합id = 10 or 선발직전.생활관복합id = 11 and 선발직전.num > 생활관_호실.수용인원) then 10\r\n" + 
						"when (선발직전.생활관복합id = 30 or 선발직전.생활관복합id = 31 or 선발직전.생활관복합id = 32) then 30 \r\n" + 
						"when (선발직전.생활관복합id = 60 and 선발직전.num <= 생활관_호실.수용인원) then 70\r\n" + 
						"when (선발직전.생활관복합id = 40 or 선발직전.생활관복합id = 41) then 40\r\n" + 
						"when (선발직전.생활관복합id = 80 or 선발직전.생활관복합id = 81 or 선발직전.생활관복합id = 82 or 선발직전.생활관복합id = 83) then 80\r\n" + 
						"else 선발직전.생활관복합id end as 생활관id, 0\r\n" + 
						"\r\n" + 
						"from (select row_number() over(partition by 선발.생활관복합id) as num ,선발.학생id, 선발.생활관복합id \r\n" + 
						"from 결핵진단서, 선발 where 결핵진단서.제출일시 is not null and year(결핵진단서.진단일시) = 2019 and 결핵진단서.학생id = 선발.학생id) as 선발직전, 생활관_호실, 생활관\r\n" + 
						"where 생활관.생활관id = 생활관_호실.생활관id and 선발직전.생활관복합id = 생활관.생활관복합id\r\n" + 
						"\r\n" + 
						"group by 선발직전.학생id;";
				Statement stmt = conn.createStatement();
				stmt.execute(SQL);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (code.equals("15")) { // 입사서약서
			try {
				data = str;
				DormOathInfo dormOath = new DormOathInfo();
				Statement stmt = conn.createStatement();
				st = new StringTokenizer(data, "#");
				
				dormOath.setStudentId(st.nextToken());
				dormOath.setAgreement1(st.nextToken());
				dormOath.setAgreement2(st.nextToken());
				System.out.println(dormOath.getStudentId() + dormOath.getAgreement1() + dormOath.getAgreement2());
				
				SQL = "delete from 입사서약 where 학생id = '" + dormOath.getStudentId() + "';";
				stmt.execute(SQL);

				SQL = "insert into 입사서약 values ('" + dormOath.getStudentId() + "', '" + dormOath.getAgreement1()
						+ "', '" + dormOath.getAgreement2() + "');";
				stmt.execute(SQL);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (code.equals("16")) {

			System.out.println(code);

			data = str;
			st = new StringTokenizer(data, "#"); // 20180650#2019-01-23
			StringTokenizer st2;
			while (st.hasMoreTokens()) {
				try {
					SQL = "update 결핵진단서 set 진단일시=? where 학생id=?;";

					PreparedStatement pstmt = conn.prepareStatement(SQL);

					pstmt.setString(2, st.nextToken());
					java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(st.nextToken());
					Date sqlDate = new Date(utilDate.getTime());
					pstmt.setDate(1, sqlDate);

					int rsl = pstmt.executeUpdate();

					System.out.println(rsl);
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
	      else if (code.equals("17")) {
	          System.out.println(code);

	          data = str;
	          st = new StringTokenizer(data, "#");
	          StringTokenizer st2;
	          while (st.hasMoreTokens()) {
	             try {
	                SQL = "update 배정 set 호실번호=? where 학생id=?;";

	                PreparedStatement pstmt = conn.prepareStatement(SQL);

	                pstmt.setString(2, st.nextToken());
	                pstmt.setInt(1, (Integer.parseInt(st.nextToken()))); 

	                int rsl = pstmt.executeUpdate();
	                System.out.println(rsl);
	                
	             } catch (SQLException sqle) {
	                sqle.printStackTrace();
	             }
	          }

	       }
	      else if (code.equals("18")) { // 미납 -> 납부 (입금)
				 data = str;
		         st = new StringTokenizer(data, "#"); 
		         
		         while (st.hasMoreTokens()) { // ? code data
		            try {
		               SQL = "update 입금\r\n" + 
		               		"set 가상계좌번호 = ?, 관비납부여부 = '미납'\r\n" + 
		               		"where 학생id = ?;";
		               
		               PreparedStatement pstmt = conn.prepareStatement(SQL);
					   pstmt.setString(2, st.nextToken());
					   pstmt.setString(1, st.nextToken());
		               int rsl = pstmt.executeUpdate();

						System.out.println(rsl);
		            } catch (SQLException e) {
		               e.printStackTrace();
		            }
		         }
		      }
		return -100;
	}


	// 학생_생활관호실조회
	public List<CheckRoom> cr_selectONE(String stunum) {
		List<CheckRoom> list = new ArrayList<CheckRoom>();
		PreparedStatement pstmt = null;

		CheckRoom crinfo = null;
		try {
			String sql = "select 식사코드.코드명 as 식사유형, 코드.코드명 as 생활관, 호실유형코드.코드명 as 호실유형 , 배정.호실번호\r\n"
					+ "     from 신청, 선발, 배정, 코드, 호실상세, (select * from 코드 where 코드구분id = 005) as 식사코드, (select * from 코드 where 코드.코드구분id = 006) as 호실유형코드\r\n"
					+ "     where 신청.학생id = '" + stunum
					+ "' and 신청.학생id = 선발.학생id and 선발.학생id = 배정.학생id and 신청.생활관복합id = 선발.생활관복합id\r\n"
					+ "     and 신청.기숙기간 = 선발.기숙기간 and 신청.식사구분코드 = 식사코드.코드 and (코드.코드구분id = 001 and 배정.생활관id = 코드.코드)\r\n"
					+ "     and 배정.생활관id = 호실상세.생활관id and 배정.호실번호 = 호실상세.호실번호 and 호실유형코드.코드 = 호실상세.호실유형;";

			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				crinfo = new CheckRoom();
				crinfo.setMeal(rs.getString("식사유형"));
				crinfo.setDorm(rs.getString("생활관"));
				crinfo.setRoomtype(rs.getString("호실유형"));
				crinfo.setRoomnum(rs.getString("호실번호"));
				list.add(crinfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 관리자_일정조회
	public List<Calender> c_selectAll() {
		List<Calender> list = new ArrayList<Calender>();
		PreparedStatement pstmt = null;

		Calender cinfo = null;
		try {
			String sql = "select * from 일정;";

			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				cinfo = new Calender();
				cinfo.setDayname(rs.getString("일정명"));
				cinfo.setFirstday(rs.getString("시작일시"));
				cinfo.setLastday(rs.getString("종료일시"));
				list.add(cinfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 관리자_금액조회
	public List<Money> m_selectAll() {
		List<Money> list = new ArrayList<Money>();
		PreparedStatement pstmt = null;

		Money minfo = null;
		try {
			String sql = "select 코드.코드명 as 생활관, 생활관_신청.기숙기간, 일정.시작일시, 일정.종료일시, (생활관사용료 + 기타공공요금 + 비용_7일식) as 총비용_7일식적용, (생활관사용료 + 기타공공요금 + 비용_5일식) as 총비용_5일식적용,(생활관사용료 + 기타공공요금) as 총비용_식사안함적용\r\n"
					+ "from 생활관_신청, 코드, 일정\r\n"
					+ "where 코드.코드구분id = 002 and 코드.코드 = 생활관_신청.생활관복합id and 일정.일정명 = 생활관_신청.기숙기간;";

			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				minfo = new Money();
				minfo.setDorm(rs.getString("생활관"));
				minfo.setPeriod(rs.getString("기숙기간"));
				minfo.setFirstday(rs.getString("시작일시"));
				minfo.setLastday(rs.getString("종료일시"));
				minfo.setSdaymeal(rs.getString("총비용_7일식적용"));
				minfo.setFdaymeal(rs.getString("총비용_5일식적용"));
				minfo.setNomeal(rs.getString("총비용_식사안함적용"));
				list.add(minfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 관리자_입사자 목록 조회(합격자)
	public List<Recruiter> r_selectAll() { 
		List<Recruiter> list = new ArrayList<Recruiter>();
		PreparedStatement pstmt = null;

		Recruiter rinfo = null;
		try {
			String sql = "select 학생.학생id, 사용자.성명, 생활관코드.코드명 as 생활관, 임시신청테이블.지망번호, 선발.기숙기간, 학생.주민등록번호, 학생.성별, 임시신청테이블.성적, 임시신청테이블.거리가산점, 임시신청테이블.점수합계  \r\n"
					+ "from 사용자, 학생, 임시신청테이블, 선발, (select * from 코드 where 코드.코드구분id = 002) as 생활관코드\r\n"
					+ "where 사용자.id = 선발.학생id and 학생.학생id = 선발.학생id and 임시신청테이블.학생id = 선발.학생id and 임시신청테이블.생활관복합id = 선발.생활관복합id and 임시신청테이블.기숙기간 = 선발.기숙기간\r\n"
					+ "and 선발.생활관복합id = 생활관코드.코드;\r\n" + "";

			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				rinfo = new Recruiter();
				rinfo.setStudentid(rs.getString("학생id"));
				rinfo.setStudentname(rs.getString("성명"));
				rinfo.setDorm(rs.getString("생활관"));
				rinfo.setDesire(rs.getString("지망번호"));
				rinfo.setPeriod(rs.getString("기숙기간"));
				rinfo.setRrn(rs.getString("주민등록번호"));
				rinfo.setSex(rs.getString("성별"));
				rinfo.setGrade(rs.getString("성적"));
				rinfo.setDistanceaddpoint(rs.getString("거리가산점"));
				rinfo.setScoresum(rs.getString("점수합계"));
				list.add(rinfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 관리자_입사선발자 목록 조회(신청자)
		public List<Applicant> a_selectAll() {
			List<Applicant> list = new ArrayList<Applicant>();
			Statement stmt = null;
			PreparedStatement pstmt = null;
			String sql;

			Applicant ainfo = null;
			try {
				sql = "Create temporary table 임시신청테이블 select 신청자및가산점부여.학생id, 신청자및가산점부여.기숙기간, 신청자및가산점부여.생활관복합id, 신청자및가산점부여.지망번호, 신청자및가산점부여.식사구분코드, 최종성적.성적, 신청자및가산점부여.가산점 as 거리가산점, 최종성적.성적+신청자및가산점부여.가산점 as 점수합계\r\n"
						+ "from (select 신청.학생id, 기숙기간, 생활관복합id, 지망번호, 식사구분코드, 지역거리가산점.가산점 \r\n"
						+ "from(select 간편화주소.학생id, 가산점 from(select 상세주소.학생id, \r\n"
						+ "case when 학생주소 like '%제주%' then '제주' \r\n" + "when 상세주소.학생주소 like '%울릉%' then '울릉군'  \r\n"
						+ "when 상세주소.학생주소 like '%서울%' then '서울'\r\n" + "when 상세주소.학생주소 like '%인천%' then '인천'\r\n"
						+ "when 상세주소.학생주소 like '%경기%' then '경기'\r\n" + "when 상세주소.학생주소 like '%강원%' then '강원'\r\n"
						+ "when 상세주소.학생주소 like '%충청%' then '충청'\r\n" + "when 상세주소.학생주소 like '%광주%' then '광주'\r\n"
						+ "when 상세주소.학생주소 like '%전라%' then '전라'\r\n" + "when 상세주소.학생주소 like '%세종%' then '세종'\r\n"
						+ "when 상세주소.학생주소 like '%대전%' then '대전'\r\n" + "when 상세주소.학생주소 like '%부산%' then '부산'\r\n"
						+ "when 상세주소.학생주소 like '%울산%' then '울산'\r\n" + "when 상세주소.학생주소 like '%경상남도%' then '경남'\r\n"
						+ "when 상세주소.학생주소 like '%대구%' then '대구'\r\n" + "when 상세주소.학생주소 like '%구미%' then '구미'\r\n"
						+ "when 상세주소.학생주소 is null then null\r\n" + "else '경북' end as 학생주소,\r\n"
						+ "case when 상세주소.보호자주소 like '%제주%' then '제주' \r\n"
						+ "when 상세주소.보호자주소 like '%울릉군%' then '울릉군'  \r\n" + "when 상세주소.보호자주소 like '%서울%' then '서울'\r\n"
						+ "when 상세주소.보호자주소 like '%인천%' then '인천'\r\n" + "when 상세주소.보호자주소 like '%경기%' then '경기'\r\n"
						+ "when 상세주소.보호자주소 like '%강원%' then '강원'\r\n" + "when 상세주소.보호자주소 like '%충청%' then '충청'\r\n"
						+ "when 상세주소.보호자주소 like '%광주%' then '광주'\r\n" + "when 상세주소.보호자주소 like '%전라%' then '전라'\r\n"
						+ "when 상세주소.보호자주소 like '%세종%' then '세종'\r\n" + "when 상세주소.보호자주소 like '%대전%' then '대전'\r\n"
						+ "when 상세주소.보호자주소 like '%부산%' then '부산'\r\n" + "when 상세주소.보호자주소 like '%울산%' then '울산'\r\n"
						+ "when 상세주소.보호자주소 like '%경상남도%' then '경남'\r\n" + "when 상세주소.보호자주소 like '%대구%' then '대구'\r\n"
						+ "when 상세주소.보호자주소 like '%구미%' then '구미'\r\n" + "when 상세주소.보호자주소 is null then null\r\n"
						+ "else '경북' end as 보호자주소\r\n"
						+ "from (select 학생.학생id, 학생주소, 보호자주소 from 학생, 신청 where 학생.학생id = 신청.학생id) as 상세주소 group by 상세주소.학생id) as 간편화주소, 거리가산점 \r\n"
						+ "where if(간편화주소.보호자주소 is not null, 간편화주소.보호자주소 = 지역명, 간편화주소.학생주소 = 지역명)) as 지역거리가산점, 신청\r\n"
						+ "where 지역거리가산점.학생id = 신청.학생id) as 신청자및가산점부여\r\n"
						+ "left join (select 최근1년평균.학생id, round(sum(최근1년평균.성적) / count(최근1년평균.학생id) , 2) as 성적\r\n"
						+ "from (select * from (select * from (select 성적.학생id,개설년도,개설학기, round(sum(학점 * 평점) / sum(학점),2) as 성적, row_number() over(partition by 성적.학생id order by 개설년도, 개설학기 asc) as 최신순 from 성적\r\n"
						+ "group by 성적.학생id, 개설년도, 개설학기) as result order by result.학생id, result.개설년도 desc, result.개설학기 desc) as 최근1년 where 최근1년.최신순 = 1 || 최근1년.최신순 = 2) as 최근1년평균\r\n"
						+ "group by 최근1년평균.학생id) as 최종성적\r\n" + "on 최종성적.학생id = 신청자및가산점부여.학생id\r\n"
						+ "order by 신청자및가산점부여.기숙기간, 신청자및가산점부여.생활관복합id, 신청자및가산점부여.지망번호, 점수합계 desc;\r\n";

				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				
				
				
				sql = "select 학생.학생id, 사용자.성명, 생활관코드.코드명 as 생활관, 임시신청테이블.지망번호, 신청.기숙기간, 학생.주민등록번호, 학생.성별, 임시신청테이블.성적, 임시신청테이블.거리가산점, 임시신청테이블.점수합계 from 사용자, 학생, 임시신청테이블, 신청, (select * from 코드 where 코드.코드구분id = 002) as 생활관코드 where 사용자.id = 신청.학생id and 학생.학생id = 신청.학생id and 임시신청테이블.학생id = 신청.학생id and 임시신청테이블.생활관복합id = 신청.생활관복합id and 임시신청테이블.기숙기간 = 신청.기숙기간 and 신청.생활관복합id = 생활관코드.코드;";

				pstmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					ainfo = new Applicant();
					ainfo.setStudentid(rs.getString("학생id"));
					ainfo.setStudentname(rs.getString("성명"));
					ainfo.setDorm(rs.getString("생활관"));
					ainfo.setDesire(rs.getString("지망번호"));
					ainfo.setPeriod(rs.getString("기숙기간"));
					ainfo.setRrn(rs.getString("주민등록번호"));
					ainfo.setSex(rs.getString("성별"));
					ainfo.setGrade(rs.getString("성적"));
					ainfo.setDistanceaddpoint(rs.getString("거리가산점"));
					ainfo.setScoresum(rs.getString("점수합계"));
					list.add(ainfo);
				}
				
				//stmt.executeUpdate("drop table 임시신청테이블;");
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (stmt != null && !stmt.isClosed())
						stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return list;
		}


	// 관리자_결핵진단서조회
	public List<Tuber> t_selectAll() {
		List<Tuber> list = new ArrayList<Tuber>();
		PreparedStatement pstmt = null;

		Tuber tinfo = null;
		try {
			String sql = "select * from 결핵진단서 where 결핵진단서.제출일시 is not null and year(결핵진단서.진단일시) = 2020;";

			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				tinfo = new Tuber();
				tinfo.setStudentid(rs.getString("학생id"));
				tinfo.setFilename(rs.getString("파일명"));
				tinfo.setSubmitdate(rs.getString("제출일시"));
				tinfo.setDiagnosisdate(rs.getString("진단일시"));
				list.add(tinfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 학생_생활관신청확인 조회
	public List<ApplicationConfirm> ac_selectAll() {
		List<ApplicationConfirm> list = new ArrayList<ApplicationConfirm>();
		PreparedStatement pstmt = null;

		ApplicationConfirm acinfo = null;
		try {
			String sql = "select 코드.코드명 as 생활관, 선발.기숙기간, 식사코드.코드명 as 식사유형 from 신청, 선발, 코드, (select * from 코드 where 코드구분id = 005) as 식사코드 where 선발.학생id = '20180764' and 신청.학생id = 선발.학생id and 신청.생활관복합id = 선발.생활관복합id and 신청.기숙기간 = 선발.기숙기간 and (코드.코드구분id = 002 and 코드.코드 = 선발.생활관복합id) and 신청.식사구분코드 = 식사코드.코드;";

			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				acinfo = new ApplicationConfirm();
				acinfo.setDorm(rs.getString("생활관"));
				acinfo.setPeriod(rs.getString("기숙기간"));
				acinfo.setMeal(rs.getString("식사유형"));
				list.add(acinfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 학생_생활관호실조회
	public List<CheckRoom> cr_selectAll() {
		List<CheckRoom> list = new ArrayList<CheckRoom>();
		PreparedStatement pstmt = null;

		CheckRoom crinfo = null;
		try {
			String sql = "select 식사코드.코드명 as 식사유형, 코드.코드명 as 생활관, 호실유형코드.코드명 as 호실유형 , 배정.호실번호, 배정.침대번호\r\n"
					+ "from 신청, 선발, 배정, 코드, 호실상세, (select * from 코드 where 코드구분id = 005) as 식사코드, (select * from 코드 where 코드.코드구분id = 006) as 호실유형코드\r\n"
					+ "where 신청.학생id = '20180457' and 신청.학생id = 선발.학생id and 선발.학생id = 배정.학생id and 신청.생활관복합id = 선발.생활관복합id\r\n"
					+ "and 신청.기숙기간 = 선발.기숙기간 and 신청.식사구분코드 = 식사코드.코드 and (코드.코드구분id = 001 and 배정.생활관id = 코드.코드)\r\n"
					+ "and 배정.생활관id = 호실상세.생활관id and 배정.호실번호 = 호실상세.호실번호 and 호실유형코드.코드 = 호실상세.호실유형;";

			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				crinfo = new CheckRoom();
				crinfo.setMeal(rs.getString("식사유형"));
				crinfo.setDorm(rs.getString("생활관"));
				crinfo.setRoomtype(rs.getString("호실유형"));
				crinfo.setRoomnum(rs.getString("호실번호"));
				list.add(crinfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 생활관 성별구별 위해
	public List<Dorminquire> d_selectAll() {
		List<Dorminquire> list = new ArrayList<Dorminquire>();
		PreparedStatement pstmt = null;

		Dorminquire dinfo = null;
		try {
			String sql = "select 생활관_정보.코드명 as 생활관명, 생활관_정보.식사안함제공여부, 생활관_정보.비용_7일식, 생활관_정보.비용_5일식, 생활관_정보.생활관사용료, 생활관_정보.기타공공요금\r\n"
					+ "\r\n"
					+ "from (select 코드.코드명, 급식실.식사안함제공여부, 생활관_신청.비용_7일식, 생활관_신청.비용_5일식, 생활관_신청.생활관사용료, 생활관_신청.기타공공요금, 생활관_신청.모집인원_남, 생활관_신청.모집인원_여 from 생활관_신청, 급식실, 코드 where 코드.코드구분id = '002' and 생활관_신청.생활관복합id = 코드.코드 and 생활관_신청.급식실id = 급식실.급식실id\r\n"
					+ ") as 생활관_정보, (select * from 학생 where 학생id = '20150001') as 학생\r\n" + "\r\n"
					+ "where if(학생.성별 = '남', 생활관_정보.모집인원_여 = 0, 생활관_정보.모집인원_남 = 0);";

			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				dinfo = new Dorminquire();
				dinfo.setDorm(rs.getString("생활관"));
				dinfo.setNomealprovided(rs.getString("식사안함제공여부"));
				dinfo.setSmeal(rs.getString("비용_7일식"));
				dinfo.setFmeal(rs.getString("비용_5일식"));
				dinfo.setDormfee(rs.getString("생활관사용료"));
				dinfo.setOthercharge(rs.getString("기타공공요금"));
				list.add(dinfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 생활관 성별구별 위해
	public List<Dorminquire> d_selectONE(String stunum) {
		List<Dorminquire> list = new ArrayList<Dorminquire>();
		PreparedStatement pstmt = null;

		Dorminquire dinfo = null;
		try {
			String sql = "select 생활관_정보.코드명 as 생활관명, 생활관_정보.식사안함제공여부, 생활관_정보.비용_7일식, 생활관_정보.비용_5일식, 생활관_정보.생활관사용료, 생활관_정보.기타공공요금\r\n"
					+ "\r\n"
					+ "from (select 코드.코드명, 급식실.식사안함제공여부, 생활관_신청.비용_7일식, 생활관_신청.비용_5일식, 생활관_신청.생활관사용료, 생활관_신청.기타공공요금, 생활관_신청.모집인원_남, 생활관_신청.모집인원_여 from 생활관_신청, 급식실, 코드 where 코드.코드구분id = '002' and 생활관_신청.생활관복합id = 코드.코드 and 생활관_신청.급식실id = 급식실.급식실id\r\n"
					+ ") as 생활관_정보, (select * from 학생 where 학생id = '" + stunum + "') as 학생\r\n" + "\r\n"
					+ "where if(학생.성별 = '남', 생활관_정보.모집인원_여 = 0, 생활관_정보.모집인원_남 = 0);";

			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				dinfo = new Dorminquire();
				dinfo.setDorm(rs.getString("생활관"));
				dinfo.setNomealprovided(rs.getString("식사안함제공여부"));
				dinfo.setSmeal(rs.getString("비용_7일식"));
				dinfo.setFmeal(rs.getString("비용_5일식"));
				dinfo.setDormfee(rs.getString("생활관사용료"));
				dinfo.setOthercharge(rs.getString("기타공공요금"));
				list.add(dinfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public List<Print> p_selectONE(String stunum) {

		System.out.println(stunum);
		List<Print> list = new ArrayList<Print>();
		PreparedStatement pstmt = null;
		Print pinfo = null;

		try {
			String sql = "select 사용자.id as 학생id, 사용자.성명, 코드.코드명 as 생활관명, 신청.기숙기간, 식사코드.코드명 as 식사유형, 생활관_신청.생활관사용료,\r\n"
					+ "case when 신청.식사구분코드 = 10 then 생활관_신청.비용_7일식 when 신청.식사구분코드 = 20 then 생활관_신청.비용_5일식\r\n"
					+ "else 0 end as 급식비, 생활관_신청.기타공공요금, 입금.입사비용, 입금.가상계좌번호\r\n"
					+ "from 선발, 신청, 생활관_신청, 코드, 입금, 사용자, (select * from 코드 where 코드구분id = 005) as 식사코드  where 선발.학생id = 신청.학생id\r\n"
					+ "and 선발.학생id = 사용자.id and 코드.코드구분id = 002 and 선발.생활관복합id = 코드.코드\r\n"
					+ "and 선발.생활관복합id = 생활관_신청.생활관복합id and 입금.학생id = 선발.학생id and 선발.기숙기간 = 신청.기숙기간\r\n"
					+ "and 선발.생활관복합id = 신청.생활관복합id\r\n" + "and 선발.학생id = '" + stunum + "' and 신청.식사구분코드=식사코드.코드;";

			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				pinfo = new Print();
				System.out.println("출력");
				pinfo.setStudentid(rs.getString("학생id"));
				pinfo.setStudentname(rs.getString("성명"));
				pinfo.setDorm(rs.getString("생활관명"));
				pinfo.setMeal(rs.getString("식사유형"));
				pinfo.setPeriod(rs.getString("기숙기간"));
				pinfo.setDormfee(rs.getString("생활관사용료"));
				pinfo.setMealfee(rs.getString("급식비"));
				pinfo.setOthercharge(rs.getString("기타공공요금"));
				pinfo.setTotalfee(rs.getString("입사비용"));
				pinfo.setAccountnum(rs.getString("가상계좌번호"));
					// 10
				list.add(pinfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public void UpdateSchedule(String schName, String startTime, String finishTime) {
		String SQL = "update 일정 SET 일정명 =?, 시작일시= ?, 종료일시 =? WHERE 일정명= '" + schName + "';";
		try { // 이미 신청한 내역 있으면 삭제하고 insert 수행 해야함
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, schName);
			pstmt.setString(2, startTime);
			pstmt.setString(3, finishTime);
			int r = pstmt.executeUpdate();
			System.out.println(r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getFileName(String studentNum) {
		try {
			String SQL = "select 파일명 from 결핵진단서 where 학생id=?";
			PreparedStatement pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1, studentNum);

			ResultSet rs = pstmt.executeQuery();
			rs.next();
			System.out.println("SQL" + rs.getString(1).toString());
			return rs.getString(1).toString();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return null;
	}

	public String showAdminDorApplication() { // 서버가실행
		try {
			stat = conn.createStatement();

			String str = "select d.학생id, e.기숙기간, a.코드명, e.지망번호, b.코드명 from 코드 a, 코드 b, 생활관_신청 c, 학생 d, 신청 e where a.코드구분id=002 and a.코드=c.생활관복합id and c.생활관복합id=e.생활관복합id and b.코드구분id=005 and b.코드=e.식사구분코드 and d.학생id=e.학생id";
			rs = stat.executeQuery(str);

			String result = "", tempstr;

			while (rs.next()) {
				result += rs.getString(1) + "#";
				result += rs.getString(2) + "#";
				result += rs.getString(3) + "#";
				result += rs.getString(4) + "#";
				result += rs.getString(5) + "#";
			}

			return result;

		} catch (SQLException e) {
			System.out.println("얻어진거 없음");
			return null;
		}
	}

	public String showStudentTuberCertificate(String StudentNum) { // 서버가실행 ...
		try {
			stat = conn.createStatement();

			String str = "select 제출일시, 진단일시 from 결핵진단서 where 학생id = '" + StudentNum + "'";
			rs = stat.executeQuery(str);

			String result = "", tempstr;

			// 제출일시와 진단일시
			rs.next(); // 
			System.out.println(rs.getString(1));
			result = rs.getString(1) + "#" + rs.getString(2);
			
			return result;
		} catch (SQLException e) {
			System.out.println("얻어진거 없음");
			return null;
		}
	}

	public String showUpdateSchedule() { // 서버가실행
		try {
			stat = conn.createStatement();
			String str = "select a.일정명, a.시작일시, a.종료일시 from 일정 a order by 일정구분코드 asc;";

			rs = stat.executeQuery(str);

			String result = "", tempstr;

			while (rs.next()) {
				result += rs.getString(1) + "#";
				result += rs.getString(2) + "#";
				result += rs.getString(3) + "#";

			}
			return result;
		} catch (SQLException e) {
			System.out.println("얻어진거 없음");
			return null;
		}
	}

	public String showUpdateDorForApplication() { // 서버가실행
		try {
			stat = conn.createStatement();
			String str = "select a.코드명, b.코드명, c.기숙기간, c.생활관사용료, c.기타공공요금, c.비용_7일식, "
					+ "c.비용_5일식, c.모집인원_남, c.모집인원_여 from 코드 a, 코드 b, 생활관_신청 c "
					+ "where a.코드구분id=002 and a.코드=c.생활관복합id and b.코드구분id=003 and b.코드=c.급식실id;";

			rs = stat.executeQuery(str);

			String result = "";

			while (rs.next()) {
				result += rs.getString(1) + "#";
				result += rs.getString(2) + "#";
				result += rs.getString(3) + "#";
				result += rs.getInt(4) + "#";
				result += rs.getInt(5) + "#";
				result += rs.getInt(6) + "#";
				result += rs.getInt(7) + "#";
				result += rs.getInt(8) + "#";
				result += rs.getInt(9) + "#";
			}

			return result;
		} catch (SQLException e) {
			System.out.println("얻어진거 없음");
			return null;
		}
	}

	public String AdminDeposit() { // 5. 관리자_입금 여부 조회
		try {
			stat = conn.createStatement();
			String str = "select * from 입금";
			rs = stat.executeQuery(str);

			String result = "";

			while (rs.next()) {
				result += rs.getString(1) + "#";
				result += rs.getInt(2) + "#";
				result += rs.getString(3) + "#";
				result += rs.getString(4) + "#";
			}

			return result;
		} catch (SQLException e) {
			System.out.println("얻어진거 없음");
			return null;
		}
	}

	public String AdminTuberCertificate() { // 6. 관리자_결핵진단서 제출확인 및 업로드
		try {
			stat = conn.createStatement();
			String str = "select * from 결핵진단서";
			rs = stat.executeQuery(str);

			String result = "";

			while (rs.next()) {
				result += rs.getString(1) + "#";
				result += rs.getString(2) + "#";
				result += rs.getString(3) + "#";
				result += rs.getString(4) + "#";
			}

			return result;
		} catch (SQLException e) {
			System.out.println("얻어진거 없음");
			return null;
		}
	}

	public String AdminFinalSelected() { // 7. 관리자_호실 배정
		try {
			stat = conn.createStatement();
			String str = "SELECT b.학생id, a.코드명, b.호실번호 FROM 코드 a, 배정 b WHERE a.코드구분id=001 and a.코드=b.생활관id";
			rs = stat.executeQuery(str);

			String result = "";

			while (rs.next()) {
				result += rs.getString(1) + "#";
				result += rs.getString(2) + "#";
				result += rs.getInt(3) + "#";
			}

			return result;
		} catch (SQLException e) {
			System.out.println("얻어진거 없음");
			return null;
		}
	}

	public String StudentDorInfo() { // 8. 학생_생활관 정보 조회
		try {
			stat = conn.createStatement();
			String str = "select a.코드명, b.코드명, c.기숙기간, c.생활관사용료, c.기타공공요금, c.비용_7일식, c.비용_5일식, c.모집인원_남, c.모집인원_여 from 코드 a, 코드 b, 생활관_신청 c where a.코드구분id=002 and a.코드=c.생활관복합id and b.코드구분id=003 and b.코드=c.급식실id";
			rs = stat.executeQuery(str);

			String result = "";

			while (rs.next()) {
				result += rs.getString(1) + "#";
				result += rs.getString(2) + "#";
				result += rs.getString(3) + "#";
				result += rs.getInt(4) + "#";
				result += rs.getInt(5) + "#";
				result += rs.getInt(6) + "#";
				result += rs.getInt(7) + "#";
				result += rs.getInt(8) + "#";
				result += rs.getInt(9) + "#";
			}

			return result;
		} catch (SQLException e) {
			System.out.println("얻어진거 없음");
			return null;
		}
	}
	
	public String CAN_OPEN(String data, String stunum) {
		try {
			String str = "";
			stat = conn.createStatement();
			boolean result ;
			switch(data) {
			case"1":
				str = "select * from 선발 where 학생id = '"+stunum+"'";
				rs = stat.executeQuery(str);
				result = rs.next();
				if(result) {
					str = "O";
				}
				else {
					str = "X";
				}
				break;
			case"2": 
				str = "select * from 결핵진단서 where  학생id = '"+stunum+"'";
				rs = stat.executeQuery(str);
				result = rs.next();
				if(result) {
					str = "O";
				}
				else {
					str = "X";
				}
				break;
			case"3": 
				str = "select * from 배정 where 학생id = '"+stunum+"'";
				rs = stat.executeQuery(str);
				result = rs.next();
				if(result == true) {
					if(rs.getInt(3) == 0) {
						str = "X";
					}
					else {
						str = "O";
					}
				}
				else {
					str = "X";
				}
				break;
			}
			return str;
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	public List<ApplicationConfirm> ac_selectONE(String stunum) {
	      Protocol protocol = new Protocol();
	      List<ApplicationConfirm> list = new ArrayList<ApplicationConfirm>();
	      PreparedStatement pstmt = null;
	      ApplicationConfirm acinfo = null;

	      try {
	         String sql = "select 신청.지망번호, 생활관복합.코드명 as 생활관명, 신청.기숙기간, 식비.코드명 as 식사유형 "
	               + "from (select * from 코드 where 코드구분id = '002') as 생활관복합, (select * from 코드 where 코드구분id = '005') as 식비, 신청 "
	               + "where 생활관복합.코드 = 신청.생활관복합id and 식비.코드 = 신청.식사구분코드 and 신청.학생id = '" + stunum + "';";

	         pstmt = conn.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery(sql);

	         while (rs.next()) {
	            acinfo = new ApplicationConfirm();
	            acinfo.setDesire(rs.getString(1));
	            acinfo.setDorm(rs.getString(2));
	            acinfo.setPeriod(rs.getString(3));
	            acinfo.setMeal(rs.getString(4));
	            list.add(acinfo);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if (pstmt != null && !pstmt.isClosed())
	               pstmt.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return list;
	   }
	public List<ApplicationConfirm1> ac_selectONE1(String stunum) {
	      Protocol protocol = new Protocol();
	      List<ApplicationConfirm1> list = new ArrayList<ApplicationConfirm1>();
	      PreparedStatement pstmt = null;
	      ApplicationConfirm1 ac1info = null;

	      try {
	         String sql = "select 생활관복합.코드명 as 생활관구분, 신청.기숙기간, 식비.코드명 as 식비구분, '합격' as 합격여부, 입금.관비납부여부 as 납부여부\r\n"
	               + "from (select * from 코드 where 코드구분id = '002') as 생활관복합, (select * from 코드 where 코드구분id = '005') as 식비, 신청, 선발, 입금\r\n"
	               + "where 신청.학생id = 선발.학생id and 선발.학생id = 입금.학생id and 생활관복합.코드 = 신청.생활관복합id and 신청.생활관복합id = 선발.생활관복합id and 신청.식사구분코드 = 식비.코드 and 선발.학생id = '"
	               + stunum + "'";

	         pstmt = conn.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery(sql);

	         while (rs.next()) {
	            ac1info = new ApplicationConfirm1();
	            ac1info.setDorm(rs.getString("생활관구분"));
	            ac1info.setPeriod(rs.getString("기숙기간"));
	            ac1info.setMeal(rs.getString("식비구분"));
	            ac1info.setPassORnot(rs.getString("합격여부"));
	            ac1info.setPayment(rs.getString("납부여부"));
	            list.add(ac1info);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if (pstmt != null && !pstmt.isClosed())
	               pstmt.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return list;
	   }
	
	public List<Dorminquire> d_selectAll(String stunum) {
	      List<Dorminquire> list = new ArrayList<Dorminquire>();
	      PreparedStatement pstmt = null;

	      Dorminquire dinfo = null;
	      try {
	         String sql = "select 생활관_정보.코드명 as 생활관명, 생활관_정보.식사안함제공여부, 생활관_정보.비용_7일식, 생활관_정보.비용_5일식, 생활관_정보.생활관사용료, 생활관_정보.기타공공요금\r\n" + 
	               "from (select 코드.코드명, 급식실.식사안함제공여부, 생활관_신청.비용_7일식, 생활관_신청.비용_5일식, 생활관_신청.생활관사용료, 생활관_신청.기타공공요금, 생활관_신청.모집인원_남, 생활관_신청.모집인원_여 from 생활관_신청, 급식실, 코드 where 코드.코드구분id = '002' and 생활관_신청.생활관복합id = 코드.코드 and 생활관_신청.급식실id = 급식실.급식실id) as 생활관_정보,\r\n" + 
	               "(select * from 학생 where 학생id = ?) as 학생정보\r\n" + 
	               "where if(학생정보.성별 = '남', 생활관_정보.모집인원_여 = 0, 생활관_정보.모집인원_남 = 0);";

	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, stunum);
	         
	         ResultSet rs = pstmt.executeQuery();

	         while (rs.next()) {
	            dinfo = new Dorminquire();
	            dinfo.setDorm(rs.getString("생활관명"));
	            dinfo.setNomealprovided(rs.getString("식사안함제공여부"));
	            dinfo.setSmeal(rs.getString("비용_7일식"));
	            dinfo.setFmeal(rs.getString("비용_5일식"));
	            dinfo.setDormfee(rs.getString("생활관사용료"));
	            dinfo.setOthercharge(rs.getString("기타공공요금"));
	            list.add(dinfo);
	         }

	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if (pstmt != null && !pstmt.isClosed())
	               pstmt.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return list;
	   }
}