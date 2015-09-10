package com.prabhash.java.algorithms.sorting;

/**
 * 
 * Quicksort, or partition-exchange sort, is a sorting algorithm developed by Tony Hoare that, on average, makes O(n log n) comparisons to sort n items. It's
 * a Divide and Conquer Algorithm.
 * 
 * In Quick Sort, we pick a random element called pivot and partition the array such that all numbers that are less than the partitioning element
 * comes before all elements that are greater than it. Repeated partition of the array and it's sub-arrays around an element eventually sorts the array.
 * 
 * Properties of Quick Sort:
 * 		- Since the partitioned element is not guaranteed to be the median or anywhere near the median, this can make Quick Sort sorting slow and in these cases
 * 		  time complexity could be O(n ^ 2)
 * 		- Before starting the quick sort, shuffle the algorithm for higher performance guarantee.
 * 		- This is a non-stable sort.
 * 		
 * Application:
 * 		- Java's Arrays.sort() uses Quick Sort algorithm.
 *  
 * Best Time Complexity = O(nlog(n))
 * Worst Time Complexity = O(n^2)
 * Space complexity = O(n) auxiliary naive
 * Space complexity = O(log n) auxiliary (Sedgewick 1978) [Used by the System Stack for Recursion]  
 * @author Prabhash Rathore 
 *
 */
public class QuickSort {
	
	//Instantiation not allowed as all the methods are static
	private QuickSort() throws InstantiationException {
		throw new InstantiationException("All methods are static so no need to instatiate this class!!");
	}
	
	public static void qSort(int[] a, int low, int high) {
		if (low < high) {
			int pivot = partition(a, low, high);
			System.out.println("\nPivot = " + pivot);
			
			qSort(a, low, pivot - 1);
			printArray(a, low, pivot - 1);
			System.out.println();
			
			qSort(a, pivot, high);
			printArray(a, pivot, high);
			System.out.println();
		}
				
	}
	
	private static int partition(int arr[], int left, int right) {
		int pivot = arr[(left + right) / 2]; // Pick a pivot point. Can be an element. For better performance, choose pivot as Median.

		while (left <= right) { // Until we've gone through the whole array
			// Find element on left that should be on right
			while (arr[left] < pivot) { 
				left++;
			}

			// Find element on right that should be on left
			while (arr[right] > pivot) {
				right--;
			}

			// Swap elements, and move left and right indices
			if (left <= right) {
				swap(arr, left, right);
				left++;
				right--;
			}
		}
		return left; 
	}
	
	private static void swap(int[] a, int s, int t) {
		int temp = a[s];
		a[s] =  a[t];
		a[t] = temp;
	}
	
	/**
	 * Utility Method to print elements of an array
	 * 
	 */
	private static void printArray(int[] a, int low, int high) {
		
		for(int i = low; i <= high; i++)
			System.out.print(a[i] + " ");
		
	}	
	
	
	public static void main(String[] args) {
		//int[] array = {12, 5, 23, 4, 18, 1};
		int[] array = {18, 14, 4, 9, 12, 7};
		
		System.out.println("Here is the original array:");
		printArray(array, 0, array.length - 1);
		
		qSort(array, 0, array.length - 1); //Sort the array using Quick Sort
		
		System.out.println("\nHere is the sorted array: ");
		printArray(array, 0, array.length - 1);
		
	}
	
	

}
