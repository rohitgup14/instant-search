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

package com.asterix.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

public class InstantSearch implements Callable<List<String>>{

	/**
	 * Set of search characters
	 */
	private char[] searchCh;

	/**
	 * Root of the tree to search
	 */
	private Node root;
	
	/**
	 * @return
	 */
	public char[] getSearchCh() {
		return searchCh;
	}

	/**
	 * @param searchCh
	 */
	public void setSearchCh(char[] searchCh) {
		this.searchCh = searchCh;
	}

	/**
	 * @return
	 */
	public Node getRoot() {
		return root;
	}

	/**
	 * @param root
	 */
	public void setRoot(Node root) {
		this.root = root;
	}

	/**
	 * @param searchCh
	 * @param root
	 */
	public InstantSearch(char[] searchCh, Node root) {
		super();
		this.searchCh = searchCh;
		this.root = root;
	}

	/**
	 * @param root
	 */
	public InstantSearch(Node root) {
		super();
		this.root = root;
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	public List<String> call() throws Exception {
		final List<String> words = new ArrayList<String>();
		class SubPopulator {
			public void popSubItems(Set<Node> children, int level) {
				level++;
				if(children != null) {
					for (Iterator<Node> it = children.iterator(); it.hasNext();) {
						Node child = (Node) it.next();
						if(child.isLeaf()) {
							words.add(child.getValue());
						}
						popSubItems(child.getChildNodes(), level);
					}
				}
			}
		}

		SubPopulator sb = new SubPopulator();
		Node parent = getRoot();
		for(char c : searchCh) {
			Node cNode = new Node(c);
			parent = search(parent,cNode);
		}
		sb.popSubItems(parent.getChildNodes(), 0);
		return words;
	}

	/**
	 * @param parent
	 * @param child
	 * @return
	 */
	private Node search(Node parent, Node child) {
		Set<Node> children = parent.getChildNodes();
		if(children != null) {
			for(Iterator<Node> itr = children.iterator();itr.hasNext();){
				Node ch = itr.next();
				if(ch.equals(child)) {
					return ch;
				}
			}
		}
		return null;
	}


}
