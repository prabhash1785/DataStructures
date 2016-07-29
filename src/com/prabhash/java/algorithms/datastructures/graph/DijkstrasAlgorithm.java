package com.prabhash.java.algorithms.datastructures.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

/**
 * Dijkstra's Algorithm to find the shortest path in a given graph.
 * 
 * @author Prabhash Rathore
 *
 */
public class DijkstrasAlgorithm {
	
	/**
	 * Find the shortest path from start to end position in a grid.
	 * 
	 * Average Time Complexity: O(E logV)
	 * Space Complexity: O(V + E)
	 * 
	 * @param graph
	 * @param start
	 * @param end
	 * @return List<Node>	path from start to end
	 */
	public static List<Node> findShortestPath(Node[][] graph, Node start, Node end) {
		if(graph == null || start == null || end == null) {
			throw new NullPointerException();
		}
		
		if(!isValidCell(graph, start.rowID, start.colID) || !isValidCell(graph, end.rowID, end.colID)) {
			System.out.println("Invalid cell positions");
			throw new IllegalArgumentException();
		}
		
		if(!start.walkable || !end.walkable) {
			System.out.println("No path exists");
			return null;
		}
	
		Queue<Node> pq = new PriorityQueue<>(new MinDistanceComparator());
		Set<Node> vistedNodes = new HashSet<>();
		
		pq.offer(start);
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			vistedNodes.add(node);
			
			if(node.equals(end)) {
				break;
			}
			
			// find all valid neighbors of current node
			List<Node> allNeighbors = getAllValidNeighbors(graph, node);
			for(Node neighbor : allNeighbors) {				
				if(vistedNodes.contains(neighbor)) {
					continue;
				}
				if(!neighbor.walkable) {
					continue;
				}
				
				int distanceFromStart = node.distanceFromStart + neighbor.cost;
				if(neighbor.distanceFromStart == 0 || distanceFromStart < neighbor.distanceFromStart) {
					neighbor.distanceFromStart = distanceFromStart;
					neighbor.parent = node;
				}
				
				// Remove this node if it already contains in PQ then add it later so it's
				// priority could be reshuffled as per the new distanceFromStart value of this node
				if(pq.contains(neighbor)) {
					pq.remove(neighbor);
				}
				
				pq.offer(neighbor);
			}
		}
		
		return derivePathFromEndNode(end);
	}
	
	private static List<Node> derivePathFromEndNode(Node node) {
		if(node == null) {
			return null;
		}
		List<Node> path = new ArrayList<>();
		while(node != null) {
			path.add(node);
			node = node.parent;
		}
		
		return path;
	}
	
	private static List<Node> getAllValidNeighbors(Node[][] graph, Node current) {
		if(graph == null || current == null) {
			return null;
		}
		
		List<Node> neighbors = new ArrayList<>(); 
		int row = current.rowID;
		int col = current.colID;
		
		// right = graph[row][col + 1]
		if(isValidCell(graph, row, col + 1)) {
			neighbors.add(graph[row][col + 1]);
		}
		
		// rightLowerDiagonal = graph[row + 1][col + 1];
		if(isValidCell(graph, row + 1, col + 1)) {
			neighbors.add(graph[row + 1][col + 1]);
		}
		
		// bottom = graph[row + 1][col];
		if(isValidCell(graph, row + 1, col)) {
			neighbors.add(graph[row + 1][col]);
		}
		
		// leftLowerDiagonal = graph[row + 1][col - 1];
		if(isValidCell(graph, row + 1, col - 1)) {
			neighbors.add(graph[row + 1][col - 1]);
		}
		
		// left = graph[row][col - 1];
		if(isValidCell(graph, row, col - 1)) {
			neighbors.add(graph[row][col - 1]);
		}
		
		// leftUpperDiagonal = graph[row - 1][col - 1];
		if(isValidCell(graph, row - 1, col - 1)) {
			neighbors.add(graph[row - 1][col - 1]);
		}
		
		// top = graph[row - 1][col];
		if(isValidCell(graph, row - 1, col)) {
			neighbors.add(graph[row - 1][col]);
		}
		
		// rightUpperDiagonal = graph[row - 1][col + 1];
		if(isValidCell(graph, row - 1, col + 1)) {
			neighbors.add(graph[row - 1][col + 1]);
		}
		
		return neighbors;
	}
	
	private static boolean isValidCell(Node[][] graph, int row, int col) {
		if(graph == null) {
			return false;
		}
		
		if(row >= 0 && row < graph.length && col >= 0 && col < graph[0].length) {
			return true;
		}
		
		return false;
	}
	
	public static class Node {
		private int cost; // cost to travel through this node
		private int rowID;
		private int colID;
		private boolean walkable;
		private int distanceFromStart;
		private Node parent;
		
		public Node(int rowID, int colID, int cost) {
			this.rowID = rowID;
			this.colID = colID;
			this.cost = cost;
			this.walkable = true;
			this.distanceFromStart = 0;
			this.parent = null;
		}
		
		@Override
		public String toString() {
			return "{" + rowID + ", " + colID + ", " + cost + "}"; 
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj == null) {
				return false;
			}
			
			if(obj.getClass() != this.getClass()) {
				return false;
			}
			
			Node node = (Node) obj;
			if(this.rowID == node.rowID && this.colID == node.colID) {
				return true;
			}
			
			return false;
		}
		
		@Override
		public int hashCode() {
			int prime = 23;
			int hashCode = 7;
			hashCode = hashCode * prime + Integer.valueOf(rowID).hashCode() + Integer.valueOf(colID).hashCode();
			return hashCode;
		}
	}
	
	public static class MinDistanceComparator implements Comparator<Node> {
		
		@Override
		public int compare(Node node1, Node node2) {
			if(node1.distanceFromStart == node2.distanceFromStart) {
				return 0;
			} else if(node1.distanceFromStart > node2.distanceFromStart){
				return 1;
			} else {
				return -1;
			}
		}
	}

	public static void main(String[] args) {
		Node[][] grid = new Node[5][5];
		Random randomCost = new Random();
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid.length; j++) {
				grid[i][j] = new Node(i, j, randomCost.nextInt(11));
			}
		}
		
		Node start = grid[0][2];
		Node end = grid[3][1];
		
		System.out.println("Here is the grid:");
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.println("\nStart node: " + start);
		System.out.println("\nEnd node: " + end);
		
		List<Node> path = findShortestPath(grid, start, end);
		if(path == null || path.size() == 0) {
			System.out.println("\nNo path exists!!");
		} else {
			System.out.println("\nHere is the path:");
			for(Node node : path) {
				System.out.print(node + " => ");
			}
		}
	}
}
