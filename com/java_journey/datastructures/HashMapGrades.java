package com.java_journey.datastructures;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


/**
 * HashMap Data Structure Example in Java.
 * A HashMap is a part of Java's collection framework and is used to store data in key-value pairs.
 * It allows for fast retrieval, insertion, and deletion of elements based on their keys.
 * Common operations on a HashMap include:
 * - put: Add a key-value pair to the map.
 * - get: Retrieve the value associated with a specific key.
 * - remove: Delete a key-value pair from the map using the key.
 * - containsKey: Check if a specific key exists in the map.
 * - size: Get the number of key-value pairs in the map.     
 * 
 * In this example, we demonstrate how to use a HashMap to store and retrieve student grades.  
 * Output:
 * --- HashMap Example: Student Grades ---
 * Added: Alice with grade 85
 * Added: Bob with grade 90
 * Added: Charlie with grade 78
 * Grade for Bob: 90
 * Updated: Alice with new grade 88 
 * Removed: Charlie
 * Contains key 'Charlie'? false
 * Total students in map: 2
 * All students and their grades: {Alice=88, Bob=90}
 * Grades List: [88, 90]
 * Average Grade: 89.0
 * --- End of HashMap Example ---
 */

public class HashMapGrades {
    public static void main(String[] args) {
        System.out.println("--- HashMap Example: Student Grades ---");
        
        // Create a HashMap to store student names and their grades
        Map<String, Integer> studentGrades = new HashMap<>();
        
        // Adding key-value pairs to the HashMap
        studentGrades.put("Alice", 85);
        System.out.println("Added: Alice with grade 85");
        studentGrades.put("Bob", 90);
        System.out.println("Added: Bob with grade 90");
        studentGrades.put("Charlie", 78);
        System.out.println("Added: Charlie with grade 78");
        
        // Retrieving a value by key
        int bobGrade = studentGrades.get("Bob");
        System.out.println("Grade for Bob: " + bobGrade);
        
        // Updating a value
        studentGrades.put("Alice", 88);
        System.out.println("Updated: Alice with new grade 88 ");
        
        // Removing a key-value pair
        studentGrades.remove("Charlie");
        System.out.println("Removed: Charlie");
        
        // Checking if a key exists
        boolean hasCharlie = studentGrades.containsKey("Charlie");
        System.out.println("Contains key 'Charlie'? " + hasCharlie);
        
        // Getting the size of the HashMap
        int size = studentGrades.size();
        System.out.println("Total students in map: " + size);
        
        // Iterating over the HashMap entries
        System.out.println("All students and their grades: " + studentGrades);
        
        // Additional operations: Getting all values and calculating average grade
        List<Integer> grades = new LinkedList<>(studentGrades.values());
        double average = grades.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        
        System.out.println("Grades List: " + grades);
        System.out.println("Average Grade: " + average);
        
        System.out.println("--- End of HashMap Example ---");
    }
}
