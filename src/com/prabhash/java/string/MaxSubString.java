package com.prabhash.java.string;

/**
 * This program will find the maximum substring for two given strings.
 * For eg, a = "apple"		b = "ppl"
 * return 3 
 * @author Prabhash Rathore
 *
 */
public class MaxSubString {
	
	/**
	 * Brute Force Algorithm to find maximum sub-string.
	 * Compare both the strings and keep track of the maximum number of match, return max. 
	 * Time Complexity: O(mn)
	 * Space: O(1)
	 */
	public static int findMaxSubString(String a, String b) {
		if(a == null || b == null) {
			return 0;
		}
		
		int m = a.length();
		int n = b.length();
		
		/* 
		 * Outer for loop needs to run on longer string for correct result so swap strings if first string is 
		 * shorter than second string in provided parameters
		 * 
		 */
		if(m < n) {
			String temp = a;
			a = b;
			b = temp;
			
			int tempInt = m;
			m = n;
			n = tempInt;
			
		}		
		
		int max = 0; //keep track of max number of character match
		
		for(int i = 0; i < m; i++) {
			int j = 0;
			while(j < n && a.charAt(i + j) == b.charAt(j)) {
				j++;
			}
			if(j == n) { //all the characters matched, this is the longest substring possible so return here
				return n;
			}
			if(j > max) {
				max = j;
			}
		}
		
		return max;
	}

	public static void main(String[] args) {

		String s = "alphabets";
		String t = "phab";
		
		System.out.println("Maximum sub-string is: " + findMaxSubString(s, t));
	}

}
