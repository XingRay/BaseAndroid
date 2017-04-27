package com.ray.baseandroid.widget.todo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class GridViewInScrollView extends GridView {

	public GridViewInScrollView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public GridViewInScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public GridViewInScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
