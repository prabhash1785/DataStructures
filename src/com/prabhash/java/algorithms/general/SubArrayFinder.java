package com.prabhash.java.algorithms.general;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/** 
 * Program to determine if array B is a sub array of array A or not.
 * This program runs in Linear time O(n) instead of O(n ^ 2)  
 * @author Prabhash Rathore
 *
 */

public class SubArrayFinder {

	private int[] a = {2, 5, 7, 9, 8, 1};
	
	private int[] b = {9, 5, 2, 11};
	
	/**
	 * Implementation using a TreeSet which reduced the time complexity to O(n)
	 * @param a[]
	 * @param b[]
	 * @return boolean
	 */
	public boolean checkSubArray(int[] a, int[] b) {
		
		boolean isSubArray = true;
		
		a = this.a;
		b = this.b;
		
		// Instead of TreeSet use HashSet implementation as this will improve the performance
		//Set<Integer> s = new HashSet<Integer>(); 
		Set<Integer> s = new TreeSet<Integer>();
		
		for(int i=0; i < a.length; i++) {
			s.add(a[i]);
		}
		
		//define an iterator to traverse elements in an array
		System.out.println("Here are elements saved in the set.... ");		
		Iterator<Integer> iterator = s.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		for(int i = 0; i < b.length; i++) {
			if(!s.contains(b[i])) {
				isSubArray = false;
				return isSubArray;
			}
		}	
		
		
		return isSubArray;
	}
	
	/**
	 * Implementation using regular arrays. Brute force algorithm, Time complexity is O(n ^ 2)
	 * @param a[]
	 * @param b[]
	 * @return boolean
	 */
	public boolean checkSubArray2(int[] a, int[] b) {
		boolean isSubArray = false;
		
		a = this.a;
		b = this.b;
		
		for(int i = 0; i < b.length; i++) {
			
			isSubArray = false;
			
			for(int j = 0; j < a.length; j++) {
				if(b[i] == a[j]) {
					isSubArray = true;
					System.out.println("Value of isSubArray " + isSubArray);
					break;
				}
			}
			
			if(isSubArray == false) {
				return isSubArray; //exit the program if any element in array B doesn't match in Array A
			}
			
		}
		
		return isSubArray;
	}
	
	
	public static void main(String[] args) {
		
		SubArrayFinder subArrayFinder = new SubArrayFinder();
		
		//Calling the Tree Set implementation
		if(subArrayFinder.checkSubArray(subArrayFinder.a, subArrayFinder.b)) {
			System.out.println("Array B is a sub-array of Array A..");
		} else {
			System.out.println("Array B is a NOT sub-array of Array A..");
		}
		
		// Calling the ordinary array implementation
		if(subArrayFinder.checkSubArray2(subArrayFinder.a, subArrayFinder.b)) {
			System.out.println("Array B is a sub-array of Array A..");
		} else {
			System.out.println("Array B is a NOT sub-array of Array A..");
		}

	}

}
