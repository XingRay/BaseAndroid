package com.ray.baseandroid.img;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author      : leixing
 * @date        : 2017-10-10
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class ImgTestActivity extends BaseActivity {
    @BindView(R.id.iv_img)
    ImageView ivImg;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_img_test);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {
        Glide.with(this).load("http://img1.imgtn.bdimg.com/it/u=594559231,2167829292&fm=27&gp=0.jpg").into(ivImg);
    }
}
