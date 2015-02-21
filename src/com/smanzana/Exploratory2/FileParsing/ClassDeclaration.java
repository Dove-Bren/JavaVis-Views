package com.smanzana.Exploratory2.FileParsing;

import java.util.HashSet;
import java.util.Set;


/**
 * Represent a class declaration.<br />
 * make this pretty later:<br />
 * public class [name] {extends class} {implements class, class}
 * @author Skyler
 * 
 */
public class ClassDeclaration {
	
	
	private String declarationLine;
	
	private String name;
	
	private String extendString;
	
	private Set<String> implementStrings;
	
	public ClassDeclaration(String declaration) {
		this.declarationLine = declaration.trim();
		
		if (declarationLine.isEmpty()) {
			return;
		}
		
		String[] tokens = declarationLine.split(" ");
		int i = 0;
		
		name = null;
		extendString = null;
		implementStrings = new HashSet<String>();
		
		while (i < tokens.length) {
			//ignore all modifiers before class keyword
			if (isModifier(tokens[i])) {
				i++;
				continue;
			}
			
			//tokens[i] is not a modifier. Hopefully it's class!
			if (tokens[i].equals("class") || tokens[i].equals("interface") || tokens[i].equals("enum")) {
				this.name = tokens[i + 1];
			}
			else {
				System.out.println("Failure of assumption [class]: " + tokens[i]);
				return;
			}
			
			i = i + 2;
			break;
		}
		
		if (name == null) {
			//no name was found, exhausted file
			System.out.println("Unable to find class name in declaration!");
			return;
		}
		
		//next is extends or implements. Either can be first
		while (i < tokens.length) {
			if (tokens[i].equals("extends")) {
				//next token is what it extends!
				this.extendString =tokens[i + 1];
				i = i+2;
				continue;
			}
			
			if (tokens[i].equals("implements")) {
				//next token is a class. If that token has a comma, expect another class
				do {
					i++;
					implementStrings.add(tokens[i].replace("," , " ").trim());
				} while (tokens[i].contains(","));
			}
			i++;
		}
		
		
	}
	
	public static boolean isModifier(String str) {
		switch (str) {
		case "public":
		case "private":
		case "abstract":
		case "final":
		case "static":
		case "volatile":
			return true;
		default:
			return false;
		}
	}
	
	public String getClassName() {
		return name;
	}
	
	public String getExtends() {
		return extendString;
	}
	
	public Set<String> getImpements() {
		return implementStrings;
	}
}
