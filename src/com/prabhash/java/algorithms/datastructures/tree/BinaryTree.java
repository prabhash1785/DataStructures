package com.prabhash.java.algorithms.datastructures.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * This is a Binary Tree not a Binary Search Tree (BST)
 * This class will have all the operations of a Binary Tree
 * @author Prabhash Rathore
 *
 */
public class BinaryTree {
	
	private Node root;
	
	public BinaryTree() {
		
	}
	
	public Node getRoot() {
		return root;
	}
	
	public void setRoot(Node root) {
		this.root = root;
	}
	
	/**
	 * Create a Binary Tree manually by adding nodes one by one to the root and the n subsequent nodes.
	 * 
	 * This tree looks like this:
	 * 							10
	 * 				4							14
	 * 		24				8			23				9
	 * 											   30				
	 * 												   19
	 * 
	 */
	public void createBinaryTree() {
		root = new Node(10);
		
		root.setLeftChild(new Node(4));
		root.setRightChild(new Node(14));
		
		root.getLeftChild().setLeftChild(new Node(24));
		root.getLeftChild().setRightChild(new Node(8));
		
		root.getRightChild().setLeftChild(new Node(23));
		root.getRightChild().setRightChild(new Node(9));
		
		root.getRightChild().getRightChild().setLeftChild(new Node(30));
		root.getRightChild().getRightChild().getLeftChild().setRightChild(new Node(19));		
		
	}
	
	/**
	 * Find number of nodes in Tree
	 * 
	 */
	public int findNumberOfNodes(Node root) {
		if(root == null) {
			return 0;
		}
		
		return findNumberOfNodes(root.getLeftChild()) + findNumberOfNodes(root.getRightChild()) + 1;
	}
			
	/**
	 * Pre-Order Traversal of Binary Tree
	 * 
	 */
	public void preOrder(Node root) {
		if(root != null) {
			System.out.print(root.getKey() + " ");
			preOrder(root.getLeftChild());
			preOrder(root.getRightChild());
		}
	}
	
	
	/**
	 * Inorder Traversal
	 * 
	 */
	public void inOrder(Node root) {
		if(root != null) {
			inOrder(root.getLeftChild());
			System.out.print(root.getData() + " ");
			inOrder(root.getRightChild());
		}
	}
	
