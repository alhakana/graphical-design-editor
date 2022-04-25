package gui.swing.controller.command;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

import gui.swing.controller.Action;
import gui.swing.mainframe.MainFrame;
import repository.model.Page;

public class CommandUndo extends Action {
	
	public CommandUndo() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
				KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_U);
		putValue(SMALL_ICON, loadIcon("/resource/images/command-undo.png"));
		putValue(NAME, "Undo");
		putValue(SHORT_DESCRIPTION, "Undo");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(MainFrame.getInstance().getTree().getSelectedNode() != null && 
				MainFrame.getInstance().getTree().getSelectedNode() instanceof Page) {
				Page page = (Page) MainFrame.getInstance().getTree().getSelectedNode();
				page.undo();
		}
	}

}
