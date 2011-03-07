package client.game;

import org.loon.framework.android.game.LGameAndroid2DActivity;
import org.loon.game.sample.llk.MainScreen;

import android.content.Context;
import android.location.LocationManager;

import client.nio.NConnector;
import client.util.LocationUtils;


public class Main extends LGameAndroid2DActivity {
	public static LocationManager locationManager;

	public void onMain() {
		this.initialization(true);
		this.setFPS(40);
		this.setScreen(new MainScreen());
		this.setShowLogo(false);
		this.setShowFPS(true);
		this.showScreen();
		
		new Thread(new NConnector("192.168.10.97:7070", NConnector.THREAD_UWAP, false)).start();
		locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
//		LocationUtils.fixPosition();
	}

}
  
//UASegment seg = new UASegment(OpCode.TEST_OP_CLIENT, false);
//try {
//	seg.writeInt(msgValue++);
//} catch (IOException e1) { 
//	e1.printStackTrace();
//}
//NConnector.sendRequest(seg);

//public void alter(LTimerContext t) {
//	SegmentManager.cycle();  
//}


//GameWorld.drawAll(g);
 
 
