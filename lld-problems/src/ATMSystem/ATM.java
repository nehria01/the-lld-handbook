package ATMSystem;

import ATMSystem.chain.CashDispenseChain;
import ATMSystem.chain.FiveHundredRupeesDispenser;
import ATMSystem.chain.TwoHundredRupeesDispenser;
import ATMSystem.chain.TwoThousandRupeesDispenser;
import ATMSystem.state.ATMState;
import ATMSystem.state.AuthenticatedState;
import ATMSystem.state.HasCardState;
import ATMSystem.state.ReadyState;

public class ATM {
    private Card currentCard;
    private BankService bankService;
    private CashService cashService;

    //STATES
    ATMState readyState;
    ATMState hasCardState;
    ATMState authenticatedState;

    ATMState currentState;
    private static volatile ATM instance = null;

    private ATM() {
        this.readyState = new ReadyState(this);
        this.hasCardState = new HasCardState(this);
        this.authenticatedState = new AuthenticatedState(this);
        this.currentState = readyState;
        this.bankService = new BankService();

        //initialize cash dispense chain
        CashDispenseChain cashDispenseChain1 = new TwoThousandRupeesDispenser(5); //2000 * 5
        CashDispenseChain cashDispenseChain2 = new FiveHundredRupeesDispenser(5); //500 * 5
        CashDispenseChain cashDispenseChain3 = new TwoHundredRupeesDispenser(10); //200 * 10
        cashDispenseChain1.setNext(cashDispenseChain2);
        cashDispenseChain2.setNext(cashDispenseChain3);
        this.cashService = new CashService(cashDispenseChain1);
    }

    public static ATM getInstance() {
        if(instance == null) {
            synchronized (ATM.class) {
                if(instance == null)
                    instance = new ATM();
            }
        }
        return instance;
    }
    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public ATMState getReadyState() {
        return readyState;
    }

    public ATMState getHasCardState() {
        return hasCardState;
    }

    public ATMState getAuthenticatedState() {
        return authenticatedState;
    }

    public void setCurrentState(ATMState currentState) {
        this.currentState = currentState;
    }

    public boolean authenticateCardPin(String cardPin) {
        return bankService.authenticatePin(this.currentCard, cardPin);
    }

    public void checkBalance() {
        double accountBalance =  bankService.checkBalance(this.currentCard);
        System.out.println("For card :: " + this.currentCard.getCardNumber() + " account balance :: " + accountBalance);
    }

    public void deposit(double amount) {
        bankService.depositAmount(this.currentCard, amount);
        System.out.println("Amount deposited");
        checkBalance();
    }

    public void withdraw(double amount) {
        if (!cashService.canDispenseCash(amount)) {
            throw new RuntimeException("Insufficient cash available in the ATM.");
        }
        //first deduct amount from account
        bankService.withdrawAmount(this.currentCard, amount);

        //dispense cash
        try {
            cashService.dispenseCash(amount);
        } catch (RuntimeException ex) {
            System.out.println("Operation unsuccessful");
            bankService.depositAmount(this.currentCard, amount);
        } finally {
            checkBalance();
        }
    }

    public void setCard(String cardNumber) {
        this.currentCard = bankService.getCard(cardNumber);
    }

    public void insertCard(String cardNumber) {
        this.currentState.insertCard(cardNumber);
    }

    public void enterPin(String pinNumber) {
        this.currentState.insertPin(pinNumber);
    }

    public void selectOperation(OperationType operationType, double... args) {
        this.currentState.selectOperation(operationType, args);
    }

    public void ejectCard() {
        this.currentState.ejectCard();
    }
}
