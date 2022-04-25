package gui.swing.controller.repository;

import java.awt.event.ActionEvent;
import java.io.File;

import gui.swing.controller.Action;
import gui.swing.mainframe.MainFrame;
import repository.model.Project;

@SuppressWarnings("serial")
public class SaveProject extends Action {
	
	public SaveProject() {
//		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
//		        KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("/resource/images/save-project.png"));
		putValue(NAME, "Save project");
		putValue(SHORT_DESCRIPTION, "Save project");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(MainFrame.getInstance().getTree().getSelectedNode() instanceof Project) {
			Project project = (Project)MainFrame.getInstance().getTree().getSelectedNode();
			File file = project.getProjectFile();
			
			// if project doesn't have file path, call save as
			if(file == null) {
				MainFrame.getInstance().getActionManager().getSaveAsProject().actionPerformed(e);
				return;
			}
			
			MainFrame.getInstance().getSerializationHandler().save(project);
		}
	}

}