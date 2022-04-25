package gui.swing.controller.graphical.operations;

import java.awt.event.ActionEvent;

import gui.swing.controller.Action;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.model.Page;

public class SlotRotate extends Action {

	public SlotRotate() {
		putValue(SMALL_ICON, loadIcon("/resource/images/state-rotate.png"));
		putValue(NAME, "Rotate Slot");
		putValue(SHORT_DESCRIPTION, "Rotate Slot");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (MainFrame.getInstance().getTree().getSelectedNode() instanceof Page) {
			Page page = (Page)MainFrame.getInstance().getTree().getSelectedNode();
			page.sendMessage(new Notification(NotificationCode.ROTATE, null));
		} else 
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.PAGE_SELECT, null));

	}
	
}