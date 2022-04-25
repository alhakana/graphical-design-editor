package gui.swing.state.states;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import gui.swing.mainframe.MainFrame;
import gui.swing.node.view.PageView;
import gui.swing.node.view.SlotView;
import gui.swing.state.State;

public class SelectState extends State {
	private final PageView mediator;
	private Point start;
	private final Rectangle2D rect;
	
	public SelectState(PageView mediator) {
		this.mediator = mediator;
		rect = new Rectangle2D.Double();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		start = e.getPoint();
		
		if(!e.isControlDown())
			mediator.getPageModel().resetColor();
		
		if (e.getButton() == MouseEvent.BUTTON1) {
			int element = mediator.getElementAtPosition(start);
			
			if (element != -1) {
				SlotView slotView = mediator.getSlots().get(element);
				mediator.getPageModel().setSelected(slotView);
			} else
				mediator.getPageModel().setSelected(null);
		}

		MainFrame.getInstance().repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		Point2D drag = e.getPoint();

		if (!e.isControlDown())
			mediator.getPageModel().resetColor();
		
		double xDrag = drag.getX(), yDrag = drag.getY();
		double xStart = start.getX(), yStart = start.getY();
		double width = drag.getX() - start.getX();
		double height = drag.getY() - start.getY();
		
		if (width < 0 && height < 0)
			rect.setRect(xDrag, yDrag, Math.abs(width), Math.abs(height));
		else if (width < 0 && height >= 0)
			rect.setRect(xDrag, yStart, Math.abs(width), Math.abs(height));
		else if (width > 0 && height < 0)
			rect.setRect(xStart, yDrag, Math.abs(width), Math.abs(height));
		else
			rect.setRect(xStart, yStart, Math.abs(width), Math.abs(height));
		
		mediator.getPageModel().setSelectionRectangle(rect);
		mediator.getPageModel().setSelected(mediator.getSlots());
		MainFrame.getInstance().repaint();
	}
	
	@Override
	public void mouseReleased(MouseEvent event) {
		mediator.getPageModel().setSelectionRectangle(new Rectangle2D.Double(0,0,0,0));
		MainFrame.getInstance().repaint();
	}
	
}
