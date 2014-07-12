package com.prabhash.java.algorithms.general;

import java.math.BigInteger;

public class FibonacciSeries {

	private int size; // how many fibonacci numbers you want to prints
	
	// initializing the size through constructor
	public FibonacciSeries(int size) { 
		this.size = size;
	}
	
	public void printFibonacciNumbers() {
		
		BigInteger[] fibArray = new BigInteger[size]; // Using BigInteger as numbers starts to overflow when the size of fibonacci series grows
		fibArray[0] = BigInteger.ZERO; //first fibonacci number
		fibArray[1] = BigInteger.ONE; ///second fibonacci number
		System.out.println("Here are first " + size + " Fibonacci Numbers: ");
		System.out.print(fibArray[0] + "\n" + fibArray[1] + "\n");
		
		for(int i = 2; i < size; i++) { // i = 2 because first two numbers are already printed.
			fibArray[i] = fibArray[i - 1].add(fibArray[i - 2]);
			System.out.println(fibArray[i] + " ");			
		}
		
	}
	
	public static void main(String[] args) {
		
		FibonacciSeries fib = new FibonacciSeries(100); //passing the number of fibonacci numbers to constructor		
		fib.printFibonacciNumbers();
		
	}

}
