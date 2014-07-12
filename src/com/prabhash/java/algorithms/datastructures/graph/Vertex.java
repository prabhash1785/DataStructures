package com.prabhash.java.algorithms.datastructures.graph;

/*
 * This class represents Vertex object in a Graph.
 * @author Prabhash Rathore
 * 
 */
public class Vertex {
	
	private String id;
	private String name;
	
	public Vertex() {
		this.id = null;
		this.name = null;
	}
	
	public Vertex(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		int hashValue = 3;
		final int primeFactor = 23;
		hashValue = hashValue * primeFactor + this.id.hashCode() + this.name.hashCode();
		return hashValue;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean flag = false;
		if((obj == null) || (obj.getClass() != this.getClass())) {
			return flag;
		} else {
			Vertex v = (Vertex) obj;
			if((v.getId() == this.getId()) && (v.getName() == this.getName())) {
				flag = true;
			}
		}		
		
		return flag;
	}
	
	@Override
	public String toString() {
		return name;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
