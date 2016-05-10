package com.prabhash.java.string;

import java.util.Arrays;

/**
 * Sufix Array for String based problems.
 * 
 * @author prrathore
 *
 */
public class SuffixArray {
	
	public static String[] createSuffixArray(String s) {
		
		if(s == null) {
			return null;
		}
		
		String[] suffixArray = new String[s.length()];
		for(int i = s.length() - 1, count = 0; i >= 0; i--, count++) {
			suffixArray[count] = s.substring(i).trim();
		}
		
		System.out.println("Suffix array before sorting:");
		for(String str : suffixArray) {
			System.out.println(str);
		}
		
		// sort the suffixArray
		Arrays.sort(suffixArray);
		return suffixArray;
	}

	public static void main(String[] args) {
		
		final String s = "America";
		String[] suffixArray = createSuffixArray(s);
		
		System.out.println("\n\nFinal Suffix array:");
		for(String suffix : suffixArray) {
			System.out.println(suffix);
		}
	}
}
