package com.prabhash.java.algorithms.datastructures.tree;

public class BinaryTreeNodeDeleter {
	
	/**
	 * To delete a node from Binary Tree, do following:
	 * - Find the node to be deleted in the tree. If node not found then just return as node does not exist in tree.
	 * - If node to be deleted has two child nodes then find the rightmost leaf node, swap their data and delete right most leaf node. 
	 * - If node to be deleted has only one child left or right then find the leaf node in their respective left or right sub-tree, swap
	 * their values and delete that leaf node
	 * - If node to be deleted is itself a leaf node then just delete that node.
	 * 
	 * @param root
	 * @param data
	 * @return boolean
	 */
	public static boolean deleteNode(Node root, int data) {

		if(root == null) {
			System.out.println("Tree is empty");
			return false;
		}

		Node nodeToBeDeleted = findNodeToBeDeleted(root, data);
		
		// Did not find a matching node
		if(nodeToBeDeleted == null) {
			return false;
		}
		
		// if leaf node
		if(nodeToBeDeleted.left == null && nodeToBeDeleted.right == null) {
			return deleteLeafNode(root, nodeToBeDeleted);
		}
		
		Node deepestNode = null;
		if((nodeToBeDeleted.left != null && nodeToBeDeleted.right != null) || (nodeToBeDeleted.right != null)) {
			
			deepestNode = findRightMostDeepNode(nodeToBeDeleted); //find right most leaf node from the node to be deleted 
			nodeToBeDeleted.data = deepestNode.data;
		} else if(nodeToBeDeleted.left != null) {
			
			deepestNode = findLeftMostDeepNode(nodeToBeDeleted); // find left most leaf node from the node to be deleted
			nodeToBeDeleted.data = deepestNode.data;
		}

		return deleteLeafNode(root, deepestNode);
	}
	
	private static Node findNodeToBeDeleted(Node root, int data) {

		if(root == null) { // base condition
			return null;
		}

		Node left = findNodeToBeDeleted(root.left, data);
		if(left != null) {
			return left;
		}

		Node right = findNodeToBeDeleted(root.right, data);
		if(right != null) {
			return right;
		}

		if(root.data == data) {
			return root;
		}

		return null;

	}

	private static Node findRightMostDeepNode(Node root) {

		if(root == null) {
			return null;
		}
		
		// if root.right is null then this must be the right most node
		if(root.right == null) {
			return root;
		}
		
		return findRightMostDeepNode(root.right);
	}
	
	private static Node findLeftMostDeepNode(Node root) {

		if(root == null) {
			return null;
		}
		
		// if root.right is null then this must be the right most node
		if(root.left == null) {
			return root;
		}
		
		return findLeftMostDeepNode(root.left);
	}

	/**
	 * This helper method just deletes the leaf node.
	 * 
	 * @param root
	 * @param nodeToBeDeleted
	 * @return
	 */
	private static boolean deleteLeafNode(Node root, Node nodeToBeDeleted) {

		if(root == null) {
			return false;
		}

		if(root.left == nodeToBeDeleted) {
			root.left = null;
			return true;
		}

		if(root.right == nodeToBeDeleted) {
			root.right = null;
			return true;
		}

		return deleteLeafNode(root.left, nodeToBeDeleted) || deleteLeafNode(root.right, nodeToBeDeleted);
	}
	
	public static class Node {
		
		private int data;
		private Node left;
		private Node right;
		
		public Node(int data) {
			this.data = data;
		}
	}

	public static void main(String[] args) {

		Node root = new Node(8);
		
		root.left = new Node(4);
		root.left.left = new Node(1);
		root.left.right = new Node(3);
		// root.left.left.left = new Node(15);

		root.right = new Node(6);
		root.right.left = new Node(7);
		root.right.right = new Node(9);

		int nodeToBeDeleted = 8;
		
		if(deleteNode(root, nodeToBeDeleted)) {
			System.out.println(nodeToBeDeleted + " is deleted");
		} else {
			System.out.println("Did not find " + nodeToBeDeleted + " in the tree");
		}
	}
}
