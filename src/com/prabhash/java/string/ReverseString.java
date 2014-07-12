package com.prabhash.java.string;

public class ReverseString {

	public String reverse(String s) {
	    
		StringBuilder output = new StringBuilder();
		
		if(s == null) {
	    	return null;
	    } else {
	        String[] token = s.split("\\s+"); //Using regex for multiple spaces
	        int n = token.length;
	        output.append(token[n - 1]);
	        for(int i = n - 2; i >= 0; i--) {
	            output.append(" ").append(token[i]);	        
	        }	        	        
	    }		
		
		return output.toString();
	        
	}
	
	public static void main(String[] args) {

		String input = "the              sky                    is         blue";
		
		System.out.println(new ReverseString().reverse(input));

	}

}
