package server.io;

public class OpCode {	

	/**
	 * 错误
	 * serial					int
	 * type						short
	 * message					string
	 */
	public static final short ERROR = -1;
	
	/**
	 * 断线
	 */
	public static final short DISCONNECTED = 0;
	
	
	/**
	 * 测试op<br>
	 * int test<br>
	 */
	public static final short TEST_OP_CLIENT = 100;
	/**
	 * 返回测试op<br>
	 * int test + 10<br>
	 */
	public static final short TEST_OP_SERVER = 101;
	
	
	/**
	 * 用户登录<br>
	 * int id<br>
	 */
	public static final short PLAYER_LOGIN_CLIENT = 102;
	/**
	 * 角色信息<br> 
	 * String name
	 */
	public static final short PLAYER_LOGIN_SERVER = 103;
	
	
	
	
}
 