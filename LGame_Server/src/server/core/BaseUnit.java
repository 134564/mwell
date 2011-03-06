package server.core;
     
import server.core.map.MapCell;
import server.core.map.VMap;
import server.core.map.VMapReference;
import server.io.Packet;

public abstract class BaseUnit implements Unit {

	private String name;
	protected VMapReference map;
	protected int x, y;
	protected double lat, lng; 
	protected MapCell mapCell;
	protected int moveType;

	public BaseUnit(){
		this.map = new VMapReference();
		/*this.states = new States();*/
	}
	
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public VMap getVMap() {
		if (map != null)
			return map.map;
		return null;
	}

	public VMapReference getMap() {
		return map;
	}

	public void setMap(VMapReference map) {
		this.map = map;
	}

	@Override
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	@Override
	public MapCell getMapCell() {
		return mapCell;
	} 

	@Override
	public void setMapCell(MapCell cell) {
		this.mapCell = cell;
	}
	
	public void broadcast(Packet pt, Player p, Player target, boolean self, boolean ingoreParty, boolean isAttack){
		if (mapCell != null) {
		    if (isStatic()) {
		    	// 如果是静态NPC的信息，向所有玩家广播
		        if (getVMap() != null) {
		            getVMap().broadcast(pt);
		        }
		    } else {
		    	// 否则向本CELL以及相邻CELL的玩家广播
		        mapCell.broadcast(p, target, pt, self, ingoreParty, isAttack);
		    }
		}
	}
	
	public void processMove(Player p) {
		if (moveType != 0) {
			Packet pt = getMovePacket(moveType, true);
			broadcast(pt,p,null,false,true,false);
			moveType = 0;
		}
	}
	public void addMoveTypeMask(int mask){
		this.moveType |= mask;
	}
	
	public int getMoveType(){
		return this.moveType;
	}
	
	public void setMoveType(int moveType){
		this.moveType = moveType;
	}
	
	@Override
	public void move(int x, int y) {
		VMap v = getVMap();
		if (v != null) {
			this.x = x;
			this.y = y;
			MapCell cell = v.getMapCell(x, y);
			if (cell != null && cell != mapCell) {
				MapCell oldCell = mapCell;
				if (oldCell != null) {
					oldCell.removeUnit(this);
				}
				mapCell = cell;
				mapCell.addUnit(this);
				if (isReady()) {
					if (!isStatic()) {
						MapCell[][] diff = MapCell.diff(oldCell, cell);
						MapCell[] removed = diff[0];
						if (this instanceof Player) {
							if (removed != null) {
								MapCell.broadcastRefreshPlayer(removed,
										(Player) this, false);
							}

							// 在走入视野的CELL刷出
							MapCell[] added = diff[1];
							if (added != null) {
								MapCell.broadcastRefreshPlayer(added,
										(Player) this, true);
							}
						} else {
							/*if (removed != null) {
								MapCell.broadcastRefreshNPC(removed, this,
										false);
							}
							MapCell[] added = diff[1];
							if (added != null) {
								MapCell.broadcastRefreshNPC(added, this, true);
							}*/
						}
					}
				}
			}
		}
	}

	private boolean isReady() { 
		return true;
	}
}
