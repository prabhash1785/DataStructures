/*
 * You have n - 1 numbers from 1 to n. Your task is to find the missing number.
 * I.e.
 * n = 5
 * v = [4, 2, 5, 1]
 * The result is 3.
 * @Twitter Interview
 * @author Prabhash Rathore
 */
package com.prabhash.java.algorithms.datastructures.array;

import java.util.Arrays;

public class FindMissingNumber {
	
	/**
	 * First sort the array and then do a sequential scan of array. If difference of any two consecutive numbers is more than
	 * 1, then the missing number should be (a[i] + 1)
	 * Time Complexity: O(n log n + n) ~ O(nlogn)
	 */
	public static int findMissingNumberUsingSequentialScan(int[] a) {
		if(a == null) {
			return -1;
		}
		Arrays.sort(a); //Sort the array first in order to do sequential search, time complexity = O(n logn)
		int size = a.length;
		for(int i = 0; i < size-1; i++) {
			if(a[i+1] - a[i] > 1) {
				return (a[i] + 1);
			}
			
		}
		return -1;
	}
	
	/**
	 * This is more of a mathematical solution and a better solution than Method 1 in terms of time complexity.
	 * Sum of first n numbers = n(n + 1)/2
	 * Take sum of given numbers in the array using a for loop..
	 * Missing number should be the difference of these sums.
	 * Warning: If n is an high order number then there is a chance of overflow while taking the sum of these numbers.
	 * Time Complexity = O(n)
	 * 
	 */
	public static int findMissingNumberUsingSum(int[] a) {
		if(a == null) {
			return -1;
		}
		int size = a.length;
		int totalSum = (size + 1) * (size + 2) / 2; // same as n (n + 1) / 2
		int actualSumOfArray = 0;
		for(int i = 0; i < size; i++) {
			actualSumOfArray += a[i]; 
		}
		return totalSum - actualSumOfArray;
	}
	
	/**
	 * Method 3 - This is the most efficient method using an XOR (lower level operation)
	 * 1.) XOR all no.s from 1 to n.let XOR be X
	 * 2) .XOR all array elements .let say XOR be Y
	 * 3) X XOR Y is our answer
	 * Time Complexity: O(n)
	 * No Space constraints, n can be any number in this case.
	 * 
	 */
	public static int findMissingNumberUsingXOR(int[] a) {
		if(a != null) {
			int x = 0, y = 0;
			int i, size = a.length;
			for(i = 0; i < size; i++) {
				x = x ^ a[i];
				System.out.println("x = " + x);
			}
			for(i = 1; i <= size + 1; i++) {
				y ^= i;
				System.out.println("y = " + y);
			}
			
			return x ^ y;	
			
		}
		
		return -1;
	}
	
	public static void main(String[] args) {

		int[] list = new int[] {4, 2, 5, 1};
		System.out.println("Missing number sequential scan is: " + findMissingNumberUsingSequentialScan(list));
		
		System.out.println("Missing Number using sum of natural nymbers method: " + findMissingNumberUsingSum(list));
		
		System.out.println("Missing number using XOR: " + findMissingNumberUsingXOR(list));

	}

}
