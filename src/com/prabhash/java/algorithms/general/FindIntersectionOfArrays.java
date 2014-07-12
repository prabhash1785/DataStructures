/**
 * Amazon Technical Interview
 * Program to find the intersection of 2 arrays and then generalize this for n given arrays
 * 
 */

package com.prabhash.java.algorithms.general;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FindIntersectionOfArrays {

	private static int[] a = {2, 6, 8, 4, 3};
	private static int[] b = {6, 4, 2, 12, 5};
	
	private static final int n = 3; // number of arrays to be compared
	private static final int[][] c = {{2, 6, 4, 1}, {4, 2, 6, 9}, {7, 1, 4, 2}}; // based on value of n = 3, here are 3 arrays
	
	
	/*
	 * Find intersection of n given arrays. Time complexity is O(n ^ 2)
	 * @parameter n
	 * @return List
	 * 	
	 */
	public static List<Integer> findIntersectionOfNArrays(int n) {
		List<Integer> list = new LinkedList<Integer>();
		
		int[] tempArray = c[0];		
				
		for(int i = 0; i+1 < n; i++) {
			
			list = findIntersection(tempArray, c[i+1]);
			
			//empty the elements in array1 for data inconsistency as higher index elements are retained in this array
			for(int k = 0; k < tempArray.length; k++) {
				tempArray[k] = -1;
			}
			
			for(int j = 0; j < list.size(); j++) { //setting the intersection of any two arrays to first parameter array for next call
				tempArray[j] = list.get(j);
			}			
			
		}
		
		return list;
	}
	
	
	/*
	 * find intersection of two given arrays. Time Complexity is O(n)
	 * @parameter int[]
	 * @parameter int[]
	 * @return List
	 */
	public static List<Integer> findIntersection(int[] a, int[] b) {
		
		List<Integer> list = new LinkedList<Integer>();
		
		// creating set to store one array in this set, this is to improve time complexity to O(n)
		Set<Integer> set = new HashSet<Integer>();
		
		for(int i = 0; i < a.length; i++) {
			set.add(a[i]);
		}
		
		for(int j = 0; j < b.length; j++) {
			if(set.contains(b[j])) {
				list.add(b[j]);
			}
		}
		
		return list;
		
	}
	
	public static void main(String[] args) {
		
		List<Integer> intersection = new LinkedList<Integer>();
		
		intersection = findIntersection(a, b);
		
		System.out.println("Here is intersection of 2 arrays: ");
		Iterator<Integer> iterator = intersection.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		intersection = null; //resetting the value of list in order to store new elements
		
		System.out.println("Code for n-array intersection starts here..");
		
		intersection = findIntersectionOfNArrays(n);
		
		System.out.println("Here is intersection of " + n + " arrays: ");
		Iterator<Integer> iterator2 = intersection.iterator();
		while(iterator2.hasNext()) {
			System.out.println(iterator2.next());
		}

	}	

}
