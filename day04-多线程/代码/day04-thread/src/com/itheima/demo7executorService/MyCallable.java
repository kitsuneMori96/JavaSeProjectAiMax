package com.itheima.demo7executorService;

import java.util.concurrent.Callable;

// 1、定义一个实现类实现Callable接口
public class MyCallable implements Callable<String> {
    private int n;
    public MyCallable(int n) {
        this.n = n;
    }
    // 2、实现call方法，定义线程执行体
    public String call() throws Exception {
        // 计算逻辑
        return "计算结果";
    }
}