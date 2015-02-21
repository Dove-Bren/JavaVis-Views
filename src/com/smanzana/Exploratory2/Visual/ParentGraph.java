package com.smanzana.Exploratory2.Visual;


public class ParentGraph extends Graph {

	
	/**
	 * Adds the passed relationship to the underlying graph.<br />
	 * Each vertex can only have one parent, so any existing parent to the passed child
	 * vertex will be erased!
	 */
	@Override
	public void addRelationship(ClassVertex parent, ClassVertex child) {

		ClassVertex real;
		real = getVertex(parent.getName());
		if (real != null) {
			parent = real;
		}
		
		real = getVertex(child.getName());
		if (real != null) {
			child = real;
		}
		
		/**
		 * first look at parent. does it exist?
		 * if yes, just add new child and create edge. Increment parent's child cound. Perform fan
		 * if no,
		 *   
		 *   
		 */
		
		if (graph.addVertex(parent.getName())) {
			System.out.println("new parent!");
			parent.setX(getNodeCount() * 100 + 20);
			parent.setY(50);
			//realParent = parent;
		} else {
			//check for special case, where both exist and we have a hole in the graph
			if (getVertex(parent.getName()) != null && getVertex(child.getName()) != null) {
				//this.nodeCount--; //should we bring it down? could cause problems if the hole is discovered
								  //until later
				
			}
			nodeCount--;
		}
		
		parent.incrementChildren();

		graph.addVertex(child.getName());
		
		vertices.add(parent);
		vertices.add(child);
		
		//removeEdge does not do anythign if edge doesn't exist. Thus no if-statements
		graph.removeEdge(parent.getName(), child.getName());
		
		graph.addEdge(parent.getName(), child.getName());
		
		if (parent != null) {
			fanChildren(parent);
		}
		
		nodeCount++;
		
	}

}
