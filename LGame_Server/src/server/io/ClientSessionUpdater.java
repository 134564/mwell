package server.io;

import server.core.Platform;
import server.core.Updatable;
import server.core.event.ServiceEvent;
import server.core.event.ServiceEventListener;
import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.procedure.TObjectProcedure;

public class ClientSessionUpdater implements Updatable, ServiceEventListener {

	protected TIntObjectHashMap<ClientSession> sessions = new TIntObjectHashMap<ClientSession>();

	protected SessionUpdateProcedure procedure = new SessionUpdateProcedure();

	public ClientSessionUpdater() {
		Platform.getEventManager().registerListener(this);
	}

	protected void addClientSession(ClientSession session) {
		sessions.put(session.getId(), session);
	}

	@Override
	public boolean update() {
		sessions.forEachValue(procedure);
		return false;
	}

	protected void removeClientSesion(ClientSession session) {
		sessions.remove(session.getId());
	}

	@Override
	public int[] getEventTypes() {
		return new int[] { ServiceEvent.EVENT_SESSION_ADDED, ServiceEvent.EVENT_SESSION_REMOVED };
	}

	@Override
	public void handleEvent(ServiceEvent event) {
		switch (event.type) {
			case ServiceEvent.EVENT_SESSION_ADDED:
				addClientSession((ClientSession) event.param1);
				break;
			case ServiceEvent.EVENT_SESSION_REMOVED:
				removeClientSesion((ClientSession) event.param1);
				break;
		}
	}

}

class SessionUpdateProcedure implements TObjectProcedure<ClientSession> {
	public boolean execute(ClientSession session) {
		session.update();
		return true;
	}
}
