package com.prabhash.java.algorithms.datastructures.tree;

/**
 * In computer science, a trie, also called digital tree and sometimes radix tree or prefix tree (as they can be searched
 * by prefixes), is an ordered tree data structure that is used to store a dynamic set or associative array where the keys
 * are usually strings.
 * Compressed Trie is better than normal Trie.
 * Performance of a Trie:
 * 	- The performance is Sub-Linear, ie. we only need to search number of characters in the text to be searched.
 * 	- Search hit would be O(m) where m is the number of characters in text to be searched.
 *  - Search miss is even faster, it stops right when there is a mis-match so less than O(m).
 *  - Takes a lot of space, R null references for every node. 
 * Aplication of Trie:
 * 	- Lexicographic Sorting (String Sorting)
 * 	- Predictive Text
 * 	- Autocomplete Dictionary such as found on Mobile Phones
 * 	- Approximate Matching Algorithms like Spell Checking
 * 
 *  Ref: http://algs4.cs.princeton.edu/52trie/TrieST.java.html
 * 
 */
public class Trie<Value> {
	
	private static final int R = 256; //Number of characters in Extended ASCII Character-Set
	private Node root = new Node();
	
	public void put(String key, Value val) {
		root = put(root, key, val, 0);
	}
	
	private Node put(Node x, String key, Value val, int d) {
		if(x == null) {
			x = new Node();
		}
		if(d == key.length()) {
			x.value = val;
			return x;
		}
		char c = key.charAt(d);
		x.next[c] = put(x.next[c], key, val, d + 1);
		return x;
	}
	
	public boolean contains(String key) {
		return get(key) != null;
	}
	
	public Value get(String key) {
		Node x = get(root, key, 0);
		if(x == null) {
			return null;
		}
		return (Value) x.value;
	}
	
	private Node get(Node x, String key, int d) {
		if(x == null) {
			return null;
		}
		if(d == key.length()) {
			return x;
		}
		char c = key.charAt(d);
		return get(x.next[c], key, d + 1);
	}
	
	public void delete(String key) {
		root = delete(root, key, 0);
	}
	
	private Node delete(Node x, String key, int d) {
		if(x == null) {
			return null;
		}
		if(key.length() == d) {
			if(x.value != null) {
				x.value = null;;
			} 
		} else {
			char c = key.charAt(d);
			x.next[c] = delete(x.next[c], key, d + 1);
		}
		
		// remove subtrie rooted at x if it is completely empty
        if (x.value != null) {
        	return x;
        }
        for (int c = 0; c < R; c++) {
            if (x.next[c] != null) {
                return x;
            }
        }
        return null;		
	}
	
	public static void main(String[] args) {
		
		Trie<Integer> trie = new Trie<Integer>();
		trie.put("amber", 1);
		trie.put("ricky", 2);
		trie.put("max", 3);
		trie.put("sunday", 4);
		
		System.out.println("Is Amber present: " + trie.contains("amber"));
		System.out.println("Is Chicago present: " + trie.contains("chicago"));
		System.out.println("Is Ricky present: " + trie.contains("ricky"));
		System.out.println("Is Max present: " + trie.contains("max"));
		System.out.println("Is Sunday present: " + trie.contains("sunday"));
		
		trie.delete("max");
		
		System.out.println("Is Max present: " + trie.contains("max"));
		System.out.println("Is Sunday present: " + trie.contains("sunday"));

	}
	
	/**
	 * This nested class represents a node in a Trie Data Structure.  
	 * Each node can have a value of type Object which represents the end of a word/string and each node can have 
	 * references to n child nodes where each reference will represent chracters from ASCII or Unicode character-set.
	 * 
	 */
	private static class Node {
		private Object value; //Use Object type instead of Generics because Java array doesn't support Generics
		private Node[] next = new Node[R];		
	}

}
