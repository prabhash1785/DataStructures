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
	
	public static void insertSort(int[] a) {
		
		int size = a.length;
		System.out.println("Size of given list is: " + size);
		
		for(int i = 1; i < size; i++) {
			int j = i;
			while((j > 0) && (a[j] < a[j-1])) {
				swap(a, j, j-1);
				j--;
			}
			
		}
		
		System.out.println("Here is the sorted array: ");
		for(int i = 0; i < size; i++)
			System.out.println(a[i]);
		
	}
	
	private static void swap(int[] b, int c, int d) {
		int temp = b[c];
		b[c] = b[d];
		b[d] = temp; 
	}
	
	public static void main(String[] args) {
		
		long t1 = System.currentTimeMillis();
		insertSort(list);
		long t2 = System.currentTimeMillis();
		
		System.out.println("Time taken in insertion sort is: " + (t2 - t1));

	}

}
