package me.wcy.music.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.wcy.music.R;
import me.wcy.music.adapter.NewListAdapter;
import me.wcy.music.http.HttpCallback;
import me.wcy.music.http.HttpClient;
import me.wcy.music.model.ReceiveMessGroup;

import static android.content.Context.MODE_PRIVATE;

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
    ArrayList<ReceiveMessGroup.ReceiveMess> receiveMesses;
    private SharedPreferences sp;
    private String localid;

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
        //localId
        sp = getActivity().getSharedPreferences("proFile", MODE_PRIVATE);//获得实例对象
        localid = String.valueOf(sp.getInt("id", 0));
        onRefresh();
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
        GetNewInfo(localid);
    }


    //Get ReceiveMessage
    public void GetNewInfo(String localID){
        HttpClient.getReceiveMess(localID, new HttpCallback<ReceiveMessGroup>() {
            @Override
            public void onSuccess(ReceiveMessGroup receiveMessGroup) {
                receiveMesses = receiveMessGroup.getLm();
                newsadapter.notifyChange(receiveMesses);
                swipe_refresh_layout.setRefreshing(false);
            }

            @Override
            public void onFail(Exception e) {
                Log.i("getNewsListError",e.getMessage());
            }
        });
    }
}
