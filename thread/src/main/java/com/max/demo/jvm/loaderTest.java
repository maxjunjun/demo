package com.max.demo.jvm;

/**
 * Created by Max on 2017/12/1.
 */
public class loaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println(loader);
        //使用ClassLoader.loadClass()来加载类，不会执行初始化块,只有在newInstance才会去执行static块
        loader.loadClass("com.max.demo.jvm.Test");
        //使用Class.forName()来加载类，默认会执行初始化块
        //Class.forName("com.max.demo.jvm.Test");
        //使用Class.forName()来加载类，并指定ClassLoader，初始化时不执行静态块
        //Class.forName("com.max.demo.jvm.Test", false, loader);
    }

    /*public static void main(String[] args) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println(loader);
        System.out.println(loader.getParent());
        System.out.println(loader.getParent().getParent());
    }*/
}
