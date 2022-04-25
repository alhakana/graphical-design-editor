package gui.swing.tree.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;

import gui.swing.mainframe.MainFrame;
import gui.swing.tree.model.TreeItem;
import listener.NameListener;
import notification.Notification;
import notification.NotificationCode;
import repository.model.Document;
import repository.model.Page;
import repository.model.Project;
import repository.model.Slot;
import repository.model.Workspace;
import repository.node.Node;
import repository.node.NodeComposite;


public class TreeCellEditor extends DefaultTreeCellEditor implements ActionListener{
	private Object clickedOn;

	public TreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
		super(arg0, arg1);
	}
	
	@Override
	public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
		clickedOn = value;
		JTextField textField = new JTextField(value.toString());
		textField.addActionListener(this);
		textField.addKeyListener(new NameListener());
        return textField;
	}
	
	@Override
	public boolean isCellEditable(EventObject event) {
		if (event instanceof MouseEvent) {
			return ((MouseEvent) event).getClickCount() == 3;
		}
		return true;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!(clickedOn instanceof TreeItem))
			return;
		
		TreeItem item = (TreeItem)clickedOn;
		String name = e.getActionCommand().trim();
		// if new name is the same as the old one
		if (name.equals(item.getName())) {
			tree.stopEditing();
			return;
		}
		
		if (item.getNodeModel() instanceof Project) {
			if (validProjectName(name)) {
				MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.NAME_EXISTS, item.getNodeModel()));
				return;
			}
		} else if (item.getNodeModel() instanceof Document) {
			if(item.getName().contains("(shared)"))
				if(validSharedDocument(item.getNodeModel(),name)) {
					MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.NAME_EXISTS, item.getNodeModel()));
					return;	
				}
			
			if (validDocumentName(name,(NodeComposite)item.getNodeModel().getParent())) {
				MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.NAME_EXISTS, item.getNodeModel()));
				return;	
			}
		} else if (item.getNodeModel() instanceof Page) {
			if (validPageName(name,(NodeComposite)item.getNodeModel().getParent())) {
				MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.NAME_EXISTS, item.getNodeModel()));
				return;	
			}
		} else if (item.getNodeModel() instanceof Slot) {
			if (validSlotName(name,(NodeComposite)item.getNodeModel().getParent())) {
				MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.NAME_EXISTS, item.getNodeModel()));
				return;	
			}
		}
		
		if (name.trim().equals("")) {
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.EMPTY, null));
			return;
		}
		
		if(name.contains("@") || name.contains("(") || name.contains(")")) {
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.SPECIAL_CHA, null));
			return;
		}
		
			
		// this means that the name is valid
		if (item.getNodeModel() instanceof Workspace) {
			item.setName(e.getActionCommand().trim());
			item.getNodeModel().sendMessage(new Notification(NotificationCode.LABEL, null));
		}
		if (item.getNodeModel() instanceof Project) {
			item.setName(e.getActionCommand().trim());
			item.getNodeModel().sendMessage(new Notification(NotificationCode.RENAME, null));		
		}
		if (item.getNodeModel() instanceof Document) {
			item.setName(e.getActionCommand().trim());
			item.getNodeModel().sendMessage(new Notification(NotificationCode.RENAME, null));
		}
		if (item.getNodeModel() instanceof Page) {
			item.setName(e.getActionCommand().trim());
			item.getNodeModel().sendMessage(new Notification(NotificationCode.LABEL, null));
		}
		if (item.getNodeModel() instanceof Slot)
			item.setName(e.getActionCommand().trim());
			
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
		tree.stopEditing();
	}
	
	private boolean validProjectName(String name) {
		Workspace ws = MainFrame.getInstance().getDocumentRep().getWorkspace();
		Node node = ws.getChildrenByName(name);
		return node != null;
	}
	
	private boolean validDocumentName(String name, NodeComposite parent) {
		Node node = parent.getChildrenByName(name);
		return node != null;
	}
	
	private boolean validSharedDocument(Node document, String name) {
		TreeItem ws = MainFrame.getInstance().getTree().getWorkspace();
		boolean shared= false, sameName = false;
		for(int i= 0; i < ws.getChildCount() ;i++) {
			TreeItem project = (TreeItem)ws.getChildAt(i);
			for(int j= 0; j < project.getChildCount() ;j++) {
				TreeItem doc = (TreeItem)project.getChildAt(j);
				if(doc.getName().contains("(shared)") && doc.getNodeModel().getParent().equals(document.getParent()))
					shared = true;
				
				if(name.equals(doc.getNodeModel().getName())) {
					sameName = true;
				}
			}	
			if(shared && sameName)
				return true;
			shared = false;
			sameName = false;
		}
		return false;
	}
	
	private boolean validPageName(String name, NodeComposite parent) {
		Node node = parent.getChildrenByName(name);
		return node != null;
	}
	
	private boolean validSlotName(String name, NodeComposite parent) {
		Node node = parent.getChildrenByName(name);
		return node != null;
	}

}
