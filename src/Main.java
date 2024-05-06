import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank(100);

        try {
            bank.load("C:\\Users\\ahmet\\IdeaProjects\\Account.java\\src\\accounts\\saved.txt");
        } catch (Exception e) {
            System.out.println("Unable to load accounts");
        }

        int choice = 0;

        System.out.println("Welcome to BankApp!");
        while (choice != 7) {
            System.out.println();
            System.out.println("\n1. Create new account");
            System.out.println("2. Perform operations in an existing account");
            System.out.println("3. Delete an existing account");
            System.out.println("4. Display the average of all account balances");
            System.out.println("5. Display the maximum and minimum account balances");
            System.out.println("6. Display all accounts that have low balance");
            System.out.println("7. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {

                //create new account
                case 1:
                    System.out.print("Enter account holder name: ");
                    String name = scanner.next();
                    scanner.nextLine();
                    System.out.print("Enter gender: ");
                    char gender = scanner.next().charAt(0);
                    scanner.nextLine();
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter address: ");
                    String address = scanner.next();
                    scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.next();
                    scanner.nextLine();
                    Person person = new Person(name, gender, age, address, phoneNumber);
                    System.out.print("Enter deposit amount: ");
                    double balance = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter date: ");
                    String date = scanner.next();
                    scanner.nextLine();
                    System.out.print("Enter the account number: ");
                    String accountNumber = scanner.next();
                    scanner.nextLine();

                    while (!(bank.findAccount(accountNumber) == null)) {
                        System.out.println("Account already exists.");
                        System.out.print("Enter account number: ");
                        accountNumber = scanner.next();
                        scanner.nextLine();
                    }

                    System.out.print("Enter the withdraw limit: ");
                    double withdrawLimit = scanner.nextDouble();

                    Account account = new Account(accountNumber, date, balance, withdrawLimit, 0, person);
                    bank.addAccount(account);
                    System.out.println("Account created successfully!");
                    System.out.println("Account number: " + account.getAccountNumber());
                    break;
                case 2:
                    System.out.print("Enter an account number: ");
                    String existingAccountNumber = scanner.next();
                    Account existingAccount = bank.findAccount(existingAccountNumber);
                    if (existingAccount == null) {
                        System.out.println("Account not found.");
                        break;
                    }
                    int option = 0;
                    while (option != 4) {
                        System.out.println("1. Deposit money");
                        System.out.println("2. Withdraw money");
                        System.out.println("3. Display account information");
                        System.out.println("4. Go back to main menu");
                        System.out.print("Enter your choice: ");
                        option = scanner.nextInt();
                        switch (option) {
                            case 1:
                                System.out.println("\nEnter the amount to deposit: ");
                                double depositAmount = scanner.nextDouble();
                                existingAccount.depositMoney(depositAmount);
                                System.out.println("Deposit successful!");
                                break;
                            case 2:
                                System.out.println("\nEnter the amount to withdraw: ");
                                double withdrawAmount = scanner.nextDouble();
                                existingAccount.withdrawMoney(withdrawAmount);
                                System.out.println("Withdrawal successful!");
                                break;
                            case 3:
                                System.out.println(existingAccount + "\n");
                                break;
                            case 4:
                                System.out.println("Going back to main menu!");
                        }
                    }
                    break;
                    case 3:
                        System.out.print("Enter the account number of the account you wish to delete: ");
                        existingAccountNumber = scanner.next();
                        bank.deleteAccount(existingAccountNumber);
                        break;
                    case 4:
                        double averageBalance = bank.getAverageBalance();
                        System.out.println("Average balance of accounts: " + averageBalance);
                        break;
                    case 5:
                        double minBal = bank.getMinimumBalance();
                        double maxBal = bank.getMaximumBalance();

                       System.out.println("Minimum balance: " + minBal);
                      System.out.println("Maximum balance: " + maxBal);
                      break;
                    case 6:
                    System.out.print("Enter balance threshold: ");
                    double balanceThreshold = scanner.nextDouble();
                    ArrayList<Account> lowBalanceAccounts = bank.getLowBalanceAccounts(balanceThreshold);
                    System.out.println("Accounts with balance below " + balanceThreshold + ":");
                    for (Account acc : lowBalanceAccounts) {
                        System.out.println("Account Number: " + acc.getAccountNumber() + ", Balance: " + acc.getCurrentBalance());
                    }
                    break;
                    case 7:
                        try {
                            bank.save("C:\\Users\\ahmet\\IdeaProjects\\Account.java\\src\\accounts\\saved.txt");
                        } catch (Exception e) {
                            System.out.println("Unable to save accounts to file.");
                        }
                        System.out.println("Thank you for choosing BankApp!");
                        break;
            }
        }
    }
}