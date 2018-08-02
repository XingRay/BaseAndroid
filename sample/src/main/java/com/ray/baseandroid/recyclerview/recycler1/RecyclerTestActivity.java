package com.ray.baseandroid.recyclerview.recycler1;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
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
 * @@author      : leixing
 * @@date        : 2017-06-23
 * Email       : leixing1012@qq.com
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class RecyclerTestActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    IRecyclerView rvList;

    private PersonAdapter mAdapter;
    private List<Person> mPersons;
    private LoadMoreFooterView loadMoreFooterView;

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
        rvList.setIAdapter(mAdapter);
        rvList.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                doRefresh();
            }
        });

        loadMoreFooterView = (LoadMoreFooterView) rvList.getLoadMoreFooterView();

        rvList.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (loadMoreFooterView.canLoadMore() && mAdapter.getItemCount() > 0) {
                    loadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
                    doLoadMore();
                }
            }
        });

        LinearLayout headerContainer = rvList.getHeaderContainer();
        View.inflate(mContext, R.layout.item_view_header, headerContainer);

        LinearLayout footerContainer = rvList.getFooterContainer();
        View.inflate(mContext, R.layout.item_view_footer, footerContainer);
    }

    private void doRefresh() {
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
                        mPersons.clear();
                        mPersons.addAll(persons);
                        mAdapter.notifyDataSetChanged();
                        rvList.setRefreshing(false);
                    }
                });
            }
        });
    }

    private void doLoadMore() {
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
                        loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
                        mPersons.addAll(persons);
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    @Override
    protected void loadData() {
        mPersons.addAll(PersonHelper.makePersons(100));
        mAdapter.notifyDataSetChanged();
    }
}
