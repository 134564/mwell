package org.loon.game.sample.llk;

import java.io.IOException;
import java.util.LinkedList;

import org.loon.framework.android.game.action.sprite.ISprite;
import org.loon.framework.android.game.action.sprite.Label;
import org.loon.framework.android.game.action.sprite.Picture;
import org.loon.framework.android.game.action.sprite.Sprite;
import org.loon.framework.android.game.action.sprite.Sprites;
import org.loon.framework.android.game.action.sprite.StatusBar;
import org.loon.framework.android.game.core.graphics.LColor;
import org.loon.framework.android.game.core.graphics.LFont;
import org.loon.framework.android.game.core.graphics.LImage;
import org.loon.framework.android.game.core.graphics.Screen;
import org.loon.framework.android.game.core.graphics.device.LGraphics;
import org.loon.framework.android.game.core.graphics.window.LMessage;
import org.loon.framework.android.game.core.graphics.window.LPaper;
import org.loon.framework.android.game.core.graphics.window.LSelect;
import org.loon.framework.android.game.core.timer.LTimer;
import org.loon.framework.android.game.core.timer.LTimerContext;

import client.event.Event;
import client.event.EventCode;
import client.event.Eventable;
import client.game.gui.CallBackAction;
import client.game.gui.LMessageEx;
import client.nio.NConnector;
import client.nio.OpCode;
import client.nio.SegmentManager;
import client.nio.UASegment;
import client.script.GameWorld;
import client.script.Player;
import client.util.LocationUtils;

