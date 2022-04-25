package repository.node;

import java.util.ArrayList;
import java.util.List;

public abstract class NodeComposite extends Node {
	private final List<Node> children;
	
	public NodeComposite(String name, Node parent) {
		super(name, parent);
		children = new ArrayList<>();
	}
	
	public abstract void addChildren(Node child);
	
	/** 
	 * @return node with name if exists or null if doesn't
	 */
	public Node getChildrenByName(String name) {
		for(Node node: getChildren()) {
			if (node.getName().equals(name))
				return node;
		}
		return null;
	}

	public List<Node> getChildren() {
		return children;
	}
}