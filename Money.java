package DormitoryProgram;

public class Money {
	private String dorm;	//생활관명
	private String period;	//기숙기간
	private String firstday;	//시작일시
	private String lastday;	//종료일시
	private String sdaymeal; //7일식
	private String fdaymeal;	//5일식
	private String nomeal;	//식사안함
	
	public Money(String dorm, String period, String firstday, String lastday, String sdaymeal, String fdaymeal, String nomeal) {
		this.dorm = dorm;
		this.period = period;
		this.firstday = firstday;
		this.lastday = lastday;
		this.sdaymeal = sdaymeal;
		this.fdaymeal = fdaymeal;
		this.nomeal = nomeal;
	}

	public Money() {
	};

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

	public String getFirstday() {
		return firstday;
	}

	public void setFirstday(String firstday) {
		this.firstday = firstday;
	}

	public String getLastday() {
		return lastday;
	}

	public void setLastday(String lastday) {
		this.lastday = lastday;
	}

	public String getSdaymeal() {
		return sdaymeal;
	}

	public void setSdaymeal(String sdaymeal) {
		this.sdaymeal = sdaymeal;
	}

	public String getFdaymeal() {
		return fdaymeal;
	}

	public void setFdaymeal(String fdaymeal) {
		this.fdaymeal = fdaymeal;
	}

	public String getNomeal() {
		return nomeal;
	}

	public void setNomeal(String nomeal) {
		this.nomeal = nomeal;
	}

}