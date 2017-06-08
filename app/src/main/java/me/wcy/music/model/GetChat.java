package me.wcy.music.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by han78 on 2017/6/1.
 */

public class GetChat {
    @SerializedName("send_user_id")
    private String send_user_id;
    @SerializedName("to_user_id")
    private String to_user_id;

    public GetChat(String send_user_id, String to_user_id) {
        this.send_user_id = send_user_id;
        this.to_user_id = to_user_id;
    }

    public String getSend_user_id() {
        return send_user_id;
    }

    public void setSend_user_id(String send_user_id) {
        this.send_user_id = send_user_id;
    }

    public String getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(String to_user_id) {
        this.to_user_id = to_user_id;
    }
}
