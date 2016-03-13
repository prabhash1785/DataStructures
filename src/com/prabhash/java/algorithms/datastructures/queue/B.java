package com.prabhash.java.algorithms.datastructures.queue;

public class B extends Item {
	
	public B(int id, String name) {
		super(id, name);
	}
	
	@Override
	public void printDescription() {
		System.out.println("Child class B: " + this.getId() + " ::: " + this.getName());
	}
}
