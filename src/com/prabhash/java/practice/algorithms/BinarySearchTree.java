package com.prabhash.java.practice.algorithms;

public class BinarySearchTree {
	
	private Node root;
	
	/**
	 * Iterative method to create a Binary Tree
	 * 
	 * @param id
	 * @param data
	 */
	public void addNode(int id, String data) {
		Node newNode = new Node(id, data);
		
		if(root == null) {
			root = newNode;
			return;
		}
		
		Node tempNode = root;
		
		while(true) {
			
			if(tempNode != null) {
				
				if(id <= tempNode.id) {
					
					if(tempNode.left == null) {
						tempNode.left = newNode;
						return;
					}
					
					tempNode = tempNode.left;
					
					
				} else {
					
					if(tempNode.right == null) {
						tempNode.right = newNode;
						return;
					}
					
					tempNode = tempNode.right;
					
				}
				
			}
			
		}
		
		
	}
	
	/**
	 * Add nodes to Binary Search Tree using Recursive algorithm
	 * 
	 */
	public void addNodeRecursively(int id, String data) {
		Node newNode = new Node(id, data);
		
		if(root == null) {
			root = newNode;
			return;
		}
		
		Node tempNode = root;
		
		createBSTRecursively(tempNode, newNode);
	}
	
	private void createBSTRecursively(Node temNode, Node newNode) {
				
		if(newNode.id <= temNode.id) {
			
			if(temNode.left == null) {
				temNode.left = newNode;
				return;
			}
			
			createBSTRecursively(temNode.left, newNode);
			
		} else {
			
			if(temNode.right == null) {
				temNode.right = newNode;
				return;
			}
			
			createBSTRecursively(temNode.right, newNode);
		}
		
	}
	
	/**
	 * Recursive Inorder traversal of BST
	 * 
	 * @author prrathore
	 *
	 */
	public void inOrder(Node root) {
		
		if(root == null) {
			return;
		}
		
		inOrder(root.left);
		System.out.print(root.id + " ");
		inOrder(root.right);
		
	}
	
	private class Node {
		
		private int id;
		private String data;
		private Node left;
		private Node right;
		
		public Node(int id, String data) {
			this.id = id;
			this.data = data;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}
		
	}

	public static void main(String[] args) {
		
		BinarySearchTree bst = new BinarySearchTree();
		
//		bst.addNode(23, "23");
//		bst.addNode(28, "28");
//		bst.addNode(3, "3");
//		bst.addNode(56, "56");
//		bst.addNode(31, "31");
//		bst.addNode(19, "19");
//		bst.addNode(77, "77");
//		bst.addNode(36, "36");
//		bst.addNode(1, "1");
//		bst.addNode(11, "11");
//		bst.addNode(55, "55");
		
		bst.addNodeRecursively(23, "23");
		bst.addNodeRecursively(28, "28");
		bst.addNodeRecursively(3, "3");
		bst.addNodeRecursively(56, "56");
		bst.addNodeRecursively(31, "31");
		bst.addNodeRecursively(19, "19");
		bst.addNodeRecursively(77, "77");
		bst.addNodeRecursively(36, "36");
		bst.addNodeRecursively(1, "1");
		bst.addNodeRecursively(11, "11");
		bst.addNodeRecursively(55, "55");
		
		System.out.println("Inorder Traversal of Binary Search Tree:");
		bst.inOrder(bst.root);
		

	}

}
