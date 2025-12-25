package com.mori.demo4genericity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class GenericityDemo5 {
    static void main(String[] args) {
        Collection<String> list = new ArrayList<>();
        list.add("张三");
        list.add("王五");
        list.add("李四");
        list.add("赵六");
        list.add("李四");
        Iterator<String> it = list.iterator();
        for(; it.hasNext();){
            System.out.println(it.next());
        }
        System.out.println("--------------------");
        for(String name:list){
            System.out.println(name);
        }
        System.out.println("--------------------");
        for(int i=0;i<list.size();i++){
            System.out.println(((ArrayList<String>) list).get(i));
        }
    }
}
