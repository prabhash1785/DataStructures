package com.prabhash.coursera.java.algorithms;

/*
 * Dynamic Connectivity Problem using Quick Find Algorithm
 * Time Complexity of n union operations is: O(n ^ 2) [Hint: each union will take O(n) time]
 */
public class QuickFind {

	private int[] id;
	
	public QuickFind(int n) {
		id = new int[n];
		for(int i = 0; i < n; i++)
			id[i] = i;
	}
	
	public boolean connected(int p, int q) {
		return id[p] == id[q];
	}
	
	public void union(int p, int q) {
		int pid = id[p];
		int qid = id[q];
		for(int i = 0; i < id.length; i++) {
			if(id[i] == pid)
				id[i] = qid;
		}
	}
	
	
	public static void main(String[] args) {
		
		QuickFind quickFind = new QuickFind(10);
		
		System.out.println("Array before union operations...");
		for(int k = 0; k < quickFind.id.length; k++)
			System.out.print(quickFind.id[k] + " ");		

		quickFind.union(9, 0);
		quickFind.union(8, 1);
		quickFind.union(1, 2);
		quickFind.union(7, 5);
		quickFind.union(9, 2);
		quickFind.union(6, 3);
		
		System.out.println("\nHere is the array after union operation: ");
		for(int k = 0; k < quickFind.id.length; k++)
			System.out.print(quickFind.id[k] + " ");
		
		System.out.println("\nIs 9 connected to 8: " + quickFind.connected(9, 8));
		System.out.println("Is 9 connected to 3: " + quickFind.connected(9, 3));
		

	}

}
