package me.wcy.music.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.Collections;

import me.wcy.music.R;
import me.wcy.music.adapter.ItemDecorationMy;
import me.wcy.music.adapter.TimeLineAdapter;
import me.wcy.music.application.MusicApplication;
import me.wcy.music.fragment.SwipeFragment;
import me.wcy.music.http.HttpCallback;
import me.wcy.music.http.HttpClient;
import me.wcy.music.model.Collotion;
import me.wcy.music.model.CommentGroup;
import me.wcy.music.utils.CoverLoader;
import me.wcy.music.utils.ToastUtils;
import me.wcy.music.utils.binding.Bind;



public class TimeLineActivity extends BaseActivity implements FloatingActionButton.OnClickListener{
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.backdrop_notice)
    ImageView mImageView;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.floatingButton)
    FloatingActionButton mActionButton;
    private SharedPreferences sp;

    TimeLineAdapter mAdapter;
    private long musicID;
    private int localID;
    private String bitmapPath;
    private Bitmap bitmap;
    private ArrayList<CommentGroup.Comment> commentArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
        initValue();
        initCommentList();
        initBitmap();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new ItemDecorationMy(this, 50));
        mAdapter = new TimeLineAdapter(this,commentArrayList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initValue() {
        Intent intent= getIntent();
        musicID = intent.getLongExtra("MUSICID",0);
        bitmapPath = intent.getStringExtra("BITMAP");
        mActionButton.setOnClickListener(this);
    }

    private void initBitmap(){
        bitmap = CoverLoader.getInstance().loadHand(bitmapPath);
        mImageView.setImageBitmap(bitmap);
    }

    private void initCommentList(){
        HttpClient.getComent(String.valueOf(musicID), new HttpCallback<CommentGroup>() {
            @Override
            public void onSuccess(CommentGroup commentGroup) {
                if(commentGroup.getMusicCommnets().size()!=0){
                    ArrayList<CommentGroup.Comment> commentList = commentGroup.getMusicCommnets();
                    Collections.sort(commentList);
                    mAdapter.setList(commentList);
                    mAdapter.notifyDataSetChanged();
                }else {
                    ToastUtils.show("赶快抢第一个评论吧");
                }
            }

            @Override
            public void onFail(Exception e) {
                ToastUtils.show("网络问题");
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floatingButton:
                if (MusicApplication.getLoginState() == 0) {
                    ToastUtils.show("请先登录");
                    return;
                }
                FragmentTransaction mFragTransaction = getFragmentManager().beginTransaction();
                Fragment fragment =  getFragmentManager().findFragmentByTag("dialogFragment");
                if(fragment!=null){
                    //为了不重复显示dialog，在显示对话框之前移除正在显示的对话框
                    mFragTransaction.remove(fragment);
                }
                sp =getSharedPreferences("proFile", MODE_PRIVATE);//获得实例对象
                localID = sp.getInt("id",0);
                SwipeFragment dialogFragment = new SwipeFragment(musicID,localID,TimeLineActivity.this);
                dialogFragment.show(mFragTransaction, "dialogFragment");
                initCommentList();
                break;
        }
    }

    public void reflash(){
        initCommentList();
    }
}
