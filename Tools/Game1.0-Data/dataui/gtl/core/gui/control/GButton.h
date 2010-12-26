/**
 * All Rights Reserved (C) PearlInPalm Co., Ltd
 * 
 * @file 	GButton.h
 * @description 声明GButton.
 * @author David.Wan
 * @date   2010-07
 */

#include "GLabel.h"

STRUCT GButton extends GLabel
{
	// 按钮点击事件的处理方法, 对GWidget::func_handleKey和GWidget::func_handleTouch进行了进一步的封装.
	int btn_clicked;		// 51函数原型:  bool btn_clicked(GButton this)
	int GB_BKG_WIDTH;	//52	background pip's width
	int GB_BKG_HEIGHT;	//53	background pip's height
	
	ImageSet image;	//58
	// 图片资源的索引
	int index;		//59
	// 图片的翻转属性
	int trans;		//60
	
}
 
 /**
 * (radioButton/CheckBox)所在的组.
 */
STRUCT GButtonGroup
{
	// 组名
	String groupId;
	// 保存该组中的所有的radioButton.
	Vector rbContainer;
}