	/**
	 * Height of a Tree
	 */
	public int getHeight(Node root) {
		if(root == null) {
			return 0;
		}
		int leftHeight = getHeight(root.getLeftChild());
		int rightHeight =getHeight(root.getRightChild());
		
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	/**
	 * Check if a tree is balanced
	 * A tree is balanced if the height of subtrees for any node is at most 1.
	 * Algorithm:
	 * 	- Find height of left and right sub-trees.
	 * 	- If height of left and right sub-trees > 1, not a Balanced Tree.
	 */
	public boolean isBalanced(Node root) {
		if(root == null) {
			return true;
		}
		if(checkSubTreeHeight(root) == -1) {
			return false;
		} else {
			return true;
		}
		
	}
	
	/**
	 * Utility Method to check if a tree is balanced by comparing the height of subtrees for every node.
	 * 
	 */
	private int checkSubTreeHeight(Node root) {
		if(root == null) {
			return 0;
		}
		int leftHeight = checkSubTreeHeight(root.getLeftChild());
		//Added to break out of recursion early as soon as a node is discovered with height diff > 1
		if(leftHeight == -1) {
			return -1;
		}
		
		int rightHeight = checkSubTreeHeight(root.getRightChild());
		//Added to break out of recursion early as soon as a node is discovered with height diff > 1
		if(rightHeight == -1) {
			return -1;
		}
		
		int height = leftHeight - rightHeight;
		if(Math.abs(height) > 1) {
			return -1;
		} else {
			return Math.max(leftHeight, rightHeight) + 1;
		}
		
	}
	
	/**
	 * Check if this Binary Tree is Binary Search Tree
	 * @param root
	 */
	public boolean checkBST(Node root) {
		
		return checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private boolean checkBST(Node root, int min, int max) {
		if(root == null) {
			return true;
		}
		if(root.getKey() < min || root.getKey() > max) {
			return false;
		}
		return checkBST(root.getLeftChild(), min, root.getKey()) && checkBST(root.getRightChild(), root.getKey(), max);
	}
	
	/**
	 * Find ancestor of two nodes in a Binary Tree
	 * Algorithm:
	 * 	- Check if both p and q are on different sides of a node then that node is the ancestor
	 * 	- else go to left subtree or right subtree
	 * Time Complexity: O(n)
	 *  
	 */
	public Node commonAncestor(Node root, Node p, Node q) {
		if((!covers(root, p)) || (!covers(root, q))) { //if either p or q are not in tree, return null
			return null;
		}
		
		return commonAncestorHelper(root, p, q);
	}
	
	private boolean covers(Node root, Node p) {
		if(root == null)
			return false;
		if(root.getKey() == p.getKey())
			return true;
		return covers(root.getLeftChild(), p) || covers(root.getRightChild(), p);
	}
	
	private Node commonAncestorHelper(Node root, Node p, Node q) {
		if(root == null)
			return null;
		if(root.getKey() == p.getKey() || root.getKey() == q.getKey())
			return root;
		
		boolean isPOnLeft = covers(root.getLeftChild(), p);
		boolean isQOnLeft = covers(root.getLeftChild(), q);
		
		//If p and q are on different side then return root as ancestor
		if(isPOnLeft != isQOnLeft)
			return root;
		
		Node childSide = isPOnLeft ? root.getLeftChild() : root.getRightChild();
		return commonAncestorHelper(childSide, p, q);
	}
	
	/**
	 * Check the sum of all the paths in a tree from root to the leaf. Return true if at least one path exists from root to leaf with a given sum or else
	 * return false.
	 * 
	 * Algorithm using BFS:
	 * Add all node to a queue and store sum value of each node to another queue. When it is a leaf node, check the stored sum value.
	 * This is a typical breadth first search(BFS) problem. 
	 * Ref: http://www.programcreek.com/2013/01/leetcode-path-sum/
	 * 
	 */
	public boolean checkSumFromRootToLeafUsingBFS(Node root, int sum) {
		
		if(root == null) {
			return false;
		}
		
		Queue<Node> nodes = new LinkedList<Node>(); //Stores all the nodes of a tree
		Queue<Integer> values = new LinkedList<Integer>(); //Stores the sum of values
		
		nodes.add(root);
		values.add(root.getKey());
		
		while(nodes.peek() != null) {
			Node curr = nodes.poll();
			int sumValue = values.poll();
			
			if(curr.getLeftChild() == null && curr.getRightChild() == null && sumValue == sum) {
				return true;
			}
			
			if(curr.getLeftChild() != null) {
				nodes.add(curr.getLeftChild());
				values.add(sumValue + curr.getLeftChild().getKey());
			}
			
			if(curr.getRightChild() != null) {
				nodes.add(curr.getRightChild());
				values.add(sumValue + curr.getRightChild().getKey());
			}
			
		}
		
		return false;
		
	}
	
	/**
	 * Check sum of nodes from root to leaf in a Tree.
	 * 
	 * This can be solved iteratively by using DFS algorithm in addition to BFS. Use 2 Stacks, one to store nodes and other to
	 * store cumulative sum of nodes from root to current node.
	 * When node is a leaf node, compare sum, if they are equal return true.
	 * 
	 */
	public boolean checkSumFromRootToLeafUsingDFS(Node root, int sum) {
		if(root == null) {
			return false;
		}
		
		Stack<Node> stack = new Stack<Node>();
		Stack<Integer> pathSum = new Stack<Integer>();
		
		stack.push(root);
		pathSum.push(root.getKey());
		
		while(!stack.isEmpty()) {
			Node temp = stack.pop();
			int tempSum = pathSum.pop();
			
			if(temp.getLeftChild() == null && temp.getRightChild() == null && tempSum == sum) {
				return true;
			}
			
			if(temp.getLeftChild() != null) {
				stack.push(temp.getLeftChild());
				pathSum.push(tempSum + temp.getLeftChild().getKey());
			}
			
			if(temp.getRightChild() != null) {
				stack.push(temp.getRightChild());
				pathSum.push(tempSum + temp.getRightChild().getKey());
			}
			
		}
		
		return false;
		
	}
	
	
	/**
	 * Recursive Method to find the path in a tree whose sum of nodes is equal to the given number.
	 * 
	 * Given a tree and a sum, return true if there is a path from the root
	 * down to a leaf, such that adding up all the values along the path equals the given sum.
	 * 
	 * Strategy: subtract the node value from the sum when recurring down, and check to see if the sum is 0 when you run out of tree.  
	 *  
	 */
	public boolean hasPathSumRecursive(Node root, int sum) {
		
		 // return true if we run out of tree and sum==0
		if(root == null) {
			return (sum == 0);
		} else {
			// otherwise check both subtrees
			int subSum = sum - root.getKey();
			return hasPathSumRecursive(root.getLeftChild(), subSum) || hasPathSumRecursive(root.getRightChild(), subSum);
		}
		
	}
	
	/**
	 * Given a binary tree, print out all of its root-to-leaf paths, one per line. Uses a recursive helper to do the work.
	 * The nodes along the paths are saved in an array. When we hit a leaf, we print the nodes in the array from root to leaf
	 *  
	 */
	public void printPaths(Node root) {
		if(root == null) {
			return;
		}
		
		int[] path = new int[20]; //Path Array which will store the nodes while recursively traversing the trees, initializing the array with 20 memory blocks
		
		printPaths(root, path, 0);
		
	}
	
	/**
	 * Utility Method which Recursively prints the paths from root to leaf of a tree.
	 * 
	 * @param root
	 * @param path
	 * @param pathLength
	 */	
	private void printPaths(Node root, int[] path, int pathLength) {
		if(root == null) { //Base Condition 
			return;
		}
		
		path[pathLength] = root.getKey();
		pathLength++;
		
		if(root.getLeftChild() == null && root.getRightChild() == null) {
			sendPathToConsole(path, pathLength); //Print the paths
		} else {
			printPaths(root.getLeftChild(), path, pathLength);
			printPaths(root.getRightChild(), path, pathLength);
		}
		
	}
	
	/**
	 * Utility method to print nodes from path array
	 * @param path
	 * @param pathLength
	 */
	private void sendPathToConsole(int[] path, int pathLength) {
		if(path == null) {
			return;
		}
		
		for(int i = 0; i < pathLength; i++) {
			System.out.print(path[i] + " ");
		}
		
		System.out.println(); //Line Break
	}
	
	/**
	 * Recursively find the maximum element in a Binary Tree
	 * Design:
	 * Keep a max variable on each recursive stack and compare with current if max is less than current the assign current to max.
	 * 
	 * Time Complexity: O(n)
	 * Space Complexity: O(n)
	 * 
	 */
	public int findMax(Node root, int max) {

		//Base Condition for Recursion
		if(root == null) {
			return max;
		}
		
		if(root.getKey() > max) {
			max = root.getKey();
		}
		
		max = findMax(root.getLeftChild(), max);
		max = findMax(root.getRightChild(), max);
		
		return max;

	} 
	
	
	/**
	 * 
	 * Find number of nodes in a Binary Tree at the last level.
	 * 	Can be done using BFS/Level Order Traversal.
	 * 	Use 2 stack, one to store current level nodes and other to store next level nodes.
	 * 	When size of next level is zero, print sizeOfCurrentLevel.
	 * 
	 */

	public int numberOfLeavesAtLastLevel(Node root) {

		//Boundary Condition
		if(root == null) {
			return 0;
		}
		
		Queue<Node> current = new LinkedList<Node>();
		Queue<Node> next = new LinkedList<Node>();
		current.add(root);
		int curSize = 1; //to be returned as output as number of leaves
		int nextSize = 0; //number of nodes in next level
		
		while(current.peek() != null) {
			
			Node temp = current.poll();
			
			if(temp.getLeftChild() != null) {
				next.add(temp.getLeftChild());
				nextSize++;
			}
			
			if(temp.getRightChild() != null) {
				next.add(temp.getRightChild());
				nextSize++;
			}
			
			if(current.peek() == null) { //when finished traversing all the nodes in current level
				
				if(nextSize == 0) { //if no child nodes in next level then current level must have all the leaves
					return curSize;
				} else {				
					current = swap(current, next);
					curSize = nextSize;
					nextSize = 0;					
				}
			}			
		
		}
		
		return curSize;

	}
	
	/**
	 * Utility method to swap Queues
	 * 
	 */
	private Queue<Node> swap(Queue<Node> q1, Queue<Node> q2) {
		if(q2 != null) {
			while(q2.peek() != null) {
				q1.add(q2.remove());
			}
		}
		
		return q1;
	}
	
	/**
	 * 
	 * Given following properties of a Binary Tree, construct a Tree.
	 * Properties:
	 * 	- A Leaf is represented as 'L'.
	 *  - An internal node is represented as 'I'
	 *  - A node can either have zero children or two children
	 *  
	 *  Given PreOrder Traversal - ILILL
	 *  
	 *   Ref: Narasimha Karumanchi
	 * 
	 */
	public static Node constructBinaryTreeFromPreOrder(char[] c, int i) {
		if(c == null || c.length == 0) {
			return null;
		}
		
		char rootVal = c[i];
		Node root = new Node(rootVal);
		
		if(rootVal == 'L') { //if it's a Leaf then return as it can't have any child nodes
			return root;
		}
		
		i = i + 1;
		root.setLeftChild(constructBinaryTreeFromPreOrder(c, i));
		
		i = i + 1;
		root.setRightChild(constructBinaryTreeFromPreOrder(c, i));		
		
		return root;
	}
	
	
	/**
	 * Unit Testing with the main method
	 *
	 */
	public static void main(String[] args) {
		
		//Create a binary tree
		BinaryTree tree = new BinaryTree();
		tree.createBinaryTree();
		
		// Print inOrder of the tree
		System.out.println("\nInorder traversal of tree:");
		tree.inOrder(tree.root);
		
		//Print the height of tree
		System.out.println("\nHeight of tree is: " + tree.getHeight(tree.root));
		
		//Check is tree is balanced
		if(tree.isBalanced(tree.root)) 
			System.out.println("\nBalanced Tree!!");
		else
			System.out.println("\nNot a balanced tree!!");
		
		//Check if tree is BST
		if(tree.checkBST(tree.root))
			System.out.println("\nBinary Search Tree!!");
		else
			System.out.println("\nNot a Binary Search Tree!!");
		
		//Find the first common ancestor in a Binary Tree
		System.out.println("\nAncestor is: " + tree.commonAncestor(tree.root, new Node(23), new Node(19)));
		
		//Check the sum of nodes from root to leaves using BFS Algorithm
		System.out.println("\nDoes sum exist from Root to Leaf using BFS: " + tree.checkSumFromRootToLeafUsingBFS(tree.root, 82));
		
		//Check the sum of nodes from root to leaves using DFS Algorithm
		System.out.println("\nDoes sum exist from Root to Leaf using DFS: " + tree.checkSumFromRootToLeafUsingDFS(tree.root, 82));
		
		//Check the sum of nodes from root to leaves
		System.out.println("\nDoes sum exist from Root to Leaf (Recursive Method): " + tree.hasPathSumRecursive(tree.root, 82));
		
		//Print all the paths from root to leaves of a tree
		System.out.println("\nHere are paths from root to leaves for this tree:");
		tree.printPaths(tree.root);
		
		//Number of nodes in Tree
		System.out.println("Number of nodes in Tree: " + tree.findNumberOfNodes(tree.root));
		
		//Find the maximum value in Binary Tree
		System.out.println("Max Value: " + tree.findMax(tree.root, Integer.MIN_VALUE));
		
		//Find number of leaves in Binary Tree
		System.out.println("Number of leaves: " + tree.numberOfLeavesAtLastLevel(tree.root));
		
		//Construct Binary Tree from given special properties and Pre-Order Traversal of Binary Tree
		String preOrder = "ILILL";
		char[] input = preOrder.toCharArray();
		Node r = constructBinaryTreeFromPreOrder(input, 0);
		System.out.println("Print pre-Order of this tree: ");
		tree.preOrder(r);		

	}

}

