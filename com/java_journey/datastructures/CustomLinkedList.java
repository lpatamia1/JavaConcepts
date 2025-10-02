package com.java_journey.datastructures;

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class CustomLinkedList {
    private Node head;

    public CustomLinkedList() {
        this.head = null;
    }

    // Add a new node at the end of the list
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Remove a node by value
    public boolean remove(int data) {
        if (head == null) return false;

        if (head.data == data) {
            head = head.next;
            return true;
        }

        Node current = head;
        while (current.next != null && current.next.data != data) {
            current = current.next;
        }

        if (current.next == null) return false;

        current.next = current.next.next;
        return true;
    }

    // Contains method to check if a value exists in the list
    public boolean contains(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) return true;
            current = current.next;
        }
        return false;
    }   

    // Display the linked list
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Main method to test the CustomLinkedList
    public static void main(String[] args) {
        CustomLinkedList list = new CustomLinkedList();
        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println("Initial List:");
        list.display();

        System.out.println("Removing 20:");
        list.remove(20);
        list.display();

        System.out.println("Removing 10:");
        list.remove(10);
        list.display();

        System.out.println("Removing 40 (not in list):");
        boolean removed = list.remove(40);
        System.out.println("Removed: " + removed);
        list.display();
    }
}
