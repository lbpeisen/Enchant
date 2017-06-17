package me.wcy.music.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.widget.Toast;

import me.wcy.music.fragment.SwipToastFragment;

/**
 * Toast工具类
 * Created by wcy on 2015/12/26.
 */
public class ToastUtils {
    private static Context sContext;
    private static Toast sToast;

    public static void init(Context context) {
        sContext = context.getApplicationContext();
    }

    public static void show(int resId) {
        show(sContext.getString(resId));
    }

    public static void show(String text) {
        if (sToast == null) {
            sToast = Toast.makeText(sContext, text, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(text);
        }
        sToast.show();
    }

    public static void showDialog(Context contenxt,String text){
        Activity activity = (Activity) contenxt;
        FragmentTransaction mFragTransaction = activity.getFragmentManager().beginTransaction();
        Fragment fragment = activity.getFragmentManager().findFragmentByTag("dialogFragment");
        if(fragment!=null){
            mFragTransaction.remove(fragment);
        }
        SwipToastFragment swipToastFragment = new SwipToastFragment(text);
        swipToastFragment.show(mFragTransaction, "dialogFragment");
    }
}
