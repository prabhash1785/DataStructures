package com.prabhash.java.string;

public class TextSearch {

	private static final String text = "dbscpoqemjalbffgz";
	private static final String pattern = "ffg";
		
	/*
	 * Pattern match for string using Naive String-Matching (Brute Force) algorithm
	 * Time Complexity is: O(nm) ~ O(n ^ 2)
	 * 		where n -> size of text
	 * 		  and m -> size of pattern
	 */
	public static int patternMatch() {
		
		int i = 0;
		int j = 0;
		int m = pattern.length();
		int n = text.length();
		
		for(i = 0; i <= n - m; i++) {
			j = 0;
			while((j < m) && (text.charAt(i+j) == pattern.charAt(j))) {
				j++;
			}
			if(j == m) {
				return i;
			}
		}
		
		return -1;		
	}
	
	public static void main(String[] args) {
		
		int position = patternMatch();
		if(position >= 0) {
			System.out.println("Here is position of match: " + position);
		} else {
			System.out.println("Pattern not present...");
		}
		

	}

}
