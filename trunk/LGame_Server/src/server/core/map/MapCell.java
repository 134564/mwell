package server.core.map;

import gnu.trove.map.hash.TLongObjectHashMap;
import gnu.trove.procedure.TObjectProcedure;

import java.util.ArrayList;
import java.util.List;
 
 

import server.core.GameObject;
import server.core.Player;
import server.core.Unit;
import server.io.Packet;

public class MapCell {
	MapCell[] cells;
	protected int startX, startY, width, height;
	/**
	 * midxΪMapCell����x���꣬ midyΪMapCell����y����
	 */
	protected int midX, midY;

	protected TLongObjectHashMap<Unit> objects = new TLongObjectHashMap<Unit>();
	
	protected int playerCount;

	protected VMap map;

	protected BroadcastThisProcedure broadcastThisProcedure = null;

	// protected BroadcastThisAdvanceProcedure broadcastThisAdvanceProcedure =
	// null;
	//
	// protected BroadcastRefreshNpcAdvanceProcedure
	// broadcastRefreshNpcAdvanceProcedure = null;

	public MapCell(VMap map, int startX, int startY, int width, int height) {
		this.startX = startX;
		this.startY = startY;
		this.width = width;
		this.height = height;
		this.midX = startX + width / 2;
		this.midY = startY + height / 2;
		this.map = map;
		broadcastThisProcedure = new BroadcastThisProcedure(map);
		// broadcastThisAdvanceProcedure = new
		// BroadcastThisAdvanceProcedure(map);
		// broadcastRefreshNpcAdvanceProcedure = new
		// BroadcastRefreshNpcAdvanceProcedure(map);
	}
	/**
	 * ��Cell�����һ����Ϸ����
	 * @param g
	 */
	public void addUnit(Unit g) {
		objects.put(g.getInstanceId(), g);
		if (g instanceof Player)
			playerCount++;
	}

	/**
	 * ��Cell���Ƴ�һ����Ϸ����
	 * @param g
	 * @return
	 */
	public boolean removeUnit(Unit g) {
		GameObject o = objects.remove(g.getInstanceId());
		boolean ret = o != null;
		if (o instanceof Player)
			playerCount--;
		return ret;
	}
	
	/**
	 * ��������MapCell�Ĳ�𣬷���ֵ��MapCell[0]���Ǳ��Ƴ���MapCell��MapCell[1]���Ǳ���ӵ�MapCell
	 * 
	 * @param oldCells
	 * @param newCells
	 *            ����Ϊnull�������׳�IllegalArgumentException
	 * @return
	 */
	private static MapCell[][] diff(MapCell[] oldCells, MapCell[] newCells) {
		if (newCells == null)
			throw new IllegalArgumentException();
		MapCell[][] ret = new MapCell[3][];
		if (oldCells != null) {
			if (oldCells == newCells) {
				return ret;
			}
			List<MapCell> addedCells = new ArrayList<MapCell>(9);
			List<MapCell> removedCells = new ArrayList<MapCell>(9);
			List<MapCell> remained = new ArrayList<MapCell>(9);
			begin: for (int i = 0; i < oldCells.length; i++) {
				for (int j = 0; j < newCells.length; j++) {
					if (oldCells[i] == newCells[j]) {
						remained.add(oldCells[i]);
						continue begin;
					}
				}
				removedCells.add(oldCells[i]);
			}
			begin1: for (int i = 0; i < newCells.length; i++) {
				for (int j = 0; j < oldCells.length; j++) {
					if (newCells[i] == oldCells[j])
						continue begin1;
				}
				addedCells.add(newCells[i]);
			}
			ret[0] = new MapCell[removedCells.size()];
			ret[1] = new MapCell[addedCells.size()];
			ret[2] = new MapCell[remained.size()];
			removedCells.toArray(ret[0]);
			addedCells.toArray(ret[1]);
			remained.toArray(ret[2]);
			return ret;
		} else {
			ret[1] = newCells;
			return ret;
		}
	}
	/**
	 * ��������MapCell�Ĳ�𣬷���ֵ��MapCell[0]���Ǳ��Ƴ���MapCell��MapCell[1]���Ǳ���ӵ�MapCell
	 * 
	 * @param oldCells
	 * @param newCells
	 *            ����Ϊnull�������׳�IllegalArgumentException
	 * @return
	 */
	public static MapCell[][] diff(MapCell oldCell, MapCell newCell) {
		if (oldCell == null) {
			return new MapCell[][] { null, newCell.cells };
		} else {
			return diff(oldCell.cells, newCell.cells);
		}
	}
	

