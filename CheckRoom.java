package DormitoryProgram;

public class CheckRoom {
	private String meal;	//식사유형
	private String dorm;	//생활관
	private String roomtype;	//호실유형
	private String roomnum;	//호실번호
	
	public CheckRoom(String meal, String dorm, String roomtype, String roomnum) {
		this.meal = meal;
		this.dorm = dorm;
		this.roomtype = roomtype;
		this.roomnum = roomnum;
	}
	
	public CheckRoom() {};
	
	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	public String getDorm() {
		return dorm;
	}

	public void setDorm(String dorm) {
		this.dorm = dorm;
	}

	public String getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}

	public String getRoomnum() {
		return roomnum;
	}

	public void setRoomnum(String roomnum) {
		this.roomnum = roomnum;
	}

}
