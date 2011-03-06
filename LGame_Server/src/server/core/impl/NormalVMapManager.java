package server.core.impl;

import server.core.Player;
import server.core.Service;
import server.core.map.VMap;
import server.core.map.VMapException;

public class NormalVMapManager extends AbstractVMapManager implements Service {

	private BaseWorld world;

	public NormalVMapManager(BaseWorld world) {
		this.world = world; 
		world.addVMapManager(this);
	}

	@Override
	public VMap addToMap(Player player, int mapId, int x, int y, boolean check) throws VMapException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		return "NormalVMapManager";
	}

	@Override
	public void startup() throws Exception {

	}

	@Override
	public void shutdown() throws Exception {
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

}
