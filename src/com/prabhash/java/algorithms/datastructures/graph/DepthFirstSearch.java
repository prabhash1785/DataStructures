/**
 * Depth-first search (DFS) is an algorithm for traversing or searching tree or graph data structures.
 * One starts at the root (selecting some arbitrary node as the root in the case of a graph) and explores as far as possible along 
 * each branch before backtracking.
 * DFS is similar to Pre-Order Traversal in Tree.
 * DFS is more space efficient than BFS.
 * This uses a Stack data structure for traversal of nodes in a Graph/Tree.
 * DFS can also be implemented RECURSIVELY without using a STACK.
 * Application of DFS:
 *  - Detecting cycle in a graph
 *  - Topological Sorting
 *  - Solving Puzzle solutions like Maze
 * Time Complexity: O(|E| + |V|)
 * Space Complexity: O(|V|)
 * 
 */

package com.prabhash.java.algorithms.datastructures.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch {

	/*
	 * Non Recursive DFS Algorithm using Stack.
	 */
	public static void DFS(Graph graph, Vertex v) {
		
		if((graph != null) && (v != null)) {
			Stack<Vertex> stack = new Stack<Vertex>();
			List<Vertex> list = new ArrayList<Vertex>();
			stack.push(v);
			list.add(v);
			while(!stack.empty()) {
				Vertex temp = stack.pop();
				System.out.print(temp + " "); //Printing the root
				for(Vertex i : graph.getAdjacentVertex(temp)) {
					if(!list.contains(i)) {
						list.add(i);
						stack.push(i);						
					}
				}
			}
			
		}
		
	}
	
	/*
	 * DFS Algorithm using Recursion
	 */
	public static void recursiveDFS(Graph graph, Vertex v) {
		
		if((graph != null) && (v != null)) {
			List<Vertex> list = new ArrayList<Vertex>();
			dfsUtil(graph, list, v);			
		}
		
	}
	
	private static void dfsUtil(Graph graph, List<Vertex> list, Vertex ver) {
		list.add(ver);
		System.out.print(ver + " ");
		for(Vertex i : graph.getAdjacentVertex(ver)) {
			if(!list.contains(i)) {
				list.add(i);
				dfsUtil(graph, list, i);						
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		Graph graph = new Graph(false); //creating an instance of an undirected graph by passing a parameter as false
		
		Vertex a = new Vertex("A", "A");
		graph.addVertex(a);
		
		Vertex b = new Vertex("B", "B");
		graph.addVertex(b);
		
		Vertex c = new Vertex("C", "C");
		graph.addVertex(c);
		
		Vertex d = new Vertex("D", "D");
		graph.addVertex(d);	
		
		Vertex e = new Vertex("E", "E");
		graph.addVertex(e);
		
		Vertex f = new Vertex("F", "F");
		graph.addVertex(f);
		
		graph.addEdge(a, b, graph.isDirected());
		graph.addEdge(b, c, graph.isDirected());
		graph.addEdge(e, f, graph.isDirected());
		graph.addEdge(a, f, graph.isDirected());
		
		graph.printGraph();
		
		//Print Graph Nodes using DFS Algorithm
		System.out.println("Output of Depth First Search using Non-Recursive DFS");
		DFS(graph, new Vertex("A", "A")); //Output: A F E B C
				
		System.out.println("\n");
		DFS(graph, new Vertex("B", "B")); //Output: B C A F E
		
		System.out.println("\n\nDFS Traversal using Recursive method");
		recursiveDFS(graph, new Vertex("A", "A")); //Output: A B C F E
		
		System.out.println("\n");
		recursiveDFS(graph, new Vertex("B", "B")); //Output: B A F E C
		

	}

}
