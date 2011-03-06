package server.core.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import server.core.Updatable;
 


public class EventManager implements Updatable{
	protected Map<Integer, List<ServiceEventListener>> listeners = new HashMap<Integer, List<ServiceEventListener>>();
	protected Queue<ServiceEvent> events = new ConcurrentLinkedQueue<ServiceEvent>();
	
	public void registerListener(ServiceEventListener listener) {
		int[] types = listener.getEventTypes();
		for (int type : types) {
			List<ServiceEventListener> lls = listeners.get(type);
			if (lls == null) {
				lls = new ArrayList<ServiceEventListener>();
				listeners.put(type, lls);
			}
			lls.add(listener);
		}
	}
	
	public void unregisterListener(ServiceEventListener listener) {
		for (List<ServiceEventListener> lls : listeners.values()) {
			lls.remove(listener);
		}
	}
	
	public void addEvent(ServiceEvent event) {
		events.add(event);
	}
	
	public boolean update() {
		while (!events.isEmpty()) {
			ServiceEvent evt = events.remove();
			List<ServiceEventListener> lls = listeners.get(evt.type);
			if (lls != null) {
				for (ServiceEventListener l : lls) {
					try {
						l.handleEvent(evt);
					} catch (Exception e) {
						//log.error(e, e);
					}
				}
			}
		}
		return false;
	}
	
	public void fireEvent(ServiceEvent event) {
		List<ServiceEventListener> lls = listeners.get(event.type);
		if (lls != null) {
			for (ServiceEventListener l : lls) {
				try {
					l.handleEvent(event);
				} catch (Exception e) {
					//log.error(e, e);
				}
			}
		}
	}
}
