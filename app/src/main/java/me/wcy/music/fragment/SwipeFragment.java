package me.wcy.music.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.labo.kaji.swipeawaydialog.SwipeAwayDialogFragment;

import me.wcy.music.activity.TimeLineActivity;
import me.wcy.music.application.MusicApplication;
import me.wcy.music.http.HttpCallback;
import me.wcy.music.http.HttpClient;
import me.wcy.music.utils.ToastUtils;

import static android.content.Context.MODE_PRIVATE;

public class SwipeFragment extends SwipeAwayDialogFragment {
    private EditText editText;
    private String musicID;
    private String localid;
    private Context context;

    public SwipeFragment(long musicID,int localid,Context context) {
        this.musicID = String.valueOf(musicID);
        this.localid = String.valueOf(localid);
        this.context = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

//        ColorDialog colorDialog = new ColorDialog(getActivity());
//        editText = new EditText(getActivity());
//        editText.setFocusable(true);
//        editText.setFocusableInTouchMode(true);
//        editText.requestFocus();
//        colorDialog.getWindow().setContentView(editText);
//        colorDialog.setPositiveListener("发送", new ColorDialog.OnPositiveListener() {
//            @Override
//            public void onClick(ColorDialog colorDialog) {
//
//            }
//        });
//        colorDialog.setNegativeListener("取消", new ColorDialog.OnNegativeListener() {
//            @Override
//            public void onClick(ColorDialog colorDialog) {
//
//            }
//        });
//        return  colorDialog;


        editText = new EditText(getActivity());
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("回复");
        builder.setView(editText);
        builder.setPositiveButton(android.R.string.ok, new Dialog.OnClickListener() {
            /*
            * 回复操作
            * */
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String comment = editText.getText().toString();
                HttpClient.sendComent(localid, musicID, comment, new HttpCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        if (s.contains("\"STATUS\":1000")){
                            ToastUtils.showDialog(context,"发送成功");
                            TimeLineActivity activity = (TimeLineActivity) context;
                            activity.reflash();

                           //ToastUtils.show("发送成功");
                        }else {
                            ToastUtils.showDialog(context,"失败");
                            //ToastUtils.show("发送失败");
                        }
                    }

                    @Override
                    public void onFail(Exception e) {
                        ToastUtils.show("发送失败");
                    }
                });
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(android.R.string.cancel, new Dialog.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return builder.create();
    }
}
