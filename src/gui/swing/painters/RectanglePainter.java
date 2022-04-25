package gui.swing.painters;

import java.awt.geom.GeneralPath;
import gui.swing.node.view.SlotView;

public class RectanglePainter extends ElementPainter {
	
	public RectanglePainter(SlotView slotView) {
		makeShape(slotView);
	}
	
	private void makeShape(SlotView slotView) {
		shape = new GeneralPath();
		
		double x = slotView.getSlotModel().getX();
		double y = slotView.getSlotModel().getY();
		double w = slotView.getSlotModel().getWCopy();
		double h = slotView.getSlotModel().getHCopy();
		double a = slotView.getSlotModel().getAngle();
		
		double cosw = w/2*Math.cos(a);
		double sinw = w/2*Math.sin(a);
		double cosh = h/2*Math.cos(a);
		double sinh = h/2*Math.sin(a);
		
		((GeneralPath) shape).moveTo(x - cosw + sinh, y - sinw - cosh);
		((GeneralPath) shape).lineTo(x + cosw + sinh, y + sinw - cosh);
		((GeneralPath) shape).lineTo(x + cosw - sinh, y + sinw + cosh);
		((GeneralPath) shape).lineTo(x - cosw - sinh, y - sinw + cosh);
		((GeneralPath) shape).closePath();
	}
}
