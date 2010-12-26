/**
 * All Rights Reserved (C) PearlInPalm Co., Ltd
 * 
 * @file 	GuiCommonDef.h
 * @description ������GUI��ʹ�õĺ�.
 * @author David.Wan
 * @date   2010-09
 */

////////////////////////////////////////////////
//  �ؼ�����.
///////////////////////////////////////////////
#define CONTROL_TYPE_UNKOWN					-1		// Unknown.
#define CONTROL_TYPE_WIDGET					0		// GWidget
#define CONTROL_TYPE_CONTAINER  			1		// GContainer
#define CONTROL_TYPE_WINDOW					2		// GWindow;
#define CONTROL_TYPE_LABEL					3		// GLabel

////////////////////////////////////////////////
//  Layout����.
///////////////////////////////////////////////
#define LAYOUT_TYPE_NONE					-1		// None
#define LAYOUT_TYPE_H						0		// ˮƽlayout.
#define LAYOUT_TYPE_V  						1		// ��ֱlayout.
#define LAYOUT_TYPE_BORDER					2		// border Layout
#define LAYOUT_TYPE_GRID					3		// grid Layout.
#define LAYOUT_TYPE_GRIDBAG					3		// gridbag Layout.

#define LAYOUT_ALIGN_HLEFT					0x04 // �����
#define LAYOUT_ALIGN_HCENTER    			0x01 // ���ж��� 
#define LAYOUT_ALIGN_HRIGHT     			0x08 // �Ҷ���
#define LAYOUT_ALIGN_VBOTTOM 				0x20 // �׶��� 
#define	LAYOUT_ALIGN_VMIDDLE    			0x02 // ���ж���
#define LAYOUT_ALIGN_VTOP      				0x10 // ������ 

#define LAYOUT_ORIENT_HORIZON				0x10 // ˮƽ���� 
#define LAYOUT_ORIENT_VERTICAL				0x20 // ��ֱ����


#define BLAYOUT_LOC_NORTH					0x01 // ��
#define BLAYOUT_LOC_SOUTH					0x02 // �� 
#define BLAYOUT_LOC_CENTER					0x04 // ��
#define BLAYOUT_LOC_WEST					0x08 // ��
#define BLAYOUT_LOC_EAST					0x10 // ��

////////////////////////////////////////////////
//  ScrollBar����.
///////////////////////////////////////////////
#define SCROLLBAR_IHORIZONAL				0	 // ˮƽ
#define SCROLLBAR_IVERTICAL					1	 // ��ֱ 

#define SCROLLBAR_ISHOW_ALWAYS				0	 // ������ʾ������
#define SCROLLBAR_ISHOW_NEVER				1	 // �Ӳ���ʾ������
#define SCROLLBAR_ISHOW_AUTO				2	 // �ڱ���ʱ��ʾ������

////////////////////////////////////////////////
// �������ֵķ��з�ʽ
////////////////////////////////////////////////
#define FLYSTR_ORIENT_TOPTOBOTTOM			0 // �������·�
#define FLYSTR_ORIENT_BOTTOMTOTOP			1 // �������Ϸ�
#define FLYSTR_ORIENT_LEFTTORIGHT			2 // �������ҷ�
#define FLYSTR_ORIENT_RIGHTTOLEFT			3 // ���������
#define FLYSTR_ORIENT_HSHOCK				4 // ˮƽ���ط�
#define FLYSTR_ORIENT_VSHOCK				5 // ��ֱ���ط�

////////////////////////////////////////////////
//  MessageBox Flags.
///////////////////////////////////////////////
#define MB_OK                       		0	 // һ��ȷ�ϰ�ť 
#define MB_OKCANCEL                 		1	 // һ��ȷ�ϰ�ť�� һ��ȡ����ť 

////////////////////////////////////////////////
//  �¼�����
///////////////////////////////////////////////
#define EVENT_TYPE_UNKOWN 					0x0	// unkown 
/* �����¼�: 0x8 */
#define EVENT_TYPE_KEYPRESSED 				9   // (0x8 + 1) ����������. 
#define EVENT_TYPE_KEYRELEASED  			11  // (0x8 + 3) �������ͷ�.

/* �����¼�: 0x80 */
#define EVENT_TYPE_TOUCHPRESSED  			144 // (0x80 + (1 << 4)) ������������.
#define EVENT_TYPE_TOUCHRELEASED 			160 // (0x80 + (2 << 4)) ���������ͷ�.
#define EVENT_TYPE_TOUCHDRAGED	 			192 // (0x80 + (4 << 4)) �����϶�.	
#define EVENT_TYPE_TOUCHMOVED	 			240 // (0x80 + (7 << 4)) �����ƶ�.	

/* �����¼�: 0x800 */
#define EVENT_TYPE_FOCUSGAINED 				2304 // (0x800 + (1 << 8)) ��ý���. 
#define EVENT_TYPE_FOCUSLOST   				2560 // (0x800 + (2 << 8)) ʧȥ����.

/* �ؼ��¼�: 0x8000 */
#define EVENT_TYPE_WIDGETRESIZED 			36864 // (0x8000 + (1 << 12)) �ؼ���С�����仯.
#define EVENT_TYPE_WIDGETMOVED   			40960 // (0x8000 + (2 << 12)) �ؼ�λ�÷����仯 .
#define EVENT_TYPE_WIDGETSHOWN   			45056 // (0x8000 + (3 << 12)) �ؼ���ʾ.
#define EVENT_TYPE_WIDGETHIDDEN  			49152 // (0x8000 + (4 << 12)) �ؼ�����.

/* Action�¼�: 0x80000 */
#define EVENT_TYPE_ACTIONPERFORMED 			589824 // (0x80000 + (1 << 16)) action�¼�������.

////////////////////////////////////////////////
//  �������붨��. �ѷ���, ��ʹ��commondef.h�ж���
//  �ļ�ֵ.
///////////////////////////////////////////////
/*
#define KEY_CODE_NONE 				   -1
#define KEY_CODE_UP 					0
#define KEY_CODE_DOWN 					1
#define KEY_CODE_LEFT 					2	
#define KEY_CODE_RIGHT 					3
//-------- ���ܼ� ----------
#define KEY_CODE_OKAY					4
#define KEY_CODE_A                      5
#define KEY_CODE_B                      6
#define KEY_CODE_C                      7
#define KEY_CODE_D                      8
#define KEY_CODE_LEFT_SOFT 				9
#define KEY_CODE_RIGHT_SOFT 			10
//-------- ���ּ� ---------
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
