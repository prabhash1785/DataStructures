package com.prabhash.java.algorithms.sorting;

/**
 * Program to implement Selection Sort. In this sorting algorithm, we repeatedly identify the smallest remaining unsorted
 * element and put it at the end of the sorted portion of the array.
 * Not a very efficient algorithm for a large array as it's time complexity is O(n ^ 2).
 * @author Prabhash Rathore
 * @date Jan 26, 2014
 *
 */

public class SelectionSort {
	
	private static final int[] a = {12, 4, 10, 2, 19, 20, 1};
	
	public static void selectionSort(int[] array) {
		
		int size = array.length;
		System.out.println("\n\nHere is the printout of intermediate sorted elements:");
		
		for(int i = 0; i < size; i++) {
			for(int j = i + 1; j < size; j++) {
				if(array[i] > array[j]) {
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
			
			//Intermediate sorted array for demo purpose
			for(int k = 0; k < size; k++)
				System.out.print(array[k] + " ");
			System.out.println();
		}
		
		System.out.println("\nHere is the sorted array: ");
		for(int i = 0; i < size; i++)
			System.out.print(array[i] + " ");
		
	}
	
	public static void main(String[] args) {
		
		System.out.println("Here is original array:");
		for(int k = 0; k < a.length; k++) {
			System.out.print(a[k] + " ");
		}
		
		
		selectionSort(a);

	}

}
