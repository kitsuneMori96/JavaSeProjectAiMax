package com.mori.demo4genericity;

import java.util.ArrayList;
import java.util.Iterator;

public class GenericityDemo6 {
    static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        list.add("宁夏枸杞");
        list.add("黑枸杞");
        list.add("云南白药");
        list.add("四川白药");
        list.add("云南枸杞");
        list.add("四川枸杞");
        list.add("南康白药");
        deleteByName(list, "白药", list2);
        System.out.println(list);
    }

    private static void deleteByName(ArrayList<String> list, String 名字, ArrayList<Integer> list2) {
//        int i=0,j=0;
//        for(String name : list){
//            if(name.contains(枸杞)){
//                list2.add(i-j);
//                j++;
//            }
//            i++;
//        }
//        for(int index : list2){
//            list.remove(index);
//        }

//        Iterator<String> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            String name = iterator.next();
//            if (name.contains(名字)) {
//                iterator.remove();
//            }
//        }

//        for (int i = 0; i < list.size(); i++) {
//            String name = list.get(i);
//            if (name.contains(名字)) {
//                list.remove(i);
//                i--;
//            }
//        }

//        for(int i= list.size()-1; i>=0; i--){
//            String name = list.get(i);
//            if(name.contains(名字)){
//                list.remove(i);
//            }
//        }

//
        list.forEach(System.out::println);
    }
}
