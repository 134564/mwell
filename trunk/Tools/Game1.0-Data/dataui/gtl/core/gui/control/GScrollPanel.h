#include "./GContainer.h"
#include "./GScrollBar.h"

STRUCT GScrollPanel extends GContainer
{
	
	//			�ֶ�							��������GWidget�Ļ��������ӣ�
	/**
	 * SCrollPanel�е���������.
	 */
	GContainer contentArea;					// 1

	/**
	 * SCrollPanel�еĴ�ֱ������.
	 */
	GScrollBar vScrollBar;					// 2
	/**
	 * SCrollPanel�е�ˮƽ������.
	 */
	GScrollBar hScrollBar;					// 3
	
	/**
	 * SCrollPanel�е��ĸ���ͷ
	 */
	GButton upArrow;						// 4
	GButton downArrow;						// 5
	GButton leftArrow;						// 6
	GButton rightArrow;						// 7

	// �������.
	int func_touchPressed;					// 8
	int func_touchReleased;					// 9
	int func_touchDragged;					// 10
	int func_keyPressed;					// 11
	int func_keyReleased;					// 12
	
	// ˮƽ�������Ƿ����
	boolean isHScrollBarPaint;				// 
	// ��ֱ�������Ƿ����
	boolean isVScrollBarPaint;				//
	
	// ˮƽ���򵼺����Ƿ����
	boolean isHArrowPaint;
	// ��ֱ���򵼺����Ƿ����
	boolean isVArrowPaint;
	
	// ���������пؼ���ƫ����
	int scrollOffsetX;
	int scrollOffsetY;
	//�ϵ������Ƿ����
	boolean isupArrowPaint;
	//�µ������Ƿ����
	boolean isdownArrowPaint;
	
	boolean bPanelDrag;

	// scrollpanl�л�ý���Ŀؼ�
	GWidget focusedWidget;

	// �ؼ��Ƿ���Ҫ����
	boolean isNeedScroll;
	
	// ��������ؼ�֮��ļ�϶��С
	int vGap;
	int hGap;
	
	//contentArea�������֮��ļ��
	int gapCA2Bar;
}