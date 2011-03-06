package server.core;

public class GameObjectRef {
	
	protected long id;
	protected int instanceId;
	protected int type;

	public GameObjectRef(long id, int instanceId,int type) {
		this.id = id;
		this.instanceId = instanceId;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public int getInstanceId() {
		return instanceId;
	}
	
	public int getType(){
		return this.type;
	}
}
