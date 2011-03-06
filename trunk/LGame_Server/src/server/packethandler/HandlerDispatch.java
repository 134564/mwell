package server.packethandler;

import gnu.trove.map.hash.TIntObjectHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import server.io.ClientSession;
import server.io.Packet;
import server.io.PacketHandler;
 

public class HandlerDispatch implements PacketHandler {
	
	protected static final Logger log = LoggerFactory.getLogger(HandlerDispatch.class);
	
	public static final String PLAYER = "player_dispatch";
	public static final String ADMIN = "admin_dispatch";
	
	protected TIntObjectHashMap<PacketHandler> handlers = new TIntObjectHashMap<PacketHandler>();
	
	/**
	 * dispatch的id，在注册的时候需要此id定位
	 */
	protected String id;
	
	public HandlerDispatch(String id){
		this.id = id;
	}
	
	public String getId(){
		return this.id;
	}

	public void register(int opcode,PacketHandler handler){
		handlers.put(opcode, handler);
	}

	public void register(int[] opcodes,PacketHandler handler){
		for(int opcode:opcodes){
			register(opcode,handler);
		}
	}
	
	public void unRegister(int opcode){
		handlers.remove(opcode);
	}
	
	public void unRegister(int[] opcodes){
		for(int opcode:opcodes){
			handlers.remove(opcode);
		}
	}
	

	@Override
	public void handle(Packet packet, ClientSession session) throws Exception {
		int opcode = packet.getOpCode();
		PacketHandler handler = handlers.get(opcode);
		if(handler!=null){
			long l1 = System.currentTimeMillis();
			handler.handle(packet, session);
			long l2 = System.currentTimeMillis();
			long t = l2 - l1;
			if(t > 30){
				log.info("[OPCODETOOLONG][{},{}]",opcode,t);
			}
		}
	}

}
