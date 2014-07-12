package com.prabhash.java.algorithms.datastructures;

import java.util.Arrays;

// this class creates an arraylist using an array
public class ArrayListImpl {
	
	private Object[] o;
	private int size = 0;
	
	public ArrayListImpl() {
		o = new Object[10];
	}
	
	public Object get(int index) {
		if(index < size) {
			return o[index];
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}
	
	public int size() {
		return size;
	}
	
	public void add(Object obj) {
		if(o.length - size <=5) {
			increaseListSize();
		}
		o[size++] = obj;
	}
	
	public Object remove(int index) {
		if(index < size) {
			Object obj = o[index];
			o[index] = null;
			int temp = index;
			while(temp < size) {
				o[temp] = o[temp + 1];
				o[temp + 1] = null;
				temp++;
			}
			size--;
			return obj;
		}
		else
			throw new ArrayIndexOutOfBoundsException();
	}
	
	private void increaseListSize() {
		o = Arrays.copyOf(o, o.length * 2);
		System.out.println("New length of arraylist is: " + o.length);
	}
	
	public static void main(String[] args) {
		ArrayListImpl al = new ArrayListImpl();
		al.add(new Integer(3));
		al.add(new Integer(10));
		al.add(new Integer(15));
		al.add(new Integer(4));
		for(int i=0; i<al.size;i++)
			System.out.println(al.get(i));
		System.out.println("List size: " + al.size());
		System.out.println("Removing element at position 2: " + al.remove(2));
		for(int i=0; i<al.size;i++)
			System.out.println(al.get(i));
		
	}
}
