package ATMSystem;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BankService {
    private Map<String, Card> cardMap = new ConcurrentHashMap<>();
    private Map<String, Account> accountMap = new ConcurrentHashMap<>();
    private Map<Card, Account> cardsToAccountMap = new ConcurrentHashMap<>();

    public BankService() {
        //seed data
        Account account1 = createAccount("AWUSJPOMZL", 5000.00);
        Card card1 = createCard("1823820181", "9811");
        linkCardToAccount(card1, account1);

        Account account2 = createAccount("QUNZHQNZHQ", 12450.00);
        Card card2 = createCard("98272515", "0633");
        linkCardToAccount(card2, account2);
    }

    public Account createAccount(String accountNumber, double accountBalance) {
        Account account = new Account(accountNumber, accountBalance);
        accountMap.put(accountNumber, account);
        return account;
    }

    public Card createCard(String cardNumber, String cardPin) {
        Card card = new Card(cardNumber, cardPin);
        cardMap.put(cardNumber, card);
        return card;
    }

    public void linkCardToAccount(Card card, Account account) {
        account.getCards().put(card.getCardNumber(), card);
        cardsToAccountMap.put(card, account);
    }

    public double checkBalance(Card card) {
        return cardsToAccountMap.get(card).getBalance();
    }

    public void withdrawAmount(Card card, double withdrawalAmount) {
        double accountBalance = cardsToAccountMap.get(card).getBalance();
        if(accountBalance < withdrawalAmount)
            throw new RuntimeException("Insufficient balance !");
        cardsToAccountMap.get(card).withdraw(withdrawalAmount);
    }

    public void depositAmount(Card card, double depositAmount) {
        cardsToAccountMap.get(card).deposit(depositAmount);
    }

    public boolean authenticatePin(Card card, String pin) {
        String pinStoredInSystem = cardMap.get(card.getCardNumber()).getCardPin();
        return pinStoredInSystem.equals(pin);
    }

    public Card getCard(String cardNumber) {
        return cardMap.get(cardNumber);
    }
}
