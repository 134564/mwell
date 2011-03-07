package client.event;

import java.util.HashMap;
import java.util.Map;

public class Event {
	private static Map<Integer, Eventable> eventListener = new HashMap<Integer, Eventable>();
	
	public static void fire(int eventCode, Object... o) {
		Eventable event = eventListener.get(eventCode);
		if (event != null) {
			event.handEvent(eventCode, o);
		}
	}
	
	public static void add(int eventCode, Eventable event) { 
		eventListener.put(eventCode, event);
	}

}
