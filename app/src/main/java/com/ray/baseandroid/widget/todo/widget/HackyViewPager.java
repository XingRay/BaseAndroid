package com.ray.baseandroid.widget.todo.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Hacky fix for Issue #4 and
 * http://code.google.com/p/android/issues/detail?id=18990
 * 
 * ScaleGestureDetector seems to mess up the touch events, which means that
 * ViewGroups which make use of onInterceptTouchEvent throw a lot of
 * IllegalArgumentException: pointerIndex out of range.
 * 
 * There's not much I can do in my code for now, but we can mask the result by
 * just catching the problem and ignoring it.
 * 
 * @author Chris Banes
 */
public class HackyViewPager extends ViewPager {

	private boolean mTouchEnabled = true;

	private static final String TAG = "HackyViewPager";

	public HackyViewPager(Context context) {
		super(context);
	}

	public HackyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if(!mTouchEnabled) return false;
		try {
			return super.onInterceptTouchEvent(ev);
		} catch (IllegalArgumentException e) {
			// 不理会
			return false;
		} catch (ArrayIndexOutOfBoundsException e) {
			// 不理会
			return false;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		return mTouchEnabled && super.onTouchEvent(ev);
	}

    public void setmTouchEnabled(boolean mTouchEnabled) {
        this.mTouchEnabled = mTouchEnabled;
    }
}