	/**
	 * ���CELL�Լ�����CELL��������ҹ㲥һ����Ϣ��������NPC����ң�Ҳ��������Ҷ���ң���
	 * @param player ��Ϣ��Դ��ң�null��ʾ����ң�
	 * @param target ��ϢĿ����ң�null��ʾ����ң�
	 * @param pt ��Ϣ��
	 * @param self �Ƿ��͸�������Լ�
	 * @param ignoreParty �Ƿ񲻷��͸����ѣ�ĳЩ���Ѿ�ͨ���������ͽӿڷ��ͣ����ﲻ���Ϳ��Ա����˷ѣ�
	 * @param isAttack �Ƿ��ǹ��������������ڴ�����������ʱ��Ҫ����ע�⡣
	 */
	public void broadcast(Player player, Player target, Packet pt,
			boolean self, boolean ingoreParty, boolean isAttack) {
		for (int i = 0; i < cells.length; i++) {
//	TODO ͬ�����Ե��о�		cells[i].broadcastThis(player, target, pt, self, ingoreParty, isAttack);
			cells[i].broadcastThis(player, pt, self, false);
		}
	}
	
	/*
	 * ��һ��CELL���������ҹ㲥һ����ҵ���Ϣ��
	 * @param player ������Ϣ�����
	 * @param pt ��Ϣ��
	 * @param self �Ƿ��͸�������Լ�
	 * @param ignoreParty �Ƿ񲻷��͸����ѣ�ĳЩ���Ѿ�ͨ���������ͽӿڷ��ͣ����ﲻ���Ϳ��Ա����˷ѣ�
	 */
	private void broadcastThis(final Player player, final Packet pt, final boolean self, final boolean ignoreParty) {
		if (playerCount == 0) {
			return;
		}
		broadcastThisProcedure.init(player, pt, self, ignoreParty);
		objects.forEachValue(broadcastThisProcedure);
		broadcastThisProcedure.clear();
	}

	/**
	 * ��CELL�Լ�����CELL�㲥һ�����ˢ��/ˢû����Ϣ��
	 * 
	 * @param player
	 *            ״̬�仯�����
	 * @param visible
	 *            true��ʾˢ��,false��ʾˢû
	 */
	public void broadcastRefreshPlayer(Player player, boolean visible) {
		broadcastRefreshPlayer(cells, player, visible);
	}

	/**
	 * ��һ��CELL�㲥һ�����ˢ��/ˢû����Ϣ��
	 * 
	 * @param cells
	 *            ���CELL
	 * @param player
	 *            ״̬�仯�����
	 * @param visible
	 *            true��ʾˢ��,false��ʾˢû
	 */
	public static void broadcastRefreshPlayer(MapCell[] cells, Player player, boolean visible) {
		for (int i = 0; i < cells.length; i++) {
			cells[i].broadcastRefreshPlayerImpl(player, visible);
		}
	}

	/**
	 * TODO: NPC ��ͬ��
	 */
	private void broadcastRefreshPlayerImpl(final Player player, final boolean visible) {
		final Packet pt1 = player.getMovePacket(0xFFFFFFFF, visible);

		final List<Unit> l1 = new ArrayList<Unit>();// ����������Ұ�Ķ���
		final List<Unit> l2 = new ArrayList<Unit>();// �뿪�������Ұ�Ķ���

		/*
		 * broadcastRefreshNpcAdvanceProcedure.init(player, pt1, l1, l2,
		 * visible); objects.forEachValue(broadcastRefreshNpcAdvanceProcedure);
		 * broadcastRefreshNpcAdvanceProcedure.clear(); sendRefresh(player, l1,
		 * true); sendRefresh(player, l2, false);
		 */

	}

	class BroadcastThisProcedure implements TObjectProcedure<Unit> {

		Player player;
		Packet pt;
		boolean self;
		boolean ignoreParty;
		VMap map;

		public BroadcastThisProcedure(VMap map) {
			this.map = map;
		}

		public void init(Player player, Packet pt, boolean self, boolean ignoreParty) {
			this.player = player;
			this.pt = pt;
			this.self = self;
			this.ignoreParty = ignoreParty;
		}

		public void clear() {
			this.player = null;
			this.pt = null;
		}

		@Override
		public boolean execute(Unit object) {
			if (object instanceof Player) {
				if (player == null) {
					((Player) object).send(pt);
					return true;
				}
				Player p = (Player) object;
				/*
				 * // ����Ƿ������Ӫ if (map.isSplitFaction() && object.getFaction()
				 * != player.getFaction()) { return true; }
				 * 
				 * Player p = (Player) object; if (!p.isReady()) { return true;
				 * } if (!self && player == object) { return true; } boolean
				 * isParty = player.getParty() != null && player.getParty() ==
				 * p.getParty(); if (ignoreParty && isParty) { return true; } if
				 * (player == p || p.canSeeInMapCell(player)) { p.send(pt); }
				 */

				p.send(pt);
			}
			return true;
		}
	}
}
