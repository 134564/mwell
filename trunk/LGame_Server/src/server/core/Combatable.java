package server.core;
 


/**
 * TODO 战斗对象
 * @author yqwang
 */
public interface Combatable extends GameObject/*,Stateful*/{
//	
//	/**获取仇恨列表*/
//	public ThreatList getThreatList();
//	/**获取职业*/
//	public int getClazz();
//	/**获取CD列表*/
//	public CoolDowns getCoolDowns();
//	/**获取当前Hp*/
//	public int getHp();
//	/**设置当前Hp*/
//	public int setHp(int hp,boolean notify,boolean broadcast);
//	/**获取最大Hp*/
//	public int getMaxHp();
//	/**获取当前Mp，很多游戏可以不用，也可以变换概念用*/
//	public int getMp();
//	/**设置当前Mp*/
//	public int setMp(int mp,boolean notify,boolean broadcast);
//	/**获取最大Mp*/
//	public int getMaxMp();
//	/**是否存活*/
//	public boolean isAlive();
//	/**cb杀死了自己*/
//	public void kill(Combatable cb);
//	/**复活*/
//	public void relive(int hp);
//	/**获取buff列表*/
//	public Buffs getBuffs();
//	/**攻击后回调*/
//	public void attack(CombatContext cc);
//	/**被攻击后回调*/
//	public void attacked(CombatContext cc);
//	/**获取当前的CombatAction*/
//	public CombatAction getCombatAction();
//	/**是否能接受指定目标施加的技能，可以是治疗，攻击等等*/
//	public boolean canAcceptSkill(Combatable source,Skill skill);
//	
//	/**
//	 * 重新计算当前属性，并且复制
//	 */
//	public void refreshProperties();
//	
//	public void breakCombatAction();
//	
//	public TurnBasedBattle getBattle();
//	
//	public void setBattle(TurnBasedBattle battle);
//	
//	public void setAiState(int state);
//	
//	public Skills getSkills();
//	
//	public void equ(Equipment equ, Equipment unEqu);
	
}
