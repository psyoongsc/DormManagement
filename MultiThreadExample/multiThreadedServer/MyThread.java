package multiThreadedServer;

public class MyThread extends Thread{

	@Override
	public void run() {
		int sum = 0;
		for(int i=1; i<=10; i++) {
			sum = sum + i;
			System.out.println(i + "더하기 결과: " + sum);
		}
		
		System.out.println("-------------------------------------");
		System.out.println("1~10 합계: " + sum);
	}
}
