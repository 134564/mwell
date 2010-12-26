#include "./Gicon.h"

STRUCT GGrid extends GIcon
{
	// grid的数量使用ImageNumber来显示，下面为ImageNumber的数据信息,
	// 使用GG_setNumberInfo方法设置
	ImageSet numberRes;		// 数字所在资源
	int numberIndex;		// 数字下标
	String number;			// 数量
	
	boolean isNumberPaint;	// 数字是否绘制
	int numberPaintFunc;  // 有数字绘制时的paint回调函数
}