package com.max.demo.designpattern;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by majun on 2017/12/4.
 */
public class Singleton2 {
    private static class LazyHolder {
        private static final Singleton2 INSTANCE = new Singleton2();
    }
    public Inner getInner(String name, String city) {
        return new Inner(name, city) {
            private String nameStr = name;

            public String getName() {
                System.out.println(name);
                System.out.println(city);
                return nameStr;
            }
        };
    }
    private Singleton2 (){}
    public static Singleton2 getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void get() {
        class PDestination {
            private String label;
            {
                System.out.println("init");
            }
            private PDestination(String whereTo) {
                label = whereTo;
            }

            public String readLabel() {
                return label;
            }
        }
        PDestination p = new PDestination("");
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //获得构造器
        Constructor con = Singleton2.class.getDeclaredConstructor();
        //设置为可访问
        con.setAccessible(true);
        //构造两个不同的对象
        Singleton2 singleton1 = (Singleton2)con.newInstance();
        Singleton2 singleton2 = (Singleton2)con.newInstance();
         //验证是否是不同对象
        System.out.println(singleton1.equals(singleton2));

    }

    abstract class Inner {
        Inner(String name, String city) {
            System.out.println(city);
        }

        abstract String getName();
    }
}


