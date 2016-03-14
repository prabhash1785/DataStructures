package com.prabhash.java.algorithms.datastructures.queue;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SpecialQueueWithOneQueueImplTest {
	
	private SpecialQueueWithOneQueueImpl specialQueue;
	
	@Before
	public void setUp() {
		
		specialQueue = new SpecialQueueWithOneQueueImpl();
		specialQueue.enQueue(new A(5, "A5"));
		specialQueue.enQueue(new B(4, "B4"));
		specialQueue.enQueue(new B(10, "B10"));
		specialQueue.enQueue(new A(20, "A20"));
		specialQueue.enQueue(new B(23, "B23"));
	}
	
	@Test
	public void testEnqueue() {
		
		specialQueue.enQueue(new A(30, "A30"));
		
		SpecialQueueWithOneQueueImpl.Node node = specialQueue.getHead();
		assertNotNull(node);
		assertEquals("A5", node.data.getName());
		
		SpecialQueueWithOneQueueImpl.Node tail = specialQueue.getTail();
		assertNotNull(tail);
		assertEquals(30, tail.data.getId());
		assertEquals("A30", tail.data.getName());
	}
	
	@Test
	public void testDequeue() {
		
		Item firstItem = specialQueue.deQueue();
		assertNotNull(firstItem);
		assertEquals(5, firstItem.getId());
		assertEquals("A5", firstItem.getName());
		
		Item secondItem = specialQueue.deQueue();
		assertEquals(4, secondItem.getId());
		assertEquals("B4", secondItem.getName());
	}
	
	@Test
	public void testDequeueA() {
		
		Item firstItem = specialQueue.deQueueA();
		assertNotNull(firstItem);
		assertEquals(5, firstItem.getId());
		assertEquals("A5", firstItem.getName());
		
		Item secondItem = specialQueue.deQueueA();
		assertEquals(20, secondItem.getId());
		assertEquals("A20", secondItem.getName());
		
		Item thirdItem = specialQueue.deQueueA();
		assertNull(thirdItem);
	}
	
	@Test
	public void testDequeueB() {
		
		Item firstItem = specialQueue.deQueueB();
		assertNotNull(firstItem);
		assertEquals(4, firstItem.getId());
		assertEquals("B4", firstItem.getName());
		
		Item secondItem = specialQueue.deQueueB();
		assertEquals(10, secondItem.getId());
		assertEquals("B10", secondItem.getName());
		
		Item thirdItem = specialQueue.deQueueB();
		assertEquals(23, thirdItem.getId());
		assertEquals("B23", thirdItem.getName());
		
		Item fourthItem = specialQueue.deQueueB();
		assertNull(fourthItem);
	}
}
