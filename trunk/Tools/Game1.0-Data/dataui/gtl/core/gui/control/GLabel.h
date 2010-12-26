#include "./GWidget.h"

// 飞行文字 信息
STRUCT FlyStrInfo{
	/*
	 * 飞行朝向 参照（GuiCommonDef.h中飞行文字的飞行方式）
	 */
	int flyOrientation;

	/*
	 * 飞行文字移动时间间隔 （以cycle为单位，一次cycle为75mm左右）
	 */
	int flyStrTick;
	
	/*
	 * 飞行文字移动距离 （以像素为单位）
	 */
	int flyStrDistance;
	
	// 飞行字飞行范围
	int flyRect_x;		// 相对GWidget的位置
	int flyRect_y;		// 相对GWidget的位置
	int flyRect_w;		// 飞行区域宽度
	int flyRect_h;		// 飞行区域高度
}

STRUCT GLabel extends GWidget 
{
	
	/*
	 * 是否是飞行文字 （默认以GLabel为飞行区域）
	 */
	boolean isFly;
	
	FlyStrInfo _flyStrInfo;
	
	/**
	 * 是否绘制3D文字.
	 */
	boolean is3D;
	
	/**
	 * 是否为一个不折行的多媒体文本，支持格式为<cxxxxx>xxx</c>格式的字体颜色，
	 * 也支持颜色字符串。
	 */
	boolean isMixedStr;
	
	// 垂直对齐方式
	int vAlignment;
	
	// 水平对齐方式
	int hAlignment;
	
	// 是否根据文本内容动态调整控件大小
	boolean adjustSize;
	
	// 3d效果时的字体填充颜色(普通效果时字体颜色)
	int textInsetColor;
	
	// 3d效果时的字体边框色（填充颜色为textColor）
	/*
	* NOTE:		3d效果字体边框颜色需小于0x80000000,否则c版本里就没有3d效果了
	*/
	int textBorderColor;
}