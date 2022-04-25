package repository.node;

import java.io.Serializable;

import notification.Notification;
import observer.IObserverImpl;

/**
 * Abstraction for all nodes.
 */
public abstract class Node extends IObserverImpl implements Serializable {
	private String name;
	private Node parent;
	public Integer count = 1;
	
	public Node(String name, Node parent) {
		this.name = name;
		this.parent = parent;		
	}
	
	public Node() {}
	
	/**
	 * Called when model sends sth to view.
	 */
	public void sendMessage(Notification notification) {}
	
	/**
	 * Every class but workspace overrides this method.
	 */
	public void deleteMe() {}
	
	/**
	 * Two nodes are equals if their names are the same.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Node) {
			Node node= (Node)obj;
			return getName().equals(node.getName());
		}
		return false;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}

	public Node getParent() {
		return parent;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
}