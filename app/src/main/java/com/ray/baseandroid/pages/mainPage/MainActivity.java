package com.ray.baseandroid.pages.mainpage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ray.baseandroid.R;
import com.ray.baseandroid.base.BaseActivity;
import com.ray.baseandroid.pages.dialogactivitytest.DialogActivityTestActivity;
import com.ray.baseandroid.pages.dim.DimActivity;
import com.ray.baseandroid.pages.inputtest.InputTestActivity;
import com.ray.baseandroid.pages.intenttest.IntentTestActivity;
import com.ray.baseandroid.pages.mainpage.data.entity.Function;
import com.ray.baseandroid.pages.oom.OOMTestActivity;
import com.ray.baseandroid.widget.recyclerview.ViewHolder;
import com.ray.baseandroid.widget.recyclerview.WrapRecyclerView;
import com.ray.baseandroid.widget.recyclerview.adapter.Adapter;
import com.ray.baseandroid.widget.recyclerview.decoration.DrawableDividerItemDecoration;
import com.ray.baseandroid.widget.recyclerview.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity implements OnItemClickListener {

    private List<Function> mFunctions;
    private Activity mActivity;
    private Context mContext;
    private FunctionAdapter mFunctionAdapter;
    private WrapRecyclerView rvList;

    private static final List<Function> functions = Arrays.asList(
            new Function("intent test", IntentTestActivity.class),
            new Function("dialog activity test", DialogActivityTestActivity.class),
            new Function("oom test", OOMTestActivity.class),
            new Function("dim activity", DimActivity.class),
            new Function("input test", InputTestActivity.class)
    );

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    protected void initVariables() {
        mActivity = this;
        mContext = this.getApplicationContext();
        mFunctions = new ArrayList<>();
        mFunctionAdapter = new FunctionAdapter(mContext, mFunctions, R.layout.list_item_main_page_item);
    }

    protected void initView() {
        setContentView(R.layout.activity_main);

        rvList = (WrapRecyclerView) findViewById(R.id.rv_list);
        rvList.setAdapter(mFunctionAdapter);
        rvList.setLayoutManager(new LinearLayoutManager(mContext));
        rvList.addItemDecoration(new DrawableDividerItemDecoration(mContext.getResources().getDrawable(R.drawable.drawable_line)));

        mFunctionAdapter.setOnItemClickListener(this);
    }

    protected void loadData() {
        mFunctions.addAll(functions);
        mFunctionAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(RecyclerView parent, View view, int position, int id) {
        Function function = mFunctions.get(id);
        if (function == null) {
            showToast("item is null, id:" + id);
            return;
        }

        function.exec(this);
    }

    private static class FunctionAdapter extends Adapter<Function> {
        public FunctionAdapter(Context context, List<Function> list, int layoutId) {
            super(context, list, layoutId);
        }

        @Override
        protected void bindView(ViewHolder holder, Function function, int viewType) {
            holder.setText(R.id.tv_text, "" + function.getName());
        }
    }
}