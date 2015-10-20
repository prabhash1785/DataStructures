package com.prabhash.java.algorithms.datastructures.array;

/**
 * https://en.wikipedia.org/wiki/Maximum_subarray_problem
 * 
 * This is used to find the maximum sum of sub array given an array with a set of positive and negative numbers. This algorithm runs in
 * linear time. 
 * 
 * Kadane's algorithm consists of a scan through the array values, computing at each position the maximum (positive sum) subarray 
 * ending at that position. This subarray is either empty (in which case its sum is zero) or consists of one more element than the 
 * maximum subarray ending at the previous position.
 * 
 * @author prrathore
 *
 */
public class MaxSubArraySumUsingKadaneAlgorithm {
	
	/**
	 * Using Kardane's algorithm, find the contiguous sub-array whose sum is maximum in the array.
	 * 
	 * Time Complexity: O(n)
	 * 
	 * @param a
	 * @print start index, end index, sub array, max sum
	 */
	public static void findMaxSumSubArray(final int[] a) {
		
		if(a == null) {
			System.out.println("\n\nArray is null;");
			return;
		}
		
		int maxEndingHere = 0;
		int maxSoFar = 0;
		
		int startIndex = 0;
		int endIndex = 0;
		
		for(int i = 0; i < a.length; i++) {
			
			maxEndingHere = maxEndingHere + a[i];
			
			if(maxEndingHere < 0) { // if maxEndingHere is less than 0 then set this to 0
				maxEndingHere = 0;
				startIndex = i + 1; //if maxEndingHere was less than 0, then lets move startIndex to next position
			}
			
			if(maxSoFar < maxEndingHere) {
				maxSoFar = maxEndingHere;
				endIndex = i;
			}
			
		}
		
		System.out.println("\n\nStart Index: " + startIndex + " :: end Index: " + endIndex);
		
		System.out.println("Sub array value is:");
		for(int k = startIndex; k <= endIndex; k++) {
			System.out.print(a[k] + " ");
		}
		
		System.out.println("\nMax sum is: " + maxSoFar);
		
	}

	public static void main(String[] args) {
		
		// first input
		final int[] list = new int[] {-2, -3, 4, -1, -2, 1, 5, -3};
		
		System.out.println("Input array is:");
		for(int i : list) {
			System.out.print(i + " ");
		}
		
		findMaxSumSubArray(list);
		
		// second test input
		final int[] list2 = new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4};
		
		System.out.println("\n\nInput array is:");
		for(int i : list2) {
			System.out.print(i + " ");
		}
		
		findMaxSumSubArray(list2);

	}

}
