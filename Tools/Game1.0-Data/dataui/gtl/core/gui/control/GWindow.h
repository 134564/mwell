#include "./GContainer.h"
#include "./GButton.h"
STRUCT GWindow extends GContainer
{
	// �������.
	/*int func_keyPressed;
	int func_keyReleased;
	int func_touchClicked;
	int func_touchPressed;
	int func_touchReleased;
	int func_touchMoved;
	int fucn_touchDragged; */
	
	// �ؼ����.
	/**
	 * ������.
	 */
	GLabel titleBar;
	/**
	 * ״̬��.
	 */
	GContainer statusBar;
	/**
	 * ��������.
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