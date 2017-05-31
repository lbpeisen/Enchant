package me.wcy.music.activity;

import android.os.Bundle;

import me.wcy.music.R;
import me.wcy.music.fragment.ChatFragment;

/**
 * Created by han78 on 2017/5/31.
 */

public class ChatActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        if (!checkServiceAlive()) {
            return;
        }
        //Fragment
        ChatFragment chatFragment = new ChatFragment();
        getFragmentManager().beginTransaction().replace(R.id.ll_fragment_container,chatFragment).commit();
    }

}
