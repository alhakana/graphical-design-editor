package repository.slot;

import notification.ShapeType;
import repository.model.Slot;

public class RectangleSlot extends Slot {

	public RectangleSlot() {
		w = 75;
		h = 50;
		type = ShapeType.RECTANGLE;
	}
}
