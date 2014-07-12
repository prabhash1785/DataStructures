/*
 * Find the number of pairs in a given array whose difference is equal to a given number k.
 * eBay Interview Question
 * @author Prabhash Rathore
 * 
 */
package com.prabhash.java.algorithms.general;

import static java.lang.Math.abs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindPairsWithDiff {
	
	/**
	 * This is a Brute Force algorithm which compares every element in the array with rest of the elements and increments
	 * the count whenever differenence is equal to k.
	 * Time Complexity: O(n ^ 2)
	 * Space Complexity: O(1)
	 */
	public int findPairsWithDiff1(int[] list, int k) {
		int count = 0;
		int size = list.length;
		
		for(int i = 0; i < size - 1; i++) {
			for(int j = i + 1; j < size; j ++) {
				if(abs(list[i] - list[j]) == k) {
					System.out.println(list[i] + " " + list[j]);
					count++;
				}
			}
		}
		
		return count;
		
	}
	
	/**
	 * This method uses Binary Search to find the pairs in list with the given difference. This improves the run time of the
	 * algorithm.
	 * Logic is to loop through all the elements and then look for (a + k) element in remaining array using Binary Search.
	 * a - b = k => a = b + k
	 * Time Complexity: O(nlogn)
	 * Space Complexity: O(1)
	 * 
	 */
	public int findPairsUsingBinarySearch(int[] list, int k) {
		int count = 0;
		int size = list.length;
		Arrays.sort(list); //Sort the array so that Binary Search can be used
		for(int i = 0; i < size - 1; i++) {
			if(Arrays.binarySearch(list, i + 1, size, list[i] + k) > 0) {
				System.out.println(list[i] + " " + (list[i] + k));
				count++;
			}
		}		
		
		return count;
	}
	
	/**
	 * This algorithm will use HashSet implementation to find the number of pairs with given difference.
	 * HashSet.contains(list[i] + k)
	 * Time Complexity: O(n)
	 * Space Complexity: O(n)
	 */
	public int findPairsUsingSet(int[] list, int k) {
		int count = 0;
		int size = list.length;
		//Store array in a Set
		Set<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < size; i++)
			set.add(list[i]);
		
		for(int j = 0; j < size - 1; j++) {
			if(set.contains(list[j] + k)) {
				System.out.println(list[j] + " " + (list[j] + k));
				count++;
			}
		}
		
		return count;
	}

	public static void main(String[] args) {
		
		int[] a = new int[] {12, 4, 27, 8, 1, 7, 5, 23, 16, 13, 19};
		int diff = 15;
		
		FindPairsWithDiff findPairs = new FindPairsWithDiff();
		int count = findPairs.findPairsWithDiff1(a,  diff);
		System.out.println("Here is the count of pairs: " + count);	
		
		System.out.println("Finding pairs with Binary Search method: " + findPairs.findPairsUsingBinarySearch(a, diff));
		
		System.out.println("Finding pairs with HashSet method: " + findPairs.findPairsUsingSet(a, diff));

	}

}
