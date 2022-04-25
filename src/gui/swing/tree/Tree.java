package gui.swing.tree;

import java.util.ArrayList;

import javax.swing.JTree;

import gui.swing.tree.model.TreeItem;
import notification.NotificationCode;
import repository.model.Document;
import repository.model.Page;
import repository.model.Project;
import repository.model.Slot;
import repository.model.Workspace;
import repository.node.Node;

public interface Tree {
	JTree generateTree(Workspace workspace);
	void addProject(Project project);
	void addDocument(Document document);
	void addSharedDocument(TreeItem document, TreeItem project);
	void addPage(Page page);
	void addSlot(Slot slot);
	Node getSelectedNode();
	void editingAtPath();
	TreeItem getWorkspace();
	TreeItem getSelectedTreeItem();
	
	/**
	 * Returns the selected components depending the level in tree.
	 * @param path level of the component
	 */
	TreeItem getTreeItem(int path);
	ArrayList<TreeItem> shareList();

	/**
	 * Project is a model and for each child is created new TreeItem and view.
	 */
	void importProject(Project project, NotificationCode code);
	
	/**
	 * Changes current workspace and returns new one.
	 */
	TreeItem changeRoot(String name);
	

	
}
