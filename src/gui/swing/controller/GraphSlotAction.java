package gui.swing.controller;

import java.awt.event.ActionEvent;
import java.io.File;

import gui.swing.filechooser.RFileChooser;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import notification.ShapeType;
import repository.model.Slot;

public class GraphSlotAction extends Action {
	
	public GraphSlotAction() {
		putValue(SMALL_ICON, loadIcon("/resource/images/graph-slot.png"));
		putValue(NAME, "Graph");
		putValue(SHORT_DESCRIPTION, "Graph");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		RFileChooser chooser;
		File file;
		
		if(MainFrame.getInstance().getTree().getSelectedNode() instanceof Slot) {
			Slot slot = (Slot)MainFrame.getInstance().getTree().getSelectedNode();

			if(slot.getContent() != null) {
				return;
			}

			chooser = new RFileChooser(NotificationCode.IMPORT_IMG);
			
			if(chooser.showOpenDialog(MainFrame.getInstance().getWorkspaceView()) == RFileChooser.APPROVE_OPTION) {
				if(!chooser.getSelectedFile().exists()) {
					chooser.cancelSelection();
				}else {
					file = chooser.getSelectedFile();
					slot.setContent(ShapeType.GRAPH);
					slot.setFile(file);
					slot.sendMessage(new Notification(NotificationCode.GRAPH, null));
				}
			}
			
		}
	}
	

}
