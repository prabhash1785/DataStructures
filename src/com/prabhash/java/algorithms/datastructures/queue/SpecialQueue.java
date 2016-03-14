package com.prabhash.java.algorithms.datastructures.queue;

/**
 * A Special Queue which stores two sub-classes of Item class. It has special methods to enQueue A object and B object respectively
 * in the order they were added in this FIFO Queue.
 * 
 * @author Prabhash Rathore
 *
 */
public interface SpecialQueue {
	
	public void enQueue(Item item);
	
	public Item deQueue();
	
	public A deQueueA();
	
	public B deQueueB();

}
