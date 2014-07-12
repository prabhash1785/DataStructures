/**
 * Groupon Interview Question
 * 
 * You are given a matrix. Starting from [0, 0], you have to move over the matrix in clockwise-spiral direction, 
 * i.e., we start from [0, 0], move upto [0, 4], and then move to [3, 4], then move to [3, 0], then move to [1, 0], 
 * then to [1, 3] and so on. 
 *
 *   Move in this way and print all the elements.
 *
 *
 * Input :
 * 1 2 3 4 5
 * 6 8 9 a b
 * c d e  f g
 * h  i  j  k l
 *
 * Output :
 * 1 2 3 4 5 b g l k j i h c 6 8 9 a f e d
 * 
 */

package com.prabhash.java.algorithms.general;

/*
 * This program is written to print the spiral of a given rectangular matrix in clockwise manner.
 * This is done by maintaining two pointer for rows and two pointers for columns starting from top to bottom for rows and 
 * left to right for columns.
 * The Big-Oh of this program is O(n ^ 2)
 * 
 * @author Prabhash Rathore
 * @date Jan 25, 2014
 * 
 */

public class SpiralMatrix {

	private static final char[][] a = {{'1', '2', '3', '4', '5'}, {'6', '8', '9', 'a', 'b'}, {'c', 'd', 'e', 'f', 'g'}, {'h', 'i', 'j', 'k', 'l'}};
	
	public static void printSpriralMatrix(char[][] matrix, int rowLength, int colLength) {
		
		int topRowPointer = 0; // top pointer to row position
		int leftColPointer = 0; // left pointer to column position
		
		int bottomRowPointer = rowLength - 1; // bottom pointer to row position 
		int rightColPointer = colLength - 1; // right pointer to column position
		
		System.out.println("Here is the Spiral Matrix: ");
		
		while((topRowPointer < rowLength/2) && (leftColPointer < colLength/2)) {
			
			// loop variables
			int i = 0;
			int j = 0;
			
			for(i = topRowPointer, j = leftColPointer; j < rightColPointer; j++)
				System.out.println(matrix[i][j]);
			
			for(i = topRowPointer, j = rightColPointer; i < bottomRowPointer; i++)
				System.out.println(matrix[i][j]);
			
			for(i = bottomRowPointer, j = rightColPointer; j > leftColPointer; j--)
				System.out.println(matrix[i][j]);
			
			for(i = bottomRowPointer, j = leftColPointer; i > topRowPointer; i--)
				System.out.println(matrix[i][j]);
			
			
			topRowPointer++;
			leftColPointer++;
			bottomRowPointer--;
			rightColPointer--;			
			
		}
		
	}
	
	public static void main(String[] args) {
		
		int rowSize = a.length;
		System.out.println("Row size is: " + rowSize);
		
		int colSize = a[0].length; //assuming given matrix is a rectangular matrix
		System.out.println("Column size is: " + colSize);
		
		// print the input matrix
		System.out.println("Here are the elements in 2D Matrix:");
		for(int i = 0; i < rowSize; i++) {
			for(int j = 0; j < colSize; j++)
				System.out.println(a[i][j]);
		}
		
		printSpriralMatrix(a, rowSize, colSize);

	}

}
