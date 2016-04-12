package com.prabhash.java.algorithms.datastructures.tree;

public class ConvertBinaryTreetoDLL {
  
  private Node root;
  private Node head;
  
  public static NodePair convertToLL(Node root) {
    
    if(root == null) {
      return null;
    }
    
    NodePair part1 = convertToLL(root.left);
    NodePair part2 = convertToLL(root.right);
    
    if(part1 != null) {
    	concat(part1.tail, root);
    }
    
    if(part2 != null) {
    	concat(root, part2.head);
    }
    
    return new NodePair(part1 == null ? root : part1.head, part2 == null ? root : part2.tail);
  }
  
  private static void concat(Node x, Node y) {
	  x.left = y;
	  y.right = x;
  }
  
  /**
   * Class to keep head and tail of a sub-tree. This is used while linking nodes of a tree for doubly linked list.
   * 
   * @author prrathore
   *
   */
  private static class NodePair {
	  
	  private Node head;
	  private Node tail;
	  
	  public NodePair(Node head, Node tail) {
		  this.head = head;
		  this.tail = tail;
	  }
  }
  
  public static class Node {
    
    private int data;
    private Node left;
    private Node right;
    
    public Node(int data) {
      this.data = data;
    }
  }
  
  
  public static void main(String[] args) {
    
    Node root = new Node(4);
    root.left = new Node(7);
    root.right = new Node(11);
    
    root.left.left = new Node(20);
    root.left.right = new Node(40);
    
    root.right.left = new Node(50);
    root.right.right = new Node(100);
    
    NodePair llNodePair = convertToLL(root);
    Node llHead = llNodePair.head;
    
    while(llHead != null) {
    	System.out.print(llHead.data + " ");
    	llHead = llHead.left;
    }
  }

}
