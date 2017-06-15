package me.wcy.music.http;

import android.graphics.Bitmap;
import android.util.Log;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;

import me.wcy.music.application.MusicApplication;
import me.wcy.music.model.ArtistInfo;
import me.wcy.music.model.ChatMessageGroup;
import me.wcy.music.model.CollectMusic;
import me.wcy.music.model.DownloadInfo;
import me.wcy.music.model.GetChat;
import me.wcy.music.model.GetMess;
import me.wcy.music.model.Lrc;
import me.wcy.music.model.OnlineMusicList;
import me.wcy.music.model.ReceiveMessGroup;
import me.wcy.music.model.SearchMusic;
import me.wcy.music.model.Splash;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;


/**
 * 网络操作类
 */
public class HttpClient {
    private static final String SPLASH_URL = "http://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1";
    private static final String BASE_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting";
    private static final String METHOD_GET_MUSIC_LIST = "baidu.ting.billboard.billList";
    private static final String METHOD_DOWNLOAD_MUSIC = "baidu.ting.song.play";
    private static final String METHOD_ARTIST_INFO = "baidu.ting.artist.getInfo";
    private static final String METHOD_SEARCH_MUSIC = "baidu.ting.search.catalogSug";
    private static final String METHOD_LRC = "baidu.ting.song.lry";
    private static final String PARAM_METHOD = "method";
    private static final String PARAM_TYPE = "type";
    private static final String PARAM_SIZE = "size";
    private static final String PARAM_OFFSET = "offset";
    private static final String PARAM_SONG_ID = "songid";
    private static final String PARAM_TING_UID = "tinguid";
    private static final String PARAM_QUERY = "query";

    public static void getSplash(final HttpCallback<Splash> callback) {
        OkHttpUtils.get().url(SPLASH_URL).build()
                .execute(new JsonCallback<Splash>(Splash.class) {
                    @Override
                    public void onResponse(Splash response, int id) {
                        Log.d(TAG, "onResponse: " + response.toString());
                        callback.onSuccess(response);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.onFail(e);
                    }

                    @Override
                    public void onAfter(int id) {
                        callback.onFinish();
                    }
                });
    }

    public static void downloadFile(String url, String destFileDir, String destFileName, final HttpCallback<File> callback) {
        OkHttpUtils.get().url(url).build()
                .execute(new FileCallBack(destFileDir, destFileName) {
                    @Override
                    public void inProgress(float progress, long total, int id) {
                    }

                    @Override
                    public void onResponse(File file, int id) {
                        callback.onSuccess(file);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.onFail(e);
                    }

                    @Override
                    public void onAfter(int id) {
                        callback.onFinish();
                    }
                });
    }

    public static void getSongListInfo(String type, int size, int offset, final HttpCallback<OnlineMusicList> callback) {
        OkHttpUtils.get().url(BASE_URL)
                .addParams(PARAM_METHOD, METHOD_GET_MUSIC_LIST)
                .addParams(PARAM_TYPE, type)
                .addParams(PARAM_SIZE, String.valueOf(size))
                .addParams(PARAM_OFFSET, String.valueOf(offset))
                .build()
                .execute(new JsonCallback<OnlineMusicList>(OnlineMusicList.class) {
                    @Override
                    public void onResponse(OnlineMusicList response, int id) {
                        callback.onSuccess(response);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.onFail(e);
                    }

                    @Override
                    public void onAfter(int id) {
                        callback.onFinish();
                    }
                });
    }

    public static void getMusicDownloadInfo(String songId, final HttpCallback<DownloadInfo> callback) {
        OkHttpUtils.get().url(BASE_URL)
                .addParams(PARAM_METHOD, METHOD_DOWNLOAD_MUSIC)
                .addParams(PARAM_SONG_ID, songId)
                .build()
                .execute(new JsonCallback<DownloadInfo>(DownloadInfo.class) {
                    @Override
                    public void onResponse(DownloadInfo response, int id) {
                        callback.onSuccess(response);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.onFail(e);
                    }

                    @Override
                    public void onAfter(int id) {
                        callback.onFinish();
                    }
                });
    }

