#include "./GWidget.h"
STRUCT GTextArea extends GWidget
{
	/**
	 * �Ƿ�ʹ��3D����.
	 */
	boolean is3D;
	
	/**
	 * �Ƿ�Ϊһ�������еĶ�ý���ı���֧�ָ�ʽΪ<cxxxxx>xxx</c>��ʽ��������ɫ��
	 * Ҳ֧����ɫ�ַ�����
	 */
	boolean isMixedStr;

	// �м��
	int lineSpace;
	
	// ������
	int totalLines;
	
	// ��ҳ��
	int totalPages;
	
	// һҳ����ʾ������
	int showLines;

	int currentLine;
	int currentPage;
	/**
	 * �Ƿ�Ϊһ�������еĶ�ý���ı���֧�ָ�ʽΪ<cxxxxx>xxx</c>��ʽ��������ɫ��
	 * Ҳ֧����ɫ�ַ�����
	 */
	//boolean isMixedStr;
	// GTextArea�����߶�
	int maxH;
}