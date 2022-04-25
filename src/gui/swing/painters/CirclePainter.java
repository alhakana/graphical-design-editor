package gui.swing.painters;

import java.awt.Point;
import java.awt.geom.GeneralPath;
import gui.swing.node.view.SlotView;

public class CirclePainter extends ElementPainter {
	
	public CirclePainter(SlotView slotView) {
		makeShape(slotView);
	}
	
	public void makeShape(SlotView slotView) {
		shape = new GeneralPath();
		
		double x = slotView.getSlotModel().getX();
		double y = slotView.getSlotModel().getY();
		double w = slotView.getSlotModel().getWCopy();
		double h = slotView.getSlotModel().getHCopy();
		
		Point a = new Point((int) (x - w/2), (int) (y - h/2));
		
		((GeneralPath)shape).moveTo(a.getX() + w/2,  a.getY());
		
		((GeneralPath)shape).quadTo(a.getX() + w, a.getY(), 
									a.getX() + w, a.getY() + h/2);
		
		((GeneralPath)shape).quadTo(a.getX() + w, a.getY() + h,
									a.getX() + w/2, a.getY()+ h);
		
		((GeneralPath)shape).quadTo(a.getX(), a.getY() + h,
									a.getX(), a.getY() + h/2);

		((GeneralPath)shape).quadTo(a.getX(), a.getY(),
									a.getX( )+ w/2,a.getY());
	}

}
