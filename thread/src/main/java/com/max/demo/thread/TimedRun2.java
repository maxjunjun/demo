package com.max.demo.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.max.demo.thread.LaunderThrowable.launderThrowable;

/**
 * Created by max on 2017/11/1.
 */
public class TimedRun2 {
    private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(1);


    public static void timedRun(final Runnable r,
                                long timeout, TimeUnit unit)
            throws InterruptedException {
        class RethrowableTask implements Runnable {
            private volatile Throwable t;

            public void run() {
                try {
                    r.run();
                } catch (Throwable t) {
                    System.out.println("退出");
                    //中断策略，保存当前抛出的异常，退出
                    this.t = t;
                }
            }

            // 再次抛出异常
            void rethrow() {
                if (t != null)
                    throw launderThrowable(t);
            }
        }

        RethrowableTask task = new RethrowableTask();
        final Thread taskThread = new Thread(task);
        //开启任务子线程
        taskThread.start();
        //定时中断任务子线程
        cancelExec.schedule(new Runnable() {
            public void run() {
                taskThread.interrupt();
            }
        }, timeout, unit);

        //限时等待任务子线程执行完毕
        taskThread.join(unit.toMillis(timeout));
        //尝试抛出task在执行中抛出到异常
        task.rethrow();
    }

    public static void main(String[] args) throws InterruptedException {
        TimedRun2.timedRun(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    //System.out.println(1111);
                }

            }
        },1,TimeUnit.SECONDS);
    }
}
