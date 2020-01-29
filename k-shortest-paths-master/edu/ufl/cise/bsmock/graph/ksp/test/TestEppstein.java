package edu.ufl.cise.bsmock.graph.ksp.test;

import edu.ufl.cise.bsmock.graph.Graph;
import edu.ufl.cise.bsmock.graph.ksp.Eppstein;
import edu.ufl.cise.bsmock.graph.util.Path;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Scanner;

/**
 * Test of Eppstein's algorithm for computing the K shortest paths between two
 * nodes in a graph.
 *
 * Copyright (C) 2015 Brandon Smock (dr.brandon.smock@gmail.com, GitHub: bsmock)
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * Created by Brandon Smock on October 7, 2015. Last updated by Brandon Smock on
 * December 24, 2015.
 */
public class TestEppstein {
	public static String OUTPUTFILE;

	public static void main(String args[]) throws IOException {
		/* Uncomment any of these example tests */
		String graphFilename = null, sourceNode, targetNode;
		int K;

		/* Example 1 */
		// graphFilename = "edu/ufl/cise/bsmock/graph/ksp/test/tiny_graph_01.txt";
		// sourceNode = "1";
		// targetNode = "10";
		// K = 10;

		/* Example 2 */
		// graphFilename = "edu/ufl/cise/bsmock/graph/ksp/test/tiny_graph_02.txt";
		// sourceNode = "1";
		// targetNode = "9";
		// K = 100;

		/* Example 3 */
		// graphFilename =
		// "edu/ufl/cise/bsmock/graph/ksp/test/small_road_network_01.txt";
		// sourceNode = "5524";
		// targetNode = "7239";
		// K = 5;
		Scanner sc  = new Scanner(System.in);
		System.out.println("please input Unit or Euclidean");
		String line3 = sc.nextLine().toUpperCase().trim();
		if(line3.equals("UNIT")) {
			graphFilename = "edu/ufl/cise/bsmock/graph/ksp/test/test1.txt";
			OUTPUTFILE="output-Eppstein-Unit.txt";
		}else if(line3.equals("EUCLIDEAN")){
			graphFilename = "edu/ufl/cise/bsmock/graph/ksp/test/test.txt";
			OUTPUTFILE="output-Eppstein-Euclidean.txt";
		}
		 
		 System.out.println("please input original city:(like: Paris, Santa_Barbara...)");
		 
		 String line =sc.nextLine();
		 sourceNode = line;
		 System.out.println("please input destination city:(like: Shanghai, New_York...)");
		 String line2 =sc.nextLine();
		 targetNode = line2;
		 K = 10;
//		graphFilename = "edu/ufl/cise/bsmock/graph/ksp/test/test.txt";
//		sourceNode = "Abilene";
//		targetNode = "Taipei";
//		K = 10;

		usageExample1(graphFilename, sourceNode, targetNode, K);
	}

	public static void usageExample1(String graphFilename, String source, String target, int k) throws IOException {
		/* Read graph from file */
		System.out.print("\n"+"Reading data from file... ");
		wirte("edu/ufl/cise/bsmock/graph/ksp/test/"+OUTPUTFILE, "Reading data from file... ");
		Graph graph = new Graph(graphFilename);
		wirte("edu/ufl/cise/bsmock/graph/ksp/test"+OUTPUTFILE, " "+"complete.");
		System.out.println("complete.");

		/* Compute the K shortest paths and record the completion time */
		System.out.print("Computing the " + k + " shortest paths from [" + source + "] to [" + target + "] ");
		System.out.print("using Eppstein's algorithm... ");
		wirte("edu/ufl/cise/bsmock/graph/ksp/test"+OUTPUTFILE, "\n"+"Computing the " + k + " shortest paths from [" + source + "] to [" + target + "] "+"using Eppstein's algorithm... ");
		List<Path> ksp;
		long timeStart = System.currentTimeMillis();
		Eppstein eppsteinAlgorithm = new Eppstein();
		ksp = eppsteinAlgorithm.ksp(graph, source, target, k);
		long timeFinish = System.currentTimeMillis();
		System.out.println("complete.");
		wirte("edu/ufl/cise/bsmock/graph/ksp/test"+OUTPUTFILE, " "+"complete.");

		System.out.println("Operation took " + (timeFinish - timeStart) / 1000.0 + " seconds.");
		wirte("edu/ufl/cise/bsmock/graph/ksp/test"+OUTPUTFILE, "\n"+"Operation took " + (timeFinish - timeStart) / 1000.0 + " seconds.");
		/* Output the K shortest paths */
		System.out.println("k) cost: [path]");
		wirte("edu/ufl/cise/bsmock/graph/ksp/test"+OUTPUTFILE, "\n"+"k) cost: [path]");
		int n = 0;
		for (Path p : ksp) {
			System.out.println(++n + ") " + p);
			wirte("edu/ufl/cise/bsmock/graph/ksp/test"+OUTPUTFILE, "\n"+n++ + ") " + p);
		}
	}
	public  static void wirte(String filename,String content) throws IOException {
		FileWriter writer = new FileWriter(filename, true);  
		   writer.write(content);   
           writer.close();   
	}
}
