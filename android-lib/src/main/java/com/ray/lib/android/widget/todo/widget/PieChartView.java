package com.ray.lib.android.widget.todo.widget;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * 饼状统计图
 */
public class PieChartView extends View {
    /**
     * 默认动画持续的时间
     */
    private final static int DEFAULT_DURATION = 2000;
    private static final int IDLE_ITEM_COLOR_DEFAULT = Color.parseColor("#eae6e8");
    private static final int SPECIAL_ITEM_EXTRA_LENGTH_RATIO_DEFAULT = 5;
    private static final int SUM_DEFAULT = 100;
    private static final float START_ANGLE_DEFAULT = -90.0f;
    private static final float CIRCLE_ANGLE = 360.0f;

    /**
     * 空条目的颜色
     */
    private int idleItemColor = IDLE_ITEM_COLOR_DEFAULT;

    /**
     * 特殊条目的额外长度占整个半径长度的比例
     */
    private int specialItemExtraLengthRatio = SPECIAL_ITEM_EXTRA_LENGTH_RATIO_DEFAULT;
    /**
     * 原始的条目数据
     */
    private List<PieChartItem> items;

    /**
     * 用于展示的条目数据
     */
    private List<PieChartItem> showItems;

    /**
     * 起始条目的角度
     */
    private float startAngle;

    /**
     * 外部(特殊)条目的边框矩形
     */
    private RectF outerChartRect;

    /**
     * 内部(普通)条目的边框矩形
     */
    private RectF innerChartRect;
    private Paint paint;

    /**
     * 展示动画的持续时间
     */
    private int duration;

    private TimeInterpolator timeInterpolator;

