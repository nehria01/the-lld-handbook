package ATMSystem;

import ATMSystem.chain.CashDispenseChain;
import ATMSystem.chain.CashDispenser;

public class CashService {
    private CashDispenseChain cashDispenserChain;

    public CashService(CashDispenseChain cashDispenser) {
        this.cashDispenserChain = cashDispenser;
    }

    public synchronized void dispenseCash(double amount) {
        cashDispenserChain.dispenseCash(amount);
    }

    public synchronized boolean canDispenseCash(double amount) {
        if (amount % 10 != 0) {
            return false;
        }
        return cashDispenserChain.canDispenseAmount(amount);
    }
}
