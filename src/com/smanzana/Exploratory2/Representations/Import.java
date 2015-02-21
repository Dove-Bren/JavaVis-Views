package com.smanzana.Exploratory2.Representations;

public class Import {
	
	private String importSpec;
	
	private String packageName;

	
	public Import(String importLine) {
		
		importLine = importLine.trim();
		importLine = importLine.substring(8); //'#import ' is 8 chars
		if (importLine.isEmpty()) {
			importSpec = null;
			packageName = null;
		}
		//#import org.blah.bluh.*; or org.blah.bluh.class
		int finalIndex = importLine.lastIndexOf(".");
		
		packageName = importLine.substring(0, finalIndex - 1);
		importSpec = importLine.substring(finalIndex + 1);
	}
	
	
	/**
	 * @return the importSpec
	 */
	public String getImportSpec() {
		return importSpec;
	}

	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}
	
	
	
	
	
	
}
