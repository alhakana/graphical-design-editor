package core;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import repository.model.Slot;

public interface SlotHandler {

	void move(ArrayList<Slot> slots, Point2D start, Point2D move);
	void changeOriginalPosition(ArrayList<Slot> slots);
	void resize(ArrayList<Slot> slots, Point start, Point resize);
	void changeOriginalSize(ArrayList<Slot> slots);
	void rotate(ArrayList<Slot> slots, Point start, Point rotate);

}
