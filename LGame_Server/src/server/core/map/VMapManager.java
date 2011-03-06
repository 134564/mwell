package server.core.map;
  
import server.core.Player;
import server.core.Updatable;
import server.core.map.GameMapDefinition.GameMapObject;
 

public interface VMapManager extends Updatable{
	public VMap addToMap(Player player,int mapId,int x,int y,boolean check) throws VMapException;
	/**
	 * 创建一张新地图，地图里所有的相关对象全部需要被初始化
	 * @param def  地图的定义，可以从World的getGameMapDefinition方法获得
	 * @return
	 */
	public VMap createVMap(GameMapDefinition def);
	Object createUnit(GameMapObject obj); 
}
