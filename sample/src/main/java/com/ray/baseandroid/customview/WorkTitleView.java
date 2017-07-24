package com.ray.baseandroid.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ray.baseandroid.R;

/**
 * Author      : leixing
 * Date        : 2017-06-20
 * Email       : leixing1012@gmail.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class WorkTitleView extends RelativeLayout {

    public static final int DEFAULT_TITLE_COLOR = 0xff666666;
    public static final int DEFAULT_TITLE_SIZE = 16;

    public static final int DEFAULT_TYPE_SIZE = 8;
    public static final int DEFAULT_TYPE_COLOR = Color.GREEN;

    public static final int DEFAULT_NAME_CONTENT_COLOR = 0xff999999;
    public static final int DEFAULT_NAME_CONTENT_SIZE = 16;

    public static final int DEFAULT_TIME_COLOR = 0xff999999;
    public static final int DEFAULT_TIME_SIZE = 12;

    private TextView tvTitle;
    private TextView tvType;
    private TextView tvNameContent;
    private TextView tvTime;

    private String mName;
    private String mContent;
    private int mTypeBackgroundColor;

    public WorkTitleView(Context context) {
        this(context, null);
    }

    public WorkTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public WorkTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_work_titile, this, true);
        findViews();
        applyAttributes(context, attrs);
        post(new Runnable() {
            @Override
            public void run() {
                setTypeBackground();
            }
        });
    }

    private void setTypeBackground() {
        Bitmap bitmap = createTypeBackground(mTypeBackgroundColor, tvType.getMeasuredWidth(), tvType.getMeasuredHeight());
        tvType.setBackground(new BitmapDrawable(null, bitmap));
    }

    private void findViews() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvType = (TextView) findViewById(R.id.tv_type);
        tvNameContent = (TextView) findViewById(R.id.tv_name_content);
        tvTime = (TextView) findViewById(R.id.tv_time);
    }

    private void applyAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.work_title_view);
        if (typedArray == null) {
            return;
        }

        setTitle(typedArray);
        setType(typedArray);
        setNameContent(typedArray);
        setTime(typedArray);

        typedArray.recycle();
    }

    public void setTitle(TypedArray typedArray) {
        TextView textView = tvTitle;

        String title = typedArray.getString(R.styleable.work_title_view_title);
        title = nullToEmpty(title);
        textView.setText(title);

        int color = typedArray.getColor(R.styleable.work_title_view_type_color, DEFAULT_TITLE_COLOR);
        textView.setTextColor(color);

        float size = typedArray.getDimension(R.styleable.work_title_view_title_size, DEFAULT_TITLE_SIZE);
        textView.setTextSize(size);
    }

    private void setType(TypedArray typedArray) {
        TextView textView = tvType;

        String type = typedArray.getString(R.styleable.work_title_view_type);
        type = nullToEmpty(type);
        textView.setText(type);

        int color = typedArray.getColor(R.styleable.work_title_view_type_color, Color.WHITE);
        textView.setTextColor(color);

        float size = typedArray.getDimension(R.styleable.work_title_view_type_size, DEFAULT_TYPE_SIZE);
        textView.setTextSize(size);

        mTypeBackgroundColor = typedArray.getColor(R.styleable.work_title_view_type_background_color, DEFAULT_TYPE_COLOR);
    }

    private void setNameContent(TypedArray typedArray) {
        TextView textView = tvNameContent;

        String name = typedArray.getString(R.styleable.work_title_view_name);
        String content = typedArray.getString(R.styleable.work_title_view_content);
        textView.setText(makeNameContent(name, content));

        float size = typedArray.getDimension(R.styleable.work_title_view_name_content_size, DEFAULT_NAME_CONTENT_SIZE);
        textView.setTextSize(size);

        int color = typedArray.getColor(R.styleable.work_title_view_name_content_color, DEFAULT_NAME_CONTENT_COLOR);
        textView.setTextColor(color);
    }

    public void setName(String name) {
        name = nullToEmpty(name);
        mName = name;
        tvNameContent.setText(makeNameContent(mName, mContent));
    }

    public void setContent(String content) {
        content = nullToEmpty(content);
        mContent = content;
        tvNameContent.setText(makeNameContent(mName, mContent));
    }

    public void setTime(TypedArray typedArray) {
        TextView textView = tvTime;

        String text = typedArray.getString(R.styleable.work_title_view_time);
        text = nullToEmpty(text);
        textView.setText(text);

        float size = typedArray.getDimension(R.styleable.work_title_view_time_size, DEFAULT_TIME_SIZE);
        textView.setTextSize(size);

        int color = typedArray.getColor(R.styleable.work_title_view_time_color, DEFAULT_TIME_COLOR);
        textView.setTextColor(color);
    }

    private String makeNameContent(String name, String content) {
        name = nullToEmpty(name);
        content = nullToEmpty(content);
        return name + " | " + content;
    }

    private String nullToEmpty(String s) {
        return s == null ? "" : s;
    }

    private Bitmap createTypeBackground(int color, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRoundRect(new RectF(0, 0, width, height), height >> 2, height >> 2, paint);
        return bitmap;
    }
}
