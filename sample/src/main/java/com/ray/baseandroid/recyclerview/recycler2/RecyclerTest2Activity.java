package com.ray.baseandroid.recyclerview.recycler2;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.ray.baseandroid.R;
import com.ray.baseandroid.recyclerview.Person;
import com.ray.baseandroid.recyclerview.PersonAdapter;
import com.ray.baseandroid.recyclerview.PersonHelper;
import com.ray.lib.android.base.page.BaseActivity;
import com.ray.lib.android.manager.ThreadPool;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author      : leixing
 * @date        : 2017-06-26
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class RecyclerTest2Activity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {
    @BindView(R.id.sl_swipe_layout)
    SwipeToLoadLayout slLayout;

    @BindView(R.id.swipe_refresh_header)
    RefreshHeaderView swipeRefreshHeader;

    @BindView(R.id.swipe_target)
    RecyclerView swipeTarget;

    @BindView(R.id.swipe_load_more_footer)
    LoadMoreFooterView swipeLoadMoreFooter;

    private List<Person> mPersons;
    private PersonAdapter mAdapter;

    @Override
    protected void initVariables() {
        mPersons = new ArrayList<>();
        mAdapter = new PersonAdapter(mContext, mPersons);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_recycler_test2);
        ButterKnife.bind(this);

        swipeTarget.setLayoutManager(new LinearLayoutManager(mContext));
        swipeTarget.setAdapter(mAdapter);

        slLayout.setOnRefreshListener(this);
        slLayout.setOnLoadMoreListener(this);
    }

    @Override
    protected void loadData() {
        mPersons.addAll(PersonHelper.makePersons(100));
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        ThreadPool.getDefault().submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final List<Person> persons = PersonHelper.makePersons(100);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        slLayout.setRefreshing(false);

                        mPersons.clear();
                        mPersons.addAll(persons);
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        });


    }

    @Override
    public void onLoadMore() {
        ThreadPool.getDefault().submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final List<Person> persons = PersonHelper.makePersons(100);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        slLayout.setLoadingMore(false);

                        mPersons.addAll(persons);
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
