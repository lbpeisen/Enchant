package me.wcy.music.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by han78 on 2017/6/5.
 */

public class ReceiveMessGroup {
    @SerializedName("lm")
    private ArrayList<ReceiveMess> lm;

    public ReceiveMessGroup(ArrayList<ReceiveMess> lm) {
        this.lm = lm;
    }

    public ArrayList<ReceiveMess> getLm() {
        return lm;
    }

    public void setLm(ArrayList<ReceiveMess> lm) {
        this.lm = lm;
    }

    public static class ReceiveMess {
        @SerializedName("lastMessage")
        private String lastMessage;
        @SerializedName("lastTIme")
        private String lastTIme;
        @SerializedName("remoteID")
        private String remoteID;
        @SerializedName("remoteName")
        private String remoteName;

        public ReceiveMess(String lastMessage, String lastTIme, String remoteID, String remoteName) {
            this.lastMessage = lastMessage;
            this.lastTIme = lastTIme;
            this.remoteID = remoteID;
            this.remoteName = remoteName;
        }

        public String getLastMessage() {
            return lastMessage;
        }

        public void setLastMessage(String lastMessage) {
            this.lastMessage = lastMessage;
        }

        public String getLastTIme() {
            return lastTIme;
        }

        public void setLastTIme(String lastTIme) {
            this.lastTIme = lastTIme;
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
    }
}
