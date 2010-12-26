/**
 * All Rights Reserved (C) PearlInPalm Co., Ltd
 * 
 * @file 	GuiCommonDef.h
 * @description 定义了GUI里使用的宏.
 * @author David.Wan
 * @date   2010-09
 */

////////////////////////////////////////////////
//  控件类型.
///////////////////////////////////////////////
#define CONTROL_TYPE_UNKOWN					-1		// Unknown.
#define CONTROL_TYPE_WIDGET					0		// GWidget
#define CONTROL_TYPE_CONTAINER  			1		// GContainer
#define CONTROL_TYPE_WINDOW					2		// GWindow;
#define CONTROL_TYPE_LABEL					3		// GLabel

////////////////////////////////////////////////
//  Layout类型.
///////////////////////////////////////////////
#define LAYOUT_TYPE_NONE					-1		// None
#define LAYOUT_TYPE_H						0		// 水平layout.
#define LAYOUT_TYPE_V  						1		// 垂直layout.
#define LAYOUT_TYPE_BORDER					2		// border Layout
#define LAYOUT_TYPE_GRID					3		// grid Layout.
#define LAYOUT_TYPE_GRIDBAG					3		// gridbag Layout.

#define LAYOUT_ALIGN_HLEFT					0x04 // 左对齐
#define LAYOUT_ALIGN_HCENTER    			0x01 // 居中对齐 
#define LAYOUT_ALIGN_HRIGHT     			0x08 // 右对齐
#define LAYOUT_ALIGN_VBOTTOM 				0x20 // 底对齐 
#define	LAYOUT_ALIGN_VMIDDLE    			0x02 // 居中对齐
#define LAYOUT_ALIGN_VTOP      				0x10 // 顶对齐 

#define LAYOUT_ORIENT_HORIZON				0x10 // 水平朝向 
#define LAYOUT_ORIENT_VERTICAL				0x20 // 垂直朝向


#define BLAYOUT_LOC_NORTH					0x01 // 北
#define BLAYOUT_LOC_SOUTH					0x02 // 南 
#define BLAYOUT_LOC_CENTER					0x04 // 中
#define BLAYOUT_LOC_WEST					0x08 // 西
#define BLAYOUT_LOC_EAST					0x10 // 东

////////////////////////////////////////////////
//  ScrollBar属性.
///////////////////////////////////////////////
#define SCROLLBAR_IHORIZONAL				0	 // 水平
#define SCROLLBAR_IVERTICAL					1	 // 垂直 

#define SCROLLBAR_ISHOW_ALWAYS				0	 // 总是显示滚动条
#define SCROLLBAR_ISHOW_NEVER				1	 // 从不显示滚动条
#define SCROLLBAR_ISHOW_AUTO				2	 // 在必须时显示滚动条

////////////////////////////////////////////////
// 飞行文字的飞行方式
////////////////////////////////////////////////
#define FLYSTR_ORIENT_TOPTOBOTTOM			0 // 从上往下飞
#define FLYSTR_ORIENT_BOTTOMTOTOP			1 // 从下往上飞
#define FLYSTR_ORIENT_LEFTTORIGHT			2 // 从左往右飞
#define FLYSTR_ORIENT_RIGHTTOLEFT			3 // 从右往左飞
#define FLYSTR_ORIENT_HSHOCK				4 // 水平来回飞
#define FLYSTR_ORIENT_VSHOCK				5 // 垂直来回飞

////////////////////////////////////////////////
//  MessageBox Flags.
///////////////////////////////////////////////
#define MB_OK                       		0	 // 一个确认按钮 
#define MB_OKCANCEL                 		1	 // 一个确认按钮， 一个取消按钮 

////////////////////////////////////////////////
//  事件类型
///////////////////////////////////////////////
#define EVENT_TYPE_UNKOWN 					0x0	// unkown 
/* 按键事件: 0x8 */
#define EVENT_TYPE_KEYPRESSED 				9   // (0x8 + 1) 按键被按下. 
#define EVENT_TYPE_KEYRELEASED  			11  // (0x8 + 3) 按键被释放.

/* 触摸事件: 0x80 */
#define EVENT_TYPE_TOUCHPRESSED  			144 // (0x80 + (1 << 4)) 触摸屏被按下.
#define EVENT_TYPE_TOUCHRELEASED 			160 // (0x80 + (2 << 4)) 触摸屏被释放.
#define EVENT_TYPE_TOUCHDRAGED	 			192 // (0x80 + (4 << 4)) 触摸拖动.	
#define EVENT_TYPE_TOUCHMOVED	 			240 // (0x80 + (7 << 4)) 触摸移动.	

/* 焦点事件: 0x800 */
#define EVENT_TYPE_FOCUSGAINED 				2304 // (0x800 + (1 << 8)) 获得焦点. 
#define EVENT_TYPE_FOCUSLOST   				2560 // (0x800 + (2 << 8)) 失去焦点.

/* 控件事件: 0x8000 */
#define EVENT_TYPE_WIDGETRESIZED 			36864 // (0x8000 + (1 << 12)) 控件大小发生变化.
#define EVENT_TYPE_WIDGETMOVED   			40960 // (0x8000 + (2 << 12)) 控件位置发生变化 .
#define EVENT_TYPE_WIDGETSHOWN   			45056 // (0x8000 + (3 << 12)) 控件显示.
#define EVENT_TYPE_WIDGETHIDDEN  			49152 // (0x8000 + (4 << 12)) 控件隐藏.

/* Action事件: 0x80000 */
#define EVENT_TYPE_ACTIONPERFORMED 			589824 // (0x80000 + (1 << 16)) action事件被触发.

////////////////////////////////////////////////
//  按键键码定义. 已废弃, 请使用commondef.h中定义
//  的键值.
///////////////////////////////////////////////
/*
#define KEY_CODE_NONE 				   -1
#define KEY_CODE_UP 					0
#define KEY_CODE_DOWN 					1
#define KEY_CODE_LEFT 					2	
#define KEY_CODE_RIGHT 					3
//-------- 功能键 ----------
#define KEY_CODE_OKAY					4
#define KEY_CODE_A                      5
#define KEY_CODE_B                      6
#define KEY_CODE_C                      7
#define KEY_CODE_D                      8
#define KEY_CODE_LEFT_SOFT 				9
#define KEY_CODE_RIGHT_SOFT 			10
//-------- 数字键 ---------
#define KEY_CODE_NUM0 					11
#define KEY_CODE_NUM1 					12
#define KEY_CODE_NUM2 					13
#define KEY_CODE_NUM3 					14
#define KEY_CODE_NUM4 					15
#define KEY_CODE_NUM5 					16
#define KEY_CODE_NUM6					17
#define KEY_CODE_NUM7 					18
#define KEY_CODE_NUM8 					19
#define KEY_CODE_NUM9 					20
//------- * # ------------
#define KEY_CODE_POUND 					21
#define KEY_CODE_STAR 					22
*/
