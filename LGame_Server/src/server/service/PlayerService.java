package server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import client.nio.OpCode;
 

import server.core.OP;
import server.core.OPHandler;
import server.core.ObjectAccessor;
import server.core.Platform;
import server.core.Player;
import server.core.Service; 
import server.core.World;
import server.core.event.ServiceEvent;
import server.core.map.VMap;
import server.core.map.VMapException;
import server.io.ClientSession; 
import server.io.Packet;

@OPHandler
public class PlayerService implements Service {
	protected static final Logger log = LoggerFactory.getLogger(PlayerService.class);

	@Override
	public String getId() {
		return "PlayerService";
	}

	@Override
	public void startup() throws Exception {
		log.info("PlayerService : startup");
		
	}

	@Override
	public void shutdown() throws Exception {
		log.info("PlayerService : shutdown"); 
	}
	
	@OP(code = OpCode.PLAYER_LOGIN_CLIENT)
	public void login(Packet packet, ClientSession session) {
		String name = packet.getString();
		String pwd = packet.getString();
		double lat = packet.getDouble();
		double lng = packet.getDouble();
		
		Player player = new Player();
		player.setName("player_" + name);
		player.setPosition(lat, lng);
		
		log.info(name + " µÇÂ¼³É¹¦ : " + "[ " + lat+ ", " + lng +"]");
		
		loginSuccess(player);
		session.setClient(player);
		
		Packet pt = new Packet(OpCode.PLAYER_LOGIN_SERVER);		
		pt.put(player.toClientBytes());
		
		session.send(pt);
	}

	private void loginSuccess(Player player) {
		World baseWorld = Platform.getWorld();
		
		try {
			VMap map = player.getVMap();
			map = baseWorld.addPlayer(player, player.getMap().getId(),player.getX(), player.getY(), false);
		} catch (VMapException e) { 
			e.printStackTrace();
		}
		Platform.getEventManager().addEvent(
				new ServiceEvent(ServiceEvent.EVENT_PLAYER_LOADED,
						player, null));
		ObjectAccessor.addGameObject(player);
		
		
		
	}

}
