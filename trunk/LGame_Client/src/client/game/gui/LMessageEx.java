package client.game.gui;

import org.loon.framework.android.game.core.graphics.LImage;
import org.loon.framework.android.game.core.graphics.window.LMessage;
 

public class LMessageEx extends LMessage {
    public CallBackAction action;
	public LMessageEx(int width, int height) {
		super(width, height); 
	}
	

	public LMessageEx(LImage image, int i, int j) {
		super(image, i, j);
	}


	public void doClick() {
		if (action == null) {
			
		}else {
			action.run();
		}
	}
	
	public void setMessage(String msg, CallBackAction action) {
		this.action = action;
		super.setMessage(msg);
	}

	public void setAction(CallBackAction action) {
		this.action = action;

	}
	 

}
