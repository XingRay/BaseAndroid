package com.ray.lib.android.widget.data;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class PieChartView extends View {
    private static final int BACKGROUND_COLOR_DEFAULT = Color.WHITE;
    private static final int IDLE_ITEM_COLOR_DEFAULT = Color.GRAY;
    private static final int SPECIAL_ITEM_EXTRA_LENGTH_RATIO_DEFAULT = 5;
    private static final int SUM_DEFAULT = 100;
    private static final float START_ANGLE_DEFAULT = -90.0f;
    private static final float CIRCLE_ANGLE = 360.0f;

    private int backgroundColor = BACKGROUND_COLOR_DEFAULT;
    private int idleItemColor = IDLE_ITEM_COLOR_DEFAULT;
    private int specialItemExtraLengthRatio = SPECIAL_ITEM_EXTRA_LENGTH_RATIO_DEFAULT;
    private List<PieChartItem> items;
    private int sum;
    private float startAngle;
    private RectF outerChartRect;
    private RectF innerChartRect;
    private Paint paint;

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

    public int getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public float getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(float startAngle) {
        this.startAngle = startAngle;
    }

    public int getSpecialItemExtraLengthRatio() {
        return specialItemExtraLengthRatio;
    }

    public PieChartView setSpecialItemExtraLengthRatio(int specialItemExtraLengthRatio) {
        if (specialItemExtraLengthRatio >= 0 && specialItemExtraLengthRatio <= 100) {
            this.specialItemExtraLengthRatio = specialItemExtraLengthRatio;
        }
        return this;
    }

    public PieChartView addItem(PieChartItem item) {
        if (items == null || item == null) {
            return this;
        }

        items.add(item);
        sum += item.getShare();
        calcItemsAngle();
        return this;
    }

    public PieChartView deleteItem(int index) {
        if (items == null || index < 0 || index >= items.size()) {
            return this;
        }

        items.remove(index);

        return this;
    }

    public int getSum() {
        return sum;
    }

    public PieChartView setSum(int sum) {
        if (sum <= 0) {
            sum = 0;
        }
        this.sum = sum;
        calcItemsAngle();

        return this;
    }

    public int sumDelete(int value) {
        if (value < 0) {
            return sumAdd(-value);
        }

        if (this.sum >= value) {
            this.sum -= value;
        } else {
            value = this.sum;
            this.sum = 0;
        }

        calcItemsAngle();

        return value;
    }

    public int sumAdd(int value) {
        if (value < 0) {
            return sumDelete(-value);
        }

        this.sum += value;

        calcItemsAngle();

        return value;
    }

    public int itemShareDelete(int index, int value) {
        if (index >= items.size()) {
            return 0;
        }

        PieChartItem item = items.get(index);
        if (item == null) {
            return 0;
        }

        int shareDelete = item.shareDelete(value);
        sumDelete(shareDelete);

        return shareDelete;
    }

    public int itemShareAdd(int index, int value) {
        if (index >= items.size()) {
            return 0;
        }

        PieChartItem item = items.get(index);
        if (item == null) {
            return 0;
        }

        int shareAdd = item.shareAdd(value);
        sumAdd(shareAdd);

        return shareAdd;
    }

    private PieChartView calcItemsAngle() {
        if (items == null) {
            return null;
        }

        for (PieChartItem item : items) {
            item.setAngle(item.getShare() * CIRCLE_ANGLE / sum);
        }

        refresh();

        return this;
    }

    public void refresh() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            invalidate();
        } else {
            this.post(new Runnable() {
                @Override
                public void run() {
                    invalidate();
                }
            });
        }
    }

    public PieChartView setItem(int index, PieChartItem item) {
        if (items == null || item == null || index < 0 || index >= items.size()) {
            return this;
        }

        PieChartItem originalItem = items.get(index);
        if (originalItem != null) {
            sumDelete(originalItem.getShare());
        }

        items.set(index, item);
        sumAdd(item.getShare());
        refresh();

        return this;
    }

    public PieChartItem getItem(int index) {
        if (items == null || index < 0 || index >= items.size()) {
            return null;
        }

        return items.get(index);
    }

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

        if (items == null) {
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
        canvas.drawColor(backgroundColor);
        float startAng = this.startAngle;

        for (PieChartItem item : items) {
            if (item == null) {
                continue;
            }

            paint.setColor(item.getColor());
            float angle = item.getAngle();
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
            canvas.drawArc(innerChartRect, startAng, CIRCLE_ANGLE, true, paint);
        }

        canvas.restore();
    }

    public static class PieChartItem {
        private int color;
        private int share;
        private float angle;
        private boolean isSpecial;

        public PieChartItem(boolean isSpecial, int color, int share) {
            this.isSpecial = isSpecial;
            this.color = color;
            this.share = share;
        }

        private int getColor() {
            return color;
        }

        private void setColor(int color) {
            this.color = color;
        }

        private int getShare() {
            return share;
        }

        private void setShare(int share) {
            if (share < 0) {
                share = 0;
            }
            this.share = share;
        }

        private int shareAdd(int value) {
            if (value >= 0) {
                this.share += value;
                return value;
            } else {
                return shareDelete(-value);
            }
        }

        private int shareDelete(int value) {
            if (value >= 0) {
                if (this.share >= value) {
                    this.share -= value;
                } else {
                    value = this.share;
                    this.share = 0;
                }
                return value;
            } else {
                return shareAdd(-value);
            }
        }

        private float getAngle() {
            return angle;
        }

        private void setAngle(float angle) {
            if (angle < 0) {
                angle = 0;
            }
            this.angle = angle;
        }

        private boolean isSpecial() {
            return isSpecial;
        }

        private void setSpecial(boolean special) {
            isSpecial = special;
        }
    }
}
