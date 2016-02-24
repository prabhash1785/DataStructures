package com.prabhash.java.algorithms.sorting;

/**
 * Divide the given array into smaller possible unit which in most cases is one. It's a portion of Divide and Conquer algorithm.
 * 
 * @author prrathore
 *
 */
public class DivisionAlgo {
	
	public static void divide(int[] a) {
		
		if(a == null) {
			return;
		}
		
		divideHelper(a, 0, a.length - 1);
	}
	
	private static void divideHelper(int[] a, int start, int end) {
		
		if(start > end) {
			return;
		}
		
		if(start == end) {
			System.out.print(a[start] + " :: ");
			return;
		}
		
		int mid = (start + end) / 2;
		
		divideHelper(a, start, mid);
		divideHelper(a, mid + 1, end);
	}
	
	public static void main(String[] args) {
		
		int[] a = new int[] {2, 4, 18, 6, 23, 100};
		
		divide(a);

	}

}
