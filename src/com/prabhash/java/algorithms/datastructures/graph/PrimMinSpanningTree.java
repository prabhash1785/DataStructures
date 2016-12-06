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
				}
			}
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
	
	public static class Vertex {
		private int id;
		private int data;
		private Vertex parent;
		private Vertex start;
		private Vertex end;
		
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
				+ (this.parent == null ? null : this.parent.id);
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
		final int[][] graph = generateGraphUsingMatrixRepresentation();
		printGraphInMatrixForm(graph);
		
		// get minimum spanning tree from Graph
		List<Vertex> minSpanningTree = findMinimumSpanningTree(graph, 0);
		System.out.println("\nHere are min spanning tree nodes:");
		for(Vertex v : minSpanningTree) {
			System.out.println(v);
		}
	}
}
