package com.nacos.alibaba.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class SyncThreadPoolExecutor {
    private volatile static ExecutorService pool;

    private SyncThreadPoolExecutor(){}
    public static ExecutorService getPool(){
        if(pool == null){
            synchronized (pool){
                if(pool == null){
                    pool = new ThreadPoolExecutor(10, 10,
                            60L, TimeUnit.SECONDS,
                            new ArrayBlockingQueue(1000)); // 定长工作队列， 防止OOM
                }
            }
        }
        return pool;
    }

    public static void run(Runnable job){
        getPool().execute(job);
    }
}
