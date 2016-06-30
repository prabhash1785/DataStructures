package com.prabhash.java.algorithms.dynamicprogramming;

/**
 * Given two sequences, find the length of longest subsequence present in both of them. A subsequence is a sequence 
 * that appears in the same relative order, but not necessarily contiguous. For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc 
 * are subsequences of “abcdefg”.
 * 
 * @author prrathore
 *
 */
public class LongestCommonSubsequence {
	
	/**
	 * Recursively compare character in first string to the second string.
	 * If ith character match then add 1 to lcs and recurse for remaining string.
	 * If ith character does not match then find the max of lcs F(s, t, m - 1, n) and F(s, t, m, n - 1).  
	 * 
	 * Time Complexity: O(2 ^ (m + n))
	 * 
	 * @param s
	 * @param t
	 * @return lcs
	 */
	public static int findLCSUsingBruteForce(String s, String t) {
		if(s == null || t == null) {
			return 0;
		}
		
		return findLCSUsingBruteForceHelper(s, t, s.length() - 1, t.length() - 1);
	}
	
	private static int findLCSUsingBruteForceHelper(String s, String t, int m, int n) {
		if(m < 0 || n < 0) {
			return 0;
		}
		
		if(s.charAt(m) == t.charAt(n)) {
			return 1 + findLCSUsingBruteForceHelper(s, t, m - 1, n - 1);
		} else {
			return Math.max(findLCSUsingBruteForceHelper(s, t, m - 1, n), findLCSUsingBruteForceHelper(s, t, m, n - 1));
		}
	}
	
	/**
	 * Find LCS using Dynamic Programming.
	 * 
	 *  Time Complexity: O(mn)
	 * 
	 * @param s
	 * @param t
	 * @return lcs
	 */
	public static int findLCSOptimized(String s, String t) {
		
		if(s == null || t == null) {
			return 0;
		}
		
		int[][] cache = new int[s.length() + 1][t.length() + 1];
		
		for(int i = 0; i <= s.length(); i++) {
			for(int j = 0; j <= t.length(); j++) {
				
				if(i == 0 || j == 0) {
					cache[i][j] = 0;
				} else if(s.charAt(i - 1) == t.charAt(j - 1)) {
					cache[i][j] = cache[i - 1][j - 1] + 1;
				} else {
					cache[i][j] = Math.max(cache[i - 1][j], cache[i][j - 1]);
				}
			}
		}
		
		return cache[s.length()][t.length()];
	}

	public static void main(String[] args) {
		
		final String x = "aggtab";
		final String y = "gxtxayb";
		
		int lcs = findLCSUsingBruteForce(x, y);
		System.out.println("Longest common sub sequence: " + lcs);
		
		int lcsOptimized = findLCSOptimized(x, y);
		System.out.println("Longest common sub sequence using Dynamic programming: " + lcsOptimized);
	}

}
