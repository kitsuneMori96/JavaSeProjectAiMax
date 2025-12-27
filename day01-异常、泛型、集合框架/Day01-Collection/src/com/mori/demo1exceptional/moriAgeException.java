package com.mori.demo1exceptional;

public class moriAgeException extends Exception{
    public moriAgeException(){

    }

    public moriAgeException(String message){
        System.out.println("年龄错误");
    }

}
