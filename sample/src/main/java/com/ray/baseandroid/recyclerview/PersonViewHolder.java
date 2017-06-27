package com.ray.baseandroid.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ray.baseandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_age)
    TextView tvAge;

    PersonViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
