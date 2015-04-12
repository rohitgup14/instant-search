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

import java.util.Iterator;
import java.util.Set;

public class Tree {
	
	/**
	 * Token for the node
	 */
	private String token;

	/**
	 * Node representing the token
	 */
	private Node root;

	public Tree(Node root) {
		super();
		this.root = root;
	}
	
	/**
	 * @param token
	 */
	public Tree(String token) {
		this.token = token;
	}

	/**
	 * @return
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 */
	public void setToken(String token) {
		this.token = token;
	}
	
	/**
	 * @return
	 */
	private Node getRootNode(){
		return root;
	}

	/**
	 * @param root
	 */
	public void setRootNode(Node root) {
		this.root = root;
	}

	/**
	 * Add node one text at a time.
	 * @param token
	 */
	public void addNode(String token) {
		if(token == null || token.trim().length() == 0) {
			return;
		}
		char[] ch = token.toCharArray();
		Node parent = getRootNode();
		for(char c: ch) {
			Node node = new Node(c);
			parent = populate(parent, node);
		}
		//Now the parent node is the leaf node. So mark it true
		parent.setLeaf(true);
		parent.setValue(token);
	}

	/**
	 * Populates the node with the data
	 * @param parent
	 * @param child
	 * @return
	 */
	private Node populate(Node parent, Node child) {
		Set<Node> children = parent.getChildNodes();
		if(children != null && !children.isEmpty()) {
			for(Iterator<Node> itr = children.iterator();itr.hasNext();){
				Node ch = itr.next();
				if(ch.equals(child)) {
					return ch;
				} 
			}
		} 
		parent.addChildren(child);
		return child;
	}

	/**
	 * Function prints the whole tree after being provided the root.
	 * @param root
	 */
	public void printTree(Node root) {
		class SubPopulator {
			public void popSubItems(Node node,int level) {
				level++;
				Set<Node> children = node.getChildNodes();
				if(children != null) {
					for (Iterator<Node> it = children.iterator(); it.hasNext();) {
						Node child = (Node) it.next();
						String levelSpace = " ";
						for(int i = 0 ; i < level ; i++) {
							levelSpace+=levelSpace+" ";
						}
						System.out.println(levelSpace+child.getId()+"("+level+")"+child.isLeaf()+" Value-->"+child.getValue());
						popSubItems(child, level);
					}
				}
			}
		}

		SubPopulator sb = new SubPopulator();
		sb.popSubItems(root, 0);
	}


}
