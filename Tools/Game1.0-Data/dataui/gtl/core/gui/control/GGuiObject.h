/**
 * All Rights Reserved (C) PearlInPalm Co., Ltd
 * 
 * @file 	GGuiObject.h
 * @description 声明Struct GGuiObject.
 * @author David.Wan
 * @date   2010-06
 */
 
#include "../common/GuiCommonDef.h"
#include "../../common/commondef.h"

/**
 * GGuiObject是gui系统中的基类.
 */
STRUCT GGuiObject
{
	/**
	 * 保存自身对象经过Realize后的虚拟机地址,
	 * 用于为从C++/Java端获取GTL脚本中的对象
	 * 提供快捷方式.
	 */
	//Object self;
	/**
	 * 该对象在客户端唯一的id, 用户辨别该对象.
	 */
	int clientId;
}
