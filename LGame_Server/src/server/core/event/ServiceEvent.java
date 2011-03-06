package server.core.event;


public class ServiceEvent {
	
	/**
	 * 添加场景事件。参数：VMap
	 */
	public static final int EVENT_MAP_ADDED = 101;
	/**
	 * 删除场景事件。参数：VMap
	 */
	public static final int EVENT_MAP_REMOVED = 102;
	/**
	 * 玩家进入场景事件。参数：VMap, Player
	 */
	public static final int EVENT_MAP_PLAYER_ADDED = 201;
	/**
	 * 新进入场景的玩家载入完成事件。参数：VMap, Player
	 */
	public static final int EVENT_MAP_PLAYER_LOADED = 202;
	/**
	 * 玩家离开场景事件。参数：VMap, Player
	 */
	public static final int EVENT_MAP_PLAYER_REMOVED = 203;
	/**
	 * 队伍创建事件。参数：Party
	 */
	public static final int EVENT_PARTY_CREATED = 301;
	/**
	 * 队伍解散事件。参数：Party
	 */
	public static final int EVENT_PARTY_DESTROIED = 302;
	/**
	 * 玩家离开队伍事件。参数：Party, PartyMember
	 */
	public static final int EVENT_MEMBER_LEAVED = 401;
	/**
	 * 玩家进入队伍事件。参数：Party, PartyMember
	 */
	public static final int EVENT_MEMBER_ADDED = 402;
	/**
	 * 玩家状态有改变。参数：Party, PartyMember
	 */
	public static final int EVENT_MEMBER_STATE_CHANGED = 403;
	/**
	 * 队伍销毁
	 */
	public static final int EVENT_PARTY_DESTROY = 404;
	/**
	 * 有队员离队
	 */
	public static final int EVENT_MEMBER_LEAVE = 405;
	/**
	 * 玩家升级事件。参数：Player
	 */
	public static final int EVENT_PLAYER_LEVELUP = 501;
	/**
	 * 角色创建事件。参数：Player
	 */
	public static final int EVENT_PLAYER_CREATED = 601;
	/**
	 * 角色数据载入完成事件。参数：Player,type type == 1从cache中载入， type == 2从数据库载入
	 */
	public static final int EVENT_PLAYER_LOADED = 602;
	/**
	 * 角色登录事件。参数：Player
	 */
	public static final int EVENT_PLAYER_LOGINED = 603;
	/**
	 * 角色退出事件。参数：Player
	 */
	public static final int EVENT_PLAYER_LOGOUTED = 604;
	/**
	 * 角色首次载入事件。参数：Player
	 */
	public static final int EVENT_PLAYER_FIRSTLOAD = 605;
	/**
	 * 角色保存事件。参数：Player
	 */
	public static final int EVENT_PLAYER_SAVED = 606;
	/**
	 * 角色数据从内存中移除事件。参数：Player
	 */
	public static final int EVENT_PLAYER_UNLOADED = 607;
	/**
	 * 角色改名事件。参数：Player
	 */
	public static final int EVENT_PLAYER_CHANGENAME = 608;
	
	/**
	 * 角色在Pk的时候的移动消息。参数:Player,x,y
	 */
	public static final int EVENT_PLAYER_PK_MOVE = 609;
	
	/**
	 * 角色在Pk的时候被攻击。参数:Player
	 */
	public static final int EVENT_PLAYER_PK_ATTACKED = 610;
	
	/**
	 * 角色被删除。参数:Player
	 */
	public static final int EVENT_PLAYER_DELETED = 611;
	/**
	 * 连接建立事件。参数：ClientSession
	 */
	public static final int EVENT_SESSION_ADDED = 701;
	/**
	 * 连接关闭事件。参数：ClientSession
	 */
	public static final int EVENT_SESSION_REMOVED = 702;
	/**
	 * 玩家交互事件。当玩家A和玩家B进行了X交互时需要触发此事件，以更新双方的临时好友表。参数：Player, Player, Integer
	 */
	public static final int EVENT_INTERACT = 801;
	/**
	 * 军团载入事件。参数：Tong
	 */
	public static final int EVENT_TONG_LOADED = 901;
	/**
	 * 玩家加入军团事件。参数：Actor, Tong
	 */
	public static final int EVENT_PLAYER_CHANGETONG = 902;
	/**
	 * 游戏对象（怪物或者玩家）死亡。参数：死亡Unit，杀手Unit(可能为null)
	 */
	public static final int EVENT_UNIT_DIE = 903;
	
	/**
	 * 玩家离开军团事件。参数：Actor，Tong
	 */
	public static final int EVENT_PLAYER_LEAVETONG = 904;
	
	/**
	 * 玩家更改阵营。参数：Player，原来的阵营
	 */
	public static final int EVENT_PLAYER_CHANGE_FACTION = 905;
	
	/**
	 * 宠物升级事件。参数：Pet
	 */
	public static final int EVENT_PET_LEVELUP = 1001;
	
	
	
	public int type;
	public Object param1;
	public Object param2;
	public Object param3;
	public Object param4;
	
	public ServiceEvent(int type) {
		this.type = type;
	}
	
	public ServiceEvent(int type, Object param1) {
		this.type = type;
		this.param1 = param1;
	}

	public ServiceEvent(int type, Object param1, Object param2) {
		this.type = type;
		this.param1 = param1;
		this.param2 = param2;
	}

	public ServiceEvent(int type, Object param1, Object param2, Object param3) {
		this.type = type;
		this.param1 = param1;
		this.param2 = param2;
		this.param3 = param3;
	}
	
	public ServiceEvent(int type, Object param1, Object param2, Object param3, Object param4){
		this.type = type;
		this.param1 = param1;
		this.param2 = param2;
		this.param3 = param3;
		this.param4 = param4;
	}
}
