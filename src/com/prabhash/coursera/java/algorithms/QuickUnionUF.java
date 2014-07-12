package com.prabhash.coursera.java.algorithms;

/*
 * Dynamic Connectivity Problem using Quick Union Algorithm
 * Time Complexity of n union operations is: O(n ^ 2) [Hint: each union will take O(n) time]
 * Drawbacks of Quick Union:
 * 	- Trees can get too tall
 * 	- Find operation could be expensive (could be n array accesses)
 */
public class QuickUnionUF {

	private int[] id;
	
	public QuickUnionUF(int n) {
		id = new int[n];
		for(int i = 0; i < n; i ++)
			id[i] = i;
	}
	
	private int root(int i) {
		while(i != id[i])
			id[i] = i;
		return i;
	}
	
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		id[i] = j;
	}
	
	public static void main(String[] args) {

		QuickUnionUF quickUnion = new QuickUnionUF(10);
		
		System.out.println("Array before union operations...");
		for(int k = 0; k < quickUnion.id.length; k++)
			System.out.print(quickUnion.id[k] + " ");		

		quickUnion.union(9, 0);
		quickUnion.union(8, 1);
		quickUnion.union(1, 2);
		quickUnion.union(7, 5);
		quickUnion.union(9, 2);
		quickUnion.union(6, 3);
		
		System.out.println("\nHere is the array after union operation: ");
		for(int k = 0; k < quickUnion.id.length; k++)
			System.out.print(quickUnion.id[k] + " ");
		
		System.out.println("\nIs 9 connected to 8: " + quickUnion.connected(9, 8));
		System.out.println("Is 9 connected to 3: " + quickUnion.connected(9, 3));

	}

}
