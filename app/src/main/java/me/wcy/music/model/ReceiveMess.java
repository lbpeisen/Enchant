package me.wcy.music.model;

/**
 * Created by han78 on 2017/6/1.
 */

public class ReceiveMess {
    private String remoteID;
    private String remoteName;
    private String mess_title;
    private String time;

    public ReceiveMess(String remoteID, String remoteName, String mess_title, String time) {
        this.remoteID = remoteID;
        this.remoteName = remoteName;
        this.mess_title = mess_title;
        this.time = time;
    }

    public String getRemoteID() {
        return remoteID;
    }

    public void setRemoteID(String remoteID) {
        this.remoteID = remoteID;
    }

    public String getRemoteName() {
        return remoteName;
    }

    public void setRemoteName(String remoteName) {
        this.remoteName = remoteName;
    }

    public String getMess_title() {
        return mess_title;
    }

    public void setMess_title(String mess_title) {
        this.mess_title = mess_title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
