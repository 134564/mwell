package server.core;

import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unchecked")
public class ObjectAccessor {
	
	protected static ConcurrentHashMap<Integer,GameObject> objects = new ConcurrentHashMap<Integer,GameObject>();
	
	public static final ConcurrentHashMap<Integer,Player> players = new ConcurrentHashMap<Integer,Player>();
	/**
	 * 缓存邮件以及聊天系统发布的物品,只存instanceId不为-1的物品，Key是ItemId<<32|instanceId,这里的GameItem只是一个clone版本
	 */
//	public static final ConcurrentHashMap<Long, GameItem> cachedItems = new ConcurrentHashMap<Long,GameItem>();
	
	public static void addGameObject(GameObject object){
		objects.put(object.getInstanceId(), object);
		if(object instanceof Player){
			players.put(object.getInstanceId(), (Player) object);
		}
	}
	
	public static Player getPlayer(int id){
		return players.get(id);
	}
	
	public static GameObject getGameObject(GameObjectRef ref){
		return objects.get(ref.getInstanceId());
	}
	
	public static GameObject getGameObject(int instanceId){
		return objects.get(instanceId);
	}
	
	public static boolean containGameObject(int instanceId){
		return objects.containsKey(instanceId);
	}
	public static void removeGameObject(GameObject object){
		if(object.getType()==GameObject.TYPE_PLAYER){
			players.remove(object.getInstanceId());
		}
		objects.remove(object.getInstanceId());
	}
//	
//	/**
//	 * 把一个物品注册到在线物品缓存中去。在线物品缓存用于保证玩家下线后别的玩家依然能查询物品信息。
//	 * @param item
//	 */
//	public static void addGameItemToCached(GameItem item) {
//		cachedItems.put(getLong(item.getTemplate().getId(), item.getInstanceId()), item);
//	}
//	
//	/**
//	 * 根据物品ID查找缓存的物品信息。
//	 * @param iemTemplaeId
//	 * @param instanceId
//	 * @return
//	 */
//	public static GameItem getCachedGameItem(int iemTemplaeId, int instanceId) {
//		return cachedItems.get(getLong(iemTemplaeId, instanceId));
//	}
//
//	/**
//	 * 把过期的物品从物品缓存中清除。
//	 * @param item
//	 */
//	public static void removeGameItemFromCache(GameItem item) {
//		cachedItems.remove(getLong(item.getTemplate().getId(), (int)item.getInstanceId()), item);
//	}
//	
	/*
	 * 生成物品缓存的KEY = 物品ID << 32 | 物品实例ID。
	 */
	private static long getLong(int i1, int i2) {
		return ((long) i1) << 32 | ( ((long) i2) & 0xFFFFFFFFL);
	}
	
	
}
