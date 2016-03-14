package com.prabhash.java.algorithms.datastructures.queue;

/**
 * Implementation of special FIFO Queue which stores two sub-classes of Item class. This supports methods to deQueue A and B
 * objects respectively in the order they are inserted in Queue.
 * 
 * @author Prabhash Rathore
 *
 */
public class SpecialQueueWithOneQueueImpl implements SpecialQueue {
	
	private Node head;
	private Node tail;
	
	public static class Node {
		
		protected Item data;
		protected Node next;
		
		public Node(Item item) {
			this.data = item;
		}
	}
	
	
	/**
	 * Add any sub-class of item A.
	 * 
	 * Time Complexity = O(1)
	 * 
	 */
	@Override
	public void enQueue(Item item) {
		
		if(item == null) {
			throw new IllegalArgumentException("item is null");
		}
		
		Node node = new Node(item);
		
		if(head == null) {
			
			head = node;
			tail = head;
		} else {

			tail.next = node;
			tail = tail.next;
		}
	}
	
	/**
	 * Dequeue the first element added to this queue.
	 * 
	 * Time Complexity: O(1)
	 * 
	 */
	@Override
	public Item deQueue() {
		
		if(head == null) {
			return null;
		}
		
		Node node = head;
		head = head.next;
		node.next = null;
		
		Item item = node.data;
		
		return item;
	}
	
	/**
	 * Dequeue the first instance of A added to Queue. 
	 * 
	 * In order to do this, start from head and navigate through the queue. As soon as we find an instance of A, return that instance
	 * and arrange the queue pointers.
	 * 
	 * Time Complexity: O(n)
	 * 
	 */
	@Override
	public A deQueueA() {
		
		if(head == null) {
			return null;
		}
		
		A a = null;
		Node current = head;
		Node prev = head;
		
		while(current != null) {
			
			if(current.data instanceof A) {
				a = (A) current.data;
				
				// if first object in Queue is instance of A then move head to next object
				if(current == head) {
					head = head.next;
					current.next = null;
				} else if(current == tail) {
					tail = prev;
					tail.next = null;
				} else {
					prev.next = current.next;
					current.next = null;
				}
				
				return a;
			}
			
			prev = current;
			current = current.next;
		}
		
		return null;
	}
	
	/**
	 * Dequeue the first instance of B added to Queue. 
	 * 
	 * In order to do this, start from head and navigate through the queue. As soon as we find an instance of B, return that instance
	 * and arrange the queue pointers.
	 * 
	 * Time Complexity: O(n)
	 * 
	 */
	@Override
	public B deQueueB() {
		
		if(head == null) {
			return null;
		}
		
		B b = null;
		Node current = head;
		Node prev = head;
		
		while(current != null) {
			
			if(current.data instanceof B) {
				b = (B) current.data;
				
				if(current == head) {
					head = head.next;
					current.next = null;
				} else if(current == tail) {
					tail = prev;
					tail.next = null;
				} else {
					prev.next = current.next;
					current.next = null;
				}
				
				return b;
			}
			
			prev = current;
			current = current.next;
		}
		
		return null;
	}
	
	public Node getHead() {
		return this.head;
	}
	
	public Node getTail() {
		return this.tail;
	}
}
