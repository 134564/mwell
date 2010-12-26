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
	//     字段								索引
	GButton decBtn;							// 49
	GButton incBtn;							// 50
	GButton bar;							// 51

	// 图片资源的高度
	int scrollBarAmount;   					// 55	滚动把的可滚动区域，不算自身长度.
	int scrollUnite;	  	 				// 56	按下上（垂直滚动条，左 水平滚动条）或按下下（垂直滚动条，右 水平滚动条）导航键时滚动区域的滚动单元.
	
	int orientation;						// 57	滚动条朝向
	
	//int contentAreaH;						// 58	滚动区域高度
	int scrollItemsH;						// 59	滚动区域中控件的总高度
	int barunit;							//scrollbar的滚动步长
	int barscrollunit;						//在触摸屏中，点击增减按钮时scrollpanell的步长
	int oldX;								//保存上一次的x坐标
	int oldY;								//保存上一次的y坐标
	boolean bTraOffSet;					//当用户拖拽scrollbar的时候，可能bar的起始位置不为零
	int nTraOffSet;						//表示如果用户拖拽第一次并没有拖拽到底，当第二次拖拽的时候需要累加上一次拖拽的高度
	int noff;							//表示鼠标点击的位置和bar的相对位移
	boolean bTop;						//表示bar是否移动到最顶端
	boolean bDown;						//表示bar是否移动到最低端
	int nNeedOffSetY;					//在scrollpanel.contaner最大偏移量。

}