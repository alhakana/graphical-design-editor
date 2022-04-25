package gui.swing.controller.repository;

import java.awt.event.ActionEvent;

import gui.swing.controller.Action;
import gui.swing.filechooser.RFileChooser;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.model.Workspace;
import repository.node.Node;

public class SaveWorkspace extends Action {
	
	public SaveWorkspace() {
//		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
//		        KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("/resource/images/save-workspace.png"));
		putValue(NAME, "Save workspace");
		putValue(SHORT_DESCRIPTION, "Save workspace");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Workspace workspace = (Workspace)((Node)MainFrame.getInstance().getTree().getWorkspace().getNodeModel());
		RFileChooser chooser = new RFileChooser(NotificationCode.SAVE_WORKSPACE);
		String pathname;
		
		if(chooser.showSaveDialog(MainFrame.getInstance().getWorkspaceView()) == RFileChooser.APPROVE_OPTION) {
			if(!chooser.getSelectedFile().exists()) {
				chooser.cancelSelection();
				MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.SAVE_AS, null));
			}else {
				pathname = chooser.getSelectedFile().getAbsolutePath() + "\\" + workspace.getName();
				MainFrame.getInstance().getSerializationHandler().saveWorkspace(workspace, pathname);
			}
		}
	
	}
}