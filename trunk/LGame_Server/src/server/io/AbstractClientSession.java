package server.io;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.mina.common.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class AbstractClientSession implements ClientSession{
	
	protected static final long DISCONNECTING_TIME = 4 * 60 * 1000L;
	
	private static final Logger log = LoggerFactory.getLogger(AbstractClientSession.class);

	protected IoSession session;
	
	protected enum State{CONNECTED,DISCONNECTING,DISCONNECTED,AUTHENTICATED,DISCHARGED};
	
	protected volatile State state = State.CONNECTED;
	
	protected PacketHandler handler;

	protected Client client;
	
	protected ArrayBlockingQueue<Packet> queue = new ArrayBlockingQueue<Packet>(1024);
	
	protected ArrayBlockingQueue<AsyncCall> calls = new ArrayBlockingQueue<AsyncCall>(256);
	
	protected ClientSessionService service;
	
	protected Identity identity;
	
	protected int id;
	
	protected static final AtomicInteger id_gen = new AtomicInteger(0);
	
	protected long lastReceiveStamp = System.currentTimeMillis();
	
	protected long disconnectedStamp = 0L;
	
	public AbstractClientSession(ClientSessionService service,IoSession session,PacketHandler handler){
		this.id = ClientSessionUtil.getSessionId();
		this.service = service;
		this.session = session;
		this.handler = handler;
	}
	
	public AbstractClientSession(ClientSessionService service,IoSession session,PacketHandler handler,int id){
		this.id = id;
		this.service = service;
		this.session = session;
		this.handler = handler;
	}
	
	public void send(Packet packet){
		if(state==State.CONNECTED||state==State.AUTHENTICATED)
			session.write(packet);
	}
	
	public PacketHandler getHandler(){
		return handler;
	}
	
	public IoSession getIoSession(){
		return session;
	}

	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
		if (client != null) {
			client.setSession(this);
		}
	}
	
	public void keepLive() {
		lastReceiveStamp = System.currentTimeMillis();
	}
	
	public void addPacket(Packet packet){
		queue.offer(packet);
		lastReceiveStamp = System.currentTimeMillis();
	}
	
	
	public boolean update(){
		if ((state != State.DISCONNECTING && state != State.DISCONNECTED)
				&& (System.currentTimeMillis() - lastReceiveStamp) > DISCONNECTING_TIME) {
			setDisconnecting();
		}
		Packet packet = null;
		PacketHandler handler = getHandler();
		while((packet=queue.poll())!=null){
			try {
				handler.handle(packet, this);
			} catch (Exception e) {
				log.error(e.toString(),e);
			}
		}
		AsyncCall call = null;
		while((call = calls.poll())!=null){
			try {
				call.callFinish();
			} catch (Exception e) {
				log.error(e.toString(),e);
			}
		}
		if(state == State.DISCONNECTED){
			service.removeClientSession(this);
		}
		if(state == State.DISCONNECTING){
			setDisconnected();
			return true;
		}
		return false;
	}
	
	public void cleanMessageQueue(){
		queue.clear();
	}
	
	public void addAsyncCall(AsyncCall call){
		calls.add(call);
	}
	
	public void close() {
		if (isConnected()) {
			cleanMessageQueue();
			setDisconnecting();
		}
	}
	
	protected void setDisconnecting(){
		state = State.DISCONNECTING;
		addPacket(new Packet(OpCode.DISCONNECTED));
	}
	
	protected void setDisconnected(){
		state = State.DISCONNECTED;
		disconnectedStamp = System.currentTimeMillis();
	}
	
	public void authenticate(Identity identity) throws SecurityException{
		this.identity = identity;
		state = State.AUTHENTICATED;
	}
	
	public Identity getIdentity(){
		return identity;
	}
	
	public int getId(){
		return id;
	}
}
