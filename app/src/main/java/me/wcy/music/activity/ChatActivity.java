package me.wcy.music.activity;


import android.content.SharedPreferences;
import android.os.Bundle;
import jp.bassaer.chatmessageview.views.ChatView;
import me.wcy.music.R;
import me.wcy.music.fragment.ChatFragment;
import me.wcy.music.fragment.NewsListFragment;

import me.wcy.music.utils.binding.Bind;

/**
 * Created by han78 on 2017/5/31.
 * 聊天界面和通知界面合为一个
 */

public class ChatActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        if (!checkServiceAlive()) {
            return;
        }
        ChatFragment chatFragment = new ChatFragment();
        getFragmentManager().beginTransaction().replace(R.id.ll_fragment_container,chatFragment).commit();
    }
}
