package gui.swing.tree.model;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import notification.Notification;
import notification.NotificationCode;
import repository.model.Document;
import repository.node.Node;
import repository.node.NodeComposite;

public class TreeItem extends DefaultMutableTreeNode {
	private String name;
	private final Node nodeModel;
	
	public TreeItem(Node nodeModel) {
		this.nodeModel = nodeModel;
		this.name = nodeModel.getName();
	}
	
	public TreeItem(Node node, String name) {
        this.name = name;
        this.nodeModel = node;
    }
	
	@Override
	public int getIndex(TreeNode aChild) {
		return findIndexByChildren((TreeItem)aChild);
	}
	
	@Override
	public TreeNode getChildAt(int index) {
		return findChildByIndex(index);
	}
	
	@Override
	public int getChildCount() {
		if(nodeModel instanceof NodeComposite)
			return ((NodeComposite) nodeModel).getChildren().size();
		return 0;
	}

	@Override
	public boolean getAllowsChildren() {
		return nodeModel instanceof NodeComposite;
	}
	
	@Override
	public boolean isLeaf() {
		return !(nodeModel instanceof NodeComposite);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public Enumeration children() {
        if(nodeModel instanceof NodeComposite)
            return (Enumeration) ((NodeComposite) nodeModel).getChildren();
        return null;
    }
	
	private TreeNode findChildByIndex(int childIndex) {
        if (nodeModel instanceof NodeComposite) {
            TreeItem toLookFor = new TreeItem(((NodeComposite) nodeModel).getChildren().get(childIndex));

            @SuppressWarnings("rawtypes")
			Iterator childrenIterator = children.iterator();
            TreeNode current;

            while (childrenIterator.hasNext()) {
                current = (TreeNode) childrenIterator.next();
                if (current.equals(toLookFor))
                    return current;
            }
        }

        return null;
    }
	
	private int findIndexByChildren(TreeItem node) {
		if(nodeModel instanceof NodeComposite)
			return ((NodeComposite) this.nodeModel).getChildren().indexOf(node.getNodeModel());
		
		return -1;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof TreeItem) {
			TreeItem item= (TreeItem)obj;
			return item.nodeModel.equals(this.nodeModel);
		}
		return false;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name.contains("(shared)")) {
			this.name = name;
			nodeModel.sendMessage(new Notification(NotificationCode.REFRESH_NAME, null));
			return;
		}
		if(nodeModel.countObservers() > 1 && nodeModel instanceof Document) {
			this.name = name + " (shared)";
			this.nodeModel.setName(name);
			return;
		}
		this.name = name;
		this.nodeModel.setName(name);
	
	}

	public Node getNodeModel() {
		return nodeModel;
	}
	
	@Override
	public void insert(MutableTreeNode newChild, int childIndex) {
		if (children == null) {
            children = new Vector<>();
        }
		children.insertElementAt(newChild, childIndex);
	}
}