/**
 * This is a sample implementaion of StringBuffer class.
 * Implemented the append method and also dynamically resizing the string based on new length.
 * @author Prabhash Rathore
 * 
 */

package com.prabhash.java.string;

public class StringBufferImpl {

	private char[] buffer;
	private int capacity;
	private int length;
	
	public StringBufferImpl() {
		this(16);
	}
	
	public StringBufferImpl(int n) {
		capacity = n;
		buffer = new char[capacity];
		length = 0;
	}
	
	public int length() {
		return length;
	}
	
	private void ensureCapacity(int newLength) {
		if(newLength <= 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		int k = Math.min(newLength, 2 * capacity);
		char[] dest = new char[k];
		System.arraycopy(buffer, 0, dest, 0, k);
		buffer = dest; //after expanding the array, point buffer to new array 
	}
	
	public StringBufferImpl append(String s) {
		if(s == null) {
			return this;
		}
		int newLength = s.length() + this.length;
		if(newLength >= this.capacity) {
			ensureCapacity(newLength);
		}
		for(int i = 0; i < s.length(); i++, length++) {
			buffer[length] = s.charAt(i);			
		}
		
		return this;
		
	}
	
	@Override
	public String toString() {
		return new String(buffer, 0, length);
	}
	
	public static void main(String[] args) {
		
		StringBufferImpl stringBuffer = new StringBufferImpl();
		stringBuffer.append("Amber");
		stringBuffer.append("Ricky");
		
		System.out.println("Output: " + stringBuffer.toString());
		
		System.out.println("Size of stringbuffer: " + stringBuffer.length);
		
		System.out.println("Capacity: " + stringBuffer.capacity);

	}

}
