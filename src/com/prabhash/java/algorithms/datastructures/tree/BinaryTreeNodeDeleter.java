package com.prabhash.java.algorithms.datastructures.tree;

public class BinaryTreeNodeDeleter {
	
	/**
	 * Method 1:
	 * 
	 * To delete a node from Binary Tree, do following:
	 * - Find the node to be deleted in the tree. If node not found then just return as node does not exist in tree.
	 * - Find the right most node (leaf node) in the tree
	 * - Replace node to be deleted with the right most leaf node
	 * - Now delete the right most leaf node
	 * 
	 * This is not the best algorithm as instead of finding the right most node in the tree, we can replace node to be deleted with the
	 * left most or right mode node from it's sub-tree. This will be more optimal solution. See method 2 below.
	 * 
	 * @param root
	 * @param data
	 * @return
	 */
	public static boolean deleteNode(Node root, int data) {

		if(root == null) {
			System.out.println("Tree is empty");
			return false;
		}

		Node nodeToBeDeleted = getNodeToBeDeleted(root, data);
		
		// Did not find a matching node
		if(nodeToBeDeleted == null) {
			return false;
		}
		
		// if leaf node
		if(nodeToBeDeleted.left == null && nodeToBeDeleted.right == null) {
			return deleteLeafNode(root, nodeToBeDeleted);
		}

		Node deepestNode = findRightMostDeepNode(root);
		nodeToBeDeleted.data = deepestNode.data;

		return deleteLeafNode(root, deepestNode);

	}

	/**
	 * Method 2: Optimized
	 * 
	 * Here is how node is deleted from Binary Tree:
	 * - Recurse through tree to find the node to be deleted. If node is not found then return false.
	 * - If node to be deleted is found in tree then there are 4 possible options:
	 * 		- If node is a leaf then set parent's left or right child to null whichever side this node is linked.
	 * 		- [2 possible options] If node has only one sub-tree i.e. either left or right then just overwrite the node with root of left or
	 * 			right child tree
	 * 		- If node has both left and right child then find the right most leaf node on either left or right sub tree of this node
	 * 			and overwrite node's data with right most leaf data and then  delete right most leaf node.  
	 * 
	 * Time Complexity: O(n)
	 * 
	 * @param root
	 * @param data
	 * @return boolean
	 */
	public static boolean deleteNodeOptimized(Node root, int data) {

		if(root == null) {
			return false;
		}
		
		if(root.data == data) {

			if(root.left != null && root.right != null) {

				Node rightMostLeafNode = findRightMostDeepNode(root.right);
				root.data = rightMostLeafNode.data;
				return deleteLeafNode(root, rightMostLeafNode);
			} else if(root.left != null) {
				
				Node leftMostLeafNode = findLeftMostDeepNode(root.left);
				root.data = leftMostLeafNode.data;
				return deleteLeafNode(root, leftMostLeafNode);
			} else if(root.right != null) {
				
				Node rightMostLeafNode = findRightMostDeepNode(root.right);
				root.data = rightMostLeafNode.data;
				return deleteLeafNode(root, rightMostLeafNode);
			} 
		}
		
		return deleteNodeOptimized(root.left, data) || deleteNodeOptimized(root.right, data);
	
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

		root.right = new Node(6);
		root.right.left = new Node(7);
		root.right.right = new Node(9);

		int nodeToBeDeleted = 1;
		
		if(deleteNode(root, nodeToBeDeleted)) {
			System.out.println(nodeToBeDeleted + " is deleted");
		} else {
			System.out.println("Did not find " + nodeToBeDeleted + " in the tree");
		}
		
//		if(deleteNodeOptimized(root, nodeToBeDeleted)) {
//			System.out.println(nodeToBeDeleted + " is deleted");
//		} else {
//			System.out.println("Did not find " + nodeToBeDeleted + " in the tree");
//		}
	}

}
