package com.max.demo.thread;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hanzhongao on 2017/11/24.
 */
public class MyThreadPrinter3 implements Runnable {
    public static Integer i = 121;
    private String name ;

    MyThreadPrinter3 () {

    }
    MyThreadPrinter3 (String name) {
        this.name = name;
    }
    @Override
    public void run() {
        Integer j =i ;

        if("A".equals(name)) {
            System.out.print("Aa"+j);
            synchronized (i) {
                try {
                    System.out.print("Ab"+i);
                    Thread.sleep(100);
                    i = i+1;
                    System.out.print("Ac"+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.print("Ba"+j);
            synchronized (name) {
                try {
                    System.out.print("Bb"+i);
                    Thread.sleep(100);
                    i = i+1;
                    System.out.print("Bc"+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Integer i = new Integer(125);
        new Thread(new MyThreadPrinter3("A")).start();
        Thread.sleep(10);
        Set<Integer> a = new HashSet<>();
        new Thread(new MyThreadPrinter3("B")).start();
    }
}
