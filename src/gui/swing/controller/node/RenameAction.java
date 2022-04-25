package gui.swing.controller.node;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

import gui.swing.controller.Action;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;

public class RenameAction extends Action {

	public RenameAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
		putValue(SMALL_ICON, loadIcon("/resource/images/rename.png"));
        putValue(NAME, "Rename");
        putValue(SHORT_DESCRIPTION, "Rename");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(MainFrame.getInstance().getTree().getSelectedNode() != null)
				MainFrame.getInstance().getTree().editingAtPath();
		else
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.RENAME, null));
	}

}
