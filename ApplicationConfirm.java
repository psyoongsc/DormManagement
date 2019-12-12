package DormitoryProgram;
//학생_신청확인
public class ApplicationConfirm {
	private String dorm;
	private String period;
	private String meal;
	private String desire;
	
	public ApplicationConfirm(String dorm, String period, String meal) {
		this.dorm = dorm;
		this.period = period;
		this.meal = meal;
	}
	
	public ApplicationConfirm() {};
	
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

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}
	
	public String getDesire() {
		return desire;
	}
	public void setDesire(String desire) {
		this.desire = desire;
	}

}
