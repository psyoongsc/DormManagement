package twoToiletProblem;

public class TwoToilets {
	private String User1;
	private String User2;
	private boolean is_1_Empty;
	private boolean is_2_Empty;
	
	public TwoToilets() {
		is_1_Empty = true;
		is_2_Empty = true;
	}
	
	public synchronized void use(String UserName) throws InterruptedException {
		while( (is_1_Empty == false) && (is_2_Empty == false) ) wait();
		
		if(is_1_Empty == true) {
			User1 = UserName;
			is_1_Empty = false;
			System.out.println("화장실1 : " + User1 + " 사용 중...");
		} else if (is_2_Empty == true) {
			User2 = UserName;
			is_2_Empty = false;
			System.out.println("화장실2 : " + User2 + " 사용 중...");
		}
	}
	
	public synchronized void done(String UserName) {
		if(UserName.equals(User1)) {
			User1 = null;
			is_1_Empty = true;
			System.out.println("화장실1 : " + UserName + " 사용완료, 현재 비어있음");
		} else if(UserName.equals(User2)) {
			User2 = null;
			is_2_Empty = true;
			System.out.println("화장실2 : " + UserName + " 사용완료, 현재 비어있음");
		}
		notifyAll();
	}
}
