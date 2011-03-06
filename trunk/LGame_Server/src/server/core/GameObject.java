package server.core;

/**
 * 游戏对象类，只要在游戏中，有Id，InstanceId，Name的对象都可以认为是一个游戏对象，包括怪物，人物，地图，物品，装备等等
 * 每个游戏对象都存在一个游戏对象引用，用来方便的从ObjectAccessor中取到相应的对象
 * @author Jeffrey
 *
 */
public interface GameObject {
	
	public static final int TYPE_PLAYER = 1; //玩家
	public static final int TYPE_CREATURE = 2; //怪物或者npc
	public static final int TYPE_ITEM = 3;//物品包括装备
	public static final int TYPE_MAP = 4;//地图
	public static final int TYPE_PET = 5;//宠物
	public static final int TYPE_GATHERUNIT = 7;//采集npc
	public static final int TYPE_TRANSFER = 8; //传送点
	public static final int TYPE_RELIVEPOINT = 9; //复活点

	public long getId();
	
	public int getInstanceId();
	
	public String getName();
	
	
	public GameObjectRef ref();
	/**
	 * GameObject 的类型，服务器保留0~32,扩展系统请从33以后定义
	 * @return
	 */
	public int getType();
}
