package server.core;
 
import server.core.map.MapCell;
import server.core.map.VMap;
import server.io.Packet;

public interface Locatable {
	public int getX();

	public int getY();

	public VMap getVMap();

	/**
	 * 将对象加入到VMap中，并且设置到指定位置。对于与removeFromMap，属于一正一反的操作。
	 * 
	 * @param map
	 * @param x
	 * @param y
	 */
	public void addToMap(VMap map, int x, int y);

	public void removeFromMap();

	public Packet getMovePacket(int mask, boolean visible);

	public boolean isVisible();// 可见

	public boolean isStatic();// 是静态的

	public MapCell getMapCell();

	public void setMapCell(MapCell cell);
	
	public void move(int x,int y);
}
