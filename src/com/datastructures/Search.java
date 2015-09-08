package com.datastructures;


public interface Search {
	public boolean hasPathTo(int u);
	public String getPathTo(int u);
	public Integer getSourceVertex();
}