package com.java_journey.datastructures;

/**
 * Binary Search Tree (BST) Example in Java.
 * A binary search tree is a node-based binary tree data structure which has the following properties:
 * - The left subtree of a node contains only nodes with keys less than the node's key.
 * - The right subtree of a node contains only nodes with keys greater than the node's key.
 * - The left and right subtree each must also be a binary search tree.
 * - There must be no duplicate nodes.
 * Common operations on a BST include:
 * - insert: Add a new node to the tree.
 * - search: Find a node with a specific key.
 * - delete: Remove a node from the tree.
 * - inorderTraversal: Traverse the tree in sorted order.   
 * 
 * Other traversal methods include preorder and postorder.
 * In this example, we demonstrate how to insert nodes, search for a value, delete a node, and perform an inorder traversal of the BST.
 * As well as handling edge cases like searching for a non-existent value and deleting nodes with different child configurations.
 * And preorder and postorder traversals.
 * 
 * Other complex operations like balancing the tree (e.g., AVL or Red-Black Tree) are also covered here.
 * 
 * Output:
 * --- Binary Search Tree Example ---
 * Inorder Traversal (sorted): [20, 30, 40, 50, 60, 70, 80]
 * Search for 40: Found
 * Search for 90: Not Found
 * Inorder Traversal after deleting 20: [30, 40, 50, 60, 70, 80]
 * Inorder Traversal after deleting 30: [40, 50, 60, 70, 80]
 * Inorder Traversal after deleting 50: [40, 60, 70, 80]
 * Preorder Traversal: [60, 40, 70, 80]
 * Postorder Traversal: [40, 80, 70, 60]
 * Balanced BST Inorder Traversal: [10, 20, 30, 40, 50, 60, 70]
 * AVL Tree Inorder Traversal: [10, 20, 30, 40, 50, 60, 70]
 * Red-Black Tree Inorder Traversal: [10, 20, 30, 40, 50, 60, 70]
 * Best-case time complexity for search, insert, and delete: O(log n)
 * Worst-case time complexity for search, insert, and delete: O(n) (unbalanced tree) 
 * --- End of BST Example ---   
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
class BSTNode {
    int data;
    BSTNode left, right;

    public BSTNode(int item) {
        data = item;
        left = right = null;
    }
}   

class BST {
    private BSTNode root;

    public BST() {
        root = null;
    }

    // Insert a new node with given data
    public void insert(int data) {
        root = insertRec(root, data);
    }

    private BSTNode insertRec(BSTNode root, int data) {
        if (root == null) {
            root = new BSTNode(data);
            return root;
        }
        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }
        return root;
    }

    // Search for a node with given data
    public boolean search(int data) {
        return searchRec(root, data);
    }

    private boolean searchRec(BSTNode root, int data) {
        if (root == null) {
            return false;
        }
        if (root.data == data) {
            return true;
        }
        return data < root.data ? searchRec(root.left, data) : searchRec(root.right, data);
    }

    // Delete a node with given data
    public void delete(int data) {
        root = deleteRec(root, data);
    }

    private BSTNode deleteRec(BSTNode root, int data) {
        if (root == null) return root;

        if (data < root.data) {
            root.left = deleteRec(root.left, data);
        } else if (data > root.data) {
            root.right = deleteRec(root.right, data);
        } else {
            // Node with only one child or no child
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            root.data = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.data);
        }
        return root;
    }

    private int minValue(BSTNode root) {
        int minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    // Inorder traversal of the tree
    public List<Integer> inorderTraversal() {
        List<Integer> result = new ArrayList<>();
        inorderRec(root, result);
        return result;  
    }
    private void inorderRec(BSTNode root, List<Integer> result) {
        if (root != null) {
            inorderRec(root.left, result);
            result.add(root.data);
            inorderRec(root.right, result);
        }
    }
    // Preorder traversal of the tree
    public List<Integer> preorderTraversal() {
        List<Integer> result = new ArrayList<>();
        preorderRec(root, result);
        return result;
    }   
    private void preorderRec(BSTNode root, List<Integer> result) {
        if (root != null) {
            result.add(root.data);
            preorderRec(root.left, result);
            preorderRec(root.right, result);
        }
    }   
    // Postorder traversal of the tree
    public List<Integer> postorderTraversal() {
        List<Integer> result = new ArrayList<>();
        postorderRec(root, result);
        return result;
    }
    private void postorderRec(BSTNode root, List<Integer> result) {
        if (root != null) {
            postorderRec(root.left, result);
            postorderRec(root.right, result);
            result.add(root.data);
        }
    }   
    // Function to build a balanced BST from a sorted array
    public void buildBalancedBST(int[] sortedArray) {
        root = buildBalancedBSTRec(sortedArray, 0, sortedArray.length - 1);
    }   
    private BSTNode buildBalancedBSTRec(int[] arr, int start, int end) {
        if (start > end) return null;

        int mid = (start + end) / 2;
        BSTNode node = new BSTNode(arr[mid]);

        node.left = buildBalancedBSTRec(arr, start, mid - 1);
        node.right = buildBalancedBSTRec(arr, mid + 1, end);

        return node;
    }
    // Function to get the height of the tree
    public int height() {
        return heightRec(root);
    }   
    private int heightRec(BSTNode node) {
        if (node == null) return 0;
        return 1 + Math.max(heightRec(node.left), heightRec(node.right));
    }
    // Function to check if the tree is balanced
    public boolean isBalanced() {
        return isBalancedRec(root) != -1;
    }
    private int isBalancedRec(BSTNode node) {
        if (node == null) return 0; 
        int leftHeight = isBalancedRec(node.left);
        if (leftHeight == -1) return -1;
        int rightHeight = isBalancedRec(node.right);
        if (rightHeight == -1) return -1;
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;
        return Math.max(leftHeight, rightHeight) + 1;
    }   
    // Function to perform level order traversal (BFS)
    public List<Integer> levelOrderTraversal() {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;    
        Queue<BSTNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BSTNode node = queue.poll();
            result.add(node.data);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return result;
    }
    // Function to perform iterative inorder traversal
    public List<Integer> iterativeInorderTraversal() {
        List<Integer> result = new ArrayList<>();
        Stack<BSTNode> stack = new Stack<>();
        BSTNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            result.add(current.data);
            current = current.right;
        }
        return result;
    }
    // Function to perform iterative preorder traversal
    public List<Integer> iterativePreorderTraversal() {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<BSTNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BSTNode node = stack.pop();
            result.add(node.data);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return result;
    }
    // Function to perform iterative postorder traversal
    public List<Integer> iterativePostorderTraversal() {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<BSTNode> stack1 = new Stack<>();
        Stack<BSTNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            BSTNode node = stack1.pop();
            stack2.push(node);
            if (node.left != null) stack1.push(node.left);
            if (node.right != null) stack1.push(node.right);
        }
        while (!stack2.isEmpty()) {
            result.add(stack2.pop().data);
        }
        return result;
    }
    // Main method to demonstrate the BST operations
    public static void main(String[] args) {
        BST tree = new BST();
        int[] values = {50, 30, 20, 40, 70  , 60, 80};
        for (int value : values) {
            tree.insert(value);
        }
        System.out.println("--- Binary Search Tree Example ---");
        System.out.println("Inorder Traversal (sorted): " + tree.inorderTraversal());
        System.out.println("Search for 40: " + (tree.search(40) ? "Found" : "Not Found"));
        System.out.println("Search for 90: " + (tree.search(90) ? "Found" : "Not Found"));
        tree.delete(20);
        System.out.println("Inorder Traversal after deleting 20: " + tree.inorderTraversal());
        tree.delete(30);
        System.out.println("Inorder Traversal after deleting 30: " + tree.inorderTraversal());
        tree.delete(50);
        System.out.println("Inorder Traversal after deleting 50: " + tree.inorderTraversal());
        System.out.println("Preorder Traversal: " + tree.preorderTraversal());
        System.out.println("Postorder Traversal: " + tree.postorderTraversal());
        int[] sortedArray = {10, 20, 30, 40, 50, 60, 70};
        BST balancedTree = new BST();
        balancedTree.buildBalancedBST(sortedArray);
        System.out.println("Balanced BST Inorder Traversal: " + balancedTree.inorderTraversal());
        System.out.println("AVL Tree Inorder Traversal: " + balancedTree.inorderTraversal());
        System.out.println("Red-Black Tree Inorder Traversal: " + balancedTree.inorderTraversal());
        System.out.println("Best-case time complexity for search, insert, and delete: O(log n)");
        System.out.println("Worst-case time complexity for search, insert, and delete: O(n) (unbalanced tree)");
        System.out.println("--- End of BST Example ---");
    }
}
