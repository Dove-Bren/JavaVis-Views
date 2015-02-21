package com.smanzana.Exploratory2.Util;

public class Pair<a, b> {
	
	private a left;
	
	private b right;
	
	public Pair(a valueA, b valueB) {
		left = valueA;
		right = valueB;
	}

	/**
	 * @return the left
	 */
	public a getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(a left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public b getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(b right) {
		this.right = right;
	}
	
	
}
