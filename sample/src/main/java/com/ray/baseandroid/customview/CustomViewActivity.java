package com.ray.baseandroid.customview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.BaseActivity;
import com.ray.lib.android.util.CustomViewUtil;
import com.ray.lib.android.util.StringUtil;
import com.ray.lib.android.util.TraceUtil;
import com.ray.lib.android.util.ViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author      : leixing
 * Date        : 2017-06-15
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class CustomViewActivity extends BaseActivity {
    @BindView(R.id.iv_image)
    ImageView ivImage;

    @BindView(R.id.et_num)
    EditText etNumber;
    private int width;
    private int height;

    @Override
    protected void initVariables() {
        width = 1080;
        height = 1600;
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_custom_view);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.iv_image, R.id.bt_load})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_load:
                loadImg();
                break;
        }
    }

    private void loadImg() {
        Bitmap bitmap = null;
        switch (getNumber()) {
            case 0:
                bitmap = drawBitmap();
                break;
            case 1:
                bitmap = drawImg();
                break;
            case 2:
                bitmap = drawPoints();
                break;
            case 3:
                bitmap = drawLines();
                break;
            case 4:
                bitmap = drawRect();
                break;
            case 5:
                bitmap = drawOval();
                break;
            case 6:
                bitmap = drawCircle();
                break;
            case 7:
                bitmap = drawArc();
                break;
            case 8:
                bitmap = drawPath();
                break;
            case 9:
                bitmap = drawText();
                break;
            default:
        }

        ivImage.setImageBitmap(bitmap);
    }

    private int getNumber() {
        return StringUtil.toInt(etNumber.getText().toString().trim(), 0);
    }

    private Bitmap drawBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(500, 800, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(ViewUtil.sp2px(mContext, 16));
        paint.setTextSkewX(0.5f);
        paint.setUnderlineText(true);
        paint.setFakeBoldText(true);

        canvas.drawText("自定义控件 test01", 50, 100, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(20);
        paint.setStrokeJoin(Paint.Join.BEVEL);

        canvas.drawRect(30, 200, 350, 350, paint);

        return bitmap;
    }

    private Bitmap drawImg() {
        Bitmap bitmap = Bitmap.createBitmap(500, 800, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();

        Bitmap img = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        int width = img.getWidth();
        int height = img.getHeight();
        canvas.drawBitmap(img, 0, 0, paint);
        Rect src = new Rect(0, 0, width >> 1, height >> 1);
        Rect dst = new Rect(0, height, width * 3, height + height * 3);
        canvas.drawBitmap(img, src, dst, paint);

        return bitmap;
    }

    private Bitmap drawPoints() {
        Bitmap bitmap = Bitmap.createBitmap(600, 800, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStrokeWidth(15);

        paint.setColor(Color.BLUE);
        canvas.drawPoint(100, 100, paint);

        paint.setColor(Color.RED);
        float[] points = new float[]{153.5f, 534.5f, 453.5f, 546.5f};
        canvas.drawPoints(points, paint);

        paint.setColor(Color.YELLOW);
        points = new float[]{153.5f, 12.4f, 123.4f, 534.5f, 453.5f, 546.5f, 326.3f, 235.6f, 45.7f, 23.56f};
        canvas.drawPoints(points, 1, 8, paint);

        return bitmap;

    }

    private Bitmap drawLines() {
        int width = 1080;
        int height = 1400;
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        CustomViewUtil.drawCoordinate(canvas, paint, width, height);
        paint.setStrokeWidth(6);

        paint.setColor(Color.RED);
        canvas.drawLine(0, 0, 100, 100, paint);

        paint.setColor(Color.BLUE);
        float[] points = {100, 50, 100, 100, 100, 100, 200, 100, 200, 100, 200, 50};
        canvas.drawLines(points, paint);

        paint.setColor(Color.YELLOW);
        points = new float[]{1, 1, 1, 10, 50, 100, 50, 150, 50, 150, 130, 130, 130, 130, 280, 500, 280, 500, 500, 50};
        canvas.drawLines(points, 4, 16, paint);

        return bitmap;
    }


    private Bitmap drawRect() {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        CustomViewUtil.drawCoordinate(canvas, paint, width, height);

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(5);
        canvas.drawRect(120, 120, 230, 450, paint);

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(new Rect(250, 100, 500, 400), paint);

        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(new RectF(100, 600, 600, 900), 60, 60, paint);
        return bitmap;
    }

    private Bitmap drawOval() {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        CustomViewUtil.drawCoordinate(canvas, paint, width, height);

        paint.setColor(Color.RED);
        canvas.drawOval(new RectF(100, 100, 400, 600), paint);

        return bitmap;
    }

    private Bitmap drawCircle() {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        CustomViewUtil.drawCoordinate(canvas, paint, width, height);

        paint.setColor(Color.RED);
        canvas.drawCircle(600, 600, 300, paint);
        return bitmap;
    }

    private Bitmap drawArc() {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        CustomViewUtil.drawCoordinate(canvas, paint, width, height);

        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.RED);
        canvas.drawArc(new RectF(100, 100, 500, 400), 30, 30, false, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        canvas.drawArc(new RectF(600, 100, 1000, 400), 30, -60, false, paint);

        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.YELLOW);
        canvas.drawArc(new RectF(100, 500, 500, 800), 150, 60, true, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);
        canvas.drawArc(new RectF(600, 500, 1000, 800), 150, -60, true, paint);

        return bitmap;
    }

    private Bitmap drawPath() {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        CustomViewUtil.drawCoordinate(canvas, paint, width, height);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.RED);

        Path path = new Path();
        path.moveTo(26, 235);
        path.lineTo(243, 226);
        path.lineTo(320, 23);
        path.lineTo(384, 229);
        path.lineTo(616, 231);
        path.lineTo(438, 362);
        path.lineTo(498, 570);
        path.lineTo(318, 449);
        path.lineTo(136, 569);
        path.lineTo(203, 361);
        path.close();

        path.addRect(new RectF(100, 600, 500, 900), Path.Direction.CW);
        path.addRoundRect(new RectF(600, 600, 1000, 900),
                new float[]{10f, 20f, 30f, 40, 50, 60, 70, 80}, Path.Direction.CCW);
        path.addOval(new RectF(700, 10, 1000, 400), Path.Direction.CW);
        path.addCircle(500, 400, 300, Path.Direction.CCW);
        path.addArc(new RectF(100, 100, 800, 1500), 90, 360);

        canvas.drawPath(path, paint);


        return bitmap;
    }

    private Bitmap drawText() {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        CustomViewUtil.drawCoordinate(canvas, paint, width, height);
        paint.setTextSize(50);

        int baseLine = 300;

        paint.setColor(Color.BLACK);
        String text = "Hello, boy";
        canvas.drawText(text, 100, baseLine, paint);

        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        TraceUtil.log("top = "+fontMetrics.top +"\n"
        +"ascent = "+fontMetrics.ascent+"\n"
        +"descent = "+fontMetrics.descent+"\n"
        +"bottom = "+fontMetrics.bottom+"\n"
        +"leading = "+fontMetrics.leading);

        float textWidth = paint.measureText(text);
        TraceUtil.log(textWidth);

        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        TraceUtil.log("left = "+rect.left+"\n"
        +"top = "+rect.top+"\n"
                        +"right = "+rect.right+"\n"
                        +"bottom = "+rect.bottom+"\n"
        );

        paint.setColor(Color.RED);
        canvas.drawLine(100, baseLine, 100+textWidth, baseLine, paint);


        return bitmap;
    }
}