/**
 * All Rights Reserved (C) PearlInPalm Co., Ltd
 * 
 * @file 	GScrollBar.h
 * @description GScrollBar.
 * @author pp
 * @date   2010-07
 */
#include ".\GContainer.h"
#include ".\GButton.h"

STRUCT GScrollBar extends GContainer 
{
	//     �ֶ�								����
	GButton decBtn;							// 49
	GButton incBtn;							// 50
	GButton bar;							// 51

	// ͼƬ��Դ�ĸ߶�
	int scrollBarAmount;   					// 55	�����ѵĿɹ������򣬲���������.
	int scrollUnite;	  	 				// 56	�����ϣ���ֱ���������� ˮƽ�������������£���ֱ���������� ˮƽ��������������ʱ��������Ĺ�����Ԫ.
	
	int orientation;						// 57	����������
	
	//int contentAreaH;						// 58	��������߶�
	int scrollItemsH;						// 59	���������пؼ����ܸ߶�
	int barunit;							//scrollbar�Ĺ�������
	int barscrollunit;						//�ڴ������У����������ťʱscrollpanell�Ĳ���
	int oldX;								//������һ�ε�x����
	int oldY;								//������һ�ε�y����
	boolean bTraOffSet;					//���û���קscrollbar��ʱ�򣬿���bar����ʼλ�ò�Ϊ��
	int nTraOffSet;						//��ʾ����û���ק��һ�β�û����ק���ף����ڶ�����ק��ʱ����Ҫ�ۼ���һ����ק�ĸ߶�
	int noff;							//��ʾ�������λ�ú�bar�����λ��
	boolean bTop;						//��ʾbar�Ƿ��ƶ������
	boolean bDown;						//��ʾbar�Ƿ��ƶ�����Ͷ�
	int nNeedOffSetY;					//��scrollpanel.contaner���ƫ������

}