package com.itheima.demo8test;

import java.util.List;

// 线程类
public class PeopleGetRedPacket extends Thread{
    private List<Integer> redPacket;
    public PeopleGetRedPacket(List<Integer> redPacket, String name) {
        super(name);
        this.redPacket = redPacket;
    }

    @Override
    public void run() {
        // 抢红包逻辑
    }
}
