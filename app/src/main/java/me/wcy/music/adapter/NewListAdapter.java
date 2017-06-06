package me.wcy.music.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.wcy.music.R;
import me.wcy.music.activity.ChatActivity;
import me.wcy.music.model.ReceiveMess;

/**
 * Created by rain on 2016/4/13.
 */
public class NewListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ReceiveMess> info=new ArrayList<ReceiveMess>();
    private LayoutInflater mLayoutInflater;
    private Context mContext;


    public NewListAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    //内容 ViewHolder
    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView content;
        private TextView time;
        private String remoteID;

        public ContentViewHolder(final View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.tv_item_title);
            content = (TextView)itemView.findViewById(R.id.tv_item_content);
            time = (TextView)itemView.findViewById(R.id.tv_item_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //启动
                    Intent userActivity = new Intent(itemView.getContext(), ChatActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    userActivity.putExtra("SENDID",remoteID);
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
        ((ContentViewHolder) holder).title.setText(info.get(position).getRemoteName());
        ((ContentViewHolder) holder).content.setText(info.get(position).getMess_title());
        ((ContentViewHolder) holder).time.setText(info.get(position).getTime());
        ((ContentViewHolder) holder).remoteID =  info.get(position).getRemoteID();
    }
    //size of adapter
    @Override
    public int getItemCount() {
        return info.size();
    }

    public  void notifyChange(ArrayList<ReceiveMess> receiveMesses){
        this.info = receiveMesses;
        notifyDataSetChanged();
    }




}

