/*
 * Find a Power Set of a given set.
 * Number of Power Set for a set with elements  = (2 ^ n)
 * @author Prabhash Rathore
 * 
 */
package com.prabhash.java.algorithms.general;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PowerSet {
	
	/**
	 * This implementation works on idea that each element in the original set can either be in the power set
	 * or not in it. With n elements in the original set, each combination can be represented by a binary string
	 * of length n. To get all possible combinations, all you need is a counter from 0 to 2n - 1. 
	 * If the kth bit in the binary string is 1, the kth element of the original set is in this combination. 
	 * 
	 * Time Complexity - O(n * (2 ^ n))
	 * Space Complexity -  O(1)
	 * 
	 */
	public static <T> Set<Set<T>> binaryPowerSet(Set<T> set) {
		
		Set<Set<T>> powerSet = new HashSet<Set<T>>();
		
		T[] a = (T[]) set.toArray();
		int n = a.length; //size of elements in input set
		
		int powerSetSize = (int) Math.pow(2, n);
		
		for(int i = 0; i < powerSetSize; i++) {
			
			String binary = Integer.toBinaryString(i);
			while(binary.length() < n) {
				binary = "0" + binary;
				System.out.println("Binary number is -> " + binary);
			}
			Set<T> tempSet = new HashSet<T>();
			for(int j = 0; j < n; j++) {
				if(binary.charAt(j) == '1') {
					tempSet.add(a[j]);
				}
			}
			powerSet.add(tempSet);
			
		}		
			
		return powerSet;
	}
	
	/**
	 * Recursive Algorithm to find all the subsets (or PowerSet) for a given set.
	 * Time Complexity: O(2 ^ n)
	 * Space Complexity: O(2 ^ n)
	 * @param <T>
	 * 
	 */
	public <T> List<List<T>> getSubsets(List<T> set, int index) {
		List<List<T>> allSubsets;
		
		if(index == set.size()) {
			allSubsets = new ArrayList<List<T>>();
			allSubsets.add(new ArrayList<T>()); //add empty subset
		} else {
			allSubsets = getSubsets(set, index + 1);
			T item = set.get(index);
			List<List<T>> moreSubsets = new ArrayList<List<T>>();
			for(List<T> subset : allSubsets) {
				List<T> newSubset = new ArrayList<T>();
				newSubset.addAll(subset);
				newSubset.add(item);
				moreSubsets.add(newSubset);
			}
			
			allSubsets.addAll(moreSubsets);
			
		}
		
		return allSubsets;
		
	}

	public static void main(String[] args) {
		
		Set<Integer> set = new HashSet<Integer>();
		set.add(1);
		set.add(2);
		set.add(3);
		
		//PowerSet using Binary String algorithm
		Set<Set<Integer>> powerSet = binaryPowerSet(set);
		for(Set<Integer> s : powerSet) {
			System.out.print("{");
			Iterator<Integer> iterator = s.iterator();
			while(iterator.hasNext()) {
				System.out.print(iterator.next() + " ");
			}
			System.out.print("}\n");
		}
		
		//Recursive method call to find power set
		List<Character> set2 = new ArrayList<Character>();
		set2.add('u');
		set2.add('v');
		set2.add('w');
		set2.add('x');
		
		System.out.println("\nRecursive call to find Power Set of a given set:");
		
		List<List<Character>> allSubsets = new PowerSet().getSubsets(set2, 0);
		for(List<Character> list1 : allSubsets) {
			System.out.print("{");
			for(Character ch : list1) {
				System.out.print(ch + " ");
			}
			System.out.print("}\n");
		}

	}

}