package multiThreadedServer;

public class MyThread extends Thread{

	@Override
	public void run() {
		int sum = 0;
		for(int i=1; i<=10; i++) {
			sum = sum + i;
			System.out.println(i + "���ϱ� ���: " + sum);
		}
		
		System.out.println("-------------------------------------");
		System.out.println("1~10 �հ�: " + sum);
	}
}
