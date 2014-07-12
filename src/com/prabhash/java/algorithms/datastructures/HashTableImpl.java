package com.prabhash.java.algorithms.datastructures;

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
		System.out.println("Key is: " + k + " hash value is: " + index);
		return index;
	}
	
	public int size() {
		System.out.println("Here is the size of hashtable: " + length);
		return length;
	}
	
	public boolean contains(Object key) {
		boolean flag = false;
		int hash = hash(key);
		
		if(hashTable[hash] != null)
			flag = true;
		
		return flag;
	}
	
	public void displayElements() {
		for(int i = 0; i < Max_SIZE; i++) {
			HashTableNode temp = hashTable[i];
			if(temp == null) {
				System.out.println("Index: " + i + " Key: " + null + " Data: " + null);
			} else {
				while(temp != null) {
					if(temp != null) {
						System.out.println("Index " + i + " Key: " + temp.getKey() + " Data: " + temp.getData());
					}
					temp = temp.getNextNode();
				}
			}
			
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
	}
	
	public void addElement(Object key, Object value) {		
		
		if(key == null || value == null) {
			System.out.println("ERROR: Either key or value is null..");
		}
		
		int index = hash(key);
		
		HashTableNode addNode = new HashTableNode();
		addNode.setKey(key);
		addNode.setData(value);
		
		if(hashTable[index] == null) {
			hashTable[index] = addNode;
		} else {
			HashTableNode tempNode = hashTable[index];
			while(tempNode != null) {
				if(tempNode == null)
					tempNode = addNode;
				tempNode = tempNode.getNextNode();
			}
			
		}
		
	}
	
	/*public HashTableNode deleteElement() {
				
		
		return;
	}
	
	public HashTableNode search(Object 0) {
		
	}*/
	
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
	
	

}
