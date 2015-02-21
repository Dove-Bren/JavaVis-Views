package com.smanzana.Exploratory2;

import java.util.LinkedList;
import java.util.List;

import com.smanzana.Exploratory2.Parse.ClassDeclaration;

/**
 * Represents a class lulz 
 * @author Skyler
 *
 */
public class Cclass {
	
	
	private String name;
	
	private String packageName;
	
	private List<Method> methods;
	
	private ClassDeclaration declaration;
	
	public Cclass(String name, ClassDeclaration decl, String packageName, List<Method> methods) {
		
		this.declaration = decl;
		this.name = name;
		this.packageName = name;
		
		this.methods = methods;
		
		if (methods == null) {
			methods = new LinkedList<Method>();
		}
				
	}
	
	public Cclass(String name, String packageName, List<Method> methods) {
		this(name, null, packageName, methods);
	}
	
	public Cclass(String name) {
		this(name, null, null, null);
	}
	
	public Cclass(ClassDeclaration decl) {
		this(decl.getClassName(), decl, null, null);
	}
	
	
	public void setDeclaration(ClassDeclaration decl) {
		this.declaration = decl;
	}
	
	public ClassDeclaration getDeclaration() {
		return this.declaration;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPackageName() {
		return this.packageName;
	}
	
	public List<Method> getMethods() {
		return this.methods;
	}
	
	public void addMethod(Method meth) {
		//not even once, kids
		methods.add(meth);
	}
	
}