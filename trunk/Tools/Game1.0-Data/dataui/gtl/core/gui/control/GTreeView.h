#include "./GContainer.h"

// ��������ݽṹ
STRUCT GTree
{
	// �����
	GWidget root;
	
	// �Ƿ���Ҫչ���ӿؼ���Ĭ��Ϊ��չ���������أ�
	boolean isNeedExpand;
	
	// �ӽ��(��GTree)
	Vector<GTree> children;
}

STRUCT GTreeView extends GContainer
{
	// ��һ����(��GTree)
	Vector trees;
	
	// ÿһ������������
	int indentation;
	
	// ��������
	int count;
}