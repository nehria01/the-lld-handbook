package SimpleATM;

public class ATMTestDrive {
    public static void main(String args[]) {
        int [] intialSeed = {18, 1, 1, 1};
        ATM atm = new ATM(intialSeed);
        int [] res = atm.withdrawMoney(2100);

        if(res.length == 1 && res[0] == -1)
            System.out.println("Withdrawal not possible : insufficient funds");
        else {
            for(int i=0;i<res.length;i++) {
                int val = atm.denominations[i];
                System.out.println("Withdrawing " +res[i] + " notes of val "+val);
            }
        }
    }
}
