package com.smanzana.Exploratory2.Visual;

public class ClassVertex implements Vertex {

	private String name;
	
	private int x;
	
	private int y;
	
	private int childrenCount;
	
	public ClassVertex(String name) {
		this.name = name;
		x = 0;
		y = 0;
		childrenCount = 0;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void incrementChildren() {
		this.childrenCount ++;
	}
	
	public int getChildrenCount() {
		return this.childrenCount;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		System.out.println("Comparing *" + o.toString() + "*");
		System.out.println("to *" + this.toString() + "*");
		if (o.toString().equalsIgnoreCase(this.toString())) {
			return true;
		}
		return false;
	}
}
