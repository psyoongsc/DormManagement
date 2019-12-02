package twoToiletProblem;

public class ToiletCompetitionThread implements Runnable{
	TwoToilets twoToilets;
	String user;
	
	public ToiletCompetitionThread(TwoToilets thisToilets, String username) {
		twoToilets = thisToilets;
		user = username;
	}
	
	@Override
	public void run() {
		try {
			twoToilets.use(user);
			Thread.sleep(5000);
			twoToilets.done(user);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
