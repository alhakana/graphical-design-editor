package factory;

import repository.model.Slot;
import repository.slot.CircleSlot;

public class CircleFactory extends SlotFactory{

	@Override
	public Slot createSlot() {
		return new CircleSlot();
	}

}
