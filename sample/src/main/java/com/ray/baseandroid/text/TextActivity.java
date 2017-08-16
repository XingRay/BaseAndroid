package com.ray.baseandroid.text;

import android.text.util.Linkify;
import android.widget.EditText;

import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.BaseActivity;
import com.ray.linkifytext.LinkifyTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author      : leixing
 * Date        : 2017-08-07
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class TextActivity extends BaseActivity {
    @BindView(R.id.tv_text)
    LinkifyTextView tvText;
    @BindView(R.id.et_input)
    EditText etInput;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_text);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {
        setText();
    }

    @OnClick(R.id.bt_click)
    public void onViewClicked() {
        String input = etInput.getText().toString().trim();
        tvText.setTextLinkifyAndSelectable(input, Linkify.ALL, true);
    }

    private void setText() {
        String text = "hhshxhxhxh 13800090001 hdjxhxh 079183110644 bxjxbxbxj 01013241567 hDjxjzjzj010546764646hxjxjxj 010-15467543 hdjxjxj shhxhdhxHsHxjhxhxhx aaabbb@hecom.cn dhdhjdhxjdhxjdj http://www.baidu.com dhfhhx https://www.baidu.com djxjixhd www.baidu.com hxjxbxhxbbxb www.baidu.com?params=qwe jdjfndjjx hxjbdjx@dhhx.cn dhuxbxb@hdjxh.com.cn djjxhd@163.com jfjcjxnxj";
        tvText.setTextLinkifyAndSelectable(text, Linkify.ALL, true);
    }
}
