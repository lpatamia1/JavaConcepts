package com.java_journey.concepts;

/**
 * Example of Polymorphism: A Payment Processing System.
 * 
 * Uses an 'interface', which is a common way to achieve polymorphism in Java.
 * An interface is a contract that guarantees certain methods will be available in the classes
 * that 'implement' it.
 * 
 * Polymorphism allows us to use different payment methods interchangeably.
 * The 'executeCheckout' method can accept any payment method that implements the 'PaymentMethod'
 * interface, making the checkout process flexible and extensible.
 * 
 * Polymorphism is a core concept in OOP that allows objects of different classes to be treated as objects of a common superclass.
 * It is often used to implement dynamic method resolution, where the method that gets executed is determined at runtime based on the object's actual class.
 * 
 * In this example, we have two payment methods: CreditCardPayment and PayPalPayment. Both implement the PaymentMethod interface.
 * The executeCheckout method can process payments using either method without needing to know the specifics of each payment method.    
 * 
 * Output:
 * --- Starting Checkout Process ---
 * Processing credit card payment of $150.75 for John Doe
 * Validating card number...
 * Payment Successful!
 * Checkout completed successfully!
 * --- Checkout Process Ended ---
 * 
 * Furthermore, we can easily add new payment methods in the future (e.g., BitcoinPayment) without changing the existing checkout logic.
 * We just need to create a new class that implements the PaymentMethod interface.
 */

interface PaymentMethod {
    boolean processPayment(double amount);
}
// This class implements the PaymentMethod interface.
// It provides a specific implementation for processing credit card payments.
class CreditCardPayment implements PaymentMethod {
    private String cardNumber;
    private String cardHolderName;

    public CreditCardPayment(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }
    // This method is defined in the PaymentMethod interface.
    // Each payment method class provides its own implementation.
    // Override annotation indicates that this method overrides a method in the interface.
    @Override
    public boolean processPayment(double amount) {
        // In a real app, this would redirect the user to PayPal's login.
        System.out.println("Processing credit card payment of $" + String.format("%.2f", amount) + " for " + cardHolderName);
        System.out.println("Validating card number...");
        System.out.println("Payment Successful!");
        return true;
    }
}
// Another class implementing the PaymentMethod interface.
// This class provides a specific implementation for processing PayPal payments.
// In a real application, this would involve redirecting the user to PayPal's login page.
class PayPalPayment implements PaymentMethod {
    private String email;
    // Constructor to initialize PayPal payment with user's email.
    public PayPalPayment(String email) {
        this.email = email;
    }
    // Implementation of the processPayment method for PayPal.
    // This method overrides the one defined in the PaymentMethod interface.
    @Override
    public boolean processPayment(double amount) {
        // In a real app, this would redirect the user to PayPal's login.
        System.out.println("Processing PayPal payment of $" + String.format("%.2f", amount) + " for " + email);
        System.out.println("Redirecting to PayPal...");
        System.out.println("Payment Successful!");
        return true;
    }
}   
// Main class to demonstrate polymorphism in payment processing.
// It contains a method to execute the checkout process using any payment method.
public class PaymentProcessingExample {
    // Its parameter is the INTERFACE 'Payment Method', not a specific class.
    // This makes the checkout process flexible.
    public static void executeCheckout(PaymentMethod paymentMethod, double totalAmount) {
        System.out.println("\n--- Starting Checkout Process ---");
        if (paymentMethod.processPayment(totalAmount)) {
            System.out.println("Checkout completed successfully!");
        } else {
            System.out.println("Checkout failed. Please try again.");
        }
        System.out.println("--- Checkout Process Ended ---\n"); 
    }
    // Main method to run the example.
    // It creates instances of different payment methods and processes a payment using each.
    public static void main(String[] args) {
        PaymentMethod creditCardPayment = new CreditCardPayment("1234-5678-9012-3456", "John Doe");
        PaymentMethod paypalPayment = new PayPalPayment("john.doe@example.com");
        double totalAmount = 150.75;
        executeCheckout(creditCardPayment, totalAmount);
        executeCheckout(paypalPayment, totalAmount);
    }
}