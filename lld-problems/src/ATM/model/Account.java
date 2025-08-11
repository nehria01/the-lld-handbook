package ATM.model;

public class Account {
    public String accountNumber;
    public Integer balance;

    public Account(String accountNumber, Integer balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(Integer amount) {
        this.balance += amount;
    }

    public void withdraw(Integer amount) {
        this.balance -= amount;
    }

}
