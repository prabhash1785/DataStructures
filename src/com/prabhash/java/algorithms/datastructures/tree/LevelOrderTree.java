package com.prabhash.java.algorithms.datastructures.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LevelOrderTree {

	private Node root;
	
	public void addNode(int key, String value) {
		Node newNode = new Node(key, value);
		if(root == null) {
			root = newNode;
		} else {
			Node focusNode = root;
			Node parent;
			while(true) {
				parent = focusNode;
				if(key < focusNode.key) {
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
	
	/**
	 * To print the nodes level by level, we use Breadth First Search traversal for a tree.
	 * For BFS, we have used a Queue data structure to store the nodes at each level.
	 * This method prints all the nodes in one lines from BFS traversal.
	 * Time Complexity: O(n)
	 * Space Complexity: O(n/2) ~ O(n)
	 */
	public void singleLineBFSPrint(Node root) {
		if(root != null) {
			Queue<Node> queue = new LinkedList<Node>();
			queue.add(root);
			
			while(queue.peek() != null) {
				Node temp = queue.remove();
				System.out.print(temp.data + " ");
				if(temp.left != null) {
					queue.add(temp.left);
				}
				if(temp.right != null) {
					queue.add(temp.right);
				}
			}
			
		} else {
			System.err.println("Empty Tree!!");
		}
		
	}
	
	/**
	 * 
	 * This method prints noded level by level in different lines.
	 * Two queues are used. One queue keeps the elements for current level and other queue keeps elements for next level.
	 * When elements in first queue are null, go to the next lines and copy elements from next level queue to current level.
	 * 
	 */
	public void printSeparateLinesByLevel(Node root) {
		Queue<Node> queue1 = new LinkedList<Node>();
		Queue<Node> queue2 = new LinkedList<Node>();
		
		queue1.add(root);
		while(queue1.peek() != null) {
			Node temp = queue1.remove();
			System.out.print(temp.data + " ");
			if(temp.left != null) { //Null checks are important otherwise null will be added to the Queue
				queue2.add(temp.left);
			}
			if(temp.right != null) {
				queue2.add(temp.right);
			}
			if(queue1.peek() == null) {
				System.out.println("\n");
				queue1 = swap(queue1, queue2);
			}
		}
		
	}
	
	/**
	 * This is done using two stacks and one boolean variable which helps in changing the subsequent level from left to right and vice versa.
	 * 
	 */
	public void printLevelNodesInZigZagOrder(Node root) {
		Stack<Node> stack1 = new Stack<Node>();
		Stack<Node> stack2 = new Stack<Node>();
		boolean leftToRight = true; 
		stack1.push(root);
		
		while(!stack1.isEmpty()) {
			Node temp = stack1.pop();
			System.out.print(temp.data + " ");
			
			if(leftToRight) {
				if(temp.left != null) {
					stack2.push(temp.left);
				}
				if(temp.right != null) {
					stack2.push(temp.right);
				}
			} else {
				if(temp.right != null) {
					stack2.push(temp.right);
				}
				if(temp.left != null) {
					stack2.push(temp.left);
				}
			}
			
			if(stack1.isEmpty()) {
				System.out.println("\n");
				leftToRight = !leftToRight; //Inverting the flag for zig zag output
				//Swap the stacks
				Stack<Node> tempStack = new Stack<Node>(); //temp Stack is empty so after swap, Stack2 will become empty
				tempStack = stack1;
				stack1 = stack2;
				stack2 = tempStack;
				
			}
			
		}
		
	}
	
	private Queue<Node> swap(Queue<Node> q1, Queue<Node> q2) {
		if(q2 != null) {
			while(q2.peek() != null) {
				q1.add(q2.remove());
			}
		}
		
		return q1;
	}
	
	public static void main(String[] args) {
		
		LevelOrderTree tree = new LevelOrderTree();
		
		tree.addNode(3, "3");
		tree.addNode(2, "2");
		tree.addNode(5, "5");
		tree.addNode(13, "13");
		tree.addNode(4, "4");
		tree.addNode(1, "1");
		
		//Print nodes by Level in one line
		System.out.println("Nodes by level order in one line:");
		tree.singleLineBFSPrint(tree.root);
		
		//Print nodes by Level in separate lines
		System.out.println("\nNodes by level order in separate lines:\n");
		tree.printSeparateLinesByLevel(tree.root);
		
		//Print nodes by Level in separate lines
		System.out.println("\nNodes by level order in separate lines and in zig-zag order:\n");
		tree.printLevelNodesInZigZagOrder(tree.root);
	}
	
	private static class Node {
		private int key;
		private String data;
		private Node left;
		private Node right;
		
		public Node(int key, String data) {
			this.key = key;
			this.data =  data;
		}
		
		@Override
		public String toString() {
			return key + " " + data;
		}
		
	}

}
