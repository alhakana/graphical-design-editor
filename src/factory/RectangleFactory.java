package factory;

import repository.model.Slot;
import repository.slot.RectangleSlot;

public class RectangleFactory extends SlotFactory{

	@Override
	public Slot createSlot() {
		return new RectangleSlot();
	}

}
