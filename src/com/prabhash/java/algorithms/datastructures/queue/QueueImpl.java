package com.prabhash.java.algorithms.datastructures.queue;

/**
 * This is a Queue implementation in Java.
 * @author Prabhash Rathore
 *
 */

public class QueueImpl {

	private String[] queue;
	private int first = -1;
	private int last = -1;
	private static final int MAX_SIZE = 25;
	
	public QueueImpl() {
		queue = new String[MAX_SIZE];
	}
	
	public void enQueue(String s) {
		if((first == -1) || (last == -1)) {
			first++;
			last++;
			queue[first] = s;
		} else {
			last++;
			queue[last] = s;
		}
	}
	
	public String deQueue() {
		String element = queue[first];
		for(int i = 0;i <= last;i++) {
			queue[i] = queue[i+1];
		}
		last--;
		System.out.println("Popped element is: " + element);
		return element;
	}
	
	public void display() {
		System.out.println("Here are the elments in the queue: ");
		for(int i = 0;i <= last;i++)
			System.out.println(queue[i]);
	}
	
	public static void main(String[] args) {
		QueueImpl queueImpl = new QueueImpl();
		queueImpl.enQueue("Science");
		queueImpl.enQueue("Social Science");
		queueImpl.enQueue("Computer Science");
		queueImpl.enQueue("Math");
		queueImpl.enQueue("English");
		queueImpl.enQueue("Hindi");
		
		queueImpl.display();
		
		queueImpl.deQueue();
		
		queueImpl.display();

	}

}
