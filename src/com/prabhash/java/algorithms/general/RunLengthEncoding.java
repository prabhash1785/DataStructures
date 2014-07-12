package com.prabhash.java.algorithms.general;

/**
 * 
 * This class is used to encode a given string using Run Length Encoding technique
 * @author Prabhash Rathore
 *
 */

public class RunLengthEncoding {

	private static String s = "WWWWWWWWWWWWBWWWWWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWBWWWWWWWWWWWWWW";
	
	public static void main(String[] args) {
		
		System.out.println("Size of string is " + s.length());
		StringBuilder encryptedString = new StringBuilder();
		for(int i=0;i<s.length();i++) {
			//System.out.println(s.charAt(i));
			int counter = 1;
			while((i+1 < s.length()) && (s.charAt(i) == s.charAt(i+1))) {
				counter++;
				i++;
			}
			System.out.println("i = " + i + ", counter = " + counter);
			/*if(counter > 1) {
				encryptedString.append(counter).append(s.charAt(i));
				System.out.println("Encypted String--> " + encryptedString);
			} else {s
				encryptedString.append(s.charAt(i));
				System.out.println("Encypted String--> " + encryptedString);
			}*/
			encryptedString.append(counter).append(s.charAt(i));
			System.out.println("Encypted String--> " + encryptedString);
		}
		
	}

}
