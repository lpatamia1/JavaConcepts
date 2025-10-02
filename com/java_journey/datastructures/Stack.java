package com.java_journey.datastructures;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Stack Data Structure Example in Java.
 * A stack is a linear data structure that follows the Last In First Out (LIFO) principle.
 * This means that the last element added to the stack will be the first one to be removed.
 * Common operations on a stack include:
 * - push: Add an element to the top of the stack.
 * - pop: Remove and return the top element of the stack.
 * - peek: Return the top element without removing it.
 * - isEmpty: Check if the stack is empty.
 * - size: Get the number of elements in the stack.     
 * 
 * In this example, we demonstrate how to use a stack to reverse a string.
 * We push each character of the string onto the stack and then pop them off to create the reversed string.
 * We use Java's built-in Deque interface with an ArrayDeque implementation to represent the stack. 
 * 
 * Output:
 * --- Stack Example: Reversing a String ---
 * Original String: Hello, World!
 * Reversed String: !dlroW ,olleH
 * 
 * Deque as Stack Example:
 * Pushed: 10
 * Pushed: 20
 * Pushed: 30
 * Top element (peek): 30
 * Popped: 30
 * Popped: 20
 * Popped: 10
 * Is stack empty? true     
 * --- End of Stack Example ---
 */

 class ArrayStack {
    private Deque<Integer> stack;

    public ArrayStack() {
        stack = new ArrayDeque<>();
    }

    // Push an element onto the stack
    public void push(int value) {
        stack.push(value);
        System.out.println("Pushed: " + value);
    }

    // Pop an element from the stack
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        int value = stack.pop();
        System.out.println("Popped: " + value);
        return value;
    }

    // Peek at the top element of the stack
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.peek();
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    // Get the size of the stack
    public int size() {
        return stack.size();
    }
}

public class Stack {
    public static void main(String[] args) {
        System.out.println("--- Stack Example: Reversing a String ---");
        String original = "Hello, World!";
        String reversed = reverseString(original);
        System.out.println("Original String: " + original);
        System.out.println("Reversed String: " + reversed);

        System.out.println("\nDeque as Stack Example:");
        ArrayStack stack = new ArrayStack();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Top element (peek): " + stack.peek());
        while (!stack.isEmpty()) {
            stack.pop();
        }
        System.out.println("Is stack empty? " + stack.isEmpty());
        System.out.println("--- End of Stack Example ---");
    }

    // Method to reverse a string using a stack
    public static String reverseString(String str) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : str.toCharArray()) {
            stack.push(ch);
        }

        StringBuilder reversed = new StringBuilder();
        while (!stack.isEmpty()) {
            reversed.append(stack.pop());
        }
        return reversed.toString();
    }
}   