package com.smanzana.Exploratory2.Graph;

import com.smanzana.Exploratory2.Driver;

public class GraphNode {
	
	private String name;
	
	/**
	 * A unique id.<br />
	 * This is going to be <i>parentPackage</i>.<i>name</i><br />
	 * Where <i>parentPackage</i> is the name of the domain exactly <i>one</i> level up.
	 */
	private String uniqueKey; 
	
	
	public GraphNode(String name) {
		this.name = name;
		
		this.uniqueKey = (Driver.class.getPackage().getName().substring(Driver.class.getPackage().getName().lastIndexOf(".") + 1)) + "." + this.name;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @return the uniqueKey
	 */
	public String getUniqueKey() {
		return uniqueKey;
	}
	
	
	
}
