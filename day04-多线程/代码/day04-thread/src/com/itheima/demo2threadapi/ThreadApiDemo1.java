package com.itheima.demo2threadapi;

public class ThreadApiDemo1 {
    public static void main(String[] args) {
        // 目标：搞清楚线程的常用方法。
        // 创建第一个线程

        // 创建第二个线程

        // 获取当前线程信息
    }
}

// 1、定义一个子类继承Thread类，成为一个线程类。
class MyThread extends Thread {
    public MyThread(String name) {
        super(name); // public Thread(String name)
    }

    // 2、重写Thread类的run方法
    @Override
    public void run() {
        // 3、在run方法中编写线程的任务代码（线程要干的活儿）
    }
}