package com.max.demo.thread;

/**
 * Created by majun on 2017/11/24.
 */
/**
 * wait用法
 * @author DreamSea
 * @time 2015.3.9
 */
public class MyThreadPrinter2 implements Runnable {

    private String name;
    private Object prev;
    private Object self;

    private MyThreadPrinter2(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0) {
            System.out.print("1:"+ name);
            synchronized (prev) {
                System.out.print("2:"+ name);
                synchronized (self) {
                    System.out.print("3:"+name);
                    count--;

                    self.notify();
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) throws Exception {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        MyThreadPrinter2 pa = new MyThreadPrinter2("A", c, a);
        MyThreadPrinter2 pb = new MyThreadPrinter2("B", a, b);
        MyThreadPrinter2 pc = new MyThreadPrinter2("C", b, c);
        new Thread(pa).start();
        Thread.sleep(1);  //确保按顺序A、B、C执行
        new Thread(pb).start();
        Thread.sleep(1);
        new Thread(pc).start();
        Thread.sleep(1);
    }
}
