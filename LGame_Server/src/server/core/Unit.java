package server.core;

import server.core.map.VMap;
 


/**
 * һ�����ŵ�ͼ��Ϣ����Ӫ���Լ��ǼǵĶ������Ϊ��һ��Unit���ɼ�����NPC�������Լ���Ҷ���һ��Unit
 * @author Jeffrey
 *
 */
public interface Unit extends GameObject,Updatable,Locatable/*,Stateful*/{
	
//	public Attributes getAttributes();
//	
//	/** ��Ӫ */
//	public int getFaction();
//	
//	/** �ȼ� */
//	public int getLevel();
//	
//	public int getSex();
	
	public void removeFromWorld();

	

	

	
	
//	public void updateSetting(GameMapObject npc);
	
//	public void addSkill(int skillId,int skillLevel);
//
//	public void removeSkill(int skillId);
}