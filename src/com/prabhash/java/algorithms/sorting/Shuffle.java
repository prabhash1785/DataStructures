package com.prabhash.java.algorithms.sorting;

import java.util.Random;

/*
 * Shuffling is one of the real time application of sorting algorithms. This is used in online poker games.
 * Time complexity is O(n).
 * @author Prabhash Rathore
 */
public class Shuffle {

	public static int[] shuffleArray(int[] a) {
		
		//now shuffle the array
		for(int i = 0; i < a.length; i++) {
			int rand = generateRandomNums(i);
			swap(a, i, rand);
		}
		
		return a;
		
	}
	
	private static void swap(int[] b, int m, int n) {
		int temp = b[m];
		b[m] = b[n];
		b[n] = temp;
	}
	
	private static int generateRandomNums(int n) {
		Random random = new Random();
		int r = random.nextInt(n + 1);
		System.out.println("Here is the computed random number: " + r);
		return r;
	}
	
	public static void main(String[] args) {
		
		int[] array = {2, 6, 8, 10, 34, 56, 89, 108, 200};
		array = shuffleArray(array);
		
		System.out.println("Here is shuffled array:");
		for(int j = 0; j < array.length; j++)
			System.out.print(array[j] + " ");

	}

}
