/**
 *
 * This class will be used to rotate the head of a linked list.
 * 
 * For eg, Input: A->B->C->D->null
 * 
 * Output: D->A->B->C->null
 * 
 * Ebay Interview Question
 * 
 */

package com.prabhash.java.algorithms.general;

public class RotateList {
	
	private Node head;
	
	/*
	 * Adding a new Node using Iterative method.
	 */
	public void addNode(Node newNode) {
				
	    if(head == null) {
			head = newNode;
			return;
		} else {
			Node temp = head;
			while(temp.next != null) {
				temp = temp.next;
			}
			temp.next = newNode;
			
		}	      
		
	}
	
	/*
	 * Traversing a Linked List using Recursion.
	 */
	public void printList(Node head) {
		if(head == null) {
			return;
		} else {
			System.out.print(head.value + " => ");
			printList(head.next);
		}
	}
	
	/*
	 * Rotate Linked List such that last node becomes the head.
	 */
	public Node rotate(Node head) {
		
		if(head == null) {
			return head;
		} else {
			Node last = head;
			Node prev = head;
			while(last.next != null) {
				prev = last;
				last = last.next;
			}
			last.next = head; //link the last node to the head
			prev.next = null; //reset the last node link to NULL
			return last; //return last node as head
		}
				
	}
	
	
	/*
	 * Reverse a Linked List using iterative technique
	 * Input: A -> B -> C -> D -> NULL
	 * Output: D -> C -> B -> A -> NULL
	 * 
	 */
	public Node reverseListIteratively(Node head) {
		if(head == null) {
			return null;
		} else {
			Node prev = null;
			Node next = null;
			while(true) {
				next = head.next;
				head.next = prev;
				prev = head;
				if(next == null) {
					return head;
				}
				head = next;				
									
			}
		}		
		
	}
	
	/*
	 * Reverse a Linked List using Recursion
	 * Input: A -> B -> C -> D -> NULL
	 * Output: D -> C -> B -> A -> NULL
	 * 
	 */
	public Node reverseLinkedListRecursively(Node head, Node prev) {
		if(head == null) {
			return prev;
		} else {
			Node next = head.next;
			head.next = prev;
			prev = head;
			return reverseLinkedListRecursively(next, head);
		}
	}
	

	public static void main(String[] args) {
		
		RotateList rotateList = new RotateList();
		
		Node a = new Node("A");
		rotateList.addNode(a);
		Node b = new Node("B");
		rotateList.addNode(b);
		Node c = new Node("C");
		rotateList.addNode(c);
		Node d = new Node("D");
		rotateList.addNode(d);
		
		rotateList.head = a;
		
		rotateList.printList(rotateList.head);
						
		/*rotateList.head = rotateList.rotate(rotateList.head);
		System.out.println("\nFirst Rotation...");
		rotateList.printList(rotateList.head);
		
		rotateList.head = rotateList.rotate(rotateList.head);
		System.out.println("\nSecond Rotation...");
		rotateList.printList(rotateList.head);*/
		
		//Reverse List
		rotateList.head = rotateList.reverseLinkedListRecursively(rotateList.head, null);
		System.out.println("\nAfter Reversing...");
		rotateList.printList(rotateList.head);

	}
	
	public static class Node {
		private String value;
		private Node next;
		
		public Node(String value) {
			this.value = value;
		}
		
		@Override
		public String toString() {
			return this.value;
		}
		
	}

}
