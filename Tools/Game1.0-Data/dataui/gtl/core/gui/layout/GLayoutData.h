/**
 * All Rights Reserved (C) PearlInPalm Co., Ltd
 * 
 * @file 	GLayoutData.gtl
 * @description ����GLayoutData�ṹ��.
 * @author David.Wan
 * @date   2010-09
 */
 
#include "../../common/CommonDef.h"
#include "../common/GuiCommonDef.h"
#include "../control/GWidget.h"

 /**
  * GLayoutData�����˻�ȡ�����ؼ���layout�����������ӿؼ�
  * ������Ϣ.
  */
 STRUCT GLayoutData 
 {
 	/**
 	 * ʹ�õ�layout��ʽ, �����µ�layout��ʽ��
 	 * LAYOUT_TYPE_H, LAYOUT_TYPE_V, LAYOUT_TYPE_BORDER,
 	 * LAYOUT_TYPE_GRID, LAYOUT_TYPE_GRIDBAG
 	 */
 	int layoutType;
 	/**
 	 * ���ֺ�ķ��صĿؼ���Ŀ.
 	 */
 	int layoutWidgetsCount;
 }
 
 /**
  * ���Բ�����Ϣ.
  */ 
 STRUCT GLineLayoutData extends GLayoutData
 {
 	/**
 	 * lineLayoutedWidgets���ղ��ֿؼ����Ⱥ�˳�򱣴���������ؿؼ�.
 	 */
 	GWidget[] lineLayoutedWidgets;
 }
 
 /**
  * borderLayout������Ϣ.
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
 * GridLayout������Ϣ.
 */ 
STRUCT GGridLayoutData extends GLayoutData
{
	/**
	 * ����.
	 */
	int rows;
	/**
	 * ����.
	 */
	int cols;
	/**
 	 * gridLayoutedWidgets���մӵ�һ�е�һ�п�ʼ����
 	 * ������β�Զ�ת����һ�е�˳�򱣴����б����ֵ�
 	 * �ؼ�, ��ȡ��Щ�ؼ���ʱ��, ����������rows��
 	 * cols�����ж�ȡ.
 	 * @note:
 	 *  1. gridLayoutedWidgets�б�����rows*cols��
 	 *  Ԫ��, ʵ�����Ŀؼ���Ŀ����û����ô�࣬����
 	 *  ���п�����Щ�����Ԫ��ΪNULL;
 	 *  2. ����gridBagLayout���ܻ���������ж��Ԫ�ر������ͬ
 	 *  һ���ؼ���ַ, ���������ʾ�ÿؼ������(��).
 	 */
 	GWidget[] gridLayoutedWidgets;
}
