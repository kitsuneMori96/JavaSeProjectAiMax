package com.mori;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Input {
    static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("day03-FIle、字符集、IO流/代码/day03-file-io/src/mori.txt");
        byte[] bytes = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bytes)) != -1) {
            System.out.println(new String(bytes,0,len));
        }
        byte[] bytes1 = fileInputStream.readAllBytes();

    }
}
