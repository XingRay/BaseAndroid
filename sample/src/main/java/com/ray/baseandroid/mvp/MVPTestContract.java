package com.ray.baseandroid.mvp;

/**
 * Author      : leixing
 * Date        : 2017-05-25
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public interface MVPTestContract {
    interface View {
        void setContent(String content);
    }

    interface Presenter {
        void loadData();
    }
}
