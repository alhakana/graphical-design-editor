package factory;

import repository.model.Slot;
import repository.slot.TriangleSlot;

public class TriangleFactory extends SlotFactory{

	@Override
	public Slot createSlot() {
		return new TriangleSlot();
	}
	
}
