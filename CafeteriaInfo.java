package DormitoryProgram;

public class CafeteriaInfo {
    String cafeteriaId; //급식실id
    String typeOfMeal1; //식사유형1
    String typeOFMeal2; //식사유형2
    String oneTimeMealCost; //1회식사비용
    String noMealProvision; //식사안함제공여부

//    public CafeteriaInfo(String cafeteriaId, String typeOfMeal1, String typeOFMeal2, String oneTimeMealCost, String noMealProvision){
//        this.cafeteriaId = cafeteriaId;
//        this.typeOfMeal1 = typeOfMeal1;
//        this.typeOFMeal2 = typeOFMeal2;
//        this.oneTimeMealCost = oneTimeMealCost;
//        this.noMealProvision = noMealProvision;
//    }

    public String getCafeteriaId() {
        return cafeteriaId;
    }

    public void setCafeteriaId(String cafeteriaId) {
        this.cafeteriaId = cafeteriaId;
    }

    public String getTypeOfMeal1() {
        return typeOfMeal1;
    }

    public void setTypeOfMeal1(String typeOfMeal1) {
        this.typeOfMeal1 = typeOfMeal1;
    }

    public String getTypeOFMeal2() {
        return typeOFMeal2;
    }

    public void setTypeOFMeal2(String typeOFMeal2) {
        this.typeOFMeal2 = typeOFMeal2;
    }

    public String getOneTimeMealCost() {
        return oneTimeMealCost;
    }

    public void setOneTimeMealCost(String oneTimeMealCost) {
        this.oneTimeMealCost = oneTimeMealCost;
    }

    public String getNoMealProvision() {
        return noMealProvision;
    }

    public void setNoMealProvision(String noMealProvision) {
        this.noMealProvision = noMealProvision;
    }
}

