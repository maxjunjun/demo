package com.max.demo.thread;

/**
 * Created by hanzhongao on 2018/2/8.
 */
class ExceptionHandler implements Thread.UncaughtExceptionHandler
{
    @Override
    public void uncaughtException(Thread t, Throwable e)
    {
        System.out.println("==Exception: "+e.getMessage());
    }
}
