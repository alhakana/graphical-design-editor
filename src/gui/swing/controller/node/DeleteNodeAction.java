package gui.swing.controller.node;

import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;

import gui.swing.controller.Action;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.model.Slot;
import repository.model.Workspace;

public class DeleteNodeAction extends Action {

	public DeleteNodeAction() {
	    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("DELETE"));
		putValue(SMALL_ICON, loadIcon("/resource/images/delete.png"));
		putValue(NAME, "Delete");
		putValue(SHORT_DESCRIPTION, "Delete");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (MainFrame.getInstance().getTree().getSelectedNode() != null && 
				 !(MainFrame.getInstance().getTree().getSelectedNode() instanceof Workspace) && 
				 !(MainFrame.getInstance().getTree().getSelectedNode() instanceof Slot)) {
				 MainFrame.getInstance().getTree().getSelectedNode().deleteMe();
		 } else
			 MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.DELETE_ME, null));	
	}
}
