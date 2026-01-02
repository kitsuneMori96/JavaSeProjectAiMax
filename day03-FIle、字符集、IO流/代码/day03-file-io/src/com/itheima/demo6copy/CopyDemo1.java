package com.itheima.demo6copy;

import java.io.*;

public class CopyDemo1 {
    public static void main(String[] args) {
        // 目标：使用字节流完成文件的复制操作。
        // 源文件：E:\resource\jt.jpg
        // 目标文件：D:\jt_new.jpg （复制过去的时候必须带文件名的，无法自动生成文件名。）
        copyFile("D:\\Picture\\little_busters_265.jpg", "D:\\Picture\\test.jpg");
    }

    // 复制文件
    public static void copyFile(String srcPath, String destPath)  {
        // 1、创建一个文件字节输入流管道与源文件接通
        // 2、读取一个字节数组，写入一个字节数组  1024 + 1024 + 3
        // 3、释放资源
        try(
                FileInputStream fis = new FileInputStream(srcPath);
                FileOutputStream fos = new FileOutputStream(destPath);
                ) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer))!= -1){
                fos.write (buffer, 0, len);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
