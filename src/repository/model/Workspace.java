package repository.model;

import java.io.File;

import notification.Notification;
import notification.NotificationCode;
import repository.node.Node;
import repository.node.NodeComposite;

/**
 * Model for root of the tree.
 */
public class Workspace extends NodeComposite {
	
	private File workspaceFile;		// workspace path
	
	public Workspace(String name) {
		super(name, null);
	}

	@Override
	public void addChildren(Node child) {
		if (child != null && child instanceof Project) {
			Project project = (Project)child;
			if (!getChildren().contains(project)) {
				getChildren().add(project);
				sendMessage(new Notification(NotificationCode.NEW_PROJECT, project));
			}
		}
	}
	
	@Override
	public void sendMessage(Notification notification) {
		notifyObservers(notification);
	}

	public File getWorkspaceFile() {
		return workspaceFile;
	}

	public void setWorkspaceFile(File workspaceFile) {
		this.workspaceFile = workspaceFile;
	}
}