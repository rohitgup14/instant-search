package com.asterix.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import com.asterix.search.Node;
import com.asterix.search.Tree;

public class Run {

	Node root = new Node(' ');
	Tree tree = new Tree(root);



	public static void main(String[] args) {
		Run r = new Run();
		r.readFile();
		
		//r.test();
		
	}

	public void readFile() {
		System.out.println("************************Started Instant Search File Reading*****************");
		try{
			// Open the file that is the first 
			// command line parameter
			FileInputStream fstream = new FileInputStream("/Users/rgupta/Downloads/us-500.txt");
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
				StringTokenizer str = new StringTokenizer(strLine, " ");
				if(str != null) {
					while(str.hasMoreTokens()) {
						// Print the content on the console
						String name = str.nextToken().trim();
						StringTokenizer strName = new StringTokenizer(name, ",");
						while(strName.hasMoreTokens()) {
							String s = strName.nextToken().trim();
							System.out.println (s);
							tree.addNode(s);
						}
					}
				}
			}
			//Close the input stream
			in.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		} 

		//tree.printTree(root);
		System.out.println("************************Exiting Instant Search Reading File*****************");
	}
	
	
	public void test() {
		tree.addNode("Rohit");
		tree.addNode("Rashi");
		tree.printTree(root);
	}
}
