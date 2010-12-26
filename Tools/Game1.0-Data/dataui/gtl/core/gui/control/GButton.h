/**
 * All Rights Reserved (C) PearlInPalm Co., Ltd
 * 
 * @file 	GButton.h
 * @description ����GButton.
 * @author David.Wan
 * @date   2010-07
 */

#include "GLabel.h"

STRUCT GButton extends GLabel
{
	// ��ť����¼��Ĵ�����, ��GWidget::func_handleKey��GWidget::func_handleTouch�����˽�һ���ķ�װ.
	int btn_clicked;		// 51����ԭ��:  bool btn_clicked(GButton this)
	int GB_BKG_WIDTH;	//52	background pip's width
	int GB_BKG_HEIGHT;	//53	background pip's height
	
	ImageSet image;	//58
	// ͼƬ��Դ������
	int index;		//59
	// ͼƬ�ķ�ת����
	int trans;		//60
	
}
 
 /**
 * (radioButton/CheckBox)���ڵ���.
 */
STRUCT GButtonGroup
{
	// ����
	String groupId;
	// ��������е����е�radioButton.
	Vector rbContainer;
}