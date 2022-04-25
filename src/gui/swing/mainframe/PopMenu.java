package gui.swing.mainframe;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopMenu extends JPopupMenu{
	
	private static PopMenu instance = null;
	
	private PopMenu() {
		makeGUI();
	}
	
	public static PopMenu getInstance() {
		if(instance == null)
			instance = new PopMenu();
		return instance;
	}
	
	private void makeGUI() {
		add(new JMenuItem(MainFrame.getInstance().getActionManager().getRenameAction()));
		
		
		this.setEnabled(true);
		this.pack();
	}
	
}