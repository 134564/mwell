#include "./Gicon.h"

STRUCT GGrid extends GIcon
{
	// grid������ʹ��ImageNumber����ʾ������ΪImageNumber��������Ϣ,
	// ʹ��GG_setNumberInfo��������
	ImageSet numberRes;		// ����������Դ
	int numberIndex;		// �����±�
	String number;			// ����
	
	boolean isNumberPaint;	// �����Ƿ����
	int numberPaintFunc;  // �����ֻ���ʱ��paint�ص�����
}