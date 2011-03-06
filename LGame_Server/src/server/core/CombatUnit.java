package server.core;

 
public abstract class CombatUnit extends BaseUnit implements Combatable{

//	protected Buffs buffs;
//	protected int clazz;
//	protected CombatAction combatAction;
//	protected CoolDowns coolDowns;
//	protected ThreatList threats;
//	protected TurnBasedBattle battle;
//	protected Skills skills;
//	public AI battleAi;
//	
//	public CombatUnit(){
//		buffs = new Buffs(this);
//		coolDowns = new CoolDowns(this);
//		threats = new ThreatList(this);
//	}
//	
//	public Skills getSkills(){
//		return skills;
//	}
//	
//	
//	@Override
//	public void attack(CombatContext cc) {
//		
//	}
//
//	@Override
//	public void attacked(CombatContext cc) {
//		
//	}
//
//	@Override
//	public boolean canAcceptSkill(Combatable source, Skill skill) {
//		return true;
//	}
//
//	@Override
//	public Buffs getBuffs() {
//		return buffs;
//	}
//	
//
//	@Override
//	public int getClazz() {
//		return clazz;
//	}
//	
//	public void setClazz(int clazz){
//		this.clazz = clazz;
//	}
//
//	@Override
//	public CombatAction getCombatAction() {
//		return combatAction;
//	}
//	
//	public void setCombatAction(CombatAction combatAction){
//		this.combatAction = combatAction;
//	}
//
//	@Override
//	public CoolDowns getCoolDowns() {
//		return coolDowns;
//	}
//
//	@Override
//	public int getHp() {
//		return getAttributes().hp;
//	}
//	
//
//	@Override
//	public int getMaxHp() {
//		return getAttributes().maxHp;
//	}
//
//	@Override
//	public int getMaxMp() {
//		return getAttributes().maxMp;
//	}
//
//	@Override
//	public int getMp() {
//		return getAttributes().mp;
//	}
//
//	@Override
//	public ThreatList getThreatList() {
//		return threats;
//	}
//
//	@Override
//	public boolean isAlive() {
//		return  !this.hasState(States.DIE);
//	}
//
//	@Override
//	public void kill(Combatable cb) {
//		this.setState(States.DIE, -1);
//		if (cb != null) {
//			cb.getThreatList().removeUnit(ref());
//			getThreatList().clear();
//		}
//	}
//
//	@Override
//	public void refreshProperties() {
//		
//	}
//	
//	private void setHp(int hp){
//		getAttributes().hp = hp;
//	}
//	
//	private void setMp(int mp){
//		getAttributes().mp = mp;
//	}
//	
//
//	@Override
//	public int setHp(int hp,boolean notify,boolean broadcast) {
//		int oldHp = getHp();
//		if(oldHp!=hp){
//			if (hp > getMaxHp()) {
//				getAttributes().hp = getMaxHp();
//			} else {
//				getAttributes().hp = hp;
//			}
//			addIntPropertyChangedItem(ChangedItem.HP, oldHp, getAttributes().hp, notify,broadcast);
//			return getAttributes().hp - oldHp;
//		}
//		return oldHp;
//	}
//
//	@Override
//	public int setMp(int mp,boolean notify,boolean broadcast) {
//		int oldMp = getMp();
//		if(oldMp!=mp){
//			if (mp > getMaxMp()) {
//				getAttributes().mp = getMaxMp();
//			} else {
//				getAttributes().mp = mp;
//			}
//			addIntPropertyChangedItem(ChangedItem.MP, oldMp, getAttributes().mp, notify,broadcast);
//			return getAttributes().mp - oldMp;
//		}
//		return oldMp;
//	}
//
//	public void setMaxHp(int maxHp){
//		getAttributes().maxHp = maxHp;
//		addIntPropertyChangedItem(ChangedItem.MAXHP,maxHp,false,true,false);
//	}
//	
//	public void setMaxMp(int maxMp){
//		getAttributes().maxMp = maxMp;
//		addIntPropertyChangedItem(ChangedItem.MAXMP,maxMp,false,true,false);
//	}
//
//
//	
//	public void breakCombatAction(){
//		if(combatAction!=null){
//			combatAction.cancel();
//		}
//	}
//
//	public TurnBasedBattle getBattle() {
//		return battle;
//	}
//
//	public void setBattle(TurnBasedBattle battle) {
//		this.battle = battle;
//			
//	}
//	
//	public void relive(int hp){
//		removeState(States.DIE);
//		setHp(hp,false,false);
//	}
//	
//	public void setBattleAi(AI ai){
//		this.battleAi = ai;
//		if(ai != null) {
//			ai.init(this);			
//		}
//	}
//	public void setAiState(int state){
//		battleAi.setState(state);
//	}
//	
//	public void createAction(int skillGroupId,int skillLevel,int priority,int target){
//		
//	}
//	
//	public void shout(String string, int i, int j) {
//		// TODO Auto-generated method stub
//	}
//
//	public boolean getUnitHpState(int unitSide, int op, int hpPer) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	public void randomSkill(String skillList) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void addSkill(int skillGroupId,int skillLv){
//	}
//	
//	public void removeSkill(int skillGroupId){
//		
//	}
//	
//	public boolean skillCount(int skillId, int count) {
//		// TODO Auto-generated method stub
//		return false;
//	}
	
}
