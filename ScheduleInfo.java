package DormitoryProgram;

public class ScheduleInfo {
    private String scheduleName; //일정명
    private String startDate; //시작일시
    private String endDate; //종료일시

//    public ScheduleInfo(String scheduleName, String startDate, String endDate){
//        this.scheduleName = scheduleName;
//        this.startDate = startDate;
//        this.endDate = endDate;
//    }

    public String getScheduleName() {
        return scheduleName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
