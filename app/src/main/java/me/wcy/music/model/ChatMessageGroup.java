package me.wcy.music.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by han78 on 2017/6/6.
 */

public class ChatMessageGroup {
    @SerializedName("messages")
    ArrayList<ChatMessage> messages;

    public ChatMessageGroup(ArrayList<ChatMessage> chatMessageArrayList) {
        this.messages = chatMessageArrayList;
    }

    public ArrayList<ChatMessage> getChatMessageArrayList() {
        return messages;
    }

    public void setChatMessageArrayList(ArrayList<ChatMessage> chatMessageArrayList) {
        this.messages = chatMessageArrayList;
    }

    public static class ChatMessage {
        @SerializedName("content")
        private String content;
        @SerializedName("create_time")
        private String create_time;
        @SerializedName("title")
        private String title;

        public ChatMessage(String content, String create_time, String title) {
            this.content = content;
            this.create_time = create_time;
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
