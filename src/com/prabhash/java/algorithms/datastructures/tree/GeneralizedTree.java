/**
 * This is an implementation of N-ary Tree. A binary tree is the special case where n=2.
 * N-ary tree can have n or less number of children  for each nodes.
 * Properties of N-ary Tree:
 * 	- Maximum number of Leaves for a tree of height h: (n ^ h)
 * Applications:
 * 	- Tree Structure of a File System where node is either a file or folder
 */

package com.prabhash.java.algorithms.datastructures.tree;

import java.util.ArrayList;
import java.util.List;

public class GeneralizedTree {
	
	private Node root;
	
	public GeneralizedTree() {
		this.root = null;
	}
	
	public GeneralizedTree(Node node) {
		this.root = node;
	}
	
	public int getNumberOfDescendents(Node node) {
		int count = 0;
		if(node != null) {
			count = node.getChildren().size();
			for(Node child : node.getChildren()) {
				count += child.getNumberOfChildren();
			}
		}		
		
		System.out.println("Number of Descendents are: " + count);
		return count;
	}
	
	public boolean SearchTree(Node node, String key) {
		boolean available = false;
		if(node != null) {
			if(node.getData().equals(key)) {
				available = true;
			} else {
				for(Node child : node.getChildren()) {
					if(child.getData().equals(key)) {
						System.out.println("Found a match!!!");
						available = true;
						break;
					}
				}
			}
		}
		
		return available;
	}
	
	public List<Node> getPreOrderTraversal() {
		List<Node> preOrder = new ArrayList<Node>();
		buildPreOrder(root, preOrder);
		return preOrder;
	}
	
	private void buildPreOrder(Node node, List<Node> preOrder) {
		preOrder.add(node);
		for (Node child : node.getChildren()) {
			buildPreOrder(child, preOrder);
		}
	}

	public List<Node> getPostOrderTraversal() {
		List<Node> postOrder = new ArrayList<Node>();
		buildPostOrder(root, postOrder);
		return postOrder;
	}

	private void buildPostOrder(Node node, List<Node> postOrder) {
		for (Node child : node.getChildren()) {
			buildPostOrder(child, postOrder);
		}
		postOrder.add(node);
	}
	
	public void printFromList(List<Node> node) {
		if(node == null) {
			System.out.println("Empty Tree..");
		} else {
			System.out.println("\nOutput is:");
			for(Node n : node) {
				System.out.print(n.getData() + " ");
			}
		}
	}

	
	public void printImmediateChildren(Node node) {
		if(node == null) {
			System.out.println("Empty Tree..");
		} else {
			System.out.println("\nOutput of tree traversal:");
			for(Node n : node.getChildren()) {
				System.out.print(n.getData() + " ");
			}
		}
	}
	

	public static void main(String[] args) {
		
		Node a = new Node("A");
		GeneralizedTree genTree = new GeneralizedTree(a);
		
		Node b = new Node("B");
		
		a.addChild(b);
		a.addChild(new Node("C"));
		a.addChild(new Node("D"));
		a.addChild(new Node("E"));
		a.addChild(new Node("F"));
		
		b.addChild(new Node("X"));
		b.addChild(new Node("Y"));
		
		System.out.println("Value of Root is: " + genTree.root);
		
		List<Node> preTree = genTree.getPreOrderTraversal();
		genTree.printFromList(preTree);	
		
		List<Node> postTree = genTree.getPreOrderTraversal();
		genTree.printFromList(postTree);
		
		//Print immediate children
		genTree.printImmediateChildren(genTree.root);
		genTree.printImmediateChildren(b);

	}
	
	/*
	 * This is a nested class which provides a defnition of a Node for an n-Ary Tree.
	 * Each node has a value stored in it and has references to n-child nodes. 
	 */	
	private static class Node {
		private String data;
		private List<Node> children;
		
		public Node(String data) {
			this.data = data;
			children = new ArrayList<Node>();
		}
		
		public Node(Node child) {
			this.data = child.getData();
			children = new ArrayList<Node>();
		}
		
		public void addChild(Node child) {
			children.add(child);
		}
		
		public void addChildAtPos(Node child, int index) {
			children.add(index, child);
		}
		
		public void removechildren() {
			this.children.clear();
		}
		
		public Node removeChildrenAtPos(int index) {
			return this.children.remove(index);
		}
		
		public int getNumberOfChildren() {
			return this.getChildren().size();
		}
		
		public boolean hasChildren() {
			return (this.getChildren().size() > 0);
		}

		/**
		 * @return the data
		 */
		public String getData() {
			return data;
		}

		/**
		 * @param data the data to set
		 */
		public void setData(String data) {
			this.data = data;
		}

		/**
		 * @return the children
		 */
		public List<Node> getChildren() {
			return children;
		}

		/**
		 * @param children the children to set
		 */
		public void setChildren(List<Node> children) {
			this.children = children;
		}
		
		@Override
		public boolean equals(Object obj) {
			boolean flag = false;
			if((obj == null) || (obj.getClass() != this.getClass())) {
				flag = false;
			} else {
				Node n = (Node) obj;
				if(n.getData() == this.getData()) {
					flag = true;
				}
			}
			
			return flag;
		}
		
		@Override
		public int hashCode() {
			int hashCode = 3;
			final int primeFactor = 23;
			hashCode = hashCode * primeFactor + (this.getData().hashCode());			
			return hashCode;
		}
		
		@Override
		public String toString() {
			return this.data;
		}
	}

}
