#include "./GButton.h"
#include "./GWidget.h"

STRUCT GRadioButton extends GButton 
{
	//�Ƿ�ѡ��.
	int isSelected;	
	//radioBtn��������.
	GButtonGroup btnGroup;
}