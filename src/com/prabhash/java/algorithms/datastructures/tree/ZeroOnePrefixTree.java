package com.prabhash.java.algorithms.datastructures.tree;

public class ZeroOnePrefixTree {
	
	private Node root;
	
	public void insertDataToPrefixTree(int num) {
		
		if(root == null) {
			root = new Node();
		}
		
		Node pointer = root;
		String binaryString = Integer.toBinaryString(num);
		//System.out.println(num + " => " + binaryString);
		
		for(int i = 0; i < binaryString.length(); i++) {
			byte bit;
			if(binaryString.charAt(i) == '0') {
				bit = 0;
			} else {
				bit = 1;
			}
			
			if(bit == 0) {
				if(pointer.lowerBitNode == null) {
					pointer.lowerBitNode = new Node(bit);
				}
				pointer = pointer.lowerBitNode;
			} else {
				if(pointer.higherBitNode == null) {
					pointer.higherBitNode = new Node(bit);
				}
				pointer = pointer.higherBitNode;
			}
			
			if(i == binaryString.length() - 1) {
				pointer.isLeaf = true;
				pointer.data = num;
			}
		}
	}
	
	public void inOrderTraversal(Node root) {
		if(root == null) {
			return;
		}
		
		inOrderTraversal(root.lowerBitNode);
		if(root.isLeaf) {
			System.out.print(root.data + " ");
		}
		inOrderTraversal(root.higherBitNode);
	}
	
	public static class Node {
		private byte bitData;
		private int data;
		private boolean isLeaf;
		private Node lowerBitNode;
		private Node higherBitNode;
		
		public Node() {
		}
		
		public Node(byte bitData) {
			this.bitData = bitData;
		}
	}

	public static void main(String[] args) {
		
		ZeroOnePrefixTree tree = new ZeroOnePrefixTree();
		tree.insertDataToPrefixTree(40);
		tree.insertDataToPrefixTree(6);
		tree.insertDataToPrefixTree(5);
		tree.insertDataToPrefixTree(4);
		
		tree.inOrderTraversal(tree.root);
	}
}
