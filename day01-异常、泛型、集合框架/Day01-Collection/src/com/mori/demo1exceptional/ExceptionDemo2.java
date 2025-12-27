package com.mori.demo1exceptional;

public class ExceptionDemo2 {
    //
    static void main(String[] args) {
        double a = 10, b = 2;
        try{
            System.out.println(div(a,b));
            System.out.println("程序正常退出");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("程序异常退出");
        }

    }

    private static double div(double a, double b) throws Exception {
        if(b == 0){
            throw new Exception("除数不能为0");
        }
        return a / b;
    }
}
