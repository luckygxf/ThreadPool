package com.gxf.threadpool.test;

import com.gxf.threadpool.framework.Executor;
import com.gxf.threadpool.framework.ExecutorImpl;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by 58 on 2017/11/24.
 */
public class TestThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Executor executor = new ExecutorImpl();
        for(int i = 0; i < 20; i++){
            Task task = new Task();
            executor.execute(task);
            Thread.sleep(1000);
        }

    }

    static class Task implements Runnable{

        public void run() {
            int index = 30;
            while(true){
                System.out.println("do task");
                System.out.println("thread id : " + Thread.currentThread().getId());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(index -- < 0){
                    break;
                }
            }
        }
    }
}
