package com.smanzana.Exploratory2.FileParsing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.smanzana.Exploratory2.Representations.Cclass;
import com.smanzana.Exploratory2.Representations.Import;
import com.smanzana.Exploratory2.Representations.Method;

/**
 * Wraps around a file and grabs key lines for analysis
 * @author Skyler
 * @bugs TODO Ignores completely any method following a class field declaration!!!!!!!!!!
 */
public class FileParser {

	
	/**
	 * Scanner object used for parsing
	 */
	private Scanner input;

	/**
	 * private reference to the file to parse
	 */
	private File file;
	
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
		
		//reset input
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
		
		if (!input.hasNextLine()) {
			System.err.println("Error when reading in file! File is empty!");
			return null;
		}
		
		Cclass cl = null;
		ClassDeclaration decl;
		List<Import> imports = new LinkedList<Import>();
		String packageName;
		String line = "";
		//assume first line is package declaration
		packageName = input.nextLine().substring(8); //'package ' is 8 chars
		packageName = packageName.substring(0, packageName.length() - 1);
		
		while (input.hasNext()) {
			line = input.nextLine().trim();
			if (line.isEmpty() || line.startsWith("/") || line.startsWith("*")) {
				continue;
			}
			
			//we're looking for imports, so as soon as we don't have one break and start next process
			if (!line.startsWith("import ")) {
				break;
			}
			
			//starts with import
			imports.add(new Import(line));
		}
		
		//SUPER IMPORTANT PROGRAMMING NOTE LOGIC OMG ODNT FORGET
		//line still has the last line. This should be the class declaration if our assumptions are correct
		if (!(  (line.contains(" class ") || line.contains(" interface ") || line.contains(" enum "))  && !line.startsWith("*") && !line.startsWith("/"))) {
			System.err.println("Error in assumption that after imports is the declaration!");
			return null;		
		}
		
		decl = new ClassDeclaration(line);
		List<Method> methods = new LinkedList<Method>();
		Pattern delim = input.delimiter();

		input.useDelimiter(Pattern.compile("[ \\t\\n\\x0B\\f\\r]"));
		//not we find all the methods. We ignore static init code
		while (input.hasNext()) {
			input.useDelimiter(Pattern.compile("[ \\t\\r]"));
			line = input.next().trim();
			
			//skip over comments
			if (line.startsWith("//")) {
				//skip line
				input.nextLine();
				continue;
			}
			if (line.startsWith("/*")) {
				while (input.hasNext() && !input.next().contains("*/")) {
					;
				}
				continue;
			}
			
			
			//if (line.isEmpty() || line.startsWith("/") || line.startsWith("*") || line.startsWith("{"))
			//we ONLY care about method declarations
			if (line.startsWith("public") || line.startsWith("private") || line.startsWith("protected")) {
				
				//extreme psuedo-compiler hardcore reading
				//if we see ", ignore EVERYTHING until we see another char by char
				//count {'s and }'s
				
				
				
				//super important: is this a field or a method?
								
				int braceCount = 0;
				line = input.next();
				while (ClassDeclaration.isModifier(line)) {
					
					line = input.next();
				}
				//after the loop, if the last line has a (, we're dealing with a constructor
				//magic, I know!
				if (line.contains("(")) {
					continue;
				}
				
				input.useDelimiter("\\)");
				String methName = input.next().trim() + ")";
				if (methName.contains(";") || methName.startsWith("(")) {
					continue;
				}
				
				if (methName.contains(" "))
				if (methName.substring(0, methName.indexOf(" ")).contains(">") && !methName.substring(0, methName.indexOf(" ")).contains("<")) {
					methName = methName.substring(methName.indexOf(">") + 1).trim();
				}
				
				String buildString = "";
				List<String> lines = new LinkedList<String>();
				
				input.useDelimiter("");
				
				//go to first bracket or semicolan
				char c = ';';
				while (input.hasNext()) {
					c = input.next().charAt(0);
					if (c == '{' || c == ';') {
						break;
					}
				}
				
				if (c == ';') {
					//it is a field!
					continue;
				}
				
				if (!input.hasNext()) {
					//we reached end of file without finding the first opening bracket!
					System.err.println("Encountered Severe error when looking for first opening brace!");
					return null;
				}
				
				braceCount++;
				
				buildString = "\t{";
				
				while (input.hasNext()) {
					char in = input.next().charAt(0);
					
					if (in == Character.LINE_SEPARATOR) {
						lines.add(buildString);
						buildString = "";
						continue;
					}
					
					buildString = buildString + in;
					
					if (in == '{') {
						braceCount++;
						continue;
					}
					if (in == '}') {
						braceCount--;
						
						if (braceCount == 0) {
							//end of method!
							if (!buildString.trim().isEmpty()) {
								lines.add(buildString);
							}
							break;
						}
						
						continue;
					}
					
					if (in == '"') {
						in = input.next().charAt(0);
						while (in != '"') {
							//do nothing but copy char! we don't care! just get to the end of the string!
							buildString += in;
							in = input.next().charAt(0);
						}
						buildString += '"';
					}
					
					
				}
				
				String[] sAr = new String[lines.size()];
				methods.add(new Method(methName, lines.toArray(sAr)));
				
				
				
				
			}
			

			input.useDelimiter(" ");
		}
		
		input.useDelimiter(delim);

		cl = new Cclass(decl, packageName, methods);
		return cl;
	}
	
}
