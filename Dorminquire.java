package DormitoryProgram;

public class Dorminquire {
	private String dorm; // 생활관명
	private String nomealprovided; // 식사안함제공여부
	private String smeal; // 7일식비용
	private String fmeal; // 5일식비용
	private String dormfee; // 생활관사용료
	private String othercharge; // 기타공공요금

	public Dorminquire(String dorm, String nomealprovided, String smeal, String fmeal, String dormfee,String othercharge) {
		this.dorm = dorm;
		this.nomealprovided = nomealprovided;
		this.smeal = smeal;
		this.fmeal = fmeal;
		this.dormfee = dormfee;
		this.othercharge = othercharge;
	}

	public Dorminquire() {};

	public String getDorm() {
		return dorm;
	}

	public void setDorm(String dorm) {
		this.dorm = dorm;
	}

	public String getNomealprovided() {
		return nomealprovided;
	}

	public void setNomealprovided(String nomealprovided) {
		this.nomealprovided = nomealprovided;
	}
	
	public String getSmeal() {
		return smeal;
	}

	public void setSmeal(String smeal) {
		this.smeal = smeal;
	}

	public String getFmeal() {
		return fmeal;
	}

	public void setFmeal(String fmeal) {
		this.fmeal = fmeal;
	}

	public String getDormfee() {
		return dormfee;
	}

	public void setDormfee(String dormfee) {
		this.dormfee = dormfee;
	}

	public String getOthercharge() {
		return othercharge;
	}

	public void setOthercharge(String othercharge) {
		this.othercharge = othercharge;
	}
}
