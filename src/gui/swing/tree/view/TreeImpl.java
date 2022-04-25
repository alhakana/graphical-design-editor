package gui.swing.tree.view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeModel;
import app.AppCore;
import gui.swing.tree.Tree;
import gui.swing.tree.model.TreeItem;
import notification.Notification;
import notification.NotificationCode;
import repository.model.Document;
import repository.model.Page;
import repository.model.Project;
import repository.model.Slot;
import repository.model.Workspace;
import repository.node.Node;

public class TreeImpl implements Tree {
	private RuTreeView treeView;
	private DefaultTreeModel treeModel;

	@Override
	public JTree generateTree(Workspace workspace) {
		TreeItem root = new TreeItem(workspace);
		treeModel = new DefaultTreeModel(root);
		treeView = new RuTreeView(treeModel);
		return treeView;
	}

	@Override
	public Node getSelectedNode() {
		if (treeView.getLastSelectedPathComponent() != null)
			return ((TreeItem) treeView.getLastSelectedPathComponent()).getNodeModel();
		return null;
	}


	/**
	 * Returns TreeItem.
	 */
	@Override
	public TreeItem getSelectedTreeItem() {
		if (treeView.getLastSelectedPathComponent() != null)
			return (TreeItem) treeView.getLastSelectedPathComponent();
		return null;
	}

	public TreeItem getTreeItem(int path) {
		return (TreeItem) treeView.getSelectionPath().getPathComponent(path);
	}

	@Override
	public void addProject(Project project) {
		Node nodeModel = ((TreeItem) treeModel.getRoot()).getNodeModel();
		((TreeItem) treeModel.getRoot()).add(new TreeItem(project));
		((Workspace) nodeModel).addChildren(project);
		SwingUtilities.updateComponentTreeUI(treeView);
	}

	@Override
	public void addDocument(Document document) {
		Node nodeModel = ((TreeItem) treeView.getLastSelectedPathComponent()).getNodeModel();
		TreeItem item = new TreeItem(document);
		((TreeItem) treeView.getLastSelectedPathComponent()).add(item);
		((Project) nodeModel).addChildren(document);
		treeView.expandPath(treeView.getSelectionPath());
		SwingUtilities.updateComponentTreeUI(treeView);
	}

	public void addSharedDocument(TreeItem document, TreeItem project) {
		project.add(document);
		((Project) project.getNodeModel()).addSharedChildren(document.getNodeModel());

		SwingUtilities.updateComponentTreeUI(treeView);
	}

	@Override
	public void addPage(Page page) {
		Node nodeModel = ((TreeItem) treeView.getLastSelectedPathComponent()).getNodeModel();
		((TreeItem) treeView.getLastSelectedPathComponent()).add(new TreeItem(page));
		((Document) nodeModel).addChildren(page);
		treeView.expandPath(treeView.getSelectionPath());
		SwingUtilities.updateComponentTreeUI(treeView);
	}

	@Override
	public void addSlot(Slot slot) {
		Node nodeModel = ((TreeItem) treeView.getLastSelectedPathComponent()).getNodeModel();
		((TreeItem) treeView.getLastSelectedPathComponent()).add(new TreeItem(slot));
		((Page) nodeModel).addChildren(slot);
		treeView.expandPath(treeView.getSelectionPath());
		SwingUtilities.updateComponentTreeUI(treeView);
	}

	@Override
	public void editingAtPath() {
		treeView.startEditingAtPath(treeView.getSelectionPath());
	}

	/**
	 * Returns ws but as TreeItem. If wanting to use the real ws, get the model.
	 */
	@Override
	public TreeItem getWorkspace() {
		return (TreeItem) treeModel.getRoot();
	}

	@Override
	public ArrayList<TreeItem> shareList() {
		ArrayList<TreeItem> list = new ArrayList<>();
		int num = ((TreeItem) treeModel.getRoot()).getChildCount();
		for (int i = 0; i < num; i++) {
			TreeItem item = (TreeItem) treeModel.getChild(getWorkspace(), i);
			if (!((Project) item.getNodeModel()).getChildren().contains(getSelectedNode()))
				list.add(item);
		}

		return list;
	}

	@Override
	public void importProject(Project project, NotificationCode code) {
		// adding project into workspace
		Node workspace = ((TreeItem) treeModel.getRoot()).getNodeModel();
		List<Node> documentList = new ArrayList<>(project.getChildren());
		project.getChildren().clear();
		TreeItem projectTreeItem = new TreeItem(project);
		((TreeItem) treeModel.getRoot()).add(projectTreeItem);
		((Workspace) workspace).addChildren(project);


		// adding document if existing
		if (documentList.size() == 0) {
			SwingUtilities.updateComponentTreeUI(treeView);
			return;
		}


		for (Node d : documentList) {
			Document document = (Document) d;

			// if shared and imported as one project, then it won't say that it is shared
			if (!document.getParent().equals(project) && code == null) {
				continue;
			} else if (!document.getParent().equals(project) && code == NotificationCode.IMPORT_PROJECT)
				document.setParent(project);


			List<Node> pageList = new ArrayList<>(document.getChildren());
			document.getChildren().clear();
			TreeItem documentItem = new TreeItem(document);
			projectTreeItem.add(documentItem);
			project.addChildren(document);

			importPage(documentItem, document, pageList);
		}

		SwingUtilities.updateComponentTreeUI(treeView);
	}

	private void importPage(TreeItem documentItem, Document document, List<Node> pagesList) {

		if (pagesList.size() == 0)
			return;

		for (Node p : pagesList) {
			Page page = (Page) p;
			List<Node> slotList = new ArrayList<>(page.getChildren());
			page.getChildren().clear();

			TreeItem pageItem = new TreeItem(page);
			documentItem.add(pageItem);
			document.addChildren(page);
			importSlot(pageItem, page, slotList);
		}
	}

	private void importSlot(TreeItem pageItem, Page page, List<Node> slotList) {

		if (slotList.size() == 0)
			return;

		for (Node s : slotList) {
			Slot slot = (Slot) s;
			TreeItem slotItem = new TreeItem(slot);
			pageItem.add(slotItem);
			page.addChildren(slot);
			page.sendMessage(new Notification(NotificationCode.NEW_SLOT, slot));

		}
	}

	@Override
	public TreeItem changeRoot(String name) {
		Workspace newWorkspace = new Workspace(name);
		TreeItem newRoot = new TreeItem(newWorkspace);
		treeModel.setRoot(newRoot);
		AppCore.getInstance().getRepository().setWorkspace(newWorkspace);
		return newRoot;
	}

}