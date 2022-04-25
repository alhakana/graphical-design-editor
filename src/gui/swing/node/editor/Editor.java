package gui.swing.node.editor;

import javax.swing.JDialog;

import gui.swing.mainframe.MainFrame;
import gui.swing.node.view.SlotView;

public class Editor extends JDialog {
	
	protected SlotView slotView;

	public Editor(SlotView slotView) {
		super(MainFrame.getInstance(), "", true);
		
		this.slotView = slotView;
		
		settings();
	}
	
	private void settings() {
		setSize(500,500);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	public SlotView getSlotView() {
		return slotView;
	}
}
