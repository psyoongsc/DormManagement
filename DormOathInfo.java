package DormitoryProgram;

public class DormOathInfo {
    private String studentId; //학생id
    private String agreement1; //입사서약동의여부
    private String agreement2; //개인정보수집활용동의

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getAgreement1() {
        return agreement1;
    }

    public void setAgreement1(String agreement1) {
        this.agreement1 = agreement1;
    }

    public String getAgreement2() {
        return agreement2;
    }

    public void setAgreement2(String agreement2) {
        this.agreement2 = agreement2;
    }
}
