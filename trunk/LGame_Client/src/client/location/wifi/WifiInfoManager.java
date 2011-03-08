package client.location.wifi;
import java.util.ArrayList;

import android.content.Context;
import android.net.wifi.WifiManager;

public class WifiInfoManager {
	
	private WifiManager wm;
	
	public WifiInfoManager(){}
	
	public ArrayList<WifiInfo> getWifiInfo(Context context){
		ArrayList<WifiInfo> wifi = new ArrayList<WifiInfo>();
		wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = new WifiInfo();
		info.mac = wm.getConnectionInfo().getBSSID();
		wifi.add(info);
		return wifi;
	}
	
	public static WifiInfoManager instance = new WifiInfoManager();
}