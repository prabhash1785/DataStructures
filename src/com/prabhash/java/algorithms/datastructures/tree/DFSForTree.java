package com.prabhash.java.algorithms.datastructures.tree;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class DFSForTree {

	private Node root;
	
	private static class Node {
		private int key;
		private String data;
		private Node left;
		private Node right;
		
		public Node(int key, String data) {
			this.key = key;
			this.data = data;
		}
		
		@Override
		public String toString() {
			return key + " " + data;
		}
	}
	
	public void addNode(int key, String data) {
		System.out.println("key: " + key + " data: " + data);
		Node newNode = new Node(key, data);
		if(root == null) {
			root = newNode;
		} else {
			Node focusNode = root;
			Node parent;
			while(true) {
				parent = focusNode;
				if(key <= focusNode.key) {
					focusNode = focusNode.left;
					if(focusNode == null) {
						parent.left = newNode;
						return;
					}
				} else {
					focusNode = focusNode.right;
					if(focusNode == null) {
						parent.right = newNode;
						return;
					}
				}
			}
		}
	}
	
	public void createBSTFromList(Map<Integer, String> map) {
		Set<Integer> set = map.keySet();
		Iterator<Integer> iterator = set.iterator();
		while(iterator.hasNext()) {
			int k = iterator.next();
			addNode(k, map.get(k));			
		}
	}
	
	/**
	 * This is exactly same as DFS Traversal. 
	 */
	public void preOrder(Node root) {
		if(root != null) {
			System.out.print(root.data + " ");
			preOrder(root.left);
			preOrder(root.right);
		}
	}
	
	/**
	 * Form of a DFS Traversal.
	 * Inorder Traversal sorts the elements of a Binary Search Tree.
	 *  
	 */
	public void inOrder(Node root) {
		if(root != null) {
			inOrder(root.left);
			System.out.print(root.data + " ");
			inOrder(root.right);
		}
	}
	
	/**
	 * Form of a DFS Traversal.
	 *  
	 */
	public void postOrder(Node root) {
		if(root != null) {
			postOrder(root.left);
			postOrder(root.right);
			System.out.print(root.data + " ");
		}
	}
	
	/**
	 * This method will traverse a given tree using Dpeth First Search (DFS).
	 * For DFS, a tree uses a supporting data structure called a Stack to store the child nodes of a tree and keep visiting the immediate nodes
	 * of each child node until you reach the end of a tree.
	 * DFSTraversal is same as Pre-Order traversal of a tree. 
	 * 
	 */
	public void DFSTraversal(Node root) {
		if(root == null) {
			System.err.println("Empty Tree!!");
		} else {
			Stack<Node> stack =  new Stack<Node>();
			stack.push(root);
			while(!stack.isEmpty()) {
				Node temp = stack.pop();
				System.out.print(temp.data + " ");
				if(temp.left != null) {
					stack.push(temp.left);					
				}
				if(temp.right != null) {
					stack.push(temp.right);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		DFSForTree tree = new DFSForTree();
		Map<Integer, String> inputMap = new HashMap<Integer, String>(); 
		inputMap.put(10, "10");
		inputMap.put(5, "5");
		inputMap.put(15, "15");
		inputMap.put(20, "20");
		inputMap.put(4, "4");
		inputMap.put(14, "14");
		inputMap.put(25, "25");
		inputMap.put(19, "19");
		inputMap.put(7, "7");
		
		tree.createBSTFromList(inputMap);
		
		System.out.println("\nHere is the Pre-Order traversal of this tree: ");
		tree.preOrder(tree.root);
		
		System.out.println("\nHere is In-Order traversal of this tree: ");
		tree.inOrder(tree.root);
		
		System.out.println("\nHere is the Post-Order traversal of this tree: ");
		tree.postOrder(tree.root);
		
		System.out.println("\nHere is the DFS Traversal of this tree: ");
		tree.DFSTraversal(tree.root);

	}

}
