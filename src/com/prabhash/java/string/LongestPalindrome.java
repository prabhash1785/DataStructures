/**
 * Imagine we have a large string like this "ABCBAHELLOHOWRACECARAREYOUIAMAIDOINGGOOD" which contains multiple
 * palindromes within it, like ABCBA, RACECAR, ARA, IAMAI etc... Now write a method which will accept this large 
 * string and return the largest palindrome from this string. If there are two palindromes which are of same size, 
 * it would be sufficient to just return any one of them.
 * Company: Microsoft
 * Ref: http://www.programcreek.com/2013/12/leetcode-solution-of-longest-palindromic-substring-java/
 *  
 */
package com.prabhash.java.string;

import java.util.LinkedList;
import java.util.List;

public class LongestPalindrome {

	public static void main(String[] args) {
		String str = "ABCBAHELLOHOWRACECARAREYOUIAMAIDOINGGOOD";
		System.out.println("Longest Palindrome: "+longestPalindrome(str));
		
		//Going to print all the palindromes
		System.out.println("Here are all the palindromes in given text: ");
		List<String> allPalindromes = getAllPalindromes(str);
		for(String s : allPalindromes) {
			System.out.println(s);
		}
	}
	
	/* 
	 * Analysis of Time and Space:
	 * Time = O(n * (n/2 + n/2)) = O(n * n) = O(n ^ 2)
	 * Space = O(1)
	 * 
	 */
	public static String longestPalindrome(String str){
	
		String longestPalindrome = null;
		if(str == null)
			return null;
		else{
			longestPalindrome=str.substring(0,1);
			for(int i=0;i<str.length()-1;i++){
				String palindrome=expand(str,i,i);
				if(palindrome.length() > 1)
					System.out.println(palindrome);
				if(palindrome.length() > longestPalindrome.length()){
					longestPalindrome = palindrome;
				}
				
				palindrome=expand(str,i,i+1);
				if(palindrome.length() > 1)
					System.out.println(palindrome);
				if(palindrome.length() > longestPalindrome.length()){
					longestPalindrome = palindrome;
				}
			}
		}
		return longestPalindrome;
		
	}
	
	/**
	 * Utility Method to find the longest palindrome
	 */
	private static String expand(String str, int left, int right) {
		if(left > right)
			return null;
		else{
			while(left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
				right++;
				left--;
			}
		}
		
		return str.substring(left+1,right);
		
	}
	
	/*
	 * This utility method will print all the palindromes in the given string.
	 * 
	 */
	public static List<String> getAllPalindromes(String input) {
		List<String> list = new LinkedList<String>();
		if(input == null) {
			return null;
		} else {
			for(int i = 0; i < input.length(); i++) {
				String temp = expand(input, i, i);
				if(temp.length() > 1) {
					list.add(temp);
				}
				
				temp = expand(input, i, i + 1);
				if(temp.length() > 1) {
					list.add(temp);
				}
				
			}
		}
		
		return list;
	}
	
}