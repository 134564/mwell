package server.core.map;

import java.util.List;

import server.core.Unit;

 
/**
 * TODO �����ͼ������, ����, NPC, ���͵�, ͼ��,��ײ,�����ڵ���. 
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
	 * �༭�����ͼ��Ʒ(����NPC, ���͵��.)
	 *
	 */
	public class GameMapObject {
		public boolean visible;
		
		/** Xλ�ã����أ� */
	    public int x;
	    /** Yλ�ã����أ� */
	    public int y;
	}
	/**
	 * @author slmiao
	 * �༭�����ͼ��Ʒ(����NPC, ���͵��.)
	 *
	 */
	public class GameMapNPC extends  GameMapObject{
		
	}
	
}
