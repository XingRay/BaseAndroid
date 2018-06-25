package com.ray.baseandroid.mainpage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ray.baseandroid.R;
import com.ray.baseandroid.alarm.AlarmSetActivity;
import com.ray.baseandroid.anr.AnrActivity;
import com.ray.baseandroid.commonlistview.ViewHolder;
import com.ray.baseandroid.commonlistview.WrapRecyclerView;
import com.ray.baseandroid.commonlistview.adapter.Adapter;
import com.ray.baseandroid.commonlistview.decoration.DrawableDividerItemDecoration;
import com.ray.baseandroid.commonlistview.listener.OnItemClickListener;
import com.ray.baseandroid.counter.CounterActivity;
import com.ray.baseandroid.customview.BezierCurveActivity;
import com.ray.baseandroid.customview.CustomView2Activity;
import com.ray.baseandroid.customview.CustomViewActivity;
import com.ray.baseandroid.dialogactivitytest.DialogActivityTestActivity;
import com.ray.baseandroid.dim.DimActivity;
import com.ray.baseandroid.img.ImgTestActivity;
import com.ray.baseandroid.inputtest.InputTestActivity;
import com.ray.baseandroid.intenttest.IntentTestActivity;
import com.ray.baseandroid.mainpage.data.entity.Function;
import com.ray.baseandroid.multiprocess.MultiProcessActivity;
import com.ray.baseandroid.multitype.MultiTypeListActivity;
import com.ray.baseandroid.mvp.MVPTestActivity;
import com.ray.baseandroid.oom.OOMTestActivity;
import com.ray.baseandroid.recyclerview.recycler1.RecyclerTestActivity;
import com.ray.baseandroid.recyclerview.recycler2.RecyclerTest2Activity;
import com.ray.baseandroid.recyclerview.recycler3.RecyclerTest3Activity;
import com.ray.baseandroid.spantest.SpannableTestActivity;
import com.ray.baseandroid.sptest.SPTestActivity;
import com.ray.baseandroid.text.TextActivity;
import com.ray.baseandroid.webview.WebTestActivity;
import com.ray.lib.android.base.page.BaseActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity implements OnItemClickListener {

    private static final List<Function> functions = Arrays.asList(
            new Function("intent test", IntentTestActivity.class),
            new Function("dialog activity test", DialogActivityTestActivity.class),
            new Function("oom test", OOMTestActivity.class),
            new Function("dim activity", DimActivity.class),
            new Function("input test", InputTestActivity.class),
            new Function("multi process", MultiProcessActivity.class),
            new Function("multiple type list", MultiTypeListActivity.class),
            new Function("mvp test", MVPTestActivity.class),
            new Function("sp test", SPTestActivity.class),
            new Function("alarm test", AlarmSetActivity.class),
            new Function("custom view", CustomViewActivity.class),
            new Function("bezier curve", BezierCurveActivity.class),
            new Function("customer view 2", CustomView2Activity.class),
            new Function("recycler view ", RecyclerTestActivity.class),
            new Function("recycler view 2", RecyclerTest2Activity.class),
            new Function("recycler view 3", RecyclerTest3Activity.class),
            new Function("anr test", AnrActivity.class),
            new Function("text", TextActivity.class),
            new Function("counter", CounterActivity.class),
            new Function("span test", SpannableTestActivity.class),
            new Function("webview", WebTestActivity.class),
            new Function("img test", ImgTestActivity.class)
    );

    private List<Function> mFunctions;
    private Activity mActivity;
    private Context mContext;
    private FunctionAdapter mFunctionAdapter;
    private WrapRecyclerView rvList;

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void initVariables() {
        mActivity = this;
        mContext = this.getApplicationContext();
        mFunctions = new ArrayList<>();
        mFunctionAdapter = new FunctionAdapter(mContext, mFunctions, R.layout.list_item_main_page_item);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);

        rvList = (WrapRecyclerView) findViewById(R.id.rv_list);
        rvList.setAdapter(mFunctionAdapter);
        rvList.setLayoutManager(new LinearLayoutManager(mContext));
        rvList.addItemDecoration(new DrawableDividerItemDecoration(mContext.getResources().getDrawable(R.drawable.drawable_line)));

        mFunctionAdapter.setOnItemClickListener(this);
    }

    @Override
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
        FunctionAdapter(Context context, List<Function> list, int layoutId) {
            super(context, list, layoutId);
        }

        @Override
        protected void bindView(ViewHolder holder, Function function, int viewType) {
            holder.setText(R.id.tv_text, "" + function.getName());
        }
    }
}