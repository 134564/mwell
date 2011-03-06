package server.core.impl;
  
import server.core.ObjectAccessor;
import server.core.Platform;
import server.core.Player;
import server.core.Unit;
import server.core.map.GameMapDefinition;
import server.core.map.GameMapDefinition.GameMapNPC;
import server.core.map.GameMapDefinition.GameMapObject;
import server.core.map.VMap;
import server.core.map.VMapException;
import server.core.map.VMapManager;

public abstract class AbstractVMapManager implements VMapManager{

	protected MapCreator mapCreator;
	protected UnitCreator unitCreator;
	
	public MapCreator getMapCreator() {
		return mapCreator;
	}

	public void setMapCreator(MapCreator mapCreator) {
		this.mapCreator = mapCreator;
	}
	
	@Override
	public VMap createVMap(GameMapDefinition def) {
		VMap map = mapCreator.create(def, this);
		
		/*BaseMapAI ai = (BaseMapAI)Platform.getAppContext().get(AiService.class).getMapAi(map.getId());
		if(ai != null)
			map.setAi(ai.clone());*/
		
		for (GameMapObject go : def.mapInfo.objects) {
			if(go instanceof GameMapNPC){
				if(!((GameMapNPC)go).visible){ //如果不可见的先屏蔽掉
					continue;
				}
			}
			Object o = createUnit(go);
			if (o != null) {
				if(o instanceof Unit){
					Unit unit = (Unit)o;
					ObjectAccessor.addGameObject(unit);
					unit.addToMap(map, go.x, go.y);
				}
				/*else if(o instanceof RelivePoint){ //传送点
					map.addRelivePoint((RelivePoint)o, go.x, go.y);
				}*/
			}
		}
		Platform.getWorld().registerVMapManager(map.getId(), this);
		return map;
	}

	@Override
	public Object createUnit(GameMapObject go) {
		return unitCreator.create(go);
	}
}
