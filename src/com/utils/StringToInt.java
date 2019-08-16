package com.utils;

/**
 * @author wuyi
 * @date 2019/8/12 14:20
 */

public class StringToInt {
    /**
     * 判断字符串是否不为空
     *
     * @param str
     * @return true：表示不为空，false：表示字符串为空
     */
    public static boolean isNotNull(String str) {
        return str != null && !"".equals(str);
    }

    /**
     * 将字符串转换为Integer
     *
     * @param str 要转换的字符串
     * @return
     */
//    , Integer defValue)
    public static Integer str2Int(String str) {
        // 1、判断字符串是否不为空
        try {
            if (isNotNull(str)) {
                return Integer.parseInt(str);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Float str2Float(String str) {
        // 1、判断字符串是否不为空
        try {
            if (isNotNull(str)) {
                return Float.parseFloat(str);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("浮点");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return -1f;

    }
}