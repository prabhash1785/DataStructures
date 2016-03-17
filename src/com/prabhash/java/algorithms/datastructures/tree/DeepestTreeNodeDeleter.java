package com.prabhash.java.algorithms.datastructures.tree;

public class DeepestTreeNodeDeleter {

	/**
	 * To delete a node from Binary Tree, do following:
	 * - Find the node to be deleted in the tree. If node not found then just return as node does not exist in tree.
	 * - Find the right most node (leaf node) in the tree
	 * - Replace node to be deleted with the right most leaf node
	 * - Now delete the right most leaf node
	 * 
	 * This is not the best algorithm as instead of finding the right most node in the tree, we can replace node to be deleted with the
	 * left most or right mode node from it's sub-tree. This will be more optimal solution.
	 * 
	 * @param root
	 * @param data
	 * @return
	 */
	public static boolean deleteDeepestNode(Node root, int data) {

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

		return deleteDeepestNodeHelper(root, deepestNode);

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

		if(root == null) {
			return null;
		}
		
		// if root.right is null then this must be the right most node
		if(root.right == null) {
			return root;
		}
		
		return findDeepestNode(root.right);
	}

	private static boolean deleteDeepestNodeHelper(Node root, Node nodeToBeDeleted) {

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

		return deleteDeepestNodeHelper(root.left, nodeToBeDeleted) || deleteDeepestNodeHelper(root.right, nodeToBeDeleted);
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

		root.right = new Node(6);
		root.right.left = new Node(7);
		root.right.right = new Node(9);

		int nodeToBeDeleted = 6;

		if(deleteDeepestNode(root, 6)) {
			System.out.println(nodeToBeDeleted + " is deleted");
		} else {
			System.out.println("Did not find " + nodeToBeDeleted + " in the tree");
		}
	}

}