    public static void getBitmap(String url, final HttpCallback<Bitmap> callback) {
        OkHttpUtils.get().url(url).build()
                .execute(new BitmapCallback() {
                    @Override
                    public void onResponse(Bitmap bitmap, int id) {
                        callback.onSuccess(bitmap);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.onFail(e);
                    }

                    @Override
                    public void onAfter(int id) {
                        callback.onFinish();
                    }
                });
    }

    public static void getLrc(String songId, final HttpCallback<Lrc> callback) {
        OkHttpUtils.get().url(BASE_URL)
                .addParams(PARAM_METHOD, METHOD_LRC)
                .addParams(PARAM_SONG_ID, songId)
                .build()
                .execute(new JsonCallback<Lrc>(Lrc.class) {
                    @Override
                    public void onResponse(Lrc response, int id) {
                        callback.onSuccess(response);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.onFail(e);
                    }

                    @Override
                    public void onAfter(int id) {
                        callback.onFinish();
                    }
                });
    }

    public static void searchMusic(String keyword, final HttpCallback<SearchMusic> callback) {
        OkHttpUtils.get().url(BASE_URL)
                .addParams(PARAM_METHOD, METHOD_SEARCH_MUSIC)
                .addParams(PARAM_QUERY, keyword)
                .build()
                .execute(new JsonCallback<SearchMusic>(SearchMusic.class) {
                    @Override
                    public void onResponse(SearchMusic response, int id) {
                        callback.onSuccess(response);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.onFail(e);
                    }

                    @Override
                    public void onAfter(int id) {
                        callback.onFinish();
                    }
                });
    }

    public static void getArtistInfo(String tingUid, final HttpCallback<ArtistInfo> callback) {
        OkHttpUtils.get().url(BASE_URL)
                .addParams(PARAM_METHOD, METHOD_ARTIST_INFO)
                .addParams(PARAM_TING_UID, tingUid)
                .build()
                .execute(new JsonCallback<ArtistInfo>(ArtistInfo.class) {
                    @Override
                    public void onResponse(ArtistInfo response, int id) {
                        callback.onSuccess(response);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.onFail(e);
                    }

                    @Override
                    public void onAfter(int id) {
                        callback.onFinish();
                    }


                });
    }

    public  static void getReceiveMess(String localID,final  HttpCallback<ReceiveMessGroup> callback ){

        OkHttpUtils
                .postString()
                .url(MusicApplication.ip + "enchant/getLatestMessages.action")
                .content(new Gson().toJson(new GetMess(localID)))//local user`s id
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new JsonCallback<ReceiveMessGroup>(ReceiveMessGroup.class) {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.onFail(e);
                    }

                    @Override
                    public void onResponse(ReceiveMessGroup response, int id) {
                        callback.onSuccess(response);
                    }

                    @Override
                    public void onBefore(Request request, int id) {
                        callback.onFinish();
                    }
                });
    }


    public  static void getChat(String localID,String remoteID,final  HttpCallback<ChatMessageGroup> callback ){
        OkHttpUtils
                .postString()
                .url(MusicApplication.ip + "enchant/getMessages.action")
                .content(new Gson().toJson(new GetChat(localID,remoteID)))//local user`s id and remote user`s id

                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new JsonCallback<ChatMessageGroup>(ChatMessageGroup.class) {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.onFail(e);
                    }

                    @Override
                    public void onResponse(ChatMessageGroup response, int id) {
                        callback.onSuccess(response);
                    }

                    @Override
                    public void onBefore(Request request, int id) {
                        callback.onFinish();
                    }
                });
    }

    public static void collectMusic(String localID, String localMusicID, String like, final HttpCallback<String> callback) {
        OkHttpUtils
                .postString()
                .url(MusicApplication.ip + "enchant/login.action")
                .content(new Gson().toJson(new CollectMusic(localID, localMusicID, like)))//local user`s id and remote user`s id
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.onFail(e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        callback.onSuccess(response);
                    }
                });
    }
}
