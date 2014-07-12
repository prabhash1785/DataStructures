package com.prabhash.java.algorithms.datastructures.tree;

/**
 * This class has various utility functions to determine certain properties of a Tree.
 * @author Prabhash Rathore
 *
 */
public class CheckBalancedTree {

	private BinarySearchTree tree;
		
	/**
	 * This method determines if a given tree is a Balanced Tree or not.
	 * For a tree to be balanced, the heights of any two sub-trees for any node should never differ by one.
	 * @param root
	 * @return boolean
	 */
	public boolean checkBalancedTree(Node root) {
		if(checkHeight(root) == -1) {
			return false;
		} else {
			return true;
		}
	}
	
	private int checkHeight(Node root) {
		if(root == null) {
			return 0;
		}
		
		//Check if left is balanced
		int leftHeight = checkHeight(root.getLeftChild());
		//System.out.println("Height of " + root.getLeftChild() + " is " + leftHeight);
		if(leftHeight == -1) {
			return -1; //Not balanced
		}
		
		//Check if right is balanced
		int rightHeight = checkHeight(root.getRightChild());
		//System.out.println("Height of " + root.getRightChild() + " is " + rightHeight);
		if(rightHeight == -1) {
			return -1; //Not balanced
		}
		
		//Check of current node is balanced
		int heightDiff = leftHeight - rightHeight;
		if(Math.abs(heightDiff) > 1) {
			return -1; // Not balanced
		} else {
			//System.out.println("Height of this tree is: " + (Math.max(leftHeight, rightHeight) + 1));
			return Math.max(leftHeight, rightHeight) + 1; //return height
		}
	}
	
	/**
	 * This method determines the height of a tree.
	 * @param root
	 * @return height
	 */
	public int getHeight(Node root) {
		if(root == null) {
			return 0;
		} else {
			return Math.max(getHeight(root.getLeftChild()), getHeight(root.getRightChild())) + 1;
		}
	}
	
	/**
	 * Check if this Binary Tree is Binary Search Tree
	 * @param root
	 */
	public boolean checkBST(Node root) {
		
		return checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private boolean checkBST(Node root, int min, int max) {
		if(root == null) {
			return true;
		}
		if(root.getKey() < min || root.getKey() > max) {
			return false;
		}
		return checkBST(root.getLeftChild(), min, root.getKey()) && checkBST(root.getRightChild(), root.getKey(), max);
	}
	
	public static void main(String[] args) {
		
		CheckBalancedTree checkTree = new CheckBalancedTree();
		
		checkTree.tree = new BinarySearchTree();
		
		checkTree.tree.add(25, "25");
		checkTree.tree.add(15, "15");
		checkTree.tree.add(35, "35");
		checkTree.tree.add(50, "50");
		checkTree.tree.add(17, "17");
		checkTree.tree.add(21, "21");
		checkTree.tree.add(5, "5");
		checkTree.tree.add(3, "3");
		checkTree.tree.add(14, "14");
		checkTree.tree.add(1, "1");
		
		Node root = checkTree.tree.getRoot();
		System.out.println("Root is : " + root.getData());
		
		//Get Height of Tree
		System.out.println("Height of tree is: " + checkTree.getHeight(root));
		
		//Check if this tree is balanced
		if(checkTree.checkBalancedTree(root)) {
			System.out.println("Not a balanced tree!!");
		} else {
			System.out.println("It's a balanced tree!!");
		}
		
		//Check if tree is BST
		System.out.println("Is BST: " + checkTree.checkBST(root));

	}

}

