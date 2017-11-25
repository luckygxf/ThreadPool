package com.gxf.threadpool.test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by 58 on 2017/11/24.
 */
public class TestThreadDemo {
    public static void main(String[] args) {
        java.util.concurrent.Executor executor = new ThreadPoolExecutor(10, 200, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        executor.execute(new Task());

    }

    static class Task implements Runnable{

        public void run() {
            System.out.println("do task");
        }
    }
}
