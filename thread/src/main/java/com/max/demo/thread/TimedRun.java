package com.max.demo.thread;

import java.util.concurrent.*;

import static com.max.demo.thread.LaunderThrowable.launderThrowable;

/**
 * Created by max on 2017/11/1.
 */
public class TimedRun {
    private static final ExecutorService taskExec = Executors.newCachedThreadPool();

    public static void timedRun(Runnable r,
                                long timeout, TimeUnit unit)
            throws InterruptedException {
        Future<?> task = taskExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (TimeoutException e) {
            // 因超时而取消任务
        } catch (ExecutionException e) {
            // 任务异常，重新抛出异常信息
            throw launderThrowable(e.getCause());
        } finally {
            // 如果该任务已经完成，将没有影响
            // 如果任务正在运行，将因为中断而被取消
            task.cancel(true); // interrupt if running
        }
    }
}
