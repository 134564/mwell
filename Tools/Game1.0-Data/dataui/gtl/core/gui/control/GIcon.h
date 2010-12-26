#include "./GWidget.h"

STRUCT GIcon extends GWidget
{
	// 按钮点击事件的处理方法, 对GWidget::func_handleKey和GWidget::func_handleTouch进行了进一步的封装.
	//int icon_clicked;		// 函数原型:  icon_clicked(GIcon this)
	int nPipWidth;			// 51
	int nPipHeight;			//52
	/*int icon_released;		
	int oldMouseX;
	int oldMouseY;
	int oldIconX;
	int oldIconY;
	int nOffSetX;
	int nOffSetY;
//	boolean bFirstDrag;		//判断是否是touchpress后的第一次drag
	boolean bIsEmpty;		//判断物品是否被拖拽走*/
	ImageSet image;			// 53
	int index;				// 54
}
