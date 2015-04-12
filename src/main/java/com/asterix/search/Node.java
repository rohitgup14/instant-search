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

import java.util.Set;
import java.util.TreeSet;

public class Node {
	/**
	 * All the immediate child nodes connected to the node.
	 */
	private Set<Node> childNodes;
	/**
	 * Unique id associated with the node
	 */
	private char id;
	/**
	 * If the node is leaf node. default is false
	 */
	private boolean isLeaf = false;
	/**
	 * Value associated with the node.
	 */
	private String value =  null;
	/**
	 * @return the childNodes
	 */
	public Set<Node> getChildNodes() {
		return childNodes;
	}
	
	/**
	 * @param id
	 */
	public Node(char id) {
		super();
		this.id = id;
	}
	/**
	 * @param childNodes the childNodes to set
	 */
	public void setChildNodes(Set<Node> nodes) {
		if(childNodes == null) {
			childNodes  = new TreeSet<Node>(new NodeComparator());
		}
		if(nodes != null) {
			this.childNodes.addAll(nodes);
		}
	}
	
	public void addChildren(Node item) {
		if(childNodes == null) {
			childNodes = new TreeSet<Node>(new NodeComparator());
		}
		setLeaf(false);
		childNodes.add(item);
	}
	
	/**
	 * @return the id
	 */
	public char getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(char id) {
		this.id = id;
	}
	/**
	 * @return the isLeaf
	 */
	public boolean isLeaf() {
		return isLeaf;
	}
	/**
	 * @param isLeaf the isLeaf to set
	 */
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
