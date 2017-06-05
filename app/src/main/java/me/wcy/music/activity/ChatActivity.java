package me.wcy.music.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

import jp.bassaer.chatmessageview.models.Message;
import jp.bassaer.chatmessageview.views.ChatView;
import jp.bassaer.chatmessageview.views.MessageView;
import me.wcy.music.R;
import me.wcy.music.utils.binding.Bind;

/**
 * Created by han78 on 2017/5/31.
 */

public class ChatActivity extends BaseActivity {

    @Bind(R.id.chat_view)
    ChatView mChatView;
    //User icon

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        if (!checkServiceAlive()) {
            return;
        }

        //Create
        mChatView.setLeftBubbleColor(Color.WHITE);
        mChatView.setSendIcon(R.drawable.ic_action_send);
        mChatView.setRightMessageTextColor(Color.WHITE);
        mChatView.setLeftMessageTextColor(Color.BLACK);
        mChatView.setUsernameTextColor(Color.BLACK);
        mChatView.setSendTimeTextColor(Color.BLACK);
        mChatView.setDateSeparatorColor(Color.WHITE);
        //
        Reflash();
    }

    private void Reflash() {
        final Bitmap myIcon = BitmapFactory.decodeResource(getResources(), R.drawable.face_2);
        Message mMessage = new Message.Builder()
                .setMessageText("This is a content")
                .setUserIcon(myIcon)
                .setUserName("系统通知")
                .setRightMessage(false)
                .setDateCell(true)
                .build();
        for (int i = 0; i < 5 ;i++){
            mChatView.receive(mMessage);
        }

    }

}
