package ATM.model;

public class User {
    public String userId;
    public String accountNumber;
    public String cardNumber;

    public User(String userId, String accountNumber, String cardNumber) {
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
    }
}
