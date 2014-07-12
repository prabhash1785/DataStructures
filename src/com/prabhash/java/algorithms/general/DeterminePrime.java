package com.prabhash.java.algorithms.general;

/**
 * 
 * Program to determine if a number is prime or not.
 * @author Prabhash Rathore
 * @since Jan 7, 2013
 *
 */
public class DeterminePrime {

	public static void main(String[] args) {
		
		// int n = Integer.parseInt(args[0]);
		int n = 100; // Number to be determined it its prime or not
		boolean isPrime = true;
		
		System.out.println("Here is the number to be determined as prime: " + n);
		System.out.println("Square of number is: " + Math.pow(n, 2));
		System.out.println("Square Root: " + Math.sqrt(n));
		
		if(n % 2 == 0) {
			System.out.println(n + " is not a prime number.");
			isPrime = false;
		} else {
			int i = 3;
			//while(i * i < n) {
			while(i <= Math.sqrt(n)) {
				if(n % i == 0) {
					System.out.println(n + " is not a prime number.");
					isPrime = false;
					break;
				}
				i = i + 2;
			}
		}
		
		if(isPrime)
			System.out.println(n + " is a prime number.");

	}

}

