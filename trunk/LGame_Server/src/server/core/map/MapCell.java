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
	 * midx为MapCell中心x坐标， midy为MapCell中心y坐标
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
	 * 向Cell中添加一个游戏对象
	 * @param g
	 */
	public void addUnit(Unit g) {
		objects.put(g.getInstanceId(), g);
		if (g instanceof Player)
			playerCount++;
	}

	/**
	 * 从Cell中移除一个游戏对象
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
	 * 返回两个MapCell的差别，返回值中MapCell[0]的是被移除的MapCell，MapCell[1]的是被添加的MapCell
	 * 
	 * @param oldCells
	 * @param newCells
	 *            不能为null，否则抛出IllegalArgumentException
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
	 * 返回两个MapCell的差别，返回值中MapCell[0]的是被移除的MapCell，MapCell[1]的是被添加的MapCell
	 * 
	 * @param oldCells
	 * @param newCells
	 *            不能为null，否则抛出IllegalArgumentException
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
	 * 向此CELL以及相邻CELL的所有玩家广播一个信息（可能是NPC对玩家，也可能是玩家对玩家）。
	 * @param player 信息来源玩家（null表示非玩家）
	 * @param target 信息目标玩家（null表示非玩家）
	 * @param pt 信息包
	 * @param self 是否发送给此玩家自己
	 * @param ignoreParty 是否不发送给队友（某些包已经通过队友特送接口发送，这里不发送可以避免浪费）
	 * @param isAttack 是否是攻击包，攻击包在处理流量控制时需要额外注意。
	 */
	public void broadcast(Player player, Player target, Packet pt,
			boolean self, boolean ingoreParty, boolean isAttack) {
		for (int i = 0; i < cells.length; i++) {
//	TODO 同步策略的研究		cells[i].broadcastThis(player, target, pt, self, ingoreParty, isAttack);
			cells[i].broadcastThis(player, pt, self, false);
		}
	}
	
	/*
	 * 向一个CELL里的所有玩家广播一个玩家的信息。
	 * @param player 发出信息的玩家
	 * @param pt 信息包
	 * @param self 是否发送给此玩家自己
	 * @param ignoreParty 是否不发送给队友（某些包已经通过队友特送接口发送，这里不发送可以避免浪费）
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
	 * 向本CELL以及相邻CELL广播一个玩家刷新/刷没的消息。
	 * 
	 * @param player
	 *            状态变化的玩家
	 * @param visible
	 *            true表示刷出,false表示刷没
	 */
	public void broadcastRefreshPlayer(Player player, boolean visible) {
		broadcastRefreshPlayer(cells, player, visible);
	}

	/**
	 * 向一组CELL广播一个玩家刷新/刷没的消息。
	 * 
	 * @param cells
	 *            相关CELL
	 * @param player
	 *            状态变化的玩家
	 * @param visible
	 *            true表示刷出,false表示刷没
	 */
	public static void broadcastRefreshPlayer(MapCell[] cells, Player player, boolean visible) {
		for (int i = 0; i < cells.length; i++) {
			cells[i].broadcastRefreshPlayerImpl(player, visible);
		}
	}

	/**
	 * TODO: NPC 的同步
	 */
	private void broadcastRefreshPlayerImpl(final Player player, final boolean visible) {
		final Packet pt1 = player.getMovePacket(0xFFFFFFFF, visible);

		final List<Unit> l1 = new ArrayList<Unit>();// 进入此玩家视野的对象
		final List<Unit> l2 = new ArrayList<Unit>();// 离开此玩家视野的对象

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
				 * // 检查是否隔离阵营 if (map.isSplitFaction() && object.getFaction()
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
