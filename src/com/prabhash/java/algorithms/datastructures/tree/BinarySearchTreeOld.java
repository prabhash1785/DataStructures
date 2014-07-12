package com.prabhash.java.algorithms.datastructures.tree;

public class BinarySearchTreeOld {

	private Node root;
	
	public void addNode(int key, String name) {
		
		Node newNode = new Node(key, name);
		
		if(root == null) {
			root = newNode;
		} else {
			
			Node focusNode = root; //used for traversing the tree
			
			// future parent of new node
			Node parent;
			
			while(true) {
				
				parent = focusNode;
				
				if(key < focusNode.key) {
					
					focusNode = focusNode.leftChild;
					
					if(focusNode == null) {
						parent.leftChild = newNode;
						return;
					}
					
				} else {
					
					focusNode = focusNode.rightChild;
					if(focusNode == null) {
						parent.rightChild = newNode;
						return;
					}
				}
			}
	
		}

	}
	
	public void inOrderTraversal(Node focusNode) {
		
		if(focusNode != null) {
			inOrderTraversal(focusNode.leftChild);
			
			System.out.println(focusNode);
			
			inOrderTraversal(focusNode.rightChild);
			
		}
				
	}
	
	public void preOrderTraversal(Node focusNode) {
			
			if(focusNode != null) {
				System.out.println(focusNode);
				
				preOrderTraversal(focusNode.leftChild);
				
				preOrderTraversal(focusNode.rightChild);		
				
			}
					
		}
	
	public void postOrderTraversal(Node focusNode) {
		
		if(focusNode != null) {
					
			postOrderTraversal(focusNode.leftChild);
			
			postOrderTraversal(focusNode.rightChild);
			
			System.out.println(focusNode);
			
		}
				
	}
	
	public static void main(String[] args) {
		BinarySearchTreeOld binaryTree = new BinarySearchTreeOld();
		binaryTree.addNode(20, "US");
		binaryTree.addNode(10, "England");
		binaryTree.addNode(70, "Australia");
		binaryTree.addNode(8, "Denmark");
		binaryTree.addNode(15, "India");
		binaryTree.addNode(200, "Russia");
		binaryTree.addNode(25, "Japan");
		binaryTree.addNode(30, "China");
		
		System.out.println("Here is inorder traversal...");
		binaryTree.inOrderTraversal(binaryTree.root);
		
		System.out.println("\nHere is preOrder traversal...");
		binaryTree.preOrderTraversal(binaryTree.root);
		
		System.out.println("\nHere is postorder traversal...");
		binaryTree.postOrderTraversal(binaryTree.root);
		
	}
	
	private class Node {
		
		private int key;
		private String name;
		
		private Node leftChild;
		private Node rightChild;
		
		public Node(int key, String name) {
			this.key = key;
			this.name = name;
		}
		
		@Override
		public String toString() {
			return name + " has the key " + key;
		}
	}
	
}
