package gui.swing.node.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import gui.swing.mainframe.MainFrame;
import gui.swing.tree.model.TreeItem;
import notification.Notification;
import observer.IListener;
import repository.model.Document;
import repository.model.Project;

public class ProjectView extends JInternalFrame implements IListener {
	private final Project projectModel;
	private static final int xCor = 15, yCor = 15;
	private static int count = 0;
	private final List<DocumentView> documents;
	private final JTabbedPane jtp;
	private final WorkspaceView containerParent;
	
	public ProjectView(Project projectModel, WorkspaceView containerParent) {
		super(projectModel.getName(), true, true, true);
		
		documents = new ArrayList<>();
		jtp = new JTabbedPane();
		this.containerParent = containerParent;
		this.projectModel = projectModel;
		
		projectModel.addObserver(this);
		installThis();
	}
	
	private void installThis() {
		setFrameIcon(new ImageIcon(getClass().getResource("/resource/images/small_project.png")));
		count++;
		setLocation(xCor*count, yCor*count);
		setIconifiable(true);
		setClosable(true);
		setSize(1300, 600);
		setVisible(true);
		add(jtp);
	}
	
	@Override
	public void update(Notification notification) {
		DocumentView dv;
		if (notification.getNotificationCode() != null) {
			switch(notification.getNotificationCode()) {
			case NEW_DOCUMENT:
				dv = new DocumentView((Document) notification.getObject(), this);
				addDocumentView(dv);
				putProjectInFocus();
				break;
			case NEW_SHARED_DOCUMENT:
				Document document = (Document) notification.getObject();
				ProjectView projectView = getPresentProjectView(document);
				dv = (DocumentView) projectView.getJtp().getSelectedComponent();
				DocumentView dvCopy = new DocumentView(document, this);
				addDocumentView(dvCopy);
				putProjectInFocus();
				dvCopy.addSharedPages(dv);
				break;
			case DELETE_ME:
				deleteMe();
				break;
			case RENAME:
				changeName();
				break;
			case SET_FOCUS:
				putProjectInFocus();
				break;
			default:
				break;
			}
		}
	}
	
	public void deleteDocTreeItem(TreeItem doc) {
		// search for ru tree item for project model
		for(int i = 0 ; i < MainFrame.getInstance().getTree().getWorkspace().getChildCount() ; i++) {
			if(projectModel.getName().equals(((TreeItem)MainFrame.getInstance().getTree().getWorkspace().getChildAt(i)).getNodeModel().getName())) {
				TreeItem item = (TreeItem)MainFrame.getInstance().getTree().getWorkspace().getChildAt(i);
				for(int j = 0 ; j < item.getChildCount() ;j++)
					if(doc.getNodeModel().getName().equals(((TreeItem)item.getChildAt(j)).getNodeModel().getName()))
						item.remove(j);
			}
		}
	}
	
	public void addDocumentView(DocumentView dv) {
		documents.add(dv);
		String name = dv.getDocumentModel().getName();
		jtp.addTab(name, dv);
		
		
		jtp.setIconAt(documents.size()-1, new ImageIcon(getClass().getResource("/resource/images/small_document.png")));
		putTabbInFocus(dv);
	}
	
	private ProjectView getPresentProjectView(Document document) {
		for(JInternalFrame pv: MainFrame.getInstance().getWorkspaceView().getAllFrames()) {
			ProjectView p = (ProjectView)pv;
			if (p.getProjectModel().equals(document.getParent()))
				return (ProjectView) pv;
		}
		return null;
	}
	
	private void changeName() {
		this.setTitle(projectModel.getName());
		
		for(DocumentView dv : documents)
			dv.notifyPages();
	}
	
	private void deleteMe() {
		ListIterator<DocumentView> li = documents.listIterator();
		while(li.hasNext()) {
			DocumentView dw = li.next();
			dw.getDocumentModel().getListeners().remove(dw);
		}
		
		// delete from tree
		MainFrame.getInstance().getTree().getWorkspace().remove(MainFrame.getInstance().getTree().getWorkspace().getIndex(MainFrame.getInstance().getTree().getSelectedTreeItem()));

		containerParent.getModelWorkspace().getChildren().remove(projectModel);
		containerParent.getProjects().remove(this);
		containerParent.remove(this);
		containerParent.repaint();
		MainFrame.getInstance().getWorkspaceTree().setSelectionRow(0);
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());	
	}

	
	/**
	 * When the document is created, put project in focus.
	 */
	public void putProjectInFocus() {
		containerParent.setFocus(this);
	}
	
	/**
	 * Put document in focus.
	 */
	public void putTabbInFocus(DocumentView dw) {
		jtp.setSelectedComponent(dw);
	}

	public Project getProjectModel() {
		return projectModel;
	}

	public List<DocumentView> getDocuments() {
		return documents;
	}

	public JTabbedPane getJtp() {
		return jtp;
	}
	
}