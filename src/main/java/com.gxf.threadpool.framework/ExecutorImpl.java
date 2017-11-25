package com.gxf.threadpool.framework;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;

/**
 * Created by 58 on 2017/11/24
 */
public class ExecutorImpl implements Executor {
    //使用线程池，执行任务
    private Thread[] threadPool;
    //任务队列
    private Queue<Runnable> taskQueue;
    //线程池大小
    private int threadPoolSize;
    //线程工厂
    private ThreadFactory threadFactory;
    //线程池默认大小
    private final int defaultThreadPoolSize = 8;
    //默认线程工厂
    private ThreadFactory defaultThreadFactory = new DefaultThreadFactory();
    //默认任务队列
    private Queue<Runnable> defaultTaskQueue = new LinkedBlockingQueue<Runnable>();
    //当前线程池线程数
    private int curThreadNum = 0;

    public ExecutorImpl(Queue<Runnable> taskQueue, int threadPoolSize, ThreadFactory threadFactory) {
        this.taskQueue = taskQueue;
        this.threadPoolSize = threadPoolSize;
        this.threadFactory = threadFactory;
    }

    public ExecutorImpl() {
        this.taskQueue = defaultTaskQueue;
        this.threadPoolSize = defaultThreadPoolSize;
        this.threadFactory = defaultThreadFactory;
    }

    /**
     * 添加任务到任务队列
     * 如果当前线程数小于线程池大小，创建新的线程
     * */
    public MyFuture execute(Runnable task) {
        taskQueue.add(task);
        if(curThreadNum < threadPoolSize){
            threadPool[curThreadNum ++] = threadFactory.newThread(task);
        }
        return null;
    }

    public void close() {
        synchronized (LockObject.lockObject){
            LockObject.lockObject.notifyAll();
        }
    }

    //提交任务到任务队列
    public void submit(Runnable task) {
        taskQueue.add(task);
    }
}
