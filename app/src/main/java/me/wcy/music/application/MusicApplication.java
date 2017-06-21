package me.wcy.music.application;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tencent.bugly.Bugly;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import me.wcy.music.BuildConfig;
import me.wcy.music.api.KeyStore;
import me.wcy.music.http.HttpInterceptor;
import me.wcy.music.utils.Preferences;
import okhttp3.OkHttpClient;


public class MusicApplication extends Application {

    public static String key = "vfXSA6XTVDYpr2HL0xOUdVqXbcJoU99C";//api_key
    public static String secret = "AHg02tIO3JY6os6niQT93LWFfWcfcVvt";//api_secret
    //    public static String ip = "http://47.94.0.178:8080/";//api_secret
    public static String ip = "http://123.207.189.238:8080/";//api_secret
    public static String faceset_token = "02977daeea0789ce074997fe0c79523c";//faceset_token
    public static int isLogin = 0;

    @Override
    public void onCreate() {
        super.onCreate();

        AppCache.init(this);
        AppCache.updateNightMode(Preferences.isNightMode());
        initOkHttpUtils();
        initImageLoader();
        initBugly();
    }

    private void initOkHttpUtils() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new HttpInterceptor())
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    private void initImageLoader() {
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSize(2 * 1024 * 1024) // 2MB
                .diskCacheSize(50 * 1024 * 1024) // 50MB
                .build();
        ImageLoader.getInstance().init(configuration);
    }

    private void initBugly() {
        if (!BuildConfig.DEBUG) {
            Bugly.init(this, KeyStore.getKey(KeyStore.BUGLY_APP_ID), false);
        }
    }

    public static void login() {
        isLogin = 1;
    }

    public static int getLoginState() {
        return isLogin;
    }

    public static void logout() {
        isLogin = 0;
    }
}
