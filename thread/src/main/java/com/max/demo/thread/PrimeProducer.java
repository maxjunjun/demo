package com.max.demo.thread;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by max on 2017/11/1.
 */
public class PrimeProducer extends Thread  {
    private final BlockingQueue<BigInteger> queue;
    PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            //使用中断的方式来取消任务
            while (!Thread.currentThread().isInterrupted()) {

                synchronized (PrimeProducer.class) {
                    queue.put(p = p.nextProbablePrime());
                    Thread.sleep(100000);
                }
            }
                //put方法会隐式检查并响应中断

        } catch (InterruptedException consumed) {
            System.out.println(Thread.currentThread().isInterrupted());
            /* 允许任务退出 */
            System.out.println("退出");
        }

        System.out.println(Thread.currentThread().isInterrupted());
    }

    public void cancel() {
        interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        PrimeProducer p  =new PrimeProducer(new ArrayBlockingQueue<BigInteger>(10));
        PrimeProducer p1  =new PrimeProducer(new ArrayBlockingQueue<BigInteger>(10));
        p.start();
        Thread.sleep(10);
        p1.start();

        //p.interrupt();
        p.cancel();
        Thread.sleep(10);
        p1.cancel();
    }
}
