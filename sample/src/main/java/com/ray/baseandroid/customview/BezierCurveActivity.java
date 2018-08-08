package com.ray.baseandroid.customview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.BaseActivity;
import com.ray.lib.android.util.CustomViewUtil;
import com.ray.lib.android.util.StringUtil;
import com.ray.lib.java.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author      : leixing
 * @date        : 2017/6/18 0:27
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * Description : xxx
 */

public class BezierCurveActivity extends BaseActivity {
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.et_num)
    EditText etNum;
    private int mWidth;
    private int mHeight;

    @Override
    protected void initVariables() {
        mWidth = 768;
        mHeight = 920;
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_bezier_curve);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.et_num, R.id.bt_load})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_load:
                load();
                break;
        }
    }

    private void load() {
        Bitmap bitmap = null;
        switch (getNumber()) {
            case 0:
                bitmap = drawBezierCurve();
                break;
            case 1:
                bitmap = draw2BezierCurve();
                break;
            case 2:
                bitmap = draw3BezierCurve();
                break;
            case 3:
                bitmap = drawPathOp(0);
                break;
            case 4:
                bitmap = drawPathOp(1);
                break;
            case 5:
                bitmap = drawPathOp(2);
                break;
            case 6:
                bitmap = drawPathOp(3);
                break;
            case 7:
                bitmap = drawPathOp(4);
                break;
            case 8:
                bitmap = drawText();
                break;
            case 9:
                bitmap = drawRandomCircle();
                break;
            case 10:
                bitmap = drawVerifyCode();
                break;
            default:
        }

        if (bitmap != null) {
            ivImg.setImageBitmap(bitmap);
        }
    }

    private int getNumber() {
        return StringUtil.toInt(etNum.getText().toString().trim());
    }

    private Bitmap drawBezierCurve() {
        Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        CustomViewUtil.drawCoordinate(canvas, paint, mWidth, mHeight);

        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.BLUE);
        Path path = new Path();
        path.moveTo(300, 300);
        path.quadTo(600, 100, 600, 600);
        canvas.drawPath(path, paint);

        paint.setStrokeWidth(8);
        paint.setColor(Color.RED);
        canvas.drawPoints(new float[]{300, 300, 600, 100, 600, 600}, paint);

        return bitmap;
    }

    private Bitmap draw2BezierCurve() {
        Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        CustomViewUtil.drawCoordinate(canvas, paint, mWidth, mHeight);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(2);
        Path path = new Path();
        path.moveTo(300, 300);
        path.cubicTo(600, 100, 300, 800, 600, 600);
        canvas.drawPath(path, paint);

        paint.setStrokeWidth(8);
        paint.setColor(Color.RED);
        canvas.drawPoints(new float[]{300, 300, 600, 100, 300, 800, 600, 600}, paint);

        return bitmap;
    }

    private Bitmap draw3BezierCurve() {
        Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        CustomViewUtil.drawCoordinate(canvas, paint, mWidth, mHeight);

        paint.setColor(Color.RED);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(50, 50);
        path.lineTo(300, 300);
        path.lineTo(25, 270);
        path.close();

        path.arcTo(new RectF(200, 100, 700, 600), 0, 90, true);
        canvas.drawPath(path, paint);

        return bitmap;
    }

    private Bitmap drawPathOp(int op) {
        Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        CustomViewUtil.drawCoordinate(canvas, paint, mWidth, mHeight);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);

        Path rectPath = new Path();
        rectPath.addRect(100, 100, 500, 500, Path.Direction.CW);

        Path circlePath = new Path();
        circlePath.addCircle(500, 500, 200, Path.Direction.CW);
        Path.Op pathOp;

        switch (op) {
            case 0:
                pathOp = Path.Op.DIFFERENCE;
                break;
            case 1:
                pathOp = Path.Op.INTERSECT;
                break;
            case 2:
                pathOp = Path.Op.REVERSE_DIFFERENCE;
                break;
            case 3:
                pathOp = Path.Op.UNION;
                break;
            case 4:
                pathOp = Path.Op.XOR;
                break;
            default:
                pathOp = null;
        }

        if (pathOp != null) {
            rectPath.op(circlePath, pathOp);
        }

        canvas.drawPath(rectPath, paint);

        return bitmap;
    }

    private Bitmap drawText() {
        Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        CustomViewUtil.drawCoordinate(canvas, paint, mWidth, mHeight);

        String text = "aaabbbcccdddeeefffggg";
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(80);
        paint.setStrokeWidth(1);
        canvas.drawText(text, 100, 100, paint);


        Path path = new Path();
        path.moveTo(100, 400);
        path.quadTo(700, 100, 100, 900);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(160);
        paint.setStrokeWidth(5);
        canvas.drawTextOnPath(text, path, 0, 0, paint);


        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);


        return bitmap;
    }

    private Bitmap drawRandomCircle() {
        Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        CustomViewUtil.drawCoordinate(canvas, paint, mWidth, mHeight);
        paint.setStrokeWidth(1);
        paint.setAntiAlias(true);

        int radius = 20;
        int diameter = radius << 1;
        for (int x = radius; x < mWidth + radius; x += diameter) {
            for (int y = radius; y < mHeight + radius; y += diameter) {
                paint.setColor(RandomUtil.getRandomInt() | 0xff000000);
                canvas.drawCircle(x, y, radius, paint);
            }
        }

        return bitmap;
    }

    private Bitmap drawVerifyCode() {
        Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        CustomViewUtil.drawCoordinate(canvas, paint, mWidth, mHeight);

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawRoundRect(new RectF(100, 100, 500, 200), 10, 10, paint);

        int numberCount = 4;
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(4);
        paint.setTextSize(50);
        for(int i=0; i<numberCount; i++){
            int randomInt = RandomUtil.getRandomInt(0, 9);
            canvas.drawText(""+randomInt, 100+i*100+25, 175, paint);
        }

        int lineNumber = 6;
        List<Float> points = new ArrayList<>();
        for(int i=0; i<lineNumber; i++){
            int fromY = RandomUtil.getRandomInt(100, 200);
            int toY = RandomUtil.getRandomInt(100, 200);
            points.add(100f);
            points.add((float) fromY);
            points.add(500f);
            points.add((float) toY);
        }
        float[] fPoints = new float[points.size()];
        for(int i=0; i<points.size();i++){
            fPoints[i]= points.get(i);
        }
        paint.setStrokeWidth(4);
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawLines(fPoints, paint);

        return bitmap;
    }
}

