package me.wcy.music.fragment;

import android.app.Dialog;
import android.os.Bundle;

import com.labo.kaji.swipeawaydialog.SwipeAwayDialogFragment;

import cn.refactor.lib.colordialog.ColorDialog;

/**
 * Created by han78 on 2017/6/17.
 */

public class SwipToastFragment extends SwipeAwayDialogFragment {
    private String text;
    public SwipToastFragment(String text) {
        this.text = text;
    }
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ColorDialog colorDialog = new ColorDialog(getActivity());
        colorDialog.setTitle("消息");
        colorDialog.setContentText(text);
        colorDialog.setPositiveListener("确定", new ColorDialog.OnPositiveListener() {
            @Override
            public void onClick(ColorDialog colorDialog) {
                colorDialog.dismiss();
            }
        });
        return colorDialog;
    }
}
