package server.io;

public interface Client {
	public void setSession(ClientSession session);
	public ClientSession getSession();
}
