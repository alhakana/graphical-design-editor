package core;

import gui.swing.controller.ActionManager;
import notification.Notification;

public abstract class ApplicationFramework {
	protected Gui gui;
	protected Repository repository;
	protected EventHandlerInterface eventHandler;
	protected SerializationHandlerInterface serializationHandler;
	protected FileUtilHandler fileUtilHandler;
	protected SlotHandler slotHandler;
	protected ActionManager actionManager;
	
	public void initialize(Gui gui, Repository repository, EventHandlerInterface eventHandler, SerializationHandlerInterface serializationHandler, FileUtilHandler fileUtilHandler, SlotHandler slotHandler, ActionManager actionManager) {
		this.gui = gui;
		this.repository = repository;
		this.eventHandler = eventHandler;
		this.serializationHandler = serializationHandler;
		this.fileUtilHandler = fileUtilHandler;
		this.slotHandler = slotHandler;
		this.actionManager = actionManager;
	}
	
	/**
	 * Starts GUI.
	 */
    public abstract void run(Notification notification);

	public Gui getGui() {
		return gui;
	}

	public Repository getRepository() {
		return repository;
	}

	public EventHandlerInterface getEventHandler() {
		return eventHandler;
	}

	public SerializationHandlerInterface getSerializationHandler() {
		return serializationHandler;
	}

	public FileUtilHandler getFileUtilHandler() {
		return fileUtilHandler;
	}

	public SlotHandler getSlotHandler() {
		return slotHandler;
	}

	public ActionManager getActionManager() {
		return actionManager;
	}
	
}