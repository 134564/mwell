package server.core;

import server.core.map.VMap;
import server.core.map.VMapException;
import server.core.map.VMapManager;

//TODO  World
public interface World  {
	public VMap addPlayer(Player p,int mapId,int x,int y,boolean check) throws VMapException;
	public void registerVMapManager(int mapId,VMapManager manager);
//	public VMapManager getVMapManager(int mapId);
//	public void addVMapManager(VMapManager manager);
//	public GameMapDefinition getMapDefinition(int id);
//	public void addMapDefinition(GameMapDefinition definition);
}
