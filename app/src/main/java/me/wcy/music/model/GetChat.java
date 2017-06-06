package me.wcy.music.model;

/**
 * Created by han78 on 2017/6/1.
 */

public class GetChat {
    private String localID;
    private String remoteID;

    public GetChat(String localID, String remoteID) {
        this.localID = localID;
        this.remoteID = remoteID;
    }

    public String getLocalID() {
        return localID;
    }

    public void setLocalID(String localID) {
        this.localID = localID;
    }

    public String getRemoteID() {
        return remoteID;
    }

    public void setRemoteID(String remoteID) {
        this.remoteID = remoteID;
    }
}
