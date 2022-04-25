package app;

import core.ApplicationFramework;
import notification.Notification;

/**
 * Central part of microkernel architecture.
 */
public class AppCore extends ApplicationFramework {
	private static AppCore instance = null;

	private AppCore() {}
	
	public static AppCore getInstance() {
		if (instance == null)
			instance = new AppCore();
		return instance;
	}
	
	@Override
	public void run(Notification notification) {
		gui.start(notification);
	}
}