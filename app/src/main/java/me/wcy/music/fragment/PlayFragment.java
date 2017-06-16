package me.wcy.music.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ldoublem.thumbUplib.ThumbUpView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.wcy.lrcview.LrcView;
import me.wcy.music.R;
import me.wcy.music.activity.ChatActivity;
import me.wcy.music.activity.TimeLineActivity;
import me.wcy.music.adapter.PlayPagerAdapter;
import me.wcy.music.application.MusicApplication;
import me.wcy.music.constants.Actions;
import me.wcy.music.enums.PlayModeEnum;
import me.wcy.music.executor.SearchLrc;
import me.wcy.music.http.HttpCallback;
import me.wcy.music.http.HttpClient;
import me.wcy.music.model.Music;
import me.wcy.music.utils.CoverLoader;
import me.wcy.music.utils.FileUtils;
import me.wcy.music.utils.Preferences;
import me.wcy.music.utils.ScreenUtils;
import me.wcy.music.utils.SystemUtils;
import me.wcy.music.utils.ToastUtils;
import me.wcy.music.utils.binding.Bind;
import me.wcy.music.widget.AlbumCoverView;
import me.wcy.music.widget.IndicatorLayout;

import static android.content.Context.MODE_PRIVATE;

/**
 * 正在播放界面
 * Created by wcy on 2015/11/27.
 */
