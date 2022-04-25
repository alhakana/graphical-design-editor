package gui.swing.node.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import notification.Notification;
import notification.NotificationCode;
import observer.IListener;
import repository.model.Project;
import repository.model.Workspace;
import repository.node.Node;

/**
 * Workspace view is the right side of MainFrame.
 */
public class WorkspaceView extends JDesktopPane implements IListener {
	private Workspace modelWorkspace;
	private List<ProjectView> projects;
	private JLabel label;
	
	public WorkspaceView(Workspace modelWorkspace) {
		projects = new ArrayList<>();
		this.modelWorkspace = modelWorkspace;
		modelWorkspace.addObserver(this);
		makeLabel();
	}
	
	@Override
	public void update(Notification notification) {
		if (notification.getNotificationCode() != null) {
			if (notification.getNotificationCode().equals(NotificationCode.NEW_PROJECT)) {
				ProjectView pv = new ProjectView((Project) notification.getObject(),this);
				projects.add(pv);
				add(pv);
				try {
					pv.setSelected(true);
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
			}else if (notification.getNotificationCode().equals(NotificationCode.LABEL))
				label.setText(modelWorkspace.getName());
		}
	}
	
	/**
	 * Set selected project in focus.
	 */
	public void setFocus(ProjectView pw) {
		moveToFront(pw);
//		selectedProjectView = pw;
	}
	
	private void makeLabel() {
		label = new JLabel(modelWorkspace.getName());
		this.add(label);
		label.setPreferredSize(new Dimension(300,10));
		label.setBounds(new Rectangle(new Point(10,10), label.getPreferredSize()));
		this.setBackground(Color.white);
	}

	public Workspace getModelWorkspace() {
		return modelWorkspace;
	}

	public List<ProjectView> getProjects() {
		return projects;
	}
	
	/**
	 * Linking workspace with workspace view.
	 * @param workspace new workspace which is set as root
	 */
	public void resetWorkspaceView(Node workspace) {
		if(projects.size() != 0) {
			// first - go to projects and close them
			for(ProjectView pw : projects) {
				pw.setVisible(false);
			}
		}
		
		projects.clear();
		projects = new ArrayList<>();
		
		modelWorkspace = (Workspace)workspace;	// linking root with view
		modelWorkspace.addObserver(this);
		label.setText(modelWorkspace.getName());
	}
	
}