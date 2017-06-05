package me.wcy.music.activity;

import android.os.Bundle;

import me.wcy.music.R;
import me.wcy.music.fragment.NewsListFragment;

/**
 * Created by rain on 2017/5/31.
 */

public class MessageActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        if (!checkServiceAlive()) {
            return;
        }
        //Fragment view
        NewsListFragment newsListFragment = new NewsListFragment();
        getFragmentManager().beginTransaction().replace(R.id.ll_fragment_container,newsListFragment).commit();
    }

}
