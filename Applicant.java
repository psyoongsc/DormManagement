package DormitoryProgram;
//관리자_신청자 조회
public class Applicant {
	private String studentid;	//학생id
	private String studentname;	//성명
	private String status;	//학적상태
	private String dorm;	//생활관
	private String period; //기숙기간
	private String roomtype;	//호실유형
	private String rrn;	//주민등록번호
	private String grade; 	//성적
	private String distanceaddpoint; //거리가산점
	private String scoresum;	//점수합계
	private String desire;	//점수합계
	private String sex;
	
	public Applicant(String studentid, String studentname, String status, String dorm, String period, String roomtype, String rrn, String grade, String distanceaddpoint, String scoresum, String desire, String sex) {
		this.studentid = studentid;
		this.studentname = studentname;
		this.status = status;
		this.dorm = dorm;
		this.period = period;
		this.roomtype = roomtype;
		this.rrn = rrn;
		this.grade = grade;
		this.distanceaddpoint = distanceaddpoint;
		this.scoresum = scoresum;
		this.desire = desire;
		this.sex = sex;
	}

	public Applicant() {
	};

	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDorm() {
		return dorm;
	}

	public void setDorm(String dorm) {
		this.dorm = dorm;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}

	public String getRrn() {
		return rrn;
	}

	public void setRrn(String rrn) {
		this.rrn = rrn;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getDistanceaddpoint() {
		return distanceaddpoint;
	}

	public void setDistanceaddpoint(String distanceaddpoint) {
		this.distanceaddpoint = distanceaddpoint;
	}

	public String getScoresum() {
		return scoresum;
	}

	public void setScoresum(String scoresum) {
		this.scoresum = scoresum;
	}
	
	public String getDesire() {
		return desire;
	}

	public void setDesire(String desire) {
		this.desire = desire;
	}
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}