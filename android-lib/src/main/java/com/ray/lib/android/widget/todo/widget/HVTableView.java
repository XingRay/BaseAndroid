package com.ray.lib.android.widget.todo.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Scroller;

/**
 * 可以固定列滚动的TableView,通过调用setAdapter方法显示数据
 * 初始版请参阅com.hecom.ResUtil.getStringRes(com.hecom.mgm.R.string.nongminbobo)技术博客：http://www.cnblogs.com/over140/archive/2011/12/07/2275207.html
 * 原理：用LinearLayout包住子项，然后调用.scrollX设置滚动位置
 *
 * @author tianlupan 2015/10/14
 */
public class HVTableView extends LinearLayout implements Choreographer.FrameCallback {

    private static final String TAG = HVTableView.class.getSimpleName();
    private HVTableAdapter mTableAdapter;
    private LinearLayout mHeadView;
    private ListView mListView;
    /**
     * 偏移坐标
     */
    private int mOffset = 0;
    /**
     * 滚动区可视大小
     */
    private int mScreenWidth;
    /**
     * 滚动最大范围
     */
    private int mHeadMaxScrollWidth = 0;
    /**
     * 手势
     */
    private GestureDetector mGesture;
    private SCROLL_STATUS scroll_mode = SCROLL_STATUS.INIT;
    private Scroller mScroller = new Scroller(getContext());
    /**
     * fling动画原理请参考  {@link ValueAnimator}
     */
    private Choreographer mDirector = Choreographer.getInstance();
    /**
     * 手势
     */
    private GestureDetector.OnGestureListener mOnGesture = new GestureDetector.SimpleOnGestureListener() {

        @Override
        public boolean onDown(MotionEvent e) {
            boolean isFlip = !mScroller.isFinished();
            restartGuesture();
            return isFlip;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //横向划动
            if (Math.abs(velocityX) > Math.abs(velocityY)) {
                checkScrollGroupWidth();
                mScroller.fling(mOffset, 0, -1 * (int) velocityX, 0, 0, mHeadMaxScrollWidth - getScreenWidth(), 0, 0);
                postSchedule();
                scroll_mode = SCROLL_STATUS.HORIZON;
                return true;
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        /** 滚动 */
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {

            //如果左右移动大小上下移动，认为是拖动事件
            boolean isHorizonScroll = Math.abs(distanceX) > Math.abs(distanceY);

            if (scroll_mode == SCROLL_STATUS.INIT) {
                if (isHorizonScroll) {
                    scroll_mode = SCROLL_STATUS.HORIZON;
                } else {
                    scroll_mode = SCROLL_STATUS.VERTICAL;
                }
            }

            if (scroll_mode == SCROLL_STATUS.HORIZON) {
                onScrollby(distanceX);
            }

            return (scroll_mode == SCROLL_STATUS.HORIZON);
        }
    };

    public HVTableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        mGesture = new GestureDetector(context, mOnGesture);
    }

    /**
     * 获取屏幕可见范围内最大屏幕
     *
     * @return
     */
    private int getScreenWidth() {
        if (mScreenWidth == 0) {
            mScreenWidth = getContext().getResources().getDisplayMetrics().widthPixels;
            if (mHeadView != null) {
                mScreenWidth = mListView.getWidth();
                //减去固定第一列
                mScreenWidth -= mTableAdapter.getTableTitlePart(true).getMeasuredWidth();
            }
        }
        return mScreenWidth;
    }

    private void setLayout() {

        mHeadView = mTableAdapter.getTableTitle();
        addView(mHeadView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        mListView = new ListView(getContext(), null);
        mListView.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(mListView);

        mListView.setVisibility(View.VISIBLE);
        mOffset = 0;
        synOffset();

    }

    public void setAdapter(HVTableAdapter adapter) {

        if (mTableAdapter != null) {
            removeView(mHeadView);
            removeView(mListView);
            removeAllViews();
        }

        mTableAdapter = adapter;

        setLayout();

        mListView.setAdapter(mTableAdapter);

        invalidate();
    }

    private void synOffset() {
        getHeaderScrollGroup().setScrollX(mOffset);

        //根据手势滚动Item视图
        for (int i = 0, j = mListView.getChildCount(); i < j; i++) {
            //这里是固定形式，Item对应的View的第二个child必须为LineareLayout
            //这种写法和adpter有实现耦合，不太好
            View child = ((ViewGroup) mListView.getChildAt(i)).getChildAt(1);
            if (child.getScrollX() != mOffset)
                child.scrollTo(mOffset, 0);
        }


        mTableAdapter.setScrollX(mOffset);
    }

    private View getHeaderScrollGroup() {
        return mTableAdapter.getTableTitlePart(false);
    }

    /**
     * 检查左右滑动的最大范围
     */
    private void checkScrollGroupWidth() {
        if (mHeadMaxScrollWidth == 0) {
            //检查总共多长
            ViewGroup viewGroup = (ViewGroup) getHeaderScrollGroup();
            int totalWidth = 0;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                totalWidth += viewGroup.getChildAt(i).getWidth();
            }
            mHeadMaxScrollWidth = totalWidth;
        }
    }

    private void onScrollby(float distanceX) {

        checkScrollGroupWidth();

        int moveX = (int) distanceX;

        int curX = getHeaderScrollGroup().getScrollX();

        //防止越界
        if (curX + moveX + getScreenWidth() > mHeadMaxScrollWidth) {
            moveX = mHeadMaxScrollWidth - getScreenWidth() - curX;
        }

        //如果往左滑，并且快靠边了，直接到头
        if (curX < 80 && moveX < 0) {
            moveX = -curX;
        }

        //控制越界问题
        if (curX + moveX < 0) {
            moveX = 0;
        }

        mOffset += moveX;

        synOffset();


        requestLayout();
    }

    //setAdapter调用之后才能返回
    public ListView getListView() {
        return mListView;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        //未设置数据前，不处理
        if (mListView == null || mHeadView == null) {
            return false;
        }

        if (ev.getAction() == MotionEvent.ACTION_UP) {
            restartGuesture();
        }

        //TODO 已经可以实现，但不太完美
        //不能用processed进行判断，因为有些小额Move事件，如x,y偏移都小于1像素，
        // 下面代码processed始终返回false,没有走onScroll过程
        boolean processed = mGesture.onTouchEvent(ev);

        return processed;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP) {
            restartGuesture();
        }
        //不能用processed进行判断，因为有些小额Move事件，如x,y偏移都小于1像素，
        // 下面代码processed始终返回false,没有走onScroll过程
        boolean processed = mGesture.onTouchEvent(event);

        if (scroll_mode == SCROLL_STATUS.VERTICAL) {
            return super.onTouchEvent(event);
        } else {
            return false;
        }

    }

    private void restartGuesture() {
        scroll_mode = SCROLL_STATUS.INIT;
        mScroller.forceFinished(true);
    }

    @Override
    public void doFrame(long frameTimeNanos) {
        mScroller.computeScrollOffset();
        if (!mScroller.isFinished() && mScroller.getCurrX() >= 0 && mScroller.getCurrX() != mScroller.getFinalX()) {
            mOffset = mScroller.getCurrX();
            synOffset();
            postSchedule();
        }
    }

    private void postSchedule() {
        mDirector.postFrameCallback(this);
    }

    private enum SCROLL_STATUS {
        INIT("未开始"), //未运行阶段
        HORIZON("水平滚动"), //水平模式
        VERTICAL("垂直滚动"); //上下滑动

        private final String mType;

        SCROLL_STATUS(String type) {
            mType = type;
        }

        @Override
        public String toString() {
            return "SCROLL_STATUS:" + mType;
        }
    }


}
