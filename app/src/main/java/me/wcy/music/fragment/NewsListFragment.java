package me.wcy.music.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.wcy.music.R;
import me.wcy.music.adapter.NewListAdapter;

/**
 * 通知
 */
public class NewsListFragment extends android.app.Fragment implements SwipeRefreshLayout.OnRefreshListener{

    @Bind(R.id.swipe_refresh_layout_news)
    SwipeRefreshLayout swipe_refresh_layout;
    private RecyclerView.LayoutManager mLayoutManager;
    @Bind(R.id.cardList_news)
    RecyclerView mRecyclerView;
    NewListAdapter newsadapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_news,container,false);
        ButterKnife.bind(this, view);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        swipe_refresh_layout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        newsadapter=new NewListAdapter(getActivity());
        mRecyclerView.setAdapter(newsadapter);//设置Adapter
        return view;
    }

    //重绘时刷新
    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }


    @Override
    public void onRefresh() {
//        OkHttpUtils
//                .postString()
//                .url(MusicApplication.ip + "enchant/register.action")
//                .content(new Gson().toJson(new User(st_username, st_passwd, st_user, pin, faceToken1)))
//                .mediaType(MediaType.parse("application/json; charset=utf-8"))
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        Log.d(TAG, "onError: register" + call);
//                        Log.d(TAG, "onError: register" + e);
//                        Log.d(TAG, "onError: register" + id);
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        Log.d(TAG, "onResponse: " + response);
//                        Log.d(TAG, "onResponse: " + response.toString());
//                        if (response.toString().contains("\"STATUS\":1005")) {
//
//                            Log.d(TAG, "onResponse:1111 ");
//                            Toast.makeText(RegisterActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
//                        } else if (response.toString().contains("\"STATUS\":1004")) {
//
//                            Log.d(TAG, "onResponse:222 ");
//                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
//                            animateRevealClose();
//                        } else if (response.toString().contains("\"STATUS\":1006")) {
//
//                            Log.d(TAG, "onResponse:3333 ");
//                            Toast.makeText(RegisterActivity.this, "未知错误", Toast.LENGTH_SHORT).show();
//                        } else if (response.toString().contains("\"STATUS\":1003")) {
//
//                            Log.d(TAG, "onResponse:3333 ");
//                            Toast.makeText(RegisterActivity.this, "用户名已存在", Toast.LENGTH_SHORT).show();
//                        }
//
//
//                    }
//                });
        newsadapter.network("test");
        swipe_refresh_layout.setRefreshing(false);
    }
}
