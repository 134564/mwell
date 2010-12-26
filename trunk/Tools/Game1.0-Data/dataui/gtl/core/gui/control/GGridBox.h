#include "./GContainer.h"
#include "./GScrollPanel.h"

STRUCT GGridBox extends GContainer
{
	// grids的个数
	int gridCount;
	
	// grids的列数
	int cols;
	
	// grids的行数
	int rows;
	
	// grid的宽
	int gridW;
	
	// grid的高
	int gridH;
	
	// Scrollpanel
	GScrollPanel scrollPanel;
}