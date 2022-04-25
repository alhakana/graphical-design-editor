package command;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.model.Page;
import repository.model.Slot;
import repository.node.Node;

public class AddDeviceCommand implements Command {
	private final String deviceType;
	private Slot slot;
	private ArrayList<Slot> slots;
	private Point start;
	private Point end;
	private double prevAngle;
	
	@Override
	public void doCommand() {
		Node nodeModel = MainFrame.getInstance().getTree().getSelectedNode();
		
		if (!(nodeModel instanceof Page)) {
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.PAGE_SELECT, null));
			return;
		}
		Page page = (Page)nodeModel;
		
		switch(deviceType) {
		case "circle": case "rectangle": case "triangle":
			slot.setParent(page);
			MainFrame.getInstance().getTree().addSlot(slot);
			page.sendMessage(new Notification(NotificationCode.NEW_SLOT, slot));
			break;
		case "delete":
//			page.getChildren().remove(slot);
			slot.sendMessage(new Notification(NotificationCode.DELETE_ME, null));
			break;
		case "move":
			MainFrame.getInstance().getSlotHandler().move(slots, start, end);
			MainFrame.getInstance().getSlotHandler().changeOriginalPosition(slots);
			break;
		case "resize":
			MainFrame.getInstance().getSlotHandler().resize(slots, start, end);
			MainFrame.getInstance().getSlotHandler().changeOriginalSize(slots);
			break;
		case "rotate":
			MainFrame.getInstance().getSlotHandler().rotate(slots, start, end);
			break;
		default:
			break;
		}
	}

	@Override
	public void undoCommand() {
		Node nodeModel = MainFrame.getInstance().getTree().getSelectedNode();
		
		if (!(nodeModel instanceof Page)) {
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.PAGE_SELECT, null));
			return;
		}
		Page page = (Page)nodeModel;
		
		switch(deviceType) {
		case "circle": case "rectangle": case "triangle":
			slot.deleteMe();
			break;
		case "delete":
			slot.setParent(page);
			MainFrame.getInstance().getTree().addSlot(slot);
			page.sendMessage(new Notification(NotificationCode.NEW_SLOT, slot));
			break;
		case "move":
			MainFrame.getInstance().getSlotHandler().move(slots, end, start);
			MainFrame.getInstance().getSlotHandler().changeOriginalPosition(slots);
			break;
		case "resize":
			MainFrame.getInstance().getSlotHandler().resize(slots, end, start);
			MainFrame.getInstance().getSlotHandler().changeOriginalSize(slots);
			break;
		case "rotate":
			for(Slot s : slots)
				s.setAngle(prevAngle);
			break;
		default:
			break;
		}
	}

	
	public AddDeviceCommand(String deviceType, Slot slot) {
		this.deviceType = deviceType;
		this.slot = slot;
	}

	public AddDeviceCommand(String deviceType, ArrayList<Slot> slots, Point2D start, Point2D end) {
		this.deviceType = deviceType;
		this.slots = new ArrayList<>();
		this.slots.addAll(slots);
		this.start = (Point) start;
		this.end = (Point) end;
	}
	
	public AddDeviceCommand(String deviceType, ArrayList<Slot> slots, Point2D start, Point2D end, double prevAngle) {
		this.deviceType = deviceType;
		this.slots = new ArrayList<>();
		this.slots.addAll(slots);
		this.start = (Point) start;
		this.end = (Point) end;
		this.prevAngle = prevAngle;
	}
}
