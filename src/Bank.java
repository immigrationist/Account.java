import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Bank {
	
    private ArrayList<Account> accounts;
    private double balanceThreshold;

    public Bank(double balanceThreshold) {
        accounts = new ArrayList<>();
        this.balanceThreshold = balanceThreshold;
        
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account findAccount(String accountNumber) {
    	
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void deleteAccount(String accountNumber) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            System.out.println("Unable to find account.");
            return;
        }
        accounts.remove(account);
        System.out.println("Account deleted successfully.");
    }

    public double getAverageBalance() {
        double totalBalance = 0;

        for (Account account : accounts) {
            totalBalance += account.getCurrentBalance();
            System.out.println(totalBalance);
        }
        return totalBalance / accounts.size();
    }

    public double getMaximumBalance() {
    	
        double maxBalance = Double.MIN_VALUE;
        for (Account account : accounts) {
            double balance = account.getCurrentBalance();
            if (balance > maxBalance) {
                maxBalance = balance;
            }
        }
        return maxBalance;
    }

    public double getMinimumBalance() {
        double minBalance = Double.MAX_VALUE;
        for (Account account : accounts) {
            double balance = account.getCurrentBalance();
            if (balance < minBalance) {
                minBalance = balance;
            }
        }
        return minBalance;
    }

    public ArrayList<Account> getLowBalanceAccounts(double balanceThreshold) {
        ArrayList<Account> lowBalanceAccounts = new ArrayList<>();
        for (Account account : accounts) {
            if (account.getCurrentBalance() < this.balanceThreshold) {
                lowBalanceAccounts.add(account);
            }
        }
        return lowBalanceAccounts;
    }

    public void save(String filePath) throws Exception {
        FileOutputStream fos = new FileOutputStream(filePath);
        OutputStreamWriter osw = new OutputStreamWriter(fos);

        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            osw.write(account.convertToText());
        }
        osw.close();
        fos.close();
    }

    public void load(String filePath) throws Exception {
        FileInputStream fis = new FileInputStream(filePath);
        Scanner fileScanner = new Scanner(fis);
        String accountInfo = "";
        String textLine = "";

        while (fileScanner.hasNextLine()) {
            textLine = fileScanner.nextLine();
            if (textLine.equals("End")) {
                Person person = new Person("", 'm', 20, "", "123-456-7890");

                Account account = new Account("", "", 0,
                        0, 0, person);

                account.loadFromText(accountInfo);
                account.getAccountHolder().setName(account.getAccountHolderName());
                accounts.add(account);
                accountInfo = "";
            }
            else
                accountInfo += textLine + "\n";
        }
    }
}