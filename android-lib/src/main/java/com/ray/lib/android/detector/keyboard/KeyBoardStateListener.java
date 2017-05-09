package com.ray.lib.android.detector.keyboard;

/**
 * Author      : leixing
 * Date        : 2017-04-27
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : 键盘状态监听器
 */

public interface KeyBoardStateListener {
    /**
     * 键盘弹起
     */
    void onPopup();

    /**
     * 键盘消失
     */
    void onDismiss();
}
