/**
 *  Write a method which will accept a string and return true if the string is a palindrome and false if it isn't. 
 *  Special conditions: 
 *  a) your method should consider lower case and upper case characters to be the same. 
 *  b) your method should ignore special characters and white spaces, for e.g. if your input were the strings were "Madam, I'm Adam!!", then you should consider it a palindrome and hence return true ignoring case and special characters. Same with inputs like "Ma'am", "boB" etc should return true.
 *
 */
package com.prabhash.java.string;

public class Palindrome {
	
	private static final String[] text = {"Madam", "Madam, I'm Adam!!", "I love Alaska!!!"};
	
	public static boolean checkPalindrome(String s) {
		boolean flag = true;
		
		System.out.println("Before order change => " + s);
		String temp = s.toLowerCase();
		System.out.println("After order change => " + temp);
		
		int i = 0;
		int j = temp.length() - 1;
		
		while(i <= j) {
			if(temp.charAt(i) != temp.charAt(j)) {
				return false;
			} else {
				i++;
				j--;
			}
				
		}
				
		
		return flag;
	}
	
	/*
	 * Utility method to remove special characters from string
	 */
	private static String eliminateSpclCharacters(String str) {
		StringBuilder simpleString = new StringBuilder();
		
		for(int i = 0; i < str.length(); i++) {
			if(((str.charAt(i) >= 'A') && (str.charAt(i) <= 'Z')) || ((str.charAt(i) >= 'a') && (str.charAt(i) <= 'z'))) {
				simpleString.append(str.charAt(i));
			}
		}
		
		System.out.println("String without special characters: " + simpleString);
		return simpleString.toString();
	}

	public static void main(String[] args) {
		
		for(String input : text) {
			//remove special characters from string
			String temp = eliminateSpclCharacters(input);
			if(checkPalindrome(temp)) {
				System.out.println(temp + " It's a palindrome..\n");
			} else {
				System.out.println(temp + " Not a palindorme..\n");
			}
		}

	}

}
