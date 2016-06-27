package com.prabhash.java.algorithms.datastructures.tree;

/**
 * Zero-One Prefix Tree. This could be used to sort numbers if this tree is traversed using
 * in-order traversal.
 * 
 * @author prrathore
 *
 */
public class ZeroOnePrefixTree {
	
	private Node root;
	
	/**
	 * Insert Binary representation of number to Prefix tree.
	 * 
	 * @param num
	 */
	public void insertDataToPrefixTree(int num) {
		
		if(root == null) {
			root = new Node();
		}
		
		Node pointer = root;
		String binaryString = getBinaryString(num);
		
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
	
	private static String getBinaryString(int num) {
		String binaryString = Integer.toBinaryString(num);
		int binaryStringLength = binaryString.length();
		// pad zeroes in front of binary string to make them 32 bit length
		while(binaryStringLength <= 32) {
			binaryString = "0" + binaryString;
			binaryStringLength++;
		}
		
		return binaryString;
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
