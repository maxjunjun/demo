package com.max.demo.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by max on 2017/11/1.
 */
public class TimedRun1 {
    private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(1);

    public static void timedRun(Runnable r,
                                long timeout, TimeUnit unit) {
        final Thread taskThread = Thread.currentThread();
        cancelExec.schedule(new Runnable() {
            public void run() {
                // 中断线程，
                // 违规，不能在不知道中断策略的前提下调用中断，
                // 该方法可能被任意线程调用。
                taskThread.interrupt();
            }
        }, timeout, unit);
        r.run();
    }
}
