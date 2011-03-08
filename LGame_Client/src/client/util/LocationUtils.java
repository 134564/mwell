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

	public static int locationedType = -1;// 0 == GPS, 1 == Gear

	public static double lat = 0;// 纬度
	public static double lng = 0;// 经度

	public static void fixPosition() {

	}

	public static void setLocationByGPS(Location location) {
		LocationUtils.lat = location.getLatitude();
		LocationUtils.lng = location.getLongitude();

		locationedType = 0;
		Event.fire(EventCode.UPDATE_LOCATION_SUCCEED);
	}

	public static void setLocationByGear(Location loc) {
		if (locationedType == -1) {
			LocationUtils.lat = loc.getLatitude();
			LocationUtils.lng = loc.getLongitude();
			Event.fire(EventCode.UPDATE_LOCATION_SUCCEED);
		}

	}

}
