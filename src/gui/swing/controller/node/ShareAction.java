package gui.swing.controller.node;

import java.awt.event.ActionEvent;

import gui.swing.controller.Action;
import gui.swing.mainframe.DocumentDialog;
import gui.swing.mainframe.MainFrame;
import repository.model.Document;

public class ShareAction extends Action {
	
	public ShareAction() {
		putValue(SMALL_ICON, loadIcon("/resource/images/share.png"));
        putValue(NAME, "Share document");
        putValue(SHORT_DESCRIPTION, "Share document");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(MainFrame.getInstance().getTree().getSelectedNode() instanceof Document)
			new DocumentDialog();
	}

}
