package client.script;

import java.util.ArrayList;
import java.util.List;

import org.loon.framework.android.game.core.graphics.device.LGraphics;
 

public class GameWorld {
	public static List<Player> spriteList = new ArrayList<Player>();

	public static String error = "";
	public static void drawAll(LGraphics g) {
		for(Player sprite : spriteList) {
			sprite.drawMe(g); 
		}
		g.setColor(0);
		g.drawString(error, 10, 50);
	}
}
