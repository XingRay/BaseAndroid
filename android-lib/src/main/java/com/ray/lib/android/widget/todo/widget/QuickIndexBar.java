package com.ray.lib.android.widget.todo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ray.lib.android.util.ViewUtil;

/**
 * Author      : leixing
 * Date        : 2016-12-22
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : 快速导航栏
 */
public class QuickIndexBar extends View {
    private ActionListener mListener;
    private String[] letters = {"↑", "☆", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
            "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    private int selectedLetterIndex = -1;// 选中
    private Paint paint = new Paint();

    public QuickIndexBar(Context context) {
        this(context, null);
    }

    public QuickIndexBar(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public QuickIndexBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 设置快速导航栏上展示的字符
     *
     * @param letters
     */
    public void setLetters(String[] letters) {
        this.letters = letters;
    }

    /**
     * 设置快速导航栏的活动监听器
     *
     * @param actionListener
     */
    public void setActionListener(ActionListener actionListener) {
        this.mListener = actionListener;
    }

    /**
     * 重写这个方法
     */
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 获取焦点改变背景颜色.
        int height = getHeight();// 获取对应高度
        int width = getWidth(); // 获取对应宽度
        int singleHeight = 0;// 获取每一个字母的高度
        int tempMarginTopDistance = 0;
        if (letters.length < 13) {//13是26个字母的一半的数值
            singleHeight = height / 2 / letters.length;
            tempMarginTopDistance = height / 4;
        } else {
            singleHeight = height / letters.length;
        }

        for (int i = 0; i < letters.length; i++) {
            // paint.setColor(Color.rgb(33, 65, 98));
            paint.setColor(Color.parseColor("#999999"));
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setAntiAlias(true);
            paint.setTextSize(ViewUtil.dp2px(getContext(), 10));
            // 选中的状态
            if (i == selectedLetterIndex) {
                paint.setColor(Color.parseColor("#3399ff"));
                paint.setFakeBoldText(true);
            }
            // x坐标等于中间-字符串宽度的一半.
            float xPos = width / 2 - paint.measureText(letters[i]) / 2;
            float yPos = tempMarginTopDistance + singleHeight * i + singleHeight;
            canvas.drawText(letters[i], xPos, yPos, paint);
            paint.reset();// 重置画笔
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();// 点击y坐标
        final int lastSelectedLetterIndex = selectedLetterIndex;
        int tmpSelectedLetterIndex = 0;
        int tempMarginTopDistance = 0;
        int height = getHeight();

        //根据字符总数重新计算导航条的高度
        if (letters.length < 13) {
            tempMarginTopDistance = height / 4;
            if (y <= tempMarginTopDistance || y >= height / 2 + height / 4) {
                return true;
            } else {
                tmpSelectedLetterIndex = (int) ((y - tempMarginTopDistance) / (height / 2) * letters.length);
            }
        } else {
            tmpSelectedLetterIndex = (int) (y / height * letters.length);// 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.
        }

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (mListener != null) {
                    mListener.onTouch();
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (lastSelectedLetterIndex != tmpSelectedLetterIndex) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    if (tmpSelectedLetterIndex >= 0 && tmpSelectedLetterIndex < letters.length) {
                        if (mListener != null) {
                            mListener.onTouchingLetterChanged(letters[tmpSelectedLetterIndex]);
                        }
                        selectedLetterIndex = tmpSelectedLetterIndex;
                        invalidate();
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                selectedLetterIndex = -1;//
                invalidate();
                if (mListener != null) {
                    mListener.onLeave();
                }
                break;

            default:
        }
        return true;
    }

    /**
     * 快速导航条的事件监听接口
     */
    public interface ActionListener {
        /**
         * 触摸的字符串发生改变的事件
         *
         * @param s
         */
        void onTouchingLetterChanged(String s);

        /**
         * 触摸到快速导航条的事件
         */
        void onTouch();

        /**
         * 离开快速导航条的事件
         */
        void onLeave();
    }

}
