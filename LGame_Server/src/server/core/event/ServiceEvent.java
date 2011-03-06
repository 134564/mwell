package server.core.event;


public class ServiceEvent {
	
	/**
	 * ��ӳ����¼���������VMap
	 */
	public static final int EVENT_MAP_ADDED = 101;
	/**
	 * ɾ�������¼���������VMap
	 */
	public static final int EVENT_MAP_REMOVED = 102;
	/**
	 * ��ҽ��볡���¼���������VMap, Player
	 */
	public static final int EVENT_MAP_PLAYER_ADDED = 201;
	/**
	 * �½��볡���������������¼���������VMap, Player
	 */
	public static final int EVENT_MAP_PLAYER_LOADED = 202;
	/**
	 * ����뿪�����¼���������VMap, Player
	 */
	public static final int EVENT_MAP_PLAYER_REMOVED = 203;
	/**
	 * ���鴴���¼���������Party
	 */
	public static final int EVENT_PARTY_CREATED = 301;
	/**
	 * �����ɢ�¼���������Party
	 */
	public static final int EVENT_PARTY_DESTROIED = 302;
	/**
	 * ����뿪�����¼���������Party, PartyMember
	 */
	public static final int EVENT_MEMBER_LEAVED = 401;
	/**
	 * ��ҽ�������¼���������Party, PartyMember
	 */
	public static final int EVENT_MEMBER_ADDED = 402;
	/**
	 * ���״̬�иı䡣������Party, PartyMember
	 */
	public static final int EVENT_MEMBER_STATE_CHANGED = 403;
	/**
	 * ��������
	 */
	public static final int EVENT_PARTY_DESTROY = 404;
	/**
	 * �ж�Ա���
	 */
	public static final int EVENT_MEMBER_LEAVE = 405;
	/**
	 * ��������¼���������Player
	 */
	public static final int EVENT_PLAYER_LEVELUP = 501;
	/**
	 * ��ɫ�����¼���������Player
	 */
	public static final int EVENT_PLAYER_CREATED = 601;
	/**
	 * ��ɫ������������¼���������Player,type type == 1��cache�����룬 type == 2�����ݿ�����
	 */
	public static final int EVENT_PLAYER_LOADED = 602;
	/**
	 * ��ɫ��¼�¼���������Player
	 */
	public static final int EVENT_PLAYER_LOGINED = 603;
	/**
	 * ��ɫ�˳��¼���������Player
	 */
	public static final int EVENT_PLAYER_LOGOUTED = 604;
	/**
	 * ��ɫ�״������¼���������Player
	 */
	public static final int EVENT_PLAYER_FIRSTLOAD = 605;
	/**
	 * ��ɫ�����¼���������Player
	 */
	public static final int EVENT_PLAYER_SAVED = 606;
	/**
	 * ��ɫ���ݴ��ڴ����Ƴ��¼���������Player
	 */
	public static final int EVENT_PLAYER_UNLOADED = 607;
	/**
	 * ��ɫ�����¼���������Player
	 */
	public static final int EVENT_PLAYER_CHANGENAME = 608;
	
	/**
	 * ��ɫ��Pk��ʱ����ƶ���Ϣ������:Player,x,y
	 */
	public static final int EVENT_PLAYER_PK_MOVE = 609;
	
	/**
	 * ��ɫ��Pk��ʱ�򱻹���������:Player
	 */
	public static final int EVENT_PLAYER_PK_ATTACKED = 610;
	
	/**
	 * ��ɫ��ɾ��������:Player
	 */
	public static final int EVENT_PLAYER_DELETED = 611;
	/**
	 * ���ӽ����¼���������ClientSession
	 */
	public static final int EVENT_SESSION_ADDED = 701;
	/**
	 * ���ӹر��¼���������ClientSession
	 */
	public static final int EVENT_SESSION_REMOVED = 702;
	/**
	 * ��ҽ����¼��������A�����B������X����ʱ��Ҫ�������¼����Ը���˫������ʱ���ѱ�������Player, Player, Integer
	 */
	public static final int EVENT_INTERACT = 801;
	/**
	 * ���������¼���������Tong
	 */
	public static final int EVENT_TONG_LOADED = 901;
	/**
	 * ��Ҽ�������¼���������Actor, Tong
	 */
	public static final int EVENT_PLAYER_CHANGETONG = 902;
	/**
	 * ��Ϸ���󣨹��������ң�����������������Unit��ɱ��Unit(����Ϊnull)
	 */
	public static final int EVENT_UNIT_DIE = 903;
	
	/**
	 * ����뿪�����¼���������Actor��Tong
	 */
	public static final int EVENT_PLAYER_LEAVETONG = 904;
	
	/**
	 * ��Ҹ�����Ӫ��������Player��ԭ������Ӫ
	 */
	public static final int EVENT_PLAYER_CHANGE_FACTION = 905;
	
	/**
	 * ���������¼���������Pet
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
