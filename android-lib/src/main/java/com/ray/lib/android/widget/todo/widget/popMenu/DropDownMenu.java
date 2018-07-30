package com.ray.lib.android.widget.todo.widget.popMenu;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ray.lib.android.R;
import com.ray.lib.android.util.ViewUtil;
import com.ray.lib.java.util.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Author      : leixing
 * Date        : 2016-12-22
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class DropDownMenu extends FrameLayout {
    private static final int ANIMATION_DURATION = 300;
    private final Context mContext;
    private final DropDownMenu mView;
    int mMenuNormalColor = Color.parseColor("#333333");
    int mMenuHighlightColor = Color.parseColor("#e15151");
    private LinearLayout llMenuContainer;
    private MenuActionListener mListener;
    private TranslateAnimation showTranslateAnimation;
    private TranslateAnimation dismissTranslateAnimation;
    private AlphaAnimation showAlphaAnimation;
    private AlphaAnimation dismissAlphaAnimation;
    private List<TextView> mMenus;
    private int mLastHighlightIndex = -1;
    private Status mStatus = Status.INIT;

    public DropDownMenu(Context context) {
        this(context, null);
    }

    public DropDownMenu(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public DropDownMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        this.mView = this;
        this.mView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        setFocusable(true);

        llMenuContainer = new LinearLayout(mContext);
        llMenuContainer.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        llMenuContainer.setOrientation(LinearLayout.VERTICAL);
        llMenuContainer.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        llMenuContainer.setBackgroundColor(getResources().getColor(R.color.white));
        llMenuContainer.setGravity(Gravity.TOP);
        addView(llMenuContainer);

        mMenus = new ArrayList<>();

        setBackgroundColor(getResources().getColor(R.color.transparent_gray));
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mStatus != Status.SHOWING) {
                    return;
                }

                if (mListener != null) {
                    mListener.onMenuBackgroundClick();
                }
            }
        });

        makeAnimations();
    }

    /**
     * 设置菜单的普通颜色
     *
     * @param color
     */
    public void setmMenuNormalColor(int color) {
        this.mMenuNormalColor = color;
    }

    /**
     * 设置菜单的高亮颜色
     *
     * @param color
     */
    public void setmMenuHighlightColor(int color) {
        this.mMenuHighlightColor = color;
    }

    /**
     * 初始化动画效果
     */
    private void makeAnimations() {
        showTranslateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, -1.0f,
                Animation.RELATIVE_TO_SELF, 0);
        showTranslateAnimation.setDuration(ANIMATION_DURATION);
        showTranslateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mStatus = Status.OPENING;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mStatus = Status.SHOWING;

                if (mListener != null) {
                    mListener.onMenuShowEnd();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        showAlphaAnimation = new AlphaAnimation(0, 1.0f);
        showAlphaAnimation.setDuration(ANIMATION_DURATION);

        dismissTranslateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, -1.0f);
        dismissTranslateAnimation.setDuration(ANIMATION_DURATION);
        dismissTranslateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mStatus = Status.DISMISSING;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mStatus = Status.INIT;

                mView.setVisibility(GONE);
                if (mListener != null) {
                    mListener.onMenuShowEnd();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        dismissAlphaAnimation = new AlphaAnimation(1.0f, 0);
        dismissAlphaAnimation.setDuration(ANIMATION_DURATION);
    }

    /**
     * 设置菜单
     *
     * @param menus
     * @return
     */
    public DropDownMenu setMenus(List<String> menus) {
        llMenuContainer.removeAllViews();
        mMenus.clear();

        if (!(menus == null || menus.isEmpty())) {
            for (int i = 0; i < menus.size(); i++) {
                TextView vMenu = buildMenu(menus.get(i));
                final int position = i;
                vMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mListener != null) {
                            mListener.onMenuClick(position);
                        }
                    }
                });
                llMenuContainer.addView(vMenu);
                mMenus.add(vMenu);
            }
        }

        return this;
    }

    public DropDownMenu highlightMenu(int index) {
        if (index == mLastHighlightIndex) {
            return this;
        }

        TextView tvLastHighlightMenu = CollectionUtil.safetyGet(mMenus, mLastHighlightIndex);
        if (tvLastHighlightMenu != null) {
            tvLastHighlightMenu.setTextColor(mMenuNormalColor);
        }

        TextView tvMenu = CollectionUtil.safetyGet(mMenus, index);
        if (tvMenu != null) {
            tvMenu.setTextColor(mMenuHighlightColor);
        }

        mLastHighlightIndex = index;
        return this;
    }

    /**
     * 构造选项, 可由子类继承来改变选项的视图
     *
     * @param menu
     * @return
     */
    protected TextView buildMenu(String menu) {
        TextView tvMenu = new TextView(mContext);
        tvMenu.setText(menu);
        tvMenu.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        int px10 = (int) ViewUtil.dp2px(mContext, 10);
        tvMenu.setPadding(px10, px10, px10, px10);
        tvMenu.setTextColor(mMenuNormalColor);
        tvMenu.setTextSize(16);
        return tvMenu;
    }

    /**
     * 展示选项单
     */
    public void show() {
        if (mListener != null) {
            mListener.onMenuShowStart();
        }
        mView.setVisibility(VISIBLE);
        llMenuContainer.startAnimation(showTranslateAnimation);
        mView.startAnimation(showAlphaAnimation);
    }

    /**
     * 隐藏选项单
     */
    public void dismiss() {
        if (mListener != null) {
            mListener.onMenuDismissStart();
        }

        llMenuContainer.startAnimation(dismissTranslateAnimation);
        mView.startAnimation(dismissAlphaAnimation);
    }

    /**
     * 设置点击事件监听器
     *
     * @param listener
     * @return
     */
    public DropDownMenu setMenuActionListener(MenuActionListener listener) {
        this.mListener = listener;
        return this;
    }

    /**
     * 菜单的状态
     */
    private enum Status {
        /**
         * 初始状态
         */
        INIT,

        /**
         * 正在打开
         */
        OPENING,

        /**
         * 正在展示
         */
        SHOWING,

        /**
         * 正在收起
         */
        DISMISSING
    }

    /**
     * 弹出选项单的点击事件
     */
    public interface MenuActionListener {
        /**
         * 选项的点击事件
         *
         * @param position 被点击的选项的下标
         */
        void onMenuClick(int position);

        /**
         * 背景的点击事件
         */
        void onMenuBackgroundClick();

        /**
         * 在选项单的展示动画执行前调用
         */
        void onMenuShowStart();

        /**
         * 在选项单的展示动画执行后调用
         */
        void onMenuShowEnd();

        /**
         * 在选项单的隐藏动画执行前调用
         */
        void onMenuDismissStart();

        /**
         * 在选项单的隐藏动画执行后调用
         */
        void onMenuDismissEnd();
    }
}
