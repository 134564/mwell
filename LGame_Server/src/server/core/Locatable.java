package server.core;
 
import server.core.map.MapCell;
import server.core.map.VMap;
import server.io.Packet;

public interface Locatable {
	public int getX();

	public int getY();

	public VMap getVMap();

	/**
	 * ��������뵽VMap�У��������õ�ָ��λ�á�������removeFromMap������һ��һ���Ĳ�����
	 * 
	 * @param map
	 * @param x
	 * @param y
	 */
	public void addToMap(VMap map, int x, int y);

	public void removeFromMap();

	public Packet getMovePacket(int mask, boolean visible);

	public boolean isVisible();// �ɼ�

	public boolean isStatic();// �Ǿ�̬��

	public MapCell getMapCell();

	public void setMapCell(MapCell cell);
	
	public void move(int x,int y);
}
