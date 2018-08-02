package com.ray.baseandroid.sptest;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.BaseActivity;
import com.ray.lib.android.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @@author      : leixing
 * @@date        : 2017-06-02
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class SPTestActivity extends BaseActivity {
    @BindView(R.id.tv_content)
    TextView tvContent;

    @BindView(R.id.et_prefix)
    EditText etPrefix;

    private int mNumber;

    @Override
    protected void initVariables() {
        mNumber = 0;
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_sp_test);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.bt_load, R.id.bt_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_load:
                loadDataFromSP();
                break;
            case R.id.bt_save:
                saveDataToSP();
                break;
        }
    }

    private void loadDataFromSP() {
        String data = SPData.getInstance().loadData();
        tvContent.setText(data);
    }

    private void saveDataToSP() {
        SPData.getInstance().saveData(etPrefix.getText().toString().trim());
        ToastUtil.showToast(this, "data saved");
    }
}
