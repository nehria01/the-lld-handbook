package ATM;

import ATM.enums.Operation;
import ATM.machine.ATM;
import ATM.model.Card;
import ATM.service.BankService;

public class ATMTestDrive {
    public static void main(String args[]) {
        BankService bankService = BankService.getInstance();
        ATM atm = new ATM(new int[]{100,200, 500, 2000}, new int[]{18, 1, 1, 1}, bankService);
        Card card = new Card("123", "3348");
        atm.insertCard(card);
        atm.authenticate("3348");
        atm.chooseOperation(Operation.WITHDRAW, 2100);

        atm.insertCard(card);
        atm.authenticate("3348");
        atm.chooseOperation(Operation.VIEW_BALANCE);


        atm.insertCard(card);
        atm.authenticate("3348");
        atm.chooseOperation(Operation.WITHDRAW, 500);
    }
}
