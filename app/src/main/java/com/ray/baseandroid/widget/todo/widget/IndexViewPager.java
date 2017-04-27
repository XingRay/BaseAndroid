package com.ray.baseandroid.widget.todo.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class IndexViewPager extends ViewPager {

	private final float RANGE = 15f;

	private boolean isCanScroll = false;

	private float startY = 0f;
	private float startX = 0f;

	private boolean isDeliver = true;

	private int userTrackLastItem=-1;

	private IViewPagerScrollListener mIViewPagerScrollListener = null;

	public IndexViewPager(Context context) {
		super(context);
	}

	public IndexViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setScanScroll(boolean isCanScroll) {
		this.isCanScroll = isCanScroll;
	}

	public void setmIViewPagerScrollListener(IViewPagerScrollListener mIViewPagerScrollListener) {
		this.mIViewPagerScrollListener = mIViewPagerScrollListener;
	}

	@Override
	public void scrollTo(int x, int y) {
		super.scrollTo(x, y);
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		if (isCanScroll) {
			return super.onTouchEvent(arg0);
		} else {
			return false;
		}

	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		if (isCanScroll) {
			// 控制viewPager自己的滑动
			switch (arg0.getAction()) {
			case MotionEvent.ACTION_DOWN:
				startX = arg0.getX();
				startY = arg0.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				float endX = arg0.getX();
				float endY = arg0.getY();
				if (Math.abs(endY - startY) > Math.abs(endX - startX)
						&& Math.abs(endY - startY) > RANGE) {
					// Log.e("onInterceptTouchEvent", "scroll");
					if (mIViewPagerScrollListener != null) {
						if (endY > startY) {
							isDeliver = mIViewPagerScrollListener.scrollToDown();
						} else {
							isDeliver = mIViewPagerScrollListener.scrollToUp();
						}
					} else {
						isDeliver = true;
					}
				} else {
					// Log.e("onInterceptTouchEvent", "no scroll");
					isDeliver = true;
				}
				break;
			}
			if (isDeliver) {
				return super.onInterceptTouchEvent(arg0);
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @author HEcom
	 * 
	 */
	public interface IViewPagerScrollListener {

		boolean scrollToUp();

		boolean scrollToDown();
	}
}
