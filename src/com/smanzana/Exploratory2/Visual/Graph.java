package com.smanzana.Exploratory2.Visual;

import java.util.HashSet;
import java.util.Set;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultDirectedGraph;

/**
 * In-house methods for creating and manipulating a graph
 * @author Skyler
 *
 */
public abstract class Graph {
	
	/**
	 * Personal graph to be manipulated
	 */
	protected DefaultDirectedGraph<String, DefaultEdge> graph;
	
	protected int nodeCount;
	
	protected Set<ClassVertex> vertices;
	
	/**
	 * Basic constructor that creates a graph and nothing else
	 */
	public Graph() {
		graph = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
		nodeCount = 0;
		vertices = new HashSet<ClassVertex>();
	}
	
	/**
	 * Adds the passed vertex to the underlying graph.
	 * @param obj The vertex to add
	 */
	public void addClass(ClassVertex obj) {
		if (graph.addVertex(obj.getName())) {
			obj.setX(getNodeCount() * 100 + 20);
			obj.setY(50);
			nodeCount++;
			vertices.add(obj);
		}
	}
	
	/**
	 * Creates an edge in the underlying graph between the two passed vertices.
	 * @param parent
	 * @param child
	 */
	public void addEdge(ClassVertex parent, ClassVertex child) {
		graph.addEdge(parent.getName(), child.getName());
	}
	
	/**
	 * Adds a relationship to the graph.<br />
	 * This method should combine the addClass and addEdge methods to make adding a relationship one
	 * easy step.<br />
	 * Implementing subclasses should keep in mind that vertices might have to be added to the graph
	 * before trying to create the edge.
	 * @param parent
	 * @param child
	 */
	public abstract void addRelationship(ClassVertex parent, ClassVertex child);
	
	public org.jgrapht.Graph<String, DefaultEdge> getGraph() {
		return this.graph;
	}
	
	public int getNodeCount() {
		return this.nodeCount;
	}
	
	public Set<ClassVertex> vertexSet() {
		return this.vertices;
	}
	
	public ClassVertex getVertex(String name) {
		
		for (ClassVertex v : vertices) {
			if (v.getName().equals(name)) {
				return v;
			}
		}
		
		return null;
	}
	
	/**
	 * Spreads all children of the passed vertex out
	 * @param vertex
	 */
	protected void fanChildren(ClassVertex vertex) {
		
		if (graph.outgoingEdgesOf(vertex.getName()).isEmpty()) {
			return;
		}
		
		Set<ClassVertex> children = new HashSet<ClassVertex>();
		
		for (DefaultEdge edge : graph.outgoingEdgesOf(vertex.getName())) {
			children.add(getVertex(graph.getEdgeTarget(edge)));
		}
		
		if (children.isEmpty()) {
			return;
		}
		
		int offset = vertex.getX() + ((vertex.getChildrenCount() - 1) * -50);
		int index = 0;
		
		for (ClassVertex child : children) {
			child.setX(offset + (index * 100));
			child.setY(vertex.getY() + 60);
			index++;
			
			fanChildren(child);
		}
	}
	
}
