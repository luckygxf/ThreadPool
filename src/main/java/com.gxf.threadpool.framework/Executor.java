package com.gxf.threadpool.framework;

/**
 * Created by 58 on 2017/11/24.
 */
public interface Executor {
    /**
     * 执行任务，立即返回MyFuture对象
     * */
    public MyFuture execute(Runnable task);

    /**
     * 关闭正在执行的任务
     * */
    public void close();

    /**
     * 提交任务
     * */
    public void submit(Runnable task);
}
