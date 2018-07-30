package com.ray.lib.java.container;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ConcurrentListHashMap
 *
 * @author : leixing
 * @date : 2018/5/16
 * Version : 0.0.1
 */
public class ConcurrentListHashMap<K, V> {
    private static final String TAG = ConcurrentListHashMap.class.getSimpleName();
    private static volatile ConcurrentListHashMap INSTANCE;
    private Map<K, List<V>> mMap;
    private final ReentrantReadWriteLock mLock;
    private final AtomicInteger mTraversalThreadNumber;
    private final ThreadLocal<Integer> mLocalTraversalNumber;

    public static ConcurrentListHashMap getInstance() {
        if (INSTANCE == null) {
            synchronized (ConcurrentListHashMap.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ConcurrentListHashMap();
                }
            }
        }
        return INSTANCE;
    }

    private ConcurrentListHashMap() {
        mMap = new HashMap<>();
        mLock = new ReentrantReadWriteLock();
        mTraversalThreadNumber = new AtomicInteger(0);
        mLocalTraversalNumber = new ThreadLocal<Integer>() {
            @Override
            protected Integer initialValue() {
                return 0;
            }
        };
    }

    public void registerIntercept(Class<K> cls, K interceptorType,
                                  V interceptor) {
        boolean isThreadInTraversal = mLocalTraversalNumber.get() > 0;
        if (isThreadInTraversal) {
            mLock.readLock().unlock();
        }
        mLock.writeLock().lock();
        try {
            boolean isInTraversal = mTraversalThreadNumber.get() > 0;
            registerInternal(cls, interceptorType, interceptor, isInTraversal);
        } finally {
            if (isThreadInTraversal) {
                mLock.readLock().lock();
            }
            mLock.writeLock().unlock();
        }
    }

    public void unregisterIntercept(Class<K> cls, K interceptorType,
                                    V interceptor) {
        boolean isThreadInTraversal = mLocalTraversalNumber.get() > 0;
        if (isThreadInTraversal) {
            mLock.readLock().unlock();
        }
        mLock.writeLock().lock();
        try {
            boolean isInTraversal = mTraversalThreadNumber.get() > 0;
//            unregisterInternal(cls, interceptorType, interceptor, isInTraversal);
        } finally {
            if (isThreadInTraversal) {
                mLock.readLock().lock();
            }
            mLock.writeLock().unlock();
        }
    }

    public void clearAllNextTimeIntercepts() {
        boolean isThreadInTraversal = mLocalTraversalNumber.get() > 0;
        if (isThreadInTraversal) {
            mLock.readLock().unlock();
        }
        mLock.writeLock().lock();
        try {
            mMap.clear();
        } finally {
            if (isThreadInTraversal) {
                mLock.readLock().lock();
            }
            mLock.writeLock().unlock();
        }
    }

    public boolean intercept(V command) {
        mLock.readLock().lock();
        mTraversalThreadNumber.incrementAndGet();
        Integer traversalNumber = mLocalTraversalNumber.get();
        mLocalTraversalNumber.set(traversalNumber + 1);
        try {
//            return interceptInternal(command);
        } finally {
            mLocalTraversalNumber.set(traversalNumber);
            if (traversalNumber == 0) {
                mLocalTraversalNumber.remove();
            }
            mTraversalThreadNumber.decrementAndGet();
            mLock.readLock().unlock();
        }
        return true;
    }

    private void registerInternal(Class<K> cls,
                                  K interceptorType,
                                  V interceptor,
                                  boolean needCopyOnWrite) {

//        Map<Class<? extends ICommand>, List<CommandInterceptor<? extends ICommand>>> intercepts;
//        if (CommandInterceptorType.EVERY_TIME == interceptorType) {
//            intercepts = mEveryTimeIntercepts;
//        } else {
//            intercepts = mMap;
//        }
//
//        List<CommandInterceptor<? extends ICommand>> interceptorList = mMap.get(cls);
//        if (interceptorList == null) {
//            interceptorList = new LinkedList<>();
//            intercepts.put(cls, interceptorList);
//        } else if (needCopyOnWrite) {
//            // copy on write if any thread is in traversal
//            interceptorList = new LinkedList<>(interceptorList);
//            intercepts.put(cls, interceptorList);
//        }
//        interceptorList.add(interceptor);
    }

    private void unregisterInternal(Class<K> cls,
                                    K interceptorType,

                                    V interceptor,
                                    boolean needCopyOnWrite) {
//        Map<Class<? extends ICommand>, List<CommandInterceptor<? extends ICommand>>> intercepts;
//        if (CommandInterceptorType.EVERY_TIME == interceptorType) {
//            intercepts = mEveryTimeIntercepts;
//        } else {
//            intercepts = mMap;
//        }
//
//        List<CommandInterceptor<? extends ICommand>> interceptorList = intercepts.get(cls);
//        if (interceptorList == null || interceptorList.isEmpty()) {
//            LogUtil.e(TAG, "interceptorList is null, make sure registerIntercept() has invoked");
//            return;
//        }
//        if (needCopyOnWrite) {
//            // copy on write if any thread is in traversal
//            interceptorList = new LinkedList<>(interceptorList);
//            intercepts.put(cls, interceptorList);
//        }
//        interceptorList.remove(interceptor);
    }

    boolean interceptInternal(K command) {
        // 先遍历下一次需要拦截的
//        if (interceptInternal(mMap, command, callBack)) {
//            return true;
//        } else {
//            return interceptInternal(mEveryTimeIntercepts, command, callBack);
//        }
        return true;
    }

    boolean interceptInternal(
            Map<Class<K>, List<V>> intercepts,
            V command) {
//        List<CommandInterceptor<? extends ICommand>> interceptorList = intercepts.get(command.getClass());
//        if (interceptorList == null || interceptorList.isEmpty()) {
//            return false;
//        }
//        for (CommandInterceptor<? extends ICommand> interceptor : interceptorList) {
//            // noinspection unchecked
//            if (((CommandInterceptor<T>) interceptor).intercept(command, callBack)) {
//                return true;
//            }
//        }
        return false;
    }

    public void checkInterceptTime(K command) {
//        if (command == null || command instanceof NLPCommand) { //  NLP command 不是最终业务执行command，
//            return;
//        }

        boolean isThreadInTraversal = mLocalTraversalNumber.get() > 0;
        if (isThreadInTraversal) {
            mLock.readLock().unlock();
        }
        mLock.writeLock().lock();
        try {
            Class cls = command.getClass();
            // 符合时序的:EVERY_TIME、DEFAULT_NEXT_TIME_IF_NOT_REMOVE_CALLBACK
//            List<CommandInterceptor<? extends ICommand>> interceptorList = mMap.get(cls);   // 只需要管下一次的拦截列表，不需要考虑每次都需要拦截的列表
            // 其它都是不符合时序的回调，全部移除
            mMap.clear();
//            if (interceptorList != null && interceptorList.size() > 0) {
//                // 重新添加回正确时序的拦截列表
//                mMap.put(cls, interceptorList);
//            }

        } finally {
            if (isThreadInTraversal) {
                mLock.readLock().lock();
            }
            mLock.writeLock().unlock();
        }
    }
}
