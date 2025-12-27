package com.mori.demo7list;

public class Test {
    static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.pop()); // 4
        System.out.println(stack.getLast()); // 3
        System.out.println(stack.getFirst());
    }
}
