package handler;

import core.EventHandlerInterface;
import notification.Notification;
import observer.IObserverImpl;

public class EventHandlerImpl extends IObserverImpl implements EventHandlerInterface {
	private int ans;
	
	@Override
	public int generateMessage(Notification notification) {
		this.notifyObservers(notification);
		return ans;
	}

	public void setAns(int ans) {
		this.ans = ans;
	}
	
}