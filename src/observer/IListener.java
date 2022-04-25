package observer;

import notification.Notification;

public interface IListener {
	void update(Notification notification);
}
