package client.nio;

public class OpCode {	

	/**
	 * ����
	 * serial					int
	 * type						short
	 * message					string
	 */
	public static final short ERROR = -1;
	
	/**
	 * ����
	 */
	public static final short DISCONNECTED = 0;
	
	
	/**
	 * ����op<br>
	 * int test<br>
	 */
	public static final short TEST_OP_CLIENT = 100;
	/**
	 * ���ز���op<br>
	 * int test + 10<br>
	 */
	public static final short TEST_OP_SERVER = 101;
	
	
	/**
	 * 用户登录<br>
	 * String username<br>
	 * String pwd<br>
	 * double id<br>
	 * double id<br>
	 */
	public static final short PLAYER_LOGIN_CLIENT = 102;
	/**
	 * 返回用户登录<br> 
	 * String name
	 */
	public static final short PLAYER_LOGIN_SERVER = 103;
	
	
	
	
}
 