#include "./GContainer.h"
#include "./GIcon.h"
#include "./GButton.h"

STRUCT GSlider extends GContainer
{		
	int maxValue;
	int minValue;
	int currentValue;
		
	int valueStep;                   //һ���ƶ��ľ���
}