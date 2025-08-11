package ATM.repository;

import ATM.model.Account;
import ATM.model.Card;
import ATM.model.User;

import java.util.HashMap;
import java.util.Map;

public class BankRepository {
    private static BankRepository instance;

    public static BankRepository getInstance() {
        if(instance == null) {
            synchronized (BankRepository.class) {
                instance = new BankRepository();
            }
        }
        return instance;
    }

    public Map<String, User> userMap;
    public Map<String, Account> accountMap;
    public Map<String, Card> cardMap;
    public Map<String, String > cardToAccountIdMap;

    private BankRepository() {
        this.userMap = new HashMap<>();
        this.accountMap = new HashMap<>();
        this.cardMap = new HashMap<>();
        this.cardToAccountIdMap = new HashMap<>();
        initialize();
    }

    private void initialize() {
        User user = new User("userId1", "123456", "123");
        Card card = new Card("123", "3348");
        Account account = new Account("123456", 2500);
        userMap.put(user.userId, user);
        accountMap.put(account.accountNumber, account);
        cardMap.put(card.cardNumber, card);
        cardToAccountIdMap.put(card.cardNumber, account.accountNumber);
    }


}
