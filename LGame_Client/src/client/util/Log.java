package client.util;

import java.io.UnsupportedEncodingException;
 
 
public class Log {
	public static void exception(Exception e){
		e.printStackTrace();
	}
	public static void debug(String str){
		log("****"+str+"****");
	}
	public static void info(String str){
		log("INFO:"+str);
	}
	public static void warn(String string) {
		log("WARN:"+string);
	}
	private static void log(String s){ 
		try {
			android.util.Log.i("Log ", new String(s.getBytes(), "ISO-8859-1"));
			android.util.Log.i("Log ", new String(s.getBytes(), "utf-8"));
			android.util.Log.i("Log ", new String(s.getBytes(), "GBK"));
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace();
		}
		
	}
	public static void err(String string) {
		System.err.println("ERRO:"+string);
		 
	}
}
