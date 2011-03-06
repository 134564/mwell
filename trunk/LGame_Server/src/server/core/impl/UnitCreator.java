package server.core.impl;

import server.core.map.GameMapDefinition.GameMapObject;

 
public interface UnitCreator {
	public Object create(GameMapObject go);
}	
