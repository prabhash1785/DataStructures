package com.prabhash.java.algorithms.datastructures.array;

public class MaxSumSubArray {

	private int[] a = {12, 5, -3, 4, 6, -10, 7, -5, 9, 6, -8};
	
	/**
	 * Solving this problem using Divide and Conquer technique
	 * Time complexity is O(n)
	 * @author Prabhash Rathore
	 */
	public void findMaxSum() {
		int leftSum = -1;
		int sum = 0;
		
		int maxLeft = -1;
				
		int mid = a.length / 2;
		System.out.println("Original size of array: " + a.length);
		System.out.println("Here is the value of mid point: " + mid);
		
		for(int i = mid; i >= 0; i--) {
			sum += a[i];
			if(sum > leftSum)
				leftSum = sum;
				maxLeft = i;
		}
		
		int rightSum = -1;
		sum = 0;
		
		int maxRight = -1;
		
		for(int i = mid+1; i < a.length; i++) {
			sum += a[i];
			if(sum > rightSum) {
				rightSum = sum;
				maxRight = i;
			}
		}
		
		int totalSum = leftSum + rightSum;
		
		System.out.println("Max value of sum is: " + totalSum);
		System.out.println("Indixes for this sum: i = " + maxLeft + " and j = " + maxRight);
		
	}
	
	public static void main(String[] args) {
		
		new MaxSumSubArray().findMaxSum();

	}

}
