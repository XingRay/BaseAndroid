package com.ray.baseandroid.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ray.baseandroid.R;
import com.ray.commonlistview.LoadRefreshRecyclerView;
import com.ray.lib.android.base.page.BaseActivity;
import com.ray.lib.java.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author      : leixing
 * Date        : 2017-06-23
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class RecyclerTestActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private PersonAdapter mAdapter;
    private List<Person> mPersons;

    @Override
    protected void initVariables() {
        mPersons = new ArrayList<>();
        mAdapter = new PersonAdapter(mContext, mPersons);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_recycler_test);
        ButterKnife.bind(this);

        rvList.setLayoutManager(new LinearLayoutManager(mContext));
        rvList.setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {

        for (int i = 0; i < 100; i++) {
            Person person = new Person();
            person.setAge(RandomUtil.getRandomInt(18, 30));
            person.setName(RandomUtil.getRandomString(8));
            person.setSex(RandomUtil.getRandomBoolean() ? "male" : "female");
            mPersons.add(person);
        }

        mAdapter.notifyDataSetChanged();
    }

    private static class PersonAdapter extends RecyclerView.Adapter<PersonViewHolder> {

        private final Context mContext;
        private final List<Person> mPersons;
        private final LayoutInflater mInflater;

        public PersonAdapter(Context context, List<Person> persons) {
            mPersons = persons;
            mContext = context;
            mInflater = LayoutInflater.from(mContext);
        }

        @Override
        public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.item_person_item, parent, false);
            return new PersonViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(PersonViewHolder holder, int position) {
            Person person = mPersons.get(position);
            holder.tvName.setText(person.getName());
            holder.tvSex.setText(person.getSex());
            holder.tvAge.setText(String.valueOf(person.getAge()));
        }

        @Override
        public int getItemCount() {
            return mPersons.size();
        }
    }

    static class PersonViewHolder extends RecyclerView.ViewHolder {
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
}
