#include "./GWidget.h"

STRUCT GProgressBar extends GWidget 
{
	int max;		//51
	int min;		//52
	int stepvalue;	//53
	int currentvalue;	//54
	int tipPaint;		//55
	int nIconWidth;//进度条外框的宽度
	int nIconHeight;//进度条外框的高度
	int nWidth;//progressbar的总宽度
	int nHeight;//progressbar的总高度
}
