package server.core;

import server.core.map.VMap;
 


/**
 * 一个有着地图信息，阵营，以及登记的对象就认为是一个Unit，采集对象，NPC，怪物以及玩家都是一个Unit
 * @author Jeffrey
 *
 */
public interface Unit extends GameObject,Updatable,Locatable/*,Stateful*/{
	
//	public Attributes getAttributes();
//	
//	/** 阵营 */
//	public int getFaction();
//	
//	/** 等级 */
//	public int getLevel();
//	
//	public int getSex();
	
	public void removeFromWorld();

	

	

	
	
//	public void updateSetting(GameMapObject npc);
	
//	public void addSkill(int skillId,int skillLevel);
//
//	public void removeSkill(int skillId);
}