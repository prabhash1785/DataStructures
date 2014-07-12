package com.prabhash.java.string;

/**
 * This class implements the logic to find Combination of a given string.
 * @author Prabhash Rathore
 *
 */

public class CombinationOfString {

	/**
	 * Method 1
	 * Print all possible combinations of r elements in a given array of size n.
	 * Numebr of Combinations = n! / (r! * (n-r)!)
	 * Ref: http://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/ 
	 * 
	 */
	public static void printCombination(int[] arr, int n, int r) {
		
		// A temporary array to store all combination one by one
		int[] data = new int[r];
		
		// Print all combination using temprary array 'data[]'
	    combinationUtil(arr, data, 0, n-1, 0, r);		
		
	}
	
	/**
	 * Utility method to find combinations of string
	 *  
	 */
	private static void combinationUtil(int[] arr, int[] data, int start, int end, int index, int r) {
		if(index == r) {
			for(int j = 0; j < r; j++) {
				System.out.print(data[j] + " ");
			}
			System.out.print("\n");
			return;
		}
		
		// replace index with all possible elements. The condition
	    // "end-i+1 >= r-index" makes sure that including one element
	    // at index will make a combination with remaining elements
	    // at remaining positions
	    for (int i=start; i<=end && end-i+1 >= r-index; i++)
	    {
	        data[index] = arr[i];

	        combinationUtil(arr, data, i+1, end, index+1, r);
	        
	    }
	}
	
	/**
	 * Method 2 to find combination of Strings
	 * All combinations = (2 ^ n) - 1
	 * Ref: http://introcs.cs.princeton.edu/java/23recursion/Combinations.java.html
	 * 
	 */
	public static void comb1(String s) {
		comb1("", s);
	}
	
	private static void comb1(String prefix, String s) {
		if(s.length() > 0) {
			System.out.println(prefix + s.charAt(0));
			comb1(prefix + s.charAt(0), s.substring(1));
			comb1(prefix, s.substring(1));
		}
	}
	
	/**
	 * Method 3 - Alternate Implementation
	 * All combinations = (2 ^ n) - 1
	 * 
	 * Ref: http://introcs.cs.princeton.edu/java/23recursion/Combinations.java.html
	 * 
	 */
	public static void comb2(String s) {
		comb2("", s); 
	}
	
    private static void comb2(String prefix, String s) {
        System.out.println(prefix);
        for (int i = 0; i < s.length(); i++)
            comb2(prefix + s.charAt(i), s.substring(i + 1));
    } 
	
	public static void main(String[] args) {
		
		int[] array = new int[] {1, 2, 3, 4, 5};
		
		// Method 1
		printCombination(array, array.length, 2);
		
		//Method 2
		comb1("abcd");
		
		//Method 3
		comb2("uvw");

	}

}
