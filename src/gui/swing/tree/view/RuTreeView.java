package gui.swing.tree.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeModel;

import gui.swing.mainframe.MainFrame;
import gui.swing.mainframe.PopMenu;
import gui.swing.tree.controller.TreeCellEditor;
import gui.swing.tree.controller.TreeSelectListener;
import notification.Notification;
import notification.NotificationCode;
import repository.model.Slot;

@SuppressWarnings("serial")
public class RuTreeView extends JTree {
	
	public RuTreeView(DefaultTreeModel treeModel) {
		setModel(treeModel);
		TreeCellRenderer cellRenderer= new TreeCellRenderer();
		addTreeSelectionListener(new TreeSelectListener());
		setCellEditor(new TreeCellEditor(this, cellRenderer));
		setCellRenderer(cellRenderer);
		setEditable(true);
		addMouseListener(getMouseAdapter(this));
	}
	
	private MouseAdapter getMouseAdapter(RuTreeView tree) {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2 && MainFrame.getInstance().getTree().getSelectedNode() instanceof Slot) {
					Slot slot = (Slot)MainFrame.getInstance().getTree().getSelectedNode();
					if(slot.getContent() != null)
						slot.sendMessage(new Notification(NotificationCode.OPEN, null));
				}
				
				if (tree.getRowForLocation(e.getX(), e.getY()) == -1)	return;
				
				if (SwingUtilities.isRightMouseButton(e)) {
					tree.setSelectionPath(tree.getPathForLocation(e.getX(), e.getY()));
					PopMenu pm = PopMenu.getInstance();
					pm.show(tree, e.getX(), e.getY());
				}
			}
		};
	}
	
}
