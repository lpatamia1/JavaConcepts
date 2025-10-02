package com.java_journey.concepts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Collections;

/**
 * Java Collections Framework (JCF) Example.
 * The Java Collections Framework provides a set of classes and interfaces for storing and manipulating groups of data as a single unit, known as a collection.
 * It includes various data structures like lists, sets, and maps, each with its own characteristics and use cases.
 * Commonly used collections include:
 * - List: An ordered collection that allows duplicate elements. Implementations include ArrayList and LinkedList.
 * - Set: A collection that does not allow duplicate elements. Implementations include HashSet and TreeSet.
 * - Map: A collection that maps keys to values, allowing for fast retrieval based on keys. Implementations include HashMap and TreeMap.    
 * This example demonstrates the usage of List, Set, and Map from the Java Collections Framework.   
 * Output:
 * --- List Example ---
 * List contents: [Laptop, Smartphone, Tablet, Keyboard, Mouse]
 * Item at index 2: Tablet
 * List size: 5
 * List after removing 'Keyboard': [Laptop, Smartphone, Tablet, Mouse]  
 * --- Set Example ---
 * Set contents: [Laptop, Smartphone, Tablet, Keyboard, Mouse]
 * Set size (should be 5, no duplicates): 5
 * Set contains 'Tablet': true
 * Set after attempting to add duplicate 'Laptop': [Laptop, Smartphone, Tablet, Keyboard, Mouse]
 * --- Map Example ---
 * Map contents: {1=Laptop, 2=Smartphone, 3=Tablet, 4=Keyboard, 5=Mouse}
 * Value for key 3: Tablet
 * Map size: 5
 * Map after removing key 4: {1=Laptop, 2=Smartphone, 3=Tablet, 5=Mouse}
 * --- End of Collections Example ---
 */

 public class JCFExample {
    public static void main(String[] args) {
        // List Example
        System.out.println("--- List Example ---");
        List<String> itemList = new ArrayList<>();
        itemList.add("Laptop");
        itemList.add("Smartphone");
        itemList.add("Tablet");
        itemList.add("Keyboard");
        itemList.add("Mouse");

        System.out.println("List contents: " + itemList);
        System.out.println("Item at index 2: " + itemList.get(2));
        System.out.println("List size: " + itemList.size());

        itemList.remove("Keyboard");
        System.out.println("List after removing 'Keyboard': " + itemList);
        
        // Set Example
        System.out.println("\n--- Set Example ---");
        Set<String> itemSet = new HashSet<>();
        itemSet.add("Laptop");
        itemSet.add("Smartphone");
        itemSet.add("Tablet");
        itemSet.add("Keyboard");
        itemSet.add("Mouse");
        itemSet.add("Laptop"); // Duplicate, will not be added

        System.out.println("Set contents: " + itemSet);
        System.out.println("Set size (should be 5, no duplicates): " + itemSet.size());
        System.out.println("Set contains 'Tablet': " + itemSet.contains("Tablet"));

        itemSet.add("Laptop"); // Attempt to add duplicate again
        System.out.println("Set after attempting to add duplicate 'Laptop': " + itemSet);

        // Map Example
        System.out.println("\n--- Map Example ---");
        Map<Integer, String> itemMap = new HashMap<>();
        itemMap.put(1, "Laptop");
        itemMap.put(2, "Smartphone");
        itemMap.put(3, "Tablet");
        itemMap.put(4, "Keyboard");
        itemMap.put(5, "Mouse");

        System.out.println("Map contents: " + itemMap);
        System.out.println("Value for key 3: " + itemMap.get(3));
        System.out.println("Map size: " + itemMap.size());

        itemMap.remove(4); // Remove entry with key 4
        System.out.println("Map after removing key 4: " + itemMap);

        System.out.println("--- End of Collections Example ---");
    }
}
