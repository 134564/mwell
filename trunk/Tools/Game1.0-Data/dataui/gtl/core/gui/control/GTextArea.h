#include "./GWidget.h"
STRUCT GTextArea extends GWidget
{
	/**
	 * 是否使用3D文字.
	 */
	boolean is3D;
	
	/**
	 * 是否为一个不折行的多媒体文本，支持格式为<cxxxxx>xxx</c>格式的字体颜色，
	 * 也支持颜色字符串。
	 */
	boolean isMixedStr;

	// 行间距
	int lineSpace;
	
	// 总行数
	int totalLines;
	
	// 总页数
	int totalPages;
	
	// 一页能显示的行数
	int showLines;

	int currentLine;
	int currentPage;
	/**
	 * 是否为一个不折行的多媒体文本，支持格式为<cxxxxx>xxx</c>格式的字体颜色，
	 * 也支持颜色字符串。
	 */
	//boolean isMixedStr;
	// GTextArea的最大高度
	int maxH;
}