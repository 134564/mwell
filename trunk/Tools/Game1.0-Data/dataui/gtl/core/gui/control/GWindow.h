#include "./GContainer.h"
#include "./GButton.h"
STRUCT GWindow extends GContainer
{
	// 函数相关.
	/*int func_keyPressed;
	int func_keyReleased;
	int func_touchClicked;
	int func_touchPressed;
	int func_touchReleased;
	int func_touchMoved;
	int fucn_touchDragged; */
	
	// 控件相关.
	/**
	 * 标题栏.
	 */
	GLabel titleBar;
	/**
	 * 状态栏.
	 */
	GContainer statusBar;
	/**
	 * 内容区域.
	 */
	GContainer contentArea;
	/**
	 * Left GIcon.
	 */
	//GLabel leftLabel;
	GButton leftButton;
	/**
	 * Right GIcon.
	 */
	//GLabel RightLabel;
	GButton rightButton;
}