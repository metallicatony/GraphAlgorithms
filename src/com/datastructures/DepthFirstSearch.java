package com.datastructures;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearch implements Search {
	private List<Boolean> marked;
	private List<Integer> edgeTo;
	private final int s;
	
	public DepthFirstSearch(Graph g, int s) {
		marked = new ArrayList<Boolean>(g.getNumOfVertices());
		edgeTo = new ArrayList<Integer>(g.getNumOfVertices());
		for (int i = 0; i < g.getNumOfVertices(); i++) {
			marked.add(false);
			edgeTo.add(null);
		}
		this.s = s;
		dfs(g, s);
	}
	
	private void dfs(Graph g, int u) {
		marked.set(u, true);
		for (int i: g.getAdjacentVertices(u)) {
			if (!marked.get(i)) {
				edgeTo.set(i, u);
				dfs(g, i);
			}
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
