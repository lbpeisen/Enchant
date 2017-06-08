package me.wcy.music.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by han78 on 2017/6/1.
 */

public class GetMess {
    @SerializedName("localID")
    private String localID;

    public GetMess(String localID) {
        this.localID = localID;
    }

    public String getLocalID() {
        return localID;
    }

    public void setLocalID(String localID) {
        this.localID = localID;
    }
}
