package com.max.demo.jvm;

/**
 * Created by majun on 2017/12/1.
 */
public class Test {
    static {
        System.out.println("静态初始化块执行了！");
    }

    public static void main(String[] args) {
        Son son = new Son();
        System.out.println();
        new Son();

        System.out.println();
        Father father = new Father();
        father.getName();
        son.getName();
    }
}
