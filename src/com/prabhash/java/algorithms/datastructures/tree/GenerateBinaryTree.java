package com.prabhash.java.algorithms.datastructures.tree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Regenerate Binary Tree from given Arrays or Strings or some kind of tree traversal patterns.
 * 
 * Time Complexity: O(n logn) where n -> number of nodes
 * If Tree is Skewed on left or right, then Time Complexity = O(n ^ 2)
 * 
 * To improve the above time complexity, we can create a Hash Table for Constant Time look up and this will improve the Time Complexity to O(n) which
 * will be the best Time Complexity for regenerating a Tree.
 * 
 * Ref: http://leetcode.com/2011/04/construct-binary-tree-from-inorder-and-preorder-postorder-traversal.html
 * 
 */
public class GenerateBinaryTree {

	/**
	 * This method will create a Tree from given InOrder and PostOrder traversal of a Tree.
	 * 
	 * For eg, in-order:   4 2 5  (1)  6 7 3 8
	 *         post-order: 4 5 2  6 7 8 3  (1)
	 * From PostOrder, we know last element in the array is the Root of the Tree.
	 * From InOrder, now we can find left subtree < root and right subtree > root
	 * Recursively create the Tree.        
	 *         
	 * @param inOrder
	 * @param postOrder
	 * @return Node
	 * @throws Exception 
	 */
	public static Node createTreeFromInOrderPostOrder(int[] inOrder, int[] postOrder) throws Exception {
		if(inOrder == null || postOrder == null) {
			return null;
		}
		
		int inStart = 0;
		int inEnd = inOrder.length - 1;
		
		int postStart = 0;
		int postEnd = postOrder.length - 1;
		
		//Validate if inOrder and postOrder have equal number of elements
		if(inEnd != postEnd) { 
			throw new Exception("Can't form the Tree, inOrder and PostOrder have different number of elements!!");
		}
		
		return createTree(inOrder, inStart, inEnd, postOrder, postStart, postEnd);
	}
	
	/**
	 * Utility method to create a Tree from InOrder and PostOrder Traversal
	 * 
	 */
	public static Node createTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
		if (inStart > inEnd || postStart > postEnd)
			return null;

		int rootValue = postorder[postEnd];
		System.out.println("Root Value = " + rootValue);
		Node root = new Node(rootValue);

		int index = 0;
		for (int i = 0; i < inorder.length; i++) {
			if (inorder[i] == rootValue) {
				index = i;
				System.out.println("index = " + index);
				break;
			}
		}

		/*
		 * For Left Node, index of PostOrder array is:
		 * 		postStart = postStart
		 * 		postEnd = postStart + (index - inStart) - 1
		 */
		root.setLeftChild(createTree(inorder, inStart, index - 1, postorder, postStart, postStart + (index - inStart) - 1));
				
		/*
		 * For Right Node, index of PostOrder array is:
		 * 		postStart = postStart + (index - inStart) - 1 + 1
		 * 		postEnd = postEnd - 1
		 */
		root.setRightChild(createTree(inorder, index + 1, inEnd, postorder, postStart + (index - inStart) - 1 + 1, postEnd - 1));
		
