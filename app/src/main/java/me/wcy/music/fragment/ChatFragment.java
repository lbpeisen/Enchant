package me.wcy.music.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.bassaer.chatmessageview.models.Message;
import jp.bassaer.chatmessageview.views.ChatView;
import me.wcy.music.R;
import me.wcy.music.http.HttpCallback;
import me.wcy.music.http.HttpClient;
import me.wcy.music.model.ChatMessageGroup;

import static android.content.Context.MODE_PRIVATE;



public class ChatFragment  extends android.app.Fragment implements SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.swipe_refresh_layout_news)
    SwipeRefreshLayout swipe_refresh_layout;
    @Bind(R.id.chat_view)
    ChatView mChatView;
    private String remoteID;
    private String localID;
    private SharedPreferences sp;
    private View localView;
    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_chat,container,false);
        ButterKnife.bind(this, view);
        localView = view;
        context = getContext();
        /*
        * 下拉刷新，暂时屏蔽该功能以免多次获得消息
        * */
        swipe_refresh_layout.setOnRefreshListener(this);
        swipe_refresh_layout.setEnabled(false);
        //Create ChatView
        mChatView.setLeftBubbleColor(Color.WHITE);
        mChatView.setSendIcon(R.drawable.ic_action_send);
        mChatView.setRightMessageTextColor(Color.WHITE);
        mChatView.setLeftMessageTextColor(Color.BLACK);
        mChatView.setUsernameTextColor(Color.BLACK);
        mChatView.setSendTimeTextColor(Color.BLACK);
        mChatView.setDateSeparatorColor(Color.WHITE);
        //localID
        sp = getActivity().getSharedPreferences("proFile", MODE_PRIVATE);//获得实例对象
        localID = String.valueOf(sp.getInt("id", 0));
        Intent intent=getActivity().getIntent();
        remoteID = intent.getStringExtra("REMOTEID");
        onRefresh();
        //键盘焦点
        return view;
    }
    /*刷新获取新信息*/
    @Override
    public void onRefresh() {

        final Bitmap myIcon = BitmapFactory.decodeResource(getResources(), R.drawable.face_2);
        HttpClient.getChat(localID, remoteID, new HttpCallback<ChatMessageGroup>() {
            @Override
            public void onSuccess(ChatMessageGroup chatMessageGroup) {
                for(ChatMessageGroup.ChatMessage chatMessage: chatMessageGroup.getChatMessageArrayList()){
                    Message mMessageBuilder = new Message.Builder()
                            .setMessageText(chatMessage.getContent())
                            .setUserName(chatMessage.getTitle())
                            .setRightMessage(false)
                            .setUserIcon(myIcon)
                            .setDateCell(true)
                            .build();
                    mChatView.receive(mMessageBuilder);
                }
                mChatView.hideKeyboard();
                swipe_refresh_layout.setRefreshing(false);
            }
            @Override
            public void onFail(Exception e) {
                Log.i("getChatError",e.getMessage());
            }
        });
    }
}
