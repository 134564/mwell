#include "./GWidget.h"

// �������� ��Ϣ
STRUCT FlyStrInfo{
	/*
	 * ���г��� ���գ�GuiCommonDef.h�з������ֵķ��з�ʽ��
	 */
	int flyOrientation;

	/*
	 * ���������ƶ�ʱ���� ����cycleΪ��λ��һ��cycleΪ75mm���ң�
	 */
	int flyStrTick;
	
	/*
	 * ���������ƶ����� ��������Ϊ��λ��
	 */
	int flyStrDistance;
	
	// �����ַ��з�Χ
	int flyRect_x;		// ���GWidget��λ��
	int flyRect_y;		// ���GWidget��λ��
	int flyRect_w;		// ����������
	int flyRect_h;		// ��������߶�
}

STRUCT GLabel extends GWidget 
{
	
	/*
	 * �Ƿ��Ƿ������� ��Ĭ����GLabelΪ��������
	 */
	boolean isFly;
	
	FlyStrInfo _flyStrInfo;
	
	/**
	 * �Ƿ����3D����.
	 */
	boolean is3D;
	
	/**
	 * �Ƿ�Ϊһ�������еĶ�ý���ı���֧�ָ�ʽΪ<cxxxxx>xxx</c>��ʽ��������ɫ��
	 * Ҳ֧����ɫ�ַ�����
	 */
	boolean isMixedStr;
	
	// ��ֱ���뷽ʽ
	int vAlignment;
	
	// ˮƽ���뷽ʽ
	int hAlignment;
	
	// �Ƿ�����ı����ݶ�̬�����ؼ���С
	boolean adjustSize;
	
	// 3dЧ��ʱ�����������ɫ(��ͨЧ��ʱ������ɫ)
	int textInsetColor;
	
	// 3dЧ��ʱ������߿�ɫ�������ɫΪtextColor��
	/*
	* NOTE:		3dЧ������߿���ɫ��С��0x80000000,����c�汾���û��3dЧ����
	*/
	int textBorderColor;
}