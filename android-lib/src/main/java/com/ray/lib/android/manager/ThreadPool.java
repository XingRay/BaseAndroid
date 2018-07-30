package com.ray.lib.android.manager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author      : leixing
 * Date        : 2017-04-28
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class ThreadPool {

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE_SECONDS = 30;
    private static final BlockingQueue<Runnable> sPoolWorkQueue;
    private static final ThreadFactory sThreadFactory;

    private static ExecutorService mIOPool;
    private static ExecutorService mCachePool;
    private static ExecutorService mSerialPool;
    private static ExecutorService mCpuPool;

    static {
        mIOPool = Executors.newFixedThreadPool(2, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "IO-POOL");
            }
        });

        mCachePool = Executors.newCachedThreadPool();

        mSerialPool = Executors.newFixedThreadPool(1, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "SERIAL-POOL");
            }
        });

        sPoolWorkQueue = new LinkedBlockingQueue<>(128);

        sThreadFactory = new ThreadFactory() {
            private final AtomicInteger mCount = new AtomicInteger(1);

            public Thread newThread(Runnable r) {
                return new Thread(r, "AsyncTask #" + mCount.getAndIncrement());
            }
        };

        mCpuPool = new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_SECONDS, TimeUnit.SECONDS,
                sPoolWorkQueue, sThreadFactory);
    }

    private ThreadPool() {
        throw new UnsupportedOperationException();
    }

    /**
     * IO读写线程池,最多二个同时执行
     *
     * @return
     */
    public static Executor ioPool() {
        return mIOPool;
    }

    /**
     * 全局cachePool,适用于AsyncHttpClient等不限制任务数的请求
     *
     * @return
     */
    public static ExecutorService getDefault() {
        return mCachePool;
    }

    /**
     * 繁重 任务线程池，适用于像ImageLoader转换图像这种时间不长但又很占CPU的任务
     * 排队执行的ThreadPool,核心线程为CORE_POOL_SIZE+1个
     *
     * @return
     */
    public static Executor cpuPool() {
        return mCpuPool;
    }

    /**
     * 串行线程池
     *
     * @return
     */
    public static ExecutorService serialPool() {
        return mSerialPool;
    }
}
