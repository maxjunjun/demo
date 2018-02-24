package com.max.demo.jvm;

import java.lang.reflect.Field;

/**
 * Created by majun on 2017/12/1.
 */
public class Test {
    static {
        System.out.println("静态初始化块执行了！");
    }

    /*public static void main(String[] args) {
        Son son = new Son();
        System.out.println();
        new Son();

        System.out.println();
        Father father = new Father();
        father.getName();
        son.getName();
    }*/

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class cache = Integer.class.getDeclaredClasses()[0];
        Field myCache = cache.getDeclaredField("cache");
        Integer[] newCache = (Integer[]) myCache.get(cache);
        newCache[132] = newCache[133];

        int a=2;
        int b=a + a;
        System.out.printf("%d + %d = %d", a, a, b);
    }
}
