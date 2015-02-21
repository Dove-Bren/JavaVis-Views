package com.smanzana.Exploratory2.Graph;

public class DirectedWeightedEdge extends UndirectedWeightedEdge{
	
	//DEFINE LEFT: SOURCE
	//RIGHT: DEST
	public GraphNode getSource() {
		return this.ends.getLeft();
	}
	
	public GraphNode getDestination() {
		return this.ends.getRight();
	}
	
}
