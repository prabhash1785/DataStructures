package com.prabhash.java.algorithms.search;

/**
 * Find the Number occurring Odd Number of times.
 * Given an array of positive integers, all numbers occur even number of times except one number which occurs odd number of times.
 * Find the numver in O(n) time and constant space.
 * Example: a = {1, 2, 3, 2, 3, 1, 3}
 * Output = 3
 * @author Prabhash Rathore
 *
 */
public class FindNumberWithOddCount {
	
	/**
	 * Do a bitwise XOR of all the elements. We get the number which has odd occurrences. This is because A XOR A = 0
	 * Limitation:
	 * 	- Finds a number with odd frequency if there is only one number with odd frequency
	 * Time Complexity = O(n)
	 * Space Complexity = O(1)
	 */
	public static int findNumberWithOddCount(int[] a) {
		if(a == null) {
			return -1;
		}
		
		int x = 0; //used to store the Bitwise XOR of all numbers in array
		for(int i = 0; i < a.length; i++) {
			x ^= a[i];
		}
		
		return x;
	}

	public static void main(String[] args) {
		int[] array = new int[] {1, 2, 3, 2, 3, 1, 3};
		
		System.out.println("Number with odd frequency is: " + findNumberWithOddCount(array));

	}

}
