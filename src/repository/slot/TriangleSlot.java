package repository.slot;

import notification.ShapeType;
import repository.model.Slot;

public class TriangleSlot extends Slot {

	public TriangleSlot() {
		w = 75;
		h = 75;
		type = ShapeType.TRIANGLE;
	}
}
