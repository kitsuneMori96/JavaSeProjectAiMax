package com.itheima.demo4_synchronized_code;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String cardId; // 卡号
    private double money; // 余额

    // 小明和小红都到这里来了取钱
    public void drawMoney(double money) {
        // 同步代码块实现线程安全
    }
}
