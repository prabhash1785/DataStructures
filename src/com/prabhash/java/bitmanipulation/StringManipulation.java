/**
 *  Implement a class that does string manipulation by overloading the following operators: <<, >>, = and ==. 
 *  For example consider the following code:
 *  StrShift example; 
 *  example = “Microsoft”; 
 *  printf(“\”example << 2\” results in %s\n“, example << 2); 
 *  In the above code the output would be “ftMicroso” which shows the last two characters of 
 *  the string “Microsoft” rotated to the left of the string. Please note that state is maintained 
 *  so two calls of example << 1 would give the same end result as calling example << 2 once.
 * @company: Microsoft
 * 
 */

package com.prabhash.java.bitmanipulation;

public class StringManipulation {
	
	/*
	 * Implement << operation on given string.
	 * For <<, >>, it works like the following:
	 * <<X
	 * 1.Reverse the whole string first.
	 * 2.Reverse the first X substr.
	 * 3.Reverse the last length-X substr.
	 * For example: 
	 * Microsoft << 2: 
	 * 1. tfosorciM 
	 * 2. First 2 chars, ftosorciM
	 * 3. Rest substr, ftMicroso
	 * 
	 * The >>X operation works the same way except the reverse the Length-X chars first and than X chars.
	 * 
	 */
	public static String shiftLeft(String input, int radix) {
		
		String output = new StringBuilder(input).reverse().toString();
		String part1 = new StringBuilder(output.substring(0, radix)).reverse().toString();
		String part2 = new StringBuilder(output.substring(radix, input.length())).reverse().toString();
		output = part1 + part2;		
		return output;	
	}
	
	/*
	 * Implementation of BitShift Right operation >> 
	 * >> X
	 * 1.Reverse the whole string first.
	 * 2.Reverse the first (length - X) substr.
	 * 3.Reverse the last X substr.
	 * For example: 
	 * Microsoft >> 2: 
	 * 1. tfosorciM 
	 * 2. First (length - 2) chars, crosoftiM
	 * 3. Rest substr, crosoftMi
	 * 
	 */
	public static String shiftRight(String input, int radix) {
			
		int size = input.length();
		String output = new StringBuilder(input).reverse().toString();
		String part1 = new StringBuilder(output.substring(0, size - radix)).reverse().toString();
		String part2 = new StringBuilder(output.substring(size - radix, size)).reverse().toString();
		output = part1 + part2;	
		return output;	
	}
	

	public static void main(String[] args) {
		String name = "Microsoft";
		System.out.println("Input Strint is: " + name);
		System.out.println("Output String for << 2: " + shiftLeft(name, 2));
		
		System.out.println("Output String for << 3: " + shiftLeft(name, 3));
		
		String output2 = shiftLeft(name, 1);
		System.out.println("Output of calling <<1 thrice: " + shiftLeft(shiftLeft(output2, 1), 1));
		
		System.out.println("Output of right bitshift >> 2 " + shiftRight(name, 2));
		
		System.out.println("Output of right bitshift calling twice >> 1 : "+ shiftRight(shiftRight(name, 1), 1));

	}

}
