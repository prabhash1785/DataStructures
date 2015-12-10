package com.prabhash.java.algorithms.general; 

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Compare two n-ary trees for their equality
 * 	
 * 			 a
 * 		a    a     a
 *    a      a    a a   a
 * 
 */
public class CompareTrees {
	
	private Node root;

	// get all nodes at each level along with the set of group of node. Compare them all, if at any level there is a mismatch
	public static boolean comparison(Node root1, Node root2) {

		if(root1 == null || root2 == null) {
			return true;
		}

		Queue<Node> queue1 = new LinkedList<Node>();
		Queue<Node> bkup1 = new LinkedList<Node>();

		Queue<Node> queue2 = new LinkedList<Node>();
		Queue<Node> bkup2 = new LinkedList<Node>();

		queue1.add(root1);
		queue2.add(root2);

		List<Integer> l1 = new ArrayList<>();
		List<Integer> l2 = new ArrayList<>();

		while(queue1.peek() != null) {
			Node n = queue1.remove();

			List<Node> list1 = n.getChildren();

			l1.add(list1.size());

			for(Node n1 : list1) {
				bkup1.add(n1);
			}

			Node m = queue2.remove();

			List<Node> list2 = m.getChildren();

			l2.add(list2.size());

			for(Node n2 : list2) {
				bkup2.add(m);
			}

			if(queue1.peek() == null) {

				Collections.sort(l1);
				Collections.sort(l2);

				if(!l1.equals(l2)) {
					return false;
				}

				queue1 = bkup1;
				bkup1 = new LinkedList<>();

				queue2 = bkup2;
				bkup2 = new LinkedList<>();


			}

		}

		return true;

	}
	
	private static class Node {

		private int key;
		private List<Node> children;
		
		public int getKey() {
			return key;
		}
		public void setKey(int key) {
			this.key = key;
		}
		public List<Node> getChildren() {
			return children;
		}
		public void setChildren(List<Node> children) {
			this.children = children;
		}

	}

}