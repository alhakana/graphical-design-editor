package repository.model;

import notification.Notification;
import notification.NotificationCode;
import repository.node.Node;
import repository.node.NodeComposite;

public class Page extends NodeComposite {

	public Page(String name, Node parent) {
		super(name, parent);
	}

	@Override
	public void addChildren(Node child) {
		if (child != null && child instanceof Slot) {
			Slot s = (Slot)child;
			if (!(getChildren().contains(s)))
				getChildren().add(s);
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

	public void setSelected(Object o) {
		sendMessage(new Notification(NotificationCode.SELECT, o));
	}

	public void resetColor() {
		sendMessage(new Notification(NotificationCode.RESET_COLOR, null));
	}

	public void setSelectionRectangle(Object o) {
		sendMessage(new Notification(NotificationCode.SELECTION_RECTANGLE, o));
	}

	public void undo() {
		sendMessage(new Notification(NotificationCode.UNDO, null));
	}
	
	public void redo() {
		sendMessage(new Notification(NotificationCode.REDO, null));
	}

	public void addCommand(Object o) {
		sendMessage(new Notification(NotificationCode.ADD_COMMAND, o));
	}

}	
