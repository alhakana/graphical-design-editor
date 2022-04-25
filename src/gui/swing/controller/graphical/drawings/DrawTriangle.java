package gui.swing.controller.graphical.drawings;

import java.awt.event.ActionEvent;

import gui.swing.controller.Action;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.model.Page;

public class DrawTriangle extends Action {
	
	public DrawTriangle() {
		putValue(SMALL_ICON, loadIcon("/resource/images/state-triangle.png"));
		putValue(NAME, "Draw Triangle");
		putValue(SHORT_DESCRIPTION, "Draw Triangle");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(MainFrame.getInstance().getTree().getSelectedNode() instanceof Page) {
			Page page = (Page)MainFrame.getInstance().getTree().getSelectedNode();
			page.sendMessage(new Notification(NotificationCode.TRIANGLE, null));
		} else MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.PAGE_SELECT, null));
	}
	

}
