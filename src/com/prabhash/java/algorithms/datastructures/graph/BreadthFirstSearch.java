/**
 * Breadth First Search is also known as Level-Order search. This is used to search data in Graph and Tree.
 * BFS visits all the immediate neighbors of a node and then visits their neighbors subsequently until it visits all the nodes.
 * It uses a Queue Data Structure to store the intermediate nodes.
 * For Queue, maximum memory needed is n/2 where n is the total number of nodes/vertices
 * Worst Case Time Complexity: O(|V| + |E|)
 * Worst Case Space Complexity: O(|V|)
 * Applications of BFS: Find all nodes for Connected Components, Shortest Distance, Serialization/Deserialization of Binary Tree
 * @author Prabhash Rathore
 *
 */

package com.prabhash.java.algorithms.datastructures.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Pseudocode of Breadth First Search (Level Order Search):
 * procedure BFS(G, v)
 * 		create a Queue Q
 * 		create a vector V
 * 	    add v to queue
 * 		add v to vector
 * 		while q!= null
 * 			t = Queue.dequeue()
 * 			if t is what you are looking for 
 * 				then return t
 * 			else
 * 				for all edges e in G.adjacentEdges(t)
 * 					u = G.adjacentVertex(t, e)
 * 					If u is not then in V
 * 						add u to V
 * 						enqueue u onto Q
 * 
 * 
 */
public class BreadthFirstSearch {
	
	public static void BFS(Graph graph, Vertex v) {
				
		if((graph == null) && (v == null)) {
			return;
		} else {
			Queue<Vertex> queue = new LinkedList<Vertex>(); //LinkedList implementation of a Queue
			List<Vertex> list = new ArrayList<Vertex>(); //this list is used to keep track of visited nodes
			queue.add(v);
			list.add(v);
									
			while(queue.peek() != null) {
				Vertex temp = queue.remove();
				System.out.print(temp + " ");
				for(Vertex i : graph.getAdjacentVertex(temp)) {
					if(!list.contains(i)) {
						list.add(i);
						queue.add(i);						
					}
				}
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
		graph.addEdge(e, f, graph.isDirected());
		graph.addEdge(a, f, graph.isDirected());
		
		graph.printGraph();
		
		//Call Breadth first search method to do a search..
		System.out.println("Output of Breadth First Search: ");
		BFS(graph, new Vertex("A", "A")); //Output: A B F E
		
		System.out.println("\n");
		BFS(graph, new Vertex("F", "F")); //Output: F E A B
		
		System.out.println("\n");
		BFS(graph, new Vertex("E", "E")); //Output: E F A B
		
		System.out.println("\n");
		BFS(graph, new Vertex("B", "B")); //Output: B A F E

	}

}
