package com.prabhash.java.algorithms.general;

import java.util.Arrays;

public class MergeArrays {

	private static int[] a = {2,56,4,87, 3, 76, 55, 101};
	private static int[] b = {21,6,4,34};
	
	public static void main(String[] args) {
		
		int[] c = new int[a.length + b.length];
		Arrays.sort(a);
		for(int i=0;i<a.length;i++) {
			System.out.println(a[i] + " ");
		}
		Arrays.sort(b);
		for(int i=0;i<b.length;i++) {
			System.out.println(b[i] + " ");
		}
		int i = 0;
		int j = 0;
		int k =0;
		//Merge Logic starts here
		while((i<a.length) && (j<b.length)) {
			if(a[i] <= b[j]) {
				c[k] = a[i];
				i++;
				k++;
			} else {
				c[k] = b[j];
				j++;
				k++;
			}			
		}
		while(i<a.length) {
				c[k] = a[i];
				i++;
				k++;
		}
		while(j<b.length) {
				c[k] = b[j];
				j++;
				k++;
		}		
		System.out.println("Length of final array c is " + c.length);
		System.out.println("Final Merged array is: \n");
		for(k=0;k<c.length;k++) {
			System.out.println(c[k] + " ");
		}
	}

}

