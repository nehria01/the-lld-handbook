package ATM.service;

import ATM.model.Account;
import ATM.model.Card;
import ATM.repository.BankRepository;

public class BankService {
    private BankRepository bankRepository;
    public static BankService instance;

    public static BankService getInstance() {
        if(instance == null) {
            synchronized (BankService.class) {
                instance = new BankService();
            }
        }
        return instance;
    }

    private BankService() {
        this.bankRepository = BankRepository.getInstance();
    }

    public boolean authenticate(String cardNumber, String pin) {
        Card card = bankRepository.cardMap.get(cardNumber);
        if(card == null || card.pin != pin) {
            System.out.println("CardNumber does not exist");
            return false;
        }
        return true;
    }

    public Integer getBalance(String cardNumber) {
        String accountId = bankRepository.cardToAccountIdMap.get(cardNumber);
        if(accountId == null)
            System.out.println("Cannot find account for card number");
        Account account = bankRepository.accountMap.get(accountId);
        return account.balance;
    }

    public void withdraw(String cardNumber, Integer amount) {
        String accountId = bankRepository.cardToAccountIdMap.get(cardNumber);
        if(accountId == null)
            System.out.println("Cannot find account for card number");
        Account account = bankRepository.accountMap.get(accountId);
        account.withdraw(amount);
    }

    public void deposit(String cardNumber, Integer amount) {
        String accountId = bankRepository.cardToAccountIdMap.get(cardNumber);
        if(accountId == null)
            System.out.println("Cannot find account for card number");
        Account account = bankRepository.accountMap.get(accountId);
        account.deposit(amount);
    }
}
