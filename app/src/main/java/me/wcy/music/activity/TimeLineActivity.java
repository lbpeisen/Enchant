package me.wcy.music.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;


import java.util.ArrayList;
import java.util.List;

import me.wcy.music.R;
import me.wcy.music.adapter.ItemDecorationMy;
import me.wcy.music.adapter.TimeLineAdapter;
import me.wcy.music.model.CommentGroup;
import me.wcy.music.utils.binding.Bind;


public class TimeLineActivity extends BaseActivity {
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    List<CommentGroup.Comment> mList = new ArrayList<>();
    TimeLineAdapter mAdapter;

    long[] times = {
            1497229200,
            1497240000,
            1497243600,
            1497247200,
            1497249000,
            1497252600
    };
    String[] events = new String[]{
            "去小北门拿快递",
            "跟同事一起聚餐",
            "写文档",
            "和产品开会",
            "整理开会内容",
            "提交代码到git上"
    };

    String[] title = new String[]{
            "去小北门拿快递",
            "跟同事一起聚餐",
            "写文档",
            "和产品开会",
            "整理开会内容",
            "提交代码到git上"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);

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
}
