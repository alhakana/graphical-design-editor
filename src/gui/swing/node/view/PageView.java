package gui.swing.node.view;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import command.Command;
import command.CommandManager;
import gui.swing.mainframe.MainFrame;
import gui.swing.painters.ElementPainter;
import gui.swing.state.StateManager;
import listener.PageViewMouseAdapter;
import listener.PageViewMouseMotionListener;
import notification.Notification;
import observer.IListener;
import repository.model.Page;
import repository.model.Slot;
import repository.node.Node;


public class PageView extends JPanel implements IListener {
	private final Page pageModel;
	private final DocumentView containerParent;
	private final JLabel label;
	private StateManager manager;
	private CommandManager commandManager;
	private final List<SlotView> slots;
	private final List<SlotView> selectedSlots;
	private Rectangle2D selectionRectangle;
	
	public PageView(Page pageModel, DocumentView containerParent) {
		setMaximumSize(new Dimension(300, 500));
		setBackground(new Color(250, 209, 111, 75));
		
		this.containerParent = containerParent;
		this.pageModel = pageModel;
		pageModel.addObserver(this);
		
		addMouseListener(new PageViewMouseAdapter(this));
		addMouseMotionListener(new PageViewMouseMotionListener(this));
		
		selectionRectangle = new Rectangle2D.Double();
		selectedSlots = new ArrayList<>();
		slots = new ArrayList<>();
		manager = new StateManager(this);
		
		if(pageModel.countObservers() < 2)
			commandManager = new CommandManager();
		else
			commandManager = null;
		
		label = new JLabel(containerParent.getContainerParent().getProjectModel().getName() + " - " + 
							containerParent.getDocumentModel().getName() + " - " + pageModel.getName());
		add(label);
	}
		
	@Override
	public void update(Notification notification) {
		if (notification.getNotificationCode() != null) {
			switch(notification.getNotificationCode()) {
			case DELETE_ME:
				deleteMe();
				break;
			case NEW_SLOT:
				addSlot((Node)notification.getObject(), false);
				MainFrame.getInstance().repaint();
				break;
			case LABEL:
				labelNameChanged();
				break;
			case SET_FOCUS:
				focus();
				break;
			case RECTANGLE:
				resetColor(new Color(255,102,178));
				getManager().setRectangleState();
				break;
			case TRIANGLE:
				resetColor(new Color(255,102,178));
				getManager().setTriangleState();
				break;
			case CIRCLE:
				resetColor(new Color(255,102,178));
				getManager().setCircleState();
				break;
			case RESET_COLOR:
				resetColor(new Color(255,102,178));
				break;
			case SELECT:
				if (notification.getObject() instanceof SlotView) {
					SlotView slotView = (SlotView)notification.getObject();
					selectSlot(slotView);
				}
				else selectSlots();
				
				getManager().setSelectState();
				break;
			case SELECTION_RECTANGLE:
				setSelectionRectangle((Rectangle2D) notification.getObject());
				break;
			case MOVE:
				colorSelected();
				getManager().setMoveState();
				break;
			case RESIZE:
				colorSelected();
				getManager().setResizeState();
				break;
			case ROTATE:
				colorSelected();
				getManager().setRotateState();
				break;
			case DELETE:
				colorSelected();
				getManager().setDeleteState();
				break;
			case UNDO:
				if (commandManager != null)
					commandManager.undoCommand();
				break;
			case REDO:
				if (commandManager != null)
					commandManager.doCommand();
				break;
			case ADD_COMMAND:
				if (commandManager != null)
					commandManager.addCommand((Command) notification.getObject());
				break;
			default:
				break;
			}
		} 
	}
	
	public void colorSelected() {
		for(SlotView sv : slots) 
			if (!selectedSlots.contains(sv))
				sv.getPainter().setColor(new Color(255,102,178));
		for(SlotView sv : selectedSlots) {
			sv.getPainter().setColor(new Color(0, 204, 255));
		}
	}

	public void resetColor(Color color) {
		for(SlotView sv : slots)
			sv.getPainter().setColor(color);
		selectedSlots.clear();
	}
	
	public int getElementAtPosition(Point point) {
		for(SlotView sv : slots) {
			if (sv.getPainter().elementAt(point))
				return slots.indexOf(sv);
		}
		
		return -1;
	}
	
	private void selectSlot(SlotView slotView) {
		for (SlotView sv : slots)
			if (sv.getSlotModel().equals(slotView.getSlotModel()))
				slotView = sv;
		
		slotView.getPainter().setColor(new Color(0, 204, 255));
		if (!selectedSlots.contains(slotView))
			selectedSlots.add(slotView);
	}
	
	public void selectSlots() {
		for(SlotView sv : slots)	
			if (sv.intersect(selectionRectangle) && !selectedSlots.contains(sv)) {
				sv.getPainter().setColor(new Color(0,204,255));
				selectedSlots.add(sv);
			}
	}
	
	public void addSlot(Node slot, boolean selected) {
		SlotView sv = new SlotView((Slot)slot, this);
		if (selected)
			sv.getPainter().setColor(new Color(0, 204, 255));
		slots.add(sv);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D graphics = (Graphics2D)g;
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		Iterator<SlotView> it = slots.iterator();
		while(it.hasNext()) {
			SlotView sv = it.next();
			ElementPainter painter = sv.getPainter();
			painter.paint(graphics, sv);
		}
		
		graphics.setColor(Color.BLACK);
		graphics.setStroke(new BasicStroke((float)1, BasicStroke.CAP_SQUARE, 
				BasicStroke.JOIN_BEVEL, 1f, new float[]{(float)3, (float)6}, 0 ));
		graphics.draw(selectionRectangle);	
		
		MainFrame.getInstance().repaint();
	}
	
	public void focus() {
		if (MainFrame.getInstance().getTree().getTreeItem(1).getNodeModel().equals(containerParent.getContainerParent().getProjectModel()))
			containerParent.putMeInFocus();
	}
	
	private void deleteMe() {
		// if document is shared delete all replicas of page views
		if(pageModel.getParent().getParent().getName().equals(containerParent.getContainerParent().getProjectModel().getName())) 
			MainFrame.getInstance().getTree().getTreeItem(2).remove(MainFrame.getInstance().getTree().getTreeItem(2).getIndex(MainFrame.getInstance().getTree().getSelectedTreeItem()));
		
		containerParent.getPages().remove(this);
		containerParent.getDocumentModel().getChildren().remove(pageModel);
		containerParent.remove(this);
		containerParent.getContainerParent().repaint();
		MainFrame.getInstance().getWorkspaceTree().setSelectionRow(0);
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());	
	}
	
	public void labelNameChanged() {
		label.setText(containerParent.getContainerParent().getProjectModel().getName() + " - " + 
						containerParent.getDocumentModel().getName() + " - " + pageModel.getName());
		MainFrame.getInstance().repaint();
	}

	public DocumentView getContainerParent() {
		return containerParent;
	}

	public Page getPageModel() {
		return pageModel;
	}

	public StateManager getManager() {
		return manager;
	}

	public void setManager(StateManager manager) {
		this.manager = manager;
	}

	public List<SlotView> getSlots() {
		return slots;
	}

	public List<SlotView> getSelectedSlots() {
		return selectedSlots;
	}

	public void setSelectionRectangle(Rectangle2D rect) {
		selectionRectangle = rect;
	}
	
	public CommandManager getCommandManager() {
		return commandManager;
	}

	public void setCommandManager(CommandManager commandManager) {
		this.commandManager = commandManager;
	}
	
}