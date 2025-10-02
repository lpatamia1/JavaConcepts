package com.java_journey.concepts;

/**
 * An example of Encapsulation in Java. The BankAccount class encapsulates the details of a bank account.
 *
 * Encapsulation is one of the four fundamental OOP concepts. It is the mechanism of wrapping the data (variables)
 * and code acting on the data (methods) together as a single unit. In encapsulation, the variables of a class
 * are hidden from other classes and can be accessed only through the methods of their current class.
 * This is also known as data hiding.
 *
 * BENEFITS OF ENCAPSULATION:
 * 1. Control: We control how data is accessed and modified, allowing for validation.
 * 2. Flexibility: We can change the internal implementation without affecting outside code.
 * 3. Security: It helps to protect sensitive data from unintended interference and misuse.
 *
 * Corrected Output:
 * --- Creating a new Bank Account ---
 * Account Holder: Jane Doe
 * Account Number: ****-****-****-7654
 * Initial Balance: $500.00
 *
 * --- Performing Transactions ---
 * Deposit of $250.50 successful. New balance: $750.50
 * Withdrawal of $100.00 successful. New balance: $650.50
 * Deposit amount must be positive.
 * Withdrawal of $1000.00 failed due to insufficient funds.
 *
 * Final Balance: $650.50
 * --- End of Transactions ---
 */

// This class is a blueprint for BankAccount objects. It is now a top-level class.
class BankAccount {
    // Private fields are hidden from other classes.
    private String accountHolderName;
    private final String accountNumber; // final means it can't be changed after creation.
    private double balance;

    public BankAccount(String accountHolderName, String accountNumber, double initialBalance) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0;
            System.out.println("Initial balance cannot be negative. Setting to $0.00");
        }
    }

    // --- Public "getter" methods to provide controlled read-access ---
    public double getBalance() {
        return this.balance;
    }

    public String getAccountHolderName() {
        return this.accountHolderName;
    }
    
    public String getAccountNumber() {
        // Encapsulation allows us to change how we display data without changing the data itself.
        return "****-****-****-" + this.accountNumber.substring(this.accountNumber.length() - 4);
    }

    // --- Public "setter" and other methods to provide controlled write-access ---
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit of $" + String.format("%.2f", amount) + " successful. New balance: $" + String.format("%.2f", this.balance));
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal of $" + String.format("%.2f", amount) + " successful. New balance: $" + String.format("%.2f", this.balance));
        } else {
            System.out.println("Withdrawal of $" + String.format("%.2f", amount) + " failed due to insufficient funds.");
        }
    }
}

// Main class to demonstrate the BankAccount. This MUST be a top-level public class.
public class EncapsulationExample {
    public static void main(String[] args) {
        System.out.println("--- Creating a new Bank Account ---");
        BankAccount myAccount = new BankAccount("Jane Doe", "9876-5432-1098-7654", 500.00);

        // We can't access the balance directly. This line would cause a compile error:
        // myAccount.balance = 1000000.0; // ERROR: The field BankAccount.balance is not visible

        // We must use the public "getter" methods to read the data.
        System.out.println("Account Holder: " + myAccount.getAccountHolderName());
        System.out.println("Account Number: " + myAccount.getAccountNumber());
        System.out.println("Initial Balance: $" + String.format("%.2f", myAccount.getBalance()));
        System.out.println("\n--- Performing Transactions ---");

        // We use the public methods to safely modify the balance.
        myAccount.deposit(250.50);
        myAccount.withdraw(100.00);

        // Let's test our validation logic.
        myAccount.deposit(-50.00);      // Invalid deposit
        myAccount.withdraw(1000.00);    // Insufficient funds

        System.out.println("\nFinal Balance: $" + String.format("%.2f", myAccount.getBalance()));
        System.out.println("--- End of Transactions ---");
    }
}

