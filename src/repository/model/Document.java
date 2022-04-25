package repository.model;

import notification.Notification;
import notification.NotificationCode;
import repository.node.Node;
import repository.node.NodeComposite;

public class Document extends NodeComposite {

	public Document(String name, Node parent) {
		super(name, parent);
	}

	@Override
	public void addChildren(Node child) {
		if (child != null && child instanceof Page) {
			Page p = (Page)child;
			if (!(getChildren().contains(child))) {
				getChildren().add(p);
				sendMessage(new Notification(NotificationCode.NEW_PAGE, p));
			}
		}
	}
	
	@Override
	public void deleteMe() {
		sendMessage(new Notification(NotificationCode.DELETE_ME, null));
	}
	
	@Override
	public void sendMessage(Notification notification) {
		notifyObservers(notification);
	}
	
}