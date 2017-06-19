package com.ray.lib.taskmanager;

import java.util.List;

/**
 * Author      : leixing
 * Date        : 2017-06-06
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : task for {@code TaskManager}
 */
public abstract class Task implements Runnable {
    /**
     * name for this task
     */
    private String mName;

    /**
     * weight for this task, use for calculate percentage of progress
     */
    private int mWeight;

    /**
     * retry times for this task, every time this task restart, it will decrease by 1
     * {@code #TaskManager} must check this value before call
     */
    private int mRetryTimes;

    /**
     * this task is successfully executed
     */
    private boolean mIsSucceed;

    /**
     * precursors of this task, this task must wait for all of precursors's successful completion before its execution
     */
    private List<Task> mPrecursors;

    /**
     * successors of this task, this task will notify its successors after it completed successfully
     */
    List<Task> mSuccessors;


    /**
     * start to execute task, it will execute after all its precursors execute successfully
     */
    void start() {
        if (!isAllPrecursorsSuccess()) {
            return;
        }
        run();

        if (mIsSucceed) {
            notifySuccessors();
        }
    }

    /**
     * restart to execute task, must check {@code retryTimes} by call {@code canRetry()}
     */
    void restart() {
        if (mRetryTimes == 0) {
            throw new IllegalStateException("can not retry anymore");
        }
        mRetryTimes--;

        run();
        if (mIsSucceed) {
            notifySuccessors();
        }
    }

    private boolean isAllPrecursorsSuccess() {
        if (mPrecursors != null) {
            for (Task precursor : mPrecursors) {
                if (!precursor.isSucceed()) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * check if this task can retry
     *
     * @return whether this task can retry
     */
    boolean canRetry() {
        return mRetryTimes > 0;
    }

    boolean isSucceed() {
        return mIsSucceed;
    }

    final void setResult(boolean isSucceed) {
        mIsSucceed = isSucceed;
    }

    private void notifySuccessors() {

    }
}
