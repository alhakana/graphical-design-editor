package gui.swing.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import gui.swing.filechooser.RFileChooser;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.model.Slot;

public class ChangeImgAction extends Action {

	@Override
	public void actionPerformed(ActionEvent e) {
		RFileChooser chooser;
		File file;
		
		if(MainFrame.getInstance().getTree().getSelectedNode() instanceof Slot) {
			Slot slot = (Slot)MainFrame.getInstance().getTree().getSelectedNode();
			
			chooser = new RFileChooser(NotificationCode.IMPORT_IMG);
			
			if(chooser.showOpenDialog(MainFrame.getInstance().getWorkspaceView()) == RFileChooser.APPROVE_OPTION) {
				if(!chooser.getSelectedFile().exists()) {
					chooser.cancelSelection();
					System.out.println("napraviti event za ovo");
				}else {
					file = chooser.getSelectedFile();
					slot.setFile(file);
					slot.sendMessage(new Notification(NotificationCode.IMPORT_IMG, null));
				}
			}
		}
		
	}

}
