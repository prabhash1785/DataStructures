package com.prabhash.java.string;

/**
 * write a function that has an int as input and return the equivalent String as an output
 * 12 -> 'twelve'
 * 4345 -> 'four thousand three hundred and forty-five'
 * 7654567643 -> 'seven billion six hundred and fifty-four million five hundred and sixty-seven thousand six hundred and forty-three'
 * Ref: http://www.careercup.com/question?id=5768136489959424
 * @Twitter Interview
 * @author Prabhash Rathore
 *
 */
public class ConvertNumberToString {
	
	private static final String[] digit = new String[] {"zero", "one", "two", "three", "four", "five", "six", "seven"
		, "eight", "nine"};
	
	private static final String[] teen = new String[] {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
		"seventeen", "eihteen", "nineteen"};
	
	private static final String[] ty = new String[] {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty",
		"ninety"};
	
	// 0 < n <1000
	private static String read(int n) {
		String s = "";
		if(n > 99) {
			s = digit[n / 100];
			s += " hundred";
			n = n % 100;
			if(n > 0) {
				s += " and ";
			}
		}
		if(n == 0) {
			return s;
		} else if(n < 10) {
			s += digit[n];
		} else if(n < 20) {
			s += teen[n - 10];
		} else {
			s += ty[(n / 10) - 2];
			n = n % 10;
			if(n > 0) {
				s += "-";
				s += digit[n];
			}
		}
		
		return s;
		
	}
	
	// 0 <= num <= 4294967295
	private static String readUnsignedInt(int num) {
		if(num == 0) {
			return "zero";
		}
		
		String s = "";
		if(num > 999999999) {
			s += read(num / 1000000000);
			s += " billion";
			num = num % 1000000000;
			if(num > 0) {
				s += " ";
				s += readUnsignedInt(num); 
			}
		} else if(num > 999999) {
			s += read(num / 1000000);
			s += " million";
			num = num % 1000000;
			if(num > 0) {
				s += " ";
				s += readUnsignedInt(num);
			}
		} else if(num > 999) {
			s += read(num / 1000);
			s += " thousand";
			num = num % 1000;
			if(num > 0) {
				if(num > 100) {
					s += " ";
				} else {
					s += " and ";
				}
				s += readUnsignedInt(num);				
				
			}
		} else {
			s = read(num);
		}
		
		return s;
		
	}
	
	// -2147483648 <= num < 2147483647
	public static String readInt(int num) {
		if(num == 0) {
			return "zero";
		}
		
		String s = "";
		int n = num;
		if(num < 0) {
			s += "negative ";
			n = - (num); //Converting the number to positive
		}
		
		s += readUnsignedInt(n);
		return s;
	}
	
	public static void main(String[] args) {
		
		//int number = -1984500567;
		int number = -199634987;
		System.out.println(readInt(number));
		
	}

}
