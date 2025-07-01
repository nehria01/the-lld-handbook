package ATMSystem;

import java.util.HashMap;
import java.util.Map;

public class Account {
    private String accountNumber;
    private Map<String, Card> cards;
    private double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.cards = new HashMap<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Map<String, Card> getCards() {
        return cards;
    }

    public double getBalance() {
        return balance;
    }

    public synchronized void deposit(double amount) {
        this.balance += amount;
    }

    public synchronized boolean withdraw(double amount) {
        if(balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
