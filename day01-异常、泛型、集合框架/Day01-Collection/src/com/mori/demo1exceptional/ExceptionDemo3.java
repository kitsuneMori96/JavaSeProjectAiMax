package com.mori.demo1exceptional;

public class ExceptionDemo3 {
    static void main(String[] args) {
        System.out.println("程序开始");
        try {
            saveAge(100);
            System.out.println("运行成功");
        } catch (moriAgeException e) {
            System.out.println("运行失败");
            throw new RuntimeException(e);
        }
        System.out.println("程序结束");
    }

    private static void saveAge(int i) throws moriAgeException {
        if(i<0||i>200){
            throw new moriAgeException("年龄错误");
        }
        else{
            System.out.println(i+"年龄保存");
        }
    }
}
