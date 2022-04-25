package gui.swing.tree.view;

import java.awt.Component;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import gui.swing.tree.model.TreeItem;
import repository.model.Document;
import repository.model.Page;
import repository.model.Project;
import repository.model.Slot;
import repository.model.Workspace;

public class TreeCellRenderer extends DefaultTreeCellRenderer{
	
	public TreeCellRenderer() {}
	
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		
		if (((TreeItem)value).getNodeModel() instanceof Workspace) {
			URL imageURL = getClass().getResource("/resource/images/workspace.png");
			Icon icon = null;
			if (imageURL != null) 
				icon = new ImageIcon(imageURL);
			setIcon(icon);
		} else if (((TreeItem)value).getNodeModel() instanceof Project){
			URL imageURL = getClass().getResource("/resource/images/small_project.png");
			Icon icon = null;
			if (imageURL != null) 
				icon = new ImageIcon(imageURL);
			setIcon(icon);
		} else if (((TreeItem)value).getNodeModel() instanceof Document){
			URL imageURL = getClass().getResource("/resource/images/small_document.png");
			Icon icon = null;
			if (imageURL != null) 
				icon = new ImageIcon(imageURL);
			setIcon(icon);
		} else if (((TreeItem)value).getNodeModel() instanceof Page){
			URL imageURL = getClass().getResource("/resource/images/small_page.png");
			Icon icon = null;
			if (imageURL != null) 
				icon = new ImageIcon(imageURL);
			setIcon(icon);
		} else if (((TreeItem)value).getNodeModel() instanceof Slot){
			URL imageURL= getClass().getResource("/resource/images/new_slot_small.png");
			Icon icon = null;
			if (imageURL != null) 
				icon = new ImageIcon(imageURL);
			setIcon(icon);
		}
		
		return this;
	}
}
