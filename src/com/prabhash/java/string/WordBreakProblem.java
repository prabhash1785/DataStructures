/*
 * Word Break Problem
 * Ref: http://thenoisychannel.com/2011/08/08/retiring-a-great-interview-problem/
 * 
 * @author Prabhash Rathore
 * 
 */

package com.prabhash.java.string;

/**
 * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * For example, given
 * s = “leetcode”,
 * dict = ["leet", "code"].
 * 
 * Return true because “leetcode” can be segmented as “leet code”.
 * 
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreakProblem {
	
	//private String text = "leetcodemaxindia";
	private String text = "hellothere";
	//private String text = "aaaaab"; //this input replicates the Worst Running Time of this algorithm which is O(2 ^ n)
	private Set<String> dictionary;
	private List<String> list; //list to store broken words for output
	
	private Map<String, String> memoized = new HashMap<String, String>(); //Used for Dynamic Programming
	
	private static int counter = 0;
		
	public WordBreakProblem() {
		dictionary = new HashSet<String>();
		dictionary.add("india");
		dictionary.add("leet");
		dictionary.add("america");
		dictionary.add("code");
		dictionary.add("max");
		dictionary.add("hell");
		dictionary.add("hello");
		dictionary.add("the");
		dictionary.add("there");
		dictionary.add("aaaaa");
		dictionary.add("aaaa");
		dictionary.add("aaa");
		dictionary.add("aa");
		dictionary.add("a");
		
		list = new LinkedList<String>();	
	}
	
	/**
	 * Method 1:
	 * This method is the most simplistic solution to break a String into two words based on dictionary match.
	 * Assumption: String has only two words.
	 * 
	 * Time Complexity: O(n) assuming String.substring() is done in O(1) time otherwise O(n ^ 2)
	 * Space Complexity: O(1) assuming no new String Objects are created in String Pool
	 */
	public String breakString(String input) {
		if(input != null) {
			int size = input.length();
			for(int i = 1; i < size; i++) {
				String left = input.substring(0, i);
				String right = input.substring(i, size);
				if(dictionary.contains(left) && (dictionary.contains(right))) {
					return left + " " + right;
				}				
			}
		}
		
		return null;		
	}
	
	/**
	 * Method 2:
	 * 
	 * This is not the BEST solution as it doesn't use BackTracking and it matches the shortest word in the Dictionary resulting in missing the long words like
	 * "Help" instead of "Helper" or "Helping".
	 * 
	 * This is a Recursive algorithm to break given String into "N" words based on Dictionary match.
	 * Recursively call the method and keep incrementing end index until there is a match. When there is a match, set start index to end and end to end + 1
	 * and keep recursing until you reach the end of String.
	 * 
	 * Time Complexity: O(n)
	 * Space Complexity: O(n) -> Using the System Stack for Recursive calls
	 * 
	 * @param input - input String to be split into words
	 * @param start - start index of string 			
	 * @param end - end index of string
	 */
	public void breakStringIntoNWords(String input, int start, int end) {
		if(input == null || input.length() == 0 || start > end || end >= input.length()) { //Base condition check for Recursion
			return;
		}
		
		String a = input.substring(start, end);
		if(dictionary.contains(a)) {
			System.out.println(a);
			breakStringIntoNWords(input, end, end + 1);
		} else {
			breakStringIntoNWords(input, start, end + 1);
		}
	}
	
	/**
	 * Method 3:
	 * 
	 * Recursive approach to find if the given words are breakable or not based on dictionary match.
	 * Saves the broken words in a list.
	 * 
	 * Time Complexity: O(2 ^ n) same as PowerSet of a Set/String
	 * Space Complexity: (1)
	 * 
	 *  @return boolean
	 * 
	 */
	public boolean isBreakableWordRecursive(String input, Set<String> dict) {
		
		if(input.length() == 0) {
			return true;
		} else {
			for(int i = 1; i <= input.length(); i++) {
				counter++;
				String firstWord = input.substring(0, i);
				String remaining = input.substring(i);
				if((dict.contains(firstWord)) && (isBreakableWordRecursive(remaining, dict))) {
					list.add(firstWord); //Saving these in a list to display the broken words in output
					return true;
				}
			}
		}
		
		return false;
		
	}
	
	/**
	 * Method 4:
	 * 
	 * This solution will use Recursive BackTracking to solve this Word Break Problem. The given input String can have any number of words in it and we need
	 * to find the longest possible words so that the String can be properly broken down.
	 * For ex, input = hellothere
	 * And if Dictionary has words {hell, hello, the, there}
	 * Then Output should be: hello there 
	 * 
	 * This is not a very optimized solution as we need to look for all the substring which are already repeated. This can be optimized using Dynamic
	 * Programming. See the solution in Method # 5 below.
	 * 
	 * Time Complexity: O(2 ^ n) same as the PowerSet of a Set/String
	 * 
	 * @param args
	 * @return String
	 */
	public String segmentString(String input) {
		if(input == null || input.length() == 0) {
			return null;
		}
		
		//if whole word exists in dictionary 
		if(dictionary.contains(input)) {
			return input;
		}
		
		for(int i = 1; i < input.length(); i++) {
			String prefix = input.substring(0, i);
			System.out.println(prefix);
			
			if(dictionary.contains(prefix)) {
				 String suffix = input.substring(i);
				 System.out.println(suffix);
				 String segSuffix = segmentString(suffix);
				 System.out.println(segSuffix);
				 if(segSuffix != null) {
					 System.out.println("Finally I am here!!");
					 return prefix + " " + segSuffix;
				 }
			}
		}
		
		return null;
	}
	
	/**
	 * Method 5: Using Recursive BackTracking and Dymanic Programming/Memoization
	 * 
	 * This solution will use Recursive BackTracking to solve this Word Break Problem. The given input String can have any number of words in it and we need
	 * to find the longest possible words so that the String can be properly broken down.
	 * For ex, input = hellothere
	 * And if Dictionary has words {hell, hello, the, there}
	 * Then Output should be: hello there 
	 * 
	 * This method uses Dynamic Programming to optimize the Time Complexity of this problem.
	 * 
	 * Time Complexity: O(n ^ 2) assuming String.substring() is done in O(1) time.
	 * 
	 * @param args
	 * @return String
	 */
	public String segmentStringUsingMemoization(String input) {
		if(input == null || input.length() == 0) {
			return null;
		}
		
		if(dictionary.contains(input)) {
			return input;
		}
		
		if(memoized.containsKey(input)) {
			System.out.println("Present in Memoized Map: " + memoized.get(input));
			return memoized.get(input);
		}
		
		for(int i = 1; i < input.length(); i++) {
			String prefix = input.substring(0, i);
			
			if(dictionary.contains(prefix)) {
				String suffix = input.substring(i);
				String segSuffix = segmentStringUsingMemoization(suffix);
				if(segSuffix != null) {
					return prefix + " " + suffix;
				}
			}
		}
		
		memoized.put(input, null);
		return null;
	}
	
	public static void main(String[] args) {
		WordBreakProblem wordBreak = new WordBreakProblem();
		
		//Method # 1 call
		System.out.println("Method 1 call output: " + wordBreak.breakString(wordBreak.text));
		
		//Method # 2 call
		wordBreak.breakStringIntoNWords(wordBreak.text, 0, 1);
		
		//Method # 3 call
		boolean result = wordBreak.isBreakableWordRecursive(wordBreak.text, wordBreak.dictionary);
		System.out.println("Method 3 - Word is breakable: " + result);
		
		if(result) {
			System.out.println("Here are the splitted words: ");
			Iterator<String> iterator = wordBreak.list.iterator();
			while(iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		}
		System.out.println("Total count is: " + counter); //printing how many times the method was executed
		
		//Method # 4 call
		System.out.println("Method 4 - Broken String => " + wordBreak.segmentString(wordBreak.text));
		
		//Method # 5 call using Dynamic Programming
		System.out.println("Method 5 - Broken String => " + wordBreak.segmentStringUsingMemoization("aaaaab"));
		

	}

}

