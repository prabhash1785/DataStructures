package com.prabhash.java.algorithms.datastructures.queue;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Hybrid Queue implementation using 3 queues which supports storage of 2 different objects.
 * 
 * @author Prabhash Rathore
 *
 */
public class SpecialQueueWithThreeQueuesImpl implements SpecialQueue {
	
	private DataNode headA, tailA;
	private DataNode headB, tailB;
	private RefNode refHead, refTail;
	
	public void enQueue(Item item) {
		if(item == null) {
			throw new NullPointerException("Null not allowed");
		}
		
		DataNode data = new DataNode(item);
		RefNode ref = new RefNode(data);
		data.ref = ref;
		
		// add Node to doubly Linked List
		addNodeToRefQueue(ref);
	}
	
	public Item deQueue() {
		if(refHead == null) {
			System.out.println("Queue is empty");
			return null;
		}
		
		RefNode node = refHead;
		DataNode data = refHead.dataNode;
		
		deQueueRef(node);
		deQueueData(data);
		
		return data.item;
	}
	
	public A deQueueA() {
		if(headA == null) {
			return null;
		}
		
		DataNode dataNode = headA;
		RefNode refNode = dataNode.ref;
		
		deQueueData(dataNode);
		deQueueRef(refNode);
		
		return (A) dataNode.item;
	}
	
	public B deQueueB() {
		if(headB == null) {
			return null;
		}
		
		DataNode dataNode = headB;
		RefNode refNode = dataNode.ref;
		
		deQueueData(dataNode);
		deQueueRef(refNode);
		
		return (B) dataNode.item;
	}
	
	private void deQueueRef(RefNode refNode) {
		if(refHead == refTail) {
			refHead = null;
			refTail = null;
			refNode = null;
		} else if(refNode == refHead) {
			refHead = refNode.next;
			refHead.prev = null;
			refNode.next = null;
		} else if(refNode == refTail) {
			refTail = refTail.prev;
			refTail.next = null;
			refNode.prev = null;
		} else {
			refNode.prev.next = refNode.next;
			refNode.next.prev = refNode.prev;
			refNode.next = null;
			refNode.prev = null;
		}
	}
	
	private void deQueueData(DataNode dataNode) {
		DataNode dataHead, dataTail;
		if(dataNode.item instanceof A) {
			dataHead = headA;
			dataTail = tailA;
		} else {
			dataHead = headB;
			dataTail = tailB;
		}
		
		// just one node in queue
		if(dataHead == dataTail) {
			if(dataNode.item instanceof A) {
				headA = null;
				tailA = null;
			} else {
				headB = null;
				tailB = null;
			}
		} else if(dataNode == dataHead) {
			if(dataNode.item instanceof A) {
				headA = headA.next;
				headA.prev = null;
				dataNode.next = null;
				dataNode = null;
				dataHead = null;
			} else {
				headB = headB.next;
				headB.prev = null;
				dataNode.next = null;
				dataNode = null;
				dataHead = null;
			}
		} else if(dataNode == dataTail) {
			if(dataNode.item instanceof A) {
				tailA = tailA.prev;
				tailA.next = null;
				dataNode.prev = null;
				dataNode = null;
				dataTail = null;
			} else {
				tailB = tailB.prev;
				tailB.next = null;
				dataNode.prev = null;
				dataNode = null;
				dataTail = null;
			}
		} else {
			dataNode.prev.next = dataNode.next;
			dataNode.next.prev = dataNode.prev;
			dataNode.next = null;
			dataNode.prev = null;
		}
	}
	
	private void addNodeToRefQueue(RefNode refNode) {
		if(isEmpty(refTail)) {
			refTail = refNode;
			refHead = refNode;
		} else {
			refTail.next = refNode;
			refNode.prev = refTail;
			refTail = refNode;
		}
		
		DataNode dataNode = refNode.dataNode;
		if(dataNode.item instanceof A) {
			if(tailA == null) {
				tailA = dataNode;
				headA = dataNode;
			} else {
				tailA.next = dataNode;
				dataNode.prev = tailA;
				tailA = dataNode;
			}
		} else {
			if(tailB == null) {
				tailB = dataNode;
				headB = dataNode;
			} else {
				tailB.next = dataNode;
				dataNode.prev = tailB;
				tailB = dataNode;
			}
		}
	}
	
	private boolean isEmpty(Object obj) {
		if(obj == null) {
			return true;
		}
		
		return false;
	}
	
	public static class DataNode {
		private Item item;
		private DataNode next;
		private DataNode prev;
		private RefNode ref;
		
		public DataNode(Item item) {
			this.item = item;
		}
		
		public String toString() {
			return this.item.toString();
		}
	}
	
	public static class RefNode {
		private DataNode dataNode;
		private RefNode next;
		private RefNode prev;
		
		public RefNode(DataNode data) {
			this.dataNode = data;
		}
	}
	
	@Test
	public void testQueue() {
		SpecialQueue queue = new SpecialQueueWithThreeQueuesImpl();
		queue.enQueue(new B(1, "B1"));
		queue.enQueue(new B(2, "B2"));
		queue.enQueue(new A(1, "A1"));
		queue.enQueue(new A(2, "A2"));
		queue.enQueue(new B(3, "B3"));
		queue.enQueue(new B(4, "B4"));
		queue.enQueue(new A(3, "A3"));
		
		Item item = queue.deQueue();
		assertEquals("B1", item.getName());
		
		item = queue.deQueue();
		assertEquals("B2", item.getName());
		
		item = queue.deQueue();
		assertEquals("A1", item.getName());
		
		B b = queue.deQueueB();
		assertEquals("B3", b.getName());
		
		A a = queue.deQueueA();
		assertEquals("A2", a.getName());
	}
	
	public static void main(String[] args) {
		SpecialQueue queue = new SpecialQueueWithThreeQueuesImpl();
		queue.enQueue(new B(1, "B1"));
		queue.enQueue(new B(2, "B2"));
		queue.enQueue(new A(1, "A1"));
		queue.enQueue(new A(2, "A2"));
		queue.enQueue(new B(3, "B3"));
		queue.enQueue(new B(4, "B4"));
		queue.enQueue(new A(3, "A3"));
		
		Item item = queue.deQueue();
		assertEquals("B1", item.getName());
		System.out.println("Item = " + item.getName());
		
		item = queue.deQueue();
		assertEquals("B2", item.getName());
		System.out.println("Item = " + item.getName());
		
		item = queue.deQueue();
		assertEquals("A1", item.getName());
		System.out.println("Item = " + item.getName());
		
		B b = queue.deQueueB();
		assertEquals("B3", b.getName());
		System.out.println("B = " + b.getName());
		
		A a = queue.deQueueA();
		assertEquals("A2", a.getName());
		System.out.println("A = " + a.getName());
	}
}
