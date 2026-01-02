package com.itheima.demo1create;

public class ThreadDemo2 {
    public static void main(String[] args) {
        // 目标：掌握多线程的创建方式二：实现Runnable接口来创建。
        // 3、创建线程任务类的对象代表一个线程任务。
        // 4、把线程任务对象交给一个线程对象来处理
        // 5、启动线程

        // 主线程循环
    }
}

// 1、定义一个线程任务类实现Runnable接口
class MyRunnable implements Runnable {
    // 2、重写run方法，设置线程任务
    @Override
    public void run() {
        // 子线程循环
    }
}