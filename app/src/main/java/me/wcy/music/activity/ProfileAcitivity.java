package me.wcy.music.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.wcy.music.R;
import me.wcy.music.activity.ui.HeaderView;

public class ProfileAcitivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    public static final int REQUEST_CODE = 0x888;
    @Bind(R.id.toolbar_header_view)
    protected HeaderView toolbarHeaderView;

    @Bind(R.id.float_header_view)
    protected HeaderView floatHeaderView;

    @Bind(R.id.appbar)
    protected AppBarLayout appBarLayout;

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    @Bind(R.id.image)
    protected ImageView imgview;
    String name;
    private boolean isHideToolbarView = false;
    private static final String TAG = "ProfileAcitivity";
    public int avaindex = -1;
    ArrayList<String> urlList;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
//        Bundle bundle = this.getIntent().getExtras();
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        avaindex = intent.getIntExtra("avatar", 0);
        Log.d(TAG, "onCreate:sss " + name);
//        avatar = bundle.getString("avatar");
        initList();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        initUi();
        imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ProfileAcitivity.this, AvatarActivity.class);
                startActivityForResult(intent1, REQUEST_CODE);
            }
        });
    }

    private void initList() {
        urlList = new ArrayList<String>();
        urlList.add("http://www.lovexn.top/img/80948.jpg");
        urlList.add("http://www.lovexn.top/img/80949.jpg");
        urlList.add("http://www.lovexn.top/img/80950.jpg");
        urlList.add("http://www.lovexn.top/img/80951.jpg");
        urlList.add("http://www.lovexn.top/img/80952.jpg");
        urlList.add("http://www.lovexn.top/img/80953.jpg");
        urlList.add("http://www.lovexn.top/img/80954.jpg");
        urlList.add("http://www.lovexn.top/img/80955.jpg");
        urlList.add("http://www.lovexn.top/img/80956.jpg");
        urlList.add("http://www.lovexn.top/img/80957.jpg");
        urlList.add("http://www.lovexn.top/img/80958.jpg");
        urlList.add("http://www.lovexn.top/img/80959.jpg");
        urlList.add("http://www.lovexn.top/img/80960.jpg");
        urlList.add("http://www.lovexn.top/img/80961.jpg");
        urlList.add("http://www.lovexn.top/img/80962.jpg");
        urlList.add("http://www.lovexn.top/img/80963.jpg");
        urlList.add("http://www.lovexn.top/img/80964.jpg");
        urlList.add("http://www.lovexn.top/img/80965.jpg");
    }


    private void initUi() {
        appBarLayout.addOnOffsetChangedListener(this);
        toolbarHeaderView.bindTo(name);

        sp = getSharedPreferences("proFile", MODE_PRIVATE);
        avaindex = sp.getInt("avatar", 0);

        Log.d(TAG, "initUi: " + avaindex);
        if (avaindex != -1) {
            Glide.with(this)
                    .load(urlList.get(avaindex))
                    .into(imgview);
        }
//        toolbarHeaderView.bindTo("Larry Page", "Last seen today at 7.00PM");
//        floatHeaderView.bindTo("Larry Page", "Last seen today at 7.00PM");
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        if (percentage == 1f && isHideToolbarView) {
            toolbarHeaderView.setVisibility(View.VISIBLE);
            isHideToolbarView = !isHideToolbarView;

        } else if (percentage < 1f && !isHideToolbarView) {
            toolbarHeaderView.setVisibility(View.GONE);
            isHideToolbarView = !isHideToolbarView;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == REQUEST_CODE) {
            Bundle b = data.getExtras();
            avaindex = b.getInt("avatar");
            Log.d(TAG, "onActivityResult:  " + avaindex);
            initAvatar();
        }
    }

    private void initAvatar() {
        Glide.with(this)
                .load(urlList.get(avaindex))
                .thumbnail(0.1f)
                .into(imgview);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt("avatar", avaindex);
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            Log.d(TAG, "onActivityResult: 11");
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
