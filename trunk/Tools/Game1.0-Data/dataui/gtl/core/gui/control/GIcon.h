#include "./GWidget.h"

STRUCT GIcon extends GWidget
{
	// ��ť����¼��Ĵ�����, ��GWidget::func_handleKey��GWidget::func_handleTouch�����˽�һ���ķ�װ.
	//int icon_clicked;		// ����ԭ��:  icon_clicked(GIcon this)
	int nPipWidth;			// 51
	int nPipHeight;			//52
	/*int icon_released;		
	int oldMouseX;
	int oldMouseY;
	int oldIconX;
	int oldIconY;
	int nOffSetX;
	int nOffSetY;
//	boolean bFirstDrag;		//�ж��Ƿ���touchpress��ĵ�һ��drag
	boolean bIsEmpty;		//�ж���Ʒ�Ƿ���ק��*/
	ImageSet image;			// 53
	int index;				// 54
}
