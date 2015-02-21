package com.smanzana.Exploratory2.FileParsing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.smanzana.Exploratory2.Representations.Cclass;

/**
 * Wraps around a file and grabs key lines for analysis
 * @author Skyler
 *
 */
public class FileParser {

	/**
	 * private reference to the file to parse
	 */
	private File file;
	
	/**
	 * Scanner object used for parsing
	 */
	private Scanner input;
	
	public FileParser() {
		this.input = null;
		this.file = null;
	}
	
	/**
	 * Creates a FileParser wrapped around the passed file.
	 * @param file
	 * @throws FileNotFoundException
	 */
	public FileParser(File file) throws FileNotFoundException {
		this();
		
		if (file == null) {
			System.err.println("FileParser unable to open null file!");
			return;
		}
		
		this.file = file;

		if (input != null) {
			input.close();
		}
		input = new Scanner(file);
	}
	
	/**
	 * Updates the parser to point at the new file.
	 * @param file
	 * @throws FileNotFoundException
	 */
	public void setFile(File file) throws FileNotFoundException {
		if (file == null) {
			System.err.println("FileParser unable to open null file!");
			return;
		}
		if (input != null) {
			input.close();
		}
		
		this.file = file;
		input = new Scanner(file);
	}
	
	/**
	 * Finds and returns the class declaration line of the previously-assigned file.<br />
	 * In order to find the declaration, this class makes some simple assumptions:<br />
	 * <ul><li>There is only one top-level class defined in each file</li>
	 * <li>The first class declaration is the declaration for the class that encompasses any internal classes</li>
	 * <li>Top-level classes are public, not private</li>
	 * </ul>
	 * @return The whole class declaration line
	 */
	public ClassDeclaration getDeclaration() {
		
		if (input == null) {
			return null;
		}
		
		String str = null;
		
		//set input to beginning of file
		try {
			if (input != null) {
				input.close();
			}
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.err.println("FileParser's file cannot be found:  " + file.getName());
			e.printStackTrace();
			return null;
		}
		
		while (input.hasNextLine()) {
			str = input.nextLine().trim();
			if (  (str.contains(" class ") || str.contains(" interface ") || str.contains(" enum "))  && !str.startsWith("*") && !str.startsWith("/")) {
				//start of a class definition. 
				//because we stop as soon as we find one, and we haven't stopped yet, this is the first
				//and under our assumptions, it's the one we want
				return new ClassDeclaration(str);
			}
		}
		return null;
	}
	
	public Cclass getcClass() {
		if (input == null) {
			return null;
		}
		
		Cclass cl = null;
		
		
		
		return cl;
	}
	
}
