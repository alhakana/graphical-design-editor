package gui.swing.mainframe;

import java.awt.Dimension;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class ToolBar extends JToolBar {
	
	public ToolBar() {
		super(SwingConstants.HORIZONTAL);
		
		add(MainFrame.getInstance().getActionManager().getOpenWorkspace());
		addSeparator(new Dimension(50,0));
		add(MainFrame.getInstance().getActionManager().getOpenProject());
		addSeparator(new Dimension(70,40));


		add(MainFrame.getInstance().getActionManager().getSaveWorkspace());
		addSeparator(new Dimension(50,0));
		add(MainFrame.getInstance().getActionManager().getSaveAsProject());
		addSeparator(new Dimension(50,0));
		add(MainFrame.getInstance().getActionManager().getSaveProject());
		addSeparator(new Dimension(70,40));

		add(MainFrame.getInstance().getActionManager().getNewProjectAction());
		addSeparator(new Dimension(50,0));
		add(MainFrame.getInstance().getActionManager().getNewDocumentAction());
		addSeparator(new Dimension(50,0));
		add(MainFrame.getInstance().getActionManager().getNewPageAction());
		addSeparator(new Dimension(70,40));


		add(MainFrame.getInstance().getActionManager().getDeleteAction());
		addSeparator(new Dimension(70,40));


		add(MainFrame.getInstance().getActionManager().getRenameAction());
		addSeparator(new Dimension(50,0));
		add(MainFrame.getInstance().getActionManager().getShareAction());
		addSeparator(new Dimension(70,40));


		add(MainFrame.getInstance().getActionManager().getCommandUndo());
		addSeparator(new Dimension(50,0));
		add(MainFrame.getInstance().getActionManager().getCommandRedo());

	}
}
