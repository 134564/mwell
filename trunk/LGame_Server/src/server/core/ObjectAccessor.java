package server.core;

import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unchecked")
public class ObjectAccessor {
	
	protected static ConcurrentHashMap<Integer,GameObject> objects = new ConcurrentHashMap<Integer,GameObject>();
	
	public static final ConcurrentHashMap<Integer,Player> players = new ConcurrentHashMap<Integer,Player>();
	/**
	 * �����ʼ��Լ�����ϵͳ��������Ʒ,ֻ��instanceId��Ϊ-1����Ʒ��Key��ItemId<<32|instanceId,�����GameItemֻ��һ��clone�汾
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
//	 * ��һ����Ʒע�ᵽ������Ʒ������ȥ��������Ʒ�������ڱ�֤������ߺ��������Ȼ�ܲ�ѯ��Ʒ��Ϣ��
//	 * @param item
//	 */
//	public static void addGameItemToCached(GameItem item) {
//		cachedItems.put(getLong(item.getTemplate().getId(), item.getInstanceId()), item);
//	}
//	
//	/**
//	 * ������ƷID���һ������Ʒ��Ϣ��
//	 * @param iemTemplaeId
//	 * @param instanceId
//	 * @return
//	 */
//	public static GameItem getCachedGameItem(int iemTemplaeId, int instanceId) {
//		return cachedItems.get(getLong(iemTemplaeId, instanceId));
//	}
//
//	/**
//	 * �ѹ��ڵ���Ʒ����Ʒ�����������
//	 * @param item
//	 */
//	public static void removeGameItemFromCache(GameItem item) {
//		cachedItems.remove(getLong(item.getTemplate().getId(), (int)item.getInstanceId()), item);
//	}
//	
	/*
	 * ������Ʒ�����KEY = ��ƷID << 32 | ��Ʒʵ��ID��
	 */
	private static long getLong(int i1, int i2) {
		return ((long) i1) << 32 | ( ((long) i2) & 0xFFFFFFFFL);
	}
	
	
}
