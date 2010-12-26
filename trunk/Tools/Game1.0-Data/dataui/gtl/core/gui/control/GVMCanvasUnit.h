#include "./GContainer.h"

/**
 * GVMCanvasUnit是脚本UI界面中的最基本单元, 相当于一个根容器控件.
 * 一个UI脚本界面必须至少有一个GVMCanvasUnit
 */
STRUCT GVMCanvasUnit extends GContainer
{
	/**
	 * 是否模态(设置为TRUE表示事件不穿透，类似模态对话框).
	 */
	boolean isModal;
}