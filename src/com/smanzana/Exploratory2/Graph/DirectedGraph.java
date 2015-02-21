package com.smanzana.Exploratory2.Graph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Basic graph data structure.
 * @author Skyler
 *
 */
public class DirectedGraph extends Graph {
	
	private Set<DirectedGraphNode> nodes;
	
	public DirectedGraph() {
		nodes = new HashSet<DirectedGraphNode>();
	}

	public Set<DirectedGraphNode> getNodes() {
		return nodes;
	}
	
	public boolean addNode(DirectedGraphNode node) {
		return nodes.add(node);
	}
	
	//TODO get remove stuffs
	
}
