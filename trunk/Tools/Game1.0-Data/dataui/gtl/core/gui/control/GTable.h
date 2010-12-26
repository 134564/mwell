#include "./GContainer.h"

STRUCT GTable extends GContainer
{
	GLabel head;  //表头
	GListBox body;   //表体
	boolean isShowHead; //是否显示表头
	int type;         //表格模型,见上面TABLE_TYPE_XXX
	int rowCnt;       //行数
	int colCnt;       //列数
	int[] rates;      //列宽比例分子
//	int base;		  //列宽比例分母
//	int minWidth;     //最小行宽
//	int minLineHeight; //最小行高
//	int maxHeight;    //最大高度
//	int itemPageCnt;  //决定了表格最大高度maxHeight后，在装载数据过程中计算获得
//    int itemHeight;   //决定了表格最大高度maxHeight后，在装载数据过程中计算获得
//    int currPage;
//	int[] model;     //表格模型,见上面TABLE_MODEL_TYPE_XXX
}