package client.nio;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer; 
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public class NSocketConnection implements Runnable {
	private boolean cut;

	// #if ConnectionThread == dual
	protected NSocketConnection _reader;
	// #endif
	protected NSocketConnection _writer;

	protected boolean _isConnected;
	public static Vector<UASegment> _segments = new Vector<UASegment>();

	private Thread _connectThread = null;

	public long lastWriteTime = System.currentTimeMillis();
	public long lastReadTime = System.currentTimeMillis();

	public static boolean offlineMode = false;

	protected final byte[] HEAD = { 'U', 'A' };

	protected final byte[] HEAD_RECV = { 'U' };

	// #ifdef buildtest
	public static final int TIME_SEND_TIMEOUT = 6000;
	// #else
	// # public static final int TIME_SEND_TIMEOUT = 60000;
	// #endif

	private NSocketConnection parent;
	private byte runType; // 0 - Reader, 1 - Writer

	protected DataInputStream _in;
	protected DataOutputStream _out;
	// protected StreamConnection _connection;
	protected SocketChannel _channel;
	protected Selector selector;
	protected SelectionKey skey;
	protected ByteBuffer readBuffer = ByteBuffer.allocate(1024);
	protected ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

	public NSocketConnection(String url) throws IOException { 
		String u = url.split(":")[0];
		String port = url.split(":")[1]; 
		
		
		_channel = SocketChannel.open(new InetSocketAddress(u, Integer.parseInt(port)));
		
		_channel.configureBlocking(false); // 非阻塞
		 
		_isConnected = true;
		_segments.removeAllElements();
 
	}

	public NSocketConnection(NSocketConnection par, byte rt) {
		parent = par;
		runType = rt;
		 
	}

	public void run() {
		if (parent == null) { 
			
			try {
				_reader = new NSocketConnection(this, (byte) 0);
				_reader._channel = this._channel;
				_reader.selector = Selector.open(); 
				_reader.skey = _reader._channel.register(_reader.selector, SelectionKey.OP_READ); 
				new Thread(_reader).start();

				_writer = new NSocketConnection(this, (byte) 1);
				_writer._channel = this._channel;
				_writer.selector = Selector.open(); 
				_writer.skey = _writer._channel.register(_writer.selector, SelectionKey.OP_WRITE); 
				new Thread(_writer).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		else if (runType == (byte) 0) {
			runReader();
		} 
		else {
			runWriter();
		}

	}

	public void start() {
		_connectThread = new Thread(this);
		_connectThread.start();
	}

	public static void writeSegment(UASegment segment) {
		_segments.addElement(segment);
	}

	protected void write(UASegment segment) throws IOException {
		segment.flush();

		if (offlineMode) {
			return;
		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);

		dos.write(HEAD);
		dos.writeInt(segment.data.length + HEAD.length + 4);
		dos.write(segment.data);
		dos.flush();
		
		writeBuffer.clear(); 
		writeBuffer.put(bos.toByteArray());  
		writeBuffer.flip();
//		writeBuffer.rewind();
		_channel.write(writeBuffer);
		
		dos.close();
	}

	public static long getNumber(byte[] buf, int off, int len) {
		long l = 0;

		for (int i = 0; i < len; i++) {
			l <<= 8;
			l += (buf[off + i]) & 0xff;
		}

		return l;
	}

	public UASegment readSegment(SocketChannel sc) throws IOException { 
		int read = 0;
		readBuffer.clear();
		while ((read = sc.read(readBuffer)) != -1) {
			if (read == 0) {
				break;
			}
		}
		readBuffer.flip();
		byte[] buf = new byte[readBuffer.limit()];
		
		readBuffer.get(buf); 
		// byte[] head = new byte[2];

		// if(readFull(sc, head) != head.length){
		// return null;
		// }
		if (buf.length < 2) {
			return null;
		}

		for (int i = 0; i < HEAD_RECV.length; i++) {
			if (HEAD[i] != buf[i]) {
				throw new IOException("Wrong protocol");
			}
		}

		byte[] lenInHead = null;

		switch (buf[1]) {
			case 'A':
				lenInHead = new byte[4];
				break;
			case 'B':
				lenInHead = new byte[2];
				break;
			case 'C':
				lenInHead = new byte[1];
				break;
			default:
				throw new IOException("Wrong protocol");
		}

		System.arraycopy(buf, 2, lenInHead, 0, lenInHead.length);
		
		if (buf.length < lenInHead.length + 2) {
			return null;
		}

		int len = 0;

		switch (buf[1]) {
			case 'A':
				len = (int) getNumber(lenInHead, 0, 4);
				break;
			case 'B':
				len = (int) (getNumber(lenInHead, 0, 2) & 0xFFFF);
				break;
			case 'C':
				len = (int) (getNumber(lenInHead, 0, 1) & 0xFF);
				break;
		}

		len -= 2 + lenInHead.length;
		byte[] bufs;
		if (len > 0) {
			bufs = new byte[len];
			System.arraycopy(buf, 2 + lenInHead.length, bufs, 0, bufs.length);
			UASegment data = new UASegment(bufs);

			return data;
		} else {
			return null;
		}

	}

	public void processSegment(UASegment segment) throws IOException {
		SegmentManager.addSegment(segment);
	}

	public void tryReconnect() {
		// 试图重新登录
		NConnector.tryReconnect();
	}

	public void testClose() {
		_in = null;
	}

	public void close() {
		_isConnected = false;

		/*
		 * try{ if(_connectThread != null){ _connectThread.join(); }
		 * }catch(InterruptedException ex5){ }
		 */

		if (_in != null) {
			try {
				_in.close();
			} catch (IOException ex2) {
			}
		}

		if (_out != null) {
			try {
				_out.close();
			} catch (IOException ex3) {
			}
		}

		if (_channel != null) {
			try {
				_channel.close();
			} catch (IOException ex4) {
			}
		}

		_in = null;
		_out = null;
		_channel = null;
	}

	// #if ConnectionThread == dual
	public void runReader() {
		// 轮询
		while (parent._isConnected) {
			try {
				if (parent.cut) {
					throw new Exception("断网测试");
				}
				// 获取Selector返回的时间值
				int n = selector.select();

				// 当传回的值大于0时，读事件发生了
				if (n > 0) {
					Set<SelectionKey> set = selector.selectedKeys();
					Iterator<SelectionKey> it = set.iterator();

					while (it.hasNext()) {
						skey = it.next();
						it.remove();

						if (skey.isReadable()) {
							SocketChannel sc = (SocketChannel) skey.channel();
							UASegment segment = parent.readSegment(sc);

							if (segment == null) {
								continue;
							}

							parent.lastReadTime = System.currentTimeMillis();

							System.out.print("UASocketConnection.runReader()");

							parent.processSegment(segment);
						}
					}
				}

			} catch (Exception ex) {
				ex.printStackTrace();
				try {
					if (parent._isConnected) {
						NConnector.closeConnection();
						parent.tryReconnect();

						// EventManager.addEvent(Const.EVENT_DISCONNECTED, 0);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				break;
			}
		}
	}

	// #endif

	// #if ConnectionThread == dual
	public void runWriter() {
		while (parent._isConnected) {
			try {
				if (parent.cut) {
					throw new Exception("断网测试");
				}

				while (NSocketConnection._segments.size() != 0) {
					UASegment segment = (UASegment) NSocketConnection._segments.elementAt(0);
					NSocketConnection._segments.removeElementAt(0);

					parent.write(segment);

					parent.lastWriteTime = System.currentTimeMillis();
				}
			} catch (Exception ex) { 
				ex.printStackTrace(); 

				try {
					if (parent._isConnected) {
						NConnector.closeConnection();
						parent.tryReconnect();
//						EventManager.addEvent(Const.EVENT_DISCONNECTED, 0);
					}
				} catch (Exception e) { 
					e.printStackTrace(); 
				}

				break;
			} finally {
				try {
					Thread.sleep(50);
				} catch (Exception e) {
				}
			}
		}
	}
 

	public void cut(boolean cut) {
		this.cut = cut;
	}
}