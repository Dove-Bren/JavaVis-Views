package com.smanzana.Exploratory2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import com.smanzana.Exploratory2.FileParsing.FileParser;
import com.smanzana.Exploratory2.Representations.Cclass;

/**
 * This class is the driver for the visualization. 
 * @author Skyler
 *
 */
public final class Driver {

	public static FileParser fileP;
	
	public static Set<Cclass> classes;
	
	
	public static void main(String[] args) {
				
		
		if (args.length == 0) {
			printUsage();
			return;
		}
		
		File file = new File(args[0]);
		
		if (!file.exists()) {
			System.out.println("Unable to find the passed file: " + file.getAbsolutePath());
			return;
		}
		fileP = new FileParser();
		classes = new HashSet<Cclass>();
	
		processFile(file);
		
		System.out.println("Got a total of: " + classes.size() + " classes!");
		
		for (Cclass c : classes) {
			System.out.println(c);
		}
		
	}
	
	
	
	private static void printUsage() {
		System.out.println("Usage:");
		System.out.println("java -jar [jar name] [dir/file]");
	}
	
	private static void processFile(File file) {
		if (file == null || !file.exists()) {
			System.err.println("Invalid file selection in Driver class: " + file);
			return;
		}
		
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				processFile(f);
			}
		} else if (file.getName().endsWith(".java")) {
			//we have an actual file. Process is
			try {
				fileP.setFile(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return;
			}
	
			Cclass cl = fileP.getcClass();
			classes.add(cl);

			
		}
	}


}
