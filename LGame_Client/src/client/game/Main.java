package client.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.CharBuffer;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.loon.framework.android.game.LGameAndroid2DActivity;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import client.event.Event;
import client.event.EventCode;
import client.location.wifi.CellIDInfo;
import client.location.wifi.CellIDInfoManager;
import client.location.wifi.WifiInfo;
import client.location.wifi.WifiInfoManager;
import client.nio.NConnector;
import client.util.LocationUtils;
import client.util.Log;

public class Main extends LGameAndroid2DActivity {

//	private static final String SERVER_URL = "192.168.1.104:7070";
	private static final String SERVER_URL = "192.168.10.197:7070";
	
	public void onMain() {
		this.maxScreen(800, 480);
		this.initialization(true);
		this.setFPS(40);
		this.setScreen(new MainScreen());
		this.setShowLogo(false);
		this.setShowFPS(true);
		this.showScreen();

		new Thread(new NConnector(SERVER_URL, NConnector.THREAD_UWAP, false)).start();

		this.listenGPSLoc();

		new Thread(new CallGear(WifiInfoManager.instance.getWifiInfo(this),
				CellIDInfoManager.instance.getCellIDInfo(this))).start();

	}

	/**
	 * GPS
	 */
	private void listenGPSLoc() {
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setPowerRequirement(Criteria.POWER_HIGH);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setSpeedRequired(false);
		criteria.setCostAllowed(true);

		String provider = locationManager.getBestProvider(criteria, true);

		if (provider != null) {
			locationManager.requestLocationUpdates(provider, 2000, 10, locationListener);
		}
	}

	/** GPS 监听 */
	private LocationListener locationListener = new LocationListener() {
		@Override
		public void onLocationChanged(Location location) {
			if (location != null) {
				LocationUtils.setLocationByGPS(location);
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

	/**
	 * 基站和wifi
	 * 
	 * @param wifi
	 * @param cellID
	 * @return
	 */
	class CallGear implements Runnable {
		private ArrayList<WifiInfo> wifi;
		private ArrayList<CellIDInfo> cellID;

		public CallGear(ArrayList<WifiInfo> wifi, ArrayList<CellIDInfo> cellID) {
			this.wifi = wifi;
			this.cellID = cellID;
		}

		public void run() {

			if (cellID == null)
				return;

			DefaultHttpClient client = new DefaultHttpClient();

			HttpPost post = new HttpPost("http://www.google.com/loc/json");
			JSONObject holder = new JSONObject();

			try {
				holder.put("version", "1.1.0");
				holder.put("host", "maps.google.com");
				holder.put("home_mobile_country_code", cellID.get(0).mobileCountryCode);
				holder.put("home_mobile_network_code", cellID.get(0).mobileNetworkCode);
				holder.put("radio_type", cellID.get(0).radioType);
				holder.put("request_address", true);
				if ("460".equals(cellID.get(0).mobileCountryCode))
					holder.put("address_language", "zh_CN");
				else
					holder.put("address_language", "en_US");

				JSONObject data, current_data;

				JSONArray array = new JSONArray();

				current_data = new JSONObject();
				current_data.put("cell_id", cellID.get(0).cellId);
				current_data.put("mobile_country_code", cellID.get(0).mobileCountryCode);
				current_data.put("mobile_network_code", cellID.get(0).mobileNetworkCode);
				current_data.put("age", 0);
				array.put(current_data);

				if (cellID.size() > 2) {
					for (int i = 1; i < cellID.size(); i++) {
						data = new JSONObject();
						data.put("cell_id", cellID.get(i).cellId);
						data.put("location_area_code", cellID.get(0).locationAreaCode);
						data.put("mobile_country_code", cellID.get(0).mobileCountryCode);
						data.put("mobile_network_code", cellID.get(0).mobileNetworkCode);
						data.put("age", 0);
						array.put(data);
					}
				}
				holder.put("cell_towers", array);

				if (wifi.get(0).mac != null) {
					data = new JSONObject();
					data.put("mac_address", wifi.get(0).mac);
					data.put("signal_strength", 8);
					data.put("age", 0);
					array = new JSONArray();
					array.put(data);
					holder.put("wifi_towers", array);
				}

				StringEntity se = new StringEntity(holder.toString());
				Log.info("Location send  " + holder.toString());
				post.setEntity(se);
				HttpResponse resp = client.execute(post);

				HttpEntity entity = resp.getEntity();

				BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
				StringBuffer sb = new StringBuffer();
				String result = br.readLine();
				while (result != null) {
					Log.info("Locaiton reseive" + result);
					sb.append(result);
					result = br.readLine();
				}

				data = new JSONObject(sb.toString());
				data = (JSONObject) data.get("location");

				Location loc = new Location(LocationManager.NETWORK_PROVIDER);
				loc.setLatitude((Double) data.get("latitude"));
				loc.setLongitude((Double) data.get("longitude"));
				loc.setAccuracy(Float.parseFloat(data.get("accuracy").toString()));
				loc.setTime(System.currentTimeMillis());

				LocationUtils.setLocationByGear(loc);

			} catch (JSONException e) {
				return;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

	}
}
