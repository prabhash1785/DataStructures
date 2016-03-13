package com.prabhash.java.algorithms.datastructures.queue;

public class A extends Item {
	
	public A(int id, String name) {
		super(id, name);
	}
	
	@Override
	public void printDescription() {
		System.out.println("Child class A: " + this.getId() + " ::: " + this.getName());
	}
}
