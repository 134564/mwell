package server.core;

import org.apache.commons.configuration.XMLConfiguration;

import server.core.event.EventManager;
  

public class Platform {
	
	static AppContext appContext; 
	static EventManager eventManager;
//	static CombatService combatService;
	static XMLConfiguration configuration;
	static ThreadPool pool;
	static Updater updater;
	static World world;
	static String gameCode; 
	static float expRatio;
	static int serverId;
	
	public static void setExpRatio(float ratio){
		expRatio = ratio;
	}
	
	public static float getExpRatio(){
		return expRatio;
	}
	
	public static void setGameCode(String code){
		gameCode = code;
	}
	
	public static String getGameCode(){
		return gameCode;
	}
	
	public static void setWorld(World w){
		world = w;
	}
	
	public static World getWorld(){
		return world;
	}
//	
//	public static CombatService getCombatService() {
//		return combatService;
//	}
//
//	public static void setCombatService(CombatService service) {
//		Platform.combatService = service;
//	}

	public static void setAppContext(AppContext context){
		appContext = context;
	}
	 
	
	public static void setEventManager(EventManager manager){
		eventManager = manager;
	}
	
	public static AppContext getAppContext(){
		return appContext;
	}
	 
	
	public static EventManager getEventManager(){
		return eventManager;
	}
	
	public static XMLConfiguration getConfiguration(){
		return configuration;
	}
	
	public static void setConfiguration(XMLConfiguration conf){
		configuration= conf;
	}
	
	public static void setUpdater(Updater u){ 
		updater = u;
	}
	
	public static Updater getUpdater(){
		return updater;
	}
	
	public static void setThreadPool(ThreadPool threadPool){
		pool = threadPool;
	}
	
	public static ThreadPool getThreadPool(){
		return pool;
	}
	
 
	
	public static void setServerId(int value){
		serverId = value;
	}
	
	public static int getServerId(){
		return serverId;
	}
}
