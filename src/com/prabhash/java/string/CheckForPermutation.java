package com.prabhash.java.string;

import java.util.HashSet;
import java.util.Set;

/**
 * Checking if a given text is a permutation of other given text.
 * Checking for two things in order to call it a permutation:
 *  - First check if their lengths are equal, if not they are not permutation
 *  - Second, check all elements in original string are present in the given text to be tested for permutation
 *  For Optimization, store one string in HashSet for constant time comparisons.
 *  Complexity:
 *  Time: O(n)
 *  Space: O(n)
 * @author Prabhash Rathore
 *
 */
public class CheckForPermutation {

	public static void main(String[] args) {
		
		//Check if b is a permutation of a
		String a = "god  ";
		String b = "dog"; 
		
		if(a.length() != b.length()) {
			System.out.println("Not a permutation..");
			return;
		} 
		
		Set<Character> set = new HashSet<Character>();
		for(int i = 0; i < b.length(); i++)
			set.add(b.charAt(i));
		
		//All the elements of "a" should be in "b" for permutation
		for(int j = 0; j < a.length(); j++) {
			if(!set.contains(a.charAt(j))) {
				System.out.println("Not a permutation!!");
				return;
			}
		}
		
		System.out.println("Permutation!!!");		

	}

}
