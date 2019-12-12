package DormitoryProgram;

//학생_신청확인
public class ApplicationConfirm1 {
   private String dorm;
   private String period;
   private String meal;
   private String passORnot;
   private String payment;
   
   public ApplicationConfirm1(String dorm, String period, String meal, String passORnot, String payment) {
      this.dorm = dorm;
      this.period = period;
      this.meal = meal;
      this.passORnot = passORnot;
      this.payment = payment;
   }
   
   public ApplicationConfirm1() {};
   
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

   public String getMeal() {
      return meal;
   }

   public void setMeal(String meal) {
      this.meal = meal;
   }
   public String getPassORnot() {
      return passORnot;
   }

   public void setPassORnot(String passORnot) {
      this.passORnot = passORnot;
   }

   public String getPayment() {
      return payment;
   }

   public void setPayment(String payment) {
      this.payment = payment;
   }

}