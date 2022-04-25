package gui.swing.controller.repository;

import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;

import gui.swing.controller.Action;
import gui.swing.filechooser.RFileChooser;
import gui.swing.mainframe.MainFrame;
import gui.swing.tree.model.TreeItem;
import notification.Notification;
import notification.NotificationCode;
import repository.model.Workspace;

public class SwitchWorkspace extends Action {
	
	public SwitchWorkspace() {
//		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
//		        KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("/resource/images/open-workspace.png"));
		putValue(NAME, "Switch workspace");
		putValue(SHORT_DESCRIPTION, "Switch workspace");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(MainFrame.getInstance().getTree().getSelectedNode() instanceof Workspace) {
			RFileChooser chooser = new RFileChooser(NotificationCode.IMPORT_WORKSPACE);
			if(chooser.showOpenDialog(MainFrame.getInstance().getWorkspaceView()) == JFileChooser.APPROVE_OPTION) {
				if(!chooser.getSelectedFile().exists()) {
					chooser.cancelSelection();
					MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.IMPORT_PROJECT, null));
				}else {
					TreeItem workspace = MainFrame.getInstance().getTree().changeRoot(chooser.getSelectedFile().getName().substring(0, chooser.getSelectedFile().getName().indexOf(".")));
					((Workspace)workspace.getNodeModel()).setWorkspaceFile(chooser.getSelectedFile());
					MainFrame.getInstance().getWorkspaceView().resetWorkspaceView(workspace.getNodeModel());		
					MainFrame.getInstance().getSerializationHandler().importWorkspace(chooser.getSelectedFile());
				}
			}
		}
	}

}