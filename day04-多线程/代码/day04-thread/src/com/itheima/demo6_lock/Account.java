package com.itheima.demo6_lock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String cardId; // 卡号
    private double money; // 余额
    private final Lock lk = new ReentrantLock(); // 保护锁对象

    // 小明和小红都到这里来了取钱
    public void drawMoney(double money) {
        // Lock锁实现线程安全
    }
}