import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class MainScreen extends Screen implements Eventable{

	final private static String SORRY = "抱歉";

	final private static String START_MES = "游戏开始！", 
	SORRY1_MES = SORRY + ", <r刷新/> 在目前使用了。", 
	SORRY2_MES = SORRY + ", <r提示/> 在目前无法使用了。",
			SORRY3_MES = SORRY + ", <r炸弹/> 在目前无法使用了。",
			FIX_FAIL = "定位失败, 重新定位?",
			FIX_SUCCEED = "定位成功, 是否降落",
			EASY_MES = "好的，这非常容易～";

	final private static String WAIT_MES = "预备……", HELP_MES = "我能为你提供什么服务吗？";

	private LTimer timer, timer1;

	private StatusBar progress;

	private Label stage, time;

	private Picture role;

	private LPaper title, over;

	private LMessageEx mes;

	private LSelect select;

	private Sprite helpRole;

	private boolean wingame, failgame, init, overFlag;

	private int stageNo, count;
	
	private CallBackAction initAction;

	public MainScreen() {
		Event.add(EventCode.UPDATE_LOCATION_FAIL, this);
		Event.add(EventCode.UPDATE_LOCATION_SUCCEED, this);
	}

	public void onLoad() { 
		initGround(); 
	}

	// 结束调用
	public void dispose() {
	 
	}
	

	private void initGround() {
		setBackground(Images.getInstance().getImage(10));
		
		role = new Picture(Images.getInstance().getImage(11));
		mes = new LMessageEx(Images.getInstance().getImage(14), (getWidth() - 460) / 2, getHeight() - 126 - 10);
		
	    initAction = new CallBackAction(){
			public void run() {
				if (!init) {
					if (count == 0) {
						role.setImage(Images.getInstance().getImage(12));
						mes.setMessage("开始定位..."); 
					} else if (mes.isComplete()) { 
						if(LocationUtils.lat != 0) {
							fixLocationSucceed();
						}
						 

					}
					count++;
				}

				if (HELP_MES.equalsIgnoreCase(mes.getMessage()) && mes.isComplete()) {
					mes.setVisible(false);
					select = new LSelect(Images.getInstance().getImage(14), (MainScreen.this.getWidth() - 460) / 2,
							MainScreen.this.getHeight() - 126 - 10) {
						public void doClick() {

							switch (getResultIndex()) {
								case 0:
									mes.setVisible(true);
									mes.setMessage(EASY_MES);
									MainScreen.this.remove(this);
									break;
								case 1:
									mes.setVisible(true);
									mes.setMessage(EASY_MES);
									MainScreen.this.remove(this);
									break;
								case 2:
									MainScreen.this.remove(this);
									break;
								case 3:
									mes.setVisible(true);
									MainScreen.this.remove(this);
									mes.setVisible(false);
									role.setVisible(false);
									helpRole.setVisible(true);
									if (stage != null) {
										stage.setVisible(true);
									}
									break;
								default:
									break;
							}
						}

					};
					select.setFontColor(LColor.black);
					select.setAlpha(0.8f);
					select.setTopOffset(-5);
					select.setMessage(new String[] { "1.刷新", "2.登录", "3.炸弹", "4.取消" });
					MainScreen.this.add(select);
					return;

				} else if ((EASY_MES.equalsIgnoreCase(mes.getMessage()) || mes.getMessage().startsWith(SORRY)) && mes.isComplete()) {

					mes.setVisible(false);
					role.setVisible(false);
					helpRole.setVisible(true);
					if (stage != null) {
						stage.setVisible(true);
					}
				}
				
				
			}

			
		};
		mes.setAction(initAction);
		mes.setMessageLength(20);
		mes.setAlpha(0.8f);
		mes.setFontColor(LColor.black);
		mes.setMessage(WAIT_MES);
		add(role);
		add(mes);

	}

	public void alter(LTimerContext t) {
		SegmentManager.cycle();
 
	}

	public void setPaused(boolean p) {
		if (p) {
			getSprites().setVisible(false);
		} else {
			getSprites().setVisible(true);
		}
	}

	public boolean isWait() {
		boolean result = false;
		if (role != null) {
			result = role.isVisible();
		}
		return result;
	}

	// 纯组件制作，所以不需要手动绘图。
	public void draw(LGraphics g) {
		GameWorld.drawAll(g);
		
		g.fill3DRect(100, 100, 20, 20, true);
		g.drawString("TEST", 100, 100 - 20);
	}

	public void onTouch(float x, float y, MotionEvent e, int pointerCount, int pointerId) {

	}

	public boolean onKeyDown(int keyCode, KeyEvent e) {
		return false;
	}

	public boolean onKeyUp(int keyCode, KeyEvent e) {
		return false;
	}

	public boolean onTouchDown(MotionEvent e) {
		if (!init) {
			return false;
		}

		if (helpRole != null) {
			if (!role.isVisible() && helpRole.isVisible()) {

				if (onClick(helpRole)) {
					if (stage != null) {
						stage.setVisible(false);
					}
					helpRole.setVisible(false);
					role.setImage(Images.getInstance().getImage(13));
					role.setVisible(true);
					mes.setMessageLength(20);
					mes.setMessage(HELP_MES);
					mes.setVisible(true);
					return true;
				}
			}
		}

		return true;
	}

	public boolean onTouchMove(MotionEvent e) {
		return true;
	}

	public boolean onTouchUp(MotionEvent e) {
		return true;
	}

	@Override
	public void handEvent(int eventCode, Object[] params) {
		switch (eventCode) {
			case EventCode.UPDATE_LOCATION_SUCCEED:
				fixLocationSucceed();
				break;
			case EventCode.UPDATE_LOCATION_FAIL:
				fixLocationFail();
				break;

			default:
				break;
		}
		
	}

	private void fixLocationFail() {
		mes.setVisible(true);
		mes.setMessage(FIX_FAIL, new CallBackAction() {
			@Override
			public void run() {
				if (mes.isComplete()) {
					Player.login();
					mes.setVisible(false);
					role.setVisible(false);
					helpRole.setVisible(true); 
				}
			}

		});
		
	}

	private void fixLocationSucceed() {
		mes.setVisible(true);
		mes.setMessage(FIX_SUCCEED, new CallBackAction() {
			@Override
			public void run() {
				if (mes.isComplete()) {
					if (GameWorld.spriteList.size() == 0) {
						Player.login();
					}else {
						mes.setVisible(false);
						role.setVisible(false); 
					}
				}
			}

		}); 
	}
	
	 
}
