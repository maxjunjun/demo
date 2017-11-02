package com.max.demo.thread;

import java.math.BigInteger;
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
            while (!Thread.currentThread().isInterrupted())
                //put方法会隐式检查并响应中断
                queue.put(p = p.nextProbablePrime());
        } catch (InterruptedException consumed) {
            /* 允许任务退出 */
            System.out.println("退出");
        }
    }

    public void cancel() {
        interrupt();
    }
}
