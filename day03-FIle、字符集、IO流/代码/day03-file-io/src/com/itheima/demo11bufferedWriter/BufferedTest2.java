package com.itheima.demo11bufferedWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BufferedTest2 {
    static void main(String[] args) {
        // 目标：完成出师表的案例。
        try (
                BufferedReader br = new BufferedReader(new FileReader("day03-FIle、字符集、IO流/代码/day03-file-io/src/csb.txt"))
                ){
            List<String> list = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null){
                list.add(line);
            }
            Collections.sort(list, Comparator.comparingInt(s -> s.charAt(0)-'0'));
//            list.sort((s1, s2) -> {
//                int i1 = s1.charAt(0)-'0';
//                int i2 = s2.charAt(0)-'0';
//                return i1-i2;
//            });
            BufferedWriter bw = new BufferedWriter(new FileWriter("day03-FIle、字符集、IO流/代码/day03-file-io/src/csb_sorted.txt"));
            for (String s : list){
                bw.write(s);
                bw.newLine();
                bw.flush();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
