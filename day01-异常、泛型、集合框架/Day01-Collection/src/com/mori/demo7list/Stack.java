package com.mori.demo7list;

import java.util.LinkedList;

public class Stack<E> {
    private final LinkedList<E> list;

    public Stack() {
        list = new LinkedList<>();
    }

    public Stack(E e){
        list = new LinkedList<>();
        list.add(e);
    }

    public void push(E e){
        list.add(e);
    }

    public E pop(){
        if(list.isEmpty()){
            throw new RuntimeException("Stack is empty!");
        }
        return list.removeLast();
    }

    public E getFirst(){
        if(list.isEmpty()) throw new RuntimeException("Stack is empty!");
        return list.getFirst();
    }

    public E getLast(){
        if(list.isEmpty()){
            throw new RuntimeException("Stack is empty!");
        }
        return list.getLast();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public int size(){
        return list.size();
    }
}
