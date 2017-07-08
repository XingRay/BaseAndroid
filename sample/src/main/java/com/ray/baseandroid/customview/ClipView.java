package com.ray.baseandroid.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.ray.baseandroid.R;

/**
 * Author      : leixing
 * Date        : 17-7-2 下午12:14
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * ×
 * Description : xxx
 */
public class ClipView extends View{

    private Bitmap mBitmap;
    private Paint mPaint;
    private Path mPath;

    public ClipView(Context context) {
        this(context, null);
    }

    public ClipView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_dog);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.clipRect(new Rect(20, 50, 120, 150));
        mPath.reset();
        mPath.addCircle(130, 90, 50, Path.Direction.CCW);
        canvas.clipPath(mPath, Region.Op.UNION);
        canvas.drawBitmap(mBitmap, 0,0,null);
    }
}
