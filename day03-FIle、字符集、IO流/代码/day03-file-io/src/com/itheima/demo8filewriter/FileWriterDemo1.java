package com.itheima.demo8filewriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileWriterDemo1 {
    public static void main(String[] args) throws IOException {
        // 目标：搞清楚文件字符输出流的使用：写字符出去的流。
        // 1. 创建一个字符输出流对象，指定写出的目的地。
        try(
                Writer writer = new FileWriter("day03-FIle、字符集、IO流/代码/day03-file-io/src/mori1.txt");
                ) {
            writer.write("你好，世界！");
            writer.write(46);
            writer.write("你好，世界！",0,2);
            //刷新缓冲区
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        // 2. 写一个字符出去：  public void write(int c)
        // 3、写一个字符串出去：  public void write(String str)
        // 4、写字符串的一部分出去：  public void write(String str, int off, int len)
        // 5、写一个字符数组出去：  public void write(char[] cbuf)
        // 6、写字符数组的一部分出去：  public void write(char[] cbuf, int off, int len)
        // 刷新缓冲区，将缓冲区中的数据全部写出去。
        // 刷新后，流可以继续使用。
        // 关闭资源，关闭包含了刷新！关闭后流不能继续使用！
    }
}
