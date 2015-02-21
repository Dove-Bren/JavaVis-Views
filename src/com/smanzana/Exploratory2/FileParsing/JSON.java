package com.smanzana.Exploratory2.FileParsing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.smanzana.Exploratory2.Tree.Tree;

public final class JSON {
	
	public static void toJSON(File outfile, Tree root) {
		if (outfile == null || (outfile.exists() && outfile.isDirectory())) {
			System.err.println("Cannot save json to file, as it either is null or a directory!");
		}
		
		if (outfile.exists()) {
			System.out.println("Overwriting " + outfile.getAbsolutePath());
		}
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(outfile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		printTree(writer, root);
		
		writer.flush();
		
		writer.close();
		
		
	}
	
	private static void printTree(PrintWriter writer, Tree tree) {
		if (tree == null) {
			return;
		}
		writer.print("{ ");
		writer.print("\"id\": \"" + tree.getName() + "\", ");
		writer.print("\"name\": \"" + tree.getName() + "\", ");
		writer.print("\"data\": {}, ");
		writer.print("\"children\": [");
		
		//go through all children and print them too
		if (tree.getChildren() != null && !tree.getChildren().isEmpty()) {
			for (Tree child : tree.getChildren()) {
				printTree(writer, child);
			}
		}
		
		writer.print("]");
		writer.print("}");
	}
	
}
