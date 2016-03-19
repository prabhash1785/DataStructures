package com.prabhash.java.algorithms.datastructures.tree;

public class BinaryTreeNodeDeleter {
	
	/**
	 * Recursively delete a node from Binary Tree. This should be the preferred way to delete node from BT or BST.
	 * 
	 * Algorithm:
	 * - Recursively traverse Binary Tree looking for node to be deleted. At each recursive step, mutate left and right child of node. This will
	 * enable us to easily delete node when the desired node is found for deletion.
	 * - If node to be deleted found and it has both left and right child sub-trees, then find the right most leaf node, swap their data
	 * and then recursively delete right most leaf node
	 * - If node to be deleted has only left or right child then just overwrite node with their left or right child
	 * - If node to be deleted is a leaf then just return null, this will recursively leaf when recursive stack backtracks.
	 * 
	 * Time Complexity: O(n)
	 * 
	 * @param root
	 * @param data
	 * @return
	 */
	public static Node deleteNodeRecursively(Node root, int data) {

		if(root == null) {
			return null;
		}

		if(root.data == data) {
			
			if(root.left != null && root.right != null) {
				Node rightMostNode = findRightMostDeepNode(root.right);
				root.data = rightMostNode.data;
				root.right = deleteNodeRecursively(root.right, rightMostNode.data);
			} else if(root.left != null) {
				return root.left;
			} else if(root.right != null) {
				return root.right;
			} else {
				return null;
			}
		}

		root.left = deleteNodeRecursively(root.left, data); // Mutate left child from recursion output
		root.right = deleteNodeRecursively(root.right, data); // Mutate right child from recursion output

		return root; // return the main root of the tree, this is not the deleted node
	}
	
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
	
	public static void printInOrder(Node root) {
		if(root != null) {
			printInOrder(root.left);
			System.out.print(root.data + " ");
			printInOrder(root.right);
		}
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
		root.left.left.left = new Node(20);

		root.right = new Node(6);
		root.right.left = new Node(7);
		root.right.right = new Node(9);

		int nodeToBeDeleted = 4;
		
		// Test for Iterative method of node deletion
		if(deleteNode(root, nodeToBeDeleted)) {
			System.out.println(nodeToBeDeleted + " is deleted");
		} else {
			System.out.println("Did not find " + nodeToBeDeleted + " in the tree");
		}
		
		System.out.println("Inorder of tree");
		printInOrder(root);
		
		// Test for recursive method of node deletion
		root = deleteNodeRecursively(root, nodeToBeDeleted);
		
		System.out.println("\nInorder after deletion");
		printInOrder(root);
	}
}
