package com.gxf.threadpool.framework;

/**
 * Created by 58 on 2017/11/24.
 */
public class Task implements Runnable {
    //执行任务，20s左右，完成
    public void run() {
        System.out.println("start do task");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end do task");
    }
}
