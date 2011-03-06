package client.nio;

import client.script.GameWorld;
 

public class NConnector implements Runnable {


	private String requestURL; // VM请求URL 
	private boolean async; // 是否为异步请求
	
	public int state; // 请求状态

	public static final int DOWNLOAD_STATE_ERROR = 0;
	public static final int DOWNLOAD_STATE_RUNNING = 1;
	public static final int DOWNLOAD_STATE_OK = 2;

	private int threadMode; // 0 - 取HTTP内容，1 - 建立UWAP连接, 2 - 发送短信

	public static final int THREAD_HTTP = 0;
	public static final int THREAD_UWAP = 1;
	public static final int THREAD_SMS = 2;
	
	 /** 建立的连接 */
    public static NSocketConnection connection = null;
	
	/**
	 * 创建一个网络连接任务。
	 * 
	 * @param url
	 * @param threadMode
	 * @param async
	 */
	public NConnector(String url, int threadMode, boolean async) {
		this.requestURL = url;
		this.threadMode = threadMode;
		this.async = async;
	}

	public static int sendRequest(UASegment segment) {
		if (connection != null) {
			NSocketConnection.writeSegment(segment);
			// System.out.println("request sent:"+segment.type);
		} else {
			GameWorld.error = "==============warning: connection is null. request not sent";
		}
		return segment.serial;
	}

	/**
	 * 建立连接。
	 * 
	 * @throws Exception
	 */
	public static NSocketConnection createConnection(String url) throws Exception {
		// 建立Socket连接。
		if (connection == null) {
			GameWorld.error = "url = " + url;

			connection = new NSocketConnection(url);
			connection.start();
		}

		return connection;
	}


	@Override
	public void run() {
		if(this.threadMode == THREAD_UWAP) {
			try {
				createConnection(this.requestURL);
			} catch (Exception e) { 
				e.printStackTrace();
			}
		}

	}

	public static void closeConnection() {
		if(connection!= null){
			connection.close();
		}
		connection = null;
		
	}

	public static void tryReconnect() {
		closeConnection();
		
	}
}
