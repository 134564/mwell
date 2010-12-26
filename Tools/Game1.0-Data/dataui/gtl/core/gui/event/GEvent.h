/**
 * All Rights Reserved (C) PearlInPalm Co., Ltd
 * 
 * @file 	GEvent.h
 * @description 定义了事件的基本结构体.
 * @author David.Wan
 * @date   2010-09
 */
 
#include "../common/GuiCommonDef.h"
#include "../../common/CommonDef.h"

/**
 * 事件基类.
 */
STRUCT GEvent
{
	int eventType;		// 事件类型.
	GWidget eventSrc;	// 事件源.
}

/**
 * 按键事件.
 */
STRUCT GKeyEvent extends GEvent
{
	int keyCode;			// 按键.
	int modifierKeyCode;	// 辅助按键.
}

/**
 * 触摸事件.
 */ 
STRUCT GTouchEvent extends GEvent
{
	int x;		// 触摸点x坐标.
	int y;		// 触摸点y坐标.
}

/**
 * 焦点事件.
 */
STRUCT GFocusEvent extends GEvent
{
	// Nothing Here.
}

/**
 * Action事件.
 */
STRUCT GActionEvent extends GEvent
{
	String actionId; // ActionId
}
