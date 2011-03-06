package server.core.impl;

import server.core.map.GameMapDefinition;
import server.core.map.VMap;
import server.core.map.VMapManager;

public class MapCreatorEx implements MapCreator{

	@Override
	public VMap create(GameMapDefinition def, VMapManager manager) { 
		return new VMap(manager,def);
	}

}
