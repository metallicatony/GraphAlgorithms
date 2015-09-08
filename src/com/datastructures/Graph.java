package com.datastructures;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A Graph data structure whose vertices are simple integers
 * @author skanniah
 *
 */
public class Graph {
	private int vertices;
	private int edges;
	private List<List<Integer>> adj;
	
	public Graph() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter the number of vertices: ");
		vertices = Integer.parseInt(br.readLine());
		adj = new ArrayList<List<Integer>>(vertices);
		for (int i = 0; i < vertices; i++) {
			adj.add(new ArrayList<Integer>());
		}

		System.out.print("Enter the number of edges: ");
		int e = Integer.parseInt(br.readLine());

		for (int i = 0; i < e; i++) {
			System.out.print("Enter the pair of vertices separated by comma: ");
			String[] connectedVertices = br.readLine().split("\\s*,\\s*");
			validateInputEdgeVertices(Integer.parseInt(connectedVertices[0]), Integer.parseInt(connectedVertices[1]));
			addEdge(Integer.parseInt(connectedVertices[0]), Integer.parseInt(connectedVertices[1]));
		}
	}
	
	/**
	 * Number of vertices in graph
	 * @return
	 */
	public int getNumOfVertices() {
		return vertices;
	}
	
	/**
	 * Number of edges in graph
	 * @return
	 */
	public int getNumOfEdges() {
		return edges;
	}
	
	/**
	 * Adds u-v edge
	 * @param u
	 * @param v
	 */
	private void addEdge(int u, int v) {
		addVertexAsAdjacent(u, v);
		addVertexAsAdjacent(v, u);
		edges++;
	}
	
	/**
	 * Deletes u-v edge
	 * @param u
	 * @param v
	 */
	private void deleteEdge(int u, int v) {
		removeVertexAsAdjacent(u, v);
		removeVertexAsAdjacent(v, u);
	}
	
	/**
	 * Returns a list of vertices that are adjacent to a source vertex
	 * @param u the source vertex
	 * @return a list of vertices
	 */
	public List<Integer> getAdjacentVertices(int u) {
		return adj.get(u);
	}
	
	/**
	 * Returns the degree of a given vertex
	 * @param adjList
	 * @return
	 */
	private int degree(List<Integer> adjList) {
		return adjList.size();
	}
	
	private void validateInputEdgeVertices(int u, int v) throws Exception {
		if (u >= vertices || v >= vertices) {
			throw new Exception("invalid vertices=[" + u + ", " + v + "]");
		}
	}
	
	private void addVertexAsAdjacent(int u, int v) {
		List<Integer> adjVertices = adj.get(u);
		adjVertices.add(v);
	}
	
	private void removeVertexAsAdjacent(int u, int v) {
		List<Integer> adjVertices = adj.get(u);
		adjVertices.remove(v);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		System.out.print("Graph as adjacency list: ");
		for (int i = 0; i < vertices; i++) {
			str = str.append("[" + i + "] = " + adj.get(i) + ", degree = [" + degree(adj.get(i)) + "]"+ "\n");
		}
		return str.toString();
	}
	
	public static void main(String[] args) throws Exception {
		Graph g = new Graph();
		System.out.println(g.toString());
		
		int s = 0;
		Search d = new DepthFirstSearch(g, s);
		System.out.println("# Depth First Search #");
		printPaths(g, d);
		
		Search b = new BreadthFirstSearch(g, s);
		System.out.println("\n# Breadth First Search #");
		printPaths(g, b);
	}
	
	public static void printPaths(Graph g, Search s) {
		for (int i = 0; i < g.getNumOfVertices(); i++) {
			System.out.println("Path from " + s.getSourceVertex() + " to " +  i + ": " + s.getPathTo(i));
		}
	}
	
}
