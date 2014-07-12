package com.prabhash.java.algorithms.general;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunLengthDecoding {

	public static StringBuilder decode(String input) {
		System.out.println("Input String is " + input);
		StringBuilder decodedString = new StringBuilder();
		Pattern pattern = Pattern.compile("[0-9]+|[a-zA-Z]");
		System.out.println("Pattern object is " + pattern);
		Matcher matcher = pattern.matcher(input);
		System.out.println("Matcher object is " + matcher);
		while (matcher.find()) {
            int number = Integer.parseInt(matcher.group());
            System.out.println("Group is " + matcher.group() + " Number is " + number);
            matcher.find();
            System.out.println("Group is " + matcher.group());
            while (number-- != 0) {
            	decodedString.append(matcher.group());
            }
        }
        return decodedString;
	}
	
	public static void main(String[] args) {
		String source = "2W10X1L";
		System.out.println("Here is decoded string: " + decode(source));
	}

}
