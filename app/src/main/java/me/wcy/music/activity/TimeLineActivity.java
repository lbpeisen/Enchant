package me.wcy.music.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;


import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import me.wcy.music.R;
import me.wcy.music.adapter.ItemDecorationMy;
import me.wcy.music.adapter.TimeLineAdapter;
import me.wcy.music.application.AppCache;
import me.wcy.music.model.CommentGroup;
import me.wcy.music.utils.CoverLoader;
import me.wcy.music.utils.ImageUtils;
import me.wcy.music.utils.ScreenUtils;
import me.wcy.music.utils.binding.Bind;

import static me.wcy.music.R.id.backdrop_notice;


public class TimeLineActivity extends BaseActivity implements FloatingActionButton.OnClickListener {
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.backdrop_notice)
    ImageView mImageView;
    @Bind(R.id.toolbar_comment)
    Toolbar mToolbar;

    @Bind(R.id.floatingButton)
    FloatingActionButton mActionButton;

    List<CommentGroup.Comment> mList = new ArrayList<>();
    TimeLineAdapter mAdapter;
    private long musicID;
    private String bitmapPath;
    private Bitmap bitmap;

    long[] times = {
            1497229200,
            1497240000,
            1497243600,
            1497247200,
            1497249000,
            1497252600,1497252600,1497252600,1497252600
    };
    String[] events = new String[]{
            "去小北门拿快递",
            "跟同事一起聚餐",
            "写文档",
            "和产品开会",
            "整理开会内容",
            "提交代码到git上","写文档","写文档","写文档"
    };

    String[] title = new String[]{
            "去小北门拿快递",
            "跟同事一起聚餐",
            "写文档",
            "和产品开会",
            "整理开会内容",
            "提交代码到git上","和产品开会","和产品开会","和产品开会"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
        ButterKnife.bind(this);
        initValue();
        initBitmap();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new ItemDecorationMy(this, 50));

        for (int i = 0; i < times.length; i++) {
            CommentGroup.Comment event = new CommentGroup.Comment();
            event.setTime(times[i]);
            event.setTitle(events[i]);
            event.setContent(events[i]);
            mList.add(event);
        }

        mAdapter = new TimeLineAdapter(this, mList);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floatingButton:
                break;
        }
    }
}
