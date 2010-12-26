/**
 * All Rights Reserved (C) PearlInPalm Co., Ltd
 * 
 * @file 	GLayoutData.gtl
 * @description 定义GLayoutData结构体.
 * @author David.Wan
 * @date   2010-09
 */
 
#include "../../common/CommonDef.h"
#include "../common/GuiCommonDef.h"
#include "../control/GWidget.h"

 /**
  * GLayoutData定义了获取容器控件的layout操作产生的子控件
  * 布局信息.
  */
 STRUCT GLayoutData 
 {
 	/**
 	 * 使用的layout方式, 有如下的layout方式：
 	 * LAYOUT_TYPE_H, LAYOUT_TYPE_V, LAYOUT_TYPE_BORDER,
 	 * LAYOUT_TYPE_GRID, LAYOUT_TYPE_GRIDBAG
 	 */
 	int layoutType;
 	/**
 	 * 布局后的返回的控件数目.
 	 */
 	int layoutWidgetsCount;
 }
 
 /**
  * 线性布局信息.
  */ 
 STRUCT GLineLayoutData extends GLayoutData
 {
 	/**
 	 * lineLayoutedWidgets按照布局控件的先后顺序保存了所有相关控件.
 	 */
 	GWidget[] lineLayoutedWidgets;
 }
 
 /**
  * borderLayout布局信息.
  */
STRUCT GBorderLayoutData extends GLayoutData
{
	/**
	 * east control.
	 */
	GWidget east;
	
	/**
	 * north control.
	 */
	GWidget north;
	
	/**
	 * west control.
	 */
	GWidget west;
	
	/**
	 * south control.
	 */
	GWidget south;
	
	/**
	 * center control.
	 */
	GWidget center;
}

/**
 * GridLayout布局信息.
 */ 
STRUCT GGridLayoutData extends GLayoutData
{
	/**
	 * 行数.
	 */
	int rows;
	/**
	 * 列数.
	 */
	int cols;
	/**
 	 * gridLayoutedWidgets按照从第一行第一列开始逐列
 	 * 并在列尾自动转向下一行的顺序保存所有被布局的
 	 * 控件, 获取这些控件的时候, 请根据上面的rows和
 	 * cols来进行读取.
 	 * @note:
 	 *  1. gridLayoutedWidgets中保存了rows*cols个
 	 *  元素, 实际填充的控件数目可能没有这么多，所以
 	 *  其中可能有些保存的元素为NULL;
 	 *  2. 对于gridBagLayout可能会存在数组中多个元素保存的是同
 	 *  一个控件地址, 这种情况表示该控件跨多行(列).
 	 */
 	GWidget[] gridLayoutedWidgets;
}
