package com.itheima.demo2reflect;

import lombok.NoArgsConstructor;

import java.io.*;
import java.lang.reflect.Field;

public class ReflectDemo5 {
    static void main(String[] args) {
        Gal gal = new Gal();
        Class c1 = gal.getClass();
        Class c2 = Gal.class;
        Class c3;
        {
            try {
                c3 = Class.forName("com.itheima.demo2reflect.Gal");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        Field[] fields = c1.getDeclaredFields();

        {
            try (
                    OutputStream os = new FileOutputStream("day06-Java高级技术/代码/day06-junit-reflect-annotation-proxy/src/com/itheima/demo2reflect/Data.txt");
                    DataOutputStream dos = new DataOutputStream(os)
            ){
                for(Field field : fields) {
                    field.setAccessible(true);
                    dos.writeUTF(field.getName()+field.get(gal));
                    //换行
                    dos.writeUTF("\r\n");
                }
            } catch (IOException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

@NoArgsConstructor
class Gal {
    public String title;
    private String editor;
    private int year;
    {
        title = "樱花,萌放.";
        editor = "漆原雪人";
        year = 2019;
    }
}