		return root;
	}
	
	/**
	 * Re-Construct Binary Tree from InOrder and PreOrder traversal of Tree.
	 * 
	 * Assumption: No duplicates in the Tree
	 * 
	 * Based on Pre-Order traversal, we know first element is the root.
	 * From InOrder:
	 * 		- left subtree array < index of root in inorder
	 * 		- right subtree array > index of root in inorder
	 * Recurse the above steps to generate the Tree
	 * 
	 * Ref: http://rleetcode.blogspot.com/2014/02/construct-binary-tree-from-preorder-and.html 
	 * @throws Exception 
	 * 
	 */
	public static Node buildTreeFromInOrderAndPreOrder(int[] inOrder, int[] preOrder) throws Exception {
		
		if(inOrder == null || inOrder.length == 0 || preOrder == null || preOrder.length == 0) {
			return null;
		}
		
		int inStart = 0;
		int inEnd = inOrder.length - 1;
		
		int preStart = 0;
		int preEnd = preOrder.length - 1;
		
		if(inEnd != preEnd) {
			throw new Exception("Unequal number of elements in inOrder and preOrder sets, can't create a Tree!!");
		}
		
		return buildTreeFromInAndPreOrder(inOrder, inStart, inEnd, preOrder, preStart, preEnd);
		
	}
	
	/**
	 * Utility method to generate tree from inOrder and preOrder
	 * 
	 */
	private static Node buildTreeFromInAndPreOrder(int[] inOrder, int inStart, int inEnd, int[] preOrder, int preStart, int preEnd) {
		
		//Base Condition for Recursion
		if(inStart > inEnd || preStart > preEnd) {
			return null;
		}
		
		int rootValue = preOrder[preStart]; //first element of preOrder array
		System.out.println("Root Value = " + rootValue);
		Node root = new Node(rootValue);
		
		int index = 0;
		for(int i = inStart; i <= inEnd; i++) {
			if(inOrder[i] == rootValue) {
				index = i;
				System.out.println("Index = " + index);
				break;
			}
		}
		
		//Length of Left Tree
		int length = index - inStart;
		
		/*
		 * For Left Node, index of PretOrder array is:
		 * 		preStart = preStart + 1
		 * 		preEnd = preStart + 1 + (index - inStart) - 1 
		 */
		root.setLeftChild(buildTreeFromInAndPreOrder(inOrder, inStart, index - 1, preOrder, preStart + 1, preStart + 1 + length - 1));
		
		/*
		 * For Right Node, index of PreOrder array is:
		 * 		preStart = preStart + 1 + (index - inStart) - 1 + 1
		 * 		preEnd = preEnd
		 */
		root.setRightChild(buildTreeFromInAndPreOrder(inOrder, index + 1, inEnd, preOrder, preStart + 1 + length - 1 + 1, preEnd));
		
		return root;
		
	}
	
	/**
	 * 
	 * Construct Binary Tree from provided inOrder and Level Order Tree Traversal arrays
	 * Algorithm Design:
	 * First element of Level Order is the root.
	 * Find that element in InOrder, that gives us left and right sub trees.
	 * Find left and right sub-array from level order array by comparing it to inOrder left array and right sub-array respectively
	 * Recurse the above steps.
	 * 
	 * For eg,
	 * inOrder =    9 5 10 4 1 6 2
	 * levelOrder = 4 5 6 9 10 1 2
	 * 
	 * @param inOrder
	 * @param levelOrder
	 * @return Node
	 */

	public static Node generateTree(int[] inOrder, int[] levelOrder) {
		
		//initial validation
		if(inOrder == null || inOrder.length == 0 || levelOrder == null || levelOrder.length == 0) {
			return null;
		}
		
		return buildTree(inOrder, levelOrder);

	}

	/**
	 * 	Utility method to generate Binary Tree from inOrder and Level Order Traversal arrays.
	 * 
	 *  inOrder =    9 5 10 4 1 6 2
	 * 	levelOrder = 4 5 6 9 10 1 2
	 * 
	*/
	private static Node buildTree(int[] inOrder, int[] levelOrder) {
		
		// Base condition for Recursion
		if(inOrder == null || inOrder.length <= 0 || levelOrder == null || levelOrder.length <= 0) {
			return null;
		}
		
		// Base condition for Recursion
		if(inOrder.length == 1 || levelOrder.length == 1) {
			return new Node(levelOrder[0]);
		}
		
		int rootVal = levelOrder[0];
		Node root = new Node(rootVal);
		
		//find root value in inOrder array and partition inOrder Tree into left and right subtree
		int index = 0;
		for(int i = 0; i < inOrder.length; i++) {
			if(inOrder[i] == rootVal) {
				index = i;
				System.out.println("index = " + index);
				break;
			}
		}
		
			
		//find leftSubTrees
		int[] leftInOrderTree = Arrays.copyOf(inOrder, index);
		printArrayForDebugging(leftInOrderTree);
		int[] leftLevelOrderTree = findLevelOrder(levelOrder, leftInOrderTree);
		printArrayForDebugging(leftLevelOrderTree);
		
		root.setLeftChild(buildTree(leftInOrderTree, leftLevelOrderTree));;
		
		//find rightSubTrees
		int[] rightInOrderTree = Arrays.copyOfRange(inOrder, index + 1, inOrder.length);
		printArrayForDebugging(rightInOrderTree);
		int[] rightLevelOrderTree = findLevelOrder(levelOrder, rightInOrderTree);
		printArrayForDebugging(rightLevelOrderTree);
		
		root.setRightChild(buildTree(rightInOrderTree, rightLevelOrderTree));;
		
		return root;

	}

	/**
	 * Utiltiy method to find left and right level order sub-arrays from LevelOrder array by comparing it to left and right inOrder arrays respectively
	 * Slower Technique: Compare both inorder and levelorder arrays to determine, levelorder arrays.
	 * Time Complexity = O(n ^ 2) because of 2 embedded for loops
	 * 
	 * Improvement: Time Complexity: O(n) by using a HashMap to store inOrder elements, so loopup in HashMap is O(1)
	 * 
	 * @param levelOrder
	 * @param inOrder
	 * @return int[]
	 */
	private static int[] findLevelOrder(int[] levelOrder, int[] inOrder) {
		
		int[] output = new int[inOrder.length];
		
		Set<Integer> inOrderSet = new HashSet<Integer>(); //Store inOrder elements for faster look up
		
		for(int i = 0; i < inOrder.length; i++) {
			inOrderSet.add(inOrder[i]);
		}		
		
		int k = 0;
		for(int i = 0; i < levelOrder.length; i++) {
			if(inOrderSet.contains(levelOrder[i])) {
					output[k] = levelOrder[i];
					k++;					
			}
			
			//Optimization - Break out of for loop as soon levelorder size is equal to inorder size as their size must be equal
			if(k >= inOrderSet.size()) {
				break;
			}
		}
		
		return output;

	}
	
	/**
	 * Print Array for Debugging
	 */
	private static void printArrayForDebugging(int[] a) {
		if(a != null) {
			System.out.println("\nArray is: ");
			for(int i = 0; i < a.length; i++)
				System.out.print(a[i] + " ");
		}
		
		System.out.println();
	}
	
	
	/**
	 * PreOrder Traversal of Tree
	 *  
	 */
	public static void preOrder(Node root) {
		if(root != null) {
			System.out.print(root.getKey() + " ");
			preOrder(root.getLeftChild());
			preOrder(root.getRightChild());
		}
	}
	
	/**
	 * InOrder Traversal
	 * 
	 */
	public static void inOrder(Node root) {
		if(root != null) {
			inOrder(root.getLeftChild());
			System.out.print(root.getKey() + " ");
			inOrder(root.getRightChild());
		}
	}
	
	/**
	 * PostOrder Traversal
	 * 
	 */
	public static void postOrder(Node root) {
		if(root != null) {
			postOrder(root.getLeftChild());
			postOrder(root.getRightChild());
			System.out.print(root.getKey() + " ");
		}
	}
	
	/**
	 * Level Order Traversal of Tree using BFS
	 * 
	 */
	public static void levelOrderTraversal(Node root) {
		if(root == null) {
			return;
		}
		
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		
		while(queue.peek() != null) {
			Node current = queue.poll();
			System.out.print(current.getKey() + " ");
			
			if(current.getLeftChild() != null) {
				queue.add(current.getLeftChild());
			}
			
			if(current.getRightChild() != null) {
				queue.add(current.getRightChild());
			}
			
		}
		
	}
	
	
	
	public static void main(String[] args) {
		
		int[] preOrderTraversal = new int[] {1, 2, 4, 5, 3, 7, 6, 8};
		int[] inOrderTraversal = new int[] {4, 2, 5, 1, 6, 7, 3, 8};
		int[] postOrderTraversal = new int[] {4, 5, 2, 6, 7, 8, 3, 1};
		int[] levelOrderTraversal = new int[] {1, 2, 3, 4, 5, 7, 8, 6};
				
		System.out.println("\nTree Generation from InOrder and PostOrder Arrays:");
		Node root1 = null;
		
		//Create Tree from inOrder and postOrder traversal
		try {
			root1 = createTreeFromInOrderPostOrder(inOrderTraversal, postOrderTraversal);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//Print Tree constructed from InOrder and PostOrder
		System.out.println("PreOrder Traversal: ");
		preOrder(root1);
		
		System.out.println("\nInOrder Traversal: ");
		inOrder(root1);
		
		System.out.println("\nPostOrder Traversal: ");
		postOrder(root1);
		
		System.out.println("\nTree Generation from InOrder and PreOrder Arrays:");
		
		//Create Tree from InOrder and PreOrder Traversal of Tree
		Node root2 = null;
		
		try {
			root2 = buildTreeFromInOrderAndPreOrder(inOrderTraversal, preOrderTraversal);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//Print Tree constructed from InOrder and PreOrder
		System.out.println("PreOrder Traversal: ");
		preOrder(root2);
		
		System.out.println("\nInOrder Traversal: ");
		inOrder(root2);
		
		System.out.println("\nPostOrder Traversal: ");
		postOrder(root2);
		
		System.out.println("\nTree Generation from InOrder and LevelOrder Arrays:");
		
		//Create Tree from InOrder and LevelOrder Traversal of Tree
		Node root3 = null;
		
		root3 = generateTree(inOrderTraversal, levelOrderTraversal);
				
		//Print Tree constructed from InOrder and PreOrder
		System.out.println("PreOrder Traversal: ");
		preOrder(root3);
		
		System.out.println("\nInOrder Traversal: ");
		inOrder(root3);
		
		System.out.println("\nPostOrder Traversal: ");
		postOrder(root3);
		
		System.out.println("\nLevel Order Traversal: ");
		levelOrderTraversal(root3);

	}

}
