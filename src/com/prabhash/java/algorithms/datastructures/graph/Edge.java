package com.prabhash.java.algorithms.datastructures.graph;

/*
 * This class represents Edges in a Graph.
 * @author Prabhash Rathore
 *  
 */

public class Edge {
	
	private String id;
	private Vertex start;
	private Vertex end;
	private int weight;
	
	public Edge() {
		this.id = null;
		this.start = null;
		this.end = null;
		this.weight = 0;
	}
	
	public Edge(String id, Vertex start, Vertex end, int weight) {
		this.id = id;
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean flag = false;
		if((obj == null) || (obj.getClass() != this.getClass())) {
			return flag;
		} else {
			Edge e = (Edge) obj;
			if((e.start.equals(this.start)) && (e.end.equals(this.end))) {
				flag = true;
			}
		}	
		
		return flag;
		
	}
	
	@Override
	public int hashCode() {
		int hash = 3;
		final int prime = 23;
		hash = hash * prime + this.start.hashCode() + this.end.hashCode();		
		return hash;
	}
	
	@Override
	public String toString() {
		return start + " <-> " + end;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the start
	 */
	public Vertex getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(Vertex start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public Vertex getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(Vertex end) {
		this.end = end;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

}
