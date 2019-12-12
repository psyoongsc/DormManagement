package DormitoryProgram;

public class DormitoryInfo {
    private String dormId; //생활관id
    private String complexDormId; //생활관복합id
    private String CafeteriaId; //급식실id

    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId;
    }

    public String getComplexDormId() {
        return complexDormId;
    }

    public void setComplexDormId(String complexDormId) {
        this.complexDormId = complexDormId;
    }

    public String getCafeteriaId() {
        return CafeteriaId;
    }

    public void setCafeteriaId(String cafeteriaId) {
        CafeteriaId = cafeteriaId;
    }
}
