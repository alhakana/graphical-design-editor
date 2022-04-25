package handler;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import core.SlotHandler;
import repository.model.Slot;

public class SlotHandlerImpl implements SlotHandler {
	private double moveX;
	private double moveY;
	
	@Override
	public void move(ArrayList<Slot> slots, Point2D start, Point2D move) {
		moveX = move.getX() - start.getX();
		moveY = move.getY() - start.getY();
		
		for(Slot s : slots) {
			double newX = s.getXCopy()+moveX;
			double newY = s.getYCopy()+moveY;
			
			s.changePosition(newX, newY);
		}
	}
	
	@Override
	public void changeOriginalPosition(ArrayList<Slot> slots) {
		for(Slot s : slots) 
			s.changeOriginalPosition();
	}
	
	private double distance(Slot slot, Point b) {
		return Math.sqrt((b.x - slot.getX()) * (b.x - slot.getX()) + (b.y - slot.getY()) * (b.y - slot.getY()));
	}

	@Override
	public void resize(ArrayList<Slot> slots, Point start, Point resize) {
		double coef = distance(slots.get(0), resize) / distance(slots.get(0), start);
		
		for(Slot s : slots) 
			s.setCoef(coef);		
	}
	
	@Override
	public void changeOriginalSize(ArrayList<Slot> slots) {
		for(Slot s : slots)
			s.setSize();
	}

	@Override
	public void rotate(ArrayList<Slot> slots, Point start, Point rotate) {
		double x = slots.get(0).getX();
		double y = slots.get(0).getY();
		
		double startX = start.getX() - x;
		double startY = start.getY() - y;
		
		double endX = rotate.getX() - x;
		double endY = rotate.getY() - y;
		
		double angleY = startX * endY - startY * endX;
		double angleX = startX * endX + startY * endY;
		
		double angle = Math.atan2(angleY, angleX);
		
		for(Slot s : slots)
			s.setAngle(angle);		
	}
	
}
