package com.prabhash.java.algorithms.datastructures.tree;

public class DeepestTreeNodeDeleter {

	private static Node root;

	public static boolean deleteDeepestNode(int data) {

		if(root == null) {
			System.out.println("Tree is empty");
			return false;
		}

		Node nodeToBeDeleted = getNodeToBeDeleted(root, data);

		// Did not find a matching node
		if(nodeToBeDeleted == null) {
			return false;
		}

		Node deepestNode = findDeepestNode(root);
		nodeToBeDeleted.data = deepestNode.data;

		return deleteDeepestNodeHelper(deepestNode);

	}

	private static Node getNodeToBeDeleted(Node root, int data) {

		if(root == null) { // base condition
			return null;
		}

		Node left = getNodeToBeDeleted(root.left, data);
		if(left != null) {
			return left;
		}

		Node right = getNodeToBeDeleted(root.right, data);
		if(right != null) {
			return right;
		}

		if(root.data == data) {
			return root;
		}

		return null;

	}

	private static Node findDeepestNode(Node root) {

		Node deepestNode = null;

		if(root != null) {
			deepestNode = root;
			deepestNode = findDeepestNode(root.left);
			deepestNode = findDeepestNode(root.right);
		}

		return deepestNode;
	}

	private static boolean deleteDeepestNodeHelper(Node node) {

		if(root == null) {
			return false;
		}

		if(root.left == node) {
			root.left = null;
			return true;
		}

		if(root.right == node) {
			root.right = null;
			return true;
		}

		return deleteDeepestNodeHelper(root.left) || deleteDeepestNodeHelper(root.right);
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

		root = new Node(8);
		
		root.left = new Node(4);
		root.left.left = new Node(1);
		root.left.right = new Node(3);

		root.right = new Node(6);
		root.right.left = new Node(7);
		root.right.right = new Node(9);

		int nodeToBeDeleted = 6;

		if(deleteDeepestNode(6)) {
			System.out.println(nodeToBeDeleted + " is deleted");
		} else {
			System.out.println("Did not find " + nodeToBeDeleted + " in the tree");
		}
	}

}
