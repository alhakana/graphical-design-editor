package gui.swing.controller.node;

import java.awt.event.ActionEvent;

import gui.swing.controller.Action;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.model.Document;
import repository.model.Page;
import repository.node.Node;

public class NewPageAction extends Action {
	
    public NewPageAction() {
//    	putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
//    			KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/resource/images/new-page.png"));
        putValue(NAME, "New Page");
        putValue(SHORT_DESCRIPTION, "New Page");
    }

    public void actionPerformed(ActionEvent arg0) {
        if(MainFrame.getInstance().getTree().getSelectedNode() != null && MainFrame.getInstance().getTree().getSelectedNode() instanceof Document){
        	Node nodeModel = MainFrame.getInstance().getTree().getSelectedNode();
        	Page p = new Page("@Page " + nodeModel.count++, nodeModel);
        	MainFrame.getInstance().getTree().addPage(p);
        } else {
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.NEW_PAGE, null));
		}
    }
}
