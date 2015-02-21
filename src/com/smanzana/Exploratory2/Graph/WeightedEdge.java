package com.smanzana.Exploratory2.Graph;

import com.smanzana.Exploratory2.Util.Pair;

public class WeightedEdge {
	
	private double weight;
	
	private Pair<GraphNode, GraphNode> ends;
	
	public WeightedEdge(double weight, GraphNode end1, GraphNode end2) {
		this.weight = weight;
		this.ends = new Pair<GraphNode, GraphNode>(end1, end2);
	}
	
	public WeightedEdge(GraphNode end1, GraphNode end2) {
		this(1.0, end1, end2);
	}
	
	public WeightedEdge(double weight) {
		this(weight, null, null);
	}
	
	public WeightedEdge() {
		this(1.0);
	}
	
	public Pair<GraphNode, GraphNode> getEnds() {
		return this.ends;
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
}
