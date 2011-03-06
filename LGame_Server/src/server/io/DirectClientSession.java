package server.io;

import java.net.InetSocketAddress; 

import org.apache.mina.common.IoSession;
 

public class DirectClientSession extends AbstractClientSession{
	protected String ip;
	
	public DirectClientSession(ClientSessionService service, IoSession session, PacketHandler handler) {
		super(service, session, handler);

		InetSocketAddress address = (InetSocketAddress)session.getRemoteAddress();
		ip = address.getAddress().getHostAddress();
	}
	
	@Override
	public void close() {
		super.close();
		session.close();
	}

	@Override
	public boolean isConnected() {
		return state==State.CONNECTED||state==State.AUTHENTICATED;
	}

	@Override
	public String getClientIP() {
		return ip;
	}

	@Override
	public boolean checkOnlineCount(int currentLoginedAccounts) {
		return true;
	}

}
