package com.smanzana.Exploratory2;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JFrame;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;

import com.smanzana.Exploratory2.FileParsing.ClassDeclaration;
import com.smanzana.Exploratory2.FileParsing.FileParser;
import com.smanzana.Exploratory2.Representations.Cclass;
import com.smanzana.Exploratory2.Representations.Method;
import com.smanzana.Exploratory2.Visual.ClassVertex;
import com.smanzana.Exploratory2.Visual.Graph;
import com.smanzana.Exploratory2.Visual.ParentGraph;

/**
 * This class is the driver for the visualization. 
 * @author Skyler
 *
 */
public final class Driver {

	public static FileParser fileP;
	
	public static Graph eGraph, iGraph;
	
	public static JGraphModelAdapter<String, DefaultEdge> eAdapter, iAdapter;
	
	public static JFrame eFrame, iFrame;

	
//	@Override
//	public void init() {
//		String[] s = new String[1];
//		s[0] = "src";
//		
//		fileP = new FileParser();
//		graph = new ParentGraph();
//        
//        resize(640, 480);
        
//		Driver.main(s);
//	}
//	
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
		
		Scanner input = null;
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		fileP = new FileParser();

// 		eGraph = new ParentGraph();
// 		iGraph = new ParentGraph();
		
		processFile(file);
		
//		eFrame = new JFrame("Extend Graph");
//        eFrame.setSize(640, 480);
//        eFrame.addWindowListener(new WindowAdapter() {
//
//			public void windowClosing(WindowEvent e) {
//				System.exit(0);
//			}
//		});
//
//		iFrame = new JFrame("Implement Graph");
//        iFrame.setSize(640, 480);
//        iFrame.addWindowListener(new WindowAdapter() {
//
//			public void windowClosing(WindowEvent e) {
//				System.exit(0);
//			}
//		});
//        
//
//    	
//    	JGraph jgraphi, jgraphe;
//
//		eAdapter = new JGraphModelAdapter<String, DefaultEdge>(eGraph.getGraph());
//        jgraphe = new JGraph( eAdapter );
//        eFrame.getContentPane().add( jgraphe );
//        
//        iAdapter = new JGraphModelAdapter<String, DefaultEdge>(iGraph.getGraph());
//        jgraphi = new JGraph( iAdapter );
//        iFrame.getContentPane().add( jgraphi );
//        
//        
//        for (ClassVertex vertex : eGraph.vertexSet()) {
//        	setVertexPosition(eAdapter, vertex);
//		}
//        
//        for (ClassVertex vertex : iGraph.vertexSet()) {
//        	setVertexPosition(iAdapter, vertex);
//        }
//
//        eFrame.setVisible(true);
//        iFrame.setVisible(true);
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

//			
			Cclass cl = fileP.getcClass();
			
			for (Method m : cl.getMethods()) {
				System.out.println("" + m.getName() + " :");
				for (String s : m.getCodeLines()) {
					System.out.print(s);
				}
				System.out.println("");
			}
//			System.out.println("Class found: " + decl.getClassName());
//			if (decl.getExtends() != null)
//			System.out.println("  Class extends: " + decl.getExtends());
//			if (!decl.getImpements().isEmpty()) {
//				System.out.println("  Class implements: " + decl.getImpements());
//			}
//			ClassVertex eVertex =  new ClassVertex(decl.getClassName());
//			ClassVertex iVertex =  new ClassVertex(decl.getClassName());
//			//us.setX(graph.getNodeCount() * 100 + 20);
//			//us.setY(50);
//			//, us.getX(), 50
//			
//			if (decl.getExtends() != null) {
//				eGraph.addRelationship(new ClassVertex(decl.getExtends()), eVertex);
//				//us.setY(us.getY() + 50);
//			} else {
//				eGraph.addClass(eVertex);
//			}
//			
//			if (!decl.getImpements().isEmpty()) {
//				for (String inter : decl.getImpements()) {
//					iGraph.addRelationship(new ClassVertex(inter), iVertex);
//				}
//			} else {
//				iGraph.addClass(iVertex);
//			}
			
		}
	}
	
	private static void setVertexPosition(JGraphModelAdapter<String, DefaultEdge> adapter, ClassVertex vertex) {
        DefaultGraphCell cell = adapter.getVertexCell( vertex.getName() );
        Map attr = cell.getAttributes(  );
        Rectangle2D b    = GraphConstants.getBounds(attr);
        b.setRect(vertex.getX(), vertex.getY(), b.getWidth(), b.getHeight());
        GraphConstants.setBounds( attr, b);

        Map cellAttr = new HashMap(  );
        cellAttr.put( cell, attr );
        adapter.edit( cellAttr, null, null, null);
	}
		
		
//
//	    GraphLayoutCache cache = jgraph.getGraphLayoutCache();
//		for (Object item : jgraph.getRoots()) {
//		    GraphCell cell = (GraphCell) item;
//		    CellView view = cache.getMapping(cell, true);
//		    Rectangle2D bounds = view.getBounds();
//		    bounds.setRect(x, y, bounds.getWidth(), bounds.getHeight());
//		    
//		    }
//		

}
