#include "./GVMCanvasUnit.h"
#include "./GLabel.h"
#include "./GButton.h"
#include "./GTextArea.h"

/**
 * GMessageBox 提示信息.
 */
STRUCT GMessageBox extends GVMCanvasUnit
{
	GLabel caption;		// 标题
	GTextArea text;		// 文本信息
	GButton OK;			// 确认按钮
	GButton Cancle;		// 取消按钮
}