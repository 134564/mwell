#include "./GButton.h"
#include "./GWidget.h"

STRUCT GRadioButton extends GButton 
{
	//是否被选中.
	int isSelected;	
	//radioBtn所属的组.
	GButtonGroup btnGroup;
}