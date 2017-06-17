package com.ray.lib.taskmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * Author      : leixing
 * Date        : 2017-06-06
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : manager for a list of task
 */
public class TaskManager {
    private List<Task> mTasks;
    private ExecutorService mExecutorService;


    public TaskManager() {
        mTasks = new ArrayList<>();
    }

    public void setExecutorService(ExecutorService executorService) {
        mExecutorService = executorService;
    }

    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("task can not be null");
        }

        mTasks.add(task);
    }

    public void start(){

    }
}
