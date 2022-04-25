package observer;

import java.util.ArrayList;
import java.util.List;

import notification.Notification;

/**
 * Important for those who extend this class. If there is a need to extend one more class
 * it is possible to implement interface IObserver and redefine those methods.
 * */
public class IObserverImpl implements IObserver {
	
	private List<IListener> listeners;
	@Override
	public void addObserver(IListener listener) {
		if(listener == null)
			return;
		if(listeners == null)
			listeners= new ArrayList<>();
		if(!listeners.contains(listener))
			listeners.add(listener);

	}
	
	public List<IListener> getListeners() {
		return listeners;
	}
//	@Override
//	public void removeObserver(IListener listener) {
//		if(listener == null || listeners == null || !listeners.contains(listener))
//			return;
//		listeners.remove(listener);
//	}

	@Override
	public void notifyObservers(Notification notification) {
		if(listeners == null || listeners.isEmpty() || notification == null)
			return;
		
		for(IListener lis : listeners)
			lis.update(notification);

	}

	@Override
	public int countObservers() {
		if(listeners == null || listeners.size() < 1)
			return 0;
		return listeners.size();
	}

}
