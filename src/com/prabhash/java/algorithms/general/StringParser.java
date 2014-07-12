package com.prabhash.java.algorithms.general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class StringParser {

	private static String s = "This is my dog my dog is the best labs are the best dogs";
	
	/**
	 * Print the list of unique words from a given input string with a lot of duplicate words
	 * Logic - Split string and store in set. In set only unique words will be stored. 
	 * This implememtation uses Java standard API
	 * @param String
	 * @return List<String>	
	 */
	public List<String> printUniqueWords(String s) {
		
		List<String> list = new ArrayList<String>();
		Set<String> set = new HashSet<String>();		
				
		StringTokenizer tokenizer = new StringTokenizer(s);
		while(tokenizer.hasMoreTokens()) {
			set.add(tokenizer.nextToken());
		}
		
		list.addAll(set);
		return list;
			
	}
	
	/**
	 * Parses a given string with duplicate words and prints the unique words. This is done without using Java API.
	 * @param String
	 * @return void
	 */
	public void printUniqueWords2(String s) {
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		StringTokenizer tokenizer = new StringTokenizer(s);
		while(tokenizer.hasMoreTokens()) {
			String temp = tokenizer.nextToken();
			if(map.containsKey(temp)) {
				Integer i = map.get(temp);
				i++;
				map.put(temp, i);
				
			} else {
				map.put(temp, 1);
			}
			
		}
		
		for(String str : map.keySet()) {
			
			System.out.println("Key = " + str + " Value: " + map.get(str));
		}
		
	}
	
	/**
	 * Print a given string in reverse order
	 * @param String
	 * @return void
	 */
	public void reverseString(String s) {
		
		Stack<String> stack = new Stack<String>();
		String[] input = s.split(" "); //Break string based on space
		
		System.out.println("Here is original string: ");
		for(String str : input) {
			System.out.println(str);
			stack.push(str); // Store elements in Stack
		}
		
		System.out.println("Here is the string in reversed order: ");
		
		for(int i = 0; i < input.length; i++) {
			System.out.println(stack.pop());
		}
					
	}
	
	
	public static void main(String[] args) {
		
		List<String> uniqueWordsList = new ArrayList<String>();
		
		StringParser stringParser = new StringParser();
		uniqueWordsList = stringParser.printUniqueWords(s);
		
		Iterator<String> iterator = uniqueWordsList.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		} 
		
		// Call other base
		stringParser.printUniqueWords2(s);
		
		//call method to reverse string
		stringParser.reverseString("I love you Amber so much");

	}

}
