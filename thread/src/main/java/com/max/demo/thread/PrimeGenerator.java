package com.max.demo.thread;

import javax.xml.bind.SchemaOutputResolver;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by max on 2017/11/1.
 */
public class PrimeGenerator implements Runnable {
    private static ExecutorService exec = Executors.newCachedThreadPool();

    private final List<BigInteger> primes
            = new ArrayList<BigInteger>();
    //取消标志位
    private volatile boolean cancelled;

    public void run() {
        BigInteger p = BigInteger.ONE;
        //每次在生成下一个素数时坚持是否取消
        //如果取消，则退出
        while (!cancelled) {
            p = p.nextProbablePrime();
            System.out.println(p);
            synchronized (this) {
                primes.add(p);
            }
        }
    }

    public void cancel() {
        cancelled = true;
    }

    public synchronized List<BigInteger> get() {
        return new ArrayList<BigInteger>(primes);
    }

    static List<BigInteger> aSecondOfPrimes() throws InterruptedException {
        PrimeGenerator generator = new PrimeGenerator();
        exec.execute(generator);
        try {
            TimeUnit.SECONDS.sleep(1);
        } finally {
            generator.cancel();
        }
        return generator.get();
    }
}
