package com.max.demo.designpattern;

/**
 * Created by max on 2017/12/4.
 */
public enum  SingletonEnum {
    INSTANCE;
    public void otherMethods(){
        System.out.println("Something");
    }

    public static void main(String[] args) {
        SingletonEnum.INSTANCE.otherMethods();
    }
}
