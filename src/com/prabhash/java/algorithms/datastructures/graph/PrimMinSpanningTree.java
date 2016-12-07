package com.prabhash.java.algorithms.datastructures.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Prim's Minimum Spanning Tree algorithm.
 * 
 * @author Prabhash Rathore
 *
 */
public class PrimMinSpanningTree {
	
	/**
	 * Find minimum spanning tree in given graph using Prim's Algorithm.
	 * 
	 * Maintain a Priority Queue (PQ) of vertices where vertices are prioritized based on weight of vertices. While PQ
	 * is not empty, extract root of PQ. Add this vertex to output. Get all neighbors of this vertex and for each neighbor, update
	 * it's parent and weight if this neighbor is still available in PQ and it's stored weight > distance of this vertex from it's
	 * parent vertex. 
	 *
	 * Reference: CLRS Page No # 634
	 * 
	 * Time Complexity: O(E logV) where E - Number of Edges and V - Number of Verices
	 * 
	 * @param graph
	 * @param vertexID
	 * @return spanningTreeVertices
	 */
	public static List<Vertex> findMinimumSpanningTree(int[][] graph, int vertexID) {
		if(graph == null) {
			throw new NullPointerException("Graph is null");
		}
		
		if(vertexID < 0 || vertexID >= graph.length) {
			throw new IllegalArgumentException("Invalid Vertex ID");
		}
		
		List<Vertex> spanningTreeVertices = new ArrayList<>();

		Queue<Vertex> priorityQueue = new PriorityQueue<>(
				(v1, v2) -> Integer.compare(v1.data, v2.data)); // Min Priority Queue
		Map<Integer, Vertex> vertexMap = new HashMap<>(); // Store Vertex ID to Vertex Mapping for constant look-up
		
		for(int i = 0; i < graph.length; i++) {
				Vertex v = new Vertex(i);
				
				// Other than vertexID from where traversal needs to start, set every other vertex weight to MAX_VALUE
				if(v.id == vertexID) {
					v.data = 0;
				} else {
					v.data = Integer.MAX_VALUE;
				}
				
				v.parent = null;
				priorityQueue.add(v);
				vertexMap.put(i, v);
		}
		
		while(!priorityQueue.isEmpty()) {
			Vertex v = priorityQueue.poll();
			vertexMap.remove(v.id);
			spanningTreeVertices.add(v); // add this vertex to spanning tree list
			
			List<Integer> neighbors = getAllAdjacentVertices(graph, v.id);
			for(Integer vID : neighbors) {
				Vertex vert = vertexMap.get(vID);
				
				if(priorityQueue.contains(vert) && graph[vert.id][v.id] < vert.data) {
					vert.data = graph[vert.id][v.id];
					vert.parent = v;
					
					// update priority queue ordering by removing this vertex and adding it back so that it gets placed at the
					// right level based on priority
					priorityQueue.remove(vert);
					priorityQueue.add(vert);
				}
			}
		}
		
		// If Graph is disconnected then minimum spanning tree cannot be determined from given vertex
		if(spanningTreeVertices.size() < graph.length) {
			throw new RuntimeException("Spanning tree cannot be determine as not all vertices are connected in Graph");
		}
		
		return spanningTreeVertices;
	}
	
	private static List<Integer> getAllAdjacentVertices(int[][] graph, int vertexID) {
		List<Integer> list = new ArrayList<>();
		
		for(int i = 0; i < graph[vertexID].length; i++) {
			if(graph[vertexID][i] > 0) {
				list.add(i);
			}
		}
		
		return list;
	}
	
	/**
	 * Represents vertex in a Graph
	 * 
	 * id - Name of vertex
	 * data - Weight of this vertex from an arbritrary vertex in Graph. This will be used to hold minimum weight to any arbritrary
	 * vertex in Graph as an outcome of Minimum Spanning Tree
	 * parent - Parent of this vertex. Will be used form Minimum Spanning Tree. 
	 * 
	 * @author Prabhash Rathore
	 *
	 */
	public static class Vertex {
		private int id;
		private int data;
		private Vertex parent;
		
		public Vertex(int id) {
			this.id = id;
		}
		
		@Override
		public boolean equals(Object v) {
			if(v == null) {
				return false;
			}
			
			if(v.getClass() != this.getClass()) {
				return false;
			}
			
			Vertex vertex = (Vertex) v;
			if(this.id == vertex.id) {
				return true;
			}
			
			return false;
		}
		
		@Override
		public int hashCode() {
			int hashCode = 11;			
			hashCode = hashCode * 23 +  this.id;
			return hashCode;
		}
		
		@Override
		public String toString() {
			return "{id=" + this.id + ", weight=" + this.data + ", parent=" 
				+ (this.parent == null ? null : this.parent.id) + "}";
		}
	}
	
	private static int[][] generateGraphUsingMatrixRepresentation() {
		int[][] graph = new int[6][6];
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				graph[i][j] = 0;
			}
		}
		
		graph[0][1] = 2;
		graph[0][4] = 1;
		graph[0][5] = 4;
		
		graph[1][0] = 2;
		graph[1][2] = 5;
		graph[1][3] = 3;
		
		graph[2][1] = 5;
		graph[2][3] = 6;
		graph[2][4] = 8;
		
		graph[3][1] = 3;
		graph[3][2] = 6;
		graph[3][4] = 7;
		
		graph[4][0] = 1;
		graph[4][2] = 8;
		graph[4][3] = 7;
		graph[4][5] = 6;
		
		graph[5][0] = 4;
		graph[5][4] = 6;
		
		return graph;
	}
	
	private static void printGraphInMatrixForm(int[][] graph) {
		if(graph == null) {
			System.out.println("Graph is null!");
			return;
		}
		
		System.out.println("Graph Representation as matrix:");
		for(int i = 0; i < graph.length; i++) {
			for(int j = 0; j < graph[0].length; j++) {
				System.out.print(graph[i][j] + "  ");
			}
			System.out.print("\n");
		}
	}
	
	public static void main(String[] args) {
		// test case 1
		final int[][] graph = generateGraphUsingMatrixRepresentation();
		printGraphInMatrixForm(graph);
		
		// get minimum spanning tree from Graph
		List<Vertex> minSpanningTree = findMinimumSpanningTree(graph, 0);
		System.out.println("\nHere are min spanning tree nodes:");
		for(Vertex v : minSpanningTree) {
			System.out.println(v);
		}
		
		// test case 2
		final int[][] graph2 = new int[][] {
				{0, 2, 0, 6, 0},
				{2, 0, 3, 8, 5},
				{0, 3, 0, 0, 7},
				{6, 8, 0, 0, 9},
				{0, 5, 7, 9, 0}
		};
		// get minimum spanning tree from Graph
		List<Vertex> minSpanningTree2 = findMinimumSpanningTree(graph2, 0);
		System.out.println("\nHere are min spanning tree nodes:");
		for(Vertex v : minSpanningTree2) {
			System.out.println(v);
		}
	}
}
