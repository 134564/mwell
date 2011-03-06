package server.core.impl;

import server.core.map.GameMapDefinition;
import server.core.map.VMap;
import server.core.map.VMapManager;
  
public interface MapCreator {
	public VMap create(GameMapDefinition def,VMapManager manager);
}
