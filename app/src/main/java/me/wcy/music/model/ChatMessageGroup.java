package me.wcy.music.model;

import java.util.ArrayList;

/**
 * Created by han78 on 2017/6/6.
 */

public class ChatMessageGroup {
    ArrayList<ChatMessage> chatMessageArrayList;

    public ChatMessageGroup(ArrayList<ChatMessage> chatMessageArrayList) {
        this.chatMessageArrayList = chatMessageArrayList;
    }

    public ArrayList<ChatMessage> getChatMessageArrayList() {
        return chatMessageArrayList;
    }

    public void setChatMessageArrayList(ArrayList<ChatMessage> chatMessageArrayList) {
        this.chatMessageArrayList = chatMessageArrayList;
    }
}
