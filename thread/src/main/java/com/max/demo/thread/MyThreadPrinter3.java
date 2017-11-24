package com.max.demo.thread;

/**
 * Created by hanzhongao on 2017/11/24.
 */
public class MyThreadPrinter3 implements Runnable {
    private Integer i = 126;

    MyThreadPrinter3 () {

    }
    MyThreadPrinter3 (Integer i) {
        this.i = i;
    }
    @Override
    public void run() {
        i=i +1;
        System.out.print("a"+i);
        synchronized (i) {
            try {
                System.out.print("b"+i);
                Thread.sleep(100);
                System.out.print("c"+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int i = 300;
        new Thread(new MyThreadPrinter3(i)).start();
        Thread.sleep(10);
        new Thread(new MyThreadPrinter3(i)).start();
    }
}
