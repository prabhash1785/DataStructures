package com.prabhash.java.algorithms.datastructures.array;

public class MaxSumSubArray {

	private int[] a = {12, 5, -3, 4, 6, -10, 7, -5, 9, 6, -8};
	
	/**
	 * Solving this problem using Divide and Conquer technique
	 * 
	 * Time complexity is O(n)
	 * @author Prabhash Rathore
	 */
	public void findMaxSum() {
		int leftMaxSum = -1;
		int sum = 0;
		
		int maxLeftIndex = -1;
				
		int mid = a.length / 2;
		System.out.println("\n\nOriginal size of array: " + a.length);
		System.out.println("Here is the value of mid point: " + a[mid]);
		
		for(int i = mid; i >= 0; i--) {
			sum += a[i];
			if(sum > leftMaxSum)
				leftMaxSum = sum;
				maxLeftIndex = i;
		}
		
		int rightMaxSum = -1;
		sum = 0;
		
		int maxRightIndex = -1;
		
		for(int i = mid+1; i < a.length; i++) {
			sum += a[i];
			if(sum > rightMaxSum) {
				rightMaxSum = sum;
				maxRightIndex = i;
			}
		}
		
		int totalSum = leftMaxSum + rightMaxSum;
		
		System.out.println("Max value of sum is: " + totalSum);
		System.out.println("Indices for this sum: i = " + maxLeftIndex + " and j = " + maxRightIndex);
		
	}
	
	public static void main(String[] args) {
		
		MaxSumSubArray maxSumSubArray = new MaxSumSubArray();
		
		System.out.println("Input Array is:");
		for(int i : maxSumSubArray.a) {
			System.out.print(i + " ");
		}
		
		maxSumSubArray.findMaxSum();

	}

}
