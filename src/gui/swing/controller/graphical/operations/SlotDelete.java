package gui.swing.controller.graphical.operations;

import java.awt.event.ActionEvent;

import gui.swing.controller.Action;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.model.Page;

public class SlotDelete extends Action {

	public SlotDelete() {
		putValue(SMALL_ICON, loadIcon("/resource/images/state-delete.png"));
		putValue(NAME, "Delete Slot");
		putValue(SHORT_DESCRIPTION, "Delete Slot");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (MainFrame.getInstance().getTree().getSelectedNode() instanceof Page) {
			Page page = (Page)MainFrame.getInstance().getTree().getSelectedNode();
			page.sendMessage(new Notification(NotificationCode.DELETE, null));
		} else 
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.PAGE_SELECT, null));

	}
}
