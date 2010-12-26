#include "./GContainer.h"

STRUCT GListBox extends GContainer
{
	//是否支持循环滚动 
	boolean isCycled;
	
	// 选中的List的索引值
	int selectedItemIndex;
	
	// List 的item的总个数
	int itemCount;
	
	// List 的items的总高度
	int itemsH;
	
	// Listbox的朝向
	int orientation;
	
	// Scrollpanel
	GScrollPanel scrollPanel;
}