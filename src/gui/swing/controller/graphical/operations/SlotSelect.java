package gui.swing.controller.graphical.operations;

import java.awt.event.ActionEvent;

import gui.swing.controller.Action;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.model.Page;

public class SlotSelect extends Action {

	public SlotSelect() {
		putValue(SMALL_ICON, loadIcon("/resource/images/state-select.png"));
		putValue(NAME, "Select Slot");
		putValue(SHORT_DESCRIPTION, "Select Slot");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (MainFrame.getInstance().getTree().getSelectedNode() instanceof Page) {
			Page page = (Page)MainFrame.getInstance().getTree().getSelectedNode();
			page.sendMessage(new Notification(NotificationCode.SELECT, null));
		} else 
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.PAGE_SELECT, null));

	}
}
