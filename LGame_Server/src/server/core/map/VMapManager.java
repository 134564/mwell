package server.core.map;
  
import server.core.Player;
import server.core.Updatable;
import server.core.map.GameMapDefinition.GameMapObject;
 

public interface VMapManager extends Updatable{
	public VMap addToMap(Player player,int mapId,int x,int y,boolean check) throws VMapException;
	/**
	 * ����һ���µ�ͼ����ͼ�����е���ض���ȫ����Ҫ����ʼ��
	 * @param def  ��ͼ�Ķ��壬���Դ�World��getGameMapDefinition�������
	 * @return
	 */
	public VMap createVMap(GameMapDefinition def);
	Object createUnit(GameMapObject obj); 
}
