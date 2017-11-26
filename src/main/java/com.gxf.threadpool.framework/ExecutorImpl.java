package com.gxf.threadpool.framework;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;

/**
 * Created by 58 on 2017/11/24
 */
public class ExecutorImpl implements Executor {
    //使用线程池，执行任务
    private Worker[] threadPool;
    //任务队列
    public static BlockingQueue<Runnable> taskQueue;
    //线程池大小
    private int threadPoolSize;
    //线程工厂
    private ThreadFactory threadFactory;
    //线程池默认大小
    private final int defaultThreadPoolSize = 8;
    //默认线程工厂
    private ThreadFactory defaultThreadFactory = new DefaultThreadFactory();
    //默认任务队列
    private BlockingQueue<Runnable> defaultTaskQueue = new LinkedBlockingQueue<Runnable>();
    //当前线程池线程数
    private int curThreadNum = 0;

    public ExecutorImpl(BlockingQueue<Runnable> taskQueue, int threadPoolSize, ThreadFactory threadFactory) {
        this.taskQueue = taskQueue;
        this.threadPoolSize = threadPoolSize;
        this.threadFactory = threadFactory;
    }

    public ExecutorImpl() {
        this.threadPool = new Worker[defaultThreadPoolSize];
        this.taskQueue = defaultTaskQueue;
        this.threadPoolSize = defaultThreadPoolSize;
        this.threadFactory = defaultThreadFactory;
    }

    /**
     * 添加任务到任务队列
     * 如果当前线程数小于线程池大小，创建新的线程
     * */
    public MyFuture execute(Runnable task) {
        //当前线程小于线程池大小，新建线程
        if(curThreadNum < threadPoolSize){
            Worker worker = new Worker(task);
            Thread t = worker.getThread();
            t.start();
            curThreadNum ++;
        }
        //大于当前线程池大小，提交到任务队列
        else{
            taskQueue.add(task);
        }
        System.out.println("cur pool size : " + curThreadNum);
        System.out.println("cur queue size : " + taskQueue.size());
        return new MyFutureImpl();
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
