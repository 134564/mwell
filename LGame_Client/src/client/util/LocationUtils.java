package client.util;

import client.event.Event;
import client.event.EventCode;
import client.game.Main;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationUtils {

	public static double lat = 0;//纬度
	public static double lng = 0;//经度

	public static void fixPosition() {   
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		String provider = Main.locationManager.getBestProvider(criteria, true);

		Location location = Main.locationManager.getLastKnownLocation(provider);
		updateWithNewLocation(location);
		Main.locationManager.requestLocationUpdates(provider, 2000, 10, locationListener);
	}

	private final static LocationListener locationListener = new LocationListener() {
		public void onLocationChanged(Location location) {
			updateWithNewLocation(location);
		}

		public void onProviderDisabled(String provider) {
//			updateWithNewLocation(null);
		}

		public void onProviderEnabled(String provider) {
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	};

	private static void updateWithNewLocation(Location location) { 
		if (location != null) {
			lat = location.getLatitude();
			lng = location.getLongitude(); 
			Log.info("定位成功!");
			Event.fire(EventCode.UPDATE_LOCATION_SUCCEED);
		} else { 
			Log.info("定位失败!");
			Event.fire(EventCode.UPDATE_LOCATION_FAIL);
		}
		 
	}
}
