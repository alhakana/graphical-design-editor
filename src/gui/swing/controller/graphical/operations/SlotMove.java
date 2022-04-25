package gui.swing.controller.graphical.operations;

import java.awt.event.ActionEvent;

import gui.swing.controller.Action;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.model.Page;

public class SlotMove extends Action {

	public SlotMove() {
		putValue(SMALL_ICON, loadIcon("/resource/images/state-move.png"));
		putValue(NAME, "Move Slot");
		putValue(SHORT_DESCRIPTION, "Move Slot");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("MoveSlotAction");
		if (MainFrame.getInstance().getTree().getSelectedNode() instanceof Page) {
			Page page = (Page)MainFrame.getInstance().getTree().getSelectedNode();
			page.sendMessage(new Notification(NotificationCode.MOVE, null));
		} else 
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.PAGE_SELECT, null));

	}
}
