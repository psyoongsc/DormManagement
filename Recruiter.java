package DormitoryProgram;

public class Recruiter {
	private String studentid;	//학생id
	private String studentname;	//학생이름
	private String dorm;	//생활관
	private String desire;	//지망번호
	private String period; //기숙기간
	private String rrn;	//주민등록번호
	private String sex;	//성별
	private String grade;	//성적
	private String distanceaddpoint;	//거리가산점
	private String scoresum;	//점수합계
	
	public Recruiter(String studentid, String studentname, String dorm, String desire, String period, String rrn, String sex, String grade, String distanceaddpoint, String scoresum) {
		this.studentid = studentid;
		this.studentname = studentname;
		this.dorm = dorm;
		this.desire = desire;
		this.period = period;
		this.rrn = rrn;
		this.sex = sex;
		this.grade = grade;
		this.distanceaddpoint = distanceaddpoint;
		this.scoresum = scoresum;
	}

	public Recruiter() {
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

	public String getDorm() {
		return dorm;
	}

	public void setDorm(String dorm) {
		this.dorm = dorm;
	}
	
	public String getDesire() {
		return desire;
	}

	public void setDesire(String desire) {
		this.desire = desire;
	}
	
	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getRrn() {
		return rrn;
	}

	public void setRrn(String rrn) {
		this.rrn = rrn;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

}