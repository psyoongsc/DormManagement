package DormitoryProgram;

public class DormitoryForApplicationInfo {
    private String complexDormId; //생활관복합id
    private String CafeteriaId; //급식실id
    private String period; //기숙기간
    private String fee; //생활관사용료
    private String publicUtilityCharges; //기타공공요금
    private String sevenDayMealCost; //비용_7일식
    private String fiveDayMealCost; //비용_5일식
    private String number_men; //복합수용인원_남
    private String number_women; //복합수용인원_여

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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getPublicUtilityCharges() {
        return publicUtilityCharges;
    }

    public void setPublicUtilityCharges(String publicUtilityCharges) {
        this.publicUtilityCharges = publicUtilityCharges;
    }

    public String getSevenDayMealCost() {
        return sevenDayMealCost;
    }

    public void setSevenDayMealCost(String sevenDayMealCost) {
        this.sevenDayMealCost = sevenDayMealCost;
    }

    public String getFiveDayMealCost() {
        return fiveDayMealCost;
    }

    public void setFiveDayMealCost(String fiveDayMealCost) {
        this.fiveDayMealCost = fiveDayMealCost;
    }

    public String getNumber_men() {
        return number_men;
    }

    public void setNumber_men(String number_men) {
        this.number_men = number_men;
    }

    public String getNumber_women() {
        return number_women;
    }

    public void setNumber_women(String number_women) {
        this.number_women = number_women;
    }
}

