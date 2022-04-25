package gui.swing.controller.node;

import java.awt.event.ActionEvent;

import gui.swing.controller.Action;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.model.Document;
import repository.model.Project;
import repository.node.Node;

public class NewDocumentAction extends Action {
	
    public NewDocumentAction() {
//        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
//                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/resource/images/new-document.png"));
        putValue(NAME, "New Document");
        putValue(SHORT_DESCRIPTION, "New Document");
    }

    public void actionPerformed(ActionEvent arg0) {
        if(MainFrame.getInstance().getTree().getSelectedNode() != null && MainFrame.getInstance().getTree().getSelectedNode() instanceof Project) {
        	Node rti= MainFrame.getInstance().getTree().getSelectedNode();
        	Document d = new Document("@Document " + rti.count++, rti);
        	MainFrame.getInstance().getTree().addDocument(d);
        } else {
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.NEW_DOCUMENT, null));
		}
    }
}
