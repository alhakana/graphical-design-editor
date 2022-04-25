package repository.model;

import java.io.File;

import notification.Notification;
import notification.NotificationCode;
import repository.node.Node;
import repository.node.NodeComposite;

public class Project extends NodeComposite {
	
	private File projectFile;
	public Project(String name, Node parent) {
		super(name, parent);
//		sendMessage(new Notification(NotificationCode.NEW_PROJECT, null));
	}

	@Override
	public void addChildren(Node child) {
		if (child != null && child instanceof Document) {
			Document doc = (Document)child;
			if (!(getChildren().contains(doc))) {
				getChildren().add(doc);
				sendMessage(new Notification(NotificationCode.NEW_DOCUMENT, doc));
			}
				
		}
	}
	
	public void addSharedChildren(Node child) {
		Document doc = (Document)child;
		getChildren().add(doc);
		sendMessage(new Notification(NotificationCode.NEW_SHARED_DOCUMENT, doc));
	}
	
	@Override
	public void deleteMe() {
		sendMessage(new Notification(NotificationCode.DELETE_ME, null));
	}
	
	@Override
	public void sendMessage(Notification notification) {
		notifyObservers(notification);
	}

	public File getProjectFile() {
		return projectFile;
	}

	public void setProjectFile(File projectFile) {
		this.projectFile = projectFile;
	}
}