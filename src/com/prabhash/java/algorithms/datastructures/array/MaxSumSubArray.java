package com.prabhash.java.algorithms.datastructures.array;

/**
 * Reference: CLRS Maximum Sum-SubArray problem.
 * 
 * Application: Find best buy and sell stock price to make max profit.
 * 
 * @author prrathore
 *
 */
public class MaxSumSubArray {
	
	/**
	 * Solving this problem using Divide and Conquer technique.
	 * 
	 * Max sum could lie in one of the possible 3 ways:
	 * - Completely on left side of array
	 * - Crossing the mid point
	 * - Completely on right
	 * 
	 * So divide the problem in above mentioned 3 ways and then solve sub-problems.
	 * 
	 * Time complexity is O(n logn)
	 * 
	 * @author Prabhash Rathore
	 */
	public static Output findMaxSumSubArray(int[] a, int low, int high) {
		
		if(high == low) {
			return new Output(low, high, a[low]);
		} else {
			
			int mid = (low + high) / 2;
			
			Output outputLeft = findMaxSumSubArray(a, low, mid);
			Output outputRight = findMaxSumSubArray(a, mid + 1, high);
			Output outputCrossingMidPoint = findMaxCrossingSubArray(a, low, mid, high);
			
			if(outputLeft.sum >= outputRight.sum && outputLeft.sum >= outputCrossingMidPoint.sum) {
				return outputLeft;
			} else if(outputRight.sum >= outputLeft.sum && outputRight.sum >= outputCrossingMidPoint.sum) {
				return outputRight;
			} else {
				return outputCrossingMidPoint;
			}
		}
	}
	
	/**
	 * Find max sum assuming max sum crosses mid point in array. This is just one of the possible scenarios. Other
	 * scenarios would be that max sum lies completely on left or completely on right.
	 * 
	 * Time Complexity: O(n)
	 * 
	 * @return
	 */
	private static Output findMaxCrossingSubArray(int[] a, int low, int mid, int high) {
		
		int leftMaxSum = -1;
		int sum = 0;
		int maxLeftIndex = -1;
		for(int i = mid; i >= low; i--) {
			sum += a[i];
			if(sum > leftMaxSum)
				leftMaxSum = sum;
				maxLeftIndex = i;
		}
		
		int rightMaxSum = -1;
		sum = 0;
		int maxRightIndex = -1;
		for(int i = mid+1; i < high; i++) {
			sum += a[i];
			if(sum > rightMaxSum) {
				rightMaxSum = sum;
				maxRightIndex = i;
			}
		}
		
		int totalSum = leftMaxSum + rightMaxSum;
		
		return new Output(maxLeftIndex, maxRightIndex, totalSum);
	}
	
	private static class Output {
		
		private int left;
		private int right;
		private int sum;
		
		public Output(int left, int right, int sum) {
			this.left = left;
			this.right = right;
			this.sum = sum;
		}
	}
	
	public static void main(String[] args) {
		
		final int[] a = {12, 5, -3, 4, 6, -10, 7, -5, 9, 6, -8};
		
		System.out.println("Input Array is:");
		for(int i : a) {
			System.out.print(i + " ");
		}
		
		Output output = findMaxSumSubArray(a, 0, a.length - 1);
		System.out.println("\nLeft index is: " + output.left);
		System.out.println("Right index is: " + output.right);
		System.out.println("Max sum is: " + output.sum);
	}
}
