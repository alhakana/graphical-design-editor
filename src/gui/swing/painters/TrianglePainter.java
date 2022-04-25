package gui.swing.painters;

import java.awt.geom.GeneralPath;
import gui.swing.node.view.SlotView;

public class TrianglePainter extends ElementPainter {
	
	public TrianglePainter(SlotView slotView) {
		makeShape(slotView);
	}
	
	public void makeShape(SlotView slotView) {
		shape = new GeneralPath();
		
		double x = slotView.getSlotModel().getX();
		double y = slotView.getSlotModel().getY();
		double w = slotView.getSlotModel().getWCopy();
		double h = slotView.getSlotModel().getHCopy();
		double a = slotView.getSlotModel().getAngle();
		
		double cosw = w/2*Math.cos(a);
		double sinw = w/2*Math.sin(a);
		double cosh = h*Math.cos(a);
		double sinh = h*Math.sin(a);
		
		((GeneralPath) shape).moveTo(sinh*2/3 + x, y - cosh*2/3);
		((GeneralPath) shape).lineTo(cosw + x - sinh/3, y + sinw + cosh/3);
		((GeneralPath) shape).lineTo(x - cosw - sinh/3, y - sinw + cosh/3);
		((GeneralPath) shape).closePath();
	}
}
