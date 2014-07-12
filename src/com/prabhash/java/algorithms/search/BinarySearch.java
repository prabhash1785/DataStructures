package com.prabhash.java.algorithms.search;

import java.util.Arrays;

// Time complexity of Binary Search is O(log(n))
// Space Complexity = O(1)
public class BinarySearch {

	private static final int[] a = {12, 4, 1, 67, 30, 22, 2, 89, 23, 18};
		
	private BinarySearch() { 
		//Instantiation of this class is not allowed
	}
	
	/**
	 * Iterative Binary Search
	 * Pre-requisite: Array should be sorted.
	 * 
	 */
	public static int binarySearchIterative(int[] a, int num) {
		if(a == null) {
			return -1;
		}
		
		//Sort the array for binary search
		Arrays.sort(a);
		
		int low = 0;
		int high = a.length - 1;
		
		while(low < high) {
			int mid = (low + high) / 2;
			if(num < a[mid]) {
				high = mid - 1;
			} else if(num > a[mid]) {
				low = mid + 1;
			} else {
				return mid;
			}
		}
		
		return -1;
	}
	
	public static int binarySearchRecursive(int a[], int n, int low, int high) {
		
		if(low > high)
			return -1;
						
		int mid = (low + high) / 2;
		if(a[mid] == n) {
			return mid;
		} else if(a[mid] < n) {
			return binarySearchRecursive(a, n, mid + 1, high);
		} else {
			return binarySearchRecursive(a, n, low, mid - 1);
		}		
				
	}
	
	public static void main(String[] args) {
		
		int num = 31; // number to be searched in the array
		System.out.println("Here is the sorted array: ");
		for(int i = 0;i < a.length; i++)
			System.out.print(a[i] + " ");
		
		//Non-Recursive Algorithm
		System.out.println("\nPosition of element " + num + " is: " + binarySearchIterative(a, num));
		
		// Recursive Algorithm
		int num2 = 31; //to be searched
		System.out.println("\nPosition of element " + num2 + " is: " + binarySearchRecursive(a, num2, 0, a.length - 1));
		
	}

}
