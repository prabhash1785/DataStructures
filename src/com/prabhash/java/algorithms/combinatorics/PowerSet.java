package com.prabhash.java.algorithms.combinatorics;

import java.util.ArrayList;
import java.util.List;

/**
 * Find powerset of a given string.
 * 
 * @author Prabhash Rathore
 *
 */
public class PowerSet {
	
	/**
	 * Find powerset of a given string.
	 * 
	 * Time Complexity: O(2 ^ n)
	 * 
	 * @param s
	 * @return powerSet
	 */
	public static List<String> getPowerSet(String s) {
		if(s == null) {
			throw new NullPointerException();
		}
		
		final List<String> powerSet = new ArrayList<>();
		powerSet.add(""); // add empty subset
		
		for(int i = 0; i < s.length(); i++) {
			String prefix = String.valueOf(s.charAt(i));
			String suffix = s.substring(i + 1);
			
			powerSet.add(prefix);
			powerSetHelper(prefix, suffix, powerSet);
		}
		
		return powerSet; 
	}
	
	private static void powerSetHelper(String prefix, String suffix, List<String> list) {
		for(int i = 0; i < suffix.length(); i++) {
			String subset = prefix + suffix.charAt(i);
			list.add(subset);
			
			// recurse on the remaining suffix with subset as prefix
			powerSetHelper(subset, suffix.substring(i + 1), list);
		}
	}
	
	public static void printAllSubSets(List<String> list) {
		if(list == null) {
			throw new NullPointerException();
		}
		
		for(String s : list) {
			System.out.println("{" + s + "} ");
		}
	}

	public static void main(String[] args) {
		String input = "abcde";
		List<String> powerSet = getPowerSet(input);
		System.out.println("Here is powerset of string " + input + ":");
		System.out.println("Number of subsets: " + powerSet.size());
		printAllSubSets(powerSet);
	}

}
