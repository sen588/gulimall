package com.atguigu.gulimall.commons.utils;

public class AppUtils {

    public static String arrayToStringWithSeperator(String[] arr, String sep) {
        StringBuffer buffer = new StringBuffer();
        if(arr != null && arr.length > 0)
        {
            for (String str : arr)
            {
                buffer.append(str);
                buffer.append(",");
            }
            return buffer.toString().substring(0, buffer.length() - 1);
        }
        return null;
    }
}
