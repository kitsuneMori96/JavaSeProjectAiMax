package com.itheima.demo1junit;
/**
 * 字符串工具类
 */
public class StringUtil {
    public static void printNumber(String name){
        if(name == null || name.equals("")) System.out.println("名字为空");
        else System.out.println("名字长度是：" + name.length());
    }

    /**
     * 求字符串的最大索引
     */
    public static int getMaxIndex(String data){
        if(data == null || data.equals("")) return -1;
        return data.length()-1;
    }
}














