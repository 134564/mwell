package server.core.impl;

import server.core.map.GameMapDefinition.GameMapNPC;
import server.core.map.GameMapDefinition.GameMapObject;
import server.core.map.UnitInstanceIdGenerator;

 
public class UnitCreatorEx implements UnitCreator {
	
//	protected FallCreator fallCreator = new DefaultFallCreator();
	

	@Override
	public Object create(GameMapObject go) {
		if(go instanceof GameMapNPC){
			/*if(((GameMapNPC) go).faction.getId()==7){ //采集npc
				return createGatherUnit((GameMapNPC) go);
			}else{*/
				return createCreature((GameMapNPC) go);
			/*}*/

		}
		/*else if(go instanceof GameMapExit){
			GameMapExit exit = (GameMapExit)go;
			TransferEx transfer = new TransferEx(exit,UnitInstanceIdGenerator.next());
			return transfer;
		}
		else if(go instanceof GameRelivePoint){
			GameRelivePoint grp = (GameRelivePoint)go;
			return new XyRelivePoint(grp);
		}*/
		return null;
	}
	
	
	protected CreatureEx createCreature(GameMapNPC npc){
		CreatureEx creature = new CreatureEx(npc,UnitInstanceIdGenerator.next());
		/*creature.setGameMapNPC(npc);
		creature.setMaxCombatCount(npc.combatCount);
		int refreshInterval = npc.refreshInterval;
		if(refreshInterval == -1){ //永久不刷新
			creature.setRefreshInterval(-1);
		}else{
			creature.setRefreshInterval(refreshInterval * 1000); //编辑器里保存的是秒
		}
		creature.setStatic(npc.isStatic);
		creature.setDieRefreshNpcId(npc.dieRefresh);
		DataService dataService = Platform.getAppContext()
				.get(DataService.class);

		MonsterGroup monsterGroup = MonsterGroup.findMonsterGroupById(
				dataService.data, npc.monsterGrpId);
		creature.setMonsterGroup(monsterGroup);
		
		
//		if(npc.name.equalsIgnoreCase("猎人"))
//		creature.initPatrolPath(npc.owner.getGamePatrolPath(npc.patrolPathId1));
		
		BaseAI ai = (BaseAI)Platform.getAppContext().get(AiService.class).getAi(creature.getNPCTemplateId());
		
		//这里需要判断的，如果不判断，服务器无法启动
		//出现错误，上面会打印找不到类的错误提示
		if(ai != null) {
			creature.setAi(ai.clone());
		}
		
		creature.setupTouchAction();	*/		
		return creature;
	}
	
	/*protected GatherUnit createGatherUnit(GameMapNPC npc){
		XyNPC template = (XyNPC)npc.template;

		//采集NPC
		GatherUnit gu = new GatherUnitEx(npc.getGlobalID(),UnitInstanceIdGenerator.next(),new GatherCallEx(), template.image); 
		gu.setName(npc.name);
		gu.setX(npc.x);
		gu.setY(npc.y);
		gu.setFaction(npc.faction.getId());		
		gu.setRefreshInterval(npc.refreshInterval*1000);
		int[] tmp = Utils.stringToIntArray(template.questIDs, ',');
		gu.questId = new short[tmp.length];
		for (int i = 0; i < tmp.length; i++) {
			gu.questId[i] = (short)tmp[i];
		}

		gu.gatherTime = template.collectTime;
		GatherFall fall = new GatherFall(gu);
		gu.setFall(fall);
		
		fallCreator.createFall(gu.getFall(), template);
		return gu;
	}*/
}
