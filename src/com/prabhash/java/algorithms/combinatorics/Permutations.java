package com.prabhash.java.algorithms.combinatorics;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all permutations of a given string.
 * 
 * @author prrathore
 *
 */
public class Permutations {
	
	/**
	 * Find all permutations of a given string.
	 * 
	 * Time Complexity: O(n!)
	 * 
	 * @param s
	 * @return permutationList
	 */
	public static List<String> getAllPermutations(String s) {
		if(s == null) {
			throw new NullPointerException();
		}
		
		List<String> permutationList = new ArrayList<>(); 
		if(s.length() == 0) {
			permutationList.add(""); // this is important to generate permutations, it is used to append characters to empty string
			return permutationList;
		}
		
		char preChar = s.charAt(0);
		String suffix = s.substring(1);
		List<String> partialPermutations = getAllPermutations(suffix);
		for(String word : partialPermutations) {
			insertCharAtEveryPositionInString(preChar, word, permutationList);
		}
		
		return permutationList;
	}
	
	private static void insertCharAtEveryPositionInString(char c, String s, List<String> list) {
		if(s == null || list == null) {
			throw new NullPointerException();
		}
		
		for(int i = 0; i <= s.length(); i++) { // make sure to go until i <= s.length()
			String prefix = s.substring(0, i);
			String suffix = s.substring(i);
			String tempPerm = prefix + c + suffix;
			list.add(tempPerm);
		}
	}

	public static void main(String[] args) {
		String text = "abc";
		List<String> permutations = getAllPermutations(text);
		System.out.println("Number of permuations = " + permutations.size());
		for(String s : permutations) {
			System.out.println(s);
		}
	}
}
