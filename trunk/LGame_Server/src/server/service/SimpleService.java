package server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import client.nio.OpCode;
 

import server.core.OP;
import server.core.OPHandler;
import server.core.Service; 
import server.io.ClientSession; 
import server.io.Packet;

@OPHandler
public class SimpleService implements Service {
	protected static final Logger log = LoggerFactory.getLogger(SimpleService.class);

	@Override
	public String getId() {
		return "SimpleService";
	}

	@Override
	public void startup() throws Exception {
		log.info("SimpleService : startup");
		
	}

	@Override
	public void shutdown() throws Exception {
		log.info("SimpleService : shutdown"); 
	}
	
	@OP(code = OpCode.TEST_OP_CLIENT)
	public void testOp(Packet packet, ClientSession session) {
		int val = packet.getInt();
		
		val += 10;
		
		Packet pt = new Packet(OpCode.TEST_OP_SERVER);
		pt.putInt(val);
		
		session.send(pt);
	}

}
