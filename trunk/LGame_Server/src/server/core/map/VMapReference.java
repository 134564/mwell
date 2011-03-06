package server.core.map;

import server.core.Player;
 
/**
 * 代理了map的引用
 * @author slmiao
 *
 */
public class VMapReference {

	public int id;
	
	public VMap map;
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setMap(VMap map){
		if(map != null){
			this.id = map.getId();
		}
		this.map = map;
	}

	/**
	 * 全局id
	 * @return
	 */
	/*
	public int getStageId() {
		return map.getStageId();
	}*/
	
	public void playerLoadingFinished(Player player){
		if(map==null)
			throw new IllegalStateException();
		map.playerLoadingFinished(player);
	}
}
