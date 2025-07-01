package ATMSystem.chain;

public interface CashDispenseChain {
    void setNext(CashDispenseChain nextChain);
    boolean canDispenseAmount(double amount);
    void dispenseCash(double amount);
}
