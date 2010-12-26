#include "./GButton.h"

STRUCT GCheckBox extends GButton 
{
	//是否被选中.
	boolean isSelected;	
	//checkBox所属的组.
	GButtonGroup btnGroup;
}
