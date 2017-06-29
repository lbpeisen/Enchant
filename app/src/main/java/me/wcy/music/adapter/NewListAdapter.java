package me.wcy.music.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import me.wcy.music.R;
import me.wcy.music.activity.ChatActivity;
import me.wcy.music.model.ReceiveMessGroup;
import me.wcy.music.utils.ImageUtils;
import me.wcy.music.utils.TransTime;

/**
 * 消息通知的Adapter
 */
public class NewListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ReceiveMessGroup.ReceiveMess> info=new ArrayList<ReceiveMessGroup.ReceiveMess>();
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
        private ImageView imageView;
        private String remoteID;

        public ContentViewHolder(final View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.tv_item_title);
            content = (TextView)itemView.findViewById(R.id.tv_item_content);
            time = (TextView)itemView.findViewById(R.id.tv_item_time);
            imageView = (ImageView)itemView.findViewById(R.id.mess_imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //启动
                    Intent userActivity = new Intent(itemView.getContext(), ChatActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    userActivity.putExtra("REMOTEID",remoteID);
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
         Bitmap myIcon = ImageUtils.createCircleImage(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.face_2));
        ((ContentViewHolder) holder).title.setText(info.get(position).getRemoteName());
        ((ContentViewHolder) holder).content.setText(info.get(position).getLastMessage());
        ((ContentViewHolder) holder).time.setText(TransTime.trans(info.get(position).getLastTime()));//将时间戳转成有效时间
        ((ContentViewHolder) holder).remoteID =  info.get(position).getRemoteID();
        ((ContentViewHolder)holder).imageView.setImageBitmap(myIcon);
    }
    //size of adapter
    @Override
    public int getItemCount() {
        return info.size();
    }

    /*时间戳转换成有效时间方法*/
    public void notifyChange(ArrayList<ReceiveMessGroup.ReceiveMess> receiveMesses){
        this.info = receiveMesses;
        notifyDataSetChanged();
    }
}

