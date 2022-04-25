package gui.swing.controller.repository;

import java.awt.event.ActionEvent;

import gui.swing.controller.Action;
import gui.swing.filechooser.RFileChooser;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.model.Project;

public class SaveAsProject extends Action {

	public SaveAsProject() {
		 putValue(SMALL_ICON, loadIcon("/resource/images/save-project-as.png"));
	     putValue(NAME, "SaveAs Project");
	     putValue(SHORT_DESCRIPTION, "SaveAs Project");
	
	}
	
	 	@Override
	public void actionPerformed(ActionEvent e) {
		if(MainFrame.getInstance().getTree().getSelectedNode() instanceof Project) {
			Project project = (Project)MainFrame.getInstance().getTree().getSelectedNode();
			String pathname;
			RFileChooser chooser = new RFileChooser(NotificationCode.SAVE_AS);
			if(chooser.showSaveDialog(MainFrame.getInstance().getWorkspaceView()) == RFileChooser.APPROVE_OPTION) {
				if(!chooser.getSelectedFile().exists()) {
					chooser.cancelSelection();
					MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.SAVE_AS, null));
				}else {
					pathname = chooser.getSelectedFile().getAbsolutePath() + "\\" + project.getName() + ".gdep";
					MainFrame.getInstance().getSerializationHandler().saveProject(project, pathname);
				}
			}
		}		
	}	     
}


