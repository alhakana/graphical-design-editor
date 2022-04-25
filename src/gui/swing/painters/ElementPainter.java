package gui.swing.painters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import gui.swing.node.view.SlotView;

public abstract class ElementPainter {
	protected Shape shape;
	private Color color = new Color(255,102,178);
	
	public void paint(Graphics2D g, SlotView slotView) {
		g.setPaint(color);
		g.setStroke(slotView.getStroke());
		g.draw(shape);
	}
	
	public boolean elementAt(Point position) {
		return shape.contains(position);
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
