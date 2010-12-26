#include "./GContainer.h"

// 树结点数据结构
STRUCT GTree
{
	// 根结点
	GWidget root;
	
	// 是否需要展开子控件（默认为不展开，即隐藏）
	boolean isNeedExpand;
	
	// 子结点(存GTree)
	Vector<GTree> children;
}

STRUCT GTreeView extends GContainer
{
	// 第一层结点(存GTree)
	Vector trees;
	
	// 每一级缩进的数量
	int indentation;
	
	// 跟结点个数
	int count;
}