/**
 * Check if all the characters in string are unique.
 * 
 */

package com.prabhash.java.string;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

public class CheckForUniqueness {

	/*
	 * Brute Force Implementation
	 * Input: abcdacd -> Output: Non-Unique
	 * Input: abcde -> Output: Unique
	 * Complexity:
	 * Time: O(n ^ 2)
	 * Space: O(1)
	 *  
	 */
	public boolean isUniqueBruteForce(String input) {
		boolean unique = false;
		if(input == null) {
			return unique;
		}
		
		String text = input.toLowerCase();
		int n = text.length();
				
		for(int i = 0; i < n - 1; i++) {
			unique = false; //reset flag to false for next character comparisons
			for(int j = i + 1; j < n; j++) {
				if(text.charAt(i) == text.charAt(j)) { //abcde
					unique = false;
					return unique;
				}
				unique = true;
			}
		}
		
		return unique;
	}
	
	/*
	 * This is an improved version of checking the uniqueness of a string.
	 * We will use a Set to keep track of visited elements. Whenever you find an element is already present in Set then it's non-unique, return false;
	 * For eg, Input = abcab 
	 * Complexity:
	 * Time = O(n)
	 * Space = O(1)
	 * 
	 */	
	public boolean isUnique(String input) {
		boolean result = false;
		if(input == null) {
			return result;
		} 
		
		String text = input.toLowerCase();
		int n = text.length();
		//boolean[] unique = new boolean[256]; //256 unique characters in ASCII Character-Set
		Set<Integer> visitedElements = new HashSet<Integer>(); //Additional data structure to keep track of visited elements
		for(int i = 0; i < n; i++) {
			int val = text.charAt(i);
			if(visitedElements.contains(val)) {			
				result = false;
				return result;
			}
			visitedElements.add(val);
			result = true;
		}
		
		return result;		
	}
	
	/*
	 * This implementation uses a BitSet to keep track of visited characters. This uses less memory compared to a Set.
	 * Complexity:
	 * Time: O(n)
	 * Space: O(1)
	 * 
	 */
	public boolean isUnique2(String input) {
		boolean result = false;
		if(input == null) {
			return result;
		}
		
		if(input.length() > 256) { //Assuming String is ASCII, unique characters can't be more than 256 chracters
			return result;
		}
		
		String text = input.toLowerCase();
		int n = text.length();
		BitSet visitedElements = new BitSet(); //Bitset to keep track of visited characters. This is efficient in terms of memory.
		for(int i = 0; i < n; i++) {
			int val = text.charAt(i);
			if(visitedElements.get(val)) {
				result = false;
				return result;
			}
			visitedElements.set(val, true);
			result = true;
		}
		
		return result;		
	}
	
	public static void main(String[] args) {
		
		CheckForUniqueness check = new CheckForUniqueness();
		
		String input = "ILoveMaxi";
		
		//Calling the Brute Force method
		if(check.isUniqueBruteForce(input)) {
			System.out.println("All characters are unique!!");
		} else {
			System.out.println("Non-Unique chracters");
		}
		
		//Calling the method based on the Set Data Structure
		if(check.isUnique(input)) {
			System.out.println("All characters are unique!!");
		} else {
			System.out.println("Non-Unique chracters");
		}	
		
		//Calling the method based on the BitSet Data Structure
		if(check.isUnique2(input)) {
			System.out.println("All characters are unique!!");
		} else {
			System.out.println("Non-Unique chracters");
		}	

	}

}
