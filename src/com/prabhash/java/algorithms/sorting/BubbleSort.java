package com.prabhash.java.algorithms.sorting;

/**
 * Bubble Sort is the most basic sorting algorithm.
 * This algorithm goes through the whole array n times. During every visit, it compares the neighboring elements
 * and does the swaps.
 * 
 * @author Prabhash Rathore
 *
 */
public class BubbleSort {
	
	/**
	 * This is a simple implementation of Bubble Sort where we traverse through the whole array n times and every time, subsequent elements are swapped.
	 * This is not efficient algorithm as even if the array gets sorted before nth visit, we keep traversing all the elements.
	 * Time Complexity: O(n ^ 2)
	 * Space Complexity: O(1) 
	 * @param list
	 * @return
	 */
	public int[] bubbleSort(int[] list) {
		if(list == null) {
			return null;
		}
		
		for(int i = 0; i < list.length; i++) {
			for(int j = 0; j < list.length - 1; j++) { //Be careful with the index of j, it may result in ArrayIndexOutOfBoundsException
				if(list[j] > list[j+1]) {
					swap(list, j, j+1);
				}
			}
		}
		
		return list;
	}
	
	/**
	 * Other efficient implementation of Bubble Sort which stops scanning the array as soon as we know the array is sorted. We know the array is sorted, when
	 * we don't make any swaps during the ith scan of array. This is done using a flag.
	 * Best Time Complexity: O(n)
	 * Average Time Complexity: O(n ^ 2)
	 * Worst Time Complexity: O(n ^ 2)
	 * Space Complexity: O(1)
	 * @param b
	 * @param m
	 * @param n
	 */
	public int[] improvedBubbleSort(int[] list) {
		if(list == null) {
			return null;
		}
		
		boolean flag = false; //flag to terminate the bubble sort as soon it is completely sorted 
		for(int i = 0; i < list.length; i++) {
			for(int j = 0; j < list.length - 1; j++) {
				if(list[j] > list[j+1]) {
					swap(list, j, j+1);
					flag = true;
				}
			}
			
			if(!flag) {
				break;
			}
		}
		
		return list;
	}
	
	private void swap(int[] b, int m, int n) {
		int temp = b[m];
		b[m] = b[n];
		b[n] = temp;
	}
	
	private void displayArray(int[] a) {
		System.out.println("Here is the Sorted Array:");
		for(int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
	}

	public static void main(String[] args) {
		
		int[] array = new int[] {12, 4, 10, 2, 19, 20, 1};
		
		BubbleSort sort = new BubbleSort();
		//array = sort.bubbleSort(array);
		array = sort.improvedBubbleSort(array);
		
		sort.displayArray(array);

	}

}
