package withoutSynchronized;

public class Account {
	long balance;
	
	public void deposit(long amount) {
		balance = balance + amount;
	}
	
	public void inquiry() {
		System.out.println("�ܾ�: " + balance);
	}
}
