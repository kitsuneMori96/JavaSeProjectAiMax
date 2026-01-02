package com.itheima.demo11bufferedWriter;

import java.io.*;

public class TimeTest3 {
    private static final String SRC_FILE = "E:\\磊哥面授\\AI+Java基础加强课程\\day02-集合架构\\视频\\16、Stream流的终结方法.avi";
    private static final String DEST_FILE = "D:\\";
    public static void main(String[] args) {
        // 目标：缓冲流，低级流的性能分析。
        //使用低级的字节流按照一个一个字节的形式复制文件: 非常的慢，简直让人无法忍受，直接淘汰，禁止使用！！
        //使用低级的字节流按照字节数组的形式复制文件: 是比较慢的，还可以接受。
        //使用高级的缓冲字节流按照一个一个字节的形式复制文件：虽然是高级管道，但一个一个字节的复制还是太慢了，无法忍受，直接淘汰！
        //使用高级的缓冲字节流按照字节数组的形式复制文件: 非常快！推荐使用！
    }

    //使用高级的缓冲字节流按照字节数组的形式复制文件。
    private static void copyFile4() {
        
    }

    //使用高级的缓冲字节流按照一个一个字节的形式复制文件。
    private static void copyFile3() {
        
    }

    //使用低级的字节流按照字节数组的形式复制文件。
    private static void copyFile2() {
        
    }

    //使用低级的字节流按照一个一个字节的形式复制文件。
    public static void copyFile1() {
        
    }
}
