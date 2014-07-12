/**
 * 
 * You have a single string which contains all the positive numbers upto N concatenated together. If you are given an input number then how would you find
 *  the index position of the number in the string. 
 *  Eg:
 *  String str = "12345678910111213141516171819202122232425......upto 10000";
 *  input = 20 should return the index of 20 in the string which is 29
 *  The example string is upto 10000. The actual string can be upto any number N.
 *  Company: Amazon
 *  
 */

package com.prabhash.java.string;

public class FindNumberInString {

	private StringBuilder input;
	
	public FindNumberInString(int n) {
		input = new StringBuilder();
		for(int i = 1; i <= n; i++) {
			input.append(i);
		}
		System.out.println("Input String: " + input);
	}
	
	/*
	 * This method uses Brute Force String comparison to find the position of a number in a given string.
	 * Time Complexity: O(mn)
	 * 
	 */
	public int findPosition(int num) {
		
		String s = new Integer(num).toString();
		System.out.println("After converting input number to string: " + s);
		
		int i = 0, j = 0;
		int m = s.length(), n = input.length();
		
		for(i = 0; i <= n - m; i++) {
			j = 0;
			while(j < m && input.charAt(i + j) == s.charAt(j)) {
				j++;
			}
			if(j == m) {
				return i;
			}
		}		
		
		return -1;
	}
	
	public static void main(String[] args) {
		
		int inputNum = 200; //higher limit to form the input string 
		FindNumberInString findNumber = new FindNumberInString(inputNum);
		
		int num = 133; //number to be found
		
		long startTime = System.nanoTime();
		int position = findNumber.findPosition(num);
		long endTime = System.nanoTime();
		
		if(position >= 0) {
			System.out.println("Position of " + num + " is " + position);
		} else {
			System.out.println(num + " doesn't exist.");
		}
		
		System.out.println("Time taken for search is: " + (endTime - startTime));

	}

}
