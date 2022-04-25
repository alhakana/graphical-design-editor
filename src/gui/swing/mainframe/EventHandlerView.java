package gui.swing.mainframe;

import javax.swing.JOptionPane;

import handler.EventHandlerImpl;
import notification.Notification;
import observer.IListener;
import repository.model.Document;
import repository.model.Page;
import repository.model.Project;
import repository.model.Slot;
import repository.node.Node;

public class EventHandlerView implements IListener {

	@Override
	public void update(Notification notification) {
		switch(notification.getNotificationCode()) {
		case EXIT_MAINFRAME: exitMainFrame((EventHandlerImpl)notification.getObject()); break;
		case NEW_SLOT: newSlot(); break;
		case NEW_PROJECT: newProject(); break;
		case NEW_PAGE: newPage(); break;
		case NEW_DOCUMENT: newDocument(); break;
		case NAME_EXISTS: sameName((Node)notification.getObject()); break;
		case EMPTY: emptyName(); break;
		case SPECIAL_CHA: specialCharacter(); break;
		case NAME_SIZE: nameSize(); break;
		case DELETE_ME: deleteMess(); break;
		case RENAME: nodeRename(); break;
		case PAGE_SELECT: selectPage(); break;
		case SLOT_SELECT: selectSlot(); break;
		case SAVE_AS: saveAs(); break;
		case IMPORT_PROJECT: importProject(); break;
		case IMPORT_NAME: importProjectName(); break;
		case SAME_DIR_NAME: sameDir(); break;
		default: break;
		}
			
	}
	
	private void sameDir() {
		JOptionPane.showMessageDialog(null, "Directory with that workspace name already exists.");		
	}
	
	private void importProjectName() {
		JOptionPane.showMessageDialog(null, "Project with that name already exists in current workspace.");		
	}
	
	private void importProject() {
		JOptionPane.showMessageDialog(null, "Error while importing.");		
	}
	
	private void saveAs() {
		JOptionPane.showMessageDialog(null, "nznm o cemu se radi promeniti ovaj alert.");		
	}
	
	private void selectPage() {
		JOptionPane.showMessageDialog(null, "You need to select a page in the tree before drawing an element.");		
	}
	
	private void selectSlot() {
		JOptionPane.showMessageDialog(null, "You need to select slots before moving them.");		
	}
	
	private void nodeRename() {
		JOptionPane.showMessageDialog(null, "Select a node to change its name.");
	}
	
	private void newProject() {
		JOptionPane.showMessageDialog(null, "Select workspace in order to create new project.");
	}
	
	private void deleteMess() {
		JOptionPane.showMessageDialog(null, "Select a node in order to delete it.");
	}	
	
	private void nameSize() {
		JOptionPane.showMessageDialog(null, "Name can not contain more that 15 characters.");
	}
	
	private void specialCharacter() {
		JOptionPane.showMessageDialog(null, "Users can not use special characters such as @,( and ).");
	}
	
	private void exitMainFrame(EventHandlerImpl model) {
		model.setAns(JOptionPane.showConfirmDialog(null, "Close app?", "Close", JOptionPane.YES_NO_OPTION));
	}
	
	private void newSlot() {
		JOptionPane.showMessageDialog(null, "Select a page in order to create a slot.");
	}
	
	private void newPage() {
		JOptionPane.showMessageDialog(null, "Select a document in order to create a page.");
	}
	
	private void newDocument() {
		JOptionPane.showMessageDialog(null, "Select a project in order to create a document.");
	}
	
	private void sameName(Node node) {
		String s = "";
		if (node instanceof Project)
			s = "project";
		else if (node instanceof Document)
			s = "document";
		else if (node instanceof Page)
			s = "page";
		else if (node instanceof Slot)
			s = "slot";
		
		JOptionPane.showMessageDialog(null, "Node " + s + " already exists.");
	}
	
	private void emptyName() {
		JOptionPane.showMessageDialog(null, "A node must have a name.");
	}

}
