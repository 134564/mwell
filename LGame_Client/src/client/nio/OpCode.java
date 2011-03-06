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
	 * �û���¼<br>
	 * int id<br>
	 */
	public static final short PLAYER_LOGIN_CLIENT = 102;
	/**
	 * ��ɫ��Ϣ<br> 
	 * String name
	 */
	public static final short PLAYER_LOGIN_SERVER = 103;
	
	
	
	
}
 