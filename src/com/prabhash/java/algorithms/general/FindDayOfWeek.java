/**
 * Netflix Interview Question
 * 
 * Use whatever language you are comfortable with (pseudocode even). Assume datastructures and libraries exist 
 * and make up their api as needed: No need to check javadoc.
 * Write a business_days_from_now() method, which takes as an input a number of business days, and returns a Date object which is that many business days 
 * from now. For this, a business day is only a weekday and not a weekend. 
 * 
 * example:
 * 
 * Today is Wednesday the 8th.
 * b_d_f_n(5)
 *
 * => Wednesday the 15th
 *
 * b_d_f_n(3)
 *
 * => Monday the 13th
 * 
 * Solution: To calculate the business day and date from a given current date, we have to keep two things in our calculations:
 * 	- Weekends - Saturday and Sunday, can be determined through Calendar class.
 *  - Holidays - Can be pulled from an existing API or a sample list can be implemented using a hashmap in Calendar class
 *  
 *  So for a given number of business days, we will iterate through the for loop and in each iteration, we will do the following:
 *  	- Add 1 to futureDate and futureDay
 *      - If futureDay is Saturday or Sunday, add 1 to the futureDate.
 *      - If futureDay is holiday, then add 1 to futureDate and futureDay both
 *  Finally, do the following to get final value of days and dates
 *  	- finalDate = finalDate % 31 (let's assume every month has 31 days)
 *      - finalDay = Calendar.get(finalDay % 5) -> This will print the final Business Day name 
 * 
 * 
 * @author Prabhash Rathore
 * @date Jan 8, 2013
 * 
 */

package com.prabhash.java.algorithms.general;

import java.util.HashMap;
import java.util.Map;

/*
 * Calculating day by adding business day to current day value and doing the % 5 to get the final business day. Doesn't take holidays into account.
 * For date, first calculating number of weeks involved in business days by dividing it by 5 and then multiply the result with 2 to get total number of weekend days
 * Then finalDate = (currentDate + numberoFWeekendDays + numberOfBusinessDays) % 30 -> Assuming every month has 30 days.
 * Note: This implementation doesn't take Holidays into account
 * 
 */
public class FindDayOfWeek {
	
	private Calendar currentDate = new Calendar(0, 5); // Setting the current day and date through constructor
	Calendar futureCalendarDate = new Calendar();
	
	/*
	 * Backtrack the current day to immediate last Monday and then find number of weekends days from Monday to the last day of business date range
	 * To calculate final date, add current date, differential from current date to Monday, number of business days and weekends and take mod of 31
	 * For Business days, just add number of business day and take mod of 5
	 * @parameter integer
	 * @return Calendar
	 *  
	*/
	public Calendar findFutureBusinessDay(int n) {
		
		int curDay = currentDate.getDay();		
		int curDate = currentDate.getDate();
		System.out.println("Current Day: " + currentDate.dayMap.get(curDay) + " the " + curDate);
		System.out.println("Number of business days: " + n);
		
		int futureDay = -1;
		int futureDate = -1;
		
		// handling boundary conditions
		if(n < 0) {
			System.out.println("Invalid number of business days provided, please provide a valid business day..");
			futureCalendarDate.setDay(futureDay);
			futureCalendarDate.setDate(futureDate);
			return futureCalendarDate;
		} else if(n == 0) {
			futureDay = curDay;
			futureDate = curDate;
			futureCalendarDate.setDay(futureDay);
			futureCalendarDate.setDate(futureDate);
			return futureCalendarDate;
						
		}
		
		
		if(curDay > 0 && curDay < 6) { // this will take care of condition when current day is a weekend day
			futureDay = curDay + n;
		} else {
			futureDay = n;
		}
		
		futureDay = futureDay % 5;
		if(futureDay == 0) {
			futureDay = 5; // if futureDay is zero => this implies it's a Friday so setting this to 5 as per my hash table mapping..
		}
		
		//difference between the hash map value of Monday and any other day, done to reset the cuurent day to May for calculation of number of weekends
		int bufferDaysToMonday =  curDay - 1; 
		int counter = bufferDaysToMonday + n; //adjusting counter to account for moving the current day to Monday
		
		futureDate = curDate - bufferDaysToMonday; //moving the future date back to the immediate Monday in that week
		
		// Calculating the number of weekend days falling in the given business days starting from Monday 
		int numberOfWeekendDays = (counter/5) * 2;
				
		futureDate = futureDate + counter + numberOfWeekendDays;
		
		//condition to handle when current date is a Saturday
		if(curDay == 6) { 
			futureDate = futureDate - 1; // if current day is Saturday then subtract by 1 to account for only one weekend day for the current weekend
		} 
					
		// assuming every month has 31 days
		futureDate = futureDate % 31;
				
		futureCalendarDate.setDay(futureDay);
		futureCalendarDate.setDate(futureDate);
		
		return futureCalendarDate;
		
	}
	
	public static void main(String[] args) {
		
		FindDayOfWeek findDayOfWeek2 = new FindDayOfWeek();
		System.out.println("\nFUTURE DATE IS: " + findDayOfWeek2.findFutureBusinessDay(27));
						
	}

	
	// Inner Class which represents a Date Object
	private class Calendar {
		private int day;
		private int date;
		private int month;
		private int year;
		private Map<Integer, String> dayMap = new HashMap<Integer, String>();
		
		public Calendar() {
			setDayMap();
		}
		
		public Calendar(int day, int date) {
			this.day = day;
			this.date = date;
			setDayMap();
		}
		
		// Setting the Day name mappings in the Hashmap
		private void setDayMap() {
			dayMap.put(1, "Monday");
			dayMap.put(2, "Tuesday");
			dayMap.put(3, "Wednesday");
			dayMap.put(4, "Thursday");
			dayMap.put(5, "Friday");
			dayMap.put(6, "Saturday");
			dayMap.put(0, "Sunday");
			dayMap.put(-1, "Garbage");
		}
		
		/**
		 * @return the day
		 */
		public int getDay() {
			return day;
		}
		/**
		 * @param day the day to set
		 */
		public void setDay(int day) {
			this.day = day;
		}
		/**
		 * @return the date
		 */
		public int getDate() {
			return date;
		}
		/**
		 * @param date the date to set
		 */
		public void setDate(int date) {
			this.date = date;
		}
		/**
		 * @return the month
		 */
		public int getMonth() {
			return month;
		}
		/**
		 * @param month the month to set
		 */
		public void setMonth(int month) {
			this.month = month;
		}
		/**
		 * @return the year
		 */
		public int getYear() {
			return year;
		}
		/**
		 * @param year the year to set
		 */
		public void setYear(int year) {
			this.year = year;
		}
		
		@Override
		public String toString() {
			return dayMap.get(getDay()) + " the " + getDate(); 
		}
		
	}

}
