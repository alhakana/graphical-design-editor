package gui.swing.tree.controller;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import gui.swing.tree.model.TreeItem;
import notification.Notification;
import notification.NotificationCode;
import repository.model.Document;
import repository.model.Page;
import repository.model.Project;
import repository.model.Slot;
import repository.node.Node;

public class TreeSelectListener implements TreeSelectionListener {

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		check((TreeItem)e.getPath().getLastPathComponent());
	}
	
	private void check(TreeItem item) {
		Node model = item.getNodeModel();
		
		if (model instanceof Project) {
			Project pr = (Project)model;
			pr.sendMessage(new Notification(NotificationCode.SET_FOCUS, null));
		} else if (model instanceof Document) {
			Document doc = (Document)model;
			doc.sendMessage(new Notification(NotificationCode.SET_FOCUS, null));
		} else if (model instanceof Page) {
			Page page = (Page)model;
			page.sendMessage(new Notification(NotificationCode.SET_FOCUS, null));
		} else if (model instanceof Slot) {
			Slot slot = (Slot)model;
			slot.sendMessage(new Notification(NotificationCode.SET_FOCUS, null));
		}
	}

}
