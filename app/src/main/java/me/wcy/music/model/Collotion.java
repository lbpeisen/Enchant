package me.wcy.music.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by han78 on 2017/6/16.
 */

public class Collotion {
    @SerializedName("id")
    String id;
    @SerializedName("music_id")
    String music_id;
    @SerializedName("favor")
    String favor;

    public Collotion(String id, String music_id, String favor) {
        this.id = id;
        this.music_id = music_id;
        this.favor = favor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMusic_id() {
        return music_id;
    }

    public void setMusic_id(String music_id) {
        this.music_id = music_id;
    }

    public String getFavor() {
        return favor;
    }

    public void setFavor(String favor) {
        this.favor = favor;
    }
}
