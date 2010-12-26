#include "./GContainer.h"
#include "./GScrollBar.h"

STRUCT GScrollPanel extends GContainer
{
	
	//			字段							索引（在GWidget的基础上增加）
	/**
	 * SCrollPanel中的内容区域.
	 */
	GContainer contentArea;					// 1

	/**
	 * SCrollPanel中的垂直滚动条.
	 */
	GScrollBar vScrollBar;					// 2
	/**
	 * SCrollPanel中的水平滚动条.
	 */
	GScrollBar hScrollBar;					// 3
	
	/**
	 * SCrollPanel中的四个箭头
	 */
	GButton upArrow;						// 4
	GButton downArrow;						// 5
	GButton leftArrow;						// 6
	GButton rightArrow;						// 7

	// 函数相关.
	int func_touchPressed;					// 8
	int func_touchReleased;					// 9
	int func_touchDragged;					// 10
	int func_keyPressed;					// 11
	int func_keyReleased;					// 12
	
	// 水平滚动条是否绘制
	boolean isHScrollBarPaint;				// 
	// 垂直滚动条是否绘制
	boolean isVScrollBarPaint;				//
	
	// 水平方向导航键是否绘制
	boolean isHArrowPaint;
	// 垂直方向导航键是否绘制
	boolean isVArrowPaint;
	
	// 滚动区域中控件的偏移量
	int scrollOffsetX;
	int scrollOffsetY;
	//上导航键是否绘制
	boolean isupArrowPaint;
	//下导航键是否绘制
	boolean isdownArrowPaint;
	
	boolean bPanelDrag;

	// scrollpanl中获得焦点的控件
	GWidget focusedWidget;

	// 控件是否需要滚动
	boolean isNeedScroll;
	
	// 滚动区域控件之间的间隙大小
	int vGap;
	int hGap;
	
	//contentArea与滚动条之间的间距
	int gapCA2Bar;
}