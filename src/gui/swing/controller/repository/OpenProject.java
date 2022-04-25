package gui.swing.controller.repository;

import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;

import gui.swing.controller.Action;
import gui.swing.filechooser.RFileChooser;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;

public class OpenProject extends Action {
	
	public OpenProject() {
		putValue(SMALL_ICON, loadIcon("/resource/images/open-project.png"));
		putValue(NAME, "Open project"); 
		putValue(SHORT_DESCRIPTION, "Open project");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		RFileChooser chooser = new RFileChooser(NotificationCode.IMPORT_PROJECT);
		
		if(chooser.showOpenDialog(MainFrame.getInstance().getWorkspaceView()) == JFileChooser.APPROVE_OPTION) {
			if(!chooser.getSelectedFile().exists()) {
				chooser.cancelSelection();
				MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.IMPORT_PROJECT, null));
			}else
				MainFrame.getInstance().getSerializationHandler().importProject(chooser.getSelectedFile(),NotificationCode.IMPORT_PROJECT);
		}
	}

}