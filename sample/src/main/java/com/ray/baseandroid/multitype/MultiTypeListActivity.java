package com.ray.baseandroid.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ray.baseandroid.R;
import com.ray.lib.android.base.page.BaseActivity;
import com.ray.lib.java.util.RandomUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ClassLinker;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import me.drakeet.multitype.MultiTypeAsserts;
import me.drakeet.multitype.TypePool;

/**
 * Author      : leixing
 * Date        : 2017-05-23
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class MultiTypeListActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private Items mItems;
    private MultiTypeAdapter mAdapter;
    static TypePool sTypePool;
    static {


    }

    @Override
    protected void initVariables() {
        mItems = new Items();
        mAdapter = new MultiTypeAdapter(mItems);
        mAdapter.register(Message.class)
                .to(new RcvMessageViewBinder(), new SendMessageViewBinder())
                .withClassLinker(new ClassLinker<Message>() {
                    @NonNull
                    @Override
                    public Class<? extends ItemViewBinder<Message, ?>> index(@NonNull Message message) {
                        if (message.getFrom().equals("me")) {
                            return SendMessageViewBinder.class;
                        } else {
                            return RcvMessageViewBinder.class;
                        }
                    }
                });
        mAdapter.register(Friend.class, new FriendViewBinder());
        mAdapter.register(Message.class).to(new RcvMessageViewBinder());

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_multi_type_list);
        ButterKnife.bind(this);
        rvList.setLayoutManager(new LinearLayoutManager(mContext));
        rvList.setAdapter(mAdapter);
        MultiTypeAsserts.assertHasTheSameAdapter(rvList, mAdapter);
    }

    @Override
    protected void loadData() {
        for (int i = 0; i < 100; i++) {
            String content = RandomUtil.getRandomString(RandomUtil.getRandomInt(5, 10));
            if (RandomUtil.getRandomBoolean()) {
                mItems.add(new Message("friend", content, "me"));
            } else {
                mItems.add(new Message("me", content, "friend"));
            }
        }

        mItems.add(new Friend("aa", "cc"));

        MultiTypeAsserts.assertAllRegistered(mAdapter, mItems);
        mAdapter.notifyDataSetChanged();
    }
}
