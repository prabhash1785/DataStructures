package com.prabhash.java.algorithms.datastructures;

/**
 * A simple implementation of a Map.
 * 
 * This uses LinkedList to deal with collisions.
 * 
 * @author prrathore
 *
 */
public class HashTableImpl {

	private HashTableNode[] hashTable;
	private final int Max_SIZE = 23; //prime number for the better hashing performance
	private int length = 0;
	
	public HashTableImpl() {
		
		hashTable = new HashTableNode[Max_SIZE];
	}
	
	public int hash(Object k) {
		int index = 0;
		String s = k.toString().toLowerCase();
		for(int i = 0;i< s.length();i++) {
			index += s.charAt(i);
		}
		index = index % Max_SIZE;
		System.out.println("Key = " + k + " :::: hash value = " + index);
		return index;
	}
	
	public int size() {
		return length;
	}
	
	public boolean contains(Object key) {
		boolean flag = false;
		int hash = hash(key);
		
		if(hashTable[hash] != null)
			flag = true;
		
		return flag;
	}
	
	public void addElement(Object key, Object value) {		
		
		if(key == null || value == null) {
			System.out.println("ERROR: Either key or value is null..");
		}
		
		length++; // increase the size of HashMap elements
		
		int index = hash(key);
		
		HashTableNode newNode = new HashTableNode();
		newNode.setKey(key);
		newNode.setData(value);
		
		if(hashTable[index] == null) {
			
			hashTable[index] = newNode;
		} else {
			
			HashTableNode tempNode = hashTable[index];
			while(tempNode.getNextNode() != null) {
				tempNode = tempNode.getNextNode();
			}
			
			tempNode.setNextNode(newNode);
			
		}
		
	}
	
	public void displayElements() {
		for(int i = 0; i < Max_SIZE; i++) {
			
			HashTableNode temp = hashTable[i];
			
			if(temp != null) {
				while(temp != null) {
					if(temp != null) {
						System.out.println("Index " + i + " Key: " + temp.getKey() + " Data: " + temp.getData());
					}
					temp = temp.getNextNode();
				}
			} 
		}
	}
	
	private class HashTableNode {
		private Object key;
		private Object data;
		private HashTableNode nextNode;
		
		public Object getKey() {
			return key;
		}

		public void setKey(Object key) {
			this.key = key;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

		public HashTableNode getNextNode() {
			return nextNode;
		}

		public void setNextNode(HashTableNode nextNode) {
			this.nextNode = nextNode;
		}

		public HashTableNode() {
			this.key = null;
			this.data = null;
			this.nextNode = null;
		}
		
		@Override
		public boolean equals(Object obj) {
			boolean result = false;
			
			if(obj == null)
				return result;
			
			if(obj instanceof HashTableNode) {
				HashTableNode node = (HashTableNode)obj;
				if((this.key == node.key) && (this.data == node.data))
					result = true;
			}
			
			return result;
		}
		
	}
	
	public static void main(String[] args) {
		HashTableImpl hashTableImpl = new HashTableImpl();
		hashTableImpl.addElement("Fruit", "Apple");
		hashTableImpl.addElement("Planet", "Earth");
		hashTableImpl.addElement("Fruit", "Mango");
		hashTableImpl.addElement("Company", "PayPal");
		hashTableImpl.addElement("Country", "India");
		hashTableImpl.addElement("Company", "Amazon");
		hashTableImpl.addElement("Country", "US");
		
		hashTableImpl.displayElements();
		
		System.out.println("Number of elements in hashmap: " + hashTableImpl.size());
	}

}
