#include "./GContainer.h"

STRUCT GListBox extends GContainer
{
	//�Ƿ�֧��ѭ������ 
	boolean isCycled;
	
	// ѡ�е�List������ֵ
	int selectedItemIndex;
	
	// List ��item���ܸ���
	int itemCount;
	
	// List ��items���ܸ߶�
	int itemsH;
	
	// Listbox�ĳ���
	int orientation;
	
	// Scrollpanel
	GScrollPanel scrollPanel;
}