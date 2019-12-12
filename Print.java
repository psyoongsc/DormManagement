package DormitoryProgram;

public class Print {
	private String studentid;	//학생id
	private String studentname;	//성명
	private String dorm;	//합격한 생활관=코드명
	private String period;	//기숙기간
	private String dormfee;	//생활관사용료
	private String mealfee;	//급식비
	private String othercharge;	//기타공공요금
	private String totalfee;	//입사비용
	private String accountnum;	//가상계좌번호
	private String eatday;
	
	public Print(String studentid, String studentname, String dorm, String period, String dormfee, 
			String mealfee, String othercharge, String totalfee, String accountnum, String eatday) {
		this.studentid = studentid;
		this.studentname = studentname;
		this.dorm = dorm;
		this.period = period;
		this.dormfee = dormfee;
		this.mealfee = mealfee;
		this.othercharge = othercharge;
		this.totalfee = totalfee;
		this.accountnum = accountnum;
		this.eatday = eatday;
	}
	
	public Print() {
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

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getDormfee() {
		return dormfee;
	}

	public void setDormfee(String dormfee) {
		this.dormfee = dormfee;
	}

	public String getMealfee() {
		return mealfee;
	}

	public void setMealfee(String mealfee) {
		this.mealfee = mealfee;
	}

	public String getOthercharge() {
		return othercharge;
	}

	public void setOthercharge(String othercharge) {
		this.othercharge = othercharge;
	}

	public String getTotalfee() {
		return totalfee;
	}

	public void setTotalfee(String totalfee) {
		this.totalfee = totalfee;
	}

	public String getAccountnum() {
		return accountnum;
	}

	public void setAccountnum(String accountnum) {
		this.accountnum = accountnum;
	}
	
	public String getMeal() {
		return eatday;
	}

	public void setMeal(String eatday) {
		this.eatday = eatday;
	}
	
}
