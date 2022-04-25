package factory;

import repository.model.Slot;

public abstract class SlotFactory {
	
	public Slot makeSlot() {
		Slot slot;
		slot = createSlot();
		return slot;
	}
	
	public abstract Slot createSlot();
	
}
