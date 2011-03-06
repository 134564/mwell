package server.core.map;
     
import java.util.ArrayList;
import java.util.List;
 
import gnu.trove.map.hash.TLongObjectHashMap;
import gnu.trove.procedure.TObjectProcedure;
import server.core.Platform;
import server.core.Player;
import server.core.Unit;
import server.core.Updatable;
import server.core.event.ServiceEvent;
import server.io.Packet;

public class VMap implements Updatable {
	protected TLongObjectHashMap<Player> players = new TLongObjectHashMap<Player>();
	protected TLongObjectHashMap<Unit> units = new TLongObjectHashMap<Unit>(); //地图内所有Unit都应该在这个列表里,列表的key是unit的instanceid
	
	private GameMapDefinition def;

	protected static final int DEFAULT_CELL_WIDTH = 120;
	protected static final int DEFAULT_CELL_HEIGHT = 120;
	/**
	 * col为列， row为行
	 */
	protected int col, row;
	protected int cellWidth, cellHeight;
	
	protected MapCell[][] cells;

	/**
	 * 附近包括自身的9个格差值
	 */
	protected static final int[][] ROUND = { { -1, -1, }, { 0, -1 }, { 1, -1 },
		{ 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 }, { -1, 0 }, { 0, 0 } };
	
	public VMap(VMapManager manager, GameMapDefinition def) {
		this(manager, def, DEFAULT_CELL_WIDTH, DEFAULT_CELL_HEIGHT);
	}

	public VMap(VMapManager manager, GameMapDefinition def, int cellWidth, int cellHeight) {
//		this.manager = manager;
		this.def = def;
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		this.col = (getWidth() / cellWidth) + (getWidth() % cellWidth != 0 ? 1 : 0);
		this.row = (getHeight() / cellHeight) + (getHeight() % cellHeight != 0 ? 1 : 0);
		this.cells = new MapCell[col][row];
		initCells();
	}
	
	protected void initCells() {
		for (int i = 0; i < this.col; i++) {
			for (int j = 0; j < this.row; j++) {
				this.cells[i][j] = new MapCell(this, i * cellWidth, j
						* cellHeight, cellWidth, cellHeight);
			}
		}
		for (int i = 0; i < this.col; i++) {
			for (int j = 0; j < this.row; j++) {
				this.cells[i][j].cells = getMapCell(ROUND, i, j);
			}

		}
	}
	
	protected MapCell[] getMapCell(int[][] offsets, int col, int row) {
		List<MapCell> mcs = new ArrayList<MapCell>(9);
		for (int i = 0; i < offsets.length; i++) {
			int x = col + offsets[i][0];
			int y = row + offsets[i][1];
			if (x >= 0 && x < this.col && y >= 0 && y < this.row) {
				mcs.add(this.cells[x][y]);
			}
		}
		MapCell[] mc = new MapCell[mcs.size()];
		mcs.toArray(mc);
		return mc;
	}
	
	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getId() {
		return def.mapInfo.getGlobalID();
	}

	/**
	 * 把一个包发送给场景里所有玩家
	 * 
	 * @param pkt
	 */
	public void broadcast(Packet pkt) {
		for (Player p : players.valueCollection()) {
			p.send(pkt);
		}
	}
	
	public void playerLoadingFinished(final Player player) {
		if (player.getVMap() == this) {
			units.forEachValue(new TObjectProcedure<Unit>() {
				@Override
				public boolean execute(Unit obj) {
					if (obj.isStatic() && obj.isVisible() && obj != player) {
						player.send(obj.getMovePacket(0xFFFFFFFF, true));
					}
					return true;
				}
			});

			// 刷新附近格子的非静态NPC和玩家给他
			player.getMapCell().broadcastRefreshPlayer(player, true);
			Platform.getEventManager().addEvent(
					new ServiceEvent(ServiceEvent.EVENT_MAP_PLAYER_LOADED,
							player.getVMap(), player));
		}

	}

	public MapCell getMapCell(int x, int y) {
		int c = x / cellWidth;
		int r = y / cellHeight;
		if (c < 0) {
			c = 0;
		} else if (c >= col) {
			c = col - 1;
		}
		if (r < 0) {
			r = 0;
		} else if (r >= row) {
			r = row - 1;
		}
		return cells[c][r];
	}

	public int getHeight() {
		return def.map.height;
	}

	public int getWidth() {
		return def.map.width;
	}
}
