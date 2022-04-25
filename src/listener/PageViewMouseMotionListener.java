package listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import gui.swing.mainframe.MainFrame;
import gui.swing.node.view.PageView;
import gui.swing.state.State;
import notification.Notification;
import notification.NotificationCode;
import repository.model.Page;
import repository.node.Node;

public class PageViewMouseMotionListener implements MouseMotionListener {
	private PageView pv;
	
	public PageViewMouseMotionListener(PageView pv) {
		this.pv = pv;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Node node = MainFrame.getInstance().getTree().getSelectedNode();
		State state = pv.getManager().getCurrentState();
		Page model = pv.getPageModel();
		
		if (!(node instanceof Page) || state == null)
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.PAGE_SELECT, null));
		else if (node.equals(model))
			state.mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

}
