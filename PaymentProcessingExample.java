package com.java_journey.concepts;

/**
 * Example of Polymorphism: A Payment Processing System.
 * 
 * Uses an 'interface', which is a common way to achieve polymorphism in Java.
 * An interface is a contract that guarantees certain methods will be available in the classes
 * that 'implement' it.
 */

interface PaymentMethod {
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {
    private String cardNumber;
    private String cardHolderName;

    public CreditCardPayment(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public boolean processPayment(double amount) {
        // In a real app, this would redirect the user to PayPal's login.
        System.out.println("Initiating PayPal payment of $" + String.format("%.2f", amount) + " for " + email);
        System.out.println("Redirecting to PayPal for authentication...");
        System.out.println("Payment Successful!");
        return true;    
    }
}

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

    public static void main(String[] args) {
        PaymentMethod creditCardPayment = new CreditCardPayment("1234-5678-9012-3456", "John Doe");
        PaymentMethod paypalPayment = new PayPalPayment("john.doe@example.com");
        double totalAmount = 150.75;
        executeCheckout(creditCardPayment, totalAmount);
        executeCheckout(paypalPayment, totalAmount);
}