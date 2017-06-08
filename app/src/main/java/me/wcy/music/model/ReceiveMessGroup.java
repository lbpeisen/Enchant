package me.wcy.music.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by han78 on 2017/6/5.
 * 接受消息列表类
 */

public class ReceiveMessGroup {
    @SerializedName("lm")
    private ArrayList<ReceiveMess> receiveMessArrayList;

    public ReceiveMessGroup(ArrayList<ReceiveMess> receiveMessArrayList) {
        this.receiveMessArrayList = receiveMessArrayList;
    }

    public ArrayList<ReceiveMess> getReceiveMessArrayList() {
        return receiveMessArrayList;
    }

    public void setReceiveMessArrayList(ArrayList<ReceiveMess> receiveMessArrayList) {
        this.receiveMessArrayList = receiveMessArrayList;
    }

    public static class ReceiveMess {
        @SerializedName("lastMessage")/*最后一条消息*/
        private String lastMessage;
        @SerializedName("lastTime")/*随后一条消息的时间*/
        private String lastTime;
        @SerializedName("remoteID")/*发送方的*/
        private String remoteID;
        @SerializedName("remoteName")/*发送方的姓名*/
        private String remoteName;

        public ReceiveMess(String lastMessage, String lastTIme, String remoteID, String remoteName) {
            this.lastMessage = lastMessage;
            this.lastTime = lastTIme;
            this.remoteID = remoteID;
            this.remoteName = remoteName;
        }

        public String getLastMessage() {
            return lastMessage;
        }

        public void setLastMessage(String lastMessage) {
            this.lastMessage = lastMessage;
        }

        public String getLastTime() {
            return lastTime;
        }

        public void setLastTime(String lastTIme) {
            this.lastTime = lastTIme;
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
