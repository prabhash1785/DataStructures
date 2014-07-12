/*
 * Create a Queue using Stack Data Structure.
 * Method 1:
 * This algorithm uses 2 Stack to create a Queue. One Stack is the main Stack where all enQueue operation are done as enQueue
 * is always done at the end so this can be perfectly done with one Stack. However while deQueueing, all the elements from
 * Stack1 are pushed to Buffer Stack and then top element from Buffer Stack is popped out.
 * 
 * Method 2:
 * This can be done using recursion as well but again in this case, we use the System Stack as a buffer because in
 * Recursion, there is always a Buffer stack on system side.
 * 
 * Ebay Interview Question
 * @author Prabhash Rathore
 * 
 */

package com.prabhash.java.algorithms.general;

import java.util.EmptyStackException;
import java.util.Stack;

public class CreateQueueFromStack {
	
	private Stack<Integer> stack1 = new Stack<Integer>();
	private Stack<Integer> buffer = new Stack<Integer>();
	
	private Stack<Integer> stack3 = new Stack<Integer>(); //This stack will be used to create Queue using Recursion
	
	/**
	 * Time: O(1) 
	 * Space: O(1)
	 */
	public void enQueue(int element) {
		stack1.push(element);
	}
	
	/*
	 * Time: O(n) -> O(n) only when Buffer stack is empty else it will be O(1)
	 * Space: O(n)
	 */
	public int deQueue() throws Exception {
		int element;
		if(!buffer.empty()) {
			element = buffer.pop();
		} else if(!stack1.empty()) {
			while(!stack1.empty()) {
				buffer.push(stack1.pop());
			}
			element = buffer.pop();
		} else {
			throw new Exception("Queue is empty!!");
		}
		
		return element;
	}
	
	/*
	 * Enqueue is really straightforward as enqueue is done in the end of the queue so we can always just push the element at
	 * the top of the stack.
	 * Time Complexity: O(1)
	 * Space Complexity: O(1)
	 */
	public void enQueueUsingRecursion(int element) {
		stack3.push(element);
	}
	
	/*
	 * Dequeue using Recursion on Stack.
	 * Time Complexity: O(n)
	 * Space Complexity: O(n)
	 */
	public int deQueueUsingRecursion() throws EmptyStackException {
		if(stack3.empty()) { 
			throw new EmptyStackException(); // this exception is throws only when Stack is empty
		}
		int top = stack3.pop();
		if(stack3.empty()) {
			return top;
		} else {
			int result = deQueueUsingRecursion();
			stack3.push(top);
			return result;
		}		
		
	}
	
	public static void main(String[] args) throws Exception {
		
		//First Queue uses 2 Stack to implement a Queue
		CreateQueueFromStack queue = new CreateQueueFromStack();
		queue.enQueue(4);
		queue.enQueue(24);
		queue.enQueue(41);
		queue.enQueue(40);
		queue.enQueue(12);
		queue.enQueue(10);
		queue.enQueue(56);
		queue.enQueue(43);
		
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
		
		//Second Queue which uses Recursion for Dequeueing elements
		CreateQueueFromStack queue2 = new CreateQueueFromStack();
		queue2.enQueueUsingRecursion(3);
		queue2.enQueueUsingRecursion(5);
		queue2.enQueueUsingRecursion(15);
		queue2.enQueueUsingRecursion(52);
		System.out.println(queue2.deQueueUsingRecursion());
		System.out.println(queue2.deQueueUsingRecursion());

	}

}
