package DormitoryProgram;

public class Calender {
	private String dayname;
	private String firstday;
	private String lastday;

	public Calender(String dayname, String firstday, String lastday) {
		this.dayname = dayname;
		this.firstday = firstday;
		this.lastday = lastday;
	}

	public Calender() {
	};

	public String getDayname() {
		return dayname;
	}

	public void setDayname(String dayname) {
		this.dayname = dayname;
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
}
