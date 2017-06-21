package me.wcy.music.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.wcy.music.R;
import me.wcy.music.activity.ui.HeaderView;
import me.wcy.music.adapter.OnMoreClickListener;
import me.wcy.music.http.HttpCallback;
import me.wcy.music.http.HttpClient;
import me.wcy.music.model.FavoriteMusicGroup;
import me.wcy.music.utils.ToastUtils;

public class ProfileAcitivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener, AdapterView.OnItemClickListener, OnMoreClickListener {

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
    private List<String> mDatas;
    private FavoriteAdapter mAdapter;
    private boolean isHideToolbarView = false;
    private static final String TAG = "ProfileAcitivity";
    public int avaindex = -1;
    public int userid = -1;
    protected RecyclerView recyclerView;
    ArrayList<String> urlList;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
//        Bundle bundle = this.getIntent().getExtras();
        recyclerView = (RecyclerView) findViewById(R.id.profile_list_view);
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
        initFavoriteMusic();
        imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ProfileAcitivity.this, AvatarActivity.class);
                startActivityForResult(intent1, REQUEST_CODE);
            }
        });
    }

    private void initFavoriteMusic() {
        HttpClient.getFavoriteMusic(String.valueOf(userid), new HttpCallback<FavoriteMusicGroup>() {
            @Override
            public void onSuccess(FavoriteMusicGroup favoriteMusicGroup) {
                Log.d(TAG, "onSuccess: " + favoriteMusicGroup.toString());
                if (favoriteMusicGroup.getFavoriteMusics().size() != 0) {
                    ArrayList<FavoriteMusicGroup.FavoriteMusic> musiclist = favoriteMusicGroup.getFavoriteMusics();
                    mAdapter.setmList(musiclist);
                    mAdapter.notifyDataSetChanged();
                } else {
                    ToastUtils.show("赶快收藏你的歌曲吧");
                }
            }

            @Override
            public void onFail(Exception e) {

            }
        });

        initData();
    }

    protected void initData() {
//        mDatas = new ArrayList<String>();
//        for (int i = 'A'; i < 'z'; i++) {
//            mDatas.add("" + (char) i);
//        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter = new FavoriteAdapter());
        recyclerView.setNestedScrollingEnabled(false);
//        recyclerView.
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
        userid = sp.getInt("id", 0);

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemClick: " + position);

    }

    @Override
    public void onMoreClick(int position) {

    }

    class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {

        ArrayList<FavoriteMusicGroup.FavoriteMusic> mList;


        public void setmList(ArrayList<FavoriteMusicGroup.FavoriteMusic> mList) {
            this.mList = mList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(ProfileAcitivity.this).inflate(R.layout.view_holder_music, parent, false));

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Log.d(TAG, "onBindViewHolder: title" + mList.get(position).getTitle());
            Log.d(TAG, "onBindViewHolder: getArtist_name" + mList.get(position).getArtist_name());
            holder.tv_title.setText(mList.get(position).getTitle());
            holder.tv_artist.setText(mList.get(position).getArtist_name());
        }

        @Override
        public int getItemCount() {
            if (mList != null)
                return mList.size();
            else return 0;
        }

        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView tv_title;
            TextView tv_artist;

            public MyViewHolder(View itemView) {
                super(itemView);
                tv_title = (TextView) itemView.findViewById(R.id.tv_title);
                tv_artist = (TextView) itemView.findViewById(R.id.tv_artist);

            }

            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
            }
        }


    }
}
