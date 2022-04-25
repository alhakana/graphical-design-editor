package gui.swing.controller.command;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import gui.swing.controller.Action;
import gui.swing.mainframe.MainFrame;
import repository.model.Page;

public class CommandRedo extends Action {
	
	public CommandRedo() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));
		putValue(SMALL_ICON, loadIcon("/resource/images/command-redo.png"));
		putValue(NAME, "Undo");
		putValue(SHORT_DESCRIPTION, "Undo");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(MainFrame.getInstance().getTree().getSelectedNode() != null && 
				MainFrame.getInstance().getTree().getSelectedNode() instanceof Page) {
				Page page = (Page) MainFrame.getInstance().getTree().getSelectedNode();
				page.redo();
		}
	}

}
