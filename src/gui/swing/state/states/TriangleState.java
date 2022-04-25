package gui.swing.state.states;

import java.awt.Point;
import java.awt.event.MouseEvent;

import command.AddDeviceCommand;
import factory.SlotFactory;
import factory.TriangleFactory;
import gui.swing.mainframe.MainFrame;
import gui.swing.node.view.PageView;
import gui.swing.state.State;
import notification.Notification;
import notification.NotificationCode;
import repository.model.Page;
import repository.node.Node;
import repository.slot.TriangleSlot;

public class TriangleState extends State {
	private final PageView mediator;
	private final SlotFactory factory;
	
	public TriangleState(PageView mediator) {
		this.mediator = mediator;
		factory = new TriangleFactory();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		Point point = e.getPoint();
		Node nodeModel = MainFrame.getInstance().getTree().getSelectedNode();
		
		if (!(nodeModel instanceof Page)) {
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.PAGE_SELECT, null));
			return;
		}
		Page page = (Page)nodeModel;
		
		TriangleSlot ts = (TriangleSlot)factory.makeSlot();
		ts.setName("@Triangle - " + nodeModel.count++);
		ts.setParent(nodeModel);
		ts.coordinates((int)point.getX(), (int)point.getY());
		
		page.addCommand(new AddDeviceCommand("circle", ts));
	}
}	