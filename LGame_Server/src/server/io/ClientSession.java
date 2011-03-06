package server.io;


/*
 * ÿ�����Ӷ�Ӧһ��ClientSession�������������ӷ�����ת���Ļ���ֱ�Ӷ��������
 */
public interface ClientSession {
	
	public int getId();
	
	/**
	 * �Ƿ�������״̬
	 */
	public boolean isConnected();

	/**
	 * �������ݰ�
	 * 
	 * @param packet
	 */
	public void send(Packet packet);

	/**
	 * �ر�����
	 */
	public void close();

	/**
	 * ������ݰ��������
	 */
	public PacketHandler getHandler();

	/**
	 * ��ȡ��ǰ��Client���η������ܷ���null�������ڽ�ɫ��û�е�¼�������
	 */
	public Client getClient();

	/**
	 * ���õ�ǰ��Client
	 */
	public void setClient(Client client);
	
	public boolean update();
	
	public void keepLive();
	
	public void addPacket(Packet packet);
	
	public void addAsyncCall(AsyncCall call);
	
	public void authenticate(Identity identity) throws SecurityException;
	
	public Identity getIdentity();
	
	
	/**
	 * ȡ�ͻ���IP��ַ
	 */
	public String getClientIP();
	
	/**
	 * ����Ƿ������¼����������������������¼��
	 */
	public boolean checkOnlineCount(int currentLoginedAccounts);
}
