/*******************************************************************************
Copyright [2015] [Rohit Gupta]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

*******************************************************************************/
package com.asterix.api;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;

import com.asterix.search.InstantSearch;
import com.asterix.search.Node;
import com.asterix.search.Tree;

@org.springframework.stereotype.Service
public class Service {

	Node root = new Node(' ');
	Tree tree = new Tree(root);

	InstantSearch search;
	
	/**
	 * Function to populate sample data.
	 */
	@PostConstruct
	public void readFile() {
		try{
			// Open the file that is the first 
			// command line parameter
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("us-500.txt").getFile());
			FileInputStream fstream = new FileInputStream(file.getAbsoluteFile());
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
				StringTokenizer str = new StringTokenizer(strLine, "\n");
				if(str != null) {
					while(str.hasMoreTokens()) {
						// Print the content on the console
						String name = str.nextToken().trim();
						StringTokenizer strName = new StringTokenizer(name, ",");
						while(strName.hasMoreTokens()) {
							String s = strName.nextToken().trim().replace("\"", "");
							//System.out.println (s);
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
	}
	
	
	/**
	 * @param input
	 * @return
	 */
	public List<String> search(String input) {
		System.out.println("Input character for Search " + input);
		ExecutorService exec = Executors.newFixedThreadPool(2);
		InstantSearch is = new InstantSearch(input.toCharArray(), root);
		Future<List<String>> future = exec.submit(is);
		List<String> list = new ArrayList<String>();
		try {
			if(future != null && future.get() != null) {
				list = future.get();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			//e.printStackTrace();
		}
		return list;
	} 
}
