package com.prabhash.java.algorithms.general;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * Groupon Interview Question
 * Create an API for in-memory cache and then this cache gets refreshed from a Data Source every 5 minutes.
 * @author Prabhash Rathore
 * @date Feb 8, 2014
 *
 */

public class InMemoryCache {

	private static final Map<Integer, Employee> dataSource = new Hashtable<Integer, Employee>(); //Persistent Data Storage
	private static Map<Integer, Employee> cache = new Hashtable<Integer, Employee>(); // cache object has to be static so different instances can't be created
		
	public Employee fetchFromCache(Integer id) {
		
		Employee employee = new Employee();
		
		if(cache != null) {
			
				if(cache.containsKey(id)) {
					employee = cache.get(id);
				} else {
					employee = null;
				}
		}
		
		return employee;		
		
	}
	
	//this method is synchronized in order to prevent data inconsistency from multi-thread accessing this method
	public synchronized void addToCache(Employee e) {
		
		if(e != null) {
			cache.put(e.getEmpID(), e);
		}
		
	}
	
	/*
	 * This method is used to refresh cache data from the DataSorce.
	 * This method will be run using a Daemon Thread and this daemon thread will run every 5 minutes.
	 * 	
	 */
	public synchronized static void refreshCache() {
		
		Set<Integer> keySet = cache.keySet();
		for(Integer i : keySet) {
			if(dataSource.containsKey(i)) {
				cache.put(i, dataSource.get(i));
			}
		}
		
	}
	
	/*
	 * Utility method to print data from cache
	 */
	public static void printFromCache() {
		
		Set<Integer> keys = cache.keySet();
		for(Integer j : keys) {
			System.out.println(cache.get(j));
		}
		
	}
	
	public static void createDataSource() {
		
		InMemoryCache memCache = new InMemoryCache();
		Employee e1 = memCache.new Employee();
		e1.setEmpID(1);
		e1.setName("Ricky Rathore");
		e1.setAdddress("Omaha, US");
		
		Employee e2 = memCache.new Employee(2, "Amber Green", "Chicago, US");
		
		Employee e3 = memCache.new Employee(3, "Laura Haberkamp", "New York, US");
		
		Employee e4 = memCache.new Employee(4, "Steven Green", "San Francisco, US");
		
		Employee e5 = memCache.new Employee(5, "Jimmy Green", "Phoenix, US");
		
		dataSource.put(e1.getEmpID(), e1);
		dataSource.put(e2.getEmpID(), e2);
		dataSource.put(e3.getEmpID(), e3);
		dataSource.put(e4.getEmpID(), e4);
		dataSource.put(e5.getEmpID(), e5);
								
	}
	
	public static void main(String[] args) {
		
		//Create Data Source
		createDataSource();
		
		System.out.println("Elements in Data Source: ");
		Set<Integer> setKey = dataSource.keySet();
		for(Integer i : setKey) {
			System.out.println(dataSource.get(i));
		}
		
		
		InMemoryCache memCache = new InMemoryCache();
		Employee e1 = memCache.new Employee();
		e1.setEmpID(1);
		e1.setName("Ricky");
		e1.setAdddress("Omaha, US");
		
		Employee e2 = memCache.new Employee(2, "Amber", "Chicago, US");
		
		Employee e3 = memCache.new Employee(3, "Laura", "New York, US");
		
		memCache.addToCache(e1);
		memCache.addToCache(e2);
		memCache.addToCache(e3);
		
		System.out.println("\nElements in in-memory cache: ");
		Set<Integer> setCacheKey = cache.keySet();
		for(Integer i : setCacheKey) {
			System.out.println(cache.get(i));
		}
		
		System.out.println("\nGoing to fetch data from cache..");
		InMemoryCache cacheObject = new InMemoryCache();
		System.out.println(cacheObject.fetchFromCache(2));
		
		System.out.println("\nAfter 5 minutes, cache is update from DataSource. Here are the updated values in cache:");
		refreshCache();
		Set<Integer> setCacheKey2 = cache.keySet();
		for(Integer i : setCacheKey2) {
			System.out.println(dataSource.get(i));
		}
					
	}
	
	public class Employee {
		
		private Integer empID;
		private String name;
		private String adddress;
		
		public Employee() {
			
		}
		
		public Employee(Integer empID, String name, String address) {
			this.empID = empID;
			this.name = name;
			this.adddress = address;
			
		}
		
		/**
		 * @return the empID
		 */
		public Integer getEmpID() {
			return empID;
		}
		/**
		 * @param empID the empID to set
		 */
		public void setEmpID(Integer empID) {
			this.empID = empID;
		}
		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * @return the adddress
		 */
		public String getAdddress() {
			return adddress;
		}
		/**
		 * @param adddress the adddress to set
		 */
		public void setAdddress(String adddress) {
			this.adddress = adddress;
		}
		
		@Override
		public String toString() {
			return "Emp ID = " + getEmpID() + " " + getName() + " lives in " + getAdddress();
		}		
		
		
	}

}
