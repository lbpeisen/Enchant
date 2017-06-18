package me.wcy.music.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by han78 on 2017/6/16.
 */

public class SendComment {
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("music_id")
    private String music_id;
    @SerializedName("comment")
    private String comment;

    public SendComment(String user_id, String music_id, String comment) {
        this.user_id = user_id;
        this.music_id = music_id;
        this.comment = comment;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMusic_id() {
        return music_id;
    }

    public void setMusic_id(String music_id) {
        this.music_id = music_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
