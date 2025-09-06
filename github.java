import java.util.Scanner;

// Account class
class Account {
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private String email;
    private String phoneNumber;

    // Constructor
    public Account(int accountNumber, String accountHolderName, double balance, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited ₹" + amount + ". New Balance: ₹" + balance);
        } else {
            System.out.println("Invalid deposit amount. Must be positive.");
        }
    }

    // Withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew ₹" + amount + ". New Balance: ₹" + balance);
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    // Show account details
    public void displayAccountDetails() {
        System.out.println("----- Account Details -----");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolderName);
        System.out.println("Balance        : ₹" + balance);
        System.out.println("Email          : " + email);
        System.out.println("Phone          : " + phoneNumber);
        System.out.println("---------------------------");
    }

    // Update contact details
    public void updateContactDetails(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        System.out.println("Contact details updated successfully.");
    }

    // Getter for account number
    public int getAccountNumber() {
        return accountNumber;
    }
}

// User Interface class
class UserInterface {
    private Account[] accounts;   // Array to store accounts
    private int accountCount;     // Keep track of how many accounts created
    private Scanner scanner;

    public UserInterface(int size) {
        accounts = new Account[size];
        accountCount = 0;
        scanner = new Scanner(System.in);
    }

    // Create a new account
    public void createAccount() {
        if (accountCount >= accounts.length) {
            System.out.println("Cannot create more accounts. Limit reached.");
            return;
        }
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();

        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        System.out.print("Enter email address: ");
        String email = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        int accountNumber = 1000 + accountCount + 1; // Generate account number
        accounts[accountCount] = new Account(accountNumber, name, initialDeposit, email, phone);
        System.out.println("Account created successfully with Account Number: " + accountNumber);
        accountCount++;
    }


    private Account findAccount(int accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return accounts[i];
            }
        }
        return null;
    }

    // Deposit operation
    public void performDeposit() {
        System.out.print("Enter account number: ");
        int accNum = scanner.nextInt();
        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        Account acc = findAccount(accNum);
        if (acc != null) {
            acc.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    // Withdrawal operation
    public void performWithdrawal() {
        System.out.print("Enter account number: ");
        int accNum = scanner.nextInt();
        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        Account acc = findAccount(accNum);
        if (acc != null) {
            acc.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    // Show account details
    public void showAccountDetails() {
        System.out.print("Enter account number: ");
        int accNum = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Account acc = findAccount(accNum);
        if (acc != null) {
            acc.displayAccountDetails();
        } else {
            System.out.println("Account not found.");
        }
    }

    // Update contact details
    public void updateContact() {
        System.out.print("Enter account number: ");
        int accNum = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter new email: ");
        String email = scanner.nextLine();
        System.out.print("Enter new phone number: ");
        String phone = scanner.nextLine();

        Account acc = findAccount(accNum);
        if (acc != null) {
            acc.updateContactDetails(email, phone);
        } else {
            System.out.println("Account not found.");
        }
    }

    // Main menu
    public void mainMenu() {
        int choice;
        do {
            System.out.println("\n--- Banking Application ---");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1: createAccount(); break;
                case 2: performDeposit(); break;
                case 3: performWithdrawal(); break;
                case 4: showAccountDetails(); break;
                case 5: updateContact(); break;
                case 6: System.out.println("Thank you for using the Banking Application!"); break;
                default: System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);
    }
}

// Main Class
public class github {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface(100); // Can store up to 100 accounts
        ui.mainMenu();
    }
}
