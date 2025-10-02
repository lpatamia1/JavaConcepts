package com.java_journey.datastructures;

/**
 * Queue Data Structure Example in Java.
 * A queue is a linear data structure that follows the First In First Out (FIFO) principle.
 * This means that the first element added to the queue will be the first one to be removed.
 * Common operations on a queue include:
 * - enqueue: Add an element to the end of the queue.
 * - dequeue: Remove and return the front element of the queue.
 * - peek: Return the front element without removing it.
 * - isEmpty: Check if the queue is empty.
 * - size: Get the number of elements in the queue.     
 * 
 * In this example, we demonstrate how to use a queue to manage a simple task list.
 * We enqueue tasks to the queue and then dequeue them to process in the order they were added.
 * We use Java's built-in Deque interface with an ArrayDeque implementation to represent the queue.     
 * 
 * Output:
 * --- Queue Example: Task Management ---
 * Enqueued: Task 1
 * Enqueued: Task 2
 * Enqueued: Task 3
 * Current Queue: [Task 1, Task 2, Task 3]
 * Peek: Task 1
 * Dequeued: Task 1
 * Dequeued: Task 2
 * Dequeued: Task 3
 * Is queue empty? true
 * --- End of Queue Example ---
 */

import java.util.ArrayDeque;
import java.util.Deque;

class ArrayQueue {
    private Deque<String> queue;

    public ArrayQueue() {
        queue = new ArrayDeque<>();
    }

    // Enqueue an element to the end of the queue
    public void enqueue(String value) {
        queue.offer(value);
        System.out.println("Enqueued: " + value);
    }

    // Dequeue an element from the front of the queue
    public String dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        String value = queue.poll();
        System.out.println("Dequeued: " + value);
        return value;
    }

    // Peek at the front element without removing it
    public String peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue.peek();
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // Get the size of the queue
    public int size() {
        return queue.size();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}

public class Queue {
    public static void main(String[] args) {
        ArrayQueue taskQueue = new ArrayQueue();
        
        System.out.println("--- Queue Example: Task Management ---");
        
        // Enqueue tasks
        taskQueue.enqueue("Task 1");
        taskQueue.enqueue("Task 2");
        taskQueue.enqueue("Task 3");
        
        System.out.println("Current Queue: " + taskQueue);
        
        // Peek at the front task
        System.out.println("Peek: " + taskQueue.peek());
        
        // Dequeue and process tasks
        while (!taskQueue.isEmpty()) {
            String task = taskQueue.dequeue();
            // Simulate processing the task
            // In a real application, you would have more complex logic here.
        }
        
        System.out.println("Is queue empty? " + taskQueue.isEmpty());
        System.out.println("--- End of Queue Example ---");
    }
}