package gui.swing.mainframe;

import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {
		public MenuBar() {
			JMenu menuFile = new JMenu("File");
			menuFile.setMnemonic(KeyEvent.VK_F);
		
			JMenu menuNew = new JMenu("New");
			menuNew.add(MainFrame.getInstance().getActionManager().getNewProjectAction());
			menuNew.add(MainFrame.getInstance().getActionManager().getNewDocumentAction());
			menuNew.add(MainFrame.getInstance().getActionManager().getNewPageAction());
		
			menuFile.add(menuNew);

			JMenu menuDelete = new JMenu("Delete");
			menuDelete.add(MainFrame.getInstance().getActionManager().getDeleteAction());
		
			menuFile.add(menuDelete);
			
			JMenu menuEdit = new JMenu("Edit");
			menuEdit.add(MainFrame.getInstance().getActionManager().getRenameAction());
			
			menuFile.add(menuEdit);
			
			add(menuFile);
			
			JMenu menuHelp = new JMenu("Help");
			menuHelp.add(MainFrame.getInstance().getActionManager().getAboutAction());
			
			add(menuHelp);	
		}
}