package server.core.impl;

import java.util.ArrayList;
import java.util.List;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap; 
import server.core.Player;
import server.core.Updatable;
import server.core.World;
import server.core.map.VMap;
import server.core.map.VMapException;
import server.core.map.VMapManager;

public class BaseWorld implements World, Updatable{

	private TIntObjectMap<VMapManager> mapid2managers = new TIntObjectHashMap<VMapManager>();
	
	List<VMapManager> managers = new ArrayList<VMapManager>();
	
	@Override
	public boolean update() { 
		for(VMapManager manager:managers){
			manager.update();
		}
		return false;
	}

	@Override
	public void registerVMapManager(int mapId,VMapManager manager){
		mapid2managers.put(mapId, manager);
	}
	
	@Override
	public VMap addPlayer(Player p, int mapId, int x, int y, boolean check) throws VMapException {
		VMapManager manager = mapid2managers.get(mapId);
		if(manager!=null){
			return manager.addToMap(p, mapId,x,y,check);
		}
		return null;
	}
	
	public void addVMapManager(VMapManager manager){
		managers.add(manager);
	}
}
