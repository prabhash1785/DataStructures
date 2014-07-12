package com.prabhash.java.algorithms.datastructures.tree;

/**
 * Create Height Balanced Binary search Tree from given Sorted Array or Sorted Linked List
 * 
 * @author Prabhash Rathore
 *
 */
public class CreateTreeFromSortedList {
	
	private static LinkedListNode head; 

	/**
	 * Create Height Balanced Binary Search Tree from Sorted Array.
	 * 
	 */
	public static Node createBSTFromSortedArray(int[] a) {
		if(a == null || a.length == 0) {
			return null;
		}
		
		return createBSTFromSortedArray(a, 0, a.length - 1);
	}
	
	/**
	 * Utility Method to create height balanced BST from Sorted Array
	 * 
	 * @param a
	 * @param start
	 * @param end
	 * @return Node
	 */
	private static Node createBSTFromSortedArray(int[] a, int start, int end) {
		if(start > end) {
			return null;
		}
		
		int mid = (start + end) / 2;
		
		Node root = new Node(a[mid]);
		
		root.setLeftChild(createBSTFromSortedArray(a, start, mid - 1));
		
		root.setRightChild(createBSTFromSortedArray(a, mid + 1, end));
		
		return root;
		
	}
	
	/**
	 * Create Height Balanced BST from Sorted Singly Linked List
	 * 
	 * Since we can't access elements from Linked List through Random Access like Array, we will construct
	 * BST from Bottom-Up.
	 * 
	 */
	public static Node createBSTFromSortedLinkedList(LinkedListNode head) {
		if(head == null) {
			return null;
		}
		
		CreateTreeFromSortedList.head = head;
		
		int sizeOfLL = getSize(head);
		
		return createBSTFromSortedLinkedList(0, sizeOfLL - 1);
		
	}
	
	/**
	 * Utility Method to create BST from LL
	 * 
	 */
	private static Node createBSTFromSortedLinkedList(int start, int end) {
		if(start > end) {
			return null;
		}
		
		int mid = (start + end) / 2;
		
		Node left = createBSTFromSortedLinkedList(start, mid - 1);
				
		Node root = new Node(head.key);
				
		head = head.next;
				
		Node right = (createBSTFromSortedLinkedList(mid + 1, end));
				
		root.setLeftChild(left);
		root.setRightChild(right);
		
		return root;
		
	}
	
	/**
	 * Get the size of LinkedList
	 * 
	 */
	private static int getSize(LinkedListNode head) {
		if(head == null) {
			return 0;
		}
		
		int length = 0;
		
		while(head != null) {
			length++;
			head = head.next;
		}
		
		System.out.println("\n\nLength of Linked List: " + length);
		return length;
	}
	
	/**
	 * Print InOrder Traversal of Tree
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
	 * Singly Linked List Class
	 */
	private static class LinkedListNode {
		private int key;
		private LinkedListNode next;
		
		public LinkedListNode(int key) {
			this.key = key;
		}
	}
	
	/**
	 * Unit Testing
	 * 
	 */
	public static void main(String[] args) {
		
		int[] array = new int[] {2, 4, 8, 12, 15, 20, 22, 32};
		
		//Construct BST from Sorted Array
		Node root = createBSTFromSortedArray(array);
		
		System.out.println("InOrder Traversal of BST constructed from Sorted Array:");
		inOrder(root);
		
		/* Construct Height Balanced BST from Sorted Single Linked List */
		LinkedListNode head = new LinkedListNode(array[0]);
		
		LinkedListNode temp = head;
		for(int i = 1; i < array.length; i++) {
			while(temp.next != null) {
				temp = temp.next;
			}
			temp.next = new LinkedListNode(array[i]);
		}
		
		//Print LinkedList Elements
		System.out.println("\n\nHere is Sorted Linked List: ");
		temp = head;
		while(temp != null) {
			System.out.print(temp.key + " ");
			temp = temp.next;
		}
		
		//Construct BST from Sorted Linked List
		Node root2 = createBSTFromSortedLinkedList(head);
		
		System.out.println("\n\nInOrder Traversal of BST constructed from Sorted Linked List:");
		inOrder(root2);
		

	}

}
