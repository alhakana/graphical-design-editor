package gui.swing.state.states;

import java.awt.Point;
import java.awt.event.MouseEvent;
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

public class RotateState extends State {
	private final PageView mediator;
	private Point start;
	private Point rotate;
	private final ArrayList<Slot> slots;
	private double prevAngle;
	
	public RotateState(PageView mediator) {
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
		prevAngle = mediator.getSelectedSlots().get(0).getSlotModel().getAngle();
		start = e.getPoint();
		slots.clear();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		rotate = e.getPoint();
		
		List<SlotView> selected = mediator.getSelectedSlots();
	
		for(SlotView sv : selected)
			slots.add(sv.getSlotModel());
		
		MainFrame.getInstance().getSlotHandler().rotate(slots, start, rotate);
	}
		
	@Override
	public void mouseReleased(MouseEvent e) {
		if (!slots.isEmpty())
			mediator.getCommandManager().addCommand(new AddDeviceCommand("rotate", slots, start, rotate, prevAngle));
		slots.clear();
	}
}
