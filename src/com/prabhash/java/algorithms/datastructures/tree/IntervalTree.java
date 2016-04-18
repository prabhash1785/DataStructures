package com.prabhash.java.algorithms.datastructures.tree;

/**
 * Interval Tree
 * 
 * @author prrathore
 *
 */
public class IntervalTree {
	
	private Node root;
	
	public IntervalTree() {
		this(null);
	}
	
	public IntervalTree(Node root) {
		this.root = root;
	}
	
	/**
	 * Insert a new interval to this Interval Tree instance.
	 * 
	 * @param node
	 */
	public void insert(Node node) {
		
		if(node == null) {
			throw new IllegalArgumentException();
		}
		
		if(root == null) {
			root = node;
			return;
		}
		
		Node current = root;
		Node parent = root;
		while(true) {
			
			parent = current;
			if(node.low <= current.low) {
				current = current.left;
				if(current == null) {
					parent.left = node;
					
					if(parent.max < node.max) {
						parent.max = node.max;
					}
					return;
				}
			} else {
				
				current = current.right;
				if(current == null) {
					
					parent.right = node;
					if(parent.max < node.max) {
						parent.max = node.max;
					}
					return;
				}
			}
			// keep updating max along the way for all nodes in the tree
			if(current.max < node.max) {
				current.max = node.max;
			}
		}
	}
	
	/**
	 * Find an overlapping interval.
	 * 
	 * Time Complexity: O(log n)
	 * 
	 * @param node
	 * @return
	 */
	public Node findOverlappingInterval(Node node) {
		
		if(node == null) {
			throw new IllegalArgumentException();
		}
		
		if(root == null) {
			return null;
		}
		
		return findOverlappingIntervalHelper(this.root, node);
	}
	
	public Node findOverlappingIntervalHelper(Node root, Node node) {
		
		if(root == null) {
			return null;
		}
		
		if(node.low <= root.high && root.low <= node.high) {
			return root;
		} else if(root.left != null && root.left.max >= node.low) {
			return findOverlappingIntervalHelper(root.left, node);
		} else {
			return findOverlappingIntervalHelper(root.right, node);
		}
	}
	
	/**
	 * Inorder traversal
	 * 
	 * @param root
	 */
	public void inOrder(Node root) {
		
		if(root != null) {
			inOrder(root.left);
			System.out.println(root);
			inOrder(root.right);
		}
	}
	
	/**
	 * Node of an interval tree
	 * 
	 * low - lower interval value. This also acts as the key for each Interval tree node.
	 * high - higher interval value
	 * max - max value of subtree rooted at that node
	 * left - left sub-tree node
	 * right - right sub-tree node
	 * 
	 */
	public static class Node {
		
		private int low;
		private int high;
		private int max;
		
		private Node left;
		private Node right;
		
		public Node(int low, int high) {
			this.low = low;
			this.high = high;
			this.max = high;
		}
		
		@Override
		public String toString() {
			return this.low + " " + this.high + " ==> " + this.max;
		}
	}
	
	public static void main(String[] args) {
		
		final IntervalTree tree = new IntervalTree(new Node(16, 21));
		tree.insert(new Node(8, 9));
		tree.insert(new Node(25, 30));
		tree.insert(new Node(5, 8));
		tree.insert(new Node(15, 23));
		tree.insert(new Node(17, 19));
		tree.insert(new Node(26, 26));
		tree.insert(new Node(0, 3));
		tree.insert(new Node(6, 10));
		tree.insert(new Node(19, 20));
		
		final Node root = tree.root;
		System.out.println("Inorder traversal of tree is:");
		tree.inOrder(root);
		
		// find overlapping interval
		Node newInterval = new Node(24, 28);
		Node overlappingInterval = tree.findOverlappingInterval(newInterval);
		System.out.println("Here is overlapping interval: " + newInterval + " <==> " + overlappingInterval);
	}
}
