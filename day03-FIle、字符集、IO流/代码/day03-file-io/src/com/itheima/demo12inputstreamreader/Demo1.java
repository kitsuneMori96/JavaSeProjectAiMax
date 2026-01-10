package com.itheima.demo12inputstreamreader;

import java.io.*;

public class Demo1 {
    public static void main(String[] args) {
        // 目标：演示一个问题：不同编码读取乱码的问题、
        // 代码：UTF-8   文件 UTF-8  读取不乱码
        // 代码：UTF-8   文件 GBK  读取乱码
        // 字符转换流
        try (
                InputStream reader = new FileInputStream("day03-FIle、字符集、IO流/代码/day03-file-io/src/cs.txt");
                InputStreamReader inputStreamReader = new InputStreamReader(reader, "GBK")
        ) {

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
