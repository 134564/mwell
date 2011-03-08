package client.game;

import org.loon.framework.android.game.LGameAndroid2DActivity;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import client.event.Event;
import client.event.EventCode;
import client.nio.NConnector;
import client.util.LocationUtils;
import client.util.Log;


public class Main extends LGameAndroid2DActivity { 

	public void onMain() {
		this.maxScreen(800, 480);
		this.initialization(true);
		this.setFPS(40);
		this.setScreen(new MainScreen());
		this.setShowLogo(false);
		this.setShowFPS(true);
		this.showScreen();
		new Thread(new NConnector("192.168.1.104:7070", NConnector.THREAD_UWAP, false)).start();
		
//		LocationUtils.fixPosition();
		
		LocationManager locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
	    Criteria criteria = new Criteria();
	    criteria.setAccuracy(Criteria.ACCURACY_FINE);
	    criteria.setPowerRequirement(Criteria.POWER_HIGH);
	    criteria.setAltitudeRequired(false);
	    criteria.setBearingRequired(false);
	    criteria.setSpeedRequired(false);
	    criteria.setCostAllowed(true);

	    String provider = locationManager.getBestProvider(criteria, true);

	    LocationListener  locationListener = new LocationListener() {
	      @Override
	      public void onLocationChanged(Location location) {
				if (location != null) {
					double lat = location.getLatitude();
					double lng = location.getLongitude();
					LocationUtils.lat = location.getLatitude();
					LocationUtils.lng = location.getLongitude();

					Log.info("lat=" + lat);
					Log.info("lng=" + lng);
//					Event.fire(EventCode.UPDATE_LOCATION_SUCCEED);
					// locationManager.removeUpdates(this);
				} else {
					Log.info("location is null");
				}
	      }

	      @Override
	      public void onProviderDisabled(String provider) {
	      }

	      @Override
	      public void onProviderEnabled(String provider) {
	      }

	      @Override
	      public void onStatusChanged(String provider, int status, Bundle extras) {
	      }

	    };

	    if (provider != null) {
	      locationManager.requestLocationUpdates(provider, 2000, 10, locationListener);
	    }
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
 
 
