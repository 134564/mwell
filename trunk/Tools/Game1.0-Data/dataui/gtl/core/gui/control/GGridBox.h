#include "./GContainer.h"
#include "./GScrollPanel.h"

STRUCT GGridBox extends GContainer
{
	// grids�ĸ���
	int gridCount;
	
	// grids������
	int cols;
	
	// grids������
	int rows;
	
	// grid�Ŀ�
	int gridW;
	
	// grid�ĸ�
	int gridH;
	
	// Scrollpanel
	GScrollPanel scrollPanel;
}