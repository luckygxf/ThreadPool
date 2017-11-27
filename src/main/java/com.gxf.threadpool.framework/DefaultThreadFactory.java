package com.gxf.threadpool.framework;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 58 on 2017/11/
 */
public class DefaultThreadFactory implements ThreadFactory {
    private static AtomicInteger poolNumber = new AtomicInteger(1);
    private ThreadGroup threadGroup;
    private AtomicInteger threadNumber = new AtomicInteger(1);
    private String namePrefix;

    DefaultThreadFactory(){
        SecurityManager s = System.getSecurityManager();
        threadGroup = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        namePrefix = "gxf-pool-" + poolNumber.incrementAndGet() + "-thread-";
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(threadGroup, r, namePrefix + threadNumber.getAndIncrement(), 0);
        if(t.isDaemon()){
            t.setDaemon(false);
        }
        if(t.getPriority() != Thread.NORM_PRIORITY){
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
