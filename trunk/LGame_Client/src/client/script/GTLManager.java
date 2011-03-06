package client.script;
 
 
import client.game.Main;
import client.nio.OpCode;
import client.nio.SegmentManager;
import client.nio.UASegment;
import client.util.Log;

public class GTLManager {

	public static void handleSegment(UASegment segment) {
		segment.reset();
		Log.info("服务器返回数据　：　" + segment.type);
		switch(segment.type) { 
			case OpCode.TEST_OP_SERVER : {
			    segment.readInt(); 
				Player.login(); 
				break;
			}
			case OpCode.PLAYER_LOGIN_SERVER : {  
				new Player(segment);
				break;
			}
		}
	}

}
