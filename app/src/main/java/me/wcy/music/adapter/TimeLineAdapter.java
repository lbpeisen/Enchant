package me.wcy.music.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import me.wcy.music.R;
import me.wcy.music.model.CommentGroup;
import me.wcy.music.utils.TransTime;

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.ViewHolder> {
    Context mContext;
    List<CommentGroup.Comment> mList;

    public void setList(List<CommentGroup.Comment> list) {
        mList = list;
    }

    public TimeLineAdapter(Context context) {
        mContext = context;
    }

    public TimeLineAdapter(Context context, List<CommentGroup.Comment> list) {
        mContext = context;
        mList=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(mList.get(position).getTitle());
        holder.content.setText(mList.get(position).getContent());
        holder.time.setText(TransTime.trans(String.valueOf(mList.get(position).getTime())));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView title;
        TextView content;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.com_title);
            time = (TextView) view.findViewById(R.id.com_time);
            content = (TextView) view.findViewById(R.id.com_content);
        }
    }

}
