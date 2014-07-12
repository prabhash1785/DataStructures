package com.prabhash.java.algorithms.datastructures.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * This is a Binary Search Tree.
 * @author Prabhash Rathore
 *
 */
public class BinarySearchTree {
	
	private Node root;
	
	/**
	 * @return the root
	 */
	public Node getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(Node root) {
		this.root = root;
	}

	public void add(int key, String data) {
		Node newNode = new Node(key, data);
		if(root == null) {
			root = newNode;
		} else {
			Node focusNode = root;
			Node parent;
			while(true) {
				parent = focusNode;
				if(key <= focusNode.getKey()) {
					focusNode = focusNode.getLeftChild();
					if(focusNode == null) {
						parent.setLeftChild(newNode);
						return;
					}
				} else {
					focusNode = focusNode.getRightChild();
					if(focusNode == null) {
						parent.setRightChild(newNode);
						return;
					}
				}
			}
		}
	}
	
   public void inOrderTraversal(Node focusNode) {
		
		if(focusNode != null) {
			inOrderTraversal(focusNode.getLeftChild());
			
			System.out.print(focusNode.getKey() + " ");
			
			inOrderTraversal(focusNode.getRightChild());
			
		}
				
	}
	
	public void preOrderTraversal(Node focusNode) {
			
			if(focusNode != null) {
				System.out.println(focusNode);
				
				preOrderTraversal(focusNode.getLeftChild());
				
				preOrderTraversal(focusNode.getRightChild());		
				
			}
					
		}
	
	public void postOrderTraversal(Node focusNode) {
		
		if(focusNode != null) {
					
			postOrderTraversal(focusNode.getLeftChild());
			
			postOrderTraversal(focusNode.getRightChild());
			
			System.out.println(focusNode);
			
		}
				
	}
	
	/**
	 * Program to delete a node from a BST Recursively.
	 * 
	 * Design:
	 * If root is null -> return null;
	 * If data < root.data -> go to left sub-tre
	 * If data > root.data -> go to right sub-tree
	 * If data == root.data:
	 * 		- If root has both left and right child then replace root with the value of max node in left subtree and then recursiveley delete max node in left sub tree
	 * 		- If root has only one child then replace root with that one child and delete child node
	 * 
	 * Time Complexity: O(log n)
	 *  
	 */
	public Node deleteNode(Node root, int data) {
		if(root == null) {
			return null;
		}
		
		if(root.getKey() == data) { //found the node to be deleted
			if(root.getLeftChild() != null && root.getRightChild() != null) {
				Node maxLeft = findMax(root.getLeftChild());
				root.setKey(maxLeft.getKey());
				root.setLeftChild(deleteNode(root.getLeftChild(), maxLeft.getKey())); //now recursively delete the max left in left sub tree				
			} else if(root.getLeftChild() != null) {
				root = root.getLeftChild();								
			} else if (root.getRightChild() != null) {
				root = root.getRightChild();								
			}
		} else if(data < root.getKey()) { //recursively go left
			root.setLeftChild(deleteNode(root.getLeftChild(), data));			
		} else if(data > root.getKey()) { //recursively go right
			root.setRightChild(deleteNode(root.getRightChild(), data));			
		} 
			
		return root;
	}
	
