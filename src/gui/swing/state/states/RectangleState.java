package gui.swing.state.states;

import java.awt.Point;
import java.awt.event.MouseEvent;

import command.AddDeviceCommand;
import factory.RectangleFactory;
import factory.SlotFactory;
import gui.swing.mainframe.MainFrame;
import gui.swing.node.view.PageView;
import gui.swing.state.State;
import notification.Notification;
import notification.NotificationCode;
import repository.model.Page;
import repository.node.Node;
import repository.slot.RectangleSlot;

public class RectangleState extends State {
	private final PageView mediator;
	private final SlotFactory factory;
	
	public RectangleState(PageView mediator) {
		this.mediator = mediator;
		factory = new RectangleFactory();
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
		
		RectangleSlot rs = (RectangleSlot)factory.makeSlot();
		rs.setName("@Rectangle - " + nodeModel.count++);
		rs.setParent(nodeModel);
		rs.coordinates((int)point.getX(), (int)point.getY());
		
		page.addCommand(new AddDeviceCommand("circle", rs));
	}
}
	