package com.ray.lib.android.detector.keyboard;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;

/**
 * Author      : leixing
 * Date        : 2017-04-26
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : 软键盘检测器
 * 注意：检测器目前只能检测竖屏状态下的键盘弹起和隐藏事件，横屏时，键盘的弹起和隐藏不会触发回调
 */

public class KeyBoardStateDetector {
    /**
     * 键盘状态-未知状态
     */
    private static final int KEY_BOARD_STATE_UNKNOW = 0;

    /**
     * 键盘状态-弹起状态
     */
    private static final int KEY_BOARD_STATE_POPUP = 1;

    /**
     * 键盘状态-消失状态
     */
    private static final int KEY_BOARD_STATE_DISMISS = 2;

    public void detect(Activity activity, final KeyBoardStateListener listener) {
        if (activity == null) {
            throw new IllegalArgumentException("activity can not be null");
        }

        if (listener == null) {
            throw new IllegalArgumentException("listener can not be null");
        }

        Window window = activity.getWindow();
        if (window == null) {
            throw new IllegalStateException("activity did not attach window");
        }

        final View contentView = window.getDecorView().findViewById(android.R.id.content);
        if (contentView == null) {
            throw new IllegalStateException("can not find content view in decor view");
        }

        contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            /**
             * 记录上次键盘的状态，防止onGlobalLayout多次调用导致KeyBoardStateListener多次调用
             */
            int keyBoardState = KEY_BOARD_STATE_DISMISS;

            @Override
            public void onGlobalLayout() {
                boolean isKeyboardPopup = isKeyboardShown(contentView);
                if (isKeyboardPopup && keyBoardState != KEY_BOARD_STATE_POPUP) {
                    keyBoardState = KEY_BOARD_STATE_POPUP;
                    listener.onPopup();
                } else if (!isKeyboardPopup && keyBoardState != KEY_BOARD_STATE_DISMISS) {
                    keyBoardState = KEY_BOARD_STATE_DISMISS;
                    listener.onDismiss();
                }
            }
        });
    }

    private boolean isKeyboardShown(View contentView) {
        Rect r = new Rect();
        contentView.getWindowVisibleDisplayFrame(r);
        int visibleHeight = r.bottom - r.top;
        int screenHeight = contentView.getRootView().getHeight();
        int keypadHeight = screenHeight - visibleHeight;

        return keypadHeight > screenHeight * 0.15;
    }
}
