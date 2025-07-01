package ATMSystem;

import java.util.Scanner;

public class ATMTestDrive {
    public static void main(String args[]) {
        ATM atm = ATM.getInstance();

        //Happy Flow
        //atm.insertCard("1823820181");
        //atm.enterPin("9811");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== ATM Machine =====");
            System.out.println("1. Insert Card");
            System.out.println("2. Enter PIN");
            System.out.println("3. Check Balance");
            System.out.println("4. Withdraw");
            System.out.println("5. Deposit");
            System.out.println("6. Exit Card");
            System.out.println("7. Quit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter Card Number: ");
                    String cardNumber = scanner.nextLine();
                    atm.insertCard(cardNumber);
                    break;

                case 2:
                    System.out.print("Enter PIN: ");
                    String pin = scanner.nextLine();
                    atm.enterPin(pin);
                    break;

                case 3:
                    atm.selectOperation(OperationType.CHECK_BALANCE);
                    break;

                case 4:
                    System.out.print("Enter Amount to Withdraw: ");
                    int amount = Integer.parseInt(scanner.nextLine());
                    atm.selectOperation(OperationType.WITHDRAW, amount);
                    break;

                case 5:
                    System.out.print("Enter Amount to Deposit: ");
                    int deposit = Integer.parseInt(scanner.nextLine());
                    atm.selectOperation(OperationType.DEPOSIT, deposit);
                    break;

                case 6:
                    atm.ejectCard();
                    break;

                case 7:
                    System.out.println("Exiting ATM... Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

}
