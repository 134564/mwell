/**
 * All Rights Reserved (C) PearlInPalm Co., Ltd
 * 
 * @file 	GWidget.h
 * @description 声明GWidget.
 * @author David.Wan
 * @date   2010-06
 */
#include "./GGuiObject.h"

/**
 * GWidget是所有控件的基类.
 */
STRUCT GWidget extends GGuiObject
{	
	//     字段								  索引
	
	// 继承自GGuiObject
	//---------------
	// int typeId;							// 0
	// int self;							// 1
	// int clientId;						// 2
	//---------------
	
	/**
	 * widget类型, 用于实现主题绘制.
	 */
	int widgetType;							// 3
	
	//相对于父控件的坐标
	int x;								    // 4
	int y;									// 5
	int w;									// 6
	int h;									// 7
	
	//boolean标志位.
	/**
	 * 是否绘制边框
	 */
	boolean isBorderPainted;				// 8
	/**
	 * 是否绘制背景
	 */
	boolean isBackgroundPainted;			// 9
	/**
	 * 是否绘制焦点.
	 */
	boolean isSelectionPainted;				// 10
	/**
	 * 该控件是否有效, 若处于无效状态, 
	 * 将会自动计算该控件的绝对坐标, 如
	 * 果存在layout的话, 将会进行一次layout
	 * 操作.
	 */
	boolean isValid;						// 11
	/** 
	 * 是否可见.
	 */
	boolean isVisible;						// 12
	/**
	 * 是否可以获得焦点.
	 */
	boolean focusable;						// 13
	/**
	 * 是否能响应事件.
	 */
	boolean enabled;						// 14
	/**
	 * 是否处理输入.
	 */
	boolean isHandleInput;					// 15
	
	//border尺寸.
	int borderLeft;							// 16
	int borderTop;							// 17
	int borderRight;						// 18
	int borderBottom;						// 19
	
	// inset尺寸.
	int insetLeft;							// 20
	int insetRight;							// 21
	int insetTop;							// 22
	int insetBottom;						// 23
	
	// 最佳尺寸.
	int preferedWidth;						// 24
	int preferedHeight;						// 25
	
	// 绘制颜色(ARGB)
	int backgroundColor;					// 26
	int foregroundColor;					// 27
	int borderColor;						// 28
	int selectionColor;						// 29
	
	// 函数相关.
	// Cycle 相关.	
	int func_cycle;							// 30 void cycle()
	int func_cycleUI;						// 31 void cycleUI()
	int func_paint;							// 32 void paint()
	int func_packet;						// 33 void packet()
	int func_destroy;						// 34 void destroy()
	
	// 控件相关.
	// 上下左右焦点切换控件.
	GWidget upWidget;						// 35
	GWidget downWidget;						// 36
	GWidget leftWidget;						// 37
	GWidget rightWidget;					// 38
	
	// 事件处理.
	int func_handleKey;						// 39 bool handleKey(GWidget this, int eventType, int keyCode, int modifierCode)
	int func_handleTouch;					// 40 bool handleTouch(GWidget this, int touchType, int x, int y)
	int func_handleFocus;					// 41 bool handleFocus(GWidget this, int focusType)
	int func_handleAction;					// 42 bool handleAction(GWidget this, String actionId)
	int func_handleWidget;					// 43 bool handleWidget(GWidget this, int eventType)
	
	// 绝对位置.
	int absX;								// 44
	int absY;								// 45
	
	// 支持Scroll时的偏移量
	int offsetX;							// 46
	int offsetY;							// 47
	
	/* 这个字段现在的用途是保存当前控件
	 * 所属的复合控件, 以后可能用于新的扩展.
	 */
	Object observer;						// 48 
	
	boolean		bDraged;					// 49
	boolean		bMoved;						// 50
	
	// 用户自己设置控件信息（如控件索引等）
	Object userData;						// 51
}
