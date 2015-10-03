package com.prabhash.java.bitmanipulation;

public class BitwiseComputation {
	
	int f( int v)
	{
	        int t = 0; int i;
	        for (i = 31; i !=0 ; i--) {
	                t |= v & 1;
	                t <<= 1; // same as multiplying by 2
	                v >>= 1; // same as dividing by 2
	        }
	        t |= v & 1;
	        return t;
	}

	public static void main(String[] args) {
		
		int v = 10;
		int output = new BitwiseComputation().f(v);
		
		System.out.println("Output: " + output); // output: 1342177280

	}

}
