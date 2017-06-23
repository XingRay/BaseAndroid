package com.ray.baseandroid.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ray.baseandroid.R;
import com.ray.commonlistview.LoadRefreshRecyclerView;
import com.ray.lib.android.base.page.BaseActivity;

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
    LoadRefreshRecyclerView rvList;

    @Override
    protected void initVariables() {
        List<Person> mPersons = new ArrayList<>();

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_recycler_test);
        ButterKnife.bind(this);

        rvList.setLayoutManager(new LinearLayoutManager(mContext));
        r =
    }

    @Override
    protected void loadData() {

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
            mInflater.inflate(R.layout.item_person_item)
            return null;
        }

        @Override
        public void onBindViewHolder(PersonViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    private static class PersonViewHolder extends RecyclerView.ViewHolder {
        public PersonViewHolder(View itemView) {
            super(itemView);
        }
    }
}
