package server.core;
 
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import server.core.map.MapCell;
import server.core.map.VMap;
import server.io.Client;
import server.io.ClientSession;
import server.io.Packet; 
 

public class Player extends CombatUnit implements Client {
	
	protected ClientSession session;
	 
	
	@Override
	public void setSession(ClientSession session) {
		if (this.session != null && this.session.isConnected()) {
			this.session.close();
		}
		this.session = session;
	}

	@Override
	public ClientSession getSession() {
		return session;
	}
	

	public void send(Packet packet) {
		if (session != null)
			session.send(packet);
	}

	public byte[] toClientBytes() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try { 
			dos.writeUTF(getName()); 
			dos.writeInt(this.x);
			dos.writeInt(this.y);
		} catch (IOException e) {
		}
		return baos.toByteArray();
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getInstanceId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GameObjectRef ref() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeFromWorld() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update() {
		processMove(this);
		return false;
	}

	@Override
	public void addToMap(VMap map, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFromMap() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Packet getMovePacket(int mask, boolean visible) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isStatic() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MapCell getMapCell() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMapCell(MapCell cell) {
		// TODO Auto-generated method stub
		
	}
 
}
