package com.itheima.demo7fileReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class FileReaderDemo1 {
    public static void main(String[] args) throws IOException {
        // 目标：掌握文件字符输入流读取字符内容到程序中来。
        // 1、创建文件字符输入流与源文件接通
        Reader reader = new FileReader("day03-FIle、字符集、IO流/代码/day03-file-io/src/mori1.txt");
        // 2、定义一个字符数组，每次读多个字符
        char[] buffer = new char[3];
        // 3、每次读取多个字符，并把字符数组转换成字符串输出
        int length;
        while ((length = reader.read(buffer)) != -1){
            System.out.println(new String(buffer,0,length));
        }
        // 拓展：文件字符输入流每次读取多个字符，性能较好，而且读取中文
        // 是按照字符读取，不会出现乱码！这是一种读取中文很好的方案。

    }
}



