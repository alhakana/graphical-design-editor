package observer;

import notification.Notification;

public interface IObserver {
	void addObserver(IListener listener);
//	void removeObserver(IListener listener);	//add if needed
	void notifyObservers(Notification notification);
	int countObservers();
}
	