/*
 * Find all the permutations of a given string.
 * Ref: http://www.programmerinterview.com/index.php/recursion/permutations-of-a-string/
 * 
 */

package com.prabhash.java.string;

import java.util.ArrayList;
import java.util.List;

public class StringPermutation {

	public static void permute(String input) {
		int n = input.length();
		boolean[] used = new boolean[n];
		StringBuffer outputString = new StringBuffer();
		char[] in = input.toCharArray();
		doPermute(in, outputString, used, n, 0);
	}
	
	/*
	 * This method finds permutation of a string using Recursion
	 * Time Complexity: O(n ^ 2)
	 * Space Complexity: O(n)
	 *  
	 */	
	private static void doPermute(char[] in, StringBuffer outputString, boolean[] used, int inputLength, int level) {
		if(level == inputLength) {
			System.out.println(outputString);
			return;
		}
		
		for(int i = 0; i < inputLength; i++) {
			
			if(used[i]) {
				continue;
			}
			
			outputString.append(in[i]);
			System.out.println("outputstring==> " + outputString + " i ==> " + i);
			used[i] = true;
			
			doPermute(in, outputString, used, inputLength, level + 1);
			used[i] = false;
			outputString.setLength(outputString.length() - 1);
			System.out.println("outputstring length: " + outputString.length() + " i ---> " + i);
		}
	}
	
	/*
	 * Solution based on Cracking the Coding Interview Book
	 */
	public static List<String> permutation(String s) {
	    if(s == null) {
	    	System.out.println("Empty input string, no permutations!!");
	    	return null;
	    }
		// The result
	    List<String> res = new ArrayList<String>();
	    // If input string's length is 1, return {s}
	    if (s.length() == 1) {
	        res.add(s);
	    } else if (s.length() > 1) {
	        int lastIndex = s.length() - 1;
	        // Find out the last character
	        String last = s.substring(lastIndex);
	        // Rest of the string
	        String rest = s.substring(0, lastIndex);
	        // Perform permutation on the rest string and
	        // merge with the last character
	        res = merge(permutation(rest), last);
	    }
	    return res;
	}

	/**
	 * @param list a result of permutation, e.g. {"ab", "ba"}
	 * @param c    the last character
	 * @return     a merged new list, e.g. {"cab", "acb" ... }
	 */
	private static List<String> merge(List<String> list, String c) {
	    List<String> res = new ArrayList<String>();
	    // Loop through all the string in the list
	    for (String s : list) {
	        // For each string, insert the last character to all possible postions
	        // and add them to the new list
	        for (int i = 0; i <= s.length(); ++i) {
	            String ps = new StringBuffer(s).insert(i, c).toString();
	            res.add(ps);
	        }
	    }
	    return res;
	}
	
	/**
	 * Method # 3
	 * Find all the Permutations for a give n string using Recursion. This is the preferred method to find permutation of a String.
	 * Time Complexity: O(n!)
	 * Ref: Cracking the Code Interview
	 */
	public static List<String> getPerms(String str) {
		if (str == null) {
			return null;
		}
		List<String> permutations = new ArrayList<String>();
		if (str.length() == 0) { // base case
			permutations.add("");
			return permutations;
		}

		char first = str.charAt(0); // get the first character
		String remainder = str.substring(1); // remove the first character
		List<String> words = getPerms(remainder);
		for (String word : words) {
			for (int j = 0; j <= word.length(); j++) {
				String s = insertCharAt(word, first, j);
				permutations.add(s);
			}
		}
		return permutations;
	}
	
	/**
	 * Method # 4
	 * 
	 * Method to find Permutation of a given array of strings.
	 * 
	 * Time Complexity: O(n!)
	 * 
	 */
	public static List<String> getPermutationsForArray(char[] input) {

		if(input == null) {
			return null;
		}
		
		List<String> permutation = new ArrayList<String>();
		
		if (input.length == 0) { // base case
			permutation.add("");
			return permutation;
		}
		
		//char c = input.charAt(0);
		char c = input[0];
		char[] rem = new char[input.length - 1];
		System.arraycopy(input, 1, rem, 0, input.length - 1); //Copy remaining elements in new array
		//String rem = input.substring(1);
		List<String> words = getPermutationsForArray(rem);
		System.out.println("Size of words: " + words.size());
		for(String word : words) {
			for(int i = 0; i <= word.length(); i++) {
				String perm = insertCharAt(word, c, i);
				permutation.add(perm);
				
			}
		}

		return permutation;
	}
	
	/**
	 * Utility method to add character to a string in a given position
	 * 
	 */
	private static String insertCharAt(String str, char ch, int i) {
		String start = str.substring(0, i);
		String last = str.substring(i);
		return start + ch + last;
	}
	
	public static void main(String[] args) {
		
		String text = "abc";
		//permute(text);
		
		//Calling method implemented using the Cracking the Coding Interview 
		/*List<String> output = new ArrayList<String>();
		output = permutation(text);
		if(output != null) {
			for(String s : output) {
				System.out.println(s);
			}
		}*/
		
		//Find permutations of String using Recursive Method # 3 above
		String input = "shop";
		System.out.println("\nPermutation of String using Recursive Method");
		List<String> permutation = getPerms(input);
		System.out.println("There are " + permutation.size() + " permutations.");
		for(String s : permutation) {
			System.out.println(s);
		}
		
		//Call Method to print permutaion of array of characters
		char[] input2 = new char[] {'x', 'y', 'z'};
		System.out.println("Permutation of array of characters: ");
		List<String> perm = getPermutationsForArray(input2);
		for(String s: perm) {
			System.out.println(s);
		}
		

	}

}
