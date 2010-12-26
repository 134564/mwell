/**
 * All Rights Reserved (C) PearlInPalm Co., Ltd
 * 
 * @file 	GWidget.h
 * @description ����GWidget.
 * @author David.Wan
 * @date   2010-06
 */
#include "./GGuiObject.h"

/**
 * GWidget�����пؼ��Ļ���.
 */
STRUCT GWidget extends GGuiObject
{	
	//     �ֶ�								  ����
	
	// �̳���GGuiObject
	//---------------
	// int typeId;							// 0
	// int self;							// 1
	// int clientId;						// 2
	//---------------
	
	/**
	 * widget����, ����ʵ���������.
	 */
	int widgetType;							// 3
	
	//����ڸ��ؼ�������
	int x;								    // 4
	int y;									// 5
	int w;									// 6
	int h;									// 7
	
	//boolean��־λ.
	/**
	 * �Ƿ���Ʊ߿�
	 */
	boolean isBorderPainted;				// 8
	/**
	 * �Ƿ���Ʊ���
	 */
	boolean isBackgroundPainted;			// 9
	/**
	 * �Ƿ���ƽ���.
	 */
	boolean isSelectionPainted;				// 10
	/**
	 * �ÿؼ��Ƿ���Ч, ��������Ч״̬, 
	 * �����Զ�����ÿؼ��ľ�������, ��
	 * ������layout�Ļ�, �������һ��layout
	 * ����.
	 */
	boolean isValid;						// 11
	/** 
	 * �Ƿ�ɼ�.
	 */
	boolean isVisible;						// 12
	/**
	 * �Ƿ���Ի�ý���.
	 */
	boolean focusable;						// 13
	/**
	 * �Ƿ�����Ӧ�¼�.
	 */
	boolean enabled;						// 14
	/**
	 * �Ƿ�������.
	 */
	boolean isHandleInput;					// 15
	
	//border�ߴ�.
	int borderLeft;							// 16
	int borderTop;							// 17
	int borderRight;						// 18
	int borderBottom;						// 19
	
	// inset�ߴ�.
	int insetLeft;							// 20
	int insetRight;							// 21
	int insetTop;							// 22
	int insetBottom;						// 23
	
	// ��ѳߴ�.
	int preferedWidth;						// 24
	int preferedHeight;						// 25
	
	// ������ɫ(ARGB)
	int backgroundColor;					// 26
	int foregroundColor;					// 27
	int borderColor;						// 28
	int selectionColor;						// 29
	
	// �������.
	// Cycle ���.	
	int func_cycle;							// 30 void cycle()
	int func_cycleUI;						// 31 void cycleUI()
	int func_paint;							// 32 void paint()
	int func_packet;						// 33 void packet()
	int func_destroy;						// 34 void destroy()
	
	// �ؼ����.
	// �������ҽ����л��ؼ�.
	GWidget upWidget;						// 35
	GWidget downWidget;						// 36
	GWidget leftWidget;						// 37
	GWidget rightWidget;					// 38
	
	// �¼�����.
	int func_handleKey;						// 39 bool handleKey(GWidget this, int eventType, int keyCode, int modifierCode)
	int func_handleTouch;					// 40 bool handleTouch(GWidget this, int touchType, int x, int y)
	int func_handleFocus;					// 41 bool handleFocus(GWidget this, int focusType)
	int func_handleAction;					// 42 bool handleAction(GWidget this, String actionId)
	int func_handleWidget;					// 43 bool handleWidget(GWidget this, int eventType)
	
	// ����λ��.
	int absX;								// 44
	int absY;								// 45
	
	// ֧��Scrollʱ��ƫ����
	int offsetX;							// 46
	int offsetY;							// 47
	
	/* ����ֶ����ڵ���;�Ǳ��浱ǰ�ؼ�
	 * �����ĸ��Ͽؼ�, �Ժ���������µ���չ.
	 */
	Object observer;						// 48 
	
	boolean		bDraged;					// 49
	boolean		bMoved;						// 50
	
	// �û��Լ����ÿؼ���Ϣ����ؼ������ȣ�
	Object userData;						// 51
}
