package server.io;

import server.core.Service; 


public interface ClientSessionService extends Service{
	public void addClientSession(ClientSession session);
	public void removeClientSession(ClientSession session);
}
