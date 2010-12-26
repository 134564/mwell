/**
 * All Rights Reserved (C) PearlInPalm Co., Ltd
 * 
 * @file 	GEvent.h
 * @description �������¼��Ļ����ṹ��.
 * @author David.Wan
 * @date   2010-09
 */
 
#include "../common/GuiCommonDef.h"
#include "../../common/CommonDef.h"

/**
 * �¼�����.
 */
STRUCT GEvent
{
	int eventType;		// �¼�����.
	GWidget eventSrc;	// �¼�Դ.
}

/**
 * �����¼�.
 */
STRUCT GKeyEvent extends GEvent
{
	int keyCode;			// ����.
	int modifierKeyCode;	// ��������.
}

/**
 * �����¼�.
 */ 
STRUCT GTouchEvent extends GEvent
{
	int x;		// ������x����.
	int y;		// ������y����.
}

/**
 * �����¼�.
 */
STRUCT GFocusEvent extends GEvent
{
	// Nothing Here.
}

/**
 * Action�¼�.
 */
STRUCT GActionEvent extends GEvent
{
	String actionId; // ActionId
}
