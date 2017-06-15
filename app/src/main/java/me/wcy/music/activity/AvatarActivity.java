package me.wcy.music.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import me.wcy.music.R;
import me.wcy.music.application.MusicApplication;
import me.wcy.music.model.User;
import me.wcy.music.utils.ToastUtils;
import me.wcy.music.utils.binding.Bind;
import okhttp3.Call;
import okhttp3.MediaType;

import static com.zhy.http.okhttp.OkHttpUtils.postString;

/**
 * Created by oreo on 2017-6-6.
 */

public class AvatarActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.bt_ava_sure)
    TextView tx_sure;

    @Bind(R.id.img1)
    public ImageView img1;
    @Bind(R.id.img2)
    ImageView img2;
    @Bind(R.id.img3)
    ImageView img3;
    @Bind(R.id.img4)
    ImageView img4;
    @Bind(R.id.img5)
    ImageView img5;
    @Bind(R.id.img6)
    ImageView img6;
    @Bind(R.id.img7)
    ImageView img7;
    @Bind(R.id.img8)
    ImageView img8;
    @Bind(R.id.img9)
    ImageView img9;
    @Bind(R.id.img10)
    ImageView img10;
    @Bind(R.id.img11)
    ImageView img11;
    @Bind(R.id.img12)
    ImageView img12;
    @Bind(R.id.img13)
    ImageView img13;
    @Bind(R.id.img14)
    ImageView img14;
    @Bind(R.id.img15)
    ImageView img15;
    @Bind(R.id.img16)
    ImageView img16;
    @Bind(R.id.img17)
    ImageView img17;
    @Bind(R.id.img18)
    ImageView img18;
    ArrayList<ImageView> imgList;
    private static final String TAG = "AvatarActivity";
    int preIndex = 0;
    int index = 0;
    ArrayList<String> urlList;
    SharedPreferences sp;
    int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avatar_layout);
        initActionBar();
        initonclick();
        glide();
        Log.d(TAG, "onCreate: ava");
        sp = getSharedPreferences("proFile", MODE_PRIVATE);
        id = sp.getInt("id", -1);

    }

    private void glide() {
        imgList.get(0).setAlpha(0.2f);
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
        for (int i = 0; i < 18; i++) {
            Glide.with(AvatarActivity.this)
                    .load(urlList.get(i))
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            Log.d(TAG, "onException: " + e);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            Log.d(TAG, "onResourceReady: " + resource);
                            return false;
                        }
                    })
                    .into(imgList.get(i));
        }
    }

    private void initonclick() {
        imgList = new ArrayList<ImageView>();
        tx_sure = (TextView) findViewById(R.id.bt_ava_sure);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);
        img5 = (ImageView) findViewById(R.id.img5);
        img6 = (ImageView) findViewById(R.id.img6);
        img7 = (ImageView) findViewById(R.id.img7);
        img8 = (ImageView) findViewById(R.id.img8);
        img9 = (ImageView) findViewById(R.id.img9);
        img10 = (ImageView) findViewById(R.id.img10);
        img11 = (ImageView) findViewById(R.id.img11);
        img12 = (ImageView) findViewById(R.id.img12);
        img13 = (ImageView) findViewById(R.id.img13);
        img14 = (ImageView) findViewById(R.id.img14);
        img15 = (ImageView) findViewById(R.id.img15);
        img16 = (ImageView) findViewById(R.id.img16);
        img17 = (ImageView) findViewById(R.id.img17);
        img18 = (ImageView) findViewById(R.id.img18);
        imgList.add(img1);
        imgList.add(img2);
        imgList.add(img3);
        imgList.add(img4);
        imgList.add(img5);
        imgList.add(img6);
        imgList.add(img7);
        imgList.add(img8);
        imgList.add(img9);
        imgList.add(img10);
        imgList.add(img11);
        imgList.add(img12);
        imgList.add(img13);
        imgList.add(img14);
        imgList.add(img15);
        imgList.add(img16);
        imgList.add(img17);
        imgList.add(img18);
        for (ImageView img : imgList) {
            img.setOnClickListener(this);
        }
        Log.d(TAG, "initonclick: " + img1);
        tx_sure.setOnClickListener(this);
//        imgList.get(1).setOnClickListener(this);
    }

    private void initActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.avatar_toobar);
        toolbar.setTitle("选择头像");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
//        toolbar.setNavigationIcon(R.);
    }

    @Override
    public void onClick(View view) {
        preIndex = index;
        switch (view.getId()) {
            case R.id.img1:
                view.setAlpha(0.2f);
                index = 0;
                break;
            case R.id.img2:
                view.setAlpha(0.2f);
                index = 1;
                break;
            case R.id.img3:
                view.setAlpha(0.2f);
                index = 2;
                break;
            case R.id.img4:
                view.setAlpha(0.2f);
                index = 3;
                break;
            case R.id.img5:
                view.setAlpha(0.2f);
                index = 4;
                break;
            case R.id.img6:
                view.setAlpha(0.2f);
                index = 5;
                break;
            case R.id.img7:
                view.setAlpha(0.4f);
                index = 6;
                break;
            case R.id.img8:
                view.setAlpha(0.2f);
                index = 7;
                break;
            case R.id.img9:
                view.setAlpha(0.2f);
                index = 8;
                break;
            case R.id.img10:
                view.setAlpha(0.2f);
                index = 9;
                break;
            case R.id.img11:
                view.setAlpha(0.2f);
                index = 10;
                break;
            case R.id.img12:
                view.setAlpha(0.2f);
                index = 11;
                break;
            case R.id.img13:
                view.setAlpha(0.2f);
                index = 12;
                break;
            case R.id.img14:
                view.setAlpha(0.2f);
                index = 13;
                break;
            case R.id.img15:
                view.setAlpha(0.2f);
                index = 14;
                break;
            case R.id.img16:
                view.setAlpha(0.2f);
                index = 15;
                break;
            case R.id.img17:
                view.setAlpha(0.2f);
                index = 16;
                break;
            case R.id.img18:
                view.setAlpha(0.2f);
                index = 17;
                break;
        }
        Log.d(TAG, "onClick: index" + index);
        Log.d(TAG, "onClick: preindex" + preIndex);
        if (preIndex != index)
            imgList.get(preIndex).setAlpha(1f);

        switch (view.getId()) {
            case R.id.bt_ava_sure:
                Log.d(TAG, "onClick: " + id);
                if (id != -1) {
                    postString()
                            .url(MusicApplication.ip + "enchant/editAvatar.action")
                            .content(new Gson().toJson(new User(id, index)))
                            .mediaType(MediaType.parse("application/json; charset=utf-8"))
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    ToastUtils.show("网络错误");
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Log.d(TAG, "onResponse: " + response);
                                    sp.edit().putInt("avatar", index).commit();
                                    Log.d(TAG, "onOptionsItemSelected: put1" + index);
                                    Log.d(TAG, "onResponse: initUi" + index);
                                }
                            });
                } else {
                    ToastUtils.show("登录出错，请重新登录");
                }
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("avatar", index);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
