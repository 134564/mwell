package server.core.event;

public interface ServiceEventListener {
	public int[] getEventTypes();
	
	public void handleEvent(ServiceEvent event);
}
