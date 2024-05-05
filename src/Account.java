import java.util.Scanner;

public class Account {
	
    private String accountNumber;
    private String dateCreated;
    private double currentBalance;
    private double withdrawLimit;
    private double withdrawAmount;
    private Person accountHolder;

    public Account(String accountNumber, String dateCreated, double currentBalance,
                   double withdrawLimit, double withdrawAmount, Person accountHolder) {
        this.accountNumber = accountNumber;
        this.dateCreated = dateCreated;
        this.currentBalance = currentBalance;
        this.withdrawLimit = withdrawLimit;
        this.withdrawAmount = withdrawAmount;
        this.accountHolder = accountHolder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
    	
        if (!accountNumber.isEmpty())
            this.accountNumber = accountNumber;
        
    }

    public Person getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(Person accountHolder) {
        this.accountHolder = accountHolder;
    }


    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
    	
        if (!dateCreated.isEmpty())
            this.dateCreated = dateCreated;
        
    }

    public double getWithdrawLimit() {
        return withdrawLimit;
    }

    public void setWithdrawLimit(double withdrawLimit) {
    	
        if (withdrawLimit >= 0)
            this.withdrawLimit = withdrawLimit;
        
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
    	
        if (currentBalance >= 0)
            this.currentBalance = currentBalance;
        
    }

    public double getWithdrawAmount() {
        return withdrawAmount;
    }

    public String toString() {
    	
        return "Bank Account" + "\nAccount Number: " + accountNumber + "\nAccount Holder: "
                + accountHolder + "\nDate Created: " + dateCreated +
                "\nCurrent Balance: " + currentBalance + "\nWithdraw Limit: " +
                withdrawLimit + "\nWithdraw Amount: " + withdrawAmount;
        
    }

    public String getAccountHolderName() {
        return accountHolder.getName();
    }

    public void depositMoney(double amount) {
        currentBalance += amount;
    }

    public boolean withdrawMoney(double amount) {
        if (this.currentBalance - amount >= 0.0 && this.withdrawAmount + amount <= this.withdrawLimit) {
            this.currentBalance -= amount;
            this.withdrawAmount += amount;
            System.out.println("withdraw successful");
            return true;
        } else {
            System.out.println("withdraw failed, withdraw limit reached");
            return false;
        }
    }

    public String convertToText() {
    	
        String text = "";

        text +=  "Account Number: " + accountNumber + " Date Created: " + dateCreated + " Current Balance: ";
        text += currentBalance + " Withdraw Limit: " + withdrawLimit + " Withdraw Amount: " + withdrawAmount + "\n";
        text += "Personal Information\n" + accountHolder;
        text += "\nEnd\n";

        return text;

    }

    public void loadFromText(String text) {
    	
        Scanner strScanner = new Scanner(text);

        accountNumber = strScanner.next();
        dateCreated = strScanner.next();
        strScanner.nextLine();
        currentBalance = strScanner.nextDouble();
        withdrawLimit = strScanner.nextDouble();
        withdrawAmount = strScanner.nextDouble();

        String name = strScanner.next();
        accountHolder.setName(name);
        
    }
}