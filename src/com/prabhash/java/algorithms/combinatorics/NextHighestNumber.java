package com.prabhash.java.algorithms.combinatorics;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a number, find the next higher number which has the exact same set of digits as the original number. 
 * For example: given 38276 return 38627
 * 
 * @author Prabhash Rathore
 */
public class NextHighestNumber {
	
	public int findNextBiggestNumber(int input  )   {
	    //take 1358642 as input for example.
	    //Step 1: split the whole number to a list for individual digital   1358642->[2,4,6,8,5,3,1]
	    // this step is O(n)
	    int digitalLevel=input;

	    List<Integer> orgNumbersList=new ArrayList<Integer>()   ;

	    do {
	        Integer nInt = new Integer(digitalLevel % 10);
	        orgNumbersList.add(nInt);

	        digitalLevel=(int) (digitalLevel/10  )  ;


	    } while( digitalLevel >0)    ;
	    int len= orgNumbersList.size();
	    int [] orgNumbers=new int[len]  ;
	    for(int i=0;i<len;i++){
	        orgNumbers[i ]  =  orgNumbersList.get(i).intValue();
	    }
	    //step 2 find the first digital less than the digital right to it
	    // this step is O(n)


	    int firstLessPointer=1;
	    while(firstLessPointer<len&&(orgNumbers[firstLessPointer]>orgNumbers[ firstLessPointer-1 ])){
	        firstLessPointer++;
	    }
	     if(firstLessPointer>=len-1&&orgNumbers[len-1]>=orgNumbers[len-2]){
	         //all number is in sorted order like 4321, no answer for it, return original
	         return input;
	     }

	    //when step 2 step finished, firstLessPointer  pointing to number 5

	     //step 3 fristLessPointer found, need to find  to  first number less than it  from low digital in the number
	    //This step is O(n)
	    int justBiggerPointer=  0 ;

	    while(justBiggerPointer<firstLessPointer&& orgNumbers[justBiggerPointer]<orgNumbers[firstLessPointer]){
	        justBiggerPointer++;
	    }
	    //when step 3 finished, justBiggerPointer  pointing to 6

	    //step 4 swap the elements  of justBiggerPointer and firstLessPointer .
	    // This  is O(1) operation   for swap

	   int tmp=  orgNumbers[firstLessPointer] ;

	    orgNumbers[firstLessPointer]=  orgNumbers[justBiggerPointer]  ;
	     orgNumbers[justBiggerPointer]=tmp ;


	     // when step 4 finished, the list looks like        [2,4,5,8,6,3,1]    the digital in the list before
	     // firstLessPointer is already sorted in our previous operation
	     // we can return result from this list  but  in a differrent way
	    int result=0;
	    int i=0;
	    int lowPointer=firstLessPointer;
	    //the following pick number from list from  the position just before firstLessPointer, here is 8 -> 5 -> 4 -> 2
	    //This Operation is O(n)
	    while(lowPointer>0)        {
	        result+= orgNumbers[--lowPointer]* Math.pow(10,i);
	        i++;
	    }
	    //the following pick number from list   from position firstLessPointer
	    //This Operation is O(n)
	    while(firstLessPointer<len)        {
	        result+= orgNumbers[firstLessPointer++ ]* Math.pow(10,i);
	        i++;
	    }
	     return  result;

	}
	
	/*
	 * find the next higher number containing the same set of digits.
	 * 
	 */
	public static int findNextBiggerNumber(int num) {
		
		if(num < 12) {
			return num;
		}
		
		int output = 0;
	
		List<Integer> list = new ArrayList<Integer>();
		
		int tempNum = num;
		
		while(tempNum > 0) {
			list.add(tempNum % 10);
			
			tempNum = tempNum / 10;
		}
		
		int[] array = new int[list.size()]; //stores the original number in form of array
		
		for(int i = list.size() - 1, j = 0; i >= 0; i--, j++) {
			array[j] = list.get(i);
		}
		
		for(int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
		
		int loc = array.length - 1;
		
		int k = array.length - 1;
		
		while(k > 0 && array[k - 1] > array[k]) {
			loc--;
			k--;
		}
		
		//if the number is already in descending order, return the original number
		if(loc == 0) {
			return  num;
		}
		
		int xPos = loc - 1;
		int x = array[xPos]; //number to be placed in right side portion of array
		
		//find the smallest digit larger than x to the right
		int digitJustBiggerThanX = array[loc];
		int digitJustBiggerThanXPos = loc;
		int diff = array[loc] - x;
		
		for(int m = loc + 1; m < array.length; m++) {
			int subtraction = array[m] - x;
			
			if(subtraction > 0 && diff > subtraction) {
				diff = subtraction;
				digitJustBiggerThanX = array[m];
				digitJustBiggerThanXPos = m;
			}
		}
		
		//swap the positions of x and digit just greater than x
		int temp = x;
		array[xPos] = array[digitJustBiggerThanXPos];
		array[digitJustBiggerThanXPos] = temp;
		
		//TODO: Sort the digits to the right of digitJustBiggerThanX
		
		//create the final number from array
		for(int i = array.length - 1, j = 0; i >= 0; i--, j++) {
			output += array[i] * Math.pow(10, j);
		}
			
		return output;
	
	}


	public static void main(String[] args) {
		
		NextHighestNumber nextHighestNumber = new NextHighestNumber();
		
//		System.out.println(nextHighestNumber.findNextBiggestNumber(47));
//		
//		System.out.println(nextHighestNumber.findNextBiggestNumber(67));
//		
//		System.out.println(nextHighestNumber.findNextBiggestNumber(123));
//		
//		System.out.println(nextHighestNumber.findNextBiggestNumber(87)); //TODO - fix array out of bound exception
		
		//int result = findNextBiggerNumber(87); //87
		int result = findNextBiggerNumber(38276); //38672
		System.out.println("Result is: " + result);

	}

}
