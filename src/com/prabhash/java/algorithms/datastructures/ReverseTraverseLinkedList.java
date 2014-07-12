package com.prabhash.java.algorithms.datastructures;

public class ReverseTraverseLinkedList
{
	
	public static class LNode {
	   int value;
	   LNode next;
	   
	   LNode (int val) {
	      value = val;
	   }
	   
	}


  public static void printList (LNode head) {
	    while (head != null)
	    {
	      System.out.print (head.value + "   ");
	      head = head.next;
	    }
	    System.out.println();
	    
  }
  
  /*
   * Recursive Reversal
   */
  public static LNode reverseList (LNode head, LNode prev) {
    
	  if (head == null) {
		  return prev;
	  } else {
	      LNode next = head.next;
		  head.next = prev;
		  prev = head;
		  return reverseList (next, head);
	  }
	      
  }
  
  /*
   * Iterative Reversal
   */
  public static LNode reverseListIterative(LNode head) {
	  
	  LNode prev = null, next = null;
      if (head == null) 
    	  return head;
      while (true) {
          next = head.next;
          head.next = prev;
          prev = head;
          if (next == null) 
        	  return head;
          head = next;
      }
	  
  }
  
  public static void main (String args[]) {
	  
	    LNode a = new LNode (11);
	    LNode b = new LNode (12);a.next = b;
	    LNode c = new LNode (13);b.next = c;
	    LNode d = new LNode (14);c.next = d;
	    LNode e = new LNode (15);d.next = e;
	    LNode f = new LNode (37);e.next = f;
	    LNode g = new LNode (39);f.next = g;
	    
	    LNode head = a;
	    System.out.println ("Before reversing list : ");
	    printList(head);
	    
	    System.out.println("Before Reversing " + head.value + " =============");
	    
	    //head = reverseList (head, null);
	    head = reverseListIterative(head);
	    
	    System.out.println("After Reversing " + head.value + " =============");    
	    
	    System.out.println ("After reversing list : ");
	    
	    printList(head);
	    
  }
  
}

