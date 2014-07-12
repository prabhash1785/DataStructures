/*
 * Circular Queue implementation using Linked List
 * Advantage of Circular Queue when it's implemented using Linked List instead of an Array:
 * 	- Don't need to worry about the size of queue as new nodes can be added and easily nodes can be removed.
 * @author Prabhash Rathore
 * 
 */
package com.prabhash.java.algorithms.datastructures.queue;

public class CircularQueueUsingLL<N> {
	
	private Node<N> front;
	private Node<N> rear;
	
	public CircularQueueUsingLL() {
		front = null;
		rear = null;
	}
	
	public synchronized void enQueue(N element) {
		Node<N> node = new Node<N>(element);
		if(rear == null) {
			rear = node;
			front = rear;
		} else {
			rear.next = node;
			rear = node;
			rear.next = front;
		}
	}
	
	public synchronized N deQueue() throws Exception {
		if(front == null) {
			System.out.println("Empty Queue!!");
			throw new Exception("Queue is empty!!");
		} else {
			N poppedElement = front.element;
			front = front.next;
			rear.next = front;
			return poppedElement;
		}
	}
	
	public N peek() throws Exception {
		if(front == null) {
			System.out.println("Queue is empty!!");
			throw new Exception("Queue is empty!!");
		} else {
			N element = front.element;
			return element;
		}
	}
	
	public void displayQueue(Node<N> front) {
		if(front == null) {
			System.err.println("Queue is Empty!!");
		} else {
			System.out.println("Here are Queue Elements:");
			while(front != rear) {
				System.out.print(front.element + " ");
				front = front.next;
			}
			System.out.println(rear.element);
		}
	}

	public static void main(String[] args) throws Exception {
		
		CircularQueueUsingLL<String> circularQueue = new CircularQueueUsingLL<String>();
		
		circularQueue.enQueue("Messer");
		circularQueue.enQueue("Max");
		circularQueue.enQueue("Ricky");
		circularQueue.enQueue("Amber");
		circularQueue.enQueue("Chicago");
		circularQueue.enQueue("Omaha");
		
		System.out.println("Dequeued Element: " + circularQueue.deQueue());
		
		System.out.println("Front element is : " + circularQueue.peek());
		
		circularQueue.displayQueue(circularQueue.front);

	}
	
	private class Node<E> {
		private E element;
		private Node<E> next;
		
		public Node(E element) {
			this.element = element;
		}
	}

}
