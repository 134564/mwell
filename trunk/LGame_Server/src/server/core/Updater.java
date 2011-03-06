package server.core;

public interface Updater {
	
	public void addSyncUpdatable(Updatable updatable);
	public void addAsyncUpdatable(Updatable updatable);
	public void update();
}
