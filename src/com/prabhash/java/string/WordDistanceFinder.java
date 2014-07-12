/*
 * This class will be given a list of words (such as might be tokenized
 * from a paragraph of text), and will provide a method that takes two
 * words and returns the shortest distance (in words) between those two
 * words in the provided text. 
 * Example:
 *   WordDistanceFinder finder = new WordDistanceFinder(Arrays.asList("the", "quick", "brown", "fox", "quick"));
 *   assert(finder.distance("fox","the") == 3);
 *   assert(finder.distance("quick", "fox") == 1);
 *   @LinkedIn Interview Question
 *   @author Prabhash Rathore
 * 
 */
package com.prabhash.java.string;

public class WordDistanceFinder {
	
	private String[] text;
	
	public WordDistanceFinder(String[] list) {
		this.text = list;
	}

	/**
	 * Brute Force method to find the distance between two words.
	 * Time Complexity: O(n)
	 * Space Complexity: O(1)
	 * 
	 */
	public int findDistance(String a, String b) {
		int size = text.length;
		int distance = -1; //distance between words to be sent as output when they are not present in the given text
		
		/* When both words are same then distance should be zero*/
		if(a == b) {
			return 0; 
		}
		for(int i = 0; i < size; i++) {
			if(text[i] == a || text[i] == b) {
				 boolean temp = text[i] == a;
				 b = temp ? b : a; //Search for b if a is found or search for a
				 distance++; //reset distance from -1 to 0
				 
				 while(i < (size - 1) && text[i] != b) {
					 i++;
					 distance++;
					 if(text[i] == b) {
						 return distance;
					 }					 
				 }
			}
		}
		
		return distance;
	}
	
	public static void main(String[] args) {
		
		String[] a = new String[] {"the", "quick", "brown", "fox", "quick"};
		WordDistanceFinder distanceFinder = new WordDistanceFinder(a);
		
		System.out.println("Distance between words is: " + distanceFinder.findDistance("fox", "the"));
		System.out.println("Distance between words is: " + distanceFinder.findDistance("quick", "fox"));
		System.out.println("Distance between words is: " + distanceFinder.findDistance("brown", "brown"));
		System.out.println("Distance between words is: " + distanceFinder.findDistance("apple", "mango"));

	}

}
