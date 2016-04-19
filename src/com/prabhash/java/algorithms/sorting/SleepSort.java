package com.prabhash.java.algorithms.sorting;

public class SleepSort {
	
	/**
	 * Sleep Sort sorts an array by spawning new thread or process for each element in the array and then each thread
	 * sleeps for the value of that number in array and then that thread prints that number when it wakes up.
	 * 
	 * Since higher number in array will make Thread sleep for longer time so based on this principle, threads holding smallest number
	 * will print small number first and then threads holding higher number will print them later in time. This way
	 * eventually all numbers will be sorted.
	 * 
	 * @param a
	 */
	public static void sleepSort(final int[] a) {
		
		if(a == null) {
			throw new IllegalArgumentException();
		}
		
		for(int num : a) {
			Thread thread = new Thread(new MyThread(num));
			thread.start();
		}
	}
	
	public static class MyThread implements Runnable {
		
		private int number;
		
		public MyThread(int number) {
			this.number = number;
		}
		
		@Override
		public void run() {
			try {
				Thread.sleep(number);
				System.out.print(number + " ");
			} catch(InterruptedException exception) {
				exception.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		
		final int[] array = new int[] {5, 3, 10, 5000, 3000, 7, 20, 15};
		
		System.out.println("Original array:");
		for(int n : array) {
			System.out.print(n + " ");
		}
		
		System.out.println("\n\nSorted array is:");
		sleepSort(array);
	}

}
