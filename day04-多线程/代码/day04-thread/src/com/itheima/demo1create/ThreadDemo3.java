package com.itheima.demo1create;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadDemo3 {
    public static void main(String[] args) {
        // 目标：掌握多线程的创建方式三：实现Callable接口，方式三的优势：可以获取线程执行完毕后的结果的。
        // 3、创建一个Callable接口的实现类对象。
        // 4、把Callable对象封装成一个真正的线程任务对象FutureTask对象。
        /**
         * 未来任务对象的作用？
         *    a、本质是一个Runnable线程任务对象，可以交给Thread线程对象处理。
         *    b、可以获取线程执行完毕后的结果。
         */
        // 5、把FutureTask对象作为参数传递给Thread线程对象。
        // 6、启动线程。

        // 创建第二个线程任务

        // 获取线程执行完毕后返回的结果
        // 获取第一个线程结果

        // 获取第二个线程结果
    }
}

// 1、定义一个实现类实现Callable接口
class MyCallable implements Callable<String> {
    private int n;
    public MyCallable(int n) {
        this.n = n;
    }
    // 2、实现call方法，定义线程执行体
    public String call() throws Exception {
        // 计算逻辑
        return "子线程计算结果";
    }
}