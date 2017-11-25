package com.gxf.threadpool.framework;

import static java.lang.Thread.sleep;

/**
 * Created by 58 on 2017/11/24.
 * 测试框架
 */
public class TestFrameWork {
    public static void main(String[] args) {
        Task task = new Task();
        Executor executor = new ExecutorImpl();
        MyFuture myFuture = executor.execute(task);
        closeExecutor(executor);
        myFuture.sync();
    }
    public static void closeExecutor(final Executor executor){
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(6000);
                    System.out.println("start close executor");
                    executor.close();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}
