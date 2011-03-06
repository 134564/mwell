package server.core.map;

import java.util.List;

import server.core.Unit;

 
/**
 * TODO 定义地图的内容, 场景, NPC, 传送点, 图层,碰撞,视线遮挡等. 
 * @author slmiao
 *
 */
public class GameMapDefinition {
 

	public GameMapInfo mapInfo;
	public GameMap map;
//	
//	public GameMapDefinition(GameMapInfo mapInfo,GameMap map){
//		this.mapInfo = mapInfo;
//		this.map = map;
//	}
	
	public class GameMapInfo { 
		public List<GameMapObject> objects;

		public int getGlobalID() { 
			return 0;
		}
		
	}
	
	public class GameMap { 
		public int height;
		public int width;
		
	}
	/**
	 * @author slmiao
	 * 编辑器里地图物品(包括NPC, 传送点等.)
	 *
	 */
	public class GameMapObject {
		public boolean visible;
		
		/** X位置（像素） */
	    public int x;
	    /** Y位置（像素） */
	    public int y;
	}
	/**
	 * @author slmiao
	 * 编辑器里地图物品(包括NPC, 传送点等.)
	 *
	 */
	public class GameMapNPC extends  GameMapObject{
		
	}
	
}
