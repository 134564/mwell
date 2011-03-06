package server;

import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.SubnodeConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
 
 

import server.core.Platform;
import server.core.event.EventManager;
import server.core.impl.BaseWorld;
import server.core.impl.DefaultAppContext;
import server.core.impl.MapCreatorEx;
import server.core.impl.NormalVMapManager;
import server.core.impl.SimpleUpdater;
import server.io.ClientSessionUpdater;
import server.io.DirectClientSessionService;
import server.packethandler.HandlerDispatch;
import server.packethandler.HandlerDispatchManager;
import server.service.PlayerService;
import server.service.SimpleService;  

public class Server implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(Server.class);
	static BaseWorld world;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Server server = new Server();
		try {
			init();
		} catch (Exception e) { 
			e.printStackTrace();
		}
		server.buildConnections(Platform.getConfiguration().configurationsAt("connections.connection"));
		
		new Thread(server).start();
		log.info("Started Ok");
	}

	private static void init() throws Exception {
		Platform.setAppContext(new DefaultAppContext());

		XMLConfiguration conf = new XMLConfiguration("config.xml");
		Platform.setConfiguration(conf);
		
		//updater
		Platform.setUpdater(new SimpleUpdater());
		
		//event
		Platform.setEventManager(new EventManager());
		Platform.getUpdater().addSyncUpdatable(Platform.getEventManager());

		//ÍøÂç
		ClientSessionUpdater clientSessionUpdater = new ClientSessionUpdater();
		Platform.getUpdater().addSyncUpdatable(clientSessionUpdater);

		HandlerDispatch playerDispatch = new HandlerDispatch(HandlerDispatch.PLAYER);
		HandlerDispatch adminDispatch = new HandlerDispatch(HandlerDispatch.ADMIN);
		HandlerDispatchManager.add(playerDispatch);
		HandlerDispatchManager.add(adminDispatch);
		
		//µØÍ¼
		world = new BaseWorld();
		Platform.setWorld(world);
		
		NormalVMapManager normalVMapManager = new NormalVMapManager(world);
		normalVMapManager.setMapCreator(new MapCreatorEx());
//		normalVMapManager.setUnitCreator(new UnitCreatorEx());
		normalVMapManager.startup();
		Platform.getAppContext().add(normalVMapManager,NormalVMapManager.class);
		
		Platform.getAppContext().create(SimpleService.class, SimpleService.class);
		Platform.getAppContext().create(PlayerService.class, PlayerService.class);
		
		
	}

	private void buildConnections(List<SubnodeConfiguration> l) {
		for (Configuration cfg : l) {
			try {
				if ("direct".equals(cfg.getString("type"))) {
					DirectClientSessionService directClientSessionService = new DirectClientSessionService(cfg,
							HandlerDispatchManager.get(HandlerDispatch.PLAYER));
					directClientSessionService.startup();

					Platform.getAppContext().add(directClientSessionService, DirectClientSessionService.class);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private int		cycle_time	= 80;
	private boolean	runing		= true;

	@Override
	public void run() {
		while (runing) {
			try {
				Platform.getUpdater().update();
			} catch (Throwable e1) {
				log.error(e1.toString());
				e1.printStackTrace();
			}
			
			try {
				Thread.sleep(cycle_time);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
