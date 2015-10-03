/*
 * Binary Heap Implementation in Java.
 * @author Prabhash Rathore
 *  
 */

package com.prabhash.java.algorithms.datastructures.tree;

import java.util.Arrays;
import java.util.Random;

/**
 * 
 * Binary Heap Data Structure is an Array Object that we can view as a nearly complete Binary Tree.
 * The tree is completely filled on all the levels except possibly the lowest, which is filled from left to right.
 * For any element at index i in the array, parent and child can be determined by following formula:
 * 	- Parent = i / 2
 *  - Left Child = 2 * i
 *  - Right Child = (2 * i) + 1
 *  
 * Heap Implementation: Array is a preferred way to implement heap compared to Linked List method of representing as a regular
 * tree because Heap is a complete Binary tree and it's filled from left to right so array is more memory efficient as we don't
 * have to save pointers for nodes and also looking up elements using index in array is faster.
 * 
 * Any ordinary array can be be visualized as a Heap. What distinguishes an ordinary array from a special property heap array are the properties of
 * Max-Heap and Min-Heap.
 * 
 * 2 kind of Heaps:
 * 	- Max Heap: A[Parent(i)] >= A[i]
 * 	- Min Heap: A[Parent(i)] <= A[i]
 * 
 * Application of Heap Data Structure:
 *  - Heap Sort (Max Heap is used)
 *  - Priority Queue (Min Heap is used)
 *  - Dijkstra Algorithm to find the shortest path
 *  
 *  Time Complexity of Heapify a Max-Heap: O(log n)
 *   
 */
public class Heap {
	
	private int[] heap;
	private int capacity = 10;
	private int heapSize;
	
	public Heap() {
		heap = new int[capacity];
		heapSize = 0;
	}
	
	public Heap(int capacity) {
		this.capacity = capacity;
		heap = new int[capacity];
		heapSize = 0;
	}
	
	private int parent(int i) {
		return i/2;
	}
	
	private int leftChild(int i) {
		return 2 * i;
	}
	
	private int rightChild(int i) {
		return ((2 * i) + 1);
	}
	
	private void swap(int[] list, int i, int largest) {
		int temp = list[i];
		list[i] = list[largest];
		list[largest] = temp;
	}
	
	private void fillArray() {
		Random random = new Random();
		for(int i = 0; i < capacity; i++) {
			heap[i] =  random.nextInt(100);
			heapSize++;
		}
		
		System.out.println("Original Input Array is: ");
		for(int k = 0; k < capacity; k++)
			System.out.print(heap[k] + " ");
		
	}
	
	private void resizeArray() {
		heap = Arrays.copyOf(heap, capacity * 2);
		capacity = capacity * 2;
	}
	
	/*
	 * Time Complexity: O(log n) or, O(h) where, h is the height of tree
	 */
	public void maxHeapify(int[] list, int i) {
		
		int l = leftChild(i);
		int r = rightChild(i);
		int largest;
		
		if(l < capacity && list[l] > list[i]) {
			largest = l;
		} else {
			largest = i;
		}
		
		if(r < capacity && list[r] > list[largest]) {
			largest = r;
		}
		
		if(largest != i) {
			swap(list, i, largest);
			maxHeapify(list, largest); //Recursive call
		}
		
	}
	
	public void heapTheArray(int index) {
		int largestChild;
		
		int root = heap[index];
		
		while(index < heapSize / 2) {
			int leftChild = leftChild(index);
			int rightChild = rightChild(index);
			
			if(rightChild < heapSize && heap[leftChild] < heap[rightChild]) {
				largestChild = rightChild;
			} else {
				largestChild = leftChild;
			}
			
			if(root >= heap[largestChild]) {
				break;
			}
			
			heap[index] = heap[largestChild];
			index = largestChild;
			
		}
		
		heap[index] = root;
		
	}
	
	public void createHeap(int[] a) {
		int heapSize = a.length;
		
		// We are staring at heapSize / 2 because leaves are by default heapified so it's an optimization
		for(int k = heapSize / 2; k >= 0; k--) {
			maxHeapify(a, k);
		}
		
	}
	
	public void insertElement(int value) {
		System.out.println("\n..Capacity: " + capacity + " ::: heap size: " + heapSize);
		if(heapSize < capacity) {
			heap[heapSize++] = value;			
		} else {
			resizeArray();
			System.out.println("\n---Capacity: " + capacity + " ::: heap size: " + heapSize);
			heap[heapSize++] = value;
		}
		System.out.println("Element to be heafied: " + heap[heapSize - 1]);
		//maxHeapify(heap, heapSize - 1);
		heapTheArray(heapSize - 1);
	}

	public static void main(String[] args) {
		
		Heap heap = new Heap(12);
		
		heap.fillArray(); //fill array with random numbers
		
		heap.createHeap(heap.heap);
		
		System.out.println("\n\nElements in the Max Heap are: ");
		for(int j = 0; j < heap.heap.length; j++)
			System.out.print(heap.heap[j] + " ");
		
		//Insert new element in heap
		heap.insertElement(25);
		System.out.println("\n\nElements in the Max Heap after adding one more element is: ");
		for(int j = 0; j < heap.heap.length; j++)
			System.out.print(heap.heap[j] + " ");
				

	}

}