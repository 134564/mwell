package client.nio;

import java.util.Vector;

import client.script.GTLManager;
import client.util.Log;

public class SegmentManager {
	/** 接收到的网络包 */
	public static Vector<UASegment> segments = new Vector<UASegment>();
	public static UASegment nextPacket;

	/**
	 * 保存服务器回传的包。
	 * 
	 * @param segment 服务器回传的包。
	 */
	public static void addSegment(UASegment segment) {
		segments.addElement(segment);
	}

	public static void cycle() {
		int len = segments.size();
		for (int i = 0; i < len; i++) {
			UASegment segment = (UASegment) segments.elementAt(0);
			segments.removeElementAt(0);
			try {
				handleSegment(segment);
			} catch (Exception e) {
				Log.exception(e);
			}
		}

	}

	private static void handleSegment(UASegment segment) {
		nextPacket = segment;

		try {
			 GTLManager.handleSegment(segment);

			if (!segment.handled) {
				segment.reset();
			}
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			nextPacket = null;
		}

	}

}
