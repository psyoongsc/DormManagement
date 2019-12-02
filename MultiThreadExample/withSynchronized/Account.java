package withSynchronized;

public class Account {
	long balance;
	
	public void deposit(long amount) {
		balance = balance + amount;
	}
	
	public void inquiry() {
		System.out.println("ภÜพื: " + balance);
	}
}
