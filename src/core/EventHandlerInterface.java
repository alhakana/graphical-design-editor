package core;

import notification.Notification;

/**
 * Interface for handling messages.
 */
public interface EventHandlerInterface {
	int generateMessage(Notification notification);
}
