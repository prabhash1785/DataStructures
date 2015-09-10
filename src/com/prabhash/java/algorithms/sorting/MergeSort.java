package com.prabhash.java.algorithms.sorting;

/**
 * 
 * Merge Sort - Divide and Conquer algorithm to sort arrays. 
 * Process - First sort left half, sort right half and then merge both halves.
 * Properties of Merge Sort:
 * 		- Stable Sort (Maintains the input order of equal elements in sorted array)
 * 		- This is used to sort a Linked-List.
 * 		- Used for Collections.sort() in Java.
 * 		- Merge Sort's merge operation is useful in Online Sorting.
 * Best Time Complexity = O(nlog(n))
 * Worst Time Complexity = O(nlog(n))
 * Space complexity = O(n)
 * @author Prabhash Rathore 
 *
 */

public class MergeSort {
  private int[] numbers;
  private int[] helper;

  private int number;
  
  public static void main(String[] args) {
	  //int[] a = {12, 2, 17, 1, 5, 10};
	  int[] a = new int[] {6, 5, 3, 1, 8, 7, 2, 4, 45, 12, 67, 39, 89};
	  new MergeSort().sort(a);
  }

  public void sort(int[] values) {
    this.numbers = values;
    number = values.length;
    this.helper = new int[number];
    mergesort(0, number - 1);
    
    System.out.println("Here is the sorted array: ");
	for(int k = 0; k < values.length; k++)
		System.out.print(values[k] + " ");
    
  }

  private void mergesort(int low, int high) {
    // check if low is smaller then high, if not then the array is sorted
    if (low < high) {
      // Get the index of the element which is in the middle
      int middle = low + (high - low) / 2;
      // Sort the left side of the array
      mergesort(low, middle);
      // Sort the right side of the array
      mergesort(middle + 1, high);
      // Combine them both
      merge(low, middle, high);
    }
  }

  private void merge(int low, int middle, int high) {

    // Copy both parts into the helper array
    for (int i = low; i <= high; i++) {
      helper[i] = numbers[i];
    }
    
    int i = low;
    int j = middle + 1;
    int k = low;
    // Copy the smallest values from either the left or the right side back
    // to the original array
    while (i <= middle && j <= high) {
      if (helper[i] <= helper[j]) {
        numbers[k] = helper[i];
        i++;
      } else {
        numbers[k] = helper[j];
        j++;
      }
      k++;
    }
    
    System.out.println("i = " + i + " j = " + j + " k = " + k);
    // Copy the rest of the left side of the array into the target array
    while (i <= middle) {
      numbers[k] = helper[i];
      k++;
      i++;
    }
    
    // Copy the rest of the right side of the array into the target array
    while (j <= high) {
      numbers[k] = helper[j];
      k++;
      j++;
    }

  }
} 