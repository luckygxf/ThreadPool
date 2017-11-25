package com.gxf.threadpool.framework;

/**
 * Created by 58 on 2017/11/24.
 */
public class MyFutureImpl implements MyFuture {

    public void sync() {
        synchronized (LockObject.lockObject){
            try {
                LockObject.lockObject.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
