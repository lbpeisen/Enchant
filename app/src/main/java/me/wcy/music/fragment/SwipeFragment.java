package me.wcy.music.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.labo.kaji.swipeawaydialog.SwipeAwayDialogFragment;

import me.wcy.music.R;

public class SwipeFragment extends SwipeAwayDialogFragment {

    private EditText editText;
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
                String comment = editText.getText().toString();
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
