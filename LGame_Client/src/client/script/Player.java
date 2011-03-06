package client.script;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.loon.framework.android.game.action.sprite.Sprite;
import org.loon.framework.android.game.core.graphics.device.LGraphics;
 
 

import client.nio.NConnector;
import client.nio.OpCode;
import client.nio.UASegment;

public class Player extends Sprite {
 
	private static final long serialVersionUID = 1L;
	int x, y;
	String name; 

	public Player(UASegment segment) {
		DataInputStream stream = new DataInputStream(new ByteArrayInputStream((segment.readBytes())));
		 
		try {
			name = stream.readUTF();
			x = stream.readInt();
			y = stream.readInt();
		} catch (IOException e) { 
			e.printStackTrace();
		}
		
		setSpriteName(name);
		
		GameWorld.spriteList.add(this);
	}

	public void drawMe(LGraphics g) {
		g.fill3DRect(x, y, 20, 20, true);
		g.drawString(name, x, y - 20);
	}

	public static void login() {
		UASegment seg = new UASegment(OpCode.PLAYER_LOGIN_CLIENT, false);
		try {
			seg.writeInt(1);
		} catch (IOException e) { 
			e.printStackTrace();
		}
		NConnector.sendRequest(seg);
	}

}
