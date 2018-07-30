package com.ray.lib.android.widget.todo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.IntDef;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leixing
 * on 2016-10-31.
 * Email : leixing1012@qq.com
 */
// TODO: 2016-10-31 待完成
public class _QuickIndexBar extends View {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private static final int DEFAULT_SELECTED_INDEX_COLOR = 0xff3399ff;
    private static final int DEFAULT_INDEX_COLOR = 0xff999999;
    private final Context mContext;
    private int selectedIndexColor = DEFAULT_SELECTED_INDEX_COLOR;
    private int indexTextSize;
    @OrientationMode
    private int orientation = VERTICAL;
    private List<Index> indexes;
    private int selectedIndexPosition = -1;
    private SideBar.OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    private Paint paint;

    public _QuickIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        paint = new Paint();
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setAntiAlias(true);

    }

    public _QuickIndexBar(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public _QuickIndexBar(Context context) {
        this(context, null);
    }

    public _QuickIndexBar setOrientation(@OrientationMode int orientation) {
        this.orientation = orientation;
        return this;
    }

    public _QuickIndexBar setIndexes(List indexes) {
        this.indexes = indexes;
        return this;
    }

    public _QuickIndexBar setIndexText(String[] texts) {
        if (texts == null || texts.length == 0) {
            return this;
        }

        if (indexes == null || indexes.size() != texts.length) {
            indexes = new ArrayList<>(texts.length);
            for (int i = 0; i < indexes.size(); i++) {
                indexes.add(new Index());
            }
        }

        for (int i = 0; i < texts.length; i++) {
            Index index = indexes.get(i);
            if (index == null) {
                continue;
            }
            index.setText(texts[i]);
        }

        return this;
    }

    public _QuickIndexBar setIndexText(int position, String text) {
        if (indexes == null || indexes.size() == 0) {
            return this;
        }

        if (position < 0 || position >= indexes.size()) {
            return this;
        }

        Index index = indexes.get(position);
        if (index == null) {
            return this;
        }
        index.setText(text);

        return this;
    }

    public _QuickIndexBar setIndexColor(int[] colors) {
        if (colors == null || colors.length == 0) {
            return this;
        }

        if (indexes == null || indexes.size() != colors.length) {
            indexes = new ArrayList<>(colors.length);
            for (int i = 0; i < indexes.size(); i++) {
                indexes.add(new Index());
            }
        }

        for (int i = 0; i < colors.length; i++) {
            Index index = indexes.get(i);
            if (index == null) {
                continue;
            }
            index.setColor(colors[i]);
        }

        return this;
    }

    public _QuickIndexBar setIndexColor(int position, int color) {
        if (indexes == null || indexes.size() == 0) {
            return this;
        }

        if (position < 0 || position >= indexes.size()) {
            return this;
        }

        Index index = indexes.get(position);
        if (index == null) {
            return this;
        }
        index.setColor(color);

        return this;
    }

    public _QuickIndexBar setSelectedIndexColor(int color) {
        this.selectedIndexColor = color;
        return this;
    }

    public _QuickIndexBar setSelectedIndexPosition(int position) {
        this.selectedIndexPosition = position;
        return this;
    }

    public _QuickIndexBar setIndexTextSize(int dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        this.indexTextSize = (int) (dp * scale + 0.5f);

        return this;
    }

    protected void onDraw(Canvas canvas) {
        if (indexes == null || indexes.size() == 0) {
            return;
        }

        super.onDraw(canvas);

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int innerHeight = getHeight() - paddingTop - paddingBottom;
        int innerWidth = getWidth() - paddingLeft - paddingRight;

        int singleIndexHeight = 0;
        int singleIndexWidth = 0;
        int indexCount = indexes.size();

        if (indexTextSize == 0) {
            setIndexTextSize(10);
        }

        for (int i = 0; i < indexCount; i++) {
            Index index = indexes.get(i);
            if (index == null) {
                continue;
            }

            String text = index.getText();
            if (TextUtils.isEmpty(text)) {
                continue;
            }

            int color = 0xffffffff;
            if (i == selectedIndexPosition) {
                color = selectedIndexColor;
                paint.setFakeBoldText(true);
            } else {
                color = index.getColor();
            }

            paint.setColor(color);

            float xPos = 0f;
            float yPos = 0f;

           /* if (orientation == HORIZONTAL) {
                xPos = paddingLeft + (innerWidth * i) / indexCount;
                yPos = paddingTop+innerHeight*i;

                Rect bounds = new Rect();
                paint.getTextBounds(text, 0, text.length(), bounds);
                Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
                int baseline = (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;



            } else {
                xPos = innerWidth / 2 - paint.measureText(text) / 2;
                yPos = tempMarginTopDistance + singleIndexHeight * i + singleIndexHeight;
            }
            // x坐标等于中间-字符串宽度的一半.

            canvas.drawText(b[i], xPos, yPos, paint);
            paint.reset();// 重置画笔*/
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        /*final int action = event.getAction();
        final float y = event.getY();// 点击y坐标
        final int oldChoose = selectedIndexPosition;
        final SideBar.OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        int c = 0;
        int tempMarginTopDistance = 0;
        int height = getHeight();
        if (b.length < 13) {
            tempMarginTopDistance = height / 4;
            if (y <= tempMarginTopDistance || y >= height / 2 + height / 4) {
                return true;
            } else {
                c = (int) ((y - tempMarginTopDistance) / (height / 2) * b.length);
            }
        } else {
            c = (int) (y / height * b.length);// 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.
        }

        switch (action) {
            case MotionEvent.ACTION_UP:
//			setBackgroundDrawable(new ColorDrawable(0x00000000));
                selectedIndexPosition = -1;//
                invalidate();
                if (mTextDialog != null) {
                    mTextDialog.setVisibility(View.INVISIBLE);
                }
                break;

            default:
//			setBackgroundResource(R.drawable.sidebar_background);
                if (oldChoose != c) {
                    getParentDepartmentByCode().requestDisallowInterceptTouchEvent(true);
                    if (c >= 0 && c < b.length) {
                        if (listener != null) {
                            listener.onTouchingLetterChanged(b[c]);
                        }
                        if (mTextDialog != null) {
                            mTextDialog.setText(b[c]);
                            mTextDialog.setVisibility(View.VISIBLE);
                            UserTrack.click("zmsx");
                        }

                        selectedIndexPosition = c;
                        invalidate();
                    }
                }

                break;
        }*/
        return true;
    }

    /**
     * 向外公开的方法
     *
     * @param onTouchingLetterChangedListener
     */
    public void setOnTouchingLetterChangedListener(SideBar.OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    @IntDef({HORIZONTAL, VERTICAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface OrientationMode {
    }

    /**
     * 接口
     *
     * @author coder
     */
    public interface OnTouchingLetterChangedListener {
        void onTouchingLetterChanged(String s);
    }

    private static class Index {
        private String text;
        private int color;

        public Index() {
            this.color = DEFAULT_INDEX_COLOR;
            this.text = "";
        }

        public Index(String text, int color) {
            this.text = text;
            this.color = color;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "Index{" +
                    "color=" + color +
                    ", text='" + text + '\'' +
                    '}';
        }
    }
}
