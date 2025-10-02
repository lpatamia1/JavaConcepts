package com.java_journey.concepts;

/**
 * Exception Handling Example in Java.
 * This example demonstrates how to handle exceptions using try-catch blocks.
 * Exception handling is crucial for building robust applications that can gracefully handle runtime errors.    
 * In this example, we attempt to divide two numbers and handle potential exceptions such as division by zero and invalid input formats.
 * The program prompts the user for input, performs the division, and catches any exceptions that may occur, providing informative error messages.  
 * 
 * The core keywords used in exception handling are:
 * - try: The block of code that may throw an exception is placed inside a try block.
 * - catch: This block handles the exception if one occurs. You can have multiple catch blocks to handle different types of exceptions.
 * - finally: This block is optional and will execute after the try and catch blocks, regardless of whether an exception occurred or not. It is typically used for cleanup activities.
 * - throw: Used to explicitly throw an exception.
 * - throws: Used in method signatures to indicate that a method may throw exceptions.  
 * 
 * This simulates processing data from an array of strings, where some entries may be invalid.
 * The program will attempt to parse each string to an integer and divide a fixed number by that integer.
 * If an entry is invalid (e.g., non-numeric or zero), it will catch the exception and print an error message.
 * 
 * Output:
 * --- Starting Exception Handling Example ---
 * Processing data: 10
 * Result: 10 / 10 = 1
 * Processing data: 0
 * Error: Division by zero is not allowed.
 * Processing data: five
 * Error: Invalid number format for input 'five'.
 * Processing data: 2
 * Result: 10 / 2 = 5
 * Processing data: -3
 * Result: 10 / -3 = -3
 * --- Exception Handling Example Completed ---
 */
import java.util.Scanner;
public class ExceptionHandlingExample {
    public static void main(String[] args) {
        System.out.println("--- Starting Exception Handling Example ---");
        String[] data = {"10", "0", "five", "2", "-3"};
        int fixedNumber = 10;

        for (String entry : data) {
            System.out.println("Processing data: " + entry);
            try {
                int divisor = Integer.parseInt(entry);
                int result = fixedNumber / divisor;
                System.out.println("Result: " + fixedNumber + " / " + divisor + " = " + result);
            } catch (ArithmeticException e) {
                System.out.println("Error: Division by zero is not allowed.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid number format for input '" + entry + "'.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            } finally {
                // This block can be used for cleanup, if necessary.
                // In this case, we don't have any specific cleanup to do.
            }
        }
        System.out.println("--- Exception Handling Example Completed ---");
    }
}   