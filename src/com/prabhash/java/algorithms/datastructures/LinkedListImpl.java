package com.prabhash.java.algorithms.datastructures;

/**
 * this is an implementation of Singly Linked List
 * @author Prabhash Rathore
 * 
 */

public class LinkedListImpl {

	//inner class definition to create a linked list node
	private class Node {
		
		private Object data;
		private Node next;
		
		public Node() {
			this.data = null;
			this.next = null;
		}
		
		public Node(Object inputData) {
            this.data = inputData;
            this.next = null;
          }
		
		public Node(Object obj, Node node) {
			this.data = obj;
			this.next = node;			
		}
		
		public Object getData() {
            return data;
          }

          public void setData(Object data) {
            this.data = data;
          }

          public Node getNext() {
            return next;
          }

          public void setNext(Node next) {
            this.next = next;
          }
	}
	// inner class definition ends here
	
	private Node head;
	private Node tail;
	
	public void addValue(Object newData) {
		
		Node newNode = new Node();
		newNode.setData(newData);
		System.out.println("Adding element: " + newData);
		
		if(head == null) {
			head = newNode;
			tail = newNode;
		} else {
			tail.setNext(newNode);
			tail = newNode;
		}
		         
	}
	
	public void addAfter(Object newData, Object after) {
		Node temp = head; // initializing to head to temp so that we don't lose the head position
		Node refNode = null;
		
		System.out.println("Going to traverse the nodes..");
		while(true) {
			if(temp == null) {
				break;
			} 
			if(temp.getData().equals(after)) {
				refNode = temp;
				break;
			}
			temp = temp.getNext();
		}
		if(refNode != null) {
			Node newNode = new Node();
			newNode.setData(newData);
			newNode.setNext(temp.getNext());
			if(temp.equals(tail))
				tail = newNode;
			temp.setNext(newNode);
		} else {
			System.out.println("Couldn't find the target node...");
		}
	}
	
	public void deleteAfter(Object after) {
		Node temp = head;
		Node refNode = null;
		System.out.println("Going to traverse the nodes..");
		while(true) {
			if(temp == null) {
				break;
			}
			if(temp.getData().equals(after)) {
				refNode = temp;
				break;
			}
			temp = temp.getNext();
		}
		if(refNode != null) {
			temp = refNode.getNext();
			refNode.setNext(temp.getNext());
			if(refNode.getNext() == null){
                tail = refNode;
            }

		} else {
			System.out.println("Couldn't find the target node...");
		}
	}
	
	public void display() {
		Node temp = head;
		while(true) {
			if(temp == null) {
				break;
			} 
			System.out.println("Node value: " + temp.getData());
			temp = temp.getNext();
		}
	}
	
	public static void main(String[] args) {
		LinkedListImpl linkedList = new LinkedListImpl();
		linkedList.addValue("New York");
		linkedList.addValue("Chicago");
		linkedList.addValue("San Francisco");
		linkedList.addValue("Las Vegas");
		
		linkedList.display();
		
		linkedList.addAfter("Denver", "Chicago");		
		linkedList.display();
		
		linkedList.deleteAfter("Denver");
		linkedList.display();

	}

}
