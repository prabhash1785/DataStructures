/*
 * Buy and Sell a stock in such a way to maximize the profit
 * @Twitter Interview
 * @author Prabhash Rathore
 */
package com.prabhash.java.algorithms.general;

/**
 * Gives a time-based array of Stock Prices. Buy and Sell a stock in such a way to maximize the profit.
 * Selling price must be after the buying price.
 * @author Prabhash Rathore
 *
 */
public class BuySellStock {

	/**
	 * Brute Force Algorithm - Compare every element with each other in the given array to find the max diff
	 * Time Complexity - O(n ^ 2)
	 * Space Complexity - O(1)
	 */
	public static int findMaxProfitBruteForce(int[] price) {
		if(price == null) {
			return -1;
		}
		
		int size = price.length;
		
		if(size < 2) {
			return 0;
		}
		
		int maxDiff = 0;
		int buy = 0;
		int sell = 0;
		
		for(int i = 0; i < size - 1; i++) {
			for(int j = i+1; j < size; j++) {
				int diff = price[j] - price[i];
				//System.out.println("buy: " + price[i] + " sell: " + price[j] + " Diff: " + diff + " maxDiff: " + maxDiff);
				if(maxDiff < diff) {
					buy = i;
					sell = j;
					maxDiff = diff;
				}
			}
		}
		
		System.out.println("\nBrute Force: Sell = " + price[sell] + " Buy = " + price[buy]);
		return maxDiff;
	}
	
	/**
	 * This method will calculate the maximum profit based on the given stock prices. This is the correct way of calculating maximum profit
	 * for the given stock prices from a given time based array.
	 * Pseudocode:
	 * 	- While scanning all the elements in the array
	 * 		- keep track of minimum element
	 * 		- Subtract minimum from all the subsequent elements and keep track of maximum difference, that would be max profit
	 * 	 
	 * Time Complexity = O(n)
	 * Space Complexity = O(1)
	 */
	public static int findMaxProfit(int[] stockPrice) {
		
		if(stockPrice == null) {
			return -1;
		}
		
		int size = stockPrice.length;
		
		/* If there is just one stock price, return 0 */
		if(size < 2) {
			return 0;
		}
		 
		int minIndex = 0; //min index
		int maxDiff = 0;
		int buyPriceIndex = 0; //Minimum Price Index to buy the Stock
		int sellPriceIndex = 0; //Highest Price Index after Minimum Price Index to sell the stock for HIGHEST Profit
		
		for(int i = 0; i < size; i++) {
			if(stockPrice[i] < stockPrice[minIndex]) {
				minIndex = i;
			}
			int diff = stockPrice[i] - stockPrice[minIndex];
			if(diff > maxDiff) {
				buyPriceIndex = minIndex;
				sellPriceIndex = i;
				maxDiff = diff;
			}
		}
		
		System.out.println("\nMax = " + stockPrice[sellPriceIndex] + " :: Min = " + stockPrice[buyPriceIndex]);
		return maxDiff;
	}
	
	public static void main(String[] args) {
		
		int[] price = new int[] {10, 1000, 12000, 7, 56, 31, 60, 45};
				
		System.out.println("Brute Force Algo - Max profit is: " + findMaxProfitBruteForce(price));
		
		//Calling the second method to get the highest profit on stock sale
		System.out.println("Linear Algo - Max profit is: " + findMaxProfit(price));

	}

}
