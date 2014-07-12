package com.prabhash.java.string;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * Program to find Cartesian Product of Sets
 * Ref: http://stackoverflow.com/questions/714108/cartesian-product-of-arbitrary-sets-in-java
 *
 */

public class CartesianProduct {
	
	public static Set<Set<Object>> getCartesianProduct(Set<Set<Object>> sets) {
	    System.out.println("Sets length: " + sets.size());
		if (sets.size() < 2)
	        throw new IllegalArgumentException(
	                "Can't have a product of fewer than two sets (got " +
	                sets.size() + ")");

	    return cartesianProduct(0, sets);
	}

	private static Set<Set<Object>> cartesianProduct(int index, Set<?>... sets) {
	    Set<Set<Object>> ret = new HashSet<Set<Object>>();
	    if (index == sets.length) {
	        ret.add(new HashSet<Object>());
	    } else {
	        for (Object obj : sets[index]) {
	            for (Set<Object> set : cartesianProduct(index+1, sets)) {
	                set.add(obj);
	                ret.add(set);
	            }
	        }
	    }
	    return ret;
	}

	public static void main(String[] args) {
		
		Set<Object> set1 = new HashSet<Object>();
		set1.add(2);
		set1.add(4);
		set1.add(6);
		
		Set<Object> set2 = new HashSet<Object>();
		set2.add(10);
		set2.add(12);
		set2.add(14);
		
		Set<Set<Object>> input = new HashSet<Set<Object>>();
		input.add(set1);
		input.add(set2);
		
		Set<Set<Object>> cartesianProd = getCartesianProduct(input);
		
		for(Set<Object> s : cartesianProd) {
			
			Iterator<Object> iterator = s.iterator();
			while(iterator.hasNext()) {
				System.out.print(iterator.next() + " ");
			}
			System.out.print("\n");
		}

	}

}
