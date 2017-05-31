package me.wcy.music.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.wcy.music.R;
import me.wcy.music.activity.AboutActivity;
import me.wcy.music.model.NewsInfo;


/**
 * Created by rain on 2016/4/13.
 */
public class NewListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<NewsInfo> info=new ArrayList<NewsInfo>();
    private LayoutInflater mLayoutInflater;
    private Context mContext;


    public NewListAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        info = addTest();
    }

    public ArrayList<NewsInfo> addTest(){
        ArrayList<NewsInfo> info = new ArrayList<NewsInfo>();
        for(int i = 1; i <10 ;i++){
            info.add(new NewsInfo(1,1,"Title","This is test news","2016"));
        }
        return  info;
    }


    //内容长度
    public int getContentItemCount(){
        return info.size();//得到item长度
    }

    //内容 ViewHolder
    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView content;
        private TextView time;
        public ContentViewHolder(final View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.tv_item_title);
            content = (TextView)itemView.findViewById(R.id.tv_item_content);
            time = (TextView)itemView.findViewById(R.id.tv_item_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //启动
                    Intent userActivity = new Intent(itemView.getContext(), AboutActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    itemView.getContext().startActivity(userActivity);
                }
            });
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContentViewHolder(mLayoutInflater.inflate(R.layout.news_item, parent, false));
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ContentViewHolder) holder).title.setText(info.get(position).getNews_title());
        ((ContentViewHolder) holder).content.setText(info.get(position).getNews_content());
        ((ContentViewHolder) holder).time.setText(info.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return  getContentItemCount();
    }

    public void network(String url){
        info = addTest();
        notifyDataSetChanged();
    }
}

