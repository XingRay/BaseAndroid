package com.ray.lib.android.manager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

/**
 * 任务执行器
 *
 * @author : leixing
 * @date : 2018/5/18
 * Version : 0.0.1
 */
@SuppressWarnings("unused")
public class TaskExecutor {
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;

    /**
     * IO读写线程池,最多二个同时执行
     */
    private static ExecutorService sIOPool;

    /**
     * 全局cachePool,适用于AsyncHttpClient等不
     * 限制任务数的请求
     */
    private static ExecutorService sCachePool;

    /**
     * 串行线程池
     */
    private static ExecutorService sSerialPool;

    /**
     * 繁重 任务线程池，适用于像ImageLoader转换图像这种时间不长但又很占CPU的任务
     * 排队执行的ThreadPool,核心线程为CORE_POOL_SIZE+1个
     */
    private static ExecutorService sCpuPool;

    /**
     * 主线程handler，用于将任务提交至主线程执行
     */
    private static Handler uiHandler;

    static {
        sIOPool = new ThreadPoolExecutor(
                2,
                2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new NamedThreadFactory("io-pool"));

        sCachePool = new ThreadPoolExecutor(
                0,
                Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                new NamedThreadFactory("cache-pool"));

        sSerialPool = new ThreadPoolExecutor(
                1,
                1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new NamedThreadFactory("serial-pool"));

        sCpuPool = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                30L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(128),
                new NamedThreadFactory("cpu-pool"));

        uiHandler = new Handler(Looper.getMainLooper());
    }

    private TaskExecutor() {
        throw new UnsupportedOperationException();
    }

    public static void ui(Runnable task) {
        uiHandler.post(task);
    }

    public static void io(Runnable task) {
        sIOPool.execute(task);
    }

    public static void cpu(Runnable task) {
        sCpuPool.execute(task);
    }

    public static void enqueue(Runnable task) {
        sSerialPool.execute(task);
    }

    public static void infinite(Runnable task) {
        sCachePool.execute(task);
    }

    private static class NamedThreadFactory implements ThreadFactory {

        private final String mThreadName;
        private final AtomicInteger mCount;

        NamedThreadFactory(String threadName) {
            mThreadName = threadName;
            mCount = new AtomicInteger(1);
        }

        @Override
        public Thread newThread(@NonNull Runnable r) {
            return new Thread(r, mThreadName + "#" + mCount.getAndIncrement());
        }
    }
}
