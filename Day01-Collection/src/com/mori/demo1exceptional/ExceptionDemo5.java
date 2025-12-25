package com.mori.demo1exceptional;


import java.util.Scanner;

public class ExceptionDemo5 {
    static void main(String[] args) {
        System.out.println("程序开始");
        while (true) {
            try {
                double price = userInputPrice();
                System.out.println("价格是：" + price);
                System.out.println("程序结束");
                break;
            } catch (Exception e) {
                System.out.println("价格输入异常，请重新输入！");
            }

        }

    }

    public static double userInputPrice(){
        Scanner sc = new Scanner(System.in);
        //判断接受数据类型
        System.out.println("请您输入商品定价：");
        if(!sc.hasNextDouble())throw new RuntimeException("价格异常");
        double price = sc.nextDouble();
        return price;
    }
}
