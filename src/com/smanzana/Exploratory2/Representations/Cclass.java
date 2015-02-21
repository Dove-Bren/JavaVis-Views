package com.smanzana.Exploratory2.Representations;

import java.util.LinkedList;
import java.util.List;

import com.smanzana.Exploratory2.FileParsing.ClassDeclaration;

/**
 * Represents a class lulz 
 * @author Skyler
 *
 */
public class Cclass {
	
	
	private String name;
	
	private String packageName;
	
	private List<Method> methods;
	
	private List<Import> imports;
	
	private ClassDeclaration declaration;
	
	public Cclass(String name, ClassDeclaration decl, String packageName, List<Method> methods) {
		
		this.declaration = decl;
		this.name = name;
		this.packageName = packageName;
		
		this.methods = methods;
		
		if (methods == null) {
			methods = new LinkedList<Method>();
		}
				
	}
	
	public Cclass (ClassDeclaration decl, String packageName, List<Method> methods) {
		this(decl.getClassName(), decl, packageName, methods);
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
	
	
	
	/**
	 * @return the imports
	 */
	public List<Import> getImports() {
		return imports;
	}

	/**
	 * @param imports the imports to set
	 */
	public void setImports(List<Import> imports) {
		this.imports = imports;
	}

	/**
	 * Uses the passed class name (one that is not prefixed by the package) to return the
	 * import statement used to import the class's reference
	 * @param localName
	 * @return
	 */
	public Import getImport(String localName) {
		
	}
	
	
	
	/**
	 * Prints out detailed information about the class.<br />
	 * In many ways, this is toString++
	 * @return
	 */
	public String info() {

		String out =  "[" + this.packageName + "." + this.name +"]:\n";
		if (declaration != null && declaration.getExtends() != null) {
			out += "Extends: " + declaration.getExtends() + "\n";
		}
		if (declaration != null && declaration.getImplements() != null && !declaration.getImplements().isEmpty()) {
			out += "Implements: " + declaration.getImplements() + "\n";
		}
		for (Method m : methods) {
			out += "  " + m.toString() + "\n";
		}
		
		
		return out;
	}
	
	@Override
	public String toString() {
		return this.packageName + "." + this.name;
	}
	
	@Override
	public boolean equals(Object o) {
		return toString().equals(o.toString());
	}
	
}
