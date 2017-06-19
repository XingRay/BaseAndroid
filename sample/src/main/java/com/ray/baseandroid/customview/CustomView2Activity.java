package com.ray.baseandroid.customview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.BaseActivity;
import com.ray.lib.android.util.StringUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author      : leixing
 * Date        : 2017/6/18 22:47
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class CustomView2Activity extends BaseActivity {
    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.et_num)
    EditText etNum;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_custom_view2);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.et_num, R.id.bt_load})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_load:
                load(StringUtil.toInt(etNum.getText().toString().trim()));
                break;
        }
    }

    private void load(int num) {
        flContainer.removeAllViews();
        View view = null;
        switch (num) {
            case 0:
                view = loadMoveBall();
                break;
            case 1:
                view = loadCoordinateView();
            default:
        }

        if (view != null) {
            flContainer.addView(view);
        }
    }

    private View loadMoveBall() {
        final MoveBallView moveBallView = new MoveBallView(mContext);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
        moveBallView.setLayoutParams(layoutParams);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                moveBallView.postInvalidate();
            }
        }, 200, 50);

        return moveBallView;
    }

    private View loadCoordinateView() {
        final CoordinateView movecoordinateViewallView = new CoordinateView(mContext);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1000);
        movecoordinateViewallView.setLayoutParams(layoutParams);

        return movecoordinateViewallView;
    }
}