public class PlayFragment extends BaseFragment implements View.OnClickListener,
        ViewPager.OnPageChangeListener, SeekBar.OnSeekBarChangeListener {
    @Bind(R.id.ll_content)
    private LinearLayout llContent;
    @Bind(R.id.iv_play_page_bg)
    private ImageView ivPlayingBg;
    @Bind(R.id.iv_back)
    private ImageView ivBack;
    @Bind(R.id.tv_title)
    private TextView tvTitle;
    @Bind(R.id.tv_artist)
    private TextView tvArtist;
    @Bind(R.id.vp_play_page)
    private ViewPager vpPlay;
    @Bind(R.id.il_indicator)
    private IndicatorLayout ilIndicator;
    @Bind(R.id.sb_progress)
    private SeekBar sbProgress;
    @Bind(R.id.tv_current_time)
    private TextView tvCurrentTime;
    @Bind(R.id.tv_total_time)
    private TextView tvTotalTime;
    @Bind(R.id.iv_mode)
    private ImageView ivMode;
    @Bind(R.id.iv_play)
    private ImageView ivPlay;
    @Bind(R.id.iv_next)
    private ImageView ivNext;
    @Bind(R.id.iv_prev)
    private ImageView ivPrev;
    @Bind(R.id.tpv)
    private ThumbUpView mThumbUpView;
    @Bind(R.id.tv_comment)
    ImageButton iv_comment;

    private AlbumCoverView mAlbumCoverView;
    private LrcView mLrcViewFull;
    private AudioManager mAudioManager;
    private List<View> mViewPagerContent;
    private int mLastProgress;
    private SharedPreferences sp;
    private int localid;
    private long musicDration;
    private String BitmapPath;
    private Music.Type type;//播放歌曲的类型
    private String artName;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sp = getActivity().getSharedPreferences("proFile", MODE_PRIVATE);//获得实例对象
        localid = sp.getInt("id", 0);
        return inflater.inflate(R.layout.fragment_play, container, false);
    }
    /*
    * 初始化
    * */
    @Override
    protected void init() {

        initSystemBar();
        initViewPager();
        ilIndicator.create(mViewPagerContent.size());
        initPlayMode();
        onChange(getPlayService().getPlayingMusic());
        onLikeButtonCreate();//Create likebutton`s listener.
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(Actions.VOLUME_CHANGED_ACTION);
    }

    @Override
    protected void setListener() {
        ivBack.setOnClickListener(this);
        ivMode.setOnClickListener(this);
        ivPlay.setOnClickListener(this);
        ivPrev.setOnClickListener(this);
        ivNext.setOnClickListener(this);
        iv_comment.setOnClickListener(this);
        sbProgress.setOnSeekBarChangeListener(this);
        vpPlay.setOnPageChangeListener(this);

    }

    /**
     * 沉浸式状态栏
     */
    private void initSystemBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int top = ScreenUtils.getSystemBarHeight();
            llContent.setPadding(0, top, 0, 0);
        }
    }
    /*
    * 初始化界面
    * */
    private void initViewPager() {
        View coverView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_play_page_cover, null);
        View lrcView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_play_page_lrc, null);
        mAlbumCoverView = (AlbumCoverView) coverView.findViewById(R.id.album_cover_view);
        mLrcViewFull = (LrcView) lrcView.findViewById(R.id.lrc_view_full);
        mAlbumCoverView.initNeedle(getPlayService().isPlaying());
        initVolume();

        mViewPagerContent = new ArrayList<>(2);
        mViewPagerContent.add(coverView);
        mViewPagerContent.add(lrcView);
        vpPlay.setAdapter(new PlayPagerAdapter(mViewPagerContent));
    }

    /*
    * 初始化音量
    * */
    private void initVolume() {
        mAudioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
    }

    /*
    * 初始化播放方式
    * */
    private void initPlayMode() {
        int mode = Preferences.getPlayMode();
        ivMode.setImageLevel(mode);
    }

    /**
     * 更新播放进度
     */
    public void onPublish(int progress) {
        if (isAdded()) {
            sbProgress.setProgress(progress);
            //更新当前播放时间
            if (progress - mLastProgress >= 1000) {
                tvCurrentTime.setText(formatTime(progress));
                mLastProgress = progress;
            }
        }
    }

    /*
    * 切换歌曲
    * */
    public void onChange(Music music) {
        if (isAdded()) {
            onPlay(music);
        }
    }
    /*
    * 歌曲暂停
    * */
    public void onPlayerPause() {
        if (isAdded()) {
            ivPlay.setSelected(false);
            mAlbumCoverView.pause();
        }
    }

    public void onPlayerResume() {
        if (isAdded()) {
            ivPlay.setSelected(true);
            mAlbumCoverView.start();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.iv_mode:
                switchPlayMode();
                break;
            case R.id.iv_play:
                play();
                break;
            case R.id.iv_next:
                next();
                break;
            case R.id.iv_prev:
                prev();
                break;
            case R.id.tv_comment:
                if(type== Music.Type.ONLINE) {
                    Intent userActivity = new Intent(getActivity(), TimeLineActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    userActivity.putExtra("MUSICID", musicDration);
                    userActivity.putExtra("BITMAP", BitmapPath);
                    userActivity.putExtra("ART",artName);
                    startActivity(userActivity);
                }else {
                    ToastUtils.show(R.string.comment_err);
                }
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    /*
    * 界面选择
    * */
    @Override
    public void onPageSelected(int position) {
        ilIndicator.setCurrent(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }
    /*
    * 拖动停止后更改相关信息
    * */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (getPlayService().isPlaying() || getPlayService().isPausing()) {
            int progress = seekBar.getProgress();
            getPlayService().seekTo(progress);
            mLrcViewFull.onDrag(progress);//更改歌词的对应时间
            tvCurrentTime.setText(formatTime(progress));
            mLastProgress = progress;
        } else {
            seekBar.setProgress(0);
        }
    }

    private static final String TAG = "PlayFragment";

    private void onPlay(Music music) {
        if (music == null) {
            return;
        }
        BitmapPath = FileUtils.getAlbumFilePath(music);
        musicDration = music.getDuration();//remote
        type = music.getType();//类型(local还是remote)
        tvTitle.setText(music.getTitle());
        tvArtist.setText(music.getArtist());
        artName =  music.getArtist();
        sbProgress.setMax((int) music.getDuration());
        sbProgress.setProgress(0);
        mLastProgress = 0;
        tvCurrentTime.setText(R.string.play_time_start);
        tvTotalTime.setText(formatTime(music.getDuration()));
        setCoverAndBg(music);
        setLrc(music);
        if (getPlayService().isPlaying() || getPlayService().isPreparing()) {
            ivPlay.setSelected(true);
            mAlbumCoverView.start();
        } else {
            ivPlay.setSelected(false);
            mAlbumCoverView.pause();
        }
        Log.d(TAG, "onPlay: " + music.getId());
    }

    private void play() {
        getPlayService().playPause();
    }

    private void next() {
        getPlayService().next();
    }

    private void prev() {
        getPlayService().prev();
    }

    private void switchPlayMode() {
        PlayModeEnum mode = PlayModeEnum.valueOf(Preferences.getPlayMode());
        switch (mode) {
            case LOOP:
                mode = PlayModeEnum.SHUFFLE;
                ToastUtils.show(R.string.mode_shuffle);
                break;
            case SHUFFLE:
                mode = PlayModeEnum.SINGLE;
                ToastUtils.show(R.string.mode_one);
                break;
            case SINGLE:
                mode = PlayModeEnum.LOOP;
                ToastUtils.show(R.string.mode_loop);
                break;
        }
        Preferences.savePlayMode(mode.value());
        initPlayMode();
    }

    private void onBackPressed() {
        getActivity().onBackPressed();
        ivBack.setEnabled(false);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ivBack.setEnabled(true);
            }
        }, 300);
    }

    private void setCoverAndBg(Music music) {
        mAlbumCoverView.setCoverBitmap(CoverLoader.getInstance().loadRound(music));
    }

    private void setLrc(final Music music) {
        if (music.getType() == Music.Type.LOCAL) {
            String lrcPath = FileUtils.getLrcFilePath(music);
            if (!TextUtils.isEmpty(lrcPath)) {
                loadLrc(lrcPath);
            } else {
                new SearchLrc(music.getArtist(), music.getTitle()) {
                    @Override
                    public void onPrepare() {
                        // 设置tag防止歌词下载完成后已切换歌曲
                        vpPlay.setTag(music);
                        loadLrc("");
                        setLrcLabel("正在搜索歌词");
                    }

                    @Override
                    public void onExecuteSuccess(@NonNull String lrcPath) {
                        if (vpPlay.getTag() != music) {
                            return;
                        }

                        // 清除tag
                        vpPlay.setTag(null);

                        loadLrc(lrcPath);
                        setLrcLabel("暂无歌词");
                    }

                    @Override
                    public void onExecuteFail(Exception e) {
                        if (vpPlay.getTag() != music) {
                            return;
                        }

                        // 清除tag
                        vpPlay.setTag(null);

                        setLrcLabel("暂无歌词");
                    }
                }.execute();
            }
        } else {
            String lrcPath = FileUtils.getLrcDir() + FileUtils.getLrcFileName(music.getArtist(), music.getTitle());
            loadLrc(lrcPath);
        }
    }

    private void loadLrc(String path) {
        File file = new File(path);
        mLrcViewFull.loadLrc(file);
    }

    private void setLrcLabel(String label) {
        mLrcViewFull.setLabel(label);
    }

    private String formatTime(long time) {
        return SystemUtils.formatTime("mm:ss", time);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    /*
    * 点赞收藏功能
    * */
    public void onLikeButtonCreate() {
        //点赞按钮
        mThumbUpView.setUnLikeType(ThumbUpView.LikeType.unlike);
        mThumbUpView.setOnThumbUp(new ThumbUpView.OnThumbUp() {
            @Override
            public void like(final boolean like) {
                if (MusicApplication.getLoginState() == 0) {
                    ToastUtils.show("请先登录");
                    return;
                }
                //点赞详细操作
                String remoteMusicID = String.valueOf(getPlayService().getPlayingMusic().getRemoteMusicID());
                String ifLike;
                if (like) {
                    ifLike = "1";
                } else {
                    ifLike = "0";
                }
                HttpClient.collectMusic(String.valueOf(localid), remoteMusicID, ifLike, new HttpCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        if (s.contains("\"STATUS\":1000")) {
                            if (like) {
                                ToastUtils.show(R.string.like);
                            } else {
                                ToastUtils.show(R.string.unlike);
                            }
                        } else {
                            ToastUtils.show(R.string.network_error);
                        }
                    }

                    @Override
                    public void onFail(Exception e) {
                        ToastUtils.show(R.string.network_error);
                    }
                });
            }
        });
    }
}
