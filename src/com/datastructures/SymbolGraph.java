package com.datastructures;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

public class SymbolGraph {
	private int numOfVertices = 0;
	private int numOfEdges = 0;
	private Map<String, Set<String>> g;
	
	public SymbolGraph(int size) {
		this.numOfVertices = size;
		g = new HashMap<String, Set<String>>(size);
	}
	
	/**
	 * Adds an edge to the graph
	 * @param u
	 * @param v
	 */
	public void addEdge(String u, String v) {
		if (addVertex(u, v) && addVertex(v, u)) numOfEdges++;
	}
	
	/**
	 * Deletes an edge from the graph
	 * @param u
	 * @param v
	 */
	public void deleteEdge(String u, String v) {
		if (deleteVertex(u, v) && deleteVertex(v, u)) numOfEdges--;
	}
	
	/**
	 * Returns the degree of a given vertex
	 * @param u
	 * @return
	 */
	public int degree(String u) {
		return g.get(u).size();
	}
	
	/**
	 * Returns adjacent vertices of a given vertex
	 * @param u
	 * @return
	 */
	public Set<String> getAdjacentVertices(String u) {
		return g.get(u);
	}
	
	/**
	 * Returns number of vertices
	 * @return a number
	 */
	public int getNumOfVertices() {
		return numOfVertices;
	}

	/**
	 * Returns number of edges in the graph
	 * @return a number
	 */
	public int getNumOfEdges() {
		return numOfEdges;
	}

	/**
	 * Adds vertex to a graph
	 * @param u
	 * @param v
	 * @return boolean
	 */
	private boolean addVertex(String u, String v) {
		if (!g.containsKey(u)) {
			Set<String> l = new HashSet<String>();
			g.put(u, l);
			return l.add(v);
		}
		return g.get(u).add(v);
	}
	
	/**
	 * Adds vertex to a graph
	 * @param u
	 * @param v
	 */
	private boolean deleteVertex(String u, String v) {
		return g.containsKey(u) && null != g.get(u) && g.get(u).remove(v);
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (String k: g.keySet()) {
			s.append(k + " = " + g.get(k) + "\n");
		}
		return s.toString();
	}
	
	public static void main(String[] args) {
		SymbolGraph g = new SymbolGraph(4);
		g.addEdge("A", "B");
		g.addEdge("A", "C");
		g.addEdge("B", "D");
		g.addEdge("D", "C");
		
		System.out.println(g);
	}
}
