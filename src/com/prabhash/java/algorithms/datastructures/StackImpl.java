package com.prabhash.java.algorithms.datastructures;

public class StackImpl {

	private Object[] stack;
	private int top = -1;
	private static final int MAX_SIZE = 20;
	
	public StackImpl() {
		stack = new Object[MAX_SIZE];
	}
	
	public void push(Object obj) {
		top++;
		stack[top] = obj;
		
	}
	
	public Object pop() {
		Object temp = stack[top];
		top--;
		return temp;
	}
	
	public void displayStack() {
		int i = top;
		while(i >= 0) {
			System.out.println(stack[i]);
			i--;
		}
	}
	
	
	public static void main(String[] args) {
		
		StackImpl stackImpl = new StackImpl();
				
		stackImpl.push("Science");
		stackImpl.push("Social Science");
		stackImpl.push("Computer Science");
		stackImpl.push("Math");
		stackImpl.push("English");
		stackImpl.push("Hindi");
		
		stackImpl.displayStack();
		
		System.out.println("Popped element is: " + stackImpl.pop());
		
		stackImpl.displayStack();		
		
	}

}
