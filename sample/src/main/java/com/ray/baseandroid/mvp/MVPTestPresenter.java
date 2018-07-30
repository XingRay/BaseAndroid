package com.ray.baseandroid.mvp;

import com.ray.lib.android.base.page.BasePresenter;
import com.ray.lib.android.manager.TaskExecutor;
import com.ray.lib.android.util.TraceUtil;

/**
 * @author : leixing
 * @date : 2017-05-25
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class MVPTestPresenter extends BasePresenter<MVPTestContract.View> implements MVPTestContract.Presenter {
    public MVPTestPresenter(MVPTestContract.View view) {
        super(view);
    }

    @Override
    public void loadData() {
        TraceUtil.log();
        TaskExecutor.io(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final String content = "hello mvp";
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getView().setContent(content);
                    }
                });
            }
        });
    }
}
