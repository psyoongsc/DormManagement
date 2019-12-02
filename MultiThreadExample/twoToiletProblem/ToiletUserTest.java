package twoToiletProblem;

public class ToiletUserTest {

	public static void main(String[] args) {
		TwoToilets twoToilets = new TwoToilets();
		for(int i=1; i<=10; i++) {
			Thread t = new Thread(new ToiletCompetitionThread(twoToilets, "»ç¿ëÀÚ"+i));
			t.start();
		}
	}
}
