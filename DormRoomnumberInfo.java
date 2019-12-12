package DormitoryProgram;


public class DormRoomnumberInfo {
    private String dormId; //생활관id
    private String roomType; //호실유형
    private String admitted; //수용인원
    private String oneTimeServiceCharge; //1일이용료

    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getAdmitted() {
        return admitted;
    }

    public void setAdmitted(String admitted) {
        this.admitted = admitted;
    }

    public String getOneTimeServiceCharge() {
        return oneTimeServiceCharge;
    }

    public void setOneTimeServiceCharge(String oneTimeServiceCharge) {
        this.oneTimeServiceCharge = oneTimeServiceCharge;
    }
}
