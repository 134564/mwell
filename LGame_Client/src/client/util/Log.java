package client.util;
 
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
		System.out.println(s);
	}
	public static void err(String string) {
		System.err.println("ERRO:"+string);
	}
}
