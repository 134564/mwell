//常用颜色定义
#define CL_BLACK                                                                0x000000
#define CL_DARKGRAY                                                             0x808080
#define CL_GRAY                                                                 0xC0C0C0
#define CL_WHITE                                                                0xFFFFFF
#define CL_RED                                                                  0xFF0000
#define CL_YELLOW                                                               0xFFFF00
#define CL_GREEN                                                                0x00FF00
#define CL_LIGHTBLUE                                                            0x00FFFF
#define CL_BLUE                                                                 0x6fBBF9
#define CL_PURPLE                                                               0xFF00FF
#define CL_LIGHTYELLOW                                                          0xFFFF80
#define CL_LIGHTGREEN                                                           0x00FF80
#define CL_WHITEBLUE                                                            0x80FFFF
#define CL_DARKBLUE                                                             0x8080FF
#define CL_DARKRED                                                              0xFF0080
#define CL_BROWN                                                                0xFF8040
#define CL_PEPC                                                                 0x3D2000 //纸上的选中颜色
#define CL_BROWN2                                                               0x993300
#define CL_PAGENOTE                                                             0xCCFFFF //#键翻页的颜色

#define TEXT_BLACK                                                              "<c000000>"
#define TEXT_DARKGRAY                                                           "<c808080>"
#define TEXT_GRAY                                                               "<cC0C0C0>"
#define TEXT_WHITE                                                              "<cFFFFFF>"
#define TEXT_RED                                                                "<cFF0000>"
#define TEXT_YELLOW                                                             "<cFFFF00>"
#define TEXT_GREEN                                                              "<c00FF00>"
#define TEXT_LIGHTBLUE                                                          "<c00FFFF>"
#define TEXT_BLUE                                                               "<c6fBBF9>"
#define TEXT_PURPLE                                                             "<cFF00FF>"
#define TEXT_LIGHTYELLOW                                                        "<cFFFF80>"
#define TEXT_LIGHTGREEN                                                         "<c00FF80>"
#define TEXT_WHITEBLUE                                                          "<c80FFFF>"
#define TEXT_DARKBLUE                                                           "<c8080FF>"
#define TEXT_DARKRED                                                            "<cFF0080>"
#define TEXT_BROWN                                                              "<cFF8040>"
#define TEXT_CL_END                                                             "</c>"
    
//键值定义
#define UP_PRESSED                                                              0
#define DOWN_PRESSED                                                            1
#define LEFT_PRESSED                                                            2
#define RIGHT_PRESSED                                                           3
#define FIRE_PRESSED                                                            4
#define GAME_A_PRESSED                                                          5
#define GAME_B_PRESSED                                                          6
#define GAME_C_PRESSED                                                          7
#define GAME_D_PRESSED                                                          8

#define SOFT_FIRST_PRESSED                                                      9
#define SOFT_LAST_PRESSED                                                       10
    
#define KEY_NUM0_PRESSED                                                        11
#define KEY_NUM1_PRESSED                                                        12
#define KEY_NUM2_PRESSED                                                        13
#define KEY_NUM3_PRESSED                                                        14
#define KEY_NUM4_PRESSED                                                        15
#define KEY_NUM5_PRESSED                                                        16
#define KEY_NUM6_PRESSED                                                        17
#define KEY_NUM7_PRESSED                                                        18
#define KEY_NUM8_PRESSED                                                        19
#define KEY_NUM9_PRESSED                                                        20
#define KEY_POUND_PRESSED                                                       21
#define KEY_STAR_PRESSED                                                        22
//todo donglinming
#define KEY_PHONE_YES                                                           23 
#define KEY_PHONE_BACKSPACE                                                     24     
//qingli
#define KEY_RIGHT_FUNC															25
#define KEY_LEFT_SHIFT      													26
#define BUTTON_MENU_PRESSED                                                     9
#define BUTTON_OK_PRESSED                                                       9
#define BUTTON_BACK_PRESSED                                                     10
#define TIMEOUT_RET																3 //messagebox 的返回类型
#define ANYKEY_RET																4 //messagebox 的返回类型

// 布尔常量
#define FALSE                                                                   0
#define TRUE                                                                    1

// 指针常量
#define NULL                                                                    0

// 输入限制常量，和MIDP2规范中一致
#define INPUT_ANY                                                               0
#define INPUT_EMAILADDR                                                         1
#define INPUT_NUMERIC                                                           2
#define INPUT_PHONENUMBER                                                       3
#define INPUT_URL                                                               4
#define INPUT_DECIMAL                                                           5
#define INPUT_PASSWORD                                                          65536

// 选择框类型常量，和MIDP2规范中一致
#define CHOICE_EXCLUSIVE                                                        1
#define CHOICE_MULTIPLE                                                         2

// Form命令类型常量，和MIDP2规范中一致
#define COMMAND_SCREEN                                                          1
#define COMMAND_BACK                                                            2
#define COMMAND_CANCEL                                                          3
#define COMMAND_OK                                                              4
#define COMMAND_HELP                                                            5
#define COMMAND_STOP                                                            6
#define COMMAND_EXIT                                                            7
#define COMMAND_ITEM                                                            8  

// 图片翻转方式
#define TRANS_NONE                                                              0
#define TRANS_MIRROR_ROT180                                                     1
#define TRANS_MIRROR                                                            2
#define TRANS_ROT180                                                            3
#define TRANS_MIRROR_ROT270                                                     4
#define TRANS_ROT90                                                             5
#define TRANS_ROT270                                                            6
#define TRANS_MIRROR_ROT90                                                      7

// 绘图对齐方式，和MIDP2规范中一致
#define G_BOTTOM                                                                32
#define G_HCENTER                                                               1
#define G_LEFT                                                                  4
#define G_RIGHT                                                                 8
#define G_TOP                                                                   16
#define G_VCENTER                                                               2
#define G_TOPLEFT                                                               20
#define G_CENTER                                                                3
#define G_TOPCENTER                                                             17
#define G_TOPRIGHT                                                              24
#define G_BOTTOMLEFT                                                            36
#define G_BOTTOMRIGHT                                                           40
#define G_BOTTOMCENTER                                                          33
#define G_LEFTCENTER                                                            6
#define G_RIGHTCENTER                                                           10

////////////////////////////////////////////////
// 脚本通用函数的类型.
////////////////////////////////////////////////
#define UIVM_TYPE_INIT           	 0
#define UIVM_TYPE_CYCLE          	 1
#define UIVM_TYPE_PROCESSPACKET  	 2
#define UIVM_TYPE_CYCLEUI        	 3
#define UIVM_TYPE_PAINT 		     4
#define UIVM_TYPE_DESTROY        	 5

////////////////////////////////////////////////
// Macros.
////////////////////////////////////////////////
#define OBJECT_FREE(ptr) free(ptr); ptr = NULL
#define OBJECT_SET(dst, src) free(dst); dst = Realize(src)