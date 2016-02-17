package com.prabhash.java.algorithms.sorting;

/**
 * Insertion Sort maintains a sorted list on the left hand side and keeps on inserting the elements from right side array into the left sorted array.
 * Insertion sort is twice as fast as Selection Sort.
 * Time Complexity - O(n ^ 2)
 * @author Prabhash Rathore
 * @date Jan 26, 2014
 *
 */

public class InsertionSort {

	private static final int[] list = {12, 4, 87, 1, 90, 21, 18, 34, 27, 19};
	
	/**
	 * Insertion Sort implementation.
	 * 
	 * In Insertion sort, there is no swapping done. In this algorithm, left side of array is always sorted and right hand side array is unsorted. We pick one element from right side
	 * array and insert that in the correct position in left sorted array. This process involves continuously moving left hand side array elements to one position right until the correct
	 * position is determined for the element to be inserted.
	 * 
	 * T = O(n ^ 2)
	 * S = O(1)
	 * 
	 * @param a
	 * @throws Exception
	 */
	public static void insertionSort(int[] a) throws Exception {
		
		if(a == null) {
			throw new Exception("Array is null");
		}
		
		for(int i = 1; i < a.length; i++) {
			
			int current = a[i];
			int j = i - 1;
			
			while(j >= 0 && a[j] > current) {
				a[j+1] = a[j];
				j--;
			}
			
			a[j+1] = current;
			
		}
		
	}
	
	private static void printArray() {
		for(int i = 0; i < list.length; i++) {
			System.out.print(list[i] + " ");
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println("Original array:");
		printArray();
		
		long t1 = System.nanoTime();
	
		try {
			insertionSort(list);
		} catch(Exception e) {
			System.out.println("\nArray is null");
		}
		
		long t2 = System.nanoTime();
		
		System.out.println("\n\nTime taken in insertion sort is: " + (t2 - t1) + " nano seconds");
		
		System.out.println("\nSorted array:");
		printArray();

	}

}
