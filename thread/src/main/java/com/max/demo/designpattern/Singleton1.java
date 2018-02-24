package com.max.demo.designpattern;

/**
 * Created by majun on 2017/12/4.
 */
public class Singleton1 {
    private Singleton1() {}  //私有构造函数
    private volatile static Singleton1 instance = null;  //单例对象
    //静态工厂方法
    public static Singleton1 getInstance() {
        if (instance == null) {      //双重检测机制
            synchronized (Singleton1.class){  //同步锁
                if (instance == null) {     //双重检测机制
                    instance = new Singleton1();
                }
            }
        }
        return instance;
    }

}
