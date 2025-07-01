package ATMSystem.chain;

import java.util.Objects;

public abstract class CashDispenser implements CashDispenseChain{
    private CashDispenseChain nextChain;
    private double noteValue;
    private double noteCount;

    public CashDispenser(double noteValue, double noteCount) {
        this.noteValue = noteValue;
        this.noteCount = noteCount;
    }

    @Override
    public void setNext(CashDispenseChain nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public boolean canDispenseAmount(double amount) {
        if(amount < 0) return false;
        if(amount == 0) return true;

        int currentValueNotesToUse = (int) Math.min(amount / noteValue, noteCount);
        double remainingAmount = (amount - (currentValueNotesToUse * noteValue));
        if(remainingAmount == 0) return true;
        if(Objects.nonNull(this.nextChain))
            return nextChain.canDispenseAmount(remainingAmount);
        return false;
    }

    @Override
    public void dispenseCash(double amount) {
        if(amount >= noteValue) {
            int currentValueNotesToUse = (int) Math.min(amount / noteValue, noteCount);
            double remainingAmount = (amount - (currentValueNotesToUse * noteValue));

            if(currentValueNotesToUse > 0) {
                System.out.println("Dispensing " + currentValueNotesToUse + " " + noteValue+ " note(s)");
                noteCount -= currentValueNotesToUse;
            }
            if(remainingAmount > 0 && Objects.nonNull(nextChain))
                nextChain.dispenseCash(remainingAmount);
        } else if(Objects.nonNull(nextChain))
            nextChain.dispenseCash(amount);
    }
}
