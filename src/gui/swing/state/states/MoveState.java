package gui.swing.state.states;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import command.AddDeviceCommand;
import gui.swing.mainframe.MainFrame;
import gui.swing.node.view.PageView;
import gui.swing.node.view.SlotView;
import gui.swing.state.State;
import notification.Notification;
import notification.NotificationCode;
import repository.model.Slot;

public class MoveState extends State {
	private final PageView mediator;
	private Point2D start;
	private Point2D move;
	private final ArrayList<Slot> slots;
	
	public MoveState(PageView mediator) {
		this.mediator = mediator;
		slots = new ArrayList<>();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		List<SlotView> selected = mediator.getSelectedSlots();
		
		if (selected.isEmpty()) {
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.SLOT_SELECT, null));
			return;
		}
		
		mediator.colorSelected();
		start = e.getPoint();
		slots.clear();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		move = e.getPoint();
		
		List<SlotView> selected = mediator.getSelectedSlots();

		for(SlotView sv : selected)
			slots.add(sv.getSlotModel());
		
		MainFrame.getInstance().getSlotHandler().move(slots, start, move);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if (!slots.isEmpty())
			mediator.getCommandManager().addCommand(new AddDeviceCommand("move", slots, start, move));
		slots.clear();
	}
	
}
