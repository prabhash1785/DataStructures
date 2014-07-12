/*
 * This class implements the Run Length Encoding on a given string.
 * Input: aabcccccaaa
 * Output: a2b1c5a3
 * 
 */
package com.prabhash.java.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunLengthEncoding {

	/**
	 * Using Run Length Encoding, repeated characters are compressed together by suffixing with their count.
	 * If no change in compressed text, then same input is returned.
	 * @param input
	 * @return output
	 * Complexity:
	 * Time: O(n)
	 * Space: O(1)
	 * 
	 */
	public String encodeString(String input) {
		if(input == null) {
			return null;
		}
		
		boolean flag = false; //this flag will be set to true if at least one of the characters is compressed
		
		StringBuilder output =  new StringBuilder();
		for(int i = 0; i < input.length(); i++) {
			int count = 1;
			while((i + 1 < input.length()) && (input.charAt(i+1) == input.charAt(i))) {
				count++;
				i++;
				flag = true;
			}
			output.append(input.charAt(i)).append(count);
			
		}
		
		if(flag) {
			return output.toString();
		} else {
			return input;
		}
		
	}
	
	/**
	 * This method will decode the given compressed string.
	 * @param args
	 * @return output
	 * For eg: Input = a5b2c3f2
	 * Output: aaaaabbcccff
	 */
	public String decompressString(String input) {
		if(input == null) {
			return null;
		}
		
		StringBuffer buffer = new StringBuffer();
		Pattern pattern = Pattern.compile("[a-zA-Z]|[0-9]+");
		System.out.println("Pattern object is " + pattern);
		Matcher matcher = pattern.matcher(input);
		System.out.println("Matcher object is " + matcher);
		while(matcher.find()) {
			String ch = matcher.group();
			System.out.println("Group is " + ch);
	        			
			matcher.find();
			int number = Integer.parseInt(matcher.group());
            System.out.println("Group is " + matcher.group() + " Number is " + number);
           
            while (number-- != 0) {
            	buffer.append(ch);
            }
		}
		
		return buffer.toString();
	}
	
	public static void main(String[] args) {
		
		RunLengthEncoding encoding = new RunLengthEncoding();
		String text = "abcc";
		String output = encoding.encodeString(text);
		System.out.println("Compressed String is: " + output);
		
		String encodedString = "a5b2c3f2";
		String decodedText = encoding.decompressString(encodedString);
		System.out.println("Original string is: " + decodedText);

	}

}