    public PieChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        outerChartRect = new RectF();
        innerChartRect = new RectF();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        items = new ArrayList<PieChartItem>();
        startAngle = START_ANGLE_DEFAULT;
    }

    public PieChartView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public PieChartView(Context context) {
        this(context, null);
    }

    /**
     * 设置动画的持续时间
     *
     * @param duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * 设置展示的数据
     *
     * @param items
     */
    public void setChartItems(List<PieChartItem> items) {
        this.items = items;
        this.showItems = items;
    }

    /**
     * 设置动画插值器
     *
     * @param interpolator
     */
    public void setInterpolator(TimeInterpolator interpolator) {
        this.timeInterpolator = interpolator;
    }

    /**
     * 开始动画
     */
    public void startAnimation() {
        ValueAnimator objectAnimator = ValueAnimator.ofFloat(0f, 1.0f);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float floatValue = (float) (animation.getAnimatedValue());
                draw(floatValue);
            }
        });
        if (timeInterpolator == null) {
            objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        } else {
            objectAnimator.setInterpolator(timeInterpolator);
        }
        if (duration == 0) {
            objectAnimator.setDuration(DEFAULT_DURATION);
        } else {
            objectAnimator.setDuration(duration);
        }
        objectAnimator.start();
    }

    /**
     * 根据原始原始数据和当前的时间插值来计算展示数据
     *
     * @param value
     */
    private void draw(float value) {
        int sum = getSumFromItems(items);
        int preItemShareSum = 0;
        int currentItemShareSum = 0;
        showItems = new ArrayList<PieChartItem>();

        for (PieChartItem item : items) {
            if (item == null) {
                continue;
            }
            currentItemShareSum += item.getShare();
            int currentShareSum = (int) (value * sum);

            if (currentShareSum >= currentItemShareSum) {
                showItems.add(item);
            } else {
                showItems.add(new PieChartItem(item.isSpecial(), item.getColor(), currentShareSum - preItemShareSum));
                showItems.add(new PieChartItem(false, getDrawingCacheBackgroundColor(), sum - currentShareSum));
                break;
            }
            preItemShareSum += item.getShare();
        }

        refresh();
    }

    private int getSumFromItems(List<PieChartItem> items) {
        int sum = 0;
        for (PieChartItem item : items) {
            if (item != null) {
                sum += item.getShare();
            }
        }

        return sum;
    }

    /**
     * 获取起始角度
     *
     * @return
     */
    public float getStartAngle() {
        return startAngle;
    }

    /**
     * 设置第一个条目角度
     *
     * @param startAngle
     */
    public void setStartAngle(float startAngle) {
        this.startAngle = startAngle;
    }

    /**
     * 获取特殊条目的额外长度占半径的比例
     *
     * @return
     */
    public int getSpecialItemExtraLengthRatio() {
        return specialItemExtraLengthRatio;
    }

    /**
     * 设置特殊条目的额外长度占半径的比例
     *
     * @param specialItemExtraLengthRatio
     * @return
     */
    public PieChartView setSpecialItemExtraLengthRatio(int specialItemExtraLengthRatio) {
        if (specialItemExtraLengthRatio >= 0 && specialItemExtraLengthRatio <= 100) {
            this.specialItemExtraLengthRatio = specialItemExtraLengthRatio;
        }
        return this;
    }

    /**
     * 刷新UI
     */
    public void refresh() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int height = getHeight() - paddingTop - paddingBottom;
        int width = getWidth() - paddingLeft - paddingRight;
        int chartDiameter;
        int innerPadding;
        int specialItemExtraLength;

        if (showItems == null) {
            return;
        }

        if (height < width) {
            chartDiameter = height;
            specialItemExtraLength = chartDiameter * specialItemExtraLengthRatio / 100;
            innerPadding = (width - height) / 2;
            outerChartRect.set(innerPadding, 0, innerPadding + chartDiameter, chartDiameter);
            innerChartRect.set(innerPadding + specialItemExtraLength, specialItemExtraLength, innerPadding + chartDiameter - specialItemExtraLength, chartDiameter - specialItemExtraLength);
        } else {
            chartDiameter = width;
            specialItemExtraLength = chartDiameter * specialItemExtraLengthRatio / 100;
            innerPadding = (height - width) / 2;
            outerChartRect.set(0, innerPadding, chartDiameter, innerPadding + chartDiameter);
            innerChartRect.set(specialItemExtraLength, innerPadding + specialItemExtraLength, chartDiameter - specialItemExtraLength, innerPadding + chartDiameter - specialItemExtraLength);
        }

        if (chartDiameter < 2 * specialItemExtraLength) {
            return;
        }

        int specialItemRadius = chartDiameter / 2;
        int normalItemRadius = specialItemRadius - specialItemExtraLength;

        if (specialItemRadius <= 0 || normalItemRadius <= 0) {
            return;
        }

        canvas.save();
        canvas.translate(paddingLeft, paddingTop);
        canvas.clipRect(0, 0, width, height);
        float startAng = this.startAngle;
        int sum = getSumFromItems(showItems);
        if (sum <= 0) {
            return;
        }

        for (PieChartItem item : showItems) {
            if (item == null) {
                continue;
            }

            paint.setColor(item.getColor());
            float angle = item.getShare() * CIRCLE_ANGLE / sum;
            if (angle > 0) {
                if (item.isSpecial) {
                    canvas.drawArc(outerChartRect, startAng, angle, true, paint);
                } else {
                    canvas.drawArc(innerChartRect, startAng, angle, true, paint);
                }
                startAng += angle;
            }
        }

        if (startAng < this.startAngle + CIRCLE_ANGLE) {
            paint.setColor(idleItemColor);
            canvas.drawArc(innerChartRect, startAng, this.startAngle + CIRCLE_ANGLE - startAng, true, paint);
        }

        canvas.restore();
    }

    /**
     * 条目数据
     */
    public static class PieChartItem {
        /**
         * 该条目展示的颜色
         */
        private int color;
        /**
         * 该条目所占的份额
         */
        private int share;

        /**
         * 该条目是否是特殊条目, 特殊条目在展示时会有部分的额外长度
         */
        private boolean isSpecial;

        public PieChartItem(boolean isSpecial, int color, int share) {
            this.isSpecial = isSpecial;
            this.color = color;
            this.share = share;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public boolean isSpecial() {
            return isSpecial;
        }

        public void setSpecial(boolean special) {
            isSpecial = special;
        }
    }
}
