#include "./GContainer.h"

STRUCT GTable extends GContainer
{
	GLabel head;  //��ͷ
	GListBox body;   //����
	boolean isShowHead; //�Ƿ���ʾ��ͷ
	int type;         //���ģ��,������TABLE_TYPE_XXX
	int rowCnt;       //����
	int colCnt;       //����
	int[] rates;      //�п��������
//	int base;		  //�п������ĸ
//	int minWidth;     //��С�п�
//	int minLineHeight; //��С�и�
//	int maxHeight;    //���߶�
//	int itemPageCnt;  //�����˱�����߶�maxHeight����װ�����ݹ����м�����
//    int itemHeight;   //�����˱�����߶�maxHeight����װ�����ݹ����м�����
//    int currPage;
//	int[] model;     //���ģ��,������TABLE_MODEL_TYPE_XXX
}