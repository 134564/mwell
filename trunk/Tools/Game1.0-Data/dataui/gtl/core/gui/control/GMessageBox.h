#include "./GVMCanvasUnit.h"
#include "./GLabel.h"
#include "./GButton.h"
#include "./GTextArea.h"

/**
 * GMessageBox ��ʾ��Ϣ.
 */
STRUCT GMessageBox extends GVMCanvasUnit
{
	GLabel caption;		// ����
	GTextArea text;		// �ı���Ϣ
	GButton OK;			// ȷ�ϰ�ť
	GButton Cancle;		// ȡ����ť
}