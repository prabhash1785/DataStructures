package com.prabhash.java.algorithms.general;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BusinessDays {

	private static final long ONE_DAY = 1000L * 60 * 60 * 24;
	private static final SimpleDateFormat FORMAT = new SimpleDateFormat(
			"yyyy/MM/dd EEE");

	public static Date getDateXBusinessDaysAfterAGivenDate(int numberOfBizDays,
			Date refDate) {
		// days to first Monday
		int daysToFirstMonday = (8 - refDate.getDay()) % 7;
		// biz days to first Monday
		int bizDaysToFirstMonday = daysToFirstMonday <= 2 ? 0
				: daysToFirstMonday - 2;
		if (bizDaysToFirstMonday > numberOfBizDays) {
			// won't reach Monday, so just add delta
			return new Date(refDate.getTime() + numberOfBizDays * ONE_DAY);
		} else {
			// add weekends to every 5 biz days, and add weekend for the first week
			int totalNumberOfDays = numberOfBizDays
					+ (daysToFirstMonday - bizDaysToFirstMonday)
					+ (numberOfBizDays - bizDaysToFirstMonday) / 5 * 2;
			return new Date(refDate.getTime() + totalNumberOfDays * ONE_DAY);
		}

	}

	public static Date getDateXBusinessDaysAfterAGivenDate_naive(
			int numberOfBizDays, Date refDate) {
		Calendar c = new GregorianCalendar();
		c.setTime(refDate);
		for (int i = 0; i < numberOfBizDays; i++) {
			// skip weekends for free
			while (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
					|| c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				c.add(Calendar.DATE, 1);
			}
			c.add(Calendar.DATE, 1);
		}
		// last skip
		while (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
				|| c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			c.add(Calendar.DATE, 1);
		}
		return c.getTime();
	}

	public static void main(String[] args) {
		Date refDate = new Date();
		for (int i = 0; i < 7; i++) {
			refDate = new Date(refDate.getTime() + ONE_DAY);
			System.out.println("=======================");
			System.out.println("reference date: " + FORMAT.format(refDate));
			for (int j = 0; j < 15; j++) {
				Date naiveDate = getDateXBusinessDaysAfterAGivenDate_naive(j,
						refDate);
				Date calcDate = getDateXBusinessDaysAfterAGivenDate(j, refDate);
				if (!naiveDate.equals(calcDate)) {
					System.out.println("***** WRONG ********");
				}
				System.out.println("naive \t" + j + " day(s) after: "
						+ FORMAT.format(naiveDate));
				System.out.println("calc \t" + j + " day(s) after: "
						+ FORMAT.format(calcDate));
			}
		}
	}

}