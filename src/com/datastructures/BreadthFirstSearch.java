package com.datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * Breadth first search
 * @author skanniah
 *
 */
public class BreadthFirstSearch implements Search {
	private List<Boolean> marked;
	private List<Integer> edgeTo;
	private final Integer s;
	
	public BreadthFirstSearch(Graph g, Integer s) {
		marked = new ArrayList<Boolean>(g.getNumOfVertices());
		edgeTo = new ArrayList<Integer>(g.getNumOfVertices());
		this.s = s;
		for (int i = 0; i < g.getNumOfVertices(); i++) {
			marked.add(false);
			edgeTo.add(null);
		}
		bfs(g, s);
	}
	
	private void bfs(Graph g, Integer s) {
		Queue<Integer> q = new PriorityQueue<Integer>();
		Integer v = s;
		marked.set(v, true);
		while (v != null) {
			for (Integer i: g.getAdjacentVertices(v)) {
				if (!marked.get(i)) {
					marked.set(i, true);
					edgeTo.set(i, v);
					q.add(i);
				}
			}
			v = q.poll();
		}
	}
	
	@Override
	public boolean hasPathTo(int u) {
		return marked.get(u);
	}
	
	@Override
	public String getPathTo(int u) {
		StringBuilder pathString = new StringBuilder();
		if (hasPathTo(u)) {
			for(int i = u; i != this.s; i = edgeTo.get(i)) {
				pathString.append(i).append("-");
			}
			pathString.append(s);
		}
		return pathString.reverse().toString();
	}

	@Override
	public Integer getSourceVertex() {
		return s;
	}
}
