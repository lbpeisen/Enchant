package me.wcy.music.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.labo.kaji.swipeawaydialog.SwipeAwayDialogFragment;

import me.wcy.music.R;
import me.wcy.music.application.MusicApplication;
import me.wcy.music.http.HttpCallback;
import me.wcy.music.http.HttpClient;
import me.wcy.music.utils.ToastUtils;

import static android.content.Context.MODE_PRIVATE;

public class SwipeFragment extends SwipeAwayDialogFragment {
    private EditText editText;
    private String musicID;
    private String localid;


    public SwipeFragment(long musicID,int localid) {
        this.musicID = String.valueOf(musicID);
        this.localid = String.valueOf(localid);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("回复");
        editText = new EditText(getActivity());
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        builder.setView(editText);
        builder.setPositiveButton(android.R.string.ok, new Dialog.OnClickListener() {
            /*
            * 回复操作
            * */
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (MusicApplication.getLoginState() == 0) {
                    ToastUtils.show("请先登录");
                    return;
                }
                String comment = editText.getText().toString();
                HttpClient.sendComent(localid, musicID, comment, new HttpCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        ToastUtils.show("发送成功");
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
