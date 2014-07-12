package com.prabhash.java.algorithms.datastructures.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
 * This is an implementation of Graph Data Structure using Adjacency List technique
 * @author Prabhash Rathore
 * 
 */
public class Graph {

	private List<Vertex> vertex;
	private List<Edge> edge;
	@SuppressWarnings("unused")
	private int numberOfVertices;
	@SuppressWarnings("unused")
	private int numberOfEdges;
	private boolean isDirected;
	
	// While instantiating this graph, user needs to input the type of the Graph - Directed or Undirected
	public Graph(boolean isDirected) {
		this.vertex = new LinkedList<Vertex>();
		this.edge = new LinkedList<Edge>();
		this.numberOfVertices = 0;
		this.numberOfEdges = 0;
		this.isDirected = isDirected;
	}
	
	public void addVertex(Vertex v) {
		vertex.add(v);
		this.numberOfVertices++;
	}
	
	public void addEdge(Vertex a, Vertex b, boolean isDirected) {
		Edge newEdge = new Edge();
		newEdge.setStart(a);
		newEdge.setEnd(b);
		edge.add(newEdge);
		this.numberOfEdges++;
		if(!isDirected) {
			addEdge(b, a, true); //Recursive call to addEdge method if is a non-directed graph
		}
	}
	
	public boolean isConnected(Vertex m, Vertex n) {
		boolean connected = false;
		Edge tempEdge = new Edge();
		tempEdge.setStart(m);
		tempEdge.setEnd(n);
		
		for(Edge e : edge) {
			if(e.equals(tempEdge)) {
				connected = true;
				break;
			}
		}
		
		return connected;
	}
	
	/*
	 * This method finds the neighboring nodes connected to a given vertex.
	 * This is a very important method to traverse a Graph used in BFS and DFS algorithms.
	 * 
	 */
	public List<Vertex> getAdjacentVertex(Vertex v) {
		List<Vertex> list = new LinkedList<Vertex>();
		if((v != null) && (edge != null)) {
			Iterator<Edge> iterator = edge.iterator();
			while(iterator.hasNext()) {
				Edge temp = iterator.next(); 
				if(temp.getStart().equals(v)) {
					list.add(temp.getEnd());
				}
			}
			
		} 
		
		return list;		
	}
	
	public void printGraph() {
		System.out.println("Number of vertices: " + this.vertex.size());
		System.out.println("Here are the vertices:");
		for(Vertex v : this.vertex)
			System.out.println(v);
		
		System.out.println("Number of edges: " + this.edge.size());
		System.out.println("Here are the edges:");
		for(Edge e : edge)
			System.out.println(e);
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
		
		graph.addEdge(a, b, graph.isDirected);
		graph.addEdge(e, f, graph.isDirected);
		graph.addEdge(a, f, graph.isDirected);
		
		graph.printGraph();
		
		System.out.println("Check if following vertices are connected");
		if(graph.isConnected(b, a))
			System.out.println("B and A are connected...");
		else
			System.out.println("B and A are NOT connected.");
		
		if(graph.isConnected(c, d))
			System.out.println("C and D are connected...");
		else
			System.out.println("C and D are NOT connected.");
		
		//Call getAdjacentVertex() to get neighboring nodes
		List<Vertex> temp = graph.getAdjacentVertex(new Vertex("A", "A"));
		System.out.println("Here are neighboring nodes of A: ");
		for(Vertex ver : temp) {
			System.out.println(ver);
		}
		
	}

	/**
	 * @return the isDirected
	 */
	public boolean isDirected() {
		return isDirected;
	}

	/**
	 * @param isDirected the isDirected to set
	 */
	public void setDirected(boolean isDirected) {
		this.isDirected = isDirected;
	}
	
}
