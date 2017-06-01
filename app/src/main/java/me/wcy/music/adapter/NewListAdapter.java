package me.wcy.music.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import me.wcy.music.R;
import me.wcy.music.activity.ChatActivity;
import me.wcy.music.application.MusicApplication;
import me.wcy.music.model.GetMess;
import me.wcy.music.model.NewsInfo;
import me.wcy.music.model.ReceiveMess;
import okhttp3.Call;
import okhttp3.MediaType;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;


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
        info = addTest();
    }

    public ArrayList<ReceiveMess> addTest(){
        ArrayList<ReceiveMess> info = new ArrayList<>();
        for(int i = 1; i <10 ;i++){
            info.add(new ReceiveMess("1","1","Title","2016"));
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

    @Override
    public int getItemCount() {
        return  getContentItemCount();
    }

    public void network(String url){
        info = addTest();
        OkHttpUtils
                .postString()
                .url(MusicApplication.ip + "enchant/login.action")
                .content(new Gson().toJson(new GetMess("33")))//local user`s id
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d(TAG, "onError: " + e);
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.d(TAG, "onResponse: " + response);
                        if (response.toString().contains("\"STATUS\":1000")) {

                        }
                    }
                });
        notifyDataSetChanged();
    }
}

