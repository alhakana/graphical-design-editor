package gui.swing.controller.node;

import java.awt.event.ActionEvent;

import gui.swing.controller.Action;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.model.Project;
import repository.model.Workspace;
import repository.node.Node;

public class NewProjectAction extends Action {

    public NewProjectAction() {
//        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
//                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/resource/images/new-project.png"));
        putValue(NAME, "New Project");
        putValue(SHORT_DESCRIPTION, "New Project");
    }

    public void actionPerformed(ActionEvent arg0) {
        if(MainFrame.getInstance().getTree().getSelectedNode() != null && MainFrame.getInstance().getTree().getSelectedNode() instanceof Workspace) {
	    	Node rn = MainFrame.getInstance().getTree().getSelectedNode();
	        Project p = new Project("@Project " + rn.count++, rn);
	        MainFrame.getInstance().getTree().addProject(p);
        }else
        	MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.NEW_PROJECT, null));
        
    }
}
