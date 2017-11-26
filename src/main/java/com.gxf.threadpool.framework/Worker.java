package com.gxf.threadpool.framework;

import java.util.concurrent.ThreadFactory;

/**
 * Created by GuanXF on 2017/11/26.
 * 执行任务的线程和任务封装
 */
public class Worker implements Runnable {
    private Thread thread;
    private Runnable firstTask;
    private ThreadFactory threadFactory = new DefaultThreadFactory();

    public Worker(Runnable task) {
        this.firstTask = task;
        thread = threadFactory.newThread(this);
    }

    public void run() {
        try {
            runWorker();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void runWorker() throws InterruptedException {
        Runnable task = firstTask;
        if(task != null || (task = takeTask()) != null){
            task.run();
            task = null;
        }
    }

    /**
     * 从任务队列获取需要执行的任务
     * */
    private Runnable takeTask() throws InterruptedException {
        return ExecutorImpl.taskQueue.take();
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
