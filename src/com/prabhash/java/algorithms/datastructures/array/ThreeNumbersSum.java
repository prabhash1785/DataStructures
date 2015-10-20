package com.prabhash.java.algorithms.datastructures.array;

import java.util.Arrays;

/**
 * Objective: Write a function to find all the combinations of three numbers that sum to zero
 * Sample input:
 * [2, 3, 1, -2, -1, 0, 2, -3, 0]
 * Sample output:
 * 2, -2, 0
 * 1, -1, 0
 * 3, -2, -1
 * 3, 0, -3
 * 3, 0, -3
 * Twitter Interview Question - CareerCup
 * @author Prabhash Rathore
 *
 */
public class ThreeNumbersSum {
	
	/**
	 * Brute Force Algorithm
	 * Time Complexity: O(n ^ 3)
	 */
	public static void printNumbersWuthSum(int[] a) {
		int size = a.length;
		for(int i = 0; i < size - 2; i++) {
			for(int j = i + 1; j < size - 1; j++) {
				for(int k = j + 1; k < size; k++) {
					if(a[i] + a[j] + a[k] == 0) {
						System.out.println(a[i] + " " + a[j] + " " + a[k]);
					}
				}
			}
		}
	}
	
	/**
	 * This method uses Binary search to improve the time complexity of algorithm.
	 * Time Complexity: O((n ^ 2) (log n) )
	 * 
	 */
	public static void sumUsingBinarySearch(int[] a) {
		int size = a.length;
		//Sort the array before using Binary Search
		Arrays.sort(a);
		for(int i = 0; i < size - 2; i++) {
			for(int j = i + 1; j < size - 1; j++) {
				if(Arrays.binarySearch(a, j + 1, size, -(a[i] + a[j])) >= 0) {
					System.out.println(a[i] + " " + a[j] + " " + (-(a[i] + a[j])));
				}
			}
		}
	}

	public static void main(String[] args) {
		
		final int[] array = {2, 3, 1, -2, -1, 0, 2, -3, 0};
		printNumbersWuthSum(array);
		
		System.out.println("\n3 number sum using Binary Search:");
		sumUsingBinarySearch(array);

	}

}