	/**
	 * Find the height of a tree.
	 * Algorithm:
	 * 	- Find the left height of root
	 *  - Find right height of root
	 *  - return the max(left or right) + 1
	 */
	public int getHeight(Node root) {
		if(root == null) {
			return 0;
		}
		int leftHeight = getHeight(root.getLeftChild());
		int rightHeight = getHeight(root.getRightChild());
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	/**
	 * Find the first common ancestor of two given nodes for a Binary Search Tree.
	 * For Binary Tree, it's really easy to find the first common ancestor. We just need to look for node where the path diverge
	 * that should be first common ancestor.
	 * Ref: http://www.geeksforgeeks.org/lowest-common-ancestor-in-a-binary-search-tree/
	 * This is a Recursive solution
	 * Time Complexity: O(log n)
	 * Space Complexity: O(log n) because of a system stack used for recursive calls.
	 */
	public Node findAncestor(Node root, Node p, Node q) {
		if(root == null || p == null | q == null) {
			return null;
		}
		
		if(root.getKey() > p.getKey() && root.getKey() > q.getKey()) {
			return findAncestor(root.getLeftChild(), p, q);
		}
		
		if(root.getKey() < p.getKey() && root.getKey() < q.getKey()) {
			return findAncestor(root.getRightChild(), p, q);
		}
		
		return root;		
		
	}
	
	/**
	 * This is an iterative solution to find the first ancestor of two given nodes.
	 * Time Complexity: O(log n)
	 * Space Complexity: O(1)
	 */
	public Node findAncestorIteratively(Node root, Node p, Node q) {
		if(root == null || p == null || q == null) {
			return null;
		}
		while(root != null) {
			if(root.getKey() > p.getKey() && root.getKey() > q.getKey()) {
				root = root.getLeftChild();
			} else if(root.getKey() < p.getKey() && root.getKey() < q.getKey()) {
				root = root.getRightChild();
			} else {
				break;
			}
		}
		
		return root;
	}
	
	/**
	 * Utility method to find distance of a given node from root. This is different from finding the height of a tree.
	 *  
	 */
	public int findDistanceOfNodeFromRoot(Node root, Node p, int length) {
		if(root == null || p == null) {
			return length;
		} else if(root.getKey() == p.getKey()) {
			return length;
		} else if(root.getKey() < p.getKey()) {
			return findDistanceOfNodeFromRoot(root.getRightChild(), p, length + 1);
		} else {
			return findDistanceOfNodeFromRoot(root.getLeftChild(), p, length + 1);
		}		
	}
	
	/**
	 * Given two nodes of a binary search tree, find number of nodes on the path between the two nodes.
	 * Here are the steps:
	 * 	- Find the ancestor of two nodes p and q
	 *  - find distance between ancestor and p = length1
	 *  - find distance between ancestor and q = length2
	 *  - return (length1 + length2 - 1)
	 * 
	 */
	public int findDistanceBetweenTwoNodes(Node root, Node p, Node q) {
		if(root == null) {
			return -1;
		}
		//find common ancestor of node p and node q
		Node ancestor = findAncestorIteratively(root, p, q);
		System.out.println("Ancestor is: " + ancestor);
		
		//find distance of a given node from ancestor
		int length1 = findDistanceOfNodeFromRoot(ancestor, p, 0);
		System.out.println("Length 1 = " + length1);
		int length2 = findDistanceOfNodeFromRoot(ancestor, q, 0);
		System.out.println("Length 2 = " + length2);
		
		return (length1 + length2 - 1); //subtract 1 as root is counted twice
		
	}
	
	/**
	 * Print all the nodes between two random nodes in a tree.
	 * Here is the algorithm:
	 *  - find the ancestor of two given nodes
	 *  - Store the nodes from ancestor to p in a set
	 *  - Store the nodes from ancestor to q in another set
	 *  - Return the set of nodes removing the duplicate 
	 * 
	 */
	public List<Node> getNodesBetweenTwoNodes(Node root, Node p, Node q) {
		if(root == null || p == null || q == null) {
			return null;
		}
		
		Node ancestor = findAncestor(root, p, q);
		
		List<Node> list1 = getNodes(ancestor, p);
		List<Node> list2 = getNodes(ancestor, q);
		
		for(Node temp : list2) {
			list1.add(temp);
		}
		
		return list1;
		
	}
	
	/**
	 * Utility method to get nodes between two random nodes
	 * 
	 */
	private List<Node> getNodes(Node p, Node q) {
		if(p == null || q == null) {
			return null;
		} else {
			List<Node> list = new LinkedList<Node>();
			while(p.getKey() != q.getKey()) {
				if(p.getKey() > q.getKey()) {
					list.add(p);
					p = p.getLeftChild();
				} else {
					list.add(p);
					p = p.getRightChild();
				}
			}
			list.add(q);
			
			return list;
		}
	}
	
	/**
	 * Find the lowest node in BST
	 * 
	 * Design:
	 * Lowest Value Node in BST is the left most node with no more left child but there could be right child so 
	 * this is not necessarily a leaf.
	 *  
	 */
	public int getMinimumValue(Node root) {
		
		if(root == null) {
			return -1;
		}
		
		if(root.getLeftChild() == null) {
			return root.getKey();
		}
		
		return getMinimumValue(root.getLeftChild());
		
	}
	
	/**
	 * Find the maxmum node in Binary Tree.
	 * 
	 * Design:
	 * In BST, max node is the right most node until it doesn't have any right child nodes.
	 * 
	 */
	public Node findMax(Node root) {
		if(root == null) {
			return null;
		}
		
		if(root.getRightChild() == null) {
			return root;
		}
		
		return findMax(root.getRightChild());
	}
	
	
	/**
	 * Unit Test cases
	 */
	public static void main(String[] args) {
		
		BinarySearchTree tree = new BinarySearchTree();
		
		tree.add(25, "25");
		tree.add(15, "15");
		tree.add(35, "35");
		tree.add(50, "50");
		tree.add(17, "17");
		tree.add(21, "21");
		tree.add(5, "5");
		tree.add(3, "3");
		tree.add(14, "14");
		tree.add(1, "1");
		
		Node root = tree.getRoot();
		System.out.println("Root is : " + root.getData());
		
		//Get Height of Tree
		System.out.println("Height of tree is: " + tree.getHeight(root));
		
		//Get first common ancestor for two nodes in Binary Search Tree Recursively
		Node commonAncestor = tree.findAncestor(root, new Node(1, "1"), new Node(14, "14"));
		if(commonAncestor != null) {
			System.out.println("First common ancestor is: " + commonAncestor.getData());
		} else {
			System.out.println("No common ancestor!!");
		}
		
		//Find common ancestor iteratively
		System.out.println("Ancestor: " + tree.findAncestorIteratively(root, new Node(1, "1"), new Node(14, "14")));		
		System.out.println("Ancestor: " + tree.findAncestorIteratively(root, new Node(3, "3"), new Node(50, "50")));
		
		//Distance between two nodes in BST
		System.out.println("Distance between 2 nodes: " + tree.findDistanceBetweenTwoNodes(root, new Node(1, "1"), new Node(50, "50")));
		
		//Print Nodes between two nodes in a tree
		System.out.println("\nHere are the nodes between two random nodes in a tree:\n");
		List<Node> list = tree.getNodesBetweenTwoNodes(root, new Node(1, "1"), new Node(21, "21"));
		for(Node node: list)
			System.out.print(node.getKey() + " ");
		
		//Minimum Value in a BST
		System.out.println("\n\nMinimum Value in BST: " + tree.getMinimumValue(root));
		
		//Delete a node from BST
		System.out.println("Deleted Node: " + tree.deleteNode(root, 5));
		System.out.println("InOrder of modified tree: ");
		tree.inOrderTraversal(root); //test the inOrder of tree to make sure desired node is deleted from tree
	}

}
