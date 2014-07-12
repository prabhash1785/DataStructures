package com.prabhash.coursera.java.algorithms;

import java.util.Arrays;

/**
 * 3-Sum Algorithm - Given n distinct integers, how many triples sum to exactly zero.
 * Application: Computational Geometry
 * @author Prabhash Rathore
 *
 */
public class ThreeSum {

	private static int[] a = {30, -40, -20, -10, 40, 0, 10, 5};
		
	/*
	 * Brute-Force method to calculate 3-sum. 
	 * Very bad performance = O(n ^ 3)
	 * 
	 */
	public static void bruteThreeSum(int[] b) {
		
		int count = 0;
		
		for(int i = 0; i < b.length; i++) {
			for(int j = i+1; j < b.length; j++) {
				for(int k = j+1; k < b.length; k++) {
					if(b[i] + b[j] + b[k] == 0) {
						count++;
					}
				}
			}
			
		}
		
		System.out.println("Count of 3-sums using Brute Force method: " + count);
		
	}
	
	/*
	 * First sort the given array.
	 * For each pair of numbers a[i] and a[j], bnary search for -(a[i] + a[j])
	 * Time Complexity: O((n ^ 2) log n) 
	 */
	public static void threeSumUsingBinarySearch(int[] c) {
		
		//first sort the array
		Arrays.sort(c);
		System.out.println("Here is the sorted array");
		for(int i = 0; i < c.length; i ++)
			System.out.print(c[i] + " ");
		
		int threeSumCount = 0;
		
		for(int i = 0; i < c.length; i++) {
			for(int j = i + 1; j < c.length; j++) {
				if(Arrays.binarySearch(c, j + 1, c.length, -(a[i] + a[j])) > 0) {
					threeSumCount++;
				}
			}
		}
		
		System.out.println("\nHere is the count of three sums: " + threeSumCount);
		
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		bruteThreeSum(a);
		
		threeSumUsingBinarySearch(a);

	}

}
