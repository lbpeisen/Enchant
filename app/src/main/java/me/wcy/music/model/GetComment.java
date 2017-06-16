package me.wcy.music.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by han78 on 2017/6/16.
 */

public class GetComment {
    @SerializedName("music_id")/*远程ID*/
    private String music_id;

    public GetComment(String music_id) {
        this.music_id = music_id;
    }

    public String getMusic_id() {
        return music_id;
    }

    public void setMusic_id(String music_id) {
        this.music_id = music_id;
    }
}
