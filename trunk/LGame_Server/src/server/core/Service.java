package server.core;

public interface Service {
	public String getId();
	public void startup() throws Exception;
	public void shutdown() throws Exception;
}
