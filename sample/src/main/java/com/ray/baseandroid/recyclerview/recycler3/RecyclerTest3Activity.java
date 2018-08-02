package com.ray.baseandroid.recyclerview.recycler3;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.ray.baseandroid.R;
import com.ray.baseandroid.commonlistview.WrapRecyclerView;
import com.ray.baseandroid.recyclerview.Person;
import com.ray.baseandroid.recyclerview.PersonAdapter;
import com.ray.baseandroid.recyclerview.PersonHelper;
import com.ray.baseandroid.recyclerview.recycler2.LoadMoreFooterView;
import com.ray.baseandroid.recyclerview.recycler2.RefreshHeaderView;
import com.ray.lib.android.base.page.BaseActivity;
import com.ray.lib.android.manager.ThreadPool;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @@author      : leixing
 * @@date        : 2017-06-26
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class RecyclerTest3Activity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {
//    @BindView(R.id.tb_toolbar)
//    Toolbar tbToolbar;
//
//    @BindView(R.id.tl_layout)
//    TabLayout tlLayout;
//
//    @BindView(R.id.abl_app_bar_layout)
//    AppBarLayout ablAppBarLayout;

    @BindView(R.id.swipe_refresh_header)
    RefreshHeaderView swipeRefreshHeader;

    @BindView(R.id.swipe_target)
    WrapRecyclerView swipeTarget;

    @BindView(R.id.swipe_load_more_footer)
    LoadMoreFooterView swipeLoadMoreFooter;

    @BindView(R.id.stl_layout)
    SwipeToLoadLayout stlLayout;

//    @BindView(R.id.cl_root)
//    CoordinatorLayout clRoot;

    private List<Person> mPersons;
    private PersonAdapter mAdapter;

    @Override
    protected void initVariables() {
        mPersons = new ArrayList<>();
        mAdapter = new PersonAdapter(mContext, mPersons);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_recycler_test3);
        ButterKnife.bind(this);

        stlLayout.setOnRefreshListener(this);
        stlLayout.setOnLoadMoreListener(this);

        swipeTarget.setAdapter(mAdapter);
        swipeTarget.setLayoutManager(new LinearLayoutManager(mContext));

        View headerView = View.inflate(mContext, R.layout.item_view_header, null);
        swipeTarget.addHeaderView(headerView);

        View footerView = View.inflate(mContext, R.layout.item_view_footer, null);
        swipeTarget.addFooterView(footerView);
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
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final List<Person> persons = PersonHelper.makePersons(100);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mPersons.clear();
                        mPersons.addAll(persons);
                        mAdapter.notifyDataSetChanged();
                        stlLayout.setRefreshing(false);
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
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final List<Person> persons = PersonHelper.makePersons(100);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        stlLayout.setLoadingMore(false);
                        mPersons.addAll(persons);
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